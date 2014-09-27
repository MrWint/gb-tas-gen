package mrwint.gbtasgen.tools.deasm.specialCallHandler;

import mrwint.gbtasgen.tools.deasm.CPUState;
import mrwint.gbtasgen.tools.deasm.DFS;
import mrwint.gbtasgen.tools.deasm.ROM;

public class TetrisSpecialCallHandler extends SpecialCallHandler {

	@Override
	public boolean vetoContinueAfterCall(int currentAddress, int jumpAddress) {
		if(jumpAddress == 0x28) // messes with stack, reads return address as jumptable
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
		dfs.addJumpTable(0x6e, 5);
		dfs.addJumpTable(0x2fb, 55);

		dfs.addJumpTable(0x6480, 8);
		dfs.addJumpTable(0x6490, 8);
		dfs.addJumpTable(0x64a0, 4);
		dfs.addJumpTable(0x64a8, 4);

		dfs.addFunction(0x0, "Unused_rst00");
		dfs.addRawBytes(0x3, 5);
		dfs.addFunction(0x8, "Unused_rst08");
		dfs.addRawBytes(0xb, 29);
		dfs.addRawBytes(0x34, 12);
		dfs.addRawBytes(0x43, 5);
		dfs.addRawBytes(0x4b, 5);
		dfs.addRawBytes(0x53, 5);
		dfs.addFunction(0xd0, ".unused_00d0");
		dfs.addRawBytes(0xda, 38);
		dfs.addIgnore(0x104, 0x4c);
		dfs.addSection(0x150, "Start");
		dfs.addFunction(0x153, ".unused_0153");
		dfs.addFunction(0x474, ".unused_0474");
		dfs.addFunction(0x57d, ".unused_057d");
		dfs.addFunction(0x582, null);
		dfs.addByteArray(0x705, 8, ".unknown_2P_0705",
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x7f6, 6, "HighCursorPosP1_2P", ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x802, 6, "HighCursorPosP2_2P", ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x8c4, 4, ".unknown_2P_08c4",
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x8d4, 4, ".unknown_2P_08d4",
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0xf3c, 9, ".unknown_2P_0f3c",
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addFunction(0x10a8, ".unused_10a8");
		dfs.addByteArray(0x10ed, 6, "Unknown_2P_10ed", ROM.FORMAT_HEX);
		dfs.addByteArray(0x10f3, 11, "Unknown_2P_10f3", ROM.FORMAT_HEX);
		dfs.addByteArray(0x10fe, 11, "Unknown_2P_10fe", ROM.FORMAT_HEX);
		dfs.addByteArray(0x1109, 9, "Unknown_2P_1109", ROM.FORMAT_HEX);
		dfs.addByteArray(0x12f5, 16, "Unknown_2c_12f5", ROM.FORMAT_HEX);
		dfs.addByteArray(0x14a8, 4, "Unknown_2b_14a8", ROM.FORMAT_HEX, ROM.FORMAT_HEX);

		dfs.addByteArray(0x141b, 1, "Unknown_26_141b", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x1422, 1, "Unknown_26_1422", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x1429, 1, "Unknown_26_1429", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x1430, 1, "Unknown_26_1430", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x1615, 10, "TypeALevelCursorPos", ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x16d2, 10, "TypeBLevelCursorPos", ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x1741, 6, "TypeBHighCursorPos", ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addRawBytes(0x174d, 1);

		dfs.addByteArray(0x1b06, 21, "LevelFrameDelayList", ROM.FORMAT_DEC);
		dfs.addByteArray(0x1b40, 4, "DemoHighLineData", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x1cdd, 1, "PauseText", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x1e31, 10, ".unknown_22_1e31", ROM.FORMAT_HEX);

		dfs.addFunction(0x2665, ".unused_2665");
		dfs.addByteArray(0x26bf, 1, "Unknown_list_26bf", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x26c7, 1, "Unknown_list_26c7", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x26cf, 2, "Unknown_2a_26cf", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x26db, 1, "Unknown_10_26db", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x26e1, 2, "Unknown_12_26e1", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x26ed, 2, "Unknown_16_26ed", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x26f9, 3, "Unknown_1d_26f9", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x270b, 3, "Unknown_1d_270b", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x271d, 2, "Unknown_1e_271d", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x2729, 2, "Unknown_1e_2729", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x2735, 10, "Unknown_22_2735", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x2771, 3, "Unknown_26_2771", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x2783, 3, "Unknown_2e_2783", ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);

		dfs.addFunction(0x27e1, ".unused_27e1");

		dfs.addByteArray(0x2839, 10, "HitStartToContinueOverlay",
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x2889, 18, "ScoreCountScreen",
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x293d, 1, null, ROM.FORMAT_HEX);
		dfs.addByteArray(0x293e, 7, "GameOverOverlay",
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x2976, 6, "PleaseTryAgainOverlay",
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);

		dfs.addFunction(0x2a10, ".unused_2a10");
		dfs.addFunction(0x2a18, ".unused_2a18");

		dfs.addByteArray(0x510f, 18, "WonDancingScreen",
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
				ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
		dfs.addByteArray(0x51c3, 1, null, ROM.FORMAT_HEX);

		dfs.addByteArray(0x62b0, 0x80, "Demo1Controls", ROM.FORMAT_BUTTONS, ROM.FORMAT_DEC);
		dfs.addByteArray(0x63b0, 0x50, "Demo2Controls", ROM.FORMAT_BUTTONS, ROM.FORMAT_DEC);

		dfs.addFunction(0x2a7f, "DMARoutine");
	}
}
