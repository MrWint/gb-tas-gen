package mrwint.gbtasgen.movie;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.state.SingleGbState;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.State.InputNode;
import mrwint.gbtasgen.state.StateBuffer;

public class GbMovie {
  RomInfo rom;
  long rerecords = 0;
  int[] inputs = new int[0];
  
  public GbMovie(RomInfo rom) {
    this.rom = rom;
  }
  
  public static GbMovie fromSingleGbState(SingleGbState state) {
    GbMovie movie = fromStateBuffer(state.stateBuffer, state.gb.rom);
    movie.rerecords = state.gb.rerecordCount;
    
    return movie;
  }
  
  public static GbMovie fromStateBuffer(StateBuffer stateBuffer, RomInfo rom) {
    GbMovie movie = new GbMovie(rom);

    State s = stateBuffer.getAnyMinState();

    List<Integer> inputs = new ArrayList<Integer>();
    InputNode curNode = s.inputs;
    while(curNode != null) {
      inputs.add(curNode.input);
      curNode = curNode.prev;
    }
    if(inputs.size() != s.stepCount)
      System.err.println("WARNING: input len ("+inputs.size()+") does not match step count ("+s.stepCount+")");
    movie.inputs = new int[s.stepCount];
    for (int i = 0; i < s.stepCount; i++)
      movie.inputs[s.stepCount - i - 1] = inputs.get(i) | Move.FRAME_START;
    
    return movie;
  }
  
  public GbMovie appendInputs(int[] inputs) {
    int[] newInputs = new int[inputs.length + this.inputs.length];
    System.arraycopy(this.inputs, 0, newInputs, 0, this.inputs.length);
    System.arraycopy(inputs, 0, newInputs, this.inputs.length, inputs.length);
    this.inputs = newInputs;
    return this;
  }
}
