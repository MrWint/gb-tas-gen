package mrwint.gbtasgen.tools.updateAssembly;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import mrwint.gbtasgen.tools.deasm.ROM;


public class FixCallLabels {
	
	private ROM rom;
	
	public FixCallLabels(ROM rom) {
		this.rom = rom;
	}

	public void writeAssembly(String fileName, String baseFileName) throws Throwable {
		Scanner sc = new Scanner(new File(baseFileName));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
		
		int curbank = 0;
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			
			if(line.startsWith("SECTION \"bank")) {
				int off = "SECTION \"bank".length();
				String numString = line.substring(off);
				numString = numString.substring(0,numString.indexOf("\""));
				curbank = Integer.valueOf(numString,16);
				System.out.println("found section of bank "+Integer.toHexString(curbank));
				bw.write(line + "\n");
				continue;
			}
			
			String[] patterns = {
					"jp \\$",
					"jp c, \\$",
					"jp nc, \\$",
					"jp z, \\$",
					"jp nz, \\$",
					"call \\$",
					"call c, \\$",
					"call nc, \\$",
					"call z, \\$",
					"call nz, \\$",
					"jp c,\\$",
					"jp nc,\\$",
					"jp z,\\$",
					"jp nz,\\$",
					"call c,\\$",
					"call nc,\\$",
					"call z,\\$",
					"call nz,\\$",
			};
			String[] parts = null;
			String pattern = null;
			
			for(String _pattern : patterns) {
				parts = line.split(_pattern);
				if(parts.length == 2) {
					pattern = _pattern;
					break;
				}
			}
			if(pattern == null) {
				bw.write(line + "\n");
				continue;
			}

			int num = Integer.valueOf(parts[1].substring(0,4),16);
			int add = num;
			if(add >= 0x8000) {
				System.out.println("found illegal call to "+Integer.toHexString(add));
				bw.write(line + "\n");
				continue;
			} else if(add >= 0x4000) {
				if(curbank == 0) {
					System.out.println("found call to unknown bank address "+Integer.toHexString(add));
					bw.write(line + "\n");
					continue;
				}
				add += (curbank-1)*0x4000;
			}
			rom.labelType[add] = 3;
			rom.type[add] = ROM.CODE;
			String label = rom.getLabel(add);
			bw.write(parts[0]);
			bw.write(pattern.substring(0,pattern.length()-2));
			bw.write(label);
			bw.write(parts[1].substring(4));
			bw.write("\n");
			totalLinesOfAssemblyWritten++;
		}
		sc.close();
		bw.close();
		System.out.println("rewrote "+totalLinesOfAssemblyWritten+" assembly instructions");
	}
	
	private int totalLinesOfAssemblyWritten = 0;
}
