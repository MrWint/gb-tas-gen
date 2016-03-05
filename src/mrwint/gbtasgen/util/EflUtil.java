package mrwint.gbtasgen.util;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.state.State;

public class EflUtil {

  public static void assertEfl() {
    if (!curGb.equalLengthFrames)
      throw new RuntimeException("used efl in non-efl environment");
  }

  public static void assertNoEfl() {
    if (curGb.equalLengthFrames)
      throw new RuntimeException("used non-efl in efl environment");
  }

  ////////////////////
  // run primitives //
  ////////////////////

  /**
   * Runs the simulation until one of the {@code addresses} are reached,
   * but at most {@code stepLimit} steps.
   * @return address or 0
   */
  public static int runToAddressLimit(int baseKeys, int startKeys,
      int stepLimit, int... addresses) {
    if (!curGb.onFrameBoundaries) {
      int add = curGb.step(0, addresses);
      if (add != 0)
        return add;
    }
    int steps = 0;
    while (steps < stepLimit) {
      int add = curGb.step(steps == 0 ? startKeys : baseKeys, addresses);
      if (add != 0)
        return add;
      steps++;
    }
    return 0;
  }

  /**
   * Runs the simulation until one of the {@code addresses} are reached.
   * @return address
   */
  public static int runToAddressNoLimit(int baseKeys, int startKeys,
      int... addresses) {
    return runToAddressLimit(baseKeys, startKeys, Integer.MAX_VALUE, addresses);
  }

  /**
   * Runs the simulation for {@code numFrames}.
   */
  public static void runFor(int numFrames, int baseKeys, int startKeys) {
    if (!curGb.onFrameBoundaries)
      curGb.step(); // end unfinished frame
    for (int i = 0; i < numFrames; i++)
      curGb.step(i == 0 ? startKeys : baseKeys);
  }

  /** returns frame of vblank, or 0 if limit reached. */
  private static int runToInputHiOrLoLimit(int joypadInputAdd, int limit) {
    int initialSteps = curGb.stepCount;
    int lastVframe;

    int add = runToAddressLimit(0, 0, limit, joypadInputAdd);
    while (true) {
      if (add == 0 || curGb.stepCount - initialSteps > limit)
        return 0;
      lastVframe = curGb.stepCount - 1; // subtract started frame
      add = curGb.step(0, curGb.rom.readJoypadAddress);
      if (add != 0)
        return lastVframe;
      add = runToAddressLimit(0, 0, limit, joypadInputAdd, curGb.rom.readJoypadAddress);
      if (add == curGb.rom.readJoypadAddress)
        return lastVframe;
    }
  }

  /** returns frame of vblank, or 0 if address or limit reached. */
  private static int runToAddressOrInputHiOrLoLimit(int joypadInputAdd, int limit, int... addresses) {
    int initialSteps = curGb.stepCount;
    int lastVframe;

    int foundAdd = 0;

    int add = runToAddressLimit(0, 0, limit, Util.arrayAppend(addresses, joypadInputAdd));
//    System.out.println("runToAddressOrInputHiOrLoLimit initial add=" + Util.toHex(add));
    while (true) {
//      System.out.println("runToAddressOrInputHiOrLoLimit start add=" + Util.toHex(add));
      if (add == 0 || curGb.stepCount - initialSteps > limit)
        return 0;
      if (add != joypadInputAdd) {
        foundAdd = add;
        if (curGb.step(0, joypadInputAdd) == 0)
          return 0;
      }
      lastVframe = curGb.stepCount - 1; // subtract started frame
      add = curGb.step(0, foundAdd == 0 ? Util.arrayAppend(addresses, curGb.rom.readJoypadAddress) : new int[] {curGb.rom.readJoypadAddress});
//      System.out.println("runToAddressOrInputHiOrLoLimit readJoypadAddress step add=" + Util.toHex(add));
      if (add == curGb.rom.readJoypadAddress)
        return lastVframe;
      if (add != 0) {
        foundAdd = add;
        if (curGb.step(0, curGb.rom.readJoypadAddress) != 0)
          return lastVframe;
      }
      add = runToAddressLimit(0, 0, limit, foundAdd == 0 ? Util.arrayAppend(addresses, joypadInputAdd, curGb.rom.readJoypadAddress) : new int[] {joypadInputAdd, curGb.rom.readJoypadAddress});
//      System.out.println("runToAddressOrInputHiOrLoLimit readJoypadAddress run add=" + Util.toHex(add));
      if (add == curGb.rom.readJoypadAddress)
        return lastVframe;
      if (foundAdd != 0)
        return 0;
      if (add != 0 && add != joypadInputAdd) {
        foundAdd = add;
        add = curGb.step(0, joypadInputAdd, curGb.rom.readJoypadAddress);
        if (add == curGb.rom.readJoypadAddress)
          return lastVframe;
        if (add == joypadInputAdd)
          continue;
        add = runToAddressLimit(0, 0, limit, joypadInputAdd, curGb.rom.readJoypadAddress);
        if (add == curGb.rom.readJoypadAddress)
          return lastVframe;
        else
          return 0;
      }
    }
  }

  ///////////////////////////////
  // Run to frame before event //
  ///////////////////////////////

  /** returns address that triggered first, or 0 if limit reached. */
  public static int runToFrameBeforeAddressLimit(int baseKeys, int startKeys, int stepLimit, int... addresses) {
    if (!curGb.onFrameBoundaries)
      curGb.step(); // end unfinished frame
    State cur = curGb.newState();
    int startSteps = curGb.stepCount;
    int add = runToAddressLimit(baseKeys, startKeys, stepLimit, addresses);
    if (add == 0) // didn't find address
      return 0;
    int steps = curGb.stepCount - startSteps - 1;
    curGb.restore(cur);
    runFor(steps, baseKeys, startKeys);
    return add;
  }
  public static int runToFrameBeforeAddressNoLimit(int baseKeys, int startKeys,
      int... addresses) {
    return runToFrameBeforeAddressLimit(baseKeys, startKeys, Integer.MAX_VALUE, addresses);
  }

  /** returns true if input frame was found. */
  private static boolean runToNextInputFrameHiOrLoLimit(int joypadInputAdd, int limit) {
    if (!curGb.onFrameBoundaries)
      curGb.step(); // end unfinished frame

    State initial = curGb.newState();
    int initialSteps = curGb.stepCount;
    int lastVframe = runToInputHiOrLoLimit(joypadInputAdd, limit);
    if (lastVframe == 0)
      return false;
    curGb.restore(initial);
    runFor(lastVframe - initialSteps, 0, 0);
    return true;
  }
  public static boolean runToNextInputFrameHiLimit(int limit) {
    return runToNextInputFrameHiOrLoLimit(curGb.rom.readJoypadInputHi, limit);
  }
  public static boolean runToNextInputFrameLoLimit(int limit) {
    return runToNextInputFrameHiOrLoLimit(curGb.rom.readJoypadInputLo, limit);
  }
  public static void runToNextInputFrameHiNoLimit() {
    runToNextInputFrameHiLimit(Integer.MAX_VALUE);
  }
  public static void runToNextInputFrameLoNoLimit() {
    runToNextInputFrameLoLimit(Integer.MAX_VALUE);
  }


  /** return 0 if limit reached, return 2 if button needs to be held for two frames (hi and lo as separated by a frame boundary), else 1 */
  private static int runToNextInputFrameHiLoLimit(int limit) {
    int joypadInputFirst = Math.min(curGb.rom.readJoypadInputHi, curGb.rom.readJoypadInputLo);
    int joypadInputLast = Math.max(curGb.rom.readJoypadInputHi, curGb.rom.readJoypadInputLo);

    if (!curGb.onFrameBoundaries)
      curGb.step(); // end unfinished frame

    State initial = curGb.newState();
    int initialSteps = curGb.stepCount;
    int lastVframe;
    int ret;

    int add = runToAddressLimit(0, 0, limit, joypadInputFirst);
    while (true) {
      if (add == 0 || curGb.stepCount - initialSteps > limit)
        return 0;
      lastVframe = curGb.stepCount - 1; // subtract started frame
      if (curGb.step(0, joypadInputLast) == 0) { // Assumption: joypadInputLast will come before curGb.rom.readJoypadAddress
        System.out.println("WARNING: joypadInputFirst not is same frame as joypadInputLast! Need two-frame press.");
        ret = 2;
      } else {
        ret = 1;
        add = curGb.step(0, curGb.rom.readJoypadAddress);
        if (add != 0)
          break;
      }
      add = runToAddressLimit(0, 0, limit, joypadInputFirst, curGb.rom.readJoypadAddress);
      if (add == curGb.rom.readJoypadAddress)
        break;
    }

    curGb.restore(initial);
    runFor(lastVframe - initialSteps, 0, 0);
    return ret;
  }

  /** return 0 if limit reached, return 2 if button needs to be held for two frames (hi and lo as separated by a frame boundary), else 1 */
  public static int runToNextInputFrameLimit(int move, int limit) {
    if ((move & 0b00001111) == 0) {
      return runToNextInputFrameHiLimit(limit) ? 1 : 0;
    } else if ((move & 0b11110000) == 0) {
      return runToNextInputFrameLoLimit(limit) ? 1 : 0;
    } else {
      return runToNextInputFrameHiLoLimit(limit);
    }
  }
  /** return 2 if button needs to be held for two frames (hi and lo as separated by a frame boundary), else 1 */
  public static int runToNextInputFrameNoLimit(int move) {
    return runToNextInputFrameLimit(move, Integer.MAX_VALUE);
  }


  /** returns -1 if limit reached, 0 if stopped on input frame, or address it stopped on */
  private static int runToAddressOrNextInputFrameHiOrLoLimit(State initial, int joypadInputAdd, int limit, int... addresses) {
    if (!curGb.onFrameBoundaries) {
      int add = curGb.step(0, addresses); // end unfinished frame
      if (add != 0)
        return add;
    }

    if (initial == null)
      initial = curGb.newState();

    int initialSteps = curGb.stepCount;
    int lastVframe = runToAddressOrInputHiOrLoLimit(joypadInputAdd, limit, addresses);
//    System.out.println("lastVframe=" + lastVframe);
    if (lastVframe != 0) {
      curGb.restore(initial);
      runFor(lastVframe - initialSteps, 0, 0);
      return 0;
    }
    curGb.restore(initial);
    int ret = runToAddressLimit(0, 0, limit, addresses);
//    System.out.println("ret=" + ret);
    return ret == 0 ? -1 : ret;
  }

  /** returns 0 if stopped on input frame, or address it stopped on */
  public static int runToAddressOrNextInputFrameNoLimit(State initial, int move, int... addresses) {
    return runToAddressOrNextInputFrameLimit(initial, move, Integer.MAX_VALUE, addresses);
  }
  /** returns -1 if limit reached, 0 if stopped on input frame, or address it stopped on */
  public static int runToAddressOrNextInputFrameLimit(State initial, int move, int limit, int... addresses) {
    int expectedRet = -2;
    if ((move & 0b00001111) == 0) {
      expectedRet = runToAddressOrNextInputFrameHiOrLoLimit(initial, curGb.rom.readJoypadInputHi, limit, addresses);
      return expectedRet;
    } else if ((move & 0b11110000) == 0) {
      expectedRet = runToAddressOrNextInputFrameHiOrLoLimit(initial, curGb.rom.readJoypadInputLo, limit, addresses);
      return expectedRet;
    }

    int initialSteps = curGb.stepCount;
    if (initial == null)
      initial = curGb.newState();

    if (runToNextInputFrameLimit(move, limit) == 0) {
//      if (expectedRet != -2 && expectedRet != -1)
//        throw new RuntimeException("-1 != " + Util.toHex(expectedRet));
//      return -1;
      return expectedRet != -2 ? expectedRet : -1;
    }
    int inputSteps = curGb.stepCount;

    curGb.restore(initial);
    int actualRet = runToAddressLimit(0, 0, inputSteps - initialSteps, addresses); // run at most to the input frame
    if (expectedRet != -2 && actualRet != expectedRet)
      throw new RuntimeException(Util.toHex(actualRet) + " != " + Util.toHex(expectedRet));
    return actualRet;
  }

  public enum PressMetric {
    RELEASED_JOY(Metric.DOWN_JOY, curGb.rom.readJoypadEndAddressReleased),
    DOWN(Metric.DOWN_JOY, curGb.rom.readJoypadEndAddressDown),
    PRESSED(Metric.PRESSED_JOY, curGb.rom.readJoypadEndAddressPressed),
    MENU(Metric.MENU_JOY, curGb.rom.readJoypadEndAddressMenu);

    public Metric m;
    public int[] add;
    PressMetric(Metric m, int[] add) {
      this.m = m;
      this.add = add;
    }
  }
  // returns true if button needs to be held for two frames (hi and lo are separated by a frame boundary)
  public static int runToNextInputFrameForMetricNoLimit(int move, PressMetric pressMetric) {
    while (true) {
      int ret = runToNextInputFrameNoLimit(move);
      State cur = curGb.newState();
      curGb.step(move, curGb.rom.readJoypadInputHi); // doesn't matter which, since hi and lo together
      runToAddressNoLimit(0, 0, curGb.rom.readJoypadAddress);
      runToAddressNoLimit(0, 0, pressMetric.add);
      runToAddressNoLimit(0, 0, curGb.rom.readJoypadInputHi); // deal with any overwriting that happens later this frame
      int metric = pressMetric.m.getMetric();
      curGb.restore(cur);
      if (metric == move)
        return ret;
      curGb.step(); // skip this input frame
    }
  }

  // finds first input frame after a specific address has been passed
  private static void runToFirstInputFrameAfterAddressHiOrLoNoLimit(int move, int joypadInputAdd, int addressToPass) {
    State initial = curGb.newState();
//    System.out.println("runToFirstInputFrameAfterAddressHiOrLo steps1: " + curGb.currentStepCount);
    runToAddressNoLimit(0, 0, addressToPass);
//    System.out.println("runToFirstInputFrameAfterAddressHiOrLo steps2: " + curGb.currentStepCount);
    int stepsToAddress = curGb.stepCount;
    curGb.restore(initial);

    while (true) {
      //      System.out.println("runToFirstInputFrameAfterAddressHiOrLo steps1: " + curGb.currentStepCount);
      runToNextInputFrameHiOrLoLimit(joypadInputAdd, Integer.MAX_VALUE);
//      System.out.println("runToFirstInputFrameAfterAddressHiOrLo steps3: " + curGb.currentStepCount);
      if (curGb.stepCount >= stepsToAddress) // address occurred before this input frame already
        return;
      State cur = curGb.newState();
      int add = curGb.step(move, addressToPass);
      if (add != 0) {
        curGb.restore(cur);
        return;
      }
      add = runToAddressNoLimit(0, 0, addressToPass, joypadInputAdd);
      if (add == joypadInputAdd) {
//        System.out.println("runToFirstInputFrameAfterAddressHiOrLo unsuitable1: " + curGb.currentStepCount);
        curGb.restore(cur);
        curGb.step(); // skip unsuitable input frame; note that addressToPass can't be skipped here
        continue;
      }
      add = runToAddressNoLimit(0, 0, curGb.rom.readJoypadAddress, joypadInputAdd);
      if (add == joypadInputAdd) {
//        System.out.println("runToFirstInputFrameAfterAddressHiOrLo unsuitable2: " + curGb.currentStepCount);
        curGb.restore(cur);
        curGb.step(); // skip unsuitable input frame; note that addressToPass can't be skipped here
        continue;
      }
      curGb.restore(cur);
      return;
    }
  }
  public static void runToFirstInputFrameAfterAddressNoLimit(int move, int addressToPass) {
    if ((move & 0b00001111) == 0) {
      runToFirstInputFrameAfterAddressHiOrLoNoLimit(move, curGb.rom.readJoypadInputHi, addressToPass);
      return;
    } else if ((move & 0b11110000) == 0) {
      runToFirstInputFrameAfterAddressHiOrLoNoLimit(move, curGb.rom.readJoypadInputLo, addressToPass);
      return;
    } else {
      throw new RuntimeException("Unsupported mixed inputs in runToFirstInputFrameAfterAddress");
    }
  }

  // runs until joypad action move won't affect execution before addressToPass anymore. Won't run past addressToPass.
  // returns 0 if on next input frame, or addressToPass if at addressToPass.
  private static int runInputPastAddressHiOrLoNoLimit(int move, int joypadInputAdd, int addressToPass) {
    while (true) {
      State initial = curGb.newState();
      runToAddressNoLimit(0, 0, addressToPass);
      int stepsToAddress = curGb.stepCount;

      curGb.restore(initial);
      runToNextInputFrameHiOrLoLimit(joypadInputAdd, Integer.MAX_VALUE);
      if (curGb.stepCount >= stepsToAddress) { // address occurred before this input frame already
        System.out.println("runInputPastAddressHiOrLo: "+curGb.stepCount+" >= "+stepsToAddress);
        curGb.restore(initial);
        runToAddressNoLimit(0, 0, addressToPass);
        return addressToPass;
      }
      State cur = curGb.newState();
      int add = curGb.step(move, joypadInputAdd, addressToPass);
      if (add == addressToPass) {
        curGb.restore(cur);
        return 0;
      }
      if (add != joypadInputAdd)
        throw new RuntimeException("runToNextInputFrameHiOrLo returned invalid input frame!");
      add = runToAddressNoLimit(0, 0, curGb.rom.readJoypadAddress, addressToPass);
      if (add == addressToPass) {
        curGb.restore(cur);
        return 0;
      }
      curGb.restore(cur);
      add = curGb.step(0, addressToPass); // skip unsuitable input frame
      if (add == addressToPass)
        return addressToPass;
    }
  }
  public static int runInputPastAddressNoLimit(int move, int addressToPass) {
    if ((move & 0b00001111) == 0) {
      return runInputPastAddressHiOrLoNoLimit(move, curGb.rom.readJoypadInputHi, addressToPass);
    } else if ((move & 0b11110000) == 0) {
      return runInputPastAddressHiOrLoNoLimit(move, curGb.rom.readJoypadInputLo, addressToPass);
    } else {
      throw new RuntimeException("Unsupported mixed inputs in runInputPastAddress");
    }
  }
}
