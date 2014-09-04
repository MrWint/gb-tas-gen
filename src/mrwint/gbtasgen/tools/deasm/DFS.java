package mrwint.gbtasgen.tools.deasm;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import mrwint.gbtasgen.tools.deasm.specialCallHandler.SpecialCallHandler;


public class DFS {
	
	public static class DFSStackElem {
		public int address;
		public CPUState s;
		
		public DFSStackElem(int address, CPUState s) {
			this.address = address;
			this.s = s;
		}
	}
	
	public ROM rom;
	public Stack<DFSStackElem> dfsStack;
	public SpecialCallHandler sch;
	
	public DFS(ROM rom, SpecialCallHandler sch) {
		this.rom = rom;
		this.sch = sch;
		this.sch.init(this);
		this.dfsStack = new Stack<DFSStackElem>();
	}

	public void dfs(int... entries) {
		
		sch.handleDFSInit();
		
		for(int entry : entries)
			dfsStack.push(new DFSStackElem(entry, new CPUState()));
		
		while(!dfsStack.isEmpty()) {
			DFSStackElem e = dfsStack.pop();
			visit(e.address,e.s);
		}
	}
	
	public void visit(int address, CPUState s) {
		
		//System.out.println("visit "+Integer.toHexString(address));
		
		if(rom.type[address] != ROM.UNKNOWN)
			return;
		rom.type[address] = ROM.CODE;
		sch.handleBeforeOp(address,s);
		//rom.comment[address] = s.getCPUStateInfo();
		
		int nextAddress = address;
		int opCodeValue = rom.data[nextAddress++] & 0xFF;
		int opData = 0;
		OpCode opCode;
		
		// fetch OpCode
		if(opCodeValue == 0xCB) {
			opData = rom.data[nextAddress++] & 0xFF;
			opCode = OpCode.opCodesCB[opData];
		}
		else {
			opCode = OpCode.opCodes[opCodeValue];
			
			// fetch optional payload data
			for(int i=0;i<opCode.extraBytes; i++)
				opData += (rom.data[nextAddress++] & 0xFF) << (i << 3); // * 2^(8*i) (little endian)
		}

		//System.out.println("visit "+opCode.name);

		boolean vetoContinue = false;
		
		CPUState ns = new CPUState(s);
		if(OpCode.explicitAbsoluteJump.contains(opCodeValue)) { // 16 bit absolute
			ns.prepareJump(opCodeValue,opData);
			int fullJumpAddress = handleJumpBankRelative(address,opData & 0xFFFF,ns,ROM.LABEL_FUNCTION);
			ns = new CPUState(s);
			vetoContinue = ns.prepareForgoJump(opCodeValue,address,fullJumpAddress, sch);
		}
		if(OpCode.explicitRelativeJump.contains(opCodeValue)) { // 8 bit signed relative
			ns.prepareJump(opCodeValue,opData);
			int fullJumpAddress = handleJumpFull(address,nextAddress + ((byte)opData),ns,ROM.LABEL_RELATIVE);
			ns = new CPUState(s);
			vetoContinue = ns.prepareForgoJump(opCodeValue,address,fullJumpAddress, sch);
		}
		if(OpCode.reset.contains(opCodeValue)) { // fixed address jump
			int fullJumpAddress = handleJumpFull(address,(opCodeValue - 0xc7),new CPUState(s),ROM.LABEL_FUNCTION);
			ns = new CPUState(s);
			vetoContinue = ns.prepareForgoJump(opCodeValue,address,fullJumpAddress, sch);
		}
		if(!OpCode.noContinue.contains(opCodeValue)) {
			if(vetoContinue) {
				rom.comment[address] = " ; call does not return";
			} else {
				ns.prepareContinue(opCodeValue,opData,address);
				dfsStack.push(new DFSStackElem(nextAddress, ns));
			}
		}
	}

	private int handleJumpBankRelative(int address, int jumpAddress, CPUState s, int jumpType) {
		if(jumpAddress < 0x4000)             // home bank jump
			return handleJumpFull(address,jumpAddress,s, jumpType);
		else if(jumpAddress >= 0x8000)       // non-ROM jump
			System.out.println("jump to non-ROM address "+Integer.toHexString(jumpAddress)+" at "+Integer.toHexString(address));
		else {
			int bank = address / 0x4000;    // test for intra-bank jump
			if(s.loadedBank != -1) {
				if(bank > 0 && bank != s.loadedBank)
					System.err.println("ERROR: loadedBank "+Integer.toHexString(s.loadedBank)+" does not match address bank "+Integer.toHexString(bank)+" at "+Integer.toHexString(address));
				else
					bank = s.loadedBank;
			}
			if(bank > 0)
				return handleJumpFull(address, jumpAddress+(bank-1)*0x4000, s, jumpType);
			else
				System.out.println("cannot determine ROM bank for jump address "+Integer.toHexString(jumpAddress)+" at "+Integer.toHexString(address));
		}
		return -1;
	}
	private int handleJumpFull(int address, int jumpAddress, CPUState s, int jumpType) {
		rom.addAccess(address,jumpAddress);
		rom.payloadAsAddress[address] = jumpAddress;
		if(rom.label[jumpAddress] == null)
			rom.labelType[jumpAddress] = Math.max(rom.labelType[jumpAddress], jumpType);
		dfsStack.push(new DFSStackElem(jumpAddress, s));
		return jumpAddress;
	}

	public void addIndirectJump(int address, int jumpAddress, CPUState s, int jumpType) {
		rom.indirectJumpTo[address] = jumpAddress;
		rom.payloadAsAddress[address] = jumpAddress;
		if(rom.label[jumpAddress] == null)
			rom.labelType[jumpAddress] = Math.max(rom.labelType[jumpAddress], jumpType);
		dfsStack.push(new DFSStackElem(jumpAddress, s));
	}
	
	public void addJumpTable(int address, int length) {
		
		if(rom.label[address] == null)
			rom.labelType[address] = 3;
		
		for(int i=0; i<length; i++) {
			int ca = address + 2*i;
			rom.type[ca] = ROM.DATA_JUMPPOINTER;
			
			int goalAddress = ((rom.data[ca]&0xFF) | ((rom.data[ca+1]&0xFF) << 8)) & 0xFFFF;
			int bank = -1;
			if(address >= 0x4000)
				bank = address/0x4000;
			if(goalAddress < 0x4000)
				bank = 1; // it's 0, but 1 makes calculation easier
			
			if(goalAddress >= 0x8000)
				System.out.println("jump tabel entry to non-RAM location "+Integer.toHexString(goalAddress)+" at "+Integer.toHexString(address)+" element "+i);
			else if(bank == -1)
				System.err.println("unable to infer bank for jump table at "+Integer.toHexString(address)+" element "+i);
			else {
				int fullAddress = goalAddress + (bank-1)*0x4000;
				//System.out.println("addJump "+Integer.toHexString(fullAddress));
				rom.payloadAsAddress[ca] = fullAddress;
				if(rom.label[fullAddress] == null)
					rom.labelType[fullAddress] = Math.max(rom.labelType[fullAddress], 3);
				dfsStack.push(new DFSStackElem(fullAddress, new CPUState()));
			}
		}
	}
	
	public void addTraceFile(String fileName) throws Throwable {
		Set<Integer> s = new HashSet<Integer>();
		InputStream is = new BufferedInputStream(new FileInputStream(fileName));
		byte[] buf = new byte[14];
		int numRead = 0;
		int numInserted = 0;
		while(is.read(buf) != -1) {
			numRead++;
			if(numRead % 1000000 == 0)
				System.out.println("processed "+numRead+" ("+numInserted+")...");
			int add = ((buf[11]&0xFF) << 8) | (buf[10]&0xFF);
			int bank = (buf[12]&0xFF);
			if(add < 0x8000) {
				if(add >= 0x4000)
					add += (bank-1)*0x4000;
				if(!s.contains(add)) {
					s.add(add);
					dfsStack.push(new DFSStackElem(add, new CPUState()));
					numInserted++;
				}
			}
		}
		is.close();
		System.out.println("inserted "+numInserted+" of "+numRead+" traces");
	}
	
	public void addAddressFiles(String... fileNames) throws Throwable {
		Set<Integer> s = new HashSet<Integer>();
		byte[] buf = new byte[4];
		int numRead = 0;
		int numInserted = 0;
		for(String fileName : fileNames) {
			InputStream is = new BufferedInputStream(new FileInputStream(fileName));
			while(is.read(buf) != -1) {
				numRead++;
				if(numRead % 1000000 == 0)
					System.out.println("processed "+numRead+" ("+numInserted+")...");
				int add = ((buf[3]&0xFF) << 24) | ((buf[2]&0xFF) << 16) | ((buf[1]&0xFF) << 8) | (buf[0]&0xFF);
				if(!s.contains(add)) {
					s.add(add);
					dfsStack.push(new DFSStackElem(add, new CPUState()));
					numInserted++;
				}
			}
			is.close();
		}
		System.out.println("inserted "+numInserted+" of "+numRead+" traces");
	}
}
