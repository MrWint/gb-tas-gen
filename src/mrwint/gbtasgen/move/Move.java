package mrwint.gbtasgen.move;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.state.State;


public abstract class Move {
	public static final int A      = 0x01;
	public static final int B      = 0x02;
	public static final int SELECT = 0x04;
	public static final int START  = 0x08;

	public static final int RIGHT  = 0x10;
	public static final int LEFT   = 0x20;
	public static final int UP     = 0x40;
	public static final int DOWN   = 0x80;
	
	public static final int RESET  = 0x800;
	
	public int execute(String name) {
		try {
			int steps = execute();
			System.out.println(name+": "+State.currentStepCount+" ("+steps+" steps, RNG: "+Integer.toHexString(Gb.getRNGState(RomInfo.rom.rngAddress))+")");
			return steps;
		} catch (Throwable e) {
			e.printStackTrace();
			return 0;
		}
	}
	public abstract int execute() throws Throwable;
	
	public abstract int getInitialKey();
}
