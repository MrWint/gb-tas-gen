#!/bin/bash
rgbasm -o temp/tetrisdx.o assembly/tetrisdx.asm
rgblink -o temp/tetrisdx.gbc temp/tetrisdx.o
rgbfix -v -l 0x33 -m 0x3 -p 0 -r 2 -t "TETRIS DX" temp/tetrisdx.gbc
cmp temp/tetrisdx.gbc roms/tetrisdx.gbc
