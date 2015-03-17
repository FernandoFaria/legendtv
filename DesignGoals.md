# Introduction #
Any good software project realizes the importance of architecture and goals, and LegendTV is no different. This page outlines the goals that LegendTV developers must all be committed to for the project to succeed.

This list is by no means comprehensive -- in addition to acknowledging the importance of goals and architecture, a project must also acknowledge the importance of adaptation to change. Thus, as time goes on, this page should grow to cover the goals that the development team feels are best suited for the project at a particular time.

# Architecture Goals #
These design goals are targeted at the underlying system architecture rather than what the user is likely to see. Regardless, these goals aim to keep LegendTV easy to maintain, which, in turn, allows us to make a positive user experience possible.

## 1. Embrace change ##
Source code is just as much of a creative expression as it's a formal specification, but that doesn't make it sacred. If a new requirement emerges that could be implemented either as a short term hack that affects only one or two modules or an elegant re-design of several modules, we should have the integrity to choose the latter. Otherwise, we may start with a good design, but end up with a mess of code at the end if we aren't willing to change that design when necessary.

**Key Idea:** Requirements are dynamic, and if we are to meet them, we must realize that source code and design must also be dynamic.

## 2. Use checked interfaces whenever possible ##
In a complex project, there are many different modules that need to talk to each other -- classes, data types, libraries, clients and servers, etc. As a result, it is not uncommon for the interface between two modules to get out-of-sync when one is changed but the other is not; such a discrepancy might merely result in a compile error in the best case scenario, or a crash or exception at run time in the worst case. As developers, we should obviously prefer the former, but how do we accomplish this?

The answer is not to see the compiler as the enemy -- a syntax or type error is the compiler's way of telling us that something we've written won't work, or _might not_ work (which means it most likely _won't work_ for at least one user at runtime). The compiler is ready and willing to look over our shoulder and let us know how our homework looks, but in return, we need to give it as much information about what we expect. In other words, we need to take advantage of _**checked interfaces**_.

This topic is too extensive to cover here; for more information, please see the page on [checked interface guidelines](CheckedInterfaceGuidelines.md).

**Key Idea:** The compiler can let us know when the code we write might not work; we need to take advantage of this as much as possible.

## 3. Don't re-invent the wheel ##
PVR software is not something new -- several open and closed source projects compete with LegendTV. Yet this is a strength more than a weakness because we can learn what works and what doesn't from our competitors. And, from the open source projects, we can even borrow some of the code for the stuff that works, and avoid writing code that's similar to the stuff that doesn't.

In addition to competing projects, there are also projects that are strongly related to LegendTV; VLC, MPlayer, and Xine just to name a few. If we can borrow code from these projects, or better still, if we link against the libraries that many of these projects provide, we can avoid having to re-design, re-implement, and re-test the same code they already possess.

**Key Idea:** The less code we have to write to get the job done, the less code we have to test, and the less code we have to maintain.

## 4. Delegate responsibility for external modules ##
In goal #1, we've established that we shouldn't re-invent the wheel, but does that mean that we can tweak the wheel to our needs? If necessary, yes, we should. In some cases, it would be better to round off the wheel and design our system properly than to compensate for a cube in the rest of our system. But, if the wheel is well-maintained, we should delegate the responsibility of changing it to those who know how it rolls best.

In other words, if we integrate VLC into our project, then we should be hesitant to modify the internal workings of VLC ourselves. If there's something that we don't like about the interface, we should consider wrapping it with something nicer in our code instead of modifying the interface directly. If we encounter a bug in VLC, we should inform the VLC project to fix it. This benefits us both -- we don't have to maintain custom patches and a custom build of VLC, and the VLC project produces a higher-quality product.

Thinking in a different way, this is similar to applying the OOP principle of encapsulation to the project. Each module that we borrow from other projects should be thought of a black box with an input and output. If we aren't happy with the way the black box, we shouldn't take it upon ourselves to try to understand how the black box works and to maintain our own black box. Instead, we need to ask the authors who see it as a white box to make it work the way we want, or we need to look for another black box that better suits our needs.

**Key Idea:** We borrow code to reduce our maintenance responsibilities, but changes to that code make us responsible for it again. Thus, we should endeavor to change the code we borrow as little as possible.

# Usability Goals #
These design goals are targeted directly at the user experience. Although the underlying architecture is what enables us to maintain LegendTV, usability is what enables users to keep using it.

## 5. Easy things don't have to be limited ##
LegendTV's is primarily targeted at the Linux platform, which has traditionally been dominated by power users and developers. Yet there are countless computer users who would like to have a feature-rich, stable, and flexible PVR application without having to be experts about the underlying technology.

With this in mind, LegendTV must offer a compelling, powerful set of features, but present them in a clean and easy-to-understand way. Just because users might not know much about how LegendTV works doesn't mean they're stupid -- the UI should reflect this. This is along the same line's as Einstein's oft-quoted, "a theory should be as simple as possible, but no simpler." We don't need to dumb down the interface or limit what the user can do just so that our program is easy to use. Conversely, we shouldn't overwhelm the user with screen after screen of settings, options, and tweaks. The middle ground is clear -- create an interface that provides the user control when he knows what he's doing, and offers to hold his hand when he doesn't.

**Key Idea:** Users take time to learn things and can't handle too much information at once, but that doesn't make them stupid. They know what they want when they want it -- we have to make sure we can give it to them.

## 6. Stability isn't a luxury ##
Some open source project's have a long-standing history of stability -- the Linux kernel,
GCC, etc. Others are somewhat less stable, mostly as a result of frequently-changing source code and/or complex functionality -- WINE (depending upon application), MythTV, etc. While many users may find it fair to trade off some stability for the latest, bleeding-edge features, LegendTV should never view stability as a luxury or an after thought.

Think about it: LegendTV is a PVR application, an application that must be able to record scheduled shows at all times of day, while simultenously allowing the user to pause and rewind live television. In most cases, LegendTV will be the heart and soul of a system that will sit in a corner somewhere, up 24/7, always recording ''something''. Any downtime means that a recording or several get missed. Yes, television programs aren't everything in life, and a user who just came home and sat down after work might not care what he's watching as long as it's something, but if that user is instead hosting a SuperBowl party for 15 of his closest friends, what he's watching matters. That means stability matters, too.

With this in mind, LegendTV must be designed to stay up under almost any circumstances, except of course hardware failure or something that can seriously only be fixed by the user (file system corruption, malformed kernel, etc). This places extraordinary pressure on the development team to ensure code is extensively tested. Moreover, it means that we must design the system to tolerate cases where the code isn't stable. If there is any doubt as to whether something might bring down the system, then it isn't fit to be present in a release (not even a 0.x release).

**Key idea:** Users expect PVR applications like LegendTV to record shows 24/7 and often build a system that is dedicated solely for that purpose. If we fail to meet this basic expectation, we're wasting the user's time and resources, and causing them to miss something that's important to them.
