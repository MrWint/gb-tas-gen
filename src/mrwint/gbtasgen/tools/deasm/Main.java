package mrwint.gbtasgen.tools.deasm;

import mrwint.gbtasgen.tools.deasm.specialCallHandler.CrystalSpecialCallHandler;
import mrwint.gbtasgen.tools.updateAssembly.FixBankswitchs;
import mrwint.gbtasgen.tools.updateAssembly.FixBankswitchs2;
import mrwint.gbtasgen.tools.updateAssembly.FixCallLabels;
import mrwint.gbtasgen.tools.updateAssembly.FixROMDataPointers;

public class Main {

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		//String romName = "roms/gold.gbc";
		String romName = "roms/crystal.gbc";
		if(args.length > 0)
			romName = args[0];
		String romBaseName = romName.substring(Math.max(0,romName.lastIndexOf("/")), romName.lastIndexOf("."));
		ROM rom = new ROM(romName);
		//rom.addSymFile("logs/pokecrystal.sym");
		rom.addEquFile("logs/equs.log");
		//DFS dfs = new DFS(rom, new GoldSpecialCallHandler());
		DFS dfs = new DFS(rom, new CrystalSpecialCallHandler());
		dfs.addAddressFiles("logs/crystal_codeAddresses-0.log","logs/crystal_codeAddresses-1.log","logs/crystal_codeAddresses-2.log");
		//dfs.addTraceFile("/media/raid/trace.log");
		dfs.dfs(0x100, 0x40,0x48,0x50,0x58,0x60);
		rom.fixLabelTypes();
		
		//FixCallLabels aa = new FixCallLabels(rom);
		//aa.writeAssembly("assembly/im.asm","assembly/main.asm");
		//aa.writeAssembly("assembly/"+romBaseName+".asm","assembly/main.asm");
		//aa.writeAssembly("assembly/"+romBaseName+".asm","assembly/item_effects.asm");
		
		AssemblyWriter assemblyWriter = new AssemblyWriter(rom);
		assemblyWriter.writeAssembly("assembly/"+romBaseName+".asm");
		//assemblyWriter.addAssembly("assembly/"+romBaseName+".asm","assembly/main.asm");
	}

}
