package mrwint.gbtasgen.movie;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.State.InputNode;
import mrwint.gbtasgen.util.Util;

public class BizhawkMovie {
	public static void saveMovie(State s, String filename) {
		try {
			String path = "movies/" + filename + RomInfo.rom.fileNameSuffix + ".bkm";
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			
			writeHeader(s,out);
			writeInputs(s,out);
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeInputs(State s, PrintWriter out) throws IOException {
		List<Integer> inputs = new ArrayList<Integer>();
		InputNode curNode = s.inputs;
		while(curNode != null) {
			inputs.add(curNode.input);
			curNode = curNode.prev;
		}
		if(inputs.size() != s.stepCount)
			System.err.println("WARNING: input len ("+inputs.size()+") does not match step count ("+s.stepCount+")");
		for(int i = inputs.size()-1; i >= 0 ; i--)
			writeInput(inputs.get(i).intValue(),out);
	}
	
	private static void writeInput(int input, PrintWriter out) throws IOException {
		out.print("|.|");
		out.print((input & Move.UP) != 0 ? "U" : ".");
		out.print((input & Move.DOWN) != 0 ? "D" : ".");
		out.print((input & Move.LEFT) != 0 ? "L" : ".");
		out.print((input & Move.RIGHT) != 0 ? "R" : ".");
		out.print((input & Move.SELECT) != 0 ? "s" : ".");
		out.print((input & Move.START) != 0 ? "S" : ".");
		out.print((input & Move.B) != 0 ? "B" : ".");
		out.print((input & Move.A) != 0 ? "A" : ".");
		out.println("|");
	}

	private static void writeHeader(State s, PrintWriter out) throws IOException {
		out.println("emuVersion Version 1.5.3");
		out.println("MovieVersion BizHawk v0.0.1");
		out.println("Platform GBC");
		out.println("GameName "+RomInfo.rom.romName);
		out.println("Author MrWint");
		out.println("rerecordCount "+State.rerecordCount);
		out.println("GUID 552d8b87-2740-497f-b946-b49f2623599d");
		out.println("SHA1 "+RomInfo.rom.romSHA1);
		out.println("Force_DMG_Mode False");
		out.println("GBA_In_CGB False");
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
					Thread.sleep(1);
					//System.out.println(Util.toHex(Gb.readMemory(0xffe1),2)+" "+Util.toHex(Gb.readMemory(0xffe2),2)+" : "+Util.toHex(Gb.readMemory(0xd464),2));
					System.out.println(Util.toHex(Gb.readMemory(0xff91),2)+" "+Util.toHex(Gb.readMemory(0xff90),2)+" "+Util.toHex(Gb.readMemory(0xff8f),2)+" "+Util.toHex(Gb.readMemory(0xff8e),2));
				}
			} catch(EOFException | InterruptedException e) {}
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new State();
	}
}
