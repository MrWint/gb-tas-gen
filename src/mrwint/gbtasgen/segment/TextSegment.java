package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Util;

public class TextSegment extends Segment {
	
	private int skipMove;
	private boolean finishLastFrame;
	
	public TextSegment() {
		this(Move.A);
	}
	
	public TextSegment(int skipMove) {
		this(skipMove,true);
	}
	
	public TextSegment(int skipMove, boolean finishLastFrame) {
		this(skipMove,finishLastFrame,StateBuffer.MAX_BUFFER_SIZE);
	}
	
	public TextSegment(int skipMove, boolean finishLastFrame, int bufferSize) {
		this.skipMove = skipMove;
		this.finishLastFrame = finishLastFrame;
		this.bufferSize = bufferSize;
	}
	
	private int textSpeed;
	private boolean textSpeedInit = false;
	private int bufferSize;
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		StateBuffer curBuffer = new StateBuffer(bufferSize);
		for(State s : in.getStates())
			skipToStart(curBuffer, s);

		StateBuffer goalBuffer = new StateBuffer(bufferSize);
		
		boolean first = true;
		
		while(!curBuffer.isEmpty()) {
			StateBuffer nextBuffer = new StateBuffer(bufferSize);
			
			for(State s : curBuffer.getStates())
				progress(s, nextBuffer, goalBuffer, first);
			curBuffer = nextBuffer;
			first = false;
		}
		
		return goalBuffer;
	}
	
	private void progress(State s, StateBuffer nextBuffer, StateBuffer goalBuffer, boolean first) {
		s.restore();
		if(!textSpeedInit) {
			textSpeedInit = true;
			textSpeed = Gb.readMemory(RomInfo.rom.optionsAddress) & RomInfo.rom.optionsTextSpeedMask;
		}
		if (textSpeed == 0) {
			State os = s;
			while (goToNextInput()) {
				State.step(); // finish step
				s = os;
				os = new State();
			}
			if(!finishLastFrame)
				s.restore();
			goalBuffer.addState(State.createState(true)); // frame finished (no inputs)
			return;
		}
		boolean inputBeforeEnd = false;
		int add = State.step(0,RomInfo.rom.printLetterDelayDoneAddress,RomInfo.rom.readJoypadAddress); // .done, input
		if(add == RomInfo.rom.readJoypadAddress) {
			inputBeforeEnd = true;
			add = State.step(0,RomInfo.rom.printLetterDelayDoneAddress);
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
				goalBuffer.addState(State.createState(true)); // frame finished (no inputs)
				s.restore();
				add = State.step(skipMove, RomInfo.rom.printLetterDelayDelayFrameAddress, RomInfo.rom.printLetterDelayDoneAddress); // before/after delaying frame -> keypress actually recognized
				if(add != RomInfo.rom.printLetterDelayDelayFrameAddress)
					System.out.println("ERROR: keypress unexpectedly not recognized!!!");
				State.step(skipMove); // finish up wait frame
				goalBuffer.addState(State.createState(true)); // frame finished (with inputs)
			}
			return;
		}
		if(textSpeed <= 1) {
			if((!first || add != 0)) // frame already finished for first frame by last step
				State.step(); // finish frame (no input) (prepared by goToNextInput)
			nextBuffer.addState(State.createState(true));
		}
		s.restore();
		add = State.step(skipMove, RomInfo.rom.printLetterDelayDelayFrameAddress, RomInfo.rom.printLetterDelayDoneAddress); // before/after delaying frame -> keypress actually recognized
		if(first) {
			if(add != RomInfo.rom.printLetterDelayDelayFrameAddress)
				System.out.println("ERROR: keypress unexpectedly not recognized first !!! ("+Integer.toHexString(add)+")");
			else {
				State.step(skipMove); // finish frame
				nextBuffer.addState(State.createState(true));
			}
			return;
		}
		if(inputBeforeEnd) {
			if(add != RomInfo.rom.printLetterDelayDelayFrameAddress)
				System.out.println("ERROR: keypress unexpectedly not recognized 2 !!!");
			State.step(skipMove); // finish extra lag frame
			progress(State.createState(true),nextBuffer,goalBuffer,false);
			return;
		}
		if(add == RomInfo.rom.printLetterDelayDelayFrameAddress)
			System.out.println("ERROR: keypress unexpectedly recognized 3 !!!");
		if(add != 0)
			add = State.step(skipMove, RomInfo.rom.printLetterDelayDelayFrameAddress); // delaying frame -> keypress actually recognized
		if(add != 0) {
			State.step(skipMove); // finish frame
			nextBuffer.addState(State.createState(true));
		} else
			System.out.println("INFO: TextSegment: caught invalid keypress");
	}

	private boolean goToNextInput() {
		int add = State.step(0, RomInfo.rom.printLetterDelayJoypadAddress); // PrintLetterDelay -> GetJoypadState call
		if(add == 0)
			return false;
		add = State.step(0, RomInfo.rom.readJoypadAddress); // actual input reading instruction
		if(add == 0)
			return false;
		return true;
	}

	private void skipToStart(StateBuffer startBuffer, State s) {
		s.restore();
		Util.runToFrameBeforeAddress(0, 0, RomInfo.rom.printLetterDelayJoypadAddress); // PrintLetterDelay -> GetJoypadState call
		Util.runToNextInputFrame(); // guard against rare case where vblank occurs right between
		
		startBuffer.addState(State.createState(true));
	}
}
