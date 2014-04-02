package mrwint.gbtasgen.state;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;

public class State {
	
	private ByteBuffer bb;
	public int stepCount;
	public int delayStepCount;
	public int ocdCount;
	public int lastMove;
	public int rngState;
	public InputNode inputs;
	public Map<String,Object> attributes;
	
	
	public State() {
		this(-1, new HashMap<String, Object>(currentAttributes));
	}
	
	private State(int rngState, Map<String, Object> attributes) {
		this(Gb.saveState(),currentStepCount,currentDelayStepCount,rngState, attributes,currentOcdCount,currentLastMove,currentInputNode);
	}
	
	private State(ByteBuffer bb, int stepCount, int delayStepCount, int rngState, Map<String, Object> attributes,int ocdCount,int lastMove,InputNode inputs) {
		if(!onFrameBoundaries)
			System.err.println("WARNING: creating State while not on frame boundaries!");
		this.bb = bb;
		this.stepCount = stepCount;
		this.delayStepCount = delayStepCount;
		this.rngState = rngState;
		this.inputs = inputs;
		this.ocdCount = ocdCount;
		this.lastMove = lastMove;
		this.attributes = attributes;
	}
	
	public int getAttributeInt(String name) {
		if(!attributes.containsKey(name))
			return -1;
		return (Integer)attributes.get(name);
	}
	public State setAttributeInt(String name, int value) {
		attributes.put(name, value);
		return this;
	}
	
	public static int getCAttributeInt(String name) {
		if(!currentAttributes.containsKey(name))
			return -1;
		return (Integer)currentAttributes.get(name);
	}
	public static void setCAttributeInt(String name, int value) {
		currentAttributes.put(name, value);
	}
	
	public static State createState() {
		return createState(currentAttributes,false);
	}
	
	public static State createState(boolean noRestore) {
		return createState(currentAttributes,noRestore);
	}
	
	public static State createState(Map<String,Object> attributes, boolean noRestore) {
		State ret = new State();
		step(); // finish current frame, forces random to reflect the inputs
		int rngState = Gb.getRNGState(RomInfo.rom.rngAddress);
		if(!noRestore)
			ret.restore();
		ret.rngState = rngState;
		ret.attributes = new HashMap<String, Object>(attributes);
		return ret;
	}
	
	public int restore() {
		if(!onFrameBoundaries)
			step(); // get to next frame boundary
		Gb.loadState(bb);
		currentInputNode = inputs;
		currentStepCount = stepCount;
		currentDelayStepCount = delayStepCount;
		currentOcdCount = ocdCount;
		currentLastMove = lastMove;
		currentAttributes = new HashMap<String, Object>(attributes);
		clearCache();
		rerecordCount++;
		return stepCount;
	}
	
	
	public void save(String filename) {
		try {
			String path = "saves/" + filename + RomInfo.rom.fileNameSuffix;
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			save(oos);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save(ObjectOutputStream oos) {
		try {
			int len = bb.limit();
			byte[] buf = new byte[len];
			bb.get(buf);
			oos.writeInt(stepCount);
			oos.writeInt(delayStepCount);
			oos.writeInt(rngState);
			oos.writeObject(attributes);
			oos.writeInt(ocdCount);
			oos.writeInt(lastMove);
			oos.writeInt(len);
			oos.write(buf);
			saveInputs(oos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveInputs(ObjectOutputStream oos) throws IOException {
		List<InputNode> inputList = new ArrayList<InputNode>();
		InputNode n = inputs;
		while(n != null) {
			inputList.add(n);
			n = n.prev;
		}
		oos.writeInt(inputList.size());
		for(int i=inputList.size()-1;i>=0;i--)
			oos.writeInt(inputList.get(i).input);
	}

	public static State load(String filename) {
		try {
			String path = "saves/" + filename + RomInfo.rom.fileNameSuffix;
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			State ret = load(ois);
			ois.close();
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static State load(ObjectInputStream ois) {
		try {
			int stepCount = ois.readInt();
			int delayStepCount = ois.readInt();
			int rngState = ois.readInt();
			@SuppressWarnings("unchecked")
			Map<String,Object> attributes = (Map<String, Object>) ois.readObject();
			int ocdCount = ois.readInt();
			int lastMove = ois.readInt();
			int len = ois.readInt();
			byte[] buf = new byte[len];
			ois.readFully(buf);
			InputNode inputs = loadInputs(ois);
			
			ByteBuffer bb = Gb.createDirectByteBuffer(len);
			bb.put(buf);
			bb.flip();
			return new State(bb,stepCount,delayStepCount,rngState,attributes,ocdCount,lastMove,inputs);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static InputNode loadInputs(ObjectInputStream ois) throws IOException {
		int len = ois.readInt();
		InputNode ret = null;
		for(int i=0;i<len;i++)
			ret = new InputNode(ois.readInt(), ret);
		return ret;
	}
	
	public static State root;
	
	public static void init(String romName) {
		Gb.startEmulator(romName);
		root = new State();
		
		ROM = new int[Gb.getROMSize()];
		
		State.step(); // initialize all emulator resources (for gambatte)
		root.restore();
	}

	public static boolean onFrameBoundaries = true;
	public static long rerecordCount = 0;
		
	public static int currentStepCount = 0;
	public static int currentDelayStepCount = 0;
	public static Map<String,Object> currentAttributes = new HashMap<String, Object>();
	public static int currentOcdCount = 0;
	public static int currentLastMove = -1;
	
	static int[] currentRegisters = new int[Gb.NUM_REGISTERS];
	static boolean currentRegistersValid = false;
	static int[] currentMemory = new int[Gb.GB_MEMORY];
	static boolean currentMemoryValid = false;
	static int[] ROM = null;
	static boolean ROMValid = false;
	
	public static class InputNode {
		public int input;
		public InputNode prev;
		public InputNode(int input, InputNode prev) {
			this.input = input;
			this.prev = prev;
		}
	}
	
	public static InputNode currentInputNode = null;
	
	public static void logInput(int moves) {
		if(!onFrameBoundaries)
			return;
		currentInputNode = new InputNode(moves, currentInputNode);
	}

	public static void step() {
		Gb.step(0);
		logInput(0);
		currentStepCount++;
		onFrameBoundaries = true;
		clearCache();
	}
	
	public static void steps(int numberOfSteps) {
		steps(numberOfSteps,0);
	}

	public static void steps(int numberOfSteps, int moves) {
		for(int i=0;i<numberOfSteps;i++)
			step(moves);
	}

	public static int step(int moves, int... addresses) {
		if(moves != 0) {
			currentOcdCount += 2;
			if(currentLastMove != moves)
				currentOcdCount++;
			currentLastMove = moves;
		}
		
		int ret = Gb.step(moves, addresses);
		logInput(moves);
		onFrameBoundaries = (ret == 0);
		if(onFrameBoundaries)
			currentStepCount++;
		clearCache();
		return ret;
	}

	public static int[] getCurrentRegisters() {
		if(!currentRegistersValid)
			Gb.getRegisters(currentRegisters);
		currentRegistersValid = true;
		return currentRegisters;
	}
	public static int getRegister(int register) {
		return getCurrentRegisters()[register];
	}

	public static int[] getCurrentMemory() {
		if(!currentMemoryValid)
			Gb.getMemory(currentMemory);
		currentMemoryValid = true;
		return currentMemory;
	}

	public static int[] getROM() {
		if(!ROMValid)
			Gb.getROM(ROM);
		ROMValid = true;
		return ROM;
	}
	
	public static void clearCache() {
		currentRegistersValid = false;
		currentMemoryValid = false;
	}
}
