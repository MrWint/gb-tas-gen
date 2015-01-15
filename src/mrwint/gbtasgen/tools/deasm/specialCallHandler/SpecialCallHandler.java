package mrwint.gbtasgen.tools.deasm.specialCallHandler;

import mrwint.gbtasgen.tools.deasm.CPUState;
import mrwint.gbtasgen.tools.deasm.DFS;
import mrwint.gbtasgen.tools.deasm.OpCode;

public class SpecialCallHandler {
	public DFS dfs;
	public void init(DFS dfs) {
		this.dfs = dfs;
	}

	public boolean handleAfterCall(int currentAddress, int callAddress, CPUState s, boolean reachable) {
		return false;
	}

	public void handleBeforeOp(int currentAddress, OpCode opCode, int opData, CPUState s) {
	}

	public void handleDFSInit() {
	}

	public boolean vetoContinueAfterCall(int currentAddress, int jumpAddress) {
		return false;
	}
}
