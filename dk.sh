#!/bin/bash
rgbasm -o temp/dk.o assembly/dk.asm
rgblink temp/dk.o -o temp/dk.gb
rgbfix -v -l 0x33 -m 0x3 -n 0 -p 0 -r 0x2 -t "DONKEY KONG" temp/dk.gb
cmp temp/dk.gb roms/dk.gb
