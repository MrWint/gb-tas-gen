package mrwint.gbtasgen.metric;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.state.State;

public interface Metric {

	int getMetric();

	static Metric RESEASED_JOY = () -> {return Gb.readMemory(RomInfo.rom.hJoypadReleasedAddress);};
	static Metric PRESSED_JOY = () -> {return Gb.readMemory(RomInfo.rom.hJoypadPressedAddress);};
	static Metric DOWN_JOY = () -> {return Gb.readMemory(RomInfo.rom.hJoypadDownAddress);};
	static Metric MENU_JOY = () -> {return Gb.readMemory(RomInfo.rom.hJoypadMenuAddress);};
	static Metric TRUE = () -> {return 1;};

	static Metric forAddress(int address) {
		return () -> {return Gb.readMemory(address);};
	}
	static Metric forRegister(int register) {
		return () -> {return State.getRegister(register);};
	}
}
