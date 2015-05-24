package mrwint.gbtasgen.state;

import java.util.HashMap;
import java.util.Map;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.pokemon.PokemonRomInfo;
import mrwint.gbtasgen.rom.sml2.Sml2RomInfo;
import mrwint.gbtasgen.rom.tetris.TetrisRomInfo;
import mrwint.gbtasgen.state.State.InputNode;
import mrwint.gbtasgen.util.EflUtil;

public class Gameboy {

  public static Gameboy curGb;


  private final Gb gb;

	public boolean onFrameBoundaries = true;
	public long rerecordCount = 0;

	public State root;

	public int currentStepCount = 0;
	public int currentDelayStepCount = 0;
	public int currentOcdCount = 0;
	public int lastMove = 0;
	public InputNode currentInputNode = null;
	public Map<String,Object> currentAttributes = new HashMap<String, Object>();

	private int[] currentRegisters;
	private boolean currentRegistersValid = false;
	private int[] currentMemory;
	private boolean currentMemoryValid = false;
	private int[] ROM = null;
	private boolean ROMValid = false;

	public final boolean equalLengthFrames;
  public final RomInfo rom;
  public PokemonRomInfo pokemon;
  public TetrisRomInfo tetris;
  public Sml2RomInfo sml2;

	public Gameboy(RomInfo rom, int screen, boolean equalLengthFrames) {
    this.rom = rom;
    this.equalLengthFrames = equalLengthFrames;

    if (rom instanceof PokemonRomInfo)
      pokemon = (PokemonRomInfo)rom;
    if (rom instanceof TetrisRomInfo)
      tetris = (TetrisRomInfo)rom;
    if (rom instanceof Sml2RomInfo)
      sml2 = (Sml2RomInfo)rom;

    this.gb = new Gb(screen, equalLengthFrames);
    this.gb.startEmulator(rom.romFileName);
    this.root = newState();

    step(); // initialize all emulator resources (for gambatte)
    restore(root);
	}

	public int getAttributeInt(String name) {
		if (!currentAttributes.containsKey(name))
			return -1;
		return (Integer)currentAttributes.get(name);
	}
	public void setAttributeInt(String name, int value) {
		currentAttributes.put(name, value);
	}

	public State newState() {
//    if (!onFrameBoundaries)
//      System.err.println("WARNING: creating State while not on frame boundaries!");
	  return new State(gb.saveState(), currentStepCount, currentDelayStepCount, -1 /* rngState */,
	      new HashMap<String, Object>(currentAttributes), currentOcdCount, lastMove, currentInputNode, onFrameBoundaries);
	}

	public State createState() {
		return createState(false);
	}

	public State createState(boolean noRestore) {
	  State ret = newState();
	  if (!onFrameBoundaries)
	    step();
		step(); // finish current frame, forces random to reflect the inputs
//	  while (step(0, 0x40) == 0);  // vblank interrupt
//	  step();
//    while (step(0, 0x40) == 0);  // vblank interrupt
		int rngState = rom.getRngState(gb);
		if (!noRestore)
			restore(ret);
		ret.rngState = rngState;
		return ret;
	}

	public int restore(State s) {
//		if (!onFrameBoundaries)
//			step(); // get to next frame boundary
		gb.loadState(s.bb);
		currentInputNode = s.inputs;
		currentStepCount = s.stepCount;
		currentDelayStepCount = s.delayStepCount;
		currentOcdCount = s.ocdCount;
		lastMove = s.lastMove;
		currentAttributes = new HashMap<String, Object>(s.attributes);
		onFrameBoundaries = s.onFrameBoundaries;
		clearCache();
		rerecordCount++;
		return s.stepCount;
	}

	private void logInput(int moves) {
		if (!onFrameBoundaries)
			return;
		currentInputNode = new InputNode(moves, currentInputNode);
    currentStepCount++;
    lastMove = moves;
	}

	public void step() {
		gb.step(onFrameBoundaries ? 0 : lastMove);
		logInput(0);
		onFrameBoundaries = true;
		clearCache();
	}

	public void steps(int numberOfSteps) {
		steps(numberOfSteps, 0);
	}

	public void steps(int numberOfSteps, int moves) {
		for (int i = 0; i < numberOfSteps; i++)
			step(moves);
	}

	public int step(int moves, int... addresses) {
		if(moves != 0) {
			currentOcdCount += 2;
			if(lastMove != moves)
				currentOcdCount++;
			lastMove = moves;
		}

		int ret = gb.step(onFrameBoundaries ? moves : lastMove, addresses);
		logInput(moves);
		onFrameBoundaries = (ret == 0);
		clearCache();
		return ret;
	}

	public int[] getCurrentRegisters() {
		if(!currentRegistersValid)
		  currentRegisters = gb.getRegisters();
		currentRegistersValid = true;
		return currentRegisters;
	}
	public int getRegister(int register) {
		return getCurrentRegisters()[register];
	}

	public int[] getCurrentMemory() {
		if(!currentMemoryValid)
		  currentMemory = gb.getMemory();
		currentMemoryValid = true;
		return currentMemory;
	}

	public int[] getROM() {
		if(!ROMValid) {
	    ROM = gb.getROM();
		}
		ROMValid = true;
		return ROM;
	}

	public void clearCache() {
		currentRegistersValid = false;
		currentMemoryValid = false;
	}

  public int readMemory(int address) {
    return gb.readMemory(address);
  }

  public void writeMemory(int address, int value) {
    gb.writeMemory(address, value);
  }
}
