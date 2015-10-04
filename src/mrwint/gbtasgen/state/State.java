package mrwint.gbtasgen.state;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
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
  public long rerecordCount;
	// state only
	public ByteBuffer bb;
	public int rngState;

	State(ByteBuffer bb, int stepCount, int delayStepCount, int rngState, Map<String, Object> attributes,int ocdCount,int lastMove,InputNode inputs, boolean onFrameBoundaries, long rerecordCount) {
		this.bb = bb;
		this.stepCount = stepCount;
		this.delayStepCount = delayStepCount;
		this.ocdCount = ocdCount;
		this.lastMove = lastMove;
		this.rngState = rngState;
		this.inputs = inputs;
		this.attributes = attributes;
		this.onFrameBoundaries = onFrameBoundaries;
		this.rerecordCount = rerecordCount;
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
      oos.writeLong(rerecordCount);
			oos.writeInt(len);
			oos.write(buf);
			saveInputs(oos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveInputs(ObjectOutputStream oos) throws IOException {
	  ArrayList<InputNode> inputList = toArray(inputs);
		oos.writeInt(inputList.size());
		for(int i=inputList.size()-1;i>=0;i--)
			oos.writeInt(inputList.get(i).input);
	}

  public static State load(ObjectInputStream ois) {
    return load(ois, null);
  }

  private static ArrayList<InputNode> toArray(InputNode n) {
    ArrayList<InputNode> inputList = new ArrayList<InputNode>();
    while(n != null) {
      inputList.add(n);
      n = n.prev;
    }
    return inputList;
  }

  public static State load(ObjectInputStream ois, InputNode refInputs) {
		try {
			int stepCount = ois.readInt();
			int delayStepCount = ois.readInt();
			int rngState = ois.readInt();
			@SuppressWarnings("unchecked")
			Map<String,Object> attributes = (Map<String, Object>) ois.readObject();
			int ocdCount = ois.readInt();
			int lastMove = ois.readInt();
      boolean onFrameBoundaries = ois.readBoolean();
      long rerecordCount = ois.readLong();
			int len = ois.readInt();
			byte[] buf = new byte[len];
			ois.readFully(buf);
			InputNode inputs = loadInputs(ois, refInputs == null ? null : toArray(refInputs));

			ByteBuffer bb = Gb.createDirectByteBuffer(len);
			bb.put(buf);
			bb.flip();
      return new State(bb,stepCount,delayStepCount,rngState,attributes,ocdCount,lastMove,inputs,onFrameBoundaries,rerecordCount);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static InputNode loadInputs(ObjectInputStream ois, ArrayList<InputNode> refInputs) throws IOException {
		int len = ois.readInt();
		InputNode ret = null;
		for(int i=0;i<len;i++) {
		  int input = ois.readInt();
		  if (refInputs != null && refInputs.size()-1-i >= 0 && refInputs.get(refInputs.size()-1-i).input == input)
		    ret = refInputs.get(refInputs.size()-1-i);
		  else {
//		    if (refInputs != null)
//		      System.out.println("refInputs failed at " + i);
		    refInputs = null;
	      ret = new InputNode(input, ret);
		  }
		}
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
