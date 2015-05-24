package mrwint.gbtasgen.segment.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public class EflTextSegmentOld implements Segment {

	private int skipMove;
  private int bufferSize;


	public EflTextSegmentOld() {
		this(Move.A);
	}

	public EflTextSegmentOld(int skipMove) {
		this(skipMove, StateBuffer.MAX_BUFFER_SIZE);
	}

	public EflTextSegmentOld(int skipMove, int bufferSize) {
    EflUtil.assertEfl();

    this.skipMove = skipMove;
		this.bufferSize = bufferSize;
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		StateBuffer curBuffer = new StateBuffer(bufferSize);
		for(State s : in.getStates())
			skipToStart(curBuffer, s);

		StateBuffer goalBuffer = new StateBuffer(bufferSize);

		while(!curBuffer.isEmpty()) {
		  System.out.println("EflTextSegment: curBuffer="+curBuffer.size()+" goalBuffer="+goalBuffer.size());
			StateBuffer nextBuffer = new StateBuffer(bufferSize);

			for(State s : curBuffer.getStates())
				progress(s, nextBuffer, goalBuffer);
			curBuffer = nextBuffer;
		}

		return goalBuffer;
	}

	private void progress(State s, StateBuffer nextBuffer, StateBuffer goalBuffer) {
    curGb.restore(s);
    int textSpeed = curGb.readMemory(curGb.pokemon.optionsAddress) & curGb.pokemon.optionsTextSpeedMask;
    int[] allowedMoves = new int[(textSpeed <= 1 ? 1 : 0) + (textSpeed >= 1 ? 1 : 0)];
    if (textSpeed <= 1)
      allowedMoves[0] = 0;
    if (textSpeed >= 1)
      allowedMoves[textSpeed <= 1 ? 1 : 0] = skipMove;

//    allowedMoves = new int[] { skipMove };

//    System.out.println("progress: textSpeed: " + textSpeed);

    for (int usedMove : allowedMoves) {
//      System.out.println("progress: usedMove: " + usedMove);
	    curGb.restore(s);
      curGb.step(usedMove, curGb.pokemon.printLetterDelayDoneAddress); // press usedMove
//      EflUtil.runInputPastAddressNoLimit(skipMove, curGb.pokemon.printLetterDelayDoneAddress);
		  if (hasMoreText(textSpeed)) {
		    EflUtil.runToNextInputFrameNoLimit(skipMove); // this is guaranteed to be for the next letter delay through hasMoreText()
	      nextBuffer.addState(curGb.createState(true));
		  } else {
        goalBuffer.addState(curGb.createState(true)); // done (no inputs)
		  }
		}
	}

  // checks whether next input frame is still for print letter delay
  private boolean hasMoreText(int textSpeed) {
    State tmp = curGb.newState();
    int initialSteps = curGb.currentStepCount;
    boolean inPrintLetterDelay = true;

//    System.out.println("hasMoreText steps: "+curGb.currentStepCount);
    // advance to next input frame, keeping track of whether you are in or outside the PrintLetterDelay loop
    while (true) {
      int add = EflUtil.runToAddressOrNextInputFrameLimit(skipMove, textSpeed, inPrintLetterDelay ? curGb.pokemon.printLetterDelayDoneAddress : curGb.pokemon.printLetterDelayJoypadAddress);
      if (add == -1 || curGb.currentStepCount - initialSteps > textSpeed) {
//        System.out.println("TextSegment: " + curGb.currentStepCount + " - " + initialSteps + " > " + textSpeed);
        curGb.restore(tmp);
        return false;
      }
      if (add == 0)
        break;
      if (add == curGb.pokemon.printLetterDelayJoypadAddress) {
//        System.out.println("in");
        inPrintLetterDelay = true;
      }
      if (add == curGb.pokemon.printLetterDelayDoneAddress) {
//        System.out.println("out");
        inPrintLetterDelay = false;
      }
    }
//    System.out.println("hasMoreText at input frame steps: " + curGb.currentStepCount + " inPrintLetterDelay: " + inPrintLetterDelay);

    // advance to vblank joypad read, keeping track of whether you are in or outside the PrintLetterDelay loop
    while (true) {
      int add = curGb.step(skipMove, curGb.rom.readJoypadInputLo, inPrintLetterDelay ? curGb.pokemon.printLetterDelayDoneAddress : curGb.pokemon.printLetterDelayJoypadAddress);
      if (add == 0)
        throw new RuntimeException("runToNextInputFrame returned invalid input frame!");
      if (add == curGb.rom.readJoypadInputLo)
        break;
      if (add == curGb.pokemon.printLetterDelayJoypadAddress) {
//        System.out.println("in");
        inPrintLetterDelay = true;
      }
      if (add == curGb.pokemon.printLetterDelayDoneAddress) {
//        System.out.println("out");
        inPrintLetterDelay = false;
      }
    }
//    System.out.println("hasMoreText at vblank steps: " + curGb.currentStepCount + " inPrintLetterDelay: " + inPrintLetterDelay);

    // advance to read joypad, keeping track of whether you are in or outside the PrintLetterDelay loop
    while (true) {
      int add = EflUtil.runToAddressNoLimit(0, 0, curGb.rom.readJoypadAddress, inPrintLetterDelay ? curGb.pokemon.printLetterDelayDoneAddress : curGb.pokemon.printLetterDelayJoypadAddress);
      if (add == curGb.rom.readJoypadAddress)
        break;
      if (add == curGb.pokemon.printLetterDelayJoypadAddress) {
//        System.out.println("in");
        inPrintLetterDelay = true;
      }
      if (add == curGb.pokemon.printLetterDelayDoneAddress) {
//        System.out.println("out");
        inPrintLetterDelay = false;
      }
    }
//    System.out.println("hasMoreText at joypad steps: " + curGb.currentStepCount + " inPrintLetterDelay: " + inPrintLetterDelay);

    curGb.restore(tmp); // back to beginning
    return inPrintLetterDelay;
  }

	// checks whether next input frame is still for print letter delay
//	private boolean hasMoreText() {
//	  State tmp = curGb.newState();
//    EflUtil.runToNextInputFrame(skipMove);
//    System.out.println("hasMoreText steps: "+curGb.currentStepCount);
//
//    int add = curGb.step(skipMove, curGb.rom.readJoypadInputLo);
//    if (add != curGb.rom.readJoypadInputLo)
//      throw new RuntimeException("runToNextInputFrame returned invalid input frame!");
//    System.out.println("hasMoreText steps: "+curGb.currentStepCount);
//    add = EflUtil.runToAddressNoLimit(0, 0, curGb.rom.readJoypadAddress, curGb.pokemon.printLetterDelayJoypadAddress, 0x01a4);
//    System.out.println("hasMoreText steps: "+curGb.currentStepCount+" add: "+Util.toHex(add));
//    curGb.restore(tmp);
//    if (add != curGb.rom.readJoypadAddress) { // next input frame will still be text delay
////    if (add == curGb.pokemon.printLetterDelayJoypadAddress) { // next input frame will still be text delay
//      System.out.println("hasMoreText: true");
//      return true;
//    } else { // next input frame is not for text delay.
//      System.out.println("hasMoreText: false");
//      return false;
//    }
//	}

	private void skipToStart(StateBuffer startBuffer, State s) {
	  curGb.restore(s);
	  EflUtil.runToFirstInputFrameAfterAddressNoLimit(skipMove, curGb.pokemon.printLetterDelayJoypadAddress); // PrintLetterDelay -> GetJoypadState call
		startBuffer.addState(curGb.createState(true));
	}
}
