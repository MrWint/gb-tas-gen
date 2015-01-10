package mrwint.gbtasgen.tools.deasm;

import mrwint.gbtasgen.tools.deasm.specialCallHandler.SML2_10SpecialCallHandler;

public class Main {

	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		String romName = "roms/sml2_10.gb";
		if(args.length > 0)
			romName = args[0];
		String romBaseName = romName.substring(Math.max(0,romName.lastIndexOf("/")), romName.lastIndexOf("."));

		ROM rom = new ROM(romName);
    rom.addEquFile("assembly/hardware_constants.asm");
    rom.addIncludeFile("assembly/macros.asm");
		rom.addEquFile("assembly/sml2_10_ramlabels.asm");
		rom.addSymFile("assembly/sml2_10.sym");
		new DFS(rom, new SML2_10SpecialCallHandler())
      .addFunction(0x40, "VBlankInterrupt")
      .addFunction(0x48, "LCDInterrupt")
      .addFunction(0x50, "TimerInterrupt")
      .addFunction(0x58, "SerialInterrupt")
			.addInit()
			.dfs();
		rom.fixLabelTypes();

		AssemblyWriter assemblyWriter = new AssemblyWriter(rom);
		assemblyWriter.writeAssembly("assembly/"+romBaseName+".asm");
	}

}
