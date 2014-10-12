
; BCD-encoded, little endian
wScore			EQU $c0a0 ; +2

wFinishedRowPtrs	EQU $c0a3 ; +7

wSinglesCount	EQU $c0ac
wSinglesScoreCount	EQU $c0ad
wSinglesScore	EQU $c0ae ; +2
wDoublesCount	EQU $c0b1
wDoublesScoreCount	EQU $c0b2
wDoublesScore	EQU $c0b3 ; +2
wTriplesCount	EQU $c0b6
wTriplesScoreCount	EQU $c0b7
wTriplesScore	EQU $c0b8 ; +2
wTetrisCount	EQU $c0bb
wTetrisScoreCount	EQU $c0bc
wTetrisScore	EQU $c0bd ; +2
wDropsCount		EQU $c0c0
wDropsScore		EQU $c0c2 ; +2
wPreventHoldDown EQU $c0c7

wCurPieceY		EQU $c201
wCurPieceX		EQU $c202
wCurPiece		EQU $c203
wPreviewPieceY	EQU $c211
wPreviewPieceX	EQU $c212
wPreviewPiece	EQU $c213

hJoyDown		EQU $ff80
hJoyPressed		EQU $ff81

hVBlankSignal	EQU $ff85 ; set after VBlank

hCurPieceState	EQU $ff98 ; 0 -> falling, 1 -> fallen, 2 -> locked, 3 -> counted
hRemainingFrameDelay	EQU $ff99
hLvlFrameDelay	EQU $ff9a

hNumFinishedLines	EQU $ff9e ; +1

hMainLoopCounter1	EQU $ffa6 ; counts down to 0
hMainLoopCounter2	EQU $ffa7 ; counts down to 0
hLevel			EQU $ffa9 ; not considering heart
hButtonHoldCounter EQU $ffaa
hGamePaused		EQU $ffab
hP1High			EQU $ffac
hP2High			EQU $ffad
hNextPreviewPiece	EQU $ffae ; the one after the preview
hDemoNextPiecePtr	EQU $ffaf ; +1

hDmaRoutine		EQU $ffb6 ; +9
hTypeASelectedLevel	EQU $ffc2
hTypeBSelectedLevel	EQU $ffc3
hTypeBSelectedHigh	EQU $ffc4

h2PSendOverRows	EQU $ffdc

hGameState		EQU $ffe1
hVBlankCounter	EQU $ffe2 ; counts up, no known uses
hBoardUpdateState	EQU $ffe3 ; 0 -> no update, 1 -> moving up lines, 2-19 -> redraw board row (19-x)

hDemo			EQU $ffe4 ; 0 (no demo), 1 or 2
hCurPieceDropCount	EQU $ffe5 ; resets to 0 if down is released

hUnused_disableJoypad EQU $ffe9
hDemoJoyDownFrames EQU $ffea
hDemoControlDataPtr EQU $ffeb ; +1
hDemoSimulatedJoyDown EQU $ffed
hDemoStoredJoyDown EQU $ffee

hSRocketType	EQU $fff3 ; $58, $59 or $5a
hHeartMode		EQU $fff4 ; >0 -> heart mode







GS_INGAME				EQU $00
GS_INGAME_LOST			EQU $01		; goes to $0d
GS_WON_ROCKET_LAUNCH3	EQU $02		; liftoff
GS_WON_ROCKET_LAUNCH4	EQU $03		; liftoff with engine fire
GS_INGAME_GAMEOVER		EQU $04		; lost or won, wait for button press
GS_INGAME_B_WON			EQU $05		; plays sound, goes to $0b
GS_TITLE_LOADING		EQU $06
GS_TITLE				EQU $07
GS_TYPE_SELECT_LOADING	EQU $08
GS_NOTHING_09			EQU $09		; does nothing
GS_INGAME_LOADING		EQU $0a
GS_INGAME_B_SCORES		EQU $0b		; counting, goes to $04
GS_WON_ROCKET_LAUNCH_ON_BUTTONPRESS	EQU $0c	; goes to $02 on buttonpress
GS_INGAME_LOST_MEH		EQU $0d		; fills board, goes to $04
GS_TYPE_SELECT			EQU $0e
GS_MUSIC_SELECT			EQU $0f
GS_A_LEVEL_SELECT_LOADING EQU $10
GS_A_LEVEL_SELECT		EQU $11
GS_B_LEVEL_SELECT_LOADING EQU $12
GS_B_LEVEL_SELECT		EQU $13
GS_B_HIGH_SELECT		EQU $14
GS_LEVEL_SELECT_HIGHSCORE EQU $15	; for A and B
GS_2P_HIGH_SELECT_LOADING EQU $16
GS_2P_HIGH_SELECT		EQU $17
GS_2P_INGAME_LOADING	EQU $18
GS_2P_INGAME_HIGH		EQU $19
GS_2P_INGAME			EQU $1a
GS_2P_INGAME_END		EQU $1b		; win or (lose after $01 and $d)
GS_2P_UNKNOWN			EQU $1c		; goes to $1a eventually
GS_2P_WON_LOADING		EQU $1d		; counts win/lose
GS_2P_LOST_LOADING		EQU $1e		; counts win/lose
GS_2P_REMATCH			EQU $1f
GS_2P_WON				EQU $20
GS_2P_LOST				EQU $21
GS_WON_DANCING_LOADING	EQU $22
GS_WON_DANCING			EQU $23
GS_LEGAL_LOADING		EQU $24
GS_LEGAL_NOSKIP			EQU $25
GS_WON_L_ROCKET_LOADING	EQU $26
GS_WON_L_ROCKET			EQU $27
GS_WON_L_ROCKET_LAUNCH1	EQU $28		; small steam
GS_WON_L_ROCKET_LAUNCH2	EQU $29		; big steam
GS_2P_TYPE_SELECT_LOADING EQU $2a	; goes to $e and immediately to $2b
GS_2P_MUSIC_SELECT		EQU $2b
GS_WON_ROCKET_LAUNCH_CONGRATS1 EQU $2c	; letter typing
GS_WON_ROCKET_LAUNCH_CONGRATS2 EQU $2d	; wait, then $0b
GS_WON_S_ROCKET_LOADING	EQU $2e
GS_WON_S_ROCKET			EQU $2f
GS_WON_S_ROCKET_LAUNCH1	EQU $30		; small steam
GS_WON_S_ROCKET_LAUNCH2	EQU $31		; big steam
GS_WON_S_ROCKET_LAUNCH3	EQU $32		; launch, small engine
GS_WON_ROCKET_LAUNCH_END EQU $33	; back to level select
GS_WON_S_ROCKET_WAIT	EQU $34		; goes to $2e
GS_LEGAL_SKIP			EQU $35
GS_NOTHING_36			EQU $36		; does nothing

