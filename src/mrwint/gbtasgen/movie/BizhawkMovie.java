package mrwint.gbtasgen.movie;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.state.DualGbState;
import mrwint.gbtasgen.state.SingleGbState;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.State.InputNode;

public class BizhawkMovie {
  public static void exportMovie(SingleGbState state, String filename) {
    try {
      String path = "movies/" + filename + state.gb.rom.fileNameSuffix + ".bkm";

      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)));

      writeHeader(state, out);
      writeInputs(state, out);

      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void exportMovie(DualGbState state, String filename) {
    try {
      String path = "movies/" + filename + "Dual" + state.gbL.rom.fileNameSuffix + state.gbR.rom.fileNameSuffix + ".bkm";

      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)));

      writeHeader(state, out);
      writeInputs(state, out);

      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void writeInputs(SingleGbState state, PrintWriter out) throws IOException {
    State s = state.stateBuffer.getAnyMinState();

    List<Integer> inputs = new ArrayList<Integer>();
    InputNode curNode = s.inputs;
    while(curNode != null) {
      inputs.add(curNode.input);
      curNode = curNode.prev;
    }
    if(inputs.size() != s.stepCount)
      System.err.println("WARNING: input len ("+inputs.size()+") does not match step count ("+s.stepCount+")");
    for(int i = inputs.size()-1; i >= 0 ; i--) {
      writeInput(inputs.get(i).intValue(),out);
      out.println("|");
    }
  }

  private static void writeInputs(DualGbState state, PrintWriter out) throws IOException {
    State sL = state.stateBufferL.getAnyMinState();
    State sR = state.stateBufferR.getAnyMinState();

    ArrayList<Integer> inputsL = new ArrayList<Integer>();
    InputNode curNode = sL.inputs;
    while(curNode != null) {
      inputsL.add(curNode.input);
      curNode = curNode.prev;
    }
    ArrayList<Integer> inputsR = new ArrayList<Integer>();
    curNode = sR.inputs;
    while(curNode != null) {
      inputsR.add(curNode.input);
      curNode = curNode.prev;
    }
    Collections.reverse(inputsL);
    Collections.reverse(inputsR);

    if(inputsL.size() != sL.stepCount)
      System.err.println("WARNING: left input len ("+inputsL.size()+") does not match step count ("+sL.stepCount+")");
    if(inputsR.size() != sR.stepCount)
      System.err.println("WARNING: right input len ("+inputsR.size()+") does not match step count ("+sR.stepCount+")");

    for(int i = 0; i < Math.max(inputsL.size(), inputsR.size()) ; i++) {
      writeInput(i < inputsL.size() ? inputsL.get(i).intValue() : 0, out);
      writeInput(i < inputsR.size() ? inputsR.get(i).intValue() : 0, out);
      out.println("|");
    }
  }

	private static void writeInput(int input, PrintWriter out) throws IOException {
		out.print("|");
		out.print((input & Move.RESET) != 0 ? "P" : ".");
		out.print("|");
		out.print((input & Move.UP) != 0 ? "U" : ".");
		out.print((input & Move.DOWN) != 0 ? "D" : ".");
		out.print((input & Move.LEFT) != 0 ? "L" : ".");
		out.print((input & Move.RIGHT) != 0 ? "R" : ".");
		out.print((input & Move.SELECT) != 0 ? "s" : ".");
		out.print((input & Move.START) != 0 ? "S" : ".");
		out.print((input & Move.B) != 0 ? "B" : ".");
		out.print((input & Move.A) != 0 ? "A" : ".");
	}

  private static void writeHeader(SingleGbState state, PrintWriter out) throws IOException {
    out.println("emuVersion Version " + (state.gb.equalLengthFrames ? "1.7.0" : "1.5.3"));
    out.println("MovieVersion BizHawk v0.0.1");
    out.println("Platform "+state.gb.rom.platform);
    out.println("GameName "+state.gb.rom.romName);
    out.println("Author MrWint");
    out.println("rerecordCount "+state.gb.rerecordCount);
    out.println("GUID 552d8b87-2740-497f-b946-b49f2623599d");
    out.println("SHA1 "+state.gb.rom.romSHA1);
    out.println("Force_DMG_Mode False");
    out.println("GBA_In_CGB False");
  }

  private static void writeHeader(DualGbState state, PrintWriter out) throws IOException {
    out.println("emuVersion Version 1.7.0");
    out.println("MovieVersion BizHawk v0.0.1");
    out.println("Platform DGB");
    out.println("GameName " + "Dual" + state.gbL.rom.fileNameSuffix + state.gbR.rom.fileNameSuffix);
    out.println("Author MrWint");
    out.println("rerecordCount " + (state.gbL.rerecordCount + state.gbR.rerecordCount));
    out.println("SyncSettings {\"o\":{\"$type\":\"BizHawk.Emulation.Cores.Nintendo.Gameboy.GambatteLink+GambatteLinkSyncSettings, BizHawk.Emulation.Cores\",\"L\":{\"ForceDMG\":false,\"GBACGB\":false,\"MulticartCompat\":false,\"RealTimeRTC\":false,\"RTCInitialTime\":0},\"R\":{\"ForceDMG\":false,\"GBACGB\":false,\"MulticartCompat\":false,\"RealTimeRTC\":false,\"RTCInitialTime\":0}}}");
    out.println("SHA1 A435C4F79D268FD1F751411507A2D3FA1077C35D");
    out.println("Core DualGambatte");
  }
}
