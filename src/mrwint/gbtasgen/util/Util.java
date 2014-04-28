package mrwint.gbtasgen.util;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.comparator.Unequal;
import mrwint.gbtasgen.state.State;

public class Util {
	public static String charConversionTable = 
			"................" +
			"................" +
			"................" +
			"................" +
			"................" +
			"@...#..........." +
			"................" +
			"....№.......... " +
			"ABCDEFGHIJKLMNOP" +
			"QRSTUVWXYZ():;[]" +
			"abcdefghijklmnop" +
			"qrstuvwxyzédlstv" +
			"................" +
			"................" +
			"'..-rm?!........" +
			"......0123456789";
	
	public static String getString(int startAdd) {
		String ret = "";
		while(true) {
			char c = charConversionTable.charAt(State.getROM()[startAdd++]);
			if(c == '@') break;
			ret += c;
		}
		return ret;
	}
	public static String getStringFromList(int startAdd, int skips) {
		while(skips-- > 0)
			while(State.getROM()[startAdd++] != 0x50);
		return getString(startAdd);
	}
	public static String getStringFromPointerList(int startAdd, int skips) {
		return getString(getPointerAddress(startAdd+2*skips));
	}
	public static int getWordAt(int add) {
		return State.getROM()[add] + (State.getROM()[add+1] << 8);
	}
	public static int getPointerAddress(int add) {
		int ptrLocal = getWordAt(add);
		if(ptrLocal < 0x4000)
			return ptrLocal;
		if(ptrLocal < 0x8000) {
			if(add < 0x4000)
				throw new RuntimeException("cannot resolve pointer address "+Util.toHex(ptrLocal)+" from address "+Util.toHex(add));
			return ptrLocal + ((add/0x4000)-1)*0x4000;
		} else
			return ptrLocal;
	}
	public static int getMemoryBigEndian(int add) {
		return (Gb.readMemory(add) << 8) + Gb.readMemory(add+1);
	}
	public static int getMemoryLittleEndian(int add) {
		return Gb.readMemory(add) + (Gb.readMemory(add+1) << 8);
	}

	// returns number of steps
	public static int runToAddress(int baseKeys, int startKeys, int... addresses) {
		int steps = 0;
		while(true) {
			int ret = State.step(steps == 0 ? startKeys : baseKeys, addresses);
			if(ret != 0)
				return steps;
			steps++;
		}
	}
	// returns address or 0
	public static int runToAddress2Limit(int baseKeys, int startKeys, int stepLimit, int... addresses) {
		int steps = 0;
		while(steps < stepLimit) {
			int ret = State.step(steps == 0 ? startKeys : baseKeys, addresses);
			if(ret != 0)
				return ret;
			steps++;
		}
		return 0;
	}
	// returns address
	public static int runToAddress2(int baseKeys, int startKeys, int... addresses) {
		return runToAddress2Limit(baseKeys, startKeys, Integer.MAX_VALUE, addresses);
	}

	public static int runFor(int numFrames, int baseKeys, int startKeys) {
		for (int i = 0; i < numFrames; i++) {
			State.step(i == 0 ? startKeys : baseKeys);
		}
		return numFrames;
	}
	
	public static int runToFrameBeforeAddress(int baseKeys, int startKeys, int... addresses) {
		State cur = new State();
		int steps = runToAddress(baseKeys,baseKeys,addresses);
		cur.restore();
		runFor(steps, baseKeys, startKeys);
		return steps;
	}
	public static int runToNextInputFrame() {
		return runToNextInputFrame(0);
	}
	public static int runToNextInputFrame(int baseKeys) {
		return runToFrameBeforeAddress(baseKeys, baseKeys, RomInfo.rom.readJoypadAddress);
	}

	public static int runToFirstDifference(int baseKeys, int altKeys, Metric m) {
		return runToFirstDifference(baseKeys,altKeys,m,new Unequal());
	}
	
	public static int runToFirstDifference(int baseKeys, int altKeys, Metric m, Comparator comp) {
		//State initial = new State();
		int steps = runToNextInputFrame();
		State cur = new State();
		while(true) {
			if(isDifferencePoint(baseKeys,altKeys,m,comp,cur,cur,null)) {
				//System.out.println("runToFirstDifference ran "+steps+" base steps");
				return steps;
			}
			steps++;
			steps += runToNextInputFrame();
			cur = new State();
		}
	}
	
	
	
	public static boolean isDifferencePoint(int baseKeys, int altKeys, Metric m, Comparator comp, State cur, State restoreOnTrue, State restoreOnFalse) {
		State.step(altKeys);
		int altMetric = m.getMetric();
		cur.restore();
		State.step(baseKeys);
		int baseMetric = m.getMetric();
		if(comp.compare(baseMetric, altMetric)) {
			if(restoreOnTrue != null)
				restoreOnTrue.restore();
			//System.out.println("isDifferencePoint: found "+Integer.toHexString(baseMetric)+" != "+Integer.toHexString(altMetric));
			return true;
		}
		//System.out.println("isDifferencePoint: found "+Integer.toHexString(baseMetric)+" == "+Integer.toHexString(altMetric));
		if(restoreOnFalse != null)
			restoreOnFalse.restore();
		return false;
	}
	
	
	
	
	
	public static boolean arrayContains(int[] a, int b) {
		for(int i=0;i<a.length; i++)
			if(a[i] == b)
				return true;
		return false;
	}
	public static boolean arrayCompare(int[] a, int[] b) {
		return arrayCompare(a,b,0,0,Math.min(a.length,b.length));
	}
	public static boolean arrayCompare(int[] a, int[] b, int len) {
		return arrayCompare(a,b,0,0,len);
	}
	public static boolean arrayCompare(int[] a, int[] b, int aOffset, int bOffset, int len) {
		for(int i=0;i<len;i++) {
			if(a[aOffset + i] != b[bOffset + i])
				return false;
		}
		return true;
	}
	public static void printDiff(int[] a,int[] b) {
		for(int i=0;i<a.length;i++) {
			if(a[i] != b[i])
				System.out.println(""+i+"("+Integer.toHexString(i)+"): "+Integer.toHexString(a[i])+":"+Integer.toHexString(b[i]));
		}
	}
	public static void printArray(int[] a) {
		for(int i=0;i<a.length;i++)
			System.out.println(""+i+"("+Integer.toHexString(i)+"): "+Integer.toHexString(a[i]));
	}
	public static String toHex(int num) {
		return toHex(num,1);
	}
	public static String toHex(int num, int minDigits) {
		String ret = Integer.toHexString(num);
		while(ret.length() < minDigits)
			ret = "0" + ret;
		return ret;
	}
	
	public static boolean isCrystal() {
		return RomInfo.rom.type == RomInfo.CRYSTAL;
	}
	public static boolean isGold() {
		return RomInfo.rom.type == RomInfo.GOLD;
	}
	public static boolean isSilver() {
		return RomInfo.rom.type == RomInfo.SILVER;
	}
	public static boolean isGen2() {
		return RomInfo.rom.type == RomInfo.SILVER || RomInfo.rom.type == RomInfo.GOLD || RomInfo.rom.type == RomInfo.CRYSTAL;
	}
	public static boolean isGen1() {
		return RomInfo.rom.type == RomInfo.RED || RomInfo.rom.type == RomInfo.BLUE;
	}
}
