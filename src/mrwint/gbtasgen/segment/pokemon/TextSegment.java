package mrwint.gbtasgen.segment.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.Util;

public class TextSegment implements Segment {

	private int skipMove;
	private boolean finishLastFrame;

	public TextSegment() {
		this(Move.A);
	}

	public TextSegment(int skipMove) {
		this(skipMove,true);
	}

	public TextSegment(int skipMove, boolean finishLastFrame) {
    EflUtil.assertNoEfl();

    this.skipMove = skipMove;
		this.finishLastFrame = finishLastFrame;
	}

	private int textSpeed;
	private boolean textSpeedInit = false;

	@Override
	public StateBuffer execute(StateBuffer in) {
		StateBuffer curBuffer = new StateBuffer();
		for(State s : in.getStates())
			skipToStart(curBuffer, s);

		StateBuffer goalBuffer = new StateBuffer();

		boolean first = true;

		while(!curBuffer.isEmpty()) {
			StateBuffer nextBuffer = new StateBuffer();

			for(State s : curBuffer.getStates())
				progress(s, nextBuffer, goalBuffer, first);
			curBuffer = nextBuffer;
			first = false;
		}

		return goalBuffer;
	}

	private void progress(State s, StateBuffer nextBuffer, StateBuffer goalBuffer, boolean first) {
		curGb.restore(s);
		if(!textSpeedInit) {
			textSpeedInit = true;
			textSpeed = curGb.readMemory(curGb.pokemon.optionsAddress) & curGb.pokemon.optionsTextSpeedMask;
		}
		if (textSpeed == 0) {
			State os = s;
			while (goToNextInput()) {
			  curGb.step(); // finish step
				s = os;
				os = curGb.newState();
			}
			if(!finishLastFrame)
			  curGb.restore(s);
			goalBuffer.addState(curGb.createState(true)); // frame finished (no inputs)
			return;
		}
		boolean inputBeforeEnd = false;
		int add = curGb.step(0,curGb.pokemon.printLetterDelayDoneAddress,curGb.pokemon.readJoypadAddress); // .done, input
		if(add == curGb.pokemon.readJoypadAddress) {
			inputBeforeEnd = true;
			add = curGb.step(0,curGb.pokemon.printLetterDelayDoneAddress);
		}
		if(add == 0 && !first)
			System.out.println("ERROR: end of function not found!!! (inputBeforeEnd="+inputBeforeEnd+")");
		if(add != 0 && first)
			System.out.println("ERROR: first frame exited prematurely!!!");
		if(!inputBeforeEnd && first)
			System.out.println("ERROR: first frame has no input!!!");
		if((!first || add != 0) && !goToNextInput()) { // that was last call
			if(!finishLastFrame || !inputBeforeEnd) {
				goalBuffer.addState(s);
			} else {
				goalBuffer.addState(curGb.createState(true)); // frame finished (no inputs)
				curGb.restore(s);
				add = curGb.step(skipMove, curGb.pokemon.printLetterDelayDelayFrameAddress, curGb.pokemon.printLetterDelayDoneAddress); // before/after delaying frame -> keypress actually recognized
				if(add != curGb.pokemon.printLetterDelayDelayFrameAddress)
					System.out.println("ERROR: keypress unexpectedly not recognized!!!");
				curGb.step(skipMove); // finish up wait frame
				goalBuffer.addState(curGb.createState(true)); // frame finished (with inputs)
			}
			return;
		}
		if(textSpeed <= 1) {
			if((!first || add != 0)) // frame already finished for first frame by last step
			  curGb.step(); // finish frame (no input) (prepared by goToNextInput)
			nextBuffer.addState(curGb.createState(true));
		}
		curGb.restore(s);
		add = curGb.step(skipMove, curGb.pokemon.printLetterDelayDelayFrameAddress, curGb.pokemon.printLetterDelayDoneAddress); // before/after delaying frame -> keypress actually recognized
		if(first) {
			if(add != curGb.pokemon.printLetterDelayDelayFrameAddress)
				System.out.println("ERROR: keypress unexpectedly not recognized first !!! ("+Integer.toHexString(add)+")");
			else {
			  curGb.step(skipMove); // finish frame
				nextBuffer.addState(curGb.createState(true));
			}
			return;
		}
		if(inputBeforeEnd) {
			if(add != curGb.pokemon.printLetterDelayDelayFrameAddress)
				System.out.println("ERROR: keypress unexpectedly not recognized 2 !!!");
			curGb.step(skipMove); // finish extra lag frame
			progress(curGb.createState(true),nextBuffer,goalBuffer,false);
			return;
		}
		if(add == curGb.pokemon.printLetterDelayDelayFrameAddress)
			System.out.println("ERROR: keypress unexpectedly recognized 3 !!!");
		if(add != 0)
			add = curGb.step(skipMove, curGb.pokemon.printLetterDelayDelayFrameAddress); // delaying frame -> keypress actually recognized
		if(add != 0) {
		  curGb.step(skipMove); // finish frame
			nextBuffer.addState(curGb.createState(true));
		} else
			System.out.println("INFO: TextSegment: caught invalid keypress");
	}

	private boolean goToNextInput() {
		int add = curGb.step(0, curGb.pokemon.printLetterDelayJoypadAddress); // PrintLetterDelay -> GetJoypadState call
		if(add == 0)
			return false;
		add = curGb.step(0, curGb.pokemon.readJoypadAddress); // actual input reading instruction
		if(add == 0)
			return false;
		return true;
	}

	private void skipToStart(StateBuffer startBuffer, State s) {
	  curGb.restore(s);
		Util.runToFrameBeforeAddress(0, 0, curGb.pokemon.printLetterDelayJoypadAddress); // PrintLetterDelay -> GetJoypadState call
		Util.runToNextInputFrame(); // guard against rare case where vblank occurs right between

		startBuffer.addState(curGb.createState(true));
	}
}
