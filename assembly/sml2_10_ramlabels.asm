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
sSaveFile1				EQU $a000 ; +79
sSaveFile2				EQU $a050 ; +79
sSaveFile3				EQU $a0a0 ; +79
sCurDemo				EQU $a0f0

sOAM					EQU $a100 ; +159

sLoadStripeCurBlockPtr	EQU $a203 ; +1
sPosBgMapPtr			EQU $a205 ; +1
sLastMarioYPos			EQU $a20c
sBlockAtPos				EQU $a20d
sScreenTransitionDirection EQU $a20e
sCurPowerup				EQU $a216
sMarioAnimationCounter	EQU $a222
sStripeRefreshDirection	EQU $a223 ; flags: DULRxxxx
sMarioDead				EQU $a224 ; 0=alive, ff=dead, 15=loaded save
sMarioXPos				EQU $a227 ; +1
sMarioYPos				EQU $a229 ; +1
sMarioFacingDirection	EQU $a22b ; 1=right,ff=left
sLives					EQU $a22c
sScrollRightDist		EQU $a235
sScrollLeftDist			EQU $a236
sScrollUpDist			EQU $a237
sScrollDownDist			EQU $a238
sMarioScreenPosY		EQU $a23b
sMarioScreenPosX		EQU $a23c
sA249_unused			EQU $a249
sA24A_unused			EQU $a24a
sTimeFrameCounter		EQU $a24b ; counts down from $30
sA24C_unused			EQU $a24c
sTime					EQU	$a254 ; +1
sA257_unused			EQU $a257
sLevelBank				EQU $a258
sCoins					EQU $a262 ; +1
sSavedNextFreeOAM		EQU $a264
sFrameCountDown			EQU $a266 ; +1
sCurLevelIndex			EQU $a269
sLastFreeOAM			EQU $a26e
sFileSelectState		EQU $a277 ; 0=init pipe, 1=ready, 2=right, 3=left, 4=jump, 5=selected, 6=erased
sFileSelectCounter		EQU $a278

sCurLevelFlags			EQU $a27d
sBGP					EQU $a27e
sOBP0					EQU $a27f
sOBP1					EQU $a280
sMoonPhysics			EQU $a287
sA288_unused			EQU $a288
sNumKills				EQU $a28d
sPipeFunc				EQU $a28e
sA28F_unused			EQU $a28f
sLevelMusic				EQU $a292
sAnimatedTileCounter	EQU $a298 ; animated tiles at $9200
sAnimatedTileMode		EQU $a299 ; 0=disabled, high nybble*2 = frequency, low nybble = tile select
sA29B_unused			EQU $a29b
sA29E_unused			EQU $a29e
sHitBell				EQU $a2a0 ; 0=not hit, ff=hit
sSelectedFile			EQU	$a2a3
sA2A7_unused			EQU $a2a7
sWindowY				EQU $a2b0
sWindowX				EQU $a2b1
sNumBeatenLevels		EQU $a2c5
sFileSelectBomb			EQU $a2c6 ; 0=no bomb, ff=bomb
sBombStarSprite			EQU $a2c7 ; !=0 -> render star sprite
sAutoScroll				EQU $a2c8
sAnimatedTileIndex		EQU $a2cd ; 0-3
sDemoMode				EQU $a2ce ; $00 = none, $0f = playback, $f0 = recording
sDemoCurIndex			EQU $a2cf
sLastJoyDown			EQU $a2d0
sLevelGFXSets			EQU $a2d2 ; low = mario gfx, high = block gfx
sEasyMode				EQU $a2e4 ; 1=easy
sScrollUpThreshold		EQU $a2d9 ; 0=more hesitant to scroll up when moving upwards
sDemoInputs				EQU $a300 ; +255

sPaused					EQU $a45d
sPlaySFX1				EQU $a460
sPlayingSFX1			EQU $a461
sPlayMusic				EQU $a468
sPlayingMusic			EQU $a469
sPlaySFX2				EQU $a470
sPlayingSFX2			EQU $a471
sPlaySFX3				EQU $a478
sPlayingSFX3			EQU $a479

sA53F_sound				EQU $a53f

sGS0cCurMap				EQU $a68b ; 0=overworld, 1=pumpkin, 2=tree, etc.
sGS0cRandomSlow			EQU	$a696 ; changes every 40 frames
sGS0cRandomFast_unused	EQU	$a6b2 ; changes every frame
sGS0cCounterDec			EQU	$a697
sGS0cCounterInc			EQU	$a69a

sTilesetData			EQU $a600 ; +167

sGS0cVBlankSignal		EQU $a785

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

sCurSaveData			EQU $a840 ; +63
sMarioShotsData			Equ $a880 ; +31

sPipeFuncArray			EQU $a900 ; +47
sScrollArray			EQU $a960 ; +47

hJoyDown				EQU $ff80
hJoyPressed				EQU $ff81
hVBlankSignal			EQU $ff82 ; set after VBlank
hFF8C_unused			EQU $ff8c
hNextFreeOAM			EQU $ff8d ; $a100 + x
hTickCounter			EQU $ff97
hFF98					EQU $ff98
hFF99					EQU $ff99
hGameState				EQU $ff9b
hDmaMemory				EQU $ffa0 ; +9
hLoadStripeBgMapPtr		EQU $ffaa ; +1
hLoadStripeCounter		EQU $ffae
hBlockLoadListPtr		EQU $ffaf ; +1 ; init to $aa00, 6 bytes per entry (ptr, 4 tiles), terminated with xx00
hFFB1					EQU $ffb1 ; +5
hPosY					EQU $ffb7 ; +1
hPosX					EQU $ffb9 ; +1
hSpriteForeground		EQU $ffbb


hMarioYPos				EQU $ffc0 ; +1
hMarioXPos				EQU $ffc2 ; +1

hSpritePosY				EQU	$ffc4
hSpritePosX				EQU	$ffc5
hSpriteIndex			EQU	$ffc6
hSpriteOBP				EQU	$ffc7
hCameraCenterY			EQU $ffc8 ; +1
hCameraCenterX			EQU $ffca ; +1
hLoadStripeY			EQU $ffcc ; +1
hLoadStripeX			EQU $ffce ; +1

