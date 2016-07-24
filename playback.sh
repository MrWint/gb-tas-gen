#!/bin/bash
rgbasm -o temp/playback.o assembly/playback.asm
rgblink -o temp/playback.gbc -m temp/playback.map -n temp/playback.sym temp/playback.o
rgbfix -C -j -k "MW" -l 0x33 -v -m 0x0 -n 0 -p 0 -r 0x0 -t "PLAYBACK" temp/playback.gbc

