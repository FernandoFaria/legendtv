# Requirement Details #
The scheduler is the core of a PVR application -- the component that decides what and when to record, based on what shows the user has indicated they want. In short, the scheduler is what will drive LegendTV recordings.

## Benefit / Rationale ##
As stated in the description, the scheduler will manage the recording schedule. The scheduler will take the listings information and the user's desired programs as input, and generate a recording schedule. This module will free the recorder from having to know about guide data and user preferences.

This feature has already proven itself indispensable in MythTV, BeyondTV, etc.

## Justifiable Need ##
A scheduler is a key component in a PVR application. The project cannot function as a video recorder if there isn't a component to manage the recording schedule; coupling the recorder to the program guide would be an unacceptable hack. Thus, there is a justifiable need for this feature.

## Prior Art / Reference Implementations ##
Almost all PVR applications on the market today (MythTV, BeyondTV, Media Center, etc) have a module that is responsible for scheduling that is somewhat apart from the module that actually does the recording.

# Implementation Details #
## Technical Overview ##
The scheduler will need to know a good deal about guide data. Specifically, the scheduler will need to know about program schedules and keying information -- that is, information that can be used to match duplicate recordings in the program schedule.

The scheduler may also need to know about the [recording module](Recorder.md), as the recording schedule will be what triggers the recording module to act. However, it may be possible to simple hand off the next 24 hours of recordings to a recording thread scheduler that will then invoke the necessary recording threads, when the time comes.

The scheduler could possibly be implemented as a stored procedure, depending upon whether the data access API of choice (JPOX, etc) and the underlying database engine (i.e. MySQL) support them. If not, it may still be possible (and desireable) to implement this module using the database. This is mainly because the database is the fastest tool for relating and sorting out data quickly.

## Affected Components ##
Implementing the scheduler may affect the recording module. No other modules should be affected and/or have direct dependencies on the scheduler.

## Potential Tools / Resources ##
Referencing the implementation of other PVR software might prove to be a useful tool.

## Prospective Obstacles ##
_None foreseen as of yet_

## Current Progress ##
Implementation of this feature has not yet begun.