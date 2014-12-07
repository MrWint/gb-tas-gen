package mrwint.gbtasgen.tools.deasm;

public class DeasmUtil {
	public static int toFull(int memoryLocation, int bank) {
		if (memoryLocation < 0x4000)
			return memoryLocation;
		if (memoryLocation < 0x8000)
			return memoryLocation + (bank-1)*0x4000;
		return memoryLocation;
	}

	public static int getBank(int romAddress) {
		if (romAddress < 0x4000)
			return 1;
		return romAddress / 0x4000;
	}

	public static String format(int value, int format) {
		String ret;
		switch (format) {
		case ROM.FORMAT_HEX:
			ret = Integer.toHexString(value);
			while (ret.length() < 2)
				ret = "0" + ret;
			return "$" + ret;
		case ROM.FORMAT_DEC:
			ret = Integer.toString(value, 10);
			while (ret.length() < 3)
				ret = " " + ret;
			return ret;
		case ROM.FORMAT_BIN:
			ret = Integer.toString(value, 2);
			while (ret.length() < 8)
				ret = "0" + ret;
			return "%" + ret;
		case ROM.FORMAT_BUTTONS:
			return toButtons(value);
		default:
			throw new IllegalArgumentException("Unknown format " + format);
		}
	}

	private static final String[] buttonConstants = {
		"BTN_A", "BTN_B", "BTN_SELECT", "BTN_START",
		"BTN_RIGHT", "BTN_LEFT", "BTN_UP", "BTN_DOWN"};
	private static String toButtons(int value) {
		String ret = "";
		for (int i=0;i<8;i++) {
			if ((value & (1 << i)) != 0) {
				if (!ret.isEmpty())
					ret += " | ";
				ret += buttonConstants[i];
			}
		}
		return ret.isEmpty() ? "0" : ret;
	}
}
