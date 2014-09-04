package mrwint.gbtasgen.tools.updateAssembly;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import mrwint.gbtasgen.tools.deasm.ROM;


public class FixBankswitchs2 {
	
	private ROM rom;
	
	public FixBankswitchs2(ROM rom) {
		this.rom = rom;
	}


	public void writeAssembly(String fileName, String baseFileName) throws Throwable {
		Scanner sc = new Scanner(new File(baseFileName));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
		
		int progress = 0;
		String aStr = "";
		String hlStr = "";
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
					if(!ll.isEmpty() && !ll.startsWith("$")) {
						aStr = ll.substring(0,ei);
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
					if(!ll.isEmpty() && !ll.startsWith("$")) {
						hlStr = ll.substring(0,ei);
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
					if(!ll.isEmpty() && !ll.startsWith("$")) {
						hlStr = ll.substring(0,ei);
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
					if(!ll.isEmpty() && !ll.startsWith("$")) {
						aStr = ll.substring(0,ei);
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
						if(aStr.equalsIgnoreCase("BANK("+hlStr+")")) {
							if(hlFirst)
								bw.write("\tcallab "+hlStr+"\n");
							else
								bw.write("\tcallba "+hlStr+"\n");
							totalLinesOfAssemblyWritten++;
							progress = 0;
							continue;
						} else
							System.err.println("invalid combination "+aStr+" "+hlStr);
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
