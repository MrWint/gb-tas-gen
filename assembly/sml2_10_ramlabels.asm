sCurDemo		EQU $a0f0
sOAM			EQU $a100 ; +159
sFrameCountDown	EQU $a266 ; +1
sLastFreeOAM	EQU $a26e

sAnimatedTileCounter	EQU $a298 ; animated tiles at $9200
sAnimatedTileMode		EQU $a299 ; 0=disabled, high nybble*2 = frequency, low nybble = tile select
sWindowY		EQU $a2b0
sWindowX		EQU $a2b1
sAnimatedTileIndex		EQU $a2cd ; 0-3
sDemoMode		EQU $a2ce ; $00 = none, $0f = playback, $f0 = recording
sDemoCurIndex	EQU $a2cf
sLastJoyDown	EQU $a2d0

sDemoInputs		EQU $a300 ; +255

sPaused			EQU $a45d

hJoyDown		EQU $ff80
hJoyPressed		EQU $ff81
hVBlankSignal	EQU $ff82 ; set after VBlank
hFF8C_unused	EQU $ff8c
hNextFreeOAM	EQU $ff8d ; $a100 + x
hTickCounter	EQU $ff97
hFF98			EQU $ff98
hFF99			EQU $ff99
hFFAA			EQU $ffaa ; +1
hFFAE			EQU $ffae
hFFAF			EQU $ffaf ; +1
hFFB1			EQU $ffb1 ; +5
hFFB7			EQU $ffb7 ; +1
hFFB9			EQU $ffb9 ; +1
hFFBB			EQU $ffbb

hGameState		EQU $ff9b
hDmaMemory		EQU $ffa0 ; +9

