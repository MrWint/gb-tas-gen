#!/bin/bash
rgbasm -o temp/tetris10.o assembly/tetris10.asm
rgblink temp/tetris10.o -o temp/tetris10.gb
rgbfix -v -l 0x1 -m 0x0 -p 0 -r 00 -t "TETRIS" temp/tetris10.gb
cmp temp/tetris10.gb roms/tetris10.gb
