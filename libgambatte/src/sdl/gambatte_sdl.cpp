/***************************************************************************
 *   Copyright (C) 2007 by Sindre Aam�s                                    *
 *   sinamas@users.sourceforge.net                                         *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License version 2 as     *
 *   published by the Free Software Foundation.                            *
 *                                                                         *
 *   This program is distributed in the hope that it will be useful,       *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         *
 *   GNU General Public License version 2 for more details.                *
 *                                                                         *
 *   You should have received a copy of the GNU General Public License     *
 *   version 2 along with this program; if not, write to the               *
 *   Free Software Foundation, Inc.,                                       *
 *   59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.             *
 ***************************************************************************/
#include <gambatte.h>
#include <pakinfo.h>
#include "array.h"
#include "rateest.h"
#include <SDL.h>
#include <cstdlib>
#include <cstring>
#include <cstdio>
#include <string>
#include <sstream>
#include <iostream>
#include <algorithm>
#include <memory>
#include <vector>
#include <map>
#ifdef __APPLE__
#include <dlfcn.h>  //To make it work on mac
#endif

#include "blitterwrapper.h"
#include "mrwint_gbtasgen_Gb.h"

namespace {

using namespace gambatte;


class GetInput : public InputGetter {
public:
	unsigned is;
	
	GetInput() : is(0) {}
	unsigned operator()() { return is; }
};

class SdlIniter {
	bool failed;
public:
	BlitterWrapper blitter;

	SdlIniter() : failed(false) {}
    void init(int numScreens) {
#ifdef __APPLE__
        pre_init();
#endif

		const int scaleOption = 1;
		if (SDL_Init(SDL_INIT_VIDEO) < 0) {
			std::fprintf(stderr, "Unable to init SDL: %s\n", SDL_GetError());
			failed = true;
		}

		blitter.setScale(scaleOption);
		blitter.setYuv(false);
		blitter.setVideoFilter(0);

		blitter.init(numScreens);
		SDL_ShowCursor(SDL_DISABLE);
		SDL_WM_SetCaption("Gambatte SDL", NULL);
	}
	~SdlIniter() { SDL_Quit(); }

#ifdef __APPLE__
    void pre_init()
    {
        void* cocoa_lib;

        cocoa_lib = dlopen( "/System/Library/Frameworks/Cocoa.framework/Cocoa", RTLD_LAZY );
        void (*nsappload)(void);
        nsappload = (void(*)()) dlsym( cocoa_lib, "NSApplicationLoad");
        nsappload();
    }
#endif
	bool isFailed() const { return failed; }
};

SdlIniter sdl;

class GambatteSdl {
public:
	Array<Sint16> inBuf;
	GB gambatte;
	
	GetInput inputGetter;
	std::map<SDLKey,unsigned> keyMap;
	
	unsigned samples;
	bool init(const char* romName);
	int screen;
	
	GambatteSdl(int screen_) : inBuf((35112 + 2064) * 2),samples(0),screen(screen_) {}
	void step();
	void step2();
	void step3();
	void handleInput();

	bool keepRunning;
};

bool GambatteSdl::init(const char* romName) {
	std::printf("Gambatte SDL SVN\n");

	keepRunning = true;
	
	if (sdl.isFailed())
		return 1;
	
	{
		const bool gbaCgbOption = false;
		const bool forceDmgOption = false;
		const bool multicartCompatOption = false;
		
		if (LoadRes const error =
				gambatte.load(romName,
				                gbaCgbOption          * GB::GBA_CGB
				              + forceDmgOption        * GB::FORCE_DMG
				              + multicartCompatOption * GB::MULTICART_COMPAT)) {
			std::printf("failed to load ROM %s: %s\n", romName, to_string(error).c_str());
			return error;
		}

		{
			PakInfo const &pak = gambatte.pakInfo();
			std::puts(gambatte.romTitle().c_str());
			std::printf("GamePak type: %s rambanks: %u rombanks: %u\n",
			            pak.mbc().c_str(), pak.rambanks(), pak.rombanks());
			std::printf("header checksum: %s\n", pak.headerChecksumOk() ? "ok" : "bad");
			std::printf("cgb: %d\n", gambatte.isCgb());
		}

		unsigned const gbbuts[8] = {
			InputGetter::START, InputGetter::SELECT,
			InputGetter::A, InputGetter::B,
			InputGetter::UP, InputGetter::DOWN,
			InputGetter::LEFT, InputGetter::RIGHT
		};
		SDLKey const inputKeys[] = {
			SDLK_RETURN, SDLK_RSHIFT,
			SDLK_d, SDLK_c,
			SDLK_UP, SDLK_DOWN,
			SDLK_LEFT, SDLK_RIGHT};
		
		for (unsigned i = 0; i < 8; ++i)
			keyMap.insert(std::pair<SDLKey,unsigned>(inputKeys[i], gbbuts[i]));
	}
	
	gambatte.setInputGetter(&inputGetter);
	
	return 0;
}

void GambatteSdl::handleInput() {
	SDL_Event e;
	
	while (SDL_PollEvent(&e)) {
		switch (e.type) {
		case SDL_KEYDOWN:
			if (e.key.keysym.mod & KMOD_CTRL) {
				switch (e.key.keysym.sym) {
				case SDLK_f:
					sdl.blitter.toggleFullScreen();
					break;
				case SDLK_r:
					//gambatte.reset();
					break;
				default: break;
				}
			} else {
				switch (e.key.keysym.sym) {
				case SDLK_ESCAPE: keepRunning = false; return;
				case SDLK_F5: gambatte.saveState(sdl.blitter.inBuf(screen).pixels, sdl.blitter.inBuf(screen).pitch); break;
				case SDLK_F6: gambatte.selectState(gambatte.currentState() - 1); break;
				case SDLK_F7: gambatte.selectState(gambatte.currentState() + 1); break;
				case SDLK_F8: gambatte.loadState(); break;
				case SDLK_0: gambatte.selectState(0); break;
				case SDLK_1: gambatte.selectState(1); break;
				case SDLK_2: gambatte.selectState(2); break;
				case SDLK_3: gambatte.selectState(3); break;
				case SDLK_4: gambatte.selectState(4); break;
				case SDLK_5: gambatte.selectState(5); break;
				case SDLK_6: gambatte.selectState(6); break;
				case SDLK_7: gambatte.selectState(7); break;
				case SDLK_8: gambatte.selectState(8); break;
				case SDLK_9: gambatte.selectState(9); break;
				default: break;
				}
			}
			// fallthrough
		case SDL_KEYUP:
			if(keyMap.count(e.key.keysym.sym)) {
				if (e.key.state)
					inputGetter.is |= keyMap[e.key.keysym.sym];
				else
					inputGetter.is &= ~keyMap[e.key.keysym.sym];
			}
			
			break;
		case SDL_QUIT:
			keepRunning = false; return;
		}
	}
}

const unsigned SAMPLES_PER_FRAME = 35112;


void GambatteSdl::step() {
	const BlitterWrapper::Buf &vbuf = sdl.blitter.inBuf(screen);
	unsigned emusamples = SAMPLES_PER_FRAME - samples;


	const int ret = gambatte.runFor(vbuf.pixels, vbuf.pitch,
			reinterpret_cast<gambatte::uint_least32_t*>(inBuf.get()), emusamples);
	if(gambatte.p_->cpu.hitInterruptAddress != 0) // went into frame
		samples += emusamples;
	else {
		samples = 0; // completed frame
		gambatte.p_->cpu.numFrames++;
	}
	
	if (ret >= 0) {
		sdl.blitter.draw();
		sdl.blitter.present();
	}
}
} // anon namespace



#define UNUSED(x)  (void)(x)


// initSdl
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_initSdl
    (JNIEnv *env, jclass clazz, jint numScreens) {
  UNUSED(env);UNUSED(clazz);

  sdl.init(numScreens);
}


// createGb
JNIEXPORT jlong JNICALL Java_mrwint_gbtasgen_Gb_createGb
    (JNIEnv *env, jclass clazz, jint screen) {
  UNUSED(env);UNUSED(clazz);

  GambatteSdl* gb = new GambatteSdl(screen);
  return (jlong)gb;
}

// startEmulator
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_startEmulator
  (JNIEnv *env, jclass clazz, jlong gb, jstring str) {
  UNUSED(clazz);

  ((GambatteSdl*)gb)->init(env->GetStringUTFChars(str, 0));
}

// nstep
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_nstep
    (JNIEnv *env, jclass clazz, jlong gb, jint keymask){
  UNUSED(env);UNUSED(clazz);

  if(keymask == -1)
    ((GambatteSdl*)gb)->handleInput();
  else
    ((GambatteSdl*)gb)->inputGetter.is = keymask;

  ((GambatteSdl*)gb)->gambatte.p_->cpu.numInterruptAddresses = 0; // no interrupts
  ((GambatteSdl*)gb)->step();
}

// nstepUntil
JNIEXPORT jint JNICALL Java_mrwint_gbtasgen_Gb_nstepUntil
    (JNIEnv *env, jclass clazz, jlong gb, jint keymask, jintArray arr){
  UNUSED(env);UNUSED(clazz);UNUSED(keymask);UNUSED(arr);

  if(keymask == -1)
    ((GambatteSdl*)gb)->handleInput();
  else
    ((GambatteSdl*)gb)->inputGetter.is = keymask;

  jsize len = env->GetArrayLength(arr);
  jint* addresses = env->GetIntArrayElements(arr,0);

  ((GambatteSdl*)gb)->gambatte.p_->cpu.numInterruptAddresses = len;
  ((GambatteSdl*)gb)->gambatte.p_->cpu.interruptAddresses = addresses; // set up interrupts
  ((GambatteSdl*)gb)->step();

  env->ReleaseIntArrayElements(arr,addresses,0);
  return ((GambatteSdl*)gb)->gambatte.p_->cpu.hitInterruptAddress;
}

// nreset
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_nreset
    (JNIEnv *env, jclass clazz, jlong gb){
  UNUSED(env);UNUSED(clazz);

  ((GambatteSdl*)gb)->gambatte.reset(0);
}

// saveState
JNIEXPORT jint JNICALL Java_mrwint_gbtasgen_Gb_saveState
    (JNIEnv *env, jclass clazz, jlong gb, jobject buffer, jint size){
  UNUSED(clazz);

  char* buffer_address = ((char*) env->GetDirectBufferAddress(buffer));
  std::vector<char> data;
  ((GambatteSdl*)gb)->gambatte.saveState(data);

  if((int)data.size() > size)
    std::cout << "too big! " << data.size() << " vs. " << size << std::endl;

  std::copy(data.begin(), data.end(), buffer_address);

  return data.size();
}

// loadState
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_loadState
    (JNIEnv *env, jclass clazz, jlong gb, jobject buffer, jint size){
  UNUSED(clazz);

  char* buffer_address = ((char*) env->GetDirectBufferAddress(buffer));

  std::vector<char> data(buffer_address,buffer_address+size);

  ((GambatteSdl*)gb)->gambatte.loadState(data);
}


// getROMSize
JNIEXPORT jint JNICALL Java_mrwint_gbtasgen_Gb_getROMSize
    (JNIEnv *env, jclass clazz, jlong gb){
  UNUSED(env);UNUSED(clazz);

  return ((GambatteSdl*)gb)->gambatte.p_->cpu.memory.cart.memptrs.numRombanks*0x4000;
}

// getROM
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_getROM
    (JNIEnv *env, jclass clazz, jlong gb, jintArray arr){
  UNUSED(env);UNUSED(clazz);UNUSED(arr);

  int size = ((GambatteSdl*)gb)->gambatte.p_->cpu.memory.cart.memptrs.numRombanks*0x4000;
  jint *rom_store = env->GetIntArrayElements(arr, 0);
  for(int i=0;i<size;i++)
    rom_store[i] = ((GambatteSdl*)gb)->gambatte.p_->cpu.memory.cart.memptrs.romdata()[i];
  env->ReleaseIntArrayElements(arr, rom_store, 0);
}

// getRegisters
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_getRegisters
    (JNIEnv *env, jclass clazz, jlong gb, jintArray arr){
  UNUSED(env);UNUSED(clazz);UNUSED(arr);

  jint *registers_store = env->GetIntArrayElements(arr, 0);

  registers_store[0] = ((GambatteSdl*)gb)->gambatte.p_->cpu.PC_;
  registers_store[1] = ((GambatteSdl*)gb)->gambatte.p_->cpu.SP;
  registers_store[2] = ((GambatteSdl*)gb)->gambatte.p_->cpu.A_ << 8;
  registers_store[3] = (((GambatteSdl*)gb)->gambatte.p_->cpu.B << 8) + ((GambatteSdl*)gb)->gambatte.p_->cpu.C;
  registers_store[4] = (((GambatteSdl*)gb)->gambatte.p_->cpu.D << 8) + ((GambatteSdl*)gb)->gambatte.p_->cpu.E;
  registers_store[5] = (((GambatteSdl*)gb)->gambatte.p_->cpu.H << 8) + ((GambatteSdl*)gb)->gambatte.p_->cpu.L;

  env->ReleaseIntArrayElements(arr, registers_store, 0);
}

// getMemory
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_getMemory
    (JNIEnv *env, jclass clazz, jlong gb, jintArray arr){
  UNUSED(env);UNUSED(clazz);UNUSED(arr);

  jint *mem_store = env->GetIntArrayElements(arr, 0);
  int cc = ((GambatteSdl*)gb)->gambatte.p_->cpu.cycleCounter_;
  for(int i=0;i<0x10000;i++)
    mem_store[i] = ((GambatteSdl*)gb)->gambatte.p_->cpu.memory.read(i,cc);
  env->ReleaseIntArrayElements(arr, mem_store, 0);
}

// readMemory
JNIEXPORT jint JNICALL Java_mrwint_gbtasgen_Gb_readMemory
    (JNIEnv *env, jclass clazz, jlong gb, jint address){
  UNUSED(env);UNUSED(clazz);

  return ((GambatteSdl*)gb)->gambatte.p_->cpu.memory.read(address,((GambatteSdl*)gb)->gambatte.p_->cpu.cycleCounter_);
}

// writeMemory
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_writeMemory
    (JNIEnv *env, jclass clazz, jlong gb, jint address, jint value){
  UNUSED(env);UNUSED(clazz);

  ((GambatteSdl*)gb)->gambatte.p_->cpu.memory.write(address,value,((GambatteSdl*)gb)->gambatte.p_->cpu.cycleCounter_);
}

// getDivState
JNIEXPORT jint JNICALL Java_mrwint_gbtasgen_Gb_getDivState
    (JNIEnv *env, jclass clazz, jlong gb){
  UNUSED(env);UNUSED(clazz);

  int cc = ((GambatteSdl*)gb)->gambatte.p_->cpu.cycleCounter_;
  int divOff = cc - ((GambatteSdl*)gb)->gambatte.p_->cpu.memory.divLastUpdate;
  int div = ((GambatteSdl*)gb)->gambatte.p_->cpu.memory.ioamhram[0x104];
  return (((div << 8) + divOff) >> 2) & 0x3FFF;
}

// offsetDiv
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_offsetDiv
    (JNIEnv *env, jclass clazz, jlong gb, jint offset){
  UNUSED(env);UNUSED(clazz);

  ((GambatteSdl*)gb)->gambatte.p_->cpu.memory.divLastUpdate -= (offset << 2);
}

