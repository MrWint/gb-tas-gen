package mrwint.gbtasgen.tools.deasm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Scanner;

public class AssemblyWriter {

	public ROM rom;

	public AssemblyWriter(ROM rom) {
		this.rom = rom;
	}

	public void writeAssembly(String fileName) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));

			int curAddress = 0;

			if (rom.includeFiles.size() > 0) {
				for (String include : rom.includeFiles) {
					bw.write("INCLUDE \""+include+"\"\n");
				}
				bw.write("\n");
			}

			while(curAddress < rom.len) {
				int curBank = curAddress / 0x4000;
				int curBankOffset = curAddress % 0x4000;
				String name = "bank"+Integer.toHexString(curBank);
				if (rom.section[curAddress] != null)
					name = rom.section[curAddress];
				if(curBank == 0) {
					bw.write("SECTION \""+name+"\",ROM0[$"+Integer.toHexString(curBankOffset)+"]\n");
				} else
					bw.write("SECTION \""+name+"\",ROMX,BANK[$"+Integer.toHexString(curBank)+"]\n");
				int bankEndAddress = Math.min((curBank+1)*0x4000, rom.len);
				for (int i = curAddress+1; i < bankEndAddress; i++)
					if (rom.section[i] != null)
						bankEndAddress = i;
				curAddress = writeAssembly(bw,curAddress,bankEndAddress);
//				curAddress = bankEndAddress;
			}

			bw.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public int writeAssembly(BufferedWriter bw, int start, int end) throws Throwable {
		int curAddress = start;
		while(curAddress < end) {
			int startAddress = curAddress;
			boolean all0s = true;
			while(curAddress < end && rom.type[curAddress] <= ROM.UNKNOWN) {
				all0s &= (rom.data[curAddress] == 0 || rom.type[curAddress] == ROM.IGNORE);
				curAddress++;
			}
			if(curAddress > startAddress) {
				if (curAddress >= end && all0s)
					break;
				writeIncBin(bw,startAddress,curAddress);
			}
			if(curAddress >= end)
				break;
			if(rom.type[curAddress] == ROM.CODE) {
				curAddress = writeCode(bw, curAddress);
				if (rom.type[curAddress] != ROM.CODE)
					bw.write("; "+prettyPrintAddress(curAddress)+"\n\n");
			} else if(rom.type[curAddress] == ROM.DATA_JUMPPOINTER) {
				curAddress = writeJumpPointer(bw, curAddress, end);
			} else if(rom.type[curAddress] == ROM.DATA_BYTEARRAY) {
				curAddress = writeByteArray(bw, curAddress, end);
			} else
				System.err.println("writing unknown section type "+rom.type[curAddress]+" at "+(curAddress++));
		}
		return curAddress;
	}

	public void addAssembly(String fileName, String baseFileName) throws Throwable {
		Scanner sc = new Scanner(new File(baseFileName));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));

		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			// example line: INCBIN "baserom.gbc",$13074,$13095 - $13074
			String prefix = "INCBIN \"baserom.gbc\"";
			if(!line.startsWith(prefix)) {
				bw.write(line + "\n");
				continue;
			}
			String[] parts = line.split(",");
			if(parts.length != 3) {
				System.out.println("line \""+line+"\" has invalid separations");
				bw.write(line + "\n");
				continue;
			}
			int start = evalNumber(parts[1]);
			int len = evalNumber(parts[2]);
			if(start == -1 || len == -1) {
				System.out.println("line \""+line+"\" has invalid range");
				bw.write(line + "\n");
				continue;
			}
			writeAssembly(bw,start,start+len);
		}
		sc.close();
		bw.close();
	}

	private int writeJumpPointer(BufferedWriter bw, int curAddress, int end) throws Throwable {

		for(;rom.type[curAddress] == ROM.DATA_JUMPPOINTER && curAddress < end;curAddress+=2) {
			addLabel(bw, curAddress);

			String dataString = "$"+Integer.toHexString(((rom.data[curAddress]&0xFF) | ((rom.data[curAddress+1]&0xFF) << 8)) & 0xFFFF);

			if(rom.payloadAsAddress[curAddress] >= 0)
				dataString = rom.getLabel(rom.payloadAsAddress[curAddress]);

			if(rom.comment[curAddress] != null && !rom.comment[curAddress].isEmpty())
				dataString += " ; "+rom.comment[curAddress];

			bw.write("\tdw "+dataString+"\n");
		}
		if (rom.type[curAddress] != ROM.DATA_BYTEARRAY)
			bw.write("\n");
		return curAddress;
	}

	private int writeByteArray(BufferedWriter bw, int curAddress, int end) throws Throwable {
		for(; rom.type[curAddress] == ROM.DATA_BYTEARRAY && curAddress < end; curAddress += rom.width[curAddress]) {
			addLabel(bw, curAddress);

			String dataString = "";
			for (int i = 0; i < rom.width[curAddress]; i++) {
				if (i > 0)
					dataString += ", ";
				dataString += Util.format(rom.data[curAddress+i]&0xFF, rom.format[curAddress+i]);
			}

			if(rom.comment[curAddress] != null && !rom.comment[curAddress].isEmpty())
				dataString += " ; "+rom.comment[curAddress];

			bw.write("\tdb "+dataString+"\n");
		}
		if (rom.type[curAddress] != ROM.DATA_JUMPPOINTER)
			bw.write("\n");
		return curAddress;
	}

	public static String prettyPrintAddress(int add) {
		return addToHex(add)+" ("+Integer.toHexString(add/0x4000)+":"+addToHex(add<0x4000 ? add : add%0x4000 + 0x4000)+")";
	}

	public static String addToHex(int add) {
		String ret = Integer.toHexString(add);
		while(ret.length() < 4)
			ret = "0"+ret;
		return ret;
	}

	private int writeCode(BufferedWriter bw, int address) throws Throwable {

		addLabel(bw, address);

		int curAddress = address;
		int opCodeValue = rom.data[curAddress++] & 0xFF;
		int opData = 0;
		OpCode opCode;

		// fetch OpCode
		if(opCodeValue == 0xCB) {
			opCodeValue = rom.data[curAddress++] & 0xFF;
			opCode = OpCode.opCodesCB[opCodeValue];
		}
		else
			opCode = OpCode.opCodes[opCodeValue];

		// fetch optional payload data
		for(int i=0;i<opCode.extraBytes; i++)
			opData += (rom.data[curAddress++] & 0xFF) << (i << 3); // * 2^(8*i) (little endian)

		int opDataAsAddress = Util.toFull(opData, Util.getBank(address));

		String name = opCode.name;

		String comment = "";

		if(name.contains("?")) {
			String tmp = name.substring(0,name.indexOf("?")).toLowerCase();
			if(rom.payloadAsAddress[address] >= 0) {
				tmp += rom.getLabel(rom.payloadAsAddress[address]);
			} else if(opData >= 0xc000 && !rom.ramLabel[opData-0xc000].isEmpty()) { // add ram labels here
				tmp += rom.ramLabel[opData-0xc000].get(0);
				comment = generateRamLabelComment("$" + Integer.toHexString(opData),opData-0xc000);
			} else if(opDataAsAddress > 0x100 && opDataAsAddress < rom.len && (rom.label[opDataAsAddress] != null || rom.labelType[opDataAsAddress] != ROM.LABEL_NONE)) { // add rom labels here
				tmp += rom.getLabel(opDataAsAddress);
				comment = "$" + Integer.toHexString(opData).toLowerCase();
			} else
				tmp += "$" + Integer.toHexString(opData).toLowerCase();
			tmp += name.substring(name.indexOf("?")+1).toLowerCase();
			name = tmp;
		} else if(name.contains("$FF00+x")) {
			String tmp = name.substring(0,name.indexOf("$FF00+x")).toLowerCase();
			if(!rom.ramLabel[opData+0x3f00].isEmpty()) { // add ram labels here
				tmp += rom.ramLabel[opData+0x3f00].get(0);
				comment = generateRamLabelComment("$FF00+$" + Integer.toHexString(opData),opData+0x3f00);
			} else
				tmp += "$FF00+$" + Integer.toHexString(opData).toLowerCase();
			tmp += name.substring(name.indexOf("x")+1).toLowerCase();
			name = tmp;
		} else if(name.contains("x")) {
			String tmp = name.substring(0,name.indexOf("x")).toLowerCase();
			if(rom.payloadAsAddress[address] >= 0) {
				tmp += rom.getLabel(rom.payloadAsAddress[address]);
			} else if(rom.payloadAsBank[address] >= 0) {
				tmp += "BANK("+rom.getLabel(rom.payloadAsBank[address])+")";
			} else
				tmp += "$" + Integer.toHexString(opData).toLowerCase();
			tmp += name.substring(name.indexOf("x")+1).toLowerCase();
			name = tmp;
		} else
			name = name.toLowerCase();
		if(rom.comment[address] != null && !rom.comment[address].isEmpty())
			comment += " ; "+rom.comment[address];
		if(rom.indirectJumpTo[address] >= 0)
			comment += " ; indirect jump to "+rom.getLabel(rom.indirectJumpTo[address])+" ("+prettyPrintAddress(rom.indirectJumpTo[address])+")";
		if(!comment.isEmpty())
			name += " ; " + comment;
		bw.write("\t"+name+"\n");
		return curAddress;
	}

	private void addLabel(BufferedWriter bw, int address) throws IOException {
		// add label
		if(rom.label[address] != null || rom.labelType[address] != 0) {
			String label = rom.getLabel(address);
			if(rom.labelType[address] > 1)
				label += ": ; "+prettyPrintAddress(address);
			if(rom.labelType[address] > 2) {
				//if(addNewLine)
					bw.write("\n");
				if(rom.accesses[address] == null)
					bw.write("; no known jump sources\n");
				else {
					Iterator<Integer> ii = rom.accesses[address].iterator();
					bw.write("; known jump sources: "+prettyPrintAddress(ii.next()));
					while(ii.hasNext())
						bw.write(", "+prettyPrintAddress(ii.next()));
					bw.write("\n");
				}
			}
			bw.write(label + "\n");
		}
	}

	private String generateRamLabelComment(String addressString, int ramAddress) {
		String comment = addressString.toLowerCase();
		if(rom.ramLabel[ramAddress].size() > 1) {
			comment += " (aliases: " + rom.ramLabel[ramAddress].get(1);
			for(int i=2;i<rom.ramLabel[ramAddress].size();i++)
				comment += ", " + rom.ramLabel[ramAddress].get(i);
			comment += ")";
		}
		return comment;
	}

	private void writeIncBin(BufferedWriter bw, int start, int end) throws Throwable {
		bw.write("INCBIN \""+rom.filename+"\",$");
		bw.write(Integer.toHexString(start));
		bw.write(",$");
		bw.write(Integer.toHexString(end));
		bw.write(" - $");
		bw.write(Integer.toHexString(start));
		bw.write("\n");
	}


	public static int evalNumber(String s) {
		if(s.indexOf(";") != -1)
			return evalNumber(s.substring(0, s.indexOf(";")));
		if(s.indexOf("+") != -1) {
			int val1 = evalNumber(s.substring(0, s.indexOf("+")));
			int val2 = evalNumber(s.substring(s.indexOf("+")+1));
			if(val1 == -1 || val2 == -1)
				return -1;
			return val1 + val2;
		}
		if(s.lastIndexOf("-") != -1) {
			int val1 = evalNumber(s.substring(0, s.lastIndexOf("-")));
			int val2 = evalNumber(s.substring(s.lastIndexOf("-")+1));
			if(val1 == -1 || val2 == -1)
				return -1;
			return val1 - val2;
		}
		if(s.indexOf("*") != -1) {
			int val1 = evalNumber(s.substring(0, s.indexOf("*")));
			int val2 = evalNumber(s.substring(s.indexOf("*")+1));
			if(val1 == -1 || val2 == -1)
				return -1;
			return val1 * val2;
		}
		if(s.lastIndexOf("/") != -1) {
			int val1 = evalNumber(s.substring(0, s.lastIndexOf("/")));
			int val2 = evalNumber(s.substring(s.lastIndexOf("/")+1));
			if(val1 == -1 || val2 == -1)
				return -1;
			return val1 / val2;
		}
		s = s.trim();
		boolean hex=false;
		if(s.startsWith("$")) {
			hex = true;
			s = s.substring(1);
		}
		if(s.startsWith("0x")) {
			hex = true;
			s = s.substring(2);
		}
		if(s.length() == 0)
			return -1;
		try {
			return Integer.valueOf(s, hex?16:10);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
