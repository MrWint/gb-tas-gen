package mrwint.gbtasgen.metric;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.state.Register;
import mrwint.gbtasgen.state.State;

public abstract class Metric {
	
	static Metric AF = new Metric() {
		@Override
		public int getMetric() {
			return State.getRegister(Register.AF);
		}
	};
	
	public static Metric RESEASED_JOY_H;
	public static Metric PRESSED_JOY_H;
	public static Metric DOWN_JOY_H;
	public static Metric SUM_JOY_H;
	public static Metric RESEASED_JOY;
	public static Metric PRESSED_JOY;
	public static Metric DOWN_JOY;

	public abstract int getMetric();

	public static void initMetrics() {
		RESEASED_JOY_H = new MemoryAddress(RomInfo.rom.hJoypadReleasedAddress + 0);
		PRESSED_JOY_H = new MemoryAddress(RomInfo.rom.hJoypadReleasedAddress + 1);
		DOWN_JOY_H = new MemoryAddress(RomInfo.rom.hJoypadReleasedAddress + 2);
		SUM_JOY_H = new MemoryAddress(RomInfo.rom.hJoypadReleasedAddress + 3);
		RESEASED_JOY = new MemoryAddress(RomInfo.rom.hJoypadReleasedAddress + 4);
		PRESSED_JOY = new MemoryAddress(RomInfo.rom.hJoypadReleasedAddress + 5);
		DOWN_JOY = new MemoryAddress(RomInfo.rom.hJoypadReleasedAddress + 6);
	}
}
