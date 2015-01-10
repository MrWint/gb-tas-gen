#!/bin/bash
rgbasm -o temp/sml2_10.o assembly/sml2_10.asm
rgblink temp/sml2_10.o -o temp/sml2_10.gb
rgbfix -v -l 0x01 -m 0x3 -n 0 -p 0 -r 0x2 -t "MARIOLAND2" temp/sml2_10.gb
cmp temp/sml2_10.gb roms/sml2_10.gb
