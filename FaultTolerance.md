# Requirement Details #
One of the greatest design strengths of the Java language is that code is checked at both compile time and run time; code that clearly will result in issues at run time is rejected by the compiler, while code that encounters unexpected results at run time will likely throw a run time exception. If the exception is expected, the application might be able to deal with the problem and continue functioning properly. In extreme cases, an exception might populate up the stack and kill its parent thread, but even this is not fatal if the application is designed to cope with thread death.

Unfortunately, this feature isn't always available to us. There is a high run time performance price for this ability, which is less than desirable for an application that needs to handle high resolution (i.e. HD 1080i) video. Moreover, there are countless open source video applications that are written in C and C++ that do more than an adequate job performing this task. Consistent with LegendTV's [design goals](DesignGoals.md), it would be impractical and unwise to re-code these applications in the Java language for the purposes of our project. Therefore, we must use JNI, the Java Native Interface, to utilize this existing native code.

Indeed, the commitment to re-use unchecked code seems to conflict with the commitment to stability; using code that is not checked at run time weakens the strength of our system because we can never be sure that the unchecked code is completely safe. One way to counter this problem is to commit to more extensive testing of the unchecked code we use, but this causes us to take on more responsibility for that code and is again inconsistent with our [design goals](DesignGoals.md). What we need to do is instead contain faults that occur in native code as if they were faults that occurred in checked Java code, returning control to (safe) Java code to handle.

For this purpose, we need to implement JNI fault tolerance -- a system that can intercept segmentation faults, floating-point exceptions, illegal instructions, and other faults in native code, and then re-cast them as Java exceptions. This would allow us to confine the "damage" of a native code problem to prevent it from taking down the entire JRE. Ideally, this would be confined to the faulting thread -- that is, the Java thread that invoked the native code -- which in turn would allow us keep the rest of the system running and re-start the thread, if necessary and possible.

## Benefit / Rationale ##
The benefit to fault-tolerant JNI invocations is clear: greater system stability. JNI fault tolerance will allow LegendTV to stay up and running in light of faults in code that it would otherwise not control, and that would otherwise take down the entire system.

## Justifiable Need ##
As outlined in the [design goals](DesignGoals.md) under "Stability isn't a luxury", LegendTV is targeted at users who will likely be running it on a dedicated PVR system -- a system that is intended to be up 24 hours a day, 7 days a week, and always recording the user's shows. Therefore, the user has a certain expectation that LegendTV will be able to stay up as long as their system is running.

Unfortunately, source code changes rapidly; not only our own code, but also the code for  the libraries and code that we're going to be linking against. We can careful oversee the changes in our project, but we can't and shouldn't take it upon ourselves to oversee each of the changes that occur in external projects. We also don't want to box ourselves into sticking with a particular legacy version of those external projects just because we know that version is stable. Clearly, then, fault tolerance is necessary for us to embrace ever-changing external code while still providing our end users with high reliability.

## Prior Art / Reference Implementations ##
To date, we are not aware of any implementations of this type of fault tolerance in C code. Many experienced C programmers feel that attempting to regain control after a segmentation fault is unsafe, risky, and error-prone. Indeed, these are recognized as challenges to overcome, but the lack of reference implementations should not be discouraging.

# Implementation Details #
## Technical Overview ##
#### Signal Handling ####
The key idea for fault tolerance is **signal handling**. The operating system enables applications to register functions that will be called when certain events occur. Some of these signals are for things like the user pressing Ctrl-C (SIGINT), the user attempting to kill the program with the "kill" command (SIGKILL), etc. In addition to these signals, there are others that are more applicable to what we are trying to do:
  * SIGSEGV - Signal received when an application attempts to access memory incorrectly.
  * SIGFPE - Signal received when an application attempts an invalid mathematical operation; usually a divide-by-zero or similar.
  * SIGILL - Signal received when an application attempts an operation that is not understood by the CPU.

#### Default Handlers and Undocumented Behavior ####
The default handler for fatal signals usually signals the OS to remove the program from memory, print a message to the user that describes the signal (i.e. "Segmentation Fault"), and to optionally dump core. If the faulting component is a shared library, then the program that invoked the faulting library is removed from memory. This means that if we are using JNI, and the native code we are interfacing with causes a fatal signal, the entire JRE (our program and all) dies. This is what we need to fix with this module.

Along those lines, if one consults the documentation for fatal signals, he will undoubtedly encounter the following:

> _According to POSIX, the behaviour of a process is undefined after it ignores a SIGFPE, SIGILL, or SIGSEGV signal that was not generated by the kill(2) or the raise(3) functions. Integer division by zero has undefined result. On some architectures it will generate a SIGFPE signal. (Also dividing the most negative integer by -1 may generate SIGFPE.) Ignoring this signal might lead to an endless loop._

Clearly, it is apparent that handling these signals is a delicate matter. An endless loop will result if we attempt to return control to Java and then return normally from the signal handler. This is because control will return the instruction in the faulting process that was executed right before the signal occurred. Since that instruction is what generated the signal in the first place, it will trigger the signal handler again, which will handle the fault, and return control again to the faulting instruction. Essentially, the lack of explicit flow control causes the program to return to the last point it knew about right before the fault, and that just happens to be the code that caused the fault in the first place.



_This section should outline the detailed technical aspects of the feature implementation. The implementation should ideally be as simple and reasonable as possible for the current project code base, unless suggesting major changes to the code base is clearly justified (i.e. it is all right to suggest major change when you feel major change is needed). (Think: **How can we feasibly implement the feature?**)_

## Affected Components ##
_This section should outline what components of the project will be affected by implementing this feature. (Think: **What would need to change or could break if we implement the feature?**)_

## Potential Tools / Resources ##
_This section should outline what tools, technologies, or existing open source projects might prove useful when implementing this feature. (Think: **What can we use to implement the feature?**)_

## Prospective Obstacles ##
_This section should outline any risks or obstacles that could make implementation of this feature difficult or impossible. This section should be continuously updated throughout implementation to include new obstacles as they are discovered. (Think: **What could delay implementation of the feature?**)_

## Current Progress ##
_This section should indicate how far along the implementation of this feature is, if any implementation has been done. (Think: **What has been done to implement the feature already?**)_



















