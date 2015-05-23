package mrwint.gbtasgen.state;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mrwint.gbtasgen.Gb;

public class State {

	// common
	public int stepCount;
	public int delayStepCount;
	public int ocdCount;
	public int lastMove;
	public InputNode inputs;
	public Map<String,Object> attributes;
  public boolean onFrameBoundaries;
	// state only
	public ByteBuffer bb;
	public int rngState;

	State(ByteBuffer bb, int stepCount, int delayStepCount, int rngState, Map<String, Object> attributes,int ocdCount,int lastMove,InputNode inputs, boolean onFrameBoundaries) {
		this.bb = bb;
		this.stepCount = stepCount;
		this.delayStepCount = delayStepCount;
		this.ocdCount = ocdCount;
		this.lastMove = lastMove;
		this.rngState = rngState;
		this.inputs = inputs;
		this.attributes = attributes;
		this.onFrameBoundaries = onFrameBoundaries;
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

	public void save(ObjectOutputStream oos) {
		try {
			int len = bb.limit();
			byte[] buf = new byte[len];
			bb.get(buf);
			bb.rewind();
			oos.writeInt(stepCount);
			oos.writeInt(delayStepCount);
			oos.writeInt(rngState);
			oos.writeObject(attributes);
			oos.writeInt(ocdCount);
      oos.writeInt(lastMove);
      oos.writeBoolean(onFrameBoundaries);
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

	public static State load(ObjectInputStream ois) {
		try {
			int stepCount = ois.readInt();
			int delayStepCount = ois.readInt();
			int rngState = ois.readInt();
			@SuppressWarnings("unchecked")
			Map<String,Object> attributes = (Map<String, Object>) ois.readObject();
			int ocdCount = ois.readInt();
			int lastMove = ois.readInt();
			boolean onFrameBoundaries = ois.readBoolean();
			int len = ois.readInt();
			byte[] buf = new byte[len];
			ois.readFully(buf);
			InputNode inputs = loadInputs(ois);

			ByteBuffer bb = Gb.createDirectByteBuffer(len);
			bb.put(buf);
			bb.flip();
      return new State(bb,stepCount,delayStepCount,rngState,attributes,ocdCount,lastMove,inputs,onFrameBoundaries);
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

	public static class InputNode {
		public int input;
		public InputNode prev;
		public InputNode(int input, InputNode prev) {
			this.input = input;
			this.prev = prev;
		}
	}
}
