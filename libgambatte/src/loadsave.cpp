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
#include "loadsave.h"
#include <cstring>
#include <iostream>

namespace gambatte {

loadsave::~loadsave() throw() {}

loadsave_load::loadsave_load(const std::vector<char>& _memory)
	: memory(_memory)
{
	ptr = 0;
}

template<typename T> void loadsave_load::do_op(T& x)
{
	unsigned long long v = 0;
	if(ptr + sizeof(T) > memory.size())
		printf("Loadstate overflow"),exit(1);
	for(size_t i = 0; i < sizeof(T); i++)
		v |= ((unsigned long long)(unsigned char)memory[ptr++] << (8 * (sizeof(T) - i - 1)));
	x = (T)v;
}

template<typename T> void loadsave_load::do_op(T& x, unsigned char _tag)
{
	if(ptr + 1 > memory.size())
		printf("Loadstate overflow");
	unsigned char _rtag = memory[ptr++];
	if(_rtag != _tag) {
		std::cout << "Wrong type tag: expected=" << (int)_tag << ", got=" << (int)_rtag << std::endl;
		std::cout << "Loadstate desynced" << std::endl;
	}
	do_op(x);
}

template<typename T> void loadsave_load::do_op(T* x, size_t s, unsigned char _tag)
{
	if(ptr + 1 > memory.size())
		printf("Loadstate overflow");
	unsigned char _rtag = memory[ptr++];
	if(_rtag != _tag) {
		std::cout << "Wrong type tag: expected=" << (int)_tag << ", got=" << (int)_rtag << std::endl;
		std::cout << "Loadstate desynced" << std::endl;
	}
	unsigned size;
	do_op(size);
	if(size != s) {
		std::cout << "Wrong number of entries: expected=" << s << ", got=" << size << std::endl;
		std::cout << "Loadstate desynced" << std::endl;
	}
	for(size_t i = 0; i < s; i++)
		do_op(x[i]);
}

void loadsave_load::operator()(bool& x)
{
	char c;
	do_op(c, 0);
	x = (c != 0);
}


loadsave_load::~loadsave_load() throw() {}
void loadsave_load::operator()(signed char& x) { do_op(x, 1); }
void loadsave_load::operator()(unsigned char& x) { do_op(x, 2); }
void loadsave_load::operator()(signed short& x) { do_op(x, 3); }
void loadsave_load::operator()(unsigned short& x) { do_op(x, 4); }
void loadsave_load::operator()(signed int& x) { do_op(x, 5); }
void loadsave_load::operator()(unsigned int& x) { do_op(x, 6); }
void loadsave_load::operator()(signed long& x) { do_op(x, 5); }
void loadsave_load::operator()(unsigned long& x) { do_op(x, 6); }
void loadsave_load::operator()(signed long long& x) { do_op(x, 7); }
void loadsave_load::operator()(unsigned long long& x) { do_op(x, 8); }
void loadsave_load::operator()(signed char* x, size_t s) { do_op(x, s, 9); }
void loadsave_load::operator()(unsigned char* x, size_t s) { do_op(x, s, 10); }
void loadsave_load::operator()(signed short* x, size_t s) { do_op(x, s, 11); }
void loadsave_load::operator()(unsigned short* x, size_t s) { do_op(x, s, 12); }
void loadsave_load::operator()(signed int* x, size_t s) { do_op(x, s, 13); }
void loadsave_load::operator()(unsigned int* x, size_t s) { do_op(x, s, 14); }
void loadsave_load::operator()(signed long* x, size_t s) { do_op(x, s, 13); }
void loadsave_load::operator()(unsigned long* x, size_t s) { do_op(x, s, 14); }
void loadsave_load::operator()(signed long long* x, size_t s) { do_op(x, s, 15); }
void loadsave_load::operator()(unsigned long long* x, size_t s) { do_op(x, s, 16); }

void loadsave_load::tag(unsigned short _tag)
{
	unsigned short _rtag;
	do_op(_rtag, 18);
	if(_tag != _rtag) {
		std::cerr << "Wrong inner tag: expected=" << (int)_tag << ", got=" << (int)_rtag << std::endl;
		printf("Loadstate desynced");
	}
}

void loadsave_load::operator()(unsigned char*& ptr, unsigned char* abase)
{
	char x;
	do_op(x, 17);
	if(!x)
		ptr = NULL;
	else {
		unsigned y;
		do_op(y);
		ptr = abase + y;
	}
}

void loadsave_load::operator()(const unsigned char*& ptr, unsigned char* abase)
{
	char x;
	do_op(x, 19);
	if(!x)
		ptr = NULL;
	else {
		unsigned y;
		do_op(y);
		ptr = abase + y;
	}
}


bool loadsave_load::saving() { return false; }

#define BLOCKBYTES 65500

void loadsave_save::pushbytes(char* bytes, size_t amount)
{
	if(!nextptr || memory[nextptr - 1].second + amount > BLOCKBYTES) {
		memory.push_back(std::make_pair(new char[BLOCKBYTES], (size_t)0));
		nextptr++;
	}
	if(cmp.size())
		if(cmp.size() < used + amount || memcmp(&cmp[used], bytes, amount)) printf("42");
	memcpy(memory[nextptr - 1].first + memory[nextptr - 1].second, bytes, amount);
	memory[nextptr - 1].second += amount;
	used += amount;
}

template<typename T> void loadsave_save::do_op(T& x)
{
	unsigned long long v = x;
	char buf[sizeof(T)];
	for(size_t i = 0; i < sizeof(T); i++)
		buf[i] = v >> (8 * (sizeof(T) - i - 1));
	pushbytes(buf, sizeof(T));
}

template<typename T> void loadsave_save::do_op(T& x, unsigned char _tag)
{
	pushbytes((char*)&_tag, 1);
	do_op(x);
}

template<typename T> void loadsave_save::do_op(T* x, size_t s, unsigned char _tag)
{
	pushbytes((char*)&_tag, 1);
	unsigned size = s;
	do_op(size);
	for(size_t i = 0; i < s; i++)
		do_op(x[i]);
}

loadsave_save::loadsave_save()
{
	used = 0;
	nextptr = 0;
}

loadsave_save::loadsave_save(const std::vector<char>& _memory)
{
	used = 0;
	nextptr = 0;
	cmp = _memory;
}

loadsave_save::~loadsave_save() throw()
{
	for(int i = 0; i<(int)memory.size(); i++)
		delete[] memory[i].first;
}

void loadsave_save::operator()(bool& x)
{
	char y = x ? 1 : 0;
	char z = 0;
	pushbytes(&z, 1);
	pushbytes(&y, 1);
}

void loadsave_save::operator()(signed char& x) { do_op(x, 1); }
void loadsave_save::operator()(unsigned char& x) { do_op(x, 2); }
void loadsave_save::operator()(signed short& x) { do_op(x, 3); }
void loadsave_save::operator()(unsigned short& x) { do_op(x, 4); }
void loadsave_save::operator()(signed int& x) { do_op(x, 5); }
void loadsave_save::operator()(unsigned int& x) { do_op(x, 6); }
void loadsave_save::operator()(signed long& x) { do_op(x, 5); }
void loadsave_save::operator()(unsigned long& x) { do_op(x, 6); }
void loadsave_save::operator()(signed long long& x) { do_op(x, 7); }
void loadsave_save::operator()(unsigned long long& x) { do_op(x, 8); }
void loadsave_save::operator()(signed char* x, size_t s) { do_op(x, s, 9); }
void loadsave_save::operator()(unsigned char* x, size_t s) { do_op(x, s, 10); }
void loadsave_save::operator()(signed short* x, size_t s) { do_op(x, s, 11); }
void loadsave_save::operator()(unsigned short* x, size_t s) { do_op(x, s, 12); }
void loadsave_save::operator()(signed int* x, size_t s) { do_op(x, s, 13); }
void loadsave_save::operator()(unsigned int* x, size_t s) { do_op(x, s, 14); }
void loadsave_save::operator()(signed long* x, size_t s) { do_op(x, s, 13); }
void loadsave_save::operator()(unsigned long* x, size_t s) { do_op(x, s, 14); }
void loadsave_save::operator()(signed long long* x, size_t s) { do_op(x, s, 15); }
void loadsave_save::operator()(unsigned long long* x, size_t s) { do_op(x, s, 16); }
bool loadsave_save::saving() { return true; }

void loadsave_save::operator()(unsigned char*& ptr, unsigned char* abase)
{
	if(!ptr) {
		char x = 0;
		do_op(x, 17);
	} else {
		char x = 1;
		unsigned y = ptr - abase;
		do_op(x, 17);
		do_op(y);
	}
}

void loadsave_save::operator()(const unsigned char*& ptr, unsigned char* abase)
{
	if(!ptr) {
		char x = 0;
		do_op(x, 19);
	} else {
		char x = 1;
		unsigned y = ptr - abase;
		do_op(x, 19);
		do_op(y);
	}
}

void loadsave_save::tag(unsigned short _tag)
{
	do_op(_tag, 18);
}

void loadsave_save::get(std::vector<char>& x)
{
	x.resize(used);
	size_t ptr = 0;
	for(int i = 0; i<(int)memory.size(); i++) {
		memcpy(&x[ptr], memory[i].first, memory[i].second);
		ptr += memory[i].second;
	}
}
}
