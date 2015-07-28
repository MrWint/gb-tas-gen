#ifndef _loadsave__hpp__included__
#define _loadsave__hpp__included__
/***************************************************************************
 *   Copyright (C) 2012 by H. Ilari Liusvaara                              *
 *   ilari.liusvaara@elisanet.fi                                           *
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

#include <vector>
#include <cstdlib>
#include <cstdio>

namespace gambatte {
	class loadsave
	{
	private:
		unsigned enumVal;
		bool enumAssigned;
	public:
		virtual ~loadsave() throw();

		virtual int getOffset() = 0;

		virtual void operator()(bool& x) = 0;
		virtual void operator()(signed char& x) = 0;
		virtual void operator()(unsigned char& x) = 0;
		virtual void operator()(signed short& x) = 0;
		virtual void operator()(unsigned short& x) = 0;
		virtual void operator()(signed int& x) = 0;
		virtual void operator()(unsigned int& x) = 0;
		virtual void operator()(signed long& x) = 0;
		virtual void operator()(unsigned long& x) = 0;
		virtual void operator()(signed long long& x) = 0;
		virtual void operator()(unsigned long long& x) = 0;
		virtual void operator()(signed char* x, size_t s) = 0;
		virtual void operator()(unsigned char* x, size_t s) = 0;
		virtual void operator()(signed short* x, size_t s) = 0;
		virtual void operator()(unsigned short* x, size_t s) = 0;
		virtual void operator()(signed int* x, size_t s) = 0;
		virtual void operator()(unsigned int* x, size_t s) = 0;
		virtual void operator()(signed long* x, size_t s) = 0;
		virtual void operator()(unsigned long* x, size_t s) = 0;
		virtual void operator()(long long* x, size_t s) = 0;
		virtual void operator()(unsigned long long* x, size_t s) = 0;
		virtual void operator()(unsigned char*& ptr, unsigned char* abase) = 0;
		virtual void operator()(const unsigned char*& ptr, unsigned char* abase) = 0;
		virtual void tag(unsigned short tag) = 0;
		void time(time_t& t) {
			unsigned long long t_ = t;
			(*this)(t_);
			t = t_;
		}
		void startEnumeration() {
			enumAssigned = false;
			enumVal = 0xFFFFFFFFU;
			if(!saving())
				(*this)(enumVal);
		}
		template<typename T> void enumerate(T& ptr, T candiate, unsigned symbol) {
			if(saving()) {
				if(ptr == candiate) {
					enumVal = symbol;
					enumAssigned = true;
				}
			} else {
				if(enumVal == symbol) {
					ptr = candiate;
					enumAssigned = true;
				}
			}
		}
		void endEnumeration() {
			if(saving())
				(*this)(enumVal);
			if(!enumAssigned)
				printf("Enumeration missing a choice"),exit(1);
		}
		virtual bool saving() = 0;
	};

	class loadsave_load : public loadsave
	{
		const std::vector<char>& memory;
		size_t ptr;
		template<typename T> inline void do_op(T& x);
		template<typename T> inline void do_op(T& x, unsigned char _tag);
		template<typename T> void do_op(T* x, size_t s, unsigned char _tag);
	public:
		loadsave_load(const std::vector<char>& _memory);
		~loadsave_load() throw();

		int getOffset(){return (int)ptr;}

		void operator()(bool& x);
		void operator()(signed char& x);
		void operator()(unsigned char& x);
		void operator()(signed short& x);
		void operator()(unsigned short& x);
		void operator()(signed int& x);
		void operator()(unsigned int& x);
		void operator()(signed long& x);
		void operator()(unsigned long& x);
		void operator()(signed long long& x);
		void operator()(unsigned long long& x);
		void operator()(signed char* x, size_t s);
		void operator()(unsigned char* x, size_t s);
		void operator()(signed short* x, size_t s);
		void operator()(unsigned short* x, size_t s);
		void operator()(signed int* x, size_t s);
		void operator()(unsigned int* x, size_t s);
		void operator()(signed long* x, size_t s);
		void operator()(unsigned long* x, size_t s);
		void operator()(signed long long* x, size_t s);
		void operator()(unsigned long long* x, size_t s);
		void operator()(unsigned char*& ptr, unsigned char* abase);
		void operator()(const unsigned char*& ptr, unsigned char* abase);
		void tag(unsigned short _tag);
		bool saving();
	};

	class loadsave_save : public loadsave
	{
		std::vector<std::pair<char*, size_t> > memory;
		size_t nextptr;
		size_t used;
		inline void pushbytes(char* bytes, size_t amount);
		template<typename T> inline void do_op(T& x);
		template<typename T> inline void do_op(T& x, unsigned char _tag);
		template<typename T> void do_op(T* x, size_t s, unsigned char _tag);
		std::vector<char> cmp;
	public:
		loadsave_save();
		loadsave_save(const std::vector<char>& _memory);
		~loadsave_save() throw();

		int getOffset(){return (int)used;}

		void operator()(bool& x);
		void operator()(signed char& x);
		void operator()(unsigned char& x);
		void operator()(signed short& x);
		void operator()(unsigned short& x);
		void operator()(signed int& x);
		void operator()(unsigned int& x);
		void operator()(signed long& x);
		void operator()(unsigned long& x);
		void operator()(signed long long& x);
		void operator()(unsigned long long& x);
		void operator()(signed char* x, size_t s);
		void operator()(unsigned char* x, size_t s);
		void operator()(signed short* x, size_t s);
		void operator()(unsigned short* x, size_t s);
		void operator()(signed int* x, size_t s);
		void operator()(unsigned int* x, size_t s);
		void operator()(signed long* x, size_t s);
		void operator()(unsigned long* x, size_t s);
		void operator()(signed long long* x, size_t s);
		void operator()(unsigned long long* x, size_t s);
		void operator()(unsigned char*& ptr, unsigned char* abase);
		void operator()(const unsigned char*& ptr, unsigned char* abase);
		void tag(unsigned short _tag);
		bool saving();
		void get(std::vector<char>&);
	};
}

#endif
