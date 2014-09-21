package mrwint.gbtasgen.tools.deasm.specialCallHandler;

import mrwint.gbtasgen.tools.deasm.CPUState;
import mrwint.gbtasgen.tools.deasm.DFS;

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

		dfs.addFunction(0x2a7f, "DMARoutine");
	}
}
