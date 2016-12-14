package mrwint.gbtasgen.tools.deasm.specialCallHandler;

import mrwint.gbtasgen.tools.deasm.CPUState;
import mrwint.gbtasgen.tools.deasm.OpCode;

public class TetrisDxSpecialCallHandler extends SpecialCallHandler {

	@Override
	public boolean vetoContinueAfterCall(int currentAddress, int jumpAddress) {
    if(jumpAddress == 0x28) // messes with stack, reads return address as jumptable
      return true;
		return false;
	}
	
	@Override
	public boolean handleAfterCall(int currentAddress, int callAddress, CPUState s, boolean reachable) {
	  if (callAddress == 0x3f4e) {
	    s.setRegister(CPUState.A, 0xc);
	    s.loadedBank = 0xc;
	    return true;
	  }
	  return false;
	}
}
