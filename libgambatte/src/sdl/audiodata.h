/***************************************************************************
 *   Copyright (C) 2007 by Sindre Aamås                                    *
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
#ifndef AUDIODATA_H
#define AUDIODATA_H

#include <ringbuffer.h>
#include <rateest.h>
#include <SDL.h>

struct AudioData {
	struct Status {
		long fromUnderrun;
		long fromOverflow;
		long rate;
	};
	
	AudioData(unsigned sampleRate, unsigned latency, unsigned periods);
	~AudioData();
	const Status write(const Sint16 *inBuf, unsigned samples);
	void read(Uint8 *stream, int len);
	
private:
	RingBuffer<Sint16> rbuf;
	RateEst rateEst;
	SDL_mutex *const mut;
	SDL_cond *const bufReadyCond;
	bool failed;
	
	static void fill_buffer(void *const data, Uint8 *const stream, const int len) {
		reinterpret_cast<AudioData*>(data)->read(stream, len);
	}
};

#endif
