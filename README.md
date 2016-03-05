gb-tas-gen
==========

#### Overview

This is a framework to create tool-assisted gameplay for Gameboy games with fine control and automation.

It runs a modified [libgambatte](https://github.com/sinamas/gambatte) as a core, and produces movies that can be played back with [BizHawk](https://github.com/TASVideos/BizHawk).

#### Installation

**Note:** This repository is my code dumping ground and not a refined and maintained piece of software. You are welcome to look trough and use the code for whatever purpose you like, it's likely in a consistent state and does what's written on the tin, but expect random, obsolete, unfinished or non-working bits and pieces everywhere, and overall horrible architecture and design. This has grown organically over the years with all sorts of things I tried and is not meant to be a clean code base.

You'll need some prerequisites: `ant`, `scons`, `libsdl1.2-dev`, as well as a Java (8+) and a C compiler.

Clone the repository, build the JNI interface by running `ant` in `libgambatte/java/`, and then compile gambatte by running `scons` in `libgambatte/`. You might need to change some paths in `libgambatte/SConstruct` for the JNI because I'm lazy like that.

After that you can put/link the compiled `libgambatte` library into your library path (e.g. `/usr/lib`) to have it be detected by the Java runtime, and then fire up your favorite Java IDE and start using it.

#### Basic Usage

The framework is written in Java, so it's easiest to use with Java (or compatible languages).

To initialize the libgambatte, call `Gb.loadGambatte(n)`, where `n` is the number of Gameboy instances you want to run. Then you can instantiate as many `Gameboy` instances and assign them the different screens. The `Gameboy` class is the heart of the framework, it wraps the JNI and contains the full state of the Gameboy instance. It allows savestating at any point (even mid-frame) and keeps track of the inputs to generate the movie files out of.

For running 1 or 2 Gameboy instances, there are utility classes (e.g. `util.SingleGbRunner`) that do the setup process. See `segment.pokemon.gen1.NoWW` for an example how to use them.

To use a ROM, you need to create a `RomInfo` for it that contains the necessary information to run it and create movies from it.

When running an instance, you can advance the emulation with `Gameboy.step(moves)`, pressing `moves` on the joypad. You can optionally specify `addresses`, breakpoints the emulation should stop at when reached. You can inspect the current state at any time using e.g. `Gameboy.readMemory(address)`.

The usual workflow is to advance the emulation to an interesting point by using breakpoints, and then inspecting the internal state and decide on actions based on this. The utility classes `util.Util` and `util.EflUtil` (depending on your frame timing) contain a collection of common building blocks, like `runToAddress` to execute until a specific breakpoint, or `runToNextInputFrame` to execute until the next joypad input is accepted.

Commonly actions are not done on a single state, but on a collection of states in a `StateBuffer`.
A StateBuffer is meant to contain slight variations of the current state and can be used e.g. to brute-force through random events or optimizations.
`Segment`s are used to define operations that transform one StateBuffer to a new one.

From these basic blocks, you can build more sophisticated and game-specific operations, like `segment.pokemon.EflTextSegment` to scroll through a line of dialog, or `segment.pokemon.EflWalkToSegment` to navigate the overworld and walk to a specific location.

Finally, after you brought your Gameboy instances to the desired goal state, you can use the `movie.BizhawkMovie` utility to export your state to a BizHawk movie file that can be played back in BizHawk.


