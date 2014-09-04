package mrwint.gbtasgen.tools.updateAssembly;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import mrwint.gbtasgen.tools.deasm.ROM;


public class FixBankswitchs {
	
	private ROM rom;
	
	public FixBankswitchs(ROM rom) {
		this.rom = rom;
	}


	public void writeAssembly(String fileName, String baseFileName) throws Throwable {
		Scanner sc = new Scanner(new File(baseFileName));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
		
		int progress = 0;
		int aNum = -1;
		int hlNum = -1;
		boolean hlFirst = false;
		
		String aLine = null;
		String hlLine = null;
		while(sc.hasNextLine()) {
			String line = sc.nextLine();

			if(progress == 0) {
				if(line.startsWith("\tld a,")) {
					String ll = line.substring("\tld a,".length()).trim();
					int ei = ll.length();
					if(ll.contains(" "))
						ei = Math.min(ei, ll.indexOf(" "));
					if(ll.contains(";"))
						ei = Math.min(ei, ll.indexOf(";"));
					if(ll.contains("\t"))
						ei = Math.min(ei, ll.indexOf("\t"));
					if(!ll.isEmpty() && ll.startsWith("$")) {
						aNum = Integer.valueOf(ll.substring(1,ei),16);
						aLine = line;
						progress = 1;
						hlFirst = false;
						continue;
					}
					if(!ll.isEmpty() && ll.charAt(0) >= '0' && ll.charAt(0) <= '9') {
						aNum = Integer.valueOf(ll.substring(0,ei),10);
						aLine = line;
						progress = 1;
						hlFirst = false;
						continue;
					}
				}
				if(line.startsWith("\tld hl,")) {
					String ll = line.substring("\tld hl,".length()).trim();
					int ei = ll.length();
					if(ll.contains(" "))
						ei = Math.min(ei, ll.indexOf(" "));
					if(ll.contains(";"))
						ei = Math.min(ei, ll.indexOf(";"));
					if(ll.contains("\t"))
						ei = Math.min(ei, ll.indexOf("\t"));
					if(!ll.isEmpty() && ll.startsWith("$")) {
						hlNum = Integer.valueOf(ll.substring(1,ei),16);
						hlLine = line;
						progress = 2;
						hlFirst = true;
						continue;
					}
					if(!ll.isEmpty() && ll.charAt(0) >= '0' && ll.charAt(0) <= '9') {
						hlNum = Integer.valueOf(ll.substring(0,ei),10);
						hlLine = line;
						progress = 2;
						hlFirst = true;
						continue;
					}
				}
				bw.write(line + "\n");
				continue;
			} else if(progress == 1) {
				if(line.startsWith("\tld hl,")) {
					String ll = line.substring("\tld hl,".length()).trim();
					int ei = ll.length();
					if(ll.contains(" "))
						ei = Math.min(ei, ll.indexOf(" "));
					if(ll.contains(";"))
						ei = Math.min(ei, ll.indexOf(";"));
					if(ll.contains("\t"))
						ei = Math.min(ei, ll.indexOf("\t"));
					if(!ll.isEmpty() && ll.startsWith("$")) {
						hlNum = Integer.valueOf(ll.substring(1,ei),16);
						hlLine = line;
						progress = 3;
						continue;
					}
					if(!ll.isEmpty() && ll.charAt(0) >= '0' && ll.charAt(0) <= '9') {
						hlNum = Integer.valueOf(ll.substring(0,ei),10);
						hlLine = line;
						progress = 3;
						continue;
					}
				}
				bw.write(aLine + "\n");
				bw.write(line + "\n");
				progress = 0;
				continue;
			} else if(progress == 2) {
				if(line.startsWith("\tld a,")) {
					String ll = line.substring("\tld a,".length()).trim();
					int ei = ll.length();
					if(ll.contains(" "))
						ei = Math.min(ei, ll.indexOf(" "));
					if(ll.contains(";"))
						ei = Math.min(ei, ll.indexOf(";"));
					if(ll.contains("\t"))
						ei = Math.min(ei, ll.indexOf("\t"));
					if(!ll.isEmpty() && ll.startsWith("$")) {
						aNum = Integer.valueOf(ll.substring(1,ei),16);
						aLine = line;
						progress = 3;
						continue;
					}
					if(!ll.isEmpty() && ll.charAt(0) >= '0' && ll.charAt(0) <= '9') {
						aNum = Integer.valueOf(ll.substring(0,ei),10);
						aLine = line;
						progress = 3;
						continue;
					}
				}
				bw.write(hlLine + "\n");
				bw.write(line + "\n");
				progress = 0;
				continue;
			} else {
				String callStr = null;
				if(line.startsWith("\trst FarCall"))
					callStr = "\trst FarCall";
				if(line.startsWith("\trst $8"))
					callStr = "\trst $8";
				if(callStr != null) {
					String ll = line.substring(callStr.length());
					if(ll.isEmpty() || ll.startsWith(" ") || ll.startsWith(";")) {
						int add = hlNum;
						if(add >= 0x8000) {
							System.out.println("found illegal FarCall to "+Integer.toHexString(add));
							System.exit(1);
						} else if(add >= 0x4000) {
							add += (aNum-1)*0x4000;
							rom.labelType[add] = 3;
							rom.type[add] = ROM.CODE;
							String label = rom.getLabel(add);
							if(hlFirst)
								bw.write("\tcallab "+label+"\n");
							else
								bw.write("\tcallba "+label+"\n");
							totalLinesOfAssemblyWritten++;
							progress = 0;
							continue;
						}
					}
				}
				if(hlFirst)
					bw.write(hlLine + "\n");
				bw.write(aLine + "\n");
				if(!hlFirst)
					bw.write(hlLine + "\n");
				bw.write(line + "\n");
				progress = 0;
				continue;
			}
		}
		sc.close();
		bw.close();
		System.out.println("rewrote "+totalLinesOfAssemblyWritten+" assembly instructions");
	}
	
	private int totalLinesOfAssemblyWritten = 0;
}
