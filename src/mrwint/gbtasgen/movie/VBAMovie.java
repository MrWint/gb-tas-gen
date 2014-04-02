package mrwint.gbtasgen.movie;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.State.InputNode;


public class VBAMovie {
	public static void saveMovie(State s, String filename) {
		try {
			String path = "movies/" + filename;
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));
			
			writeHeader(s,dos);
			writeAuthorInfo(dos);
			writeInputs(s,dos);
			
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeInputs(State s, DataOutputStream dos) throws IOException {
		List<Integer> inputs = new ArrayList<Integer>();
		InputNode curNode = s.inputs;
		while(curNode != null) {
			inputs.add(curNode.input);
			curNode = curNode.prev;
		}
		if(inputs.size() != s.stepCount)
			System.err.println("WARNING: input len ("+inputs.size()+") does not match step count ("+s.stepCount+")");
		for(int i = inputs.size()-1; i >= 0 ; i--)
			dos.writeShort(Short.reverseBytes(inputs.get(i).shortValue()));
	}

	private static void writeAuthorInfo(DataOutputStream dos) throws IOException {
		for(int i=0;i<192;i++)
			dos.write(0);
	}

	private static void writeHeader(State s, DataOutputStream dos) throws IOException {
		dos.writeInt(0x56424d1a); // magic number
		dos.writeInt(Integer.reverseBytes(1)); // major version
		dos.writeInt(Integer.reverseBytes((int)(System.currentTimeMillis()/1000))); // time stamp
		dos.writeInt(Integer.reverseBytes(s.stepCount));
		dos.writeInt(Integer.reverseBytes((int)State.rerecordCount));
		dos.write(0); // no save state
		dos.write(1); // controller 1 in use
		dos.write(4); // SGB
		dos.write(0x70); // lag reduction, gbcHdma5Fix, echoRAMFix
		dos.writeInt(Integer.reverseBytes(0)); // theApp.winSaveType
		dos.writeInt(Integer.reverseBytes(0x10000)); // theApp.winFlashSize
		dos.writeInt(Integer.reverseBytes(2)); // gbEmulatorType SGB
		dos.write("POKEMON RED\0".getBytes("UTF-8")); // ROM name
		dos.write(0); // minor version
		dos.write(0x20); // CRC
		dos.write(new byte[]{(byte)0xE6, (byte)0x91}); // internal ROM Checksum
		dos.writeInt(Integer.reverseBytes(3)); // Unit Code
		dos.writeInt(Integer.reverseBytes(0)); // save state offset (unused)
		dos.writeInt(Integer.reverseBytes(0x100)); // input data offset
	}
	
	public static State loadMovie(String filename) {
		try {
			String path = "movies/" + filename;
			DataInputStream dis = new DataInputStream(new FileInputStream(path));
			
			dis.skip(0x3C); // skip to input offset
			int inputAddress = Integer.reverseBytes(dis.readInt());
			dis.skip(inputAddress - 0x40);
			
			State.root.restore();
			
			try{
				while(true) {
					int input = Short.reverseBytes(dis.readShort()) & 0xFF;
					State.step(input);
				}
			} catch(EOFException e) {}
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new State();
	}
}
