#ifndef CRITICAL_H
#define CRITICAL_H

#include <pthread.h>

#define	CRITICAL_SECTION(MUTEX, BODY)	\
		pthread_mutex_lock(&MUTEX);		\
		BODY							\
		pthread_mutex_unlock(&MUTEX);
#endif
