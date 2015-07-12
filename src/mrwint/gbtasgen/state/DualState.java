package mrwint.gbtasgen.state;

import java.nio.ByteBuffer;
import java.util.Map;

import mrwint.gbtasgen.state.State.InputNode;

public class DualState {

	// common
	public int stepCountL, stepCountR;
	public int delayStepCountL, delayStepCountR;
	public int ocdCountL, ocdCountR;
	public int lastMoveL, lastMoveR;
	public InputNode inputsL, inputsR;
	public Map<String,Object> attributesL, attributesR;
  public boolean onFrameBoundariesL, onFrameBoundariesR;
  public long rerecordCountL, rerecordCountR;
	// state only
	public ByteBuffer bb;

	DualState(ByteBuffer bb,
	    int stepCountL, int stepCountR,
	    int delayStepCountL, int delayStepCountR,
	    Map<String, Object> attributesL, Map<String, Object> attributesR,
	    int ocdCountL, int ocdCountR,
	    int lastMoveL, int lastMoveR,
	    InputNode inputsL, InputNode inputsR,
	    boolean onFrameBoundariesL, boolean onFrameBoundariesR,
	    long rerecordCountL, long rerecordCountR) {
		this.bb = bb;
    this.stepCountL = stepCountL;
    this.stepCountR = stepCountR;
    this.delayStepCountL = delayStepCountL;
    this.delayStepCountR = delayStepCountR;
    this.ocdCountL = ocdCountL;
    this.ocdCountR = ocdCountR;
    this.lastMoveL = lastMoveL;
    this.lastMoveR = lastMoveR;
    this.inputsL = inputsL;
    this.inputsR = inputsR;
    this.attributesL = attributesL;
    this.attributesR = attributesR;
    this.onFrameBoundariesL = onFrameBoundariesL;
    this.onFrameBoundariesR = onFrameBoundariesR;
    this.rerecordCountL = rerecordCountL;
    this.rerecordCountR = rerecordCountR;
	}
}
