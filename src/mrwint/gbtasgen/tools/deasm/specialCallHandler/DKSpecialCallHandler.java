package mrwint.gbtasgen.tools.deasm.specialCallHandler;

import mrwint.gbtasgen.tools.deasm.CPUState;

public class DKSpecialCallHandler extends SpecialCallHandler {

  @Override
  public boolean vetoContinueAfterCall(int currentAddress, int jumpAddress) {
    if(jumpAddress == 0x8) // messes with stack, reads return address as jumptable
      return true;
    if(jumpAddress == 0xfc2) // messes with stack, reads return address as jumptable
      return true;
    return false;
  }

  @Override
  public void handleBeforeOp(int currentAddress, CPUState s) {
  }

  @Override
  public void handleDFSInit() {
    // rst $8
    dfs.addJumpTable(0x1a7, 32);
    dfs.addJumpTable(0x2ec, 60);
    dfs.addJumpTable(0x6bd, 46);
    dfs.addJumpTable(0xd75, 23);
    dfs.addJumpTable(0x181c, 44);
    dfs.addJumpTable(0x2668, 27, 1);
    dfs.addJumpTable(0x2894, 4, 1);
    dfs.addJumpTable(0x28f6, 8, 0x17);

    dfs.addFunction(0x2a58, ".asm_2a58");

    dfs.addBankJumpPointerTable(0x2b2e, 66);

    dfs.addJumpTable(0x33fa, 11, 1);
    dfs.addJumpTable(0x3458, 6, 1);
    dfs.addJumpTable(0x34c6, 8, 1);
    dfs.addJumpTable(0x3569, 9, 1);
    dfs.addJumpTable(0x3581, 37, 1);
    dfs.addJumpTable(0x363e, 3, 1);
    dfs.addJumpTable(0x364a, 8, 0xc);
    dfs.addJumpTable(0x3660, 5, 0xc);

    dfs.addJumpTable(0x1c1d7, 6);
    dfs.addJumpTable(0x1c4fb, 5);
    dfs.addJumpTable(0x1c524, 13);
    dfs.addJumpTable(0x1c81c, 13);
    dfs.addJumpTable(0x1ced3, 2);
    dfs.addJumpTable(0x1d21b, 5);
    dfs.addJumpTable(0x1d67e, 9);
    dfs.addJumpTable(0x1d8ef, 3);
    dfs.addJumpTable(0x1da1a, 3);
    dfs.addJumpTable(0x1df37, 9);
    dfs.addJumpTable(0x1e460, 4);
    dfs.addJumpTable(0x1e4ee, 3);
    dfs.addJumpTable(0x1e585, 2);
    dfs.addJumpTable(0x1e7e3, 8);
    dfs.addJumpTable(0x1e8c8, 3);
    dfs.addJumpTable(0x1ef73, 2);
    dfs.addJumpTable(0x1eff0, 13);
    dfs.addJumpTable(0x1f36f, 1);
    dfs.addJumpTable(0x1f5d1, 19);

    dfs.addJumpTable(0x21f6b, 8);
    dfs.addJumpTable(0x2211f, 7);
    dfs.addJumpTable(0x22283, 7);
    dfs.addJumpTable(0x22467, 6);
    dfs.addJumpTable(0x227cc, 5);
    dfs.addJumpTable(0x229be, 2);
    dfs.addJumpTable(0x22a34, 4);
    dfs.addJumpTable(0x22aca, 6);
    dfs.addJumpTable(0x22daa, 6);
    dfs.addJumpTable(0x22f65, 11);

    dfs.addFunction(0x26660, "Func26660");
    dfs.addJumpTable(0x2664a, 11);
    dfs.addJumpTable(0x26d33, 11);
    dfs.addJumpTable(0x2880a, 16);
    dfs.addJumpTable(0x28e56, 8);
    dfs.addJumpTable(0x291a1, 2);
    dfs.addJumpTable(0x2924b, 5);
    dfs.addJumpTable(0x2934b, 8);
    dfs.addJumpTable(0x29421, 5);
    dfs.addJumpTable(0x296b1, 6);
    dfs.addJumpTable(0x296fb, 3);
    dfs.addJumpTable(0x29894, 4);
    dfs.addJumpTable(0x29918, 6);
    dfs.addJumpTable(0x29cf8, 8);
    dfs.addJumpTable(0x29eca, 15);
    dfs.addJumpTable(0x2a40e, 6);
    dfs.addJumpTable(0x2a59c, 7);
    dfs.addJumpTable(0x2a7a6, 7);
    dfs.addJumpTable(0x2ad94, 7);
    dfs.addJumpTable(0x2b06d, 7);
    dfs.addJumpTable(0x2bd08, 4);
    dfs.addJumpTable(0x2be36, 10);
    dfs.addJumpTable(0x2bf9c, 3);

    dfs.addJumpTable(0x4013e, 3);
    dfs.addJumpTable(0x40354, 4);
    dfs.addJumpTable(0x4044a, 11);
    dfs.addJumpTable(0x406b7, 6);
    dfs.addJumpTable(0x40990, 2);
    dfs.addJumpTable(0x40e8b, 12);
    dfs.addJumpTable(0x412ad, 3);
    dfs.addJumpTable(0x413d2, 17);
    dfs.addJumpTable(0x419b8, 16);
    dfs.addJumpTable(0x419df, 7);
    dfs.addJumpTable(0x41a16, 5);
    dfs.addJumpTable(0x41b07, 3);
    dfs.addJumpTable(0x41bcd, 8);
    dfs.addJumpTable(0x41d19, 3);
    dfs.addJumpTable(0x41dcb, 5);
    dfs.addJumpTable(0x41ede, 4);
    dfs.addJumpTable(0x42163, 2);
    dfs.addJumpTable(0x4217c, 6);
    dfs.addJumpTable(0x42468, 8);
    dfs.addJumpTable(0x42b00, 6);
    dfs.addJumpTable(0x42d2e, 40);

    dfs.addJumpTable(0x7c053, 3);
    dfs.addJumpTable(0x7c0f0, 3);
    dfs.addJumpTable(0x7c162, 5);
    dfs.addJumpTable(0x7c1fb, 5);
    dfs.addJumpTable(0x7c499, 3);
    dfs.addJumpTable(0x7c576, 3);
    dfs.addJumpTable(0x7c6c0, 4);
    dfs.addJumpTable(0x7c941, 2);
    dfs.addJumpTable(0x7c9bf, 2);
    dfs.addJumpTable(0x7caa4, 3);
    dfs.addJumpTable(0x7cce3, 3);
    dfs.addJumpTable(0x7cdb4, 3);
    dfs.addJumpTable(0x7ce3f, 4);
    dfs.addJumpTable(0x7d06b, 7);
    dfs.addJumpTable(0x7d20f, 4);
    dfs.addJumpTable(0x7d3d9, 3);
    dfs.addJumpTable(0x7e4a1, 3);
    dfs.addJumpTable(0x7f9a8, 6);

    dfs.addJumpTable(0x5f6f3, 74);

    dfs.addFunction(0xfb8, "DMARoutine");
  }

  @Override
  public boolean handleAfterCall(int currentAddress, int callAddress, CPUState s, boolean reachable) {
    if(callAddress == 0x10) { // rst $10: BankSwitch
      if(s.rMask[CPUState.A] == 0xFF)
        s.loadedBank = s.r[CPUState.A] & 0x7F;
      else
        s.loadedBank = -1;
      return true;
    }
    if (callAddress >= 0x4000 || callAddress == 0x0dc3) {
      s.invalidateRegisters();
      return true;
    }
    return false;
  }
}
