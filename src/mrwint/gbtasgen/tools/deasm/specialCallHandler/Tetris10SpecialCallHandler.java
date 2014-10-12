package mrwint.gbtasgen.tools.deasm.specialCallHandler;

import mrwint.gbtasgen.tools.deasm.CPUState;
import mrwint.gbtasgen.tools.deasm.DFS;
import mrwint.gbtasgen.tools.deasm.ROM;

public class Tetris10SpecialCallHandler extends SpecialCallHandler {

	@Override
	public boolean vetoContinueAfterCall(int currentAddress, int jumpAddress) {
		if(jumpAddress == 0x28) // messes with stack, reads return address as jumptable
			return true;
		if(jumpAddress == 0x38) // invalid Joypad interrupt
			return true;
		return false;
	}

	@Override
	public void handleBeforeOp(int currentAddress, CPUState s) {
		s.loadedBank = 0x1;
		return;
	}

	@Override
	public void handleDFSInit() {
		// rst $28
		dfs.addJumpTable(0x191, 5);
		dfs.addJumpTable(0x37a, 54, "GameStateTable");

		dfs.addJumpTable(0x6500, 8);
		dfs.addJumpTable(0x6510, 8);
		dfs.addJumpTable(0x6520, 4);
		dfs.addJumpTable(0x6528, 4);

		dfs.addFunction(0x0, "Unused_rst00");
		dfs.addRawBytes(0x3, 5);
		dfs.addFunction(0x8, "Unused_rst08");
		dfs.addRawBytes(0xb, 29);
		dfs.addRawBytes(0x34, 12);
		dfs.addRawBytes(0x43, 5);
		dfs.addRawBytes(0x4b, 5);
		dfs.addRawBytes(0x53, 5);
//		dfs.addFunction(0xd0, ".unused_00d0");
//		dfs.addRawBytes(0xda, 38);
//		dfs.addIgnore(0x104, 0x4c);
//		dfs.addSection(0x150, "Start");
//		dfs.addFunction(0x153, ".unused_0153");
//		dfs.addFunction(0x474, ".unused_0474");
//		dfs.addFunction(0x57d, ".unused_057d");
//		dfs.addFunction(0x582, null);
//		dfs.addByteArray(0x705, 8, ".unknown_2P_0705",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x7f6, 6, "HighCursorPosP1_2P", ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x802, 6, "HighCursorPosP2_2P", ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x8c4, 4, ".unknown_2P_08c4",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x8d4, 4, ".unknown_2P_08d4",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0xf3c, 9, ".unknown_2P_0f3c",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addFunction(0x10a8, ".unused_10a8");
//		dfs.addByteArray(0x10ed, 6, "Unknown_2P_10ed", ROM.FORMAT_HEX);
//		dfs.addByteArray(0x10f3, 11, "Unknown_2P_10f3", ROM.FORMAT_HEX);
//		dfs.addByteArray(0x10fe, 11, "Unknown_2P_10fe", ROM.FORMAT_HEX);
//		dfs.addByteArray(0x1109, 9, "Unknown_2P_1109", ROM.FORMAT_HEX);
//		dfs.addByteArray(0x12f5, 16, "Unknown_2c_12f5", ROM.FORMAT_HEX);
//		dfs.addByteArray(0x14a8, 4, "Unknown_2b_14a8", ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//
//		dfs.addByteArray(0x141b, 1, "Unknown_26_141b", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x1422, 1, "Unknown_26_1422", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x1429, 1, "Unknown_26_1429", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x1430, 1, "Unknown_26_1430", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x1615, 10, "TypeALevelCursorPos", ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x16d2, 10, "TypeBLevelCursorPos", ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x1741, 6, "TypeBHighCursorPos", ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addRawBytes(0x174d, 1);
//
		dfs.addByteArray(0x1b61, 21, "LevelFrameDelayList", ROM.FORMAT_DEC);
		dfs.addByteArray(0x1b9b, 4, "DemoHighLineData", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x1cdd, 1, "PauseText", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x1e31, 10, ".unknown_22_1e31", ROM.FORMAT_HEX);
//
//		dfs.addFunction(0x2665, ".unused_2665");
//		dfs.addByteArray(0x26bf, 1, "Unknown_list_26bf", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x26c7, 1, "Unknown_list_26c7", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x26cf, 2, "Unknown_2a_26cf", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x26db, 1, "Unknown_10_26db", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x26e1, 2, "Unknown_12_26e1", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x26ed, 2, "Unknown_16_26ed", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x26f9, 3, "Unknown_1d_26f9", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x270b, 3, "Unknown_1d_270b", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x271d, 2, "Unknown_1e_271d", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x2729, 2, "Unknown_1e_2729", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x2735, 10, "Unknown_22_2735", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x2771, 3, "Unknown_26_2771", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x2783, 3, "Unknown_2e_2783", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//
//		dfs.addFunction(0x27e1, ".unused_27e1");
//
//		dfs.addByteArray(0x2839, 10, "HitStartToContinueOverlay",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x2889, 18, "ScoreCountScreen",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x293d, 1, null, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x293e, 7, "GameOverOverlay",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x2976, 6, "PleaseTryAgainOverlay",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//
//		dfs.addFunction(0x2a10, ".unused_2a10");
//		dfs.addFunction(0x2a18, ".unused_2a18");
		dfs.addPointerTable(0x2bac, 94, "PieceDataTable");
		String[] knownPieces = {
				"LRot0", "LRot1", "LRot2", "LRot3",
				"JRot0", "JRot1", "JRot2", "JRot3",
				"IRot0", "IRot1", "IRot2", "IRot3",
				"ORot0", "ORot1", "ORot2", "ORot3",
				"ZRot0", "ZRot1", "ZRot2", "ZRot3",
				"SRot0", "SRot1", "SRot2", "SRot3",
				"TRot0", "TRot1", "TRot2", "TRot3",
				"AType", "BType", "CType", "OffType",
				"Num0", "Num1", "Num2", "Num3", "Num4", "Num5", "Num6", "Num7", "Num8", "Num9",
		};
		for (int i=0;i<94; i++) {
			String name = i<knownPieces.length ? knownPieces[i] : "Unknown";
			int add = dfs.rom.payloadAsAddress[0x2bac+2*i];
			dfs.addPointerTable(add, 1, name+"D1_"+Integer.toHexString(add));
			dfs.addRawBytes(add+2, 2);
			int addadd = dfs.rom.payloadAsAddress[add];
			dfs.addPointerTable(addadd, 1, name+"D2_"+Integer.toHexString(addadd));
			int cadd = addadd+2;
			while ((cadd - addadd < 0x100) && dfs.rom.data[cadd] != (byte)0xff) cadd++;
			dfs.addRawBytes(addadd + 2, cadd - addadd - 1);
			int addaddadd = dfs.rom.payloadAsAddress[addadd];
			dfs.addByteArray(addaddadd, 1, null,
					ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
					ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
					ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
					ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		}
		dfs.addRawBytes(0x31f1, 0x20, "Shape4x4");
		dfs.addRawBytes(0x3211, 0x10, "Shape8x1");
		dfs.addRawBytes(0x3221, 0x1c, "Shape2x7");
		dfs.addRawBytes(0x323d, 0x38, "ShapeRocket4x8");
		dfs.addRawBytes(0x3275, 0x12, "Shape3x3");

		dfs.addRawBytes(0x3287, 0xc50, "UnknownTileset2bpp_3287");

		dfs.addByteArray(0x3ed7, 0xe, "IngameTypeAScreenTiles",
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x3fef, 1, null,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX);
		dfs.addByteArray(0x4000, 1, null,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x4003, 0x3, null,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x403f, 0x12, "IngameTypeBScreenTiles",
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//
		dfs.addByteArray(0x41a7, 0x138, "AlphabetTileset1bpp", ROM.FORMAT_BIN);
		dfs.addRawBytes(0x42df, 0xa0, "UnknownTileset2bpp_42df");
		dfs.addRawBytes(0x437f, 0x6d0, "UnknownTileset2bpp_437f");
//		dfs.addByteArray(0x4a07, 0x12, "LegalScreenTiles",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x4b6f, 0x12, "TitleScreenTiles",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x4cd7, 0x12, "TypeSelectScreenTiles",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x4e3f, 0x12, "TypeALevelSelectScreenTiles",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x4fa7, 0x12, "TypeBLevelSelectScreenTiles",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x510f, 18, "WonDancingScreen",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x51c3, 1, null, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x51c4, 4, "RocketPlatformScreenLines",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x5214, 0x12, "HighSelect2PScreenTiles",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x537c, 0x12, "Unknown_2P_537c_ScreenTiles",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x54e4, 4, "WinsLosses2PHeaderScreenLines",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addByteArray(0x5534, 6, "WinsLosses2PFooterScreenLines",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//
//		dfs.addRawBytes(0x55ac, 0xd04, "MarioLuigiRocketTileset2bpp");
//
//		dfs.addByteArray(0x62b0, 0x80, "Demo1Controls", ROM.FORMAT_BUTTONS, ROM.FORMAT_DEC);
//		dfs.addByteArray(0x63b0, 0x50, "Demo2Controls", ROM.FORMAT_BUTTONS, ROM.FORMAT_DEC);
//		dfs.addRawBytes(0x6450, 0x30, "Unknown_24_6450");
//
//		dfs.addPointerTable(0x64b0, 0x11, "Unknown_Sound_64b0");
//		for (int i=0;i<0x11; i++) {
//			int add = dfs.rom.payloadAsAddress[0x64b0+2*i];
//			dfs.addRawBytes(add, 1, "UnknownD1_Sound_"+Integer.toHexString(add));
//			dfs.addPointerTable(add+1, 5);
//			for (int j=0;j<5;j++)
//				if (dfs.rom.payloadAsAddress[add+1+2*j] == 0)
//					dfs.rom.payloadAsAddress[add+1+2*j] = -1;
//
//			dfs.addRawBytes(0x6ef9, 0xc, "UnknownD2_Sound_6ef9");
//			dfs.addRawBytes(0x6f05, 0x9, "UnknownD2_Sound_6f05");
//			dfs.addRawBytes(0x6f0e, 0x1d, "UnknownD2_Sound_6f0e");
//			dfs.addRawBytes(0x6f2b, 0x14, "UnknownD2_Sound_6f2b");
//			dfs.addRawBytes(0x6ffa, 0xe, "UnknownD2_Sound_6ffa");
//			dfs.addRawBytes(0x7008, 0x13a, "UnknownD2_Sound_7008");
//			dfs.addRawBytes(0x7142, 0xa, "UnknownD2_Sound_7142");
//			dfs.addRawBytes(0x714c, 0xa, "UnknownD2_Sound_714c");
//			dfs.addRawBytes(0x7156, 0xc, "UnknownD2_Sound_7156");
//			dfs.addRawBytes(0x7162, 0x156, "UnknownD2_Sound_7162");
//			dfs.addRawBytes(0x72b8, 0xe, "UnknownD2_Sound_72b8");
//			dfs.addRawBytes(0x72c6, 0xe, "UnknownD2_Sound_72c6");
//			dfs.addRawBytes(0x72d4, 0x2e, "UnknownD2_Sound_72d4");
//			dfs.addRawBytes(0x7302, 0x241, "UnknownD2_Sound_7302");
//			dfs.addRawBytes(0x7543, 0x8, "UnknownD2_Sound_7543");
//			dfs.addRawBytes(0x754b, 0x6, "UnknownD2_Sound_754b");
//			dfs.addRawBytes(0x7551, 0x3c, "UnknownD2_Sound_7551");
//			dfs.addRawBytes(0x758d, 0x8, "UnknownD2_Sound_758d");
//			dfs.addRawBytes(0x7595, 0x6, "UnknownD2_Sound_7595");
//			dfs.addRawBytes(0x759b, 0x61, "UnknownD2_Sound_759b");
//			dfs.addRawBytes(0x75fc, 0x4, "UnknownD2_Sound_75fc");
//			dfs.addRawBytes(0x7600, 0x2, "UnknownD2_Sound_7600");
//			dfs.addRawBytes(0x7602, 0x31, "UnknownD2_Sound_7602");
//			dfs.addRawBytes(0x7633, 0x8, "UnknownD2_Sound_7633");
//			dfs.addRawBytes(0x763b, 0x6, "UnknownD2_Sound_763b");
//			dfs.addRawBytes(0x7641, 0x22, "UnknownD2_Sound_7641");
//			dfs.addRawBytes(0x7663, 0x209, "UnknownD2_Sound_7663");
//			dfs.addRawBytes(0x786c, 0xa, "UnknownD2_Sound_786c");
//			dfs.addRawBytes(0x7876, 0x8, "UnknownD2_Sound_7876");
//			dfs.addRawBytes(0x787e, 0x8, "UnknownD2_Sound_787e");
//			dfs.addRawBytes(0x7886, 0x17a, "UnknownD2_Sound_7886");
//			dfs.addRawBytes(0x7a00, 0x26, "UnknownD2_Sound_7a00");
//			dfs.addRawBytes(0x7a26, 0x4, "UnknownD2_Sound_7a26");
//			dfs.addRawBytes(0x7a2a, 0x45, "UnknownD2_Sound_7a2a");
//			dfs.addRawBytes(0x7a6f, 0x4, "UnknownD2_Sound_7a6f");
//			dfs.addRawBytes(0x7a73, 0x2, "UnknownD2_Sound_7a73");
//			dfs.addRawBytes(0x7a75, 0x6a, "UnknownD2_Sound_7a75");
//			dfs.addRawBytes(0x7adf, 0x4, "UnknownD2_Sound_7adf");
//			dfs.addRawBytes(0x7ae3, 0x2, "UnknownD2_Sound_7ae3");
//			dfs.addRawBytes(0x7ae5, 0x2, "UnknownD2_Sound_7ae5");
//			dfs.addRawBytes(0x7ae7, 0x7e, "UnknownD2_Sound_7ae7");
//			dfs.addRawBytes(0x7b65, 0x6, "UnknownD2_Sound_7b65");
//			dfs.addRawBytes(0x7b6b, 0x4, "UnknownD2_Sound_7b6b");
//			dfs.addRawBytes(0x7b6f, 0x4, "UnknownD2_Sound_7b6f");
//			dfs.addRawBytes(0x7b73, 0xb1, "UnknownD2_Sound_7b73");
//			dfs.addRawBytes(0x7c24, 0x4, "UnknownD2_Sound_7c24");
//			dfs.addRawBytes(0x7c28, 0x2, "UnknownD2_Sound_7c28");
//			dfs.addRawBytes(0x7c2a, 0x2, "UnknownD2_Sound_7c2a");
//			dfs.addRawBytes(0x7c2c, 0xcd, "UnknownD2_Sound_7c2c");
//			dfs.addRawBytes(0x7cf9, 0x6, "UnknownD2_Sound_7cf9");
//			dfs.addRawBytes(0x7cff, 0x12, "UnknownD2_Sound_7cff");
//			dfs.addRawBytes(0x7d11, 0x10, "UnknownD2_Sound_7d11");
//			dfs.addRawBytes(0x7d21, 0x123, "UnknownD2_Sound_7d21");
//			dfs.addRawBytes(0x7e44, 0x4, "UnknownD2_Sound_7e44");
//			dfs.addRawBytes(0x7e48, 0x2, "UnknownD2_Sound_7e48");
//			dfs.addRawBytes(0x7e4a, 0x2, "UnknownD2_Sound_7e4a");
//			dfs.addRawBytes(0x7e4c, 0x45, "UnknownD2_Sound_7e4c");
//			dfs.addRawBytes(0x7e91, 0xc, "UnknownD2_Sound_7e91");
//			dfs.addRawBytes(0x7e9d, 0xc, "UnknownD2_Sound_7e9d");
//			dfs.addRawBytes(0x7ea9, 0xc, "UnknownD2_Sound_7ea9");
//			dfs.addRawBytes(0x7eb5, 0x13b, "UnknownD2_Sound_7eb5");
//		}
//		dfs.addRawBytes(0x657b, 0x4, "Unknown_Sound_657b");
//		dfs.addRawBytes(0x657f, 0x4, "Unknown_Sound_657f");
//		dfs.addRawBytes(0x659b, 0x5, "Unknown_Sound_659b");
//		dfs.addRawBytes(0x65a0, 0x5, "Unknown_Sound_65a0");
//		dfs.addRawBytes(0x65a5, 0x5, "Unknown_Sound_65a5");
//		dfs.addRawBytes(0x65e7, 0x5, "Unknown_Sound_65e7");
//		dfs.addRawBytes(0x65ec, 0x5, "Unknown_Sound_65ec");
//		dfs.addRawBytes(0x6623, 0x5, "Unknown_Sound_6623");
//		dfs.addRawBytes(0x6640, 0x5, "Unknown_Sound_6640");
//		dfs.addRawBytes(0x6645, 0x5, "Unknown_Sound_6645");
//		dfs.addRawBytes(0x664a, 0x5, "Unknown_Sound_664a");
//		dfs.addRawBytes(0x664f, 0x5, "Unknown_Sound_664f");
//		dfs.addRawBytes(0x6695, 0x5, "Unknown_Sound_6695");
//		dfs.addRawBytes(0x669a, 0xb, "Unknown_Sound_669a");
//		dfs.addRawBytes(0x66a5, 0xa, "Unknown_Sound_66a5");
//		dfs.addRawBytes(0x66ec, 0x5, "Unknown_Sound_66ec");
//		dfs.addRawBytes(0x66f1, 0x6, "Unknown_Sound_66f1");
//		dfs.addRawBytes(0x66f7, 0x5, "Unknown_Sound_66f7");
//		dfs.addRawBytes(0x6740, 0x5, "Unknown_Sound_6740");
//		dfs.addRawBytes(0x6745, 0x4, "Unknown_Sound_6745");
//		dfs.addRawBytes(0x6749, 0x4, "Unknown_Sound_6749");
//		dfs.addRawBytes(0x674d, 0x4, "Unknown_Sound_674d");
//		dfs.addRawBytes(0x6751, 0x4, "Unknown_Sound_6751");
//		dfs.addRawBytes(0x6755, 0x24, "Unknown_Sound_6755");
//		dfs.addRawBytes(0x6779, 0x24, "Unknown_Sound_6779");
//		dfs.addRawBytes(0x67fb, 0x5, "Unknown_Sound_67fb");
//		dfs.addRawBytes(0x6857, 0x3, "Unknown_Sound_6857");
//		dfs.addRawBytes(0x685a, 0x2, "Unknown_Sound_685a");
//		dfs.addRawBytes(0x685c, 0x3, "Unknown_Sound_685c");
//		dfs.addRawBytes(0x685f, 0x2, "Unknown_Sound_685f");
//		dfs.addRawBytes(0x6861, 0x3, "Unknown_Sound_6861");
//		dfs.addRawBytes(0x6864, 0x2, "Unknown_Sound_6864");
//		dfs.addRawBytes(0x6866, 0x3, "Unknown_Sound_6866");
//		dfs.addRawBytes(0x6869, 0x2, "Unknown_Sound_6869");
//		dfs.addByteArray(0x6abe, 17, "Unknown_Sound_6abe",
//				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
//		dfs.addFunction(0x6d9d, ".unused_6d9d");
//		dfs.addFunction(0x6da1, ".unused_6da1");
//
//		dfs.addRawBytes(0x6dcb, 0x37, "Unknown_Sound_6dcb");
//		dfs.addRawBytes(0x6e02, 0x92, "Unknown_Sound_6e02");
//		dfs.addRawBytes(0x6e94, 0x15, "Unknown_Sound_6e94");
//		dfs.addRawBytes(0x6ea9, 0x20, "Unknown_Sound_6ea9");
//		dfs.addRawBytes(0x6ec9, 0x10, "Unknown_Sound_6ec9");
//		dfs.addRawBytes(0x6ed9, 0x10, "Unknown_Sound_6ed9");
//		dfs.addRawBytes(0x6ee9, 0x10, "Unknown_Sound_6ee9");
//
//
		dfs.addFunction(0x2ac7, "DMARoutine");
	}
}
