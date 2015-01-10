package mrwint.gbtasgen.tools.deasm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import mrwint.gbtasgen.util.Util;

public class ROM {

	public static final byte IGNORE = -2;
	public static final byte UNKNOWN = -1;
	public static final byte CODE = 0;
	public static final byte DATA_JUMPPOINTER = 1;
  public static final byte DATA_BYTEARRAY = 2;
  public static final byte DATA_BANKJUMPPOINTER = 3;

	public static final int LABEL_NONE = 0;
	public static final int LABEL_RELATIVE = 1;
	public static final int LABEL_ABSOLUTE = 2;
	public static final int LABEL_FUNCTION = 3;

	public static final int FORMAT_HEX = 0;
	public static final int FORMAT_DEC = 1;
	public static final int FORMAT_BIN = 2;
	public static final int FORMAT_BUTTONS = 3;

	public int len;
	public String filename;
	public byte[] data;
  public boolean[] reachable;
	public String[] section;
	public byte[] type;
	public Set<Integer>[] accesses;
	public String[] label;
	public int[] labelType;
	public int[] format;
	public int[] width;
	public String[] comment;
	public int[] payloadAsAddress;
	public int[] payloadAsBank;
	public int[] indirectJumpTo;

	public ArrayList<String> includeFiles;

	public ArrayList<String>[] ramLabel;

	@SuppressWarnings("unchecked")
	public ROM(String filename) {
		this.filename = filename;
		File f = new File(filename);
		len = (int)f.length();
		data = new byte[len];
		reachable = new boolean[len];
		section = new String[len];
		type = new byte[len];
		accesses = new Set[len];
		label = new String[len];
		labelType = new int[len];
		format = new int[len];
		width = new int[len];
		comment = new String[len];
		payloadAsAddress = new int[len];
		payloadAsBank = new int[len];
		indirectJumpTo = new int[len];
		ramLabel = new ArrayList[0x8000];

		includeFiles = new ArrayList<>();

		for(int i=0;i<len;i++) {
			payloadAsAddress[i] = -1;
			payloadAsBank[i] = -1;
			indirectJumpTo[i] = -1;
			labelType[i] = LABEL_NONE;
			type[i] = UNKNOWN;
			format[i] = FORMAT_HEX;
			width[i] = 1;
			reachable[i] = false;
		}
		for(int i=0;i<0x8000;i++)
			ramLabel[i] = new ArrayList<String>();

		InputStream is;
		try {
			is = new FileInputStream(filename);
			is.read(data);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addAccess(int fromAddress, int toAddress) {
		if(accesses[toAddress] == null)
			accesses[toAddress] = new TreeSet<Integer>();
		accesses[toAddress].add(fromAddress);
	}

	public String getLabel(int i) {
		if(label[i] != null)
			return label[i];

		if(type[i] == CODE) {
			if(labelType[i] == LABEL_RELATIVE)
				return (reachable[i] ? ".asm_" : ".unused_")+Util.toHex(i, 4);
			if(labelType[i] == LABEL_ABSOLUTE)
				return (reachable[i] ? "asm_" : "unused")+Util.toHex(i, 4);
			if(labelType[i] == LABEL_FUNCTION)
				return (reachable[i] ? "Func" : "Unused")+Util.toHex(i, 4);
		} else if(type[i] == DATA_JUMPPOINTER || type[i] == DATA_BANKJUMPPOINTER) {
			if(labelType[i] == LABEL_RELATIVE)
				return ".jumptable_"+Util.toHex(i, 4);
			if(labelType[i] >= LABEL_ABSOLUTE)
				return "Jumptable_"+Util.toHex(i, 4);
		} else {
			if(labelType[i] == LABEL_RELATIVE)
				return ".unknown_"+Util.toHex(i, 4);
			if(labelType[i] >= LABEL_ABSOLUTE)
				return "Unknown_"+Util.toHex(i, 4);
		}
		System.err.println("writing label of invalid type "+labelType[i]+" at "+Integer.toHexString(i));
		return "#ERROR#";
	}

	// make relative labels absolute if there are any absolute labels between them
	public void fixLabelTypes() {

		for(int i=0;i<len;i++) // set all external labels as explicit
			if(label[i] != null)
				labelType[i] = Math.max(labelType[i], label[i].startsWith(".")
						? LABEL_RELATIVE
						: Character.isLowerCase(label[i].charAt(0)) ? LABEL_ABSOLUTE : LABEL_FUNCTION);

		boolean changed;
		int numChanged = 0;
		do {
			changed = false;
			for(int i=0;i<len;i++) {
				if(payloadAsAddress[i] != -1) {
					int payloadAddress = payloadAsAddress[i];
					if(labelType[payloadAddress] == LABEL_RELATIVE) {
						for(int j=Math.min(payloadAddress,i); j<=Math.max(payloadAddress,i); j++) {
							if(labelType[j] > LABEL_RELATIVE) {
								labelType[payloadAddress] = LABEL_ABSOLUTE;
								changed = true;
								numChanged++;
								break;
							}
						}
					}
				}
			}
		} while(changed);
		System.out.println("changed "+numChanged+" labels");
	}


	public void addSymFile(String fileName) throws Throwable {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ASCII"));
		int numRead = 0;
		int numInserted = 0;
		String line;
		while(true) {
			line = br.readLine();
			if(line == null)
				break;
			if(line.contains(";"))
				line = line.substring(0,line.indexOf(";")); // cut off comments
			if(line.trim().isEmpty())
				continue;
			numRead++;
			String add = line.substring(0, line.indexOf(" "));
			String name = line.substring(line.indexOf(" ")+1);
			int bank = Integer.valueOf(add.substring(0,add.indexOf(":")), 16);
			int address = Integer.valueOf(add.substring(add.indexOf(":")+1), 16);
			if(name.contains("."))
				name = name.substring(name.lastIndexOf("."));
			if(name.startsWith(".asm"))
				continue;
			if(address >= 0x8000) {
				ramLabel[address-0x8000].add(name);
				continue;
			}
			if(address >= 0x8000)
				continue;
			if(address >= 0x4000)
				address += (bank-1)*0x4000;
			label[address] = name;
			numInserted++;
		}
		br.close();
		System.out.println("inserted "+numInserted+" of "+numRead+" labels");
	}

  public void addIncludeFile(String fileName) throws Throwable {
    includeFiles.add(fileName);
  }

	public void addEquFile(String fileName) throws Throwable {
		includeFiles.add(fileName);

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ASCII"));
		int numRead = 0;
		int numInserted = 0;
		String line;
		while(true) {
			line = br.readLine();
			if(line == null)
				break;
			int additionalEqus = 0;
			if(line.contains(";")) {
				String comment = line.substring(line.indexOf(";")+1).trim();
				line = line.substring(0,line.indexOf(";")); // cut off comments
				if (comment.startsWith("+"))
					additionalEqus = Integer.valueOf(comment.substring(1));
			}
			line = line.replace('\t', ' ');
			while(!line.equals(line.replace("  ", " ")))
				line = line.replace("  ", " ");
			line = line.trim();
			if(line.isEmpty())
				continue;
			numRead++;
			String[] ss = line.split(" ");
			if(ss.length != 3)
				System.err.println("invalid line ("+ss.length+") "+line);
			if(!ss[1].equals("EQU"))
				System.err.println("invalid line (no EQU) "+line);
			String name = ss[0];
			int address;
			if(ss[2].charAt(0) == '$')
				address = Integer.valueOf(ss[2].substring(1),16);
			else if(ss[2].charAt(0) == '%')
				address = Integer.valueOf(ss[2].substring(1),2);
			else
				address = Integer.valueOf(ss[2],10);
			if(address >= 0x8000) {
				ramLabel[address-0x8000].add(name);
				numInserted++;
				for (int i = 1; i <= additionalEqus ; i++) {
					ramLabel[address-0x8000+i].add(name + "+" + i);
					numInserted++;
				}
			}
		}
		br.close();
		System.out.println("inserted "+numInserted+" of "+numRead+" EQUs");
	}
}
