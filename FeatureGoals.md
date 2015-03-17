# Introduction #
This page contains a list of the features that are desired for an upcoming release. This page will grow and periodically be trimmed as the project evolves, but should avoid feature creep as much as possible. _(Before adding a feature, think clearly about what value the feature will add to the end-user experience and what need has already been demonstrated by users for that feature; if a feature merely "sounds cool", it probably isn't needed.)_

# Target Releases #
Features will be delivered during successive iterations. For information on what types of features are aimed for delivery in what release, see the [Release Feature Goals](ReleaseFeatureGoals.md) page.

# Feature List #
## High Priority ##
  * **[Intelligent scheduling](Scheduler.md)** - This is a PVR application; this is kind of a no-brainer.
  * **[Recording core](Recorder.md)** - Again, PVR application; recording is a must
  * **[Normalized, clear-cut database structure](DatabaseStructure.md)** - The main design principle for the database is write once, reference many. In other words, no redundant information; otherwise, data can get redundant and out of sync too easily. The database design must ultimately be as normalized as reasonably possible.
  * **[Fault-tolerance on back-end, especially around JNI invocations](FaultTolerance.md)** - To avoid any recordings being missed, the back-end must stay up under almost all circumstances, except for perhaps incorrectly-configured hardware or an extremely serious programmer error (exceptions thrown left and right, continuous crashes in VLC core, etc).  VLC will be the weak spot here because it is written in C, so segmentation faults, floating-point exceptions, and illegal instructions are possible.
  * **[DataDirect listings support](ListingsGrabber.md)** - This is the most prevalent listings provider for North America; we need to support it.
  * **[Java3D interface](Java3D.md)** - The user base will most likely have a card with GL support, so we should take advantage of it. This avoids the use of Swing, SWT, or AWT all together, while at the same time bringing performance and simplicity to video playback (we don't have to handle mode switching in/out of OpenGL, SWT/Swing/AWT painting wierdness, etc).

## Normal Priority ##
  * **[Backup Program](BackupProgram.md)** - Favorite shows, upcoming recordings, preferences, and previously-recorded shows all amount to lots of information; the user should be able to back this information up in case they need to switch machines/versions, etc.
  * **[Command-line interface](InterfaceCommandLine.md)** - Almost all of Linux is driven from the command-line -- even GUI applications usually provide utility programs that can be run from the command line. We should provide this as well, to allow users to schedule programs, monitor status, perform housekeeping, and perhaps even remotely control a front-end; all from the command line.
  * **[Remote interface (via LIRC)](InterfaceRemote.md)** - A TV application should support control from away from the system. LIRC already supports a wide variety of remotes.
  * **[Web interface](InterfaceWeb.md)** - Most major PVR packages have this (ReplayTV, TiVO, BeyondTV, MythTV, etc). This allows people to monitor the system and schedule programming away from home.
  * **[Commercial flagging](CommercialSkip.md)** - Most major PVR programs (MythTV especially; not Media Center, however) support flagging and auto-skipping commercial breaks. We need to do this as well. However, we may want to see how feasible real-time flagging is -- that would give us an edge over other projects that must do flagging after recording.
  * **[SSDP for back-end discovery](SSDPDiscovery.md)** - Front-ends should be capable of discovering each other via Simple Server Discovery Protocol, so users do not have to remember IP addresses. Although the protocol does not traverse firewalls well, this should not pose a problem for the typical operating environment (LAN, home setup).
  * **[Step-by-step / wizard-based first-run setup](FirstRunWizard.md)** - MythTV, lirc, ivtv, and dvb are very complicated to setup at the moment. LegendTV should NOT be that difficult -- users should be able to install and go, without having to fiddle with IP addresses, hardware device paths, etc. The target audience is NOT the experienced Linux sys admin -- most PVR boxes will be serving the single function of a set-top box, so why assume that the user is an IT admin?
  * **[Quake-like input binding](QuakeKeyBinding.md)** - Users should be able to configure keys / remote buttons the same way a Quake player would -- select an action, press a button, done! The user should not need a special plugin or external config file to do this.
  * **[T9 / numeric input interpretation support](NumericInputInterpreter.md)** - The user will most likely be controlling the system via a remote; as a result, most PVR applications attempt to present the user with an on-screen keyboard that they have to tediously navigate back and forth to input text. Instead, we should look at the remote just like a cell phone -- it has 10 digits (0-9), so we should support having the user press a number a certain number of times to pertain to a particular digit (eg. B is 2 twice). If we implement T9, we can auto-complete the user's input based on the unique sequence of keys for particular words, and then learn the words we don't know.
  * **[LCD/VFD Display Support](LCDOutput.md)** - Many PVR cases sport a bright blue or green VFD or LCD display that shows what the system is up to. We should support this to be on par with MythTV.
  * **[Music playback / music library support](MusicPlayback.md)** - VLC already sports most of the capabilities needed to implement this, so it's low-hanging fruit to some extent.
  * **[Most features supported by MythTV](MythTVFeatureList.md)** - There are many features that MythTV provides that we must provide as well. These are too numerous to name here, but include things like LCD support, flexible scheduling options, etc.

## Low Priority ##
  * **[Picture-In-Picture Support](PIPSupport.md)** - It's commonplace on many TVs and PVRs, why not?
  * **[Additional Plugins](Plugins.md)** - Many other PVR packages have plugins that provide the weather, rent Netflix DVDs, run game emulators, etc. We should write similar plugins.
  * **[RewardTV Integration](RewardTV.md)** - [RewardTV](http://www.rewardtv.com) is an awesome site that rewards members for correctly answering trivia questions and surveys about the TV shows or movies that they watched the previous day. It might be nice to have a front-end to the site built-in. There's also the potential for a partnership here.
  * **[Volume Normalization](VolumeNormalization.md)** - There is a growing trend of local affiliates not properly normalizing their audio output between shows and commercials during broadcasts, especially on HD stations. We should build in something to compensate for this.
  * **[Recording multiple shows from the same multiplex](SharedMultiplexRecording.md)** - MythTV users have wanted this capability for a while -- we should look into it. If two or more HD programs need to be recorded at the same time and they happen to be on the same multiplex, we should be able to record them on the same card.
  * **[Multi-user support for recordings](MultiUserSupport.md)** - Different users in the home have different tastes. Some users might have the same tastes, but watch programs at different times. Users should be allowed to schedule what they want to record without having to see other users' recordings, and be able to delete recordings they've watched without affecting the recordings of other users. Our software may be the first to support this feature.
  * **[Automatic capture card detection](CaptureCardDetection.md)** - Capture cards might be changed between boots or even invocations of the back-end, so the back-end should be dynamic enough to detect this. Also, during the first-run setup, the user should be able to setup cards merely by having the back-end detect them -- the user should not have to worry about device names for each tuner.
  * **[ID3 (or similar) tagging for recordings](VideoTags.md)** - Users may want to exchange files / copy files between installations. We should be able to tag recordings so that if we run into a file that we don't recognize (i.e. isn't in the database), we can import it. ID3, although targeted at MP3s initially, may prove adequate in this respect.
  * **[OpenGL XvMC Support](OpenGLXvMC.md)** - The MythTV developers have played with this, but AFAIK it has not been implemented; this is required for the feature below.
  * **[Hardware / GPU de-interlace support](HardwareDeinterlace.md)** - Currently, XvMC supports only BOB de-interlace, or no de-interlacing at all, which is completely unacceptable for HD content. If we can use OpenGL with XvMC texturing, we may be able to do de-interlacing in hardware on the GPU using shader scripts. This would seriously reduce the load on the CPU when HD content is being played back, while at the same time allow us utilize XvMC capabilities in newer cards and produce a high quality picture in the process.