package mrwint.gbtasgen.segment.pokemon.gen1;

import static mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants.FRAME_CYCLES;

import java.util.ArrayList;
import java.util.Arrays;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.movie.BizhawkMovie;
import mrwint.gbtasgen.movie.GbMovie;
import mrwint.gbtasgen.movie.LsmvMovie;
import mrwint.gbtasgen.rom.pokemon.gen1.YellowRomInfo;
import mrwint.gbtasgen.segment.SingleGbSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.ace.Intro;
import mrwint.gbtasgen.segment.pokemon.gen1.ace.OakSpeech;
import mrwint.gbtasgen.segment.pokemon.gen1.ace.SaveCorrupt;
import mrwint.gbtasgen.segment.pokemon.gen1.ace.AceYellow;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.SingleGbState;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.tools.playback.loganalyzer.Calibration;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackAddresses;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Record;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Wait;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteInitialOperations;
import mrwint.gbtasgen.util.SingleGbRunner;

public class ACE extends SingleGbSegment {

	@Override
	protected void execute() {
//		seq(new Intro());
//		save("intro");
//
//		load("intro");
//		seq(new OakSpeech());
//		save("oakSpeech");
//
//		load("oakSpeech");
//		seq(new SaveCorrupt());
//		save("saveCorrupt");
//
//		load("saveCorrupt");
//		seq(new AceYellow());
//		save("aceYellow");
	}

	public static void main(String[] args) {
    SingleGbRunner.run(new YellowRomInfo(), new ACE(), true);

//    LsmvMovie.exportMovie(getAceMovie(calibrateAceInputCycleOffsetOperations()), "calibrationTest");
	}
	
	public static GbMovie getAceMovie(ArrayList<PlaybackOperation> operations) {
    // load GbMovie
    StateBuffer buffer = StateBuffer.load("aceYellow", new YellowRomInfo().fileNameSuffix);
    GbMovie movie = GbMovie.fromStateBuffer(buffer, new YellowRomInfo());
    
    // append second stage data
    movie.appendInputs(FIRST_STAGE_BUFFER);
    movie.appendInputs(toNibbleInputs(SECOND_STAGE_DATA));
    movie.appendInputs(PlaybackOperation.toInputs(operations, ACE_INPUT_CYCLE_OFFSET));
    
    return movie;
	}
	
  // Initial WriteInitialOperations cycleCounter:  165316384
  // First frame end cycleCounter: 165421248 -> Offset: FRAME_CYCLES - (165421248 - 165316384) = FRAME_CYCLES - 104864 = 35584
  /** Initial cycle offset when using playback.gbc */
  public static final int ACE_INPUT_CYCLE_OFFSET = FRAME_CYCLES * 28 + 35584;
  /**
   * Returns a set of playback operations to check the calibration of the ACE_INPUT_CYCLE_OFFSET value.
   * After the first Wait, the first input of the Record should fall into the new frame.
   * After the second Wait, the first input of the Record should fall into the previous frame.
   * I.e., the new input frames should be on the 17th and 34th input.
   */
  public static ArrayList<PlaybackOperation> calibrateAceInputCycleOffsetOperations() {
    WriteInitialOperations writeInitialOperations = new WriteInitialOperations();
    Wait wait = new Wait(
        FRAME_CYCLES - (ACE_INPUT_CYCLE_OFFSET % FRAME_CYCLES) // Cycles left to next input frame boundary
        - writeInitialOperations.getCycleCount() // Time taken up by initial operation writing
        - Record.getCycleCount(4) // Time taken up by initial Record call
        - Record.getFirstInputCycles()); // Time until first input in second Record call
    Wait wait2 = new Wait(FRAME_CYCLES - Record.getCycleCount(4) - 4);
    Record record = Record.forStackFrames(Arrays.asList(wait.getJumpAddress(), PlaybackAddresses.RECORD));
    Record record2 = Record.forStackFrames(Arrays.asList(wait2.getJumpAddress(), PlaybackAddresses.RECORD));
    
    ArrayList<PlaybackOperation> playback = new ArrayList<>();
    playback.add(writeInitialOperations);
    playback.add(record);
    playback.add(wait);
    playback.add(record2);
    playback.add(wait2);
    playback.add(Record.forStackFrames(Arrays.asList(wait2.getJumpAddress())));
    playback.add(wait2);
    return playback;
  }

  private static int[] toNibbleInputs(int[] data) {
	  int[] inputs = new int[data.length * 2];
	  for (int i = 0; i < data.length; i++) {
      inputs[2*i] = (data[i] >> 4) ^ 0x3;
      inputs[2*i + 1] = (data[i] & 0xf) ^ 0x3;
	  }
    return inputs;
  }
	
	private static final int[] FIRST_STAGE_BUFFER = {
	  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	};

  private static final int[] SECOND_STAGE_DATA = {
	  0xcd, 0x96, 0x1e,
	  0xf3,
	  0xe2,
	  0xe0, 0x40,
	  0x3c,
	  0xe0, 0x4d,
	  0x10, 0x00,
	  0x21, 0x00, 0xc0,
	  0xf2,
	  0xcb, 0x37,
	  0x57,
	  0xf2,
	  0xaa,
	  0x22,
	  0xab,
	  0x20, 0xf6,
	  0xc3, 0x00, 0xc0,
	  0x5d,
	};
}
