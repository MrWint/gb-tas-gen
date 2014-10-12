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
	SdlIniter() : failed(false) {
		if (SDL_Init(SDL_INIT_VIDEO) < 0) {
			std::fprintf(stderr, "Unable to init SDL: %s\n", SDL_GetError());
			failed = true;
		}
	}
	~SdlIniter() { SDL_Quit(); }
	bool isFailed() const { return failed; }
};

class GambatteSdl {
public:
	Array<Sint16> inBuf;
	GB gambatte;
	SdlIniter sdlIniter;
	BlitterWrapper blitter;
	GetInput inputGetter;
	std::map<SDLKey,unsigned> keyMap;
	
	unsigned samples;
	bool init(const char* romName);
	
	GambatteSdl() : inBuf((35112 + 2064) * 2),samples(0) {}
	void step();
	void step2();
	void step3();
	void handleInput();

	bool keepRunning;
};

bool GambatteSdl::init(const char* romName) {
	std::printf("Gambatte SDL SVN\n");

	keepRunning = true;
	
	if (sdlIniter.isFailed())
		return 1;
	
	{
		const bool gbaCgbOption = false;
		const bool forceDmgOption = false;
		const bool multicartCompatOption = false;
		const int scaleOption = 1;
		
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
		
		blitter.setScale(scaleOption);
		blitter.setYuv(false);
		blitter.setVideoFilter(0);
		
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
	
	blitter.init();
	SDL_ShowCursor(SDL_DISABLE);
	SDL_WM_SetCaption("Gambatte SDL", NULL);
	
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
					blitter.toggleFullScreen();
					break;
				case SDLK_r:
					//gambatte.reset();
					break;
				default: break;
				}
			} else {
				switch (e.key.keysym.sym) {
				case SDLK_ESCAPE: keepRunning = false; return;
				case SDLK_F5: gambatte.saveState(blitter.inBuf().pixels, blitter.inBuf().pitch); break;
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
	const BlitterWrapper::Buf &vbuf = blitter.inBuf();
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
		blitter.draw();
		blitter.present();
	}
}
} // anon namespace

GambatteSdl gambatteSdl;



#define UNUSED(x)  (void)(x)

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    startEmulator
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_startEmulator
  (JNIEnv *env, jclass clazz, jstring str) {
  UNUSED(clazz);

  gambatteSdl.init(env->GetStringUTFChars(str, 0));
}


/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    nstep
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_nstep
  (JNIEnv *env, jclass clazz, jint keymask){
  UNUSED(env);UNUSED(clazz);

  if(keymask == -1)
    gambatteSdl.handleInput();
  else
    gambatteSdl.inputGetter.is = keymask;

  gambatteSdl.gambatte.p_->cpu.numInterruptAddresses = 0; // no interrupts
  gambatteSdl.step();
}

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    nstepUntil
 * Signature: (I[I)I
 */
JNIEXPORT jint JNICALL Java_mrwint_gbtasgen_Gb_nstepUntil
  (JNIEnv *env, jclass clazz, jint keymask, jintArray arr){
  UNUSED(env);UNUSED(clazz);UNUSED(keymask);UNUSED(arr);

  if(keymask == -1)
    gambatteSdl.handleInput();
  else
    gambatteSdl.inputGetter.is = keymask;

  jsize len = env->GetArrayLength(arr);
  jint* addresses = env->GetIntArrayElements(arr,0);

  gambatteSdl.gambatte.p_->cpu.numInterruptAddresses = len;
  gambatteSdl.gambatte.p_->cpu.interruptAddresses = addresses; // set up interrupts
  gambatteSdl.step();

  env->ReleaseIntArrayElements(arr,addresses,0);
  return gambatteSdl.gambatte.p_->cpu.hitInterruptAddress;
}

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    nreset
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_nreset
  (JNIEnv *env, jclass clazz){
  UNUSED(env);UNUSED(clazz);
  gambatteSdl.gambatte.reset(0);
}

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    saveState
 * Signature: (Ljava/nio/ByteBuffer;I)J
 */
JNIEXPORT jlong JNICALL Java_mrwint_gbtasgen_Gb_saveState
  (JNIEnv *env, jclass clazz, jobject buffer, jint size){
	UNUSED(clazz);
	char* buffer_address = ((char*) env->GetDirectBufferAddress(buffer));

	std::vector<char> data;
	gambatteSdl.gambatte.saveState(data);

	if((int)data.size() > size)
		std::cout << "too big! " << data.size() << " vs. " << size << std::endl;

	std::copy(data.begin(), data.end(), buffer_address);

	return data.size();
}

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    loadState
 * Signature: (Ljava/nio/ByteBuffer;I)V
 */
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_loadState
  (JNIEnv *env, jclass clazz, jobject buffer, jint size){
	UNUSED(clazz);
	char* buffer_address = ((char*) env->GetDirectBufferAddress(buffer));

	std::vector<char> data(buffer_address,buffer_address+size);

	gambatteSdl.gambatte.loadState(data);
}


/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    getROMSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_mrwint_gbtasgen_Gb_getROMSize
  (JNIEnv *env, jclass clazz){
	UNUSED(env);UNUSED(clazz);
	return gambatteSdl.gambatte.p_->cpu.memory.cart.memptrs.numRombanks*0x4000;
}

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    getROM
 * Signature: ([I)V
 */
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_getROM
(JNIEnv *env, jclass clazz, jintArray arr){
	UNUSED(env);UNUSED(clazz);UNUSED(arr);
	int size = gambatteSdl.gambatte.p_->cpu.memory.cart.memptrs.numRombanks*0x4000;
	jint *rom_store = env->GetIntArrayElements(arr, 0);
	for(int i=0;i<size;i++)
		rom_store[i] = gambatteSdl.gambatte.p_->cpu.memory.cart.memptrs.romdata()[i];
	env->ReleaseIntArrayElements(arr, rom_store, 0);
}

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    getRegisters
 * Signature: ([I)V
 */
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_getRegisters
  (JNIEnv *env, jclass clazz, jintArray arr){
  UNUSED(env);UNUSED(clazz);UNUSED(arr);
	jint *registers_store = env->GetIntArrayElements(arr, 0);

	registers_store[0] = gambatteSdl.gambatte.p_->cpu.PC_;
	registers_store[1] = gambatteSdl.gambatte.p_->cpu.SP;
	registers_store[2] = gambatteSdl.gambatte.p_->cpu.A_ << 8;
	registers_store[3] = (gambatteSdl.gambatte.p_->cpu.B << 8) + gambatteSdl.gambatte.p_->cpu.C;
	registers_store[4] = (gambatteSdl.gambatte.p_->cpu.D << 8) + gambatteSdl.gambatte.p_->cpu.E;
	registers_store[5] = (gambatteSdl.gambatte.p_->cpu.H << 8) + gambatteSdl.gambatte.p_->cpu.L;

	env->ReleaseIntArrayElements(arr, registers_store, 0);
}

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    getMemory
 * Signature: ([I)V
 */
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_getMemory
  (JNIEnv *env, jclass clazz, jintArray arr){
  UNUSED(env);UNUSED(clazz);UNUSED(arr);
	UNUSED(clazz);
	jint *mem_store = env->GetIntArrayElements(arr, 0);
	int cc = gambatteSdl.gambatte.p_->cpu.cycleCounter_;
	for(int i=0;i<0x10000;i++)
		mem_store[i] = gambatteSdl.gambatte.p_->cpu.memory.read(i,cc);
	env->ReleaseIntArrayElements(arr, mem_store, 0);
}

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    readMemory
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_mrwint_gbtasgen_Gb_readMemory
  (JNIEnv *env, jclass clazz, jint address){
	UNUSED(env);UNUSED(clazz);
	return gambatteSdl.gambatte.p_->cpu.memory.read(address,gambatteSdl.gambatte.p_->cpu.cycleCounter_);
}

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    writeMemory
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_writeMemory
  (JNIEnv *env, jclass clazz, jint address, jint value){
	UNUSED(env);UNUSED(clazz);
	gambatteSdl.gambatte.p_->cpu.memory.write(address,value,gambatteSdl.gambatte.p_->cpu.cycleCounter_);
}

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    getRNGState
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_mrwint_gbtasgen_Gb_getRNGState
  (JNIEnv *env, jclass clazz){
	UNUSED(env);UNUSED(clazz);
	int cc = gambatteSdl.gambatte.p_->cpu.cycleCounter_;
	int divOff = cc - gambatteSdl.gambatte.p_->cpu.memory.divLastUpdate;
	int div = gambatteSdl.gambatte.p_->cpu.memory.ioamhram[0x104];
	return (((div << 8) + divOff) >> 2) & 0x3FFF;
}

/*
 * Class:     mrwint_gbtasgen_Gb
 * Method:    offsetRNG
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_mrwint_gbtasgen_Gb_offsetRNG
  (JNIEnv *env, jclass clazz, jint offset){
    gambatteSdl.gambatte.p_->cpu.memory.divLastUpdate -= (offset << 2);
}

