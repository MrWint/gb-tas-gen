; Save format
; $0      map position
; $1-$7  unknown
; $8-$31  level status flags (bit3=bell,bit7=completed)
; $32-$3f  unknown
; $40-$41 coins
; $42	  beatenLevels
; $43	  lives
; $44	  kills
; $45	  checksum
; $46-$49 $12345678
sSaveFile1		EQU $a000 ; +79
sSaveFile2		EQU $a050 ; +79
sSaveFile3		EQU $a0a0 ; +79

sCurDemo		EQU $a0f0
sOAM			EQU $a100 ; +159

sCurPowerup		EQU $a216
sMarioAnimationCounter	EQU $a222
sMarioFacingDirection	EQU $a22b ; 1=right,ff=left
sLives			EQU $a22c

sTime			EQU	$a254 ; +1
sA257_unused	EQU $a257
sLevelBank		EQU $a258
sCoins			EQU $a262 ; +1
sFrameCountDown	EQU $a266 ; +1
sCurLevelIndex	EQU $a269
sLastFreeOAM	EQU $a26e

sFileSelectState	EQU $a277 ; 0=init pipe, 1=ready, 2=right, 3=left, 4=jump, 5=selected, 6=erased
sFileSelectCounter	EQU $a278
sFileSelectBomb	EQU $a2c6 ; 0=no bomb, ff=bomb

sCurLevelFlags	EQU $a27d
sBGP			EQU $a27e
sOBP0			EQU $a27f
sOBP1			EQU $a280
sMoonPhysics	EQU $a287
sNumKills		EQU $a28d

sAnimatedTileCounter	EQU $a298 ; animated tiles at $9200
sAnimatedTileMode		EQU $a299 ; 0=disabled, high nybble*2 = frequency, low nybble = tile select
sHitBell		EQU $a2a0 ; 0=not hit, ff=hit
sSelectedFile	EQU	$a2a3
sA2A7_unused	EQU $a2a7
sWindowY		EQU $a2b0
sWindowX		EQU $a2b1
sNumBeatenLevels	EQU $a2c5
sBombStarSprite	EQU $a2c7 ; !=0 -> render star sprite
sAutoScroll		EQU $a2c8
sAnimatedTileIndex		EQU $a2cd ; 0-3
sDemoMode		EQU $a2ce ; $00 = none, $0f = playback, $f0 = recording
sDemoCurIndex	EQU $a2cf
sLastJoyDown	EQU $a2d0
sLevelGFXSets	EQU $a2d2 ; low = mario gfx, high = block gfx

sEasyMode		EQU $a2e4 ; 1=easy

sDemoInputs		EQU $a300 ; +255

sPaused			EQU $a45d

sPlaySFX1		EQU $a460
sPlayingSFX1	EQU $a461
sPlayMusic		EQU $a468
sPlayingMusic	EQU $a469
sPlaySFX2		EQU $a470
sPlayingSFX2	EQU $a471
sPlaySFX3		EQU $a478
sPlayingSFX3	EQU $a479

sGS0cCurMap		EQU $a68b ; 0=overworld, 1=pumpkin, 2=tree, etc.
sGS0cRandomSlow	EQU	$a696 ; changes every 40 frames
sGS0cRandomFast_unused	EQU	$a6b2 ; changes every frame
sGS0cCounterDec	EQU	$a697
sGS0cCounterInc	EQU	$a69a
sGS0cVBlankSignal	EQU $a785

sLevelHeaderMarioPosY	EQU $a800 ; +1
sLevelHeaderMarioPosX	EQU $a802 ; +1
sLevelHeaderCameraPosY	EQU $a804 ; +1
sLevelHeaderCameraPosX	EQU $a806 ; +1
sLevelHeaderA808_unused		EQU $a808
sLevelHeaderA809_unused		EQU $a809
sLevelHeaderLevelNumber	EQU $a80a
sLevelHeaderTilesetPtr	EQU $a80b ; +1
sLevelHeaderBank		EQU $a80d
sLevelHeaderMusic		EQU $a80e
sLevelHeaderBGP			EQU $a80f
sLevelHeaderOBP0		EQU $a810
sLevelHeaderOBP1		EQU $a811
sLevelHeaderNrInBank	EQU $a812
sLevelHeaderTime		EQU $a813

sCurSaveData	EQU $a840 ; +63

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
hSpriteForeground	EQU $ffbb

hGameState		EQU $ff9b
hDmaMemory		EQU $ffa0 ; +9

hMarioYPos		EQU $ffc0 ; +1
hMarioXPos		EQU $ffc2 ; +1

hSpritePosY		EQU	$ffc4
hSpritePosX		EQU	$ffc5
hSpriteIndex	EQU	$ffc6
hSpriteOBP		EQU	$ffc7

