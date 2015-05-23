package mrwint.gbtasgen.metric;

import static mrwint.gbtasgen.state.Gameboy.curGb;

public interface Metric {

	int getMetric();

	static final Metric TRUE = () -> {return 1;};

	static final Metric RELEASED_JOY = () -> {return curGb.readMemory(curGb.rom.hJoypadReleasedAddress);};
	static final Metric PRESSED_JOY = () -> {return curGb.readMemory(curGb.rom.hJoypadPressedAddress);};
	static final Metric DOWN_JOY = () -> {return curGb.readMemory(curGb.rom.hJoypadDownAddress);};
	static final Metric MENU_JOY = () -> {return curGb.readMemory(curGb.rom.hJoypadMenuAddress);};

  static Metric not(Metric m) {
    return () -> m.getMetric() == 0 ? 1 : 0;
  }
  static Metric forAddress(int address) {
    return () -> curGb.readMemory(address);
  }
	static Metric forRegister(int register) {
		return () -> curGb.getRegister(register);
	}
}
