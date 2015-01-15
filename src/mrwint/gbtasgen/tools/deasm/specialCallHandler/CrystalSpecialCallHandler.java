package mrwint.gbtasgen.tools.deasm.specialCallHandler;

import mrwint.gbtasgen.tools.deasm.CPUState;
import mrwint.gbtasgen.tools.deasm.DFS;
import mrwint.gbtasgen.tools.deasm.OpCode;

public class CrystalSpecialCallHandler extends SpecialCallHandler {
	public DFS dfs;
	@Override
	public void init(DFS dfs) {
		this.dfs = dfs;
	}

	@Override
	@SuppressWarnings("static-access")
	public boolean handleAfterCall(int currentAddress, int callAddress, CPUState s, boolean reachable) {
		if(callAddress == 0x8) { // rst $8: FarCall a:hl
			if((s.rMask[s.A] & s.rMask[s.H] & s.rMask[s.L]) == 0xFF) {
				int jumpAddress;
				if(s.r[s.H] < 0x40)
					jumpAddress = ((s.r[s.H]*0x100) | s.r[s.L]);
				else if(s.r[s.H] < 0x80)
					jumpAddress = (s.r[s.A]-1)*0x4000 + ((s.r[s.H]*0x100) | s.r[s.L]);
				else {
					System.err.println("ERROR: invalid FarCall to "+Integer.toHexString((s.r[s.H]*0x100) | s.r[s.L]));
					return false;
				}

				// set up indirect labels
				if(s.r[s.H] >= 0x40)
					if(s.rLoadedFromAddress[s.A] != -1)
						dfs.rom.payloadAsBank[s.rLoadedFromAddress[s.A]] = jumpAddress;
				if(s.rLoadedFromAddress[s.H] == s.rLoadedFromAddress[s.L] && s.rLoadedFromAddress[s.H] != -1)
					dfs.rom.payloadAsAddress[s.rLoadedFromAddress[s.H]] = jumpAddress;

				dfs.addIndirectJump(currentAddress, jumpAddress, new CPUState(), 3, reachable);
			}
			return false;
		}
		if(callAddress == 0x10) { // rst $10: BankSwitch
			if(s.rMask[s.A] == 0xFF)
				s.loadedBank = s.r[s.A] & 0x7F;
			else
				s.loadedBank = -1;
			return true;
		}

		return false;
	}

	@Override
	public void handleBeforeOp(int currentAddress, OpCode opCode, int opData, CPUState s) {
		if(currentAddress == 0x242)
			s.loadedBank = 0x1;
		if(currentAddress == 0x2dc9)
			s.loadedBank = 0x1;
		if(currentAddress == 0x2e1a)
			s.loadedBank = 0x1;
		if(currentAddress == 0x351b)
			s.loadedBank = 0x9;
		if(currentAddress == 0x3bd5)
			s.loadedBank = 0x3a;
		if(currentAddress == 0x3ba9)
			s.loadedBank = 0x3a;
	}

	@Override
	public void handleDFSInit() {
		// jmp [hl]
		dfs.addJumpTable(0x2a1, 8);
		dfs.addJumpTable(0x626a, 5);
		dfs.addJumpTable(0x62af, 5);
		dfs.addJumpTable(0x842ea, 32);
		dfs.addJumpTable(0x8c126, 4);
		dfs.addJumpTable(0x8d25b, 35);
		dfs.addJumpTable(0x9322d, 3);
		dfs.addJumpTable(0x932bc, 3);
		dfs.addJumpTable(0xe467f, 4);
		dfs.addJumpTable(0xe46fd, 5);
		dfs.addJumpTable(0xe491e, 28);
		dfs.addJumpTable(0xe8136, 8);
		dfs.addJumpTable(0xe8720, 48);
		dfs.addJumpTable(0x11d0c7, 11);
		dfs.addJumpTable(0x17f061, 16);

		dfs.addJumpTable(0x10030, 11);
		dfs.addJumpTable(0x104c3, 11);
		dfs.addJumpTable(0x106d1, 4);
		dfs.addJumpTable(0x107e1, 4);
		dfs.addJumpTable(0x1172e, 8);
		dfs.addJumpTable(0x11977, 2);
		dfs.addJumpTable(0x12017, 2);
		dfs.addJumpTable(0x12644, 7);
		dfs.addJumpTable(0x2519d, 7);
		dfs.addJumpTable(0x29686, 7);
		dfs.addJumpTable(0x40115, 14);
		dfs.addJumpTable(0x40405, 4);
		dfs.addJumpTable(0x40bf0, 3);
		dfs.addJumpTable(0x4e564, 3);
		dfs.addJumpTable(0x84031, 20);
		dfs.addJumpTable(0x8c323, 33);
		dfs.addJumpTable(0x8ca1b, 4);
		dfs.addJumpTable(0x8e854, 7);
		dfs.addJumpTable(0x90f13, 13);
		dfs.addJumpTable(0xb863a, 88);
		dfs.addJumpTable(0xc805a, 54);
		dfs.addJumpTable(0xc8d85, 3);
		dfs.addJumpTable(0xc8ddd, 5);
		dfs.addJumpTable(0xcc2a4, 48);
		dfs.addJumpTable(0xccfce, 80);
		dfs.addJumpTable(0xe23df, 5);
		dfs.addJumpTable(0xe24a1, 4);
		dfs.addJumpTable(0xe25d2, 5);
		dfs.addJumpTable(0xe2699, 4);
		dfs.addJumpTable(0xe42e5, 8);
		dfs.addJumpTable(0x109937, 13);
		dfs.addJumpTable(0x117728, 8);
		dfs.addJumpTable(0x11c2bb, 23);
		dfs.addJumpTable(0x170696, 32);
		dfs.addJumpTable(0x171a45, 12);

		dfs.addJumpTable(0xb8fb8, 4);

		dfs.addJumpTable(0x118704, 32);
		dfs.addJumpTable(0x92853, 19);
		//dfs.addJumpTable(0x117af8, 10);
		//dfs.addJumpTable(0x117af8, 10);

		// rst $28
		dfs.addJumpTable(0x4b45, 26);
		dfs.addJumpTable(0x47e9, 28);
		dfs.addJumpTable(0x5075, 90);
		dfs.addJumpTable(0x81d6, 4);
		dfs.addJumpTable(0xd1e9, 4);
		dfs.addJumpTable(0xd220, 4);
		dfs.addJumpTable(0xd257, 4);
		dfs.addJumpTable(0xd4f2, 11);
		dfs.addJumpTable(0x15a57, 5);
		dfs.addJumpTable(0x15b56, 6);
		dfs.addJumpTable(0x15eee, 7);
		dfs.addJumpTable(0x26c7e, 7);
		dfs.addJumpTable(0x26f5f, 5);
		dfs.addJumpTable(0x50089, 9);
		dfs.addJumpTable(0x8b354, 3);

		dfs.addJumpTable(0x10637, 7);
		dfs.addJumpTable(0x12377, 6);
		dfs.addJumpTable(0x4e00d, 3);
		dfs.addJumpTable(0x4e2b5, 5);
		dfs.addJumpTable(0xd00da, 12);
		dfs.addJumpTable(0xd025d, 2);
		dfs.addJumpTable(0x10044e, 14);
		dfs.addJumpTable(0x100e8c, 11);


		dfs.addJumpTable(0x49d60, 6);
		dfs.addJumpTable(0x89e04, 3);
		dfs.addJumpTable(0x89e18, 3);
		dfs.addJumpTable(0x89e3c, 14);
		dfs.addJumpTable(0x8a671, 4);
		dfs.addJumpTable(0x8a6bc, 2);
		dfs.addJumpTable(0x8a74f, 4);
		dfs.addJumpTable(0x8a9c5, 2);
		dfs.addJumpTable(0x8aa6d, 3);
		dfs.addJumpTable(0x8b4a0, 2);
		dfs.addJumpTable(0x966c3, 4);
		dfs.addJumpTable(0x9679d, 2);
		dfs.addJumpTable(0x96a47, 9);
		dfs.addJumpTable(0x96afd, 8);
		dfs.addJumpTable(0x96c6e, 4);
		dfs.addJumpTable(0x96cb1, 170);
		dfs.addJumpTable(0x17a7b6, 6);

		dfs.addJumpTable(0xc796, 3);
		dfs.addJumpTable(0xc91a, 4);
		dfs.addJumpTable(0xca4c, 3);

		dfs.addJumpTable(0xe4ba, 3);
		dfs.addJumpTable(0xe73c, 179);
		dfs.addJumpTable(0xf0a3, 3);
		dfs.addJumpTable(0x1031f, 7);
		dfs.addJumpTable(0x13354, 4);
		dfs.addJumpTable(0x133d1, 7);
		dfs.addJumpTable(0x4dd2a, 8);
		//dfs.addJumpTable(0xe46f, 10);

		// Func_47a8
		dfs.addJumpTable(0x48af, 2);
		dfs.addJumpTable(0x4987, 3);
		dfs.addJumpTable(0x4990, 3);
		//dfs.addJumpTable(0x4b89, 2);

		// Func_47a8
		dfs.addJumpTable(0x4b89, 2);
		dfs.addJumpTable(0x4bc2, 4);
		dfs.addJumpTable(0x4c1b, 4);
		dfs.addJumpTable(0x4c8c, 7);
		dfs.addJumpTable(0x4d17, 4);
		dfs.addJumpTable(0x4d81, 2);
		dfs.addJumpTable(0x4e0f, 2);
		dfs.addJumpTable(0x4e59, 2);
		dfs.addJumpTable(0x4e86, 4);
		dfs.addJumpTable(0x4f36, 2);
		dfs.addJumpTable(0x4f7d, 3);
		dfs.addJumpTable(0x4f86, 2);

		// Func_ce71e
		dfs.addJumpTable(0xcd071, 2);
		dfs.addJumpTable(0xcd15f, 12);
		dfs.addJumpTable(0xcd5ec, 3);
		dfs.addJumpTable(0xcd71d, 4);
		dfs.addJumpTable(0xcd7a7, 2);
		dfs.addJumpTable(0xcd957, 5);
		dfs.addJumpTable(0xcdcc6, 2);
		dfs.addJumpTable(0xcdd93, 2);
		dfs.addJumpTable(0xcde6e, 2);
		dfs.addJumpTable(0xce0c8, 3);

		dfs.addJumpTable(0xcd0e6, 2);
		dfs.addJumpTable(0xcd12d, 2);
		dfs.addJumpTable(0xcd215, 3);
		dfs.addJumpTable(0xcd287, 5);
		dfs.addJumpTable(0xcd2c1, 2);
		dfs.addJumpTable(0xcd309, 4);
		dfs.addJumpTable(0xcd3b1, 3);
		dfs.addJumpTable(0xcd3f5, 10);
		dfs.addJumpTable(0xcd47b, 9);
		dfs.addJumpTable(0xcd58d, 2);
		dfs.addJumpTable(0xcd66d, 5);
		dfs.addJumpTable(0xcd6e6, 2);
		dfs.addJumpTable(0xcd80f, 4);
		dfs.addJumpTable(0xcd827, 7);
		dfs.addJumpTable(0xcd89d, 7);
		dfs.addJumpTable(0xcd903, 2);
		dfs.addJumpTable(0xcda34, 3);
		dfs.addJumpTable(0xcda50, 4);
		dfs.addJumpTable(0xcdad9, 3);
		dfs.addJumpTable(0xcdb09, 5);
		dfs.addJumpTable(0xcdb83, 14);
		dfs.addJumpTable(0xcdd2d, 2);
		dfs.addJumpTable(0xcddfc, 3);
		dfs.addJumpTable(0xcde8c, 2);
		dfs.addJumpTable(0xcdf5c, 2);
		dfs.addJumpTable(0xce00e, 3);
		dfs.addJumpTable(0xce066, 4);
		dfs.addJumpTable(0xce111, 2);
		dfs.addJumpTable(0xce1ea, 2);
		dfs.addJumpTable(0xce229, 2);
		dfs.addJumpTable(0xce258, 4);
		dfs.addJumpTable(0xce300, 3);
		dfs.addJumpTable(0xce362, 2);
		dfs.addJumpTable(0xce38c, 3);
		dfs.addJumpTable(0xce402, 2);
		dfs.addJumpTable(0xce43d, 3);
		dfs.addJumpTable(0xce49f, 2);
		dfs.addJumpTable(0xce55e, 3);
		dfs.addJumpTable(0xce596, 2);
		dfs.addJumpTable(0xce5f1, 4);
		dfs.addJumpTable(0xce632, 4);

		// Func_c80d7
		dfs.addJumpTable(0xc81b6, 5);
		dfs.addJumpTable(0xc8217, 6);
		dfs.addJumpTable(0xc8284, 6);
		dfs.addJumpTable(0xc83f0, 5);
		dfs.addJumpTable(0xc8808, 4);
		dfs.addJumpTable(0xc892d, 3);
		dfs.addJumpTable(0xc82f8, 5);
		dfs.addJumpTable(0xc8548, 3);
		dfs.addJumpTable(0xc859c, 3);
		dfs.addJumpTable(0xc860a, 3);
		dfs.addJumpTable(0xc8642, 3);
		dfs.addJumpTable(0xc8665, 3);
		dfs.addJumpTable(0xc868c, 6);
		dfs.addJumpTable(0xc870c, 3);
		dfs.addJumpTable(0xc8764, 3);
		dfs.addJumpTable(0xc87aa, 4);
		dfs.addJumpTable(0xc883a, 4);
		dfs.addJumpTable(0xc88ea, 4);
		dfs.addJumpTable(0xc891c, 5);
		dfs.addJumpTable(0xc8967, 3);
		dfs.addJumpTable(0xc89b8, 3);
		dfs.addJumpTable(0xc89f1, 3);
		dfs.addJumpTable(0xc8a3d, 6);
		dfs.addJumpTable(0xc8acf, 2);
		dfs.addJumpTable(0xc8b08, 3);
		dfs.addJumpTable(0xc8c64, 2);
		dfs.addJumpTable(0xc8ca5, 3);

		// jp [hl] continuations
		dfs.dfsStack.add(new DFS.DFSStackElem(0x4932f, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x9168e, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x8ea8c, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x5042d, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x50f12, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0xf3df, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x172eb9, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x118284, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x365d7, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x178000, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x1060e5, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x129f4, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x129d5, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x24706, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0xe00ee, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0xe1e5b, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x926c7, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x93188, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x44607, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x445f4, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x26806, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0xedfa, new CPUState(), true));
		dfs.dfsStack.add(new DFS.DFSStackElem(0x114243, new CPUState(), true));
	}

	@Override
	public boolean vetoContinueAfterCall(int currentAddress, int jumpAddress) {
		if(jumpAddress == 0x4793) // messes with stack, reads return address as jump table
			return true;
		if(jumpAddress == 0x47a8) // messes with stack, reads return address as jump table
			return true;
		if(jumpAddress == 0xc80d7) // messes with stack, reads return address as jump table
			return true;
		if(jumpAddress == 0xce71e) // messes with stack, reads return address as jump table
			return true;
		return false;
	}
}
