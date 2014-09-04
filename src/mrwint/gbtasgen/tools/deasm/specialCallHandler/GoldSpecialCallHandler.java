package mrwint.gbtasgen.tools.deasm.specialCallHandler;

import deasm.CPUState;
import deasm.DFS;

public class GoldSpecialCallHandler extends SpecialCallHandler {

	@Override
	public boolean vetoContinueAfterCall(int currentAddress, int jumpAddress) {
		if(jumpAddress == 0xc80d5) // messes with stack, reads return address as data
			return true;
		if(jumpAddress == 0xce663) // messes with stack, reads return address as data
			return true;
		if(jumpAddress == 0x476c) // messes with stack, reads return address as jumptable
			return true;
		if(jumpAddress == 0x4781) // messes with stack, reads return address as jumptable
			return true;
		return false;
	}
	
	@Override
	public void handleDFSInit() {
		// jmp [hl]
		dfs.addJumpTable(0x170, 8);
		dfs.addJumpTable(0x6287, 5);
		dfs.addJumpTable(0x641b, 3);
		dfs.addJumpTable(0x11b1a, 8);
		dfs.addJumpTable(0x11d36, 2);
		dfs.addJumpTable(0x23981, 3);
		dfs.addJumpTable(0x294df, 7);
		dfs.addJumpTable(0x842ea, 32);
		dfs.addJumpTable(0x869ce, 13);
		dfs.addJumpTable(0x8c42a, 4);
		dfs.addJumpTable(0x8d473, 42);
		dfs.addJumpTable(0x8e7b4, 4);
		dfs.addJumpTable(0x93782, 3);
		dfs.addJumpTable(0x93811, 3);
		dfs.addJumpTable(0xc805a, 53);
		dfs.addJumpTable(0xcc283, 48);
		dfs.addJumpTable(0xccf1d, 80);
		dfs.addJumpTable(0xe4714, 8);
		dfs.addJumpTable(0xe4a7c, 6);
		dfs.addJumpTable(0xe4d18, 17);
		dfs.addJumpTable(0xe4e9f, 17);
		//dfs.addJumpTable(0xe538b, 10); dbw
		dfs.addJumpTable(0xe8136, 8);
		dfs.addJumpTable(0xe8720, 48);
		
		// rst $28
		dfs.addJumpTable(0x4b1e, 25);
		dfs.addJumpTable(0x47c2, 28);
		dfs.addJumpTable(0x501d, 89);
		dfs.addJumpTable(0x5acd, 4);
		dfs.addJumpTable(0x87c6, 4);
		dfs.addJumpTable(0xcbbb, 3);
		dfs.addJumpTable(0xcfa8, 5);
		dfs.addJumpTable(0xd1f6, 4);
		dfs.addJumpTable(0xd22d, 4);
		dfs.addJumpTable(0xd264, 4);
		dfs.addJumpTable(0xd4ff, 11);
		dfs.addJumpTable(0xe7c0, 179);
		dfs.addJumpTable(0xf0f9, 3);
		dfs.addJumpTable(0x15d44, 4);
		dfs.addJumpTable(0x15ddf, 6);
		dfs.addJumpTable(0x16143, 7);
		dfs.addJumpTable(0x26e59, 7);
		dfs.addJumpTable(0x27132, 5);
		dfs.addJumpTable(0x50089, 8);
		dfs.addJumpTable(0x9660c, 4);
		dfs.addJumpTable(0x966e2, 2);
		dfs.addJumpTable(0x9697a, 9);
		dfs.addJumpTable(0x96a30, 8);
		dfs.addJumpTable(0x96ba1, 4);
		dfs.addJumpTable(0x96be4, 162);

		// Func_476c
		dfs.addJumpTable(0x4888, 2);
		dfs.addJumpTable(0x4960, 3);
		dfs.addJumpTable(0x4969, 3);

		// Func_4781
		dfs.addJumpTable(0x4b60, 2);
		dfs.addJumpTable(0x4b99, 4);
		dfs.addJumpTable(0x4bf2, 4);
		dfs.addJumpTable(0x4c63, 7);
		dfs.addJumpTable(0x4cee, 4);
		dfs.addJumpTable(0x4d58, 2);
		dfs.addJumpTable(0x4de6, 2);
		dfs.addJumpTable(0x4e30, 2);
		dfs.addJumpTable(0x4e5d, 4);
		dfs.addJumpTable(0x4f0d, 2);
		dfs.addJumpTable(0x4f54, 3);
		
		//dfs.addJumpTable(0xf0f9, 10);
		//dfs.addJumpTable(0xf0f9, 10);
		//dfs.addJumpTable(0xf0f9, 10);

		// jp [hl] continuations
		dfs.dfsStack.add(new DFS.DFSStackElem(0x168, new CPUState()));
		dfs.rom.labelType[0x168] = 1;
		dfs.dfsStack.add(new DFS.DFSStackElem(0x9164d, new CPUState()));
		dfs.rom.labelType[0x9164d] = 1;
	}
	
	@Override
	public void handleBeforeOp(int currentAddress, CPUState s) {
		if(currentAddress == 0x68b)
			s.loadedBank = 0x1;
		if(currentAddress == 0x3daf)
			s.loadedBank = 0x3a;
		if(currentAddress == 0x3760)
			s.loadedBank = 0x9;
		if(currentAddress == 0x2e8f)
			s.loadedBank = 0x1;
		if(currentAddress == 0x2edd)
			s.loadedBank = 0x1;
		return;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public boolean handleAfterCall(int currentAddress, int callAddress, CPUState s) {
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
				
				dfs.addIndirectJump(currentAddress, jumpAddress, new CPUState(), 3);
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
}
