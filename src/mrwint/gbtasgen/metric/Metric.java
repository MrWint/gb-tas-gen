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
	
	public static Metric RESEASED_JOY;
	public static Metric PRESSED_JOY;
	public static Metric DOWN_JOY;
	public static Metric MENU_JOY;
	public static Metric TRUE = new Metric() {
		@Override
		public int getMetric() {
			return 1;
		}
	};

	public abstract int getMetric();

	public static void initMetrics() {
		RESEASED_JOY = new MemoryAddress(RomInfo.rom.hJoypadReleasedAddress);
		PRESSED_JOY = new MemoryAddress(RomInfo.rom.hJoypadPressedAddress);
		DOWN_JOY = new MemoryAddress(RomInfo.rom.hJoypadDownAddress);
		MENU_JOY = new MemoryAddress(RomInfo.rom.hJoypadMenuAddress);
	}
}
