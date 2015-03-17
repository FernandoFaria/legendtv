# Introduction #
As described on the [design goals page](DesignGoals.md), the C, C++, and Java compilers all provide various types of code checking that can save us time during testing. Our main concern, then, is to write our code in such a way that we can take advantage of this built-in checking as much as possible. Compiling is not as expensive or time consuming as it once was, so we should endeavor to burden the compiler with as much validation as it can offer. What follows are several guidelines we can follow during implementation that should help us accomplish this.

# Guidelines #

## Heed Warnings Like Errors ##
This guideline is almost cliché because so many projects employ it, but it hasn't lost its importance. Essentially, we need to pretend that a compiler warning is the same as an error, and write our code to avoid it. This mostly applies to C and C++ code, as Java issue warnings only for unchecked/unsafe operations with generics, but we should aim to avoid these warnings as well, if we can.

For many developers, this guideline may seem a bit harsh -- after all, there are several circumstances under which the C/C++ compiler issues warnings for perfectly valid, safe code. But, instead of dismissing the warning, try to re-write the code to clarify what's happening. If the warning is about a conversion between a pointer and an integer, try adding a type cast to clarify that this is the expected behavior. The main reason to do this is because it forces you to re-think your expectations, which might uncover a flaw in your logic. If you cannot prevent a warning after having re-written the code several times then, and only then, should you consider adding a pragma declaration or similar statement to suppress the warning.

Enumerated types are preferred for the simple reason that the compiler can guarantee an enumerated type variable contains only one of the accepted, expected values; it cannot make any such guarantee if the variable is instead an integer. This means that if we use integer constants, we'll need additional code at run-time to ensure that the integer value is in the range of defined constants -- code which will have to be updated if those constants change. And, of course, this checking occurs at run time when the user will be using the program, not at compile time when it has a higher chance of getting caught and fixed by the developer. Lastly, there's the simple fact that a simple `enum` type declaration is much cleaner and clearer than several macro or constant definitions. Clearly, enumerated types are a better way to go for fixed sets of data.

**A word about Java enumerated types:** Enumerated types are somewhat new in the Java language, having been introduced in Java 1.5/5.0. As a result, several veteran Java programmers might not be familiar with them, or might prefer the old style of integer constants. We've already discussed the type-checking advantages that enumerated types offer, but Java extends the advantages even more to include the following benefits:
  * Enumerated types can be enumerated with the Java 1.5/5.0 "foreach"-style `for` loop
  * Subsets of enumerated type values can be obtained and used like collections (replacing the need for exclusive ORs/ANDs to pass several values)
  * Enumerated type values can be looked-up at run-time by name.
  * Enumerated types can have constructors so that additional, relevant data can be tacked on to each constant.
  * Enumerated types can have additional methods that are callable on any of the enumerated values.

## Avoid Using Macros (C/C++) ##
This guideline is sure to receive harsh criticism from C/C++ veterans, but there is a good foundation for it. Admittedly, the C/C++ preprocessor is a very powerful tool that allows developers to perform several "neat" tricks to generate code, remove code that has not been selected at compile-time, and much more. Many developers feel that by putting large blocks of frequently referenced code in macros, they can reduce the amount of code they need to maintain while saving the cost of a function call. Unfortunately, this power comes at the steep cost of context.

Specifically, the C/C++ preprocessor is not **_context-aware_**. That means that using macro "functions" simply causes the preprocessor to drop the text of the macro in place of the macro "function call" (i.e. macro invocation) and replace each reference to one of the macro parameters with the text that was provided for that parameter in the macro invocation. As a result, if a function call or calculation is provided as one of the parameters to the macro, that function call or calculation is dropped in each time that the parameter is referenced in the macro, causing it to be evaluated multiple times. This is inefficient and may lead to unexpected results, which cancels out the savings that might be gained by avoiding a function call.

The following example may help to illustrate this point:
```
#include <stdio.h>

#define A_SQUARED(a) a*a

int main()
{
   int a = 1;

   printf("(A + 1)^2 is %d", A_SQUARED(++a));
}
```

One would expect that `(A+1)^2` is `2^2`, which is 4, but the answer turns out to be 6 in this case, as the `++a` part is evaluated each time that `a` is referenced in the macro. As a result, it is easy to see that unlike normal C function calls, macro parameters are evaluated by name instead of by value.

In addition to the problem of multiple evaluation, there is also the problem of **_code distance_**. Since macros are not context-sensitive, they affect the context in which they live; since macros are usually defined in header files or at the beginning of source files, the code surrounding the macro is separate from the code in the macro. These two elements together are even more volatile, because a developer needs to be fully aware of what variables a macro might declare or change; otherwise, unexpected behavior may result. At the very least, it makes for confusing and hard-to-follow code.

In short, preprocessor macro expansion is powerful, but dangerous and easily abused. Although a macro may provide a performance benefit, as it is the equivalent to an inline function, it is more likely that it would lead to duplicate execution or unexpected behavior as a result of bashed local context. If code is frequently referenced, it is far more preferable to put it in a proper function rather than a macro. Any decent C/C++ compiler will optimize/inline that function if it feels necessary, so there really isn't a risk to performance.

**A notable exception:** Although macros are strongly discouraged, there are very rare circumstances where they may be preferable or even required. One such case is in our JNI fault tolerance code, which requires callers to setup a stack "save point" upon entry to a JNI invocation.

The main reason we need a macro here is because the save point must live in a function whose invocation is still active (i.e. the invoked JNI function), so we cannot put the logic for the saving in a separate function that is called by the JNI invocation, as the function would return control before we needed to use the save point. Furthermore, it would be a bad idea to have the code for saving the stack at the beginning of each JNI invocation, as the code might need to be changed or could be copied incorrectly into one or more invocations, causing a maintenance nightmare. As a result, the only "clean" way to minimize caller responsibilities while at the same time properly honor the pre-conditions of `setjmp()` are to use a macro that is invoked in each JNI invocation function.

In order to reduce the risk of context clobbering, the macros utilized by JNI fault tolerance are usually called with a block of code as the first parameter. The block causes the compiler to consider the code inside the macro as a separate, inner scope, which reduces some of the bashing that might occur from variables defined and manipulated in the macro itself.

## Avoid Using Strings as an Interface ##
## Use Constants Instead of Inline Strings ##
## Minimize the Use of Reflection (Java) ##
## Use XML Schemas ##

