package mrwint.gbtasgen.tools.deasm.specialCallHandler;

import deasm.CPUState;
import deasm.DFS;

public class SpecialCallHandler {
	public DFS dfs;
	public void init(DFS dfs) {
		this.dfs = dfs;
	}
	
	public boolean handleAfterCall(int currentAddress, int callAddress, CPUState s) {
		return false;
	}

	public void handleBeforeOp(int currentAddress, CPUState s) {
	}

	public void handleDFSInit() {
	}

	public boolean vetoContinueAfterCall(int currentAddress, int jumpAddress) {
		return false;
	}
}
