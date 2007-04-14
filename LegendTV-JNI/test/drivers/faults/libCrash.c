#define _GNU_SOURCE

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <pthread.h>
#include <signal.h>

#include "../../../src/faults/jFaults.h"
#include "../../../src/faults/memTrack.h"
#include "libCrash.h"

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved)
{
	printf("JNI Loading... ");
	
	JFault_init();
	MemTrack_start("libCrash");
	
	printf("done.\n");
	
	return JNI_VERSION_1_4;
}

JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *jvm, void *reserved)
{	
	printf("JNI Unloading... ");
	
	MemTrack_stop("libCrash", 1);
	JFault_finalize();

	printf("done.\n");
}

void memLeak()
{
	int		leakSize	= 8 * 1024 * 1024;
	void*	memLeak;
	
	// Allocate dynamic memory, to test clean-up
	memLeak	= malloc(leakSize);
	memset(memLeak, 0, leakSize);
	
	memLeak	= malloc(leakSize / 2);
	memset(memLeak, 0, leakSize / 2);
	
	memLeak	= malloc(leakSize / 4);
	memset(memLeak, 0, leakSize / 4);
}

void segFault()
{
	int*	badPointer	= 0;
	
	*badPointer	= 123;
}

void divideByZero()
{
	printf("5 divided by zero is %d", 5/0);
}

void illegalInstruction()
{
	unsigned char	insn[4]			= {0xff, 0xff, 0xff, 0xff};
	void 			(*badFunc)()	= (void (*)())insn;
	
	badFunc();
}

void crash()
{
	int	j;

	srand((unsigned int)time(NULL));

	j	= 1 + (int)(3.0 * rand() / (RAND_MAX + 1.0));

	switch (j)
	{
		case 1:	
			segFault();
			break;
			
		case 2:
			divideByZero();
			break;
			
		case 3:
			illegalInstruction();
			break;
	}
	
	printf("In crash!!\n");
}

void* asyncCrash(void* unused)
{
	crash();
	
	return NULL;
}

void crash2(JNIEnv *env)
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		memLeak();
		memLeak();
		crash(NULL);
		
		printf("After crash!!\n");
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_test_drivers_faults_LibCrash_noCrash(JNIEnv *env, jobject self)
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		printf("No crashies!\n");
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_test_drivers_faults_LibCrash_asyncCrash(JNIEnv *env, jobject self)
{
	pthread_t	newThread;
		
	JFAULT_TOLERANT_SCOPE(env,
	{
		printf("Creating async crash...\n");
		pthread_create(&newThread, NULL, asyncCrash, NULL);
	});
	
	//pthread_join(newThread, NULL);
	
	printf("Returning from async crash...\n");
}

JNIEXPORT int JNICALL Java_com_googlecode_legendtv_test_drivers_faults_LibCrash_crash(JNIEnv *env, jobject self)
{
	JFAULT_TOLERANT_SCOPE_WITH_RETURN(env,
	{
		crash2(env);
	
		printf("After crash2!!\n");
	}, 0);
	
	return 1;
}
