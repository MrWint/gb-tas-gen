package mrwint.gbtasgen.util;

import static mrwint.gbtasgen.metric.comparator.Comparator.UNEQUAL;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.state.State;

public class Util {
	public static int getRomWordLE(int add) {
		return curGb.getROM()[add] + (curGb.getROM()[add+1] << 8);
	}
	public static int getRomPointerAddressLE(int add) {
		int ptrLocal = getRomWordLE(add);
		if(ptrLocal < 0x4000)
			return ptrLocal;
		if(ptrLocal < 0x8000) {
			if(add < 0x4000)
				throw new RuntimeException("cannot resolve pointer address "+Util.toHex(ptrLocal)+" from address "+Util.toHex(add));
			return ptrLocal + ((add/0x4000)-1)*0x4000;
		} else
			return ptrLocal;
	}
	public static int getMemoryWordBE(int add) {
		return (curGb.readMemory(add) << 8) + curGb.readMemory(add+1);
	}
	public static int getMemoryWordLE(int add) {
		return curGb.readMemory(add) + (curGb.readMemory(add+1) << 8);
	}

	// returns address or 0
	public static int runToAddressLimit(int baseKeys, int startKeys, int stepLimit, int... addresses) {
		int steps = 0;
		while(steps < stepLimit) {
			int ret = curGb.step(steps == 0 ? startKeys : baseKeys, addresses);
			if(ret != 0)
				return ret;
			steps++;
		}
		return 0;
	}
	// returns address
	public static int runToAddressNoLimit(int baseKeys, int startKeys, int... addresses) {
		return runToAddressLimit(baseKeys, startKeys, Integer.MAX_VALUE, addresses);
	}

	public static void runFor(int numFrames, int baseKeys, int startKeys) {
		for (int i = 0; i < numFrames; i++) {
		  curGb.step(i == 0 ? startKeys : baseKeys);
		}
	}
  public static void runUntil(int keys, Metric m, Comparator comp, int value) {
    while (!comp.compare(m.getMetric(), value))
      curGb.step(keys);
  }
  public static void runToFrameBeforeUntil(int keys, Metric m, Comparator comp, int value) {
    State cur = curGb.newState();
    int startSteps = curGb.currentStepCount;
    runUntil(keys, m, comp, value);
    int steps = curGb.currentStepCount - startSteps - 1;
    curGb.restore(cur);
    runFor(steps, keys, keys);
  }

	public static void runToFrameBeforeAddress(int baseKeys, int startKeys, int... addresses) {
		State cur = curGb.newState();
		int startSteps = curGb.currentStepCount;
		runToAddressNoLimit(baseKeys, startKeys, addresses);
		int steps = curGb.currentStepCount - startSteps;
		curGb.restore(cur);
		runFor(steps, baseKeys, startKeys);
	}
	public static void runToNextInputFrame() {
		runToNextInputFrame(0, 0);
	}
	public static void runToNextInputFrame(int baseKeys, int startKeys) {
		runToFrameBeforeAddress(baseKeys, startKeys, curGb.rom.readJoypadAddress);
	}

	public static void runToFirstDifference(int baseKeys, int altKeys, Metric m) {
		runToFirstDifference(baseKeys,altKeys,m,UNEQUAL);
	}
	public static void runToFirstDifference(int baseKeys, int altKeys, Metric m, Comparator comp) {
		while(true) {
			runToNextInputFrame();
			State cur = curGb.newState();
			if(isDifferencePoint(baseKeys,altKeys,m,comp,cur)) {
				curGb.restore(cur);
				//System.out.println("runToFirstDifference ran "+steps+" base steps");
				return;
			}
		}
	}

	private static boolean isDifferencePoint(int baseKeys, int altKeys, Metric m, Comparator comp, State cur) {
	  curGb.step(altKeys);
		int altMetric = m.getMetric();
		curGb.restore(cur);
		curGb.step(baseKeys);
		int baseMetric = m.getMetric();
		return comp.compare(baseMetric, altMetric);
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
}
