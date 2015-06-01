package mrwint.gbtasgen.segment;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.state.DualGbHelper;
import mrwint.gbtasgen.state.DualGbState;
import mrwint.gbtasgen.state.StateBuffer;

public abstract class DualGbSegment {

	private DualGbState state;

	public DualGbState execute(DualGbState state) {
	  this.state = state;
	  execute();
	  return this.state;
	}

	protected abstract void execute();

  public void seqL(Segment s) {
    curGb = state.gbL;
    state = new DualGbState(state.gbL, state.gbR, s.execute(state.stateBufferL), state.stateBufferR);
    curGb = null;
  }

  public void seqR(Segment s) {
    curGb = state.gbR;
    state = new DualGbState(state.gbL, state.gbR, state.stateBufferL, s.execute(state.stateBufferR));
    curGb = null;
  }

  public void seqDual(int[] movesL, int[] movesR) {
    state.gbL.restore(state.stateBufferL.getAnyMinState());
    state.gbR.restore(state.stateBufferR.getAnyMinState());

    DualGbHelper.executeDualGb(state.gbL, state.gbR, movesL, movesR);

    StateBuffer sbL = new StateBuffer();
    sbL.addState(state.gbL.createState(true));
    StateBuffer sbR = new StateBuffer();
    sbR.addState(state.gbR.createState(true));
    state = new DualGbState(state.gbL, state.gbR, sbL, sbR);
  }

  public void load(String name) {
    state = DualGbState.load(state.gbL, state.gbR, name);
  }
  public void save(String name) {
    state.save(name);
  }
  public void loadL(String name) {
    state = new DualGbState(state.gbL, state.gbR, StateBuffer.load(name, "Dual" + state.gbL.rom.fileNameSuffix), state.stateBufferR);
  }
  public void saveL(String name) {
    state.stateBufferL.save(name, "Dual" + state.gbL.rom.fileNameSuffix);
  }
  public void loadR(String name) {
    state = new DualGbState(state.gbL, state.gbR, state.stateBufferL, StateBuffer.load(name, "Dual" + state.gbR.rom.fileNameSuffix));
  }
  public void saveR(String name) {
    state.stateBufferR.save(name, "Dual" + state.gbR.rom.fileNameSuffix);
  }
}
