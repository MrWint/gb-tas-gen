package mrwint.gbtasgen.tools.deasm;

import mrwint.gbtasgen.tools.deasm.specialCallHandler.CrystalSpecialCallHandler;
import mrwint.gbtasgen.tools.deasm.specialCallHandler.SpecialCallHandler;
import mrwint.gbtasgen.tools.deasm.specialCallHandler.Tetris10SpecialCallHandler;
import mrwint.gbtasgen.tools.deasm.specialCallHandler.Tetris11SpecialCallHandler;
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
		String romName = "roms/tetris11.gb";
		if(args.length > 0)
			romName = args[0];
		String romBaseName = romName.substring(Math.max(0,romName.lastIndexOf("/")), romName.lastIndexOf("."));

		ROM rom = new ROM(romName);
		rom.addEquFile("assembly/hardware_constants.asm");
		rom.addEquFile("assembly/tetris11_ramlabels.asm");
		rom.addSymFile("assembly/tetris11.sym");
		new DFS(rom, new Tetris11SpecialCallHandler())
			.addInterrupts()
			.addInit()
			.dfs();
		rom.fixLabelTypes();

		AssemblyWriter assemblyWriter = new AssemblyWriter(rom);
		assemblyWriter.writeAssembly("assembly/"+romBaseName+".asm");
	}

}
