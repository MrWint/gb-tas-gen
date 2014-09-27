#!/bin/bash
rgbasm -o temp/tetris11.o assembly/tetris11.asm
rgblink temp/tetris11.o -o temp/tetris11.gb
rgbfix -v -l 0x1 -m 0x0 -n 1 -p 0 -r 00 -t "TETRIS" temp/tetris11.gb
cmp temp/tetris11.gb roms/tetris11.gb
