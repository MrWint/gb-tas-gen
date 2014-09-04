package mrwint.gbtasgen.tools.updateAssembly;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import mrwint.gbtasgen.tools.deasm.AssemblyWriter;
import mrwint.gbtasgen.tools.deasm.ROM;


public class FixROMDataPointers {
	
	private ROM rom;
	
	public FixROMDataPointers(ROM rom) {
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
					"\tld a, [$",
					"\tld bc, $",
					"\tld de, $",
					"\tld hl, $",
					"\tld sp, $",

					"\tld a,[$",
					"\tld bc,$",
					"\tld de,$",
					"\tld hl,$",
					"\tld sp,$",
			};
			String prefix = null;
			String postfix = null;
			String comment = "";
			String ll = null;
			
			for(String pattern : patterns) {
				if(!line.startsWith(pattern))
					continue;
				prefix = pattern.substring(0,pattern.length()-1);
				break;
			}
			if(prefix == null) {
				bw.write(line + "\n");
				continue;
			}
			ll = line.substring(prefix.length());
			if(ll.startsWith("$ff00+c")) {
				bw.write(line + "\n");
				continue;
			}
			//System.out.println(line+" | "+ll);
			int numEndIdx = ll.length();
			if(ll.contains("]"))
				numEndIdx = Math.min(numEndIdx, ll.indexOf("]"));
			if(ll.contains(" "))
				numEndIdx = Math.min(numEndIdx, ll.indexOf(" "));
			if(ll.contains(";"))
				numEndIdx = Math.min(numEndIdx, ll.indexOf(";"));
			int num = AssemblyWriter.evalNumber(ll.substring(0,numEndIdx));
			ll = ll.substring(numEndIdx);
			int commentIdx = ll.indexOf(";");
			if(commentIdx == -1)
				postfix = ll;
			else {
				postfix = ll.substring(0, commentIdx);
				comment = ll.substring(commentIdx+1);
			}
			if(num < 0x4000 || num >= 0x8000) {
				bw.write(line + "\n");
				continue;
			}
			int add = num;
			if(curbank == 0) {
				System.out.println("found reference to unknown bank address "+Integer.toHexString(add)+" in line "+line);
				bw.write(line + "\n");
				continue;
			}
			add += (curbank-1)*0x4000;
			
			String label;
			if(rom.labelType[add] == 0) {
				rom.labelType[add] = 3;
				label = rom.getLabel(add);
				newLabels.put(Integer.valueOf(add), label);
			} else
				label = rom.getLabel(add);

			
			bw.write(prefix+label+postfix+" ; "+"$"+Integer.toHexString(num));
			if(!comment.isEmpty())
				bw.write(" ;"+comment);
			bw.write("\n");
			totalLinesOfAssemblyWritten++;
		}
		sc.close();
		bw.close();
		System.out.println("rewrote "+totalLinesOfAssemblyWritten+" assembly instructions and discovered "+newLabels.size()+" new labels");
	}
	
	public Map<Integer, String> newLabels = new TreeMap<Integer, String>();

	public void writeAssembly2(String fileName, String baseFileName) throws Throwable {
		Scanner sc = new Scanner(new File(baseFileName));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));

		totalLinesOfAssemblyWritten = 0;
		
		Iterator<Entry<Integer, String>> it = newLabels.entrySet().iterator();
		Entry<Integer, String> cur = (it.hasNext() ? it.next() : null);
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			
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
			int start = AssemblyWriter.evalNumber(parts[1]);
			int len = AssemblyWriter.evalNumber(parts[2]);
			int end = start+len;
			if(start == -1 || len == -1) {
				System.out.println("line \""+line+"\" has invalid range");
				bw.write(line + "\n");
				continue;
			}
			while(cur != null && cur.getKey() < start)
				cur = (it.hasNext() ? it.next() : null);
			while(cur != null && cur.getKey() < end) {
				if(writeIncBin(bw,start,cur.getKey()))
					bw.write("\n");
				bw.write(cur.getValue()+": ; "+AssemblyWriter.prettyPrintAddress(cur.getKey())+"\n");
				start = cur.getKey();
				cur = (it.hasNext() ? it.next() : null);
				totalLinesOfAssemblyWritten++;
			}
			writeIncBin(bw,start,end);
		}
		sc.close();
		bw.close();
		System.out.println("wrote "+totalLinesOfAssemblyWritten+" new labels");
	}

	private boolean writeIncBin(BufferedWriter bw, int start, int end) throws Throwable {
		if(start == end)
			return false;
		bw.write("INCBIN \"baserom.gbc\",$");
		bw.write(Integer.toHexString(start));
		bw.write(",$");
		bw.write(Integer.toHexString(end));
		bw.write(" - $");
		bw.write(Integer.toHexString(start));
		bw.write("\n");
		return true;
	}

	private int totalLinesOfAssemblyWritten = 0;
}
