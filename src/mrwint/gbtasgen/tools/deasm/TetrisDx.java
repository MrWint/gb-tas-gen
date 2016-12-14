package mrwint.gbtasgen.tools.deasm;

import mrwint.gbtasgen.tools.deasm.specialCallHandler.TetrisDxSpecialCallHandler;

public class TetrisDx {

	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		String romName = "roms/tetrisdx.gbc";
		String romBaseName = romName.substring(Math.max(0,romName.lastIndexOf("/")), romName.lastIndexOf("."));

		ROM rom = new ROM(romName);
    rom.addEquFile("assembly/hardware_constants.asm");
    rom.addIncludeFile("assembly/macros.asm");
		new DFS(rom, new TetrisDxSpecialCallHandler())
			.addInit()
			.dfs();
		rom.fixLabelTypes();

		AssemblyWriter assemblyWriter = new AssemblyWriter(rom);
		assemblyWriter.writeAssembly("assembly/"+romBaseName+".asm");
	}

}
