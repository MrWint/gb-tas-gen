package mrwint.gbtasgen.tools.deasm.specialCallHandler;

import mrwint.gbtasgen.tools.deasm.CPUState;
import mrwint.gbtasgen.tools.deasm.DFS;

public class SpecialCallHandler {
	public DFS dfs;
	public void init(DFS dfs) {
		this.dfs = dfs;
	}

	public boolean handleAfterCall(int currentAddress, int callAddress, CPUState s, boolean reachable) {
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
