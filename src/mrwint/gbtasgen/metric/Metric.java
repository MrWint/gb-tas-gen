package mrwint.gbtasgen.metric;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.state.State;

public interface Metric {

	int getMetric();

	static final Metric TRUE = () -> {return 1;};

	static final Metric RESEASED_JOY = () -> {return Gb.readMemory(RomInfo.rom.hJoypadReleasedAddress);};
	static final Metric PRESSED_JOY = () -> {return Gb.readMemory(RomInfo.rom.hJoypadPressedAddress);};
	static final Metric DOWN_JOY = () -> {return Gb.readMemory(RomInfo.rom.hJoypadDownAddress);};
	static final Metric MENU_JOY = () -> {return Gb.readMemory(RomInfo.rom.hJoypadMenuAddress);};

	static Metric forAddress(int address) {
		return () -> Gb.readMemory(address);
	}
	static Metric forRegister(int register) {
		return () -> State.getRegister(register);
	}
}
