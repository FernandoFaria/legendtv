# CC=	gcc-3.3 -march=pentium4 -Wall -I/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Headers/
CC=gcc
CFLAGS=-g -fPIC -std=gnu99 -Wall
LDFLAGS=-O3 -ldl -shared

HEADERS=$(wildcard ./core/*.h) $(wildcard ./core/generated/*.h) $(wildcard ./faults/*.h)
SOURCES=$(wildcard ./core/*.c) $(wildcard ./faults/*.c)
OBJECTS=$(SOURCES:.c=.o)

.c.o:
	$(CC) $(CFLAGS) -c $< -o $@

all: libLTV-JVLC.so

libLTV-JVLC.so: $(HEADERS) $(OBJECTS)
	${CC} $(LDFLAGS) -Wl,-soname,$@ -lvlc-control $(OBJECTS) -o $@
	
clean:
	rm -f $(OBJECTS)