
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

