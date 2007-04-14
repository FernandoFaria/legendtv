# CC=	gcc-3.3 -march=pentium4 -Wall -I/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Headers/
CC=gcc
CFLAGS=-g -fPIC -std=gnu99 -Wall
LDFLAGS=-O3 -shared

HEADERS=$(wildcard ../../../src/faults/*.h) $(wildcard ./*.h)
SOURCES=$(wildcard ../../../src/faults/*.c) $(wildcard ./*.c) 
OBJECTS=$(SOURCES:.c=.o)

.c.o:
	$(CC) $(CFLAGS) -c $< -o $@

all: libCrash.so

libCrash.so: $(HEADERS) $(OBJECTS)
	${CC} $(LDFLAGS) -Wl,-soname,$@ $(OBJECTS) -o $@
	
clean:
	rm -f $(OBJECTS)