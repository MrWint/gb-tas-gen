package mrwint.gbtasgen.segment.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import java.util.TreeMap;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public class EflTextSegment implements Segment {

	private int skipMove;

	public EflTextSegment() {
		this(Move.A);
	}

	public EflTextSegment(int skipMove) {
    EflUtil.assertEfl();

    this.skipMove = skipMove;
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		StateBuffer curBuffer = new StateBuffer();
		for(State s : in.getStates())
			skipToStart(curBuffer, s);

		StateBuffer goalBuffer = new StateBuffer();

//		boolean first = true;

		TreeMap<Integer, StateBuffer> next = new TreeMap<Integer, StateBuffer>();
		if (curBuffer.size() > 0)
		  next.put(0, curBuffer);

		while(!next.isEmpty()) {
		  Integer fewestSkips = next.firstKey();
      curBuffer = next.get(fewestSkips);
//      System.out.println("EflTextSegment2: fewestSkips="+fewestSkips+" curBuffer="+curBuffer.size()+" goalBuffer="+goalBuffer.size());
		  next.remove(fewestSkips);

			for(State s : curBuffer.getStates())
				progress(s, fewestSkips, next, goalBuffer);
//			first = false;
		}

		return goalBuffer;
	}

	private void addNext(State s, int delays, TreeMap<Integer, StateBuffer> next) {
	  if (!next.containsKey(delays))
	    next.put(delays, new StateBuffer());
    next.get(delays).addState(s);
	}

  private void progress(State s, int delays, TreeMap<Integer, StateBuffer> next, StateBuffer goalBuffer) {
    curGb.restore(s);
    int textSpeed = curGb.readMemory(curGb.pokemon.optionsAddress) & curGb.pokemon.optionsTextSpeedMask;
//  System.out.println("progress: textSpeed: " + textSpeed);
    if (textSpeed <= 1) {
      progressWith(s, 0, textSpeed, delays, next, goalBuffer);
      if (textSpeed >= 1)
        curGb.restore(s);
    }
    if (textSpeed >= 1)
      progressWith(s, skipMove, textSpeed, delays, next, goalBuffer);
  }

  private void progressWith(State initial, int usedMove, int textSpeed, int delays, TreeMap<Integer, StateBuffer> next, StateBuffer goalBuffer) {
//    System.out.println("progress: usedMove: " + usedMove + ", delays: " + delays+ ", steps: " + curGb.stepCount);
    curGb.step(usedMove, curGb.pokemon.printLetterDelayDoneAddress);

    // hasMoreText
    int limit = textSpeed + 30;
//    State initial = curGb.newState();
    int initialSteps = curGb.stepCount;
    boolean inPrintLetterDelay = true;

    int numDonePasses = 0;
    int numPotentialDonePasses = 0;

    int lastVframe = Integer.MAX_VALUE;

    if (!curGb.onFrameBoundaries) { // forward to next frame boundary
      while (true) {
        int add = curGb.step(0, inPrintLetterDelay ? curGb.pokemon.printLetterDelayDoneAddress : curGb.pokemon.printLetterDelayJoypadAddress);
        if (add == 0)
          break;
        if (inPrintLetterDelay)
          numDonePasses++;
        inPrintLetterDelay = !inPrintLetterDelay;
//        System.out.println(inPrintLetterDelay ? "in" : "out");
      }
    }

    findInitialPotentialInputFrame:
    while (true) {
      int add = EflUtil.runToAddressLimit(0, 0, limit, curGb.rom.readJoypadInputLo, inPrintLetterDelay ? curGb.pokemon.printLetterDelayDoneAddress : curGb.pokemon.printLetterDelayJoypadAddress);
//      System.out.println("initial run add: " + Util.toHex(add));

      if (add == 0 || curGb.stepCount - initialSteps > limit) { // limit reached
        curGb.restore(initial);
        curGb.step(usedMove, curGb.pokemon.printLetterDelayDoneAddress);
        goalBuffer.addState(curGb.createState(true));
//        System.out.println("hit limit 1");
        return;
      }
      if (add != curGb.rom.readJoypadInputLo) { // trigger found (may be in an input frame)
        if (inPrintLetterDelay)
          numPotentialDonePasses++;
        inPrintLetterDelay = !inPrintLetterDelay;
//        System.out.println(inPrintLetterDelay ? "in" : "out");

        while (true) { // look for more triggers or potential input frame state
          add = curGb.step(0, curGb.rom.readJoypadInputLo, inPrintLetterDelay ? curGb.pokemon.printLetterDelayDoneAddress : curGb.pokemon.printLetterDelayJoypadAddress);
//          System.out.println("initial step add: " + Util.toHex(add));
          if (add == 0) { // frame ended; no input frame -> count triggers for real; run to next address and repeat
            numDonePasses += numPotentialDonePasses;
            numPotentialDonePasses = 0;
            continue findInitialPotentialInputFrame;
          }
          if (add != curGb.rom.readJoypadInputLo) { // more potential triggers, count and continue
            if (inPrintLetterDelay)
              numPotentialDonePasses++;
            inPrintLetterDelay = !inPrintLetterDelay;
//            System.out.println(inPrintLetterDelay ? "in" : "out");
            continue;
          }
          break; // hit readJoypadInputLo
        }
      }
      break; // hit readJoypadInputLo
    }

//    System.out.println("found initial input frame at: " + curGb.stepCount);

    searchForValidInputFrame:
    while (true) {
//      System.out.println("try next input frame at: " + curGb.stepCount);

      // invariant: at curGb.rom.readJoypadInputLo of potential input frame
      lastVframe = curGb.stepCount - 1; // subtract started frame

      if (curGb.stepCount - initialSteps > limit) { // limit reached
        curGb.restore(initial);
        curGb.step(usedMove, curGb.pokemon.printLetterDelayDoneAddress);
        goalBuffer.addState(curGb.createState(true));
//        System.out.println("hit limit 2");
        return;
      }

      while (true) {
        int add = curGb.step(0, curGb.rom.readJoypadAddress, inPrintLetterDelay ? curGb.pokemon.printLetterDelayDoneAddress : curGb.pokemon.printLetterDelayJoypadAddress);
//        System.out.println("step add: " + Util.toHex(add));
        if (add == curGb.rom.readJoypadAddress) { // input frame found
          break searchForValidInputFrame; // break out and handle result at the end
        }
        if (add != 0) { // more potential triggers, count and continue
          if (inPrintLetterDelay)
            numPotentialDonePasses++;
          inPrintLetterDelay = !inPrintLetterDelay;
//          System.out.println(inPrintLetterDelay ? "in" : "out");
          continue;
        }
        break; // hit frame end
      }

      // invariant: at start of frame after potential input frame
      int numPotentialDonePassesOnPrevFrames = numPotentialDonePasses;
      numPotentialDonePasses = 0; // all potential triggers have moved to a previous frame

      while (true) {
        int add = EflUtil.runToAddressLimit(0, 0, limit, curGb.rom.readJoypadInputLo, curGb.rom.readJoypadAddress, inPrintLetterDelay ? curGb.pokemon.printLetterDelayDoneAddress : curGb.pokemon.printLetterDelayJoypadAddress);
//        System.out.println("run add: " + Util.toHex(add));
        if (add == curGb.rom.readJoypadAddress) { // input frame found
          break searchForValidInputFrame; // break out and handle result at the end
        }
        if (add == curGb.rom.readJoypadInputLo) { // found new Vframe
          numDonePasses += numPotentialDonePassesOnPrevFrames; // all potential triggers on previous frames are actual triggers
          continue searchForValidInputFrame; // start over
        }
        if (add != 0) { // potential trigger found
          if (inPrintLetterDelay)
            numPotentialDonePasses++;
          inPrintLetterDelay = !inPrintLetterDelay;
//          System.out.println(inPrintLetterDelay ? "in" : "out");

          while (true) { // look for more triggers or potential input frame state
            add = curGb.step(0, curGb.rom.readJoypadInputLo, curGb.rom.readJoypadAddress, inPrintLetterDelay ? curGb.pokemon.printLetterDelayDoneAddress : curGb.pokemon.printLetterDelayJoypadAddress);
//            System.out.println("step2 add: " + Util.toHex(add));
            if (add == curGb.rom.readJoypadAddress) { // input frame found
              break searchForValidInputFrame; // break out and handle result at the end
            }
            if (add == curGb.rom.readJoypadInputLo) { // found new Vframe
              numDonePasses += numPotentialDonePassesOnPrevFrames; // all potential triggers on previous frames are actual triggers
              continue searchForValidInputFrame; // start over
            }
            if (add != 0) { // more potential triggers, count and continue
              if (inPrintLetterDelay)
                numPotentialDonePasses++;
              inPrintLetterDelay = !inPrintLetterDelay;
//              System.out.println(inPrintLetterDelay ? "in" : "out");
              continue;
            }
            break; // frame ended
          }
          // frame ended
          numPotentialDonePassesOnPrevFrames += numPotentialDonePasses;
          numPotentialDonePasses = 0; // all potential triggers have moved to a previous frame
          continue;
        }
        curGb.restore(initial); // limit reached
        curGb.step(usedMove, curGb.pokemon.printLetterDelayDoneAddress);
        goalBuffer.addState(curGb.createState(true));
//        System.out.println("hit limit 3");
        return;
      }
    }

//    System.out.println("progress finished at: " + curGb.stepCount + ", inPrintLetterDelay="+inPrintLetterDelay);

    if (inPrintLetterDelay) { // found suitable input frame
      curGb.restore(initial);
      curGb.step(usedMove, curGb.pokemon.printLetterDelayDoneAddress);
      EflUtil.runFor(lastVframe - initialSteps, 0, 0);
      addNext(curGb.createState(true), delays + numDonePasses, next);
    } else {
      curGb.restore(initial);
      curGb.step(usedMove, curGb.pokemon.printLetterDelayDoneAddress);
      goalBuffer.addState(curGb.createState(true));
    }
  }

	private void skipToStart(StateBuffer startBuffer, State s) {
	  curGb.restore(s);
	  EflUtil.runToFirstInputFrameAfterAddressNoLimit(skipMove, curGb.pokemon.printLetterDelayJoypadAddress); // PrintLetterDelay -> GetJoypadState call
		startBuffer.addState(curGb.createState(true));
	}
}
