package mrwint.gbtasgen.state;

import java.util.HashMap;

import mrwint.gbtasgen.Gb;

public class DualGbUtil {

  ////////////////////
  // step functions //
  ////////////////////

  public static void dualStep(Gameboy gbL, Gameboy gbR, int moveL, int moveR) {
    Gb.stepDual(gbL.gb, gbR.gb, moveL, moveR);
    gbL.logInput(moveL);
    gbR.logInput(moveR);
    gbL.onFrameBoundaries = true;
    gbR.onFrameBoundaries = true;
    gbL.clearCache();
    gbR.clearCache();
  }

  public static int dualStepUntilL(Gameboy gbL, Gameboy gbR, int moveL, int moveR, int... addressesL) {
    int add = Gb.stepDualUntilL(gbL.gb, gbR.gb, moveL, moveR, addressesL);
    gbL.logInput(moveL);
    gbR.logInput(moveR);
    gbL.onFrameBoundaries = (add == 0);
    gbR.onFrameBoundaries = (add == 0);
    gbL.clearCache();
    gbR.clearCache();
    return add;
  }

  public static int dualStepUntilR(Gameboy gbL, Gameboy gbR, int moveL, int moveR, int... addressesR) {
    int add = Gb.stepDualUntilR(gbL.gb, gbR.gb, moveL, moveR, addressesR);
    gbL.logInput(moveL);
    gbR.logInput(moveR);
    gbL.onFrameBoundaries = (add == 0);
    gbR.onFrameBoundaries = (add == 0);
    gbL.clearCache();
    gbR.clearCache();
    return add;
  }

  /////////////////////////
  // save/load functions //
  /////////////////////////
 
  public static DualState save(Gameboy gbL, Gameboy gbR) {
    return new DualState(Gb.saveDualState(gbL.gb, gbR.gb),
        gbL.stepCount, gbR.stepCount,
        gbL.delayStepCount, gbR.delayStepCount,
        gbL.attributes, gbR.attributes,
        gbL.ocdCount, gbR.ocdCount,
        gbL.lastMove, gbR.lastMove,
        gbL.inputNode, gbR.inputNode,
        gbL.onFrameBoundaries, gbR.onFrameBoundaries,
        gbL.rerecordCount, gbR.rerecordCount);
  }
  
  public static void load(Gameboy gbL, Gameboy gbR, DualState s) {
    Gb.loadDualState(gbL.gb, gbR.gb, s.bb);
    gbL.inputNode = s.inputsL;
    gbR.inputNode = s.inputsR;
    gbL.stepCount = s.stepCountL;
    gbR.stepCount = s.stepCountR;
    gbL.delayStepCount = s.delayStepCountL;
    gbR.delayStepCount = s.delayStepCountR;
    gbL.ocdCount = s.ocdCountL;
    gbR.ocdCount = s.ocdCountR;
    gbL.lastMove = s.lastMoveL;
    gbR.lastMove = s.lastMoveR;
    gbL.attributes = new HashMap<String, Object>(s.attributesL);
    gbR.attributes = new HashMap<String, Object>(s.attributesR);
    gbL.onFrameBoundaries = s.onFrameBoundariesL;
    gbR.onFrameBoundaries = s.onFrameBoundariesR;
    gbL.clearCache();
    gbR.clearCache();
    gbL.rerecordCount = Math.max(gbL.rerecordCount, s.rerecordCountL) + 1;
    gbR.rerecordCount = Math.max(gbR.rerecordCount, s.rerecordCountR) + 1;
  }

  ////////////////////
  // run primitives //
  ////////////////////

  public static void runFor(Gameboy gbL, Gameboy gbR, int numFrames) {
    if (!gbL.onFrameBoundaries)
      dualStep(gbL, gbR, 0, 0); // end unfinished frame
    for (int i = 0; i < numFrames; i++)
      dualStep(gbL, gbR, 0, 0);
  }

  /** returns address or 0 */
  public static int runToAddressL(int limit, Gameboy gbL, Gameboy gbR, int... addresses) {
    if (!gbL.onFrameBoundaries) {
      int add = dualStepUntilL(gbL, gbR, 0, 0, addresses);
      if (add != 0)
        return add;
    }
    int steps = 0;
    while (steps < limit) {
      int add = dualStepUntilL(gbL, gbR, 0, 0, addresses);
      if (add != 0)
        return add;
      steps++;
    }
    return 0;
  }
  public static int runToAddressR(int limit, Gameboy gbL, Gameboy gbR, int... addresses) {
    if (!gbR.onFrameBoundaries) {
      int add = dualStepUntilR(gbL, gbR, 0, 0, addresses);
      if (add != 0)
        return add;
    }
    int steps = 0;
    while (steps < limit) {
      int add = dualStepUntilR(gbL, gbR, 0, 0, addresses);
      if (add != 0)
        return add;
      steps++;
    }
    return 0;
  }

  /** returns frame of vblank or 0 if limit reached. */
  private static int runToInputHiOrLoL(Gameboy gbL, Gameboy gbR, int limit, int joypadInputAdd) {
    int initialSteps = gbL.stepCount;
    int add = runToAddressL(limit, gbL, gbR, joypadInputAdd);
    while (true) {
      if (add == 0 || gbL.stepCount - initialSteps > limit)
        return 0;
      int lastVframe = gbL.stepCount - 1; // subtract started frame
      add = dualStepUntilL(gbL, gbR, 0, 0, gbL.rom.readJoypadAddress);
      if (add != 0)
        return lastVframe;
      add = runToAddressL(limit, gbL, gbR, joypadInputAdd, gbL.rom.readJoypadAddress);
      if (add == gbL.rom.readJoypadAddress)
        return lastVframe;
    }
  }
  private static int runToInputHiOrLoR(Gameboy gbL, Gameboy gbR, int limit, int joypadInputAdd) {
    int initialSteps = gbR.stepCount;
    int add = runToAddressR(limit, gbL, gbR, joypadInputAdd);
    while (true) {
      if (add == 0 || gbR.stepCount - initialSteps > limit)
        return 0;
      int lastVframe = gbR.stepCount - 1; // subtract started frame
      add = dualStepUntilR(gbL, gbR, 0, 0, gbR.rom.readJoypadAddress);
      if (add != 0)
        return lastVframe;
      add = runToAddressR(limit, gbL, gbR, joypadInputAdd, gbR.rom.readJoypadAddress);
      if (add == gbR.rom.readJoypadAddress)
        return lastVframe;
    }
  }

  ///////////////////////////////
  // run to frame before event //
  ///////////////////////////////

  /** returns 1, or 0 if limit reached. */
  private static int runToNextInputFrameHiOrLoL(Gameboy gbL, Gameboy gbR, DualState initial, int limit, int joypadInputAdd) {
    if (!gbL.onFrameBoundaries)
      dualStep(gbL, gbR, 0, 0); // end unfinished frame

    int initialSteps = gbL.stepCount;
    int lastVframe = runToInputHiOrLoL(gbL, gbR, limit, joypadInputAdd);
    if (lastVframe == 0)
      return 0;
    load(gbL, gbR, initial);
    runFor(gbL, gbR, lastVframe - initialSteps);
    return 1;
  }
  private static int runToNextInputFrameHiOrLoR(Gameboy gbL, Gameboy gbR, DualState initial, int limit, int joypadInputAdd) {
    if (!gbR.onFrameBoundaries)
      dualStep(gbL, gbR, 0, 0); // end unfinished frame

    int initialSteps = gbR.stepCount;
    int lastVframe = runToInputHiOrLoR(gbL, gbR, limit, joypadInputAdd);
    if (lastVframe == 0)
      return 0;
    load(gbL, gbR, initial);
    runFor(gbL, gbR, lastVframe - initialSteps);
    return 1;
  }

  /** return 2 if button needs to be held for two frames (hi and lo as separated by a frame boundary), else 1; 0 if limit reached */
  private static int runToNextInputFrameHiLoL(Gameboy gbL, Gameboy gbR, DualState initial, int limit) {
    int joypadInputFirst = Math.min(gbL.rom.readJoypadInputHi, gbL.rom.readJoypadInputLo);
    int joypadInputLast = Math.max(gbL.rom.readJoypadInputHi, gbL.rom.readJoypadInputLo);

    if (!gbL.onFrameBoundaries)
      dualStep(gbL, gbR, 0, 0); // end unfinished frame

    int initialSteps = gbL.stepCount;
    int lastVframe;
    int ret;

    int add = runToAddressL(limit, gbL, gbR, joypadInputFirst);
    while (true) {
      if (add == 0 || gbL.stepCount - initialSteps > limit)
        return 0;
      lastVframe = gbL.stepCount - 1; // subtract started frame
      if (dualStepUntilL(gbL, gbR, 0, 0, joypadInputLast) == 0) { // Assumption: joypadInputLast will come before curGb.rom.readJoypadAddress
        System.out.println("WARNING: joypadInputFirst not is same frame as joypadInputLast! Need two-frame press.");
        ret = 2;
      } else {
        ret = 1;
        add = dualStepUntilL(gbL, gbR, 0, 0, gbL.rom.readJoypadAddress);
        if (add != 0)
          break;
      }
      add = runToAddressL(limit, gbL, gbR, joypadInputFirst, gbL.rom.readJoypadAddress);
      if (add == gbL.rom.readJoypadAddress)
        break;
    }

    load(gbL, gbR, initial);
    runFor(gbL, gbR, lastVframe - initialSteps);
    return ret;
  }
  private static int runToNextInputFrameHiLoR(Gameboy gbL, Gameboy gbR, DualState initial, int limit) {
    int joypadInputFirst = Math.min(gbR.rom.readJoypadInputHi, gbR.rom.readJoypadInputLo);
    int joypadInputLast = Math.max(gbR.rom.readJoypadInputHi, gbR.rom.readJoypadInputLo);

    if (!gbR.onFrameBoundaries)
      dualStep(gbL, gbR, 0, 0); // end unfinished frame

    int initialSteps = gbR.stepCount;
    int lastVframe;
    int ret;

    int add = runToAddressR(limit, gbL, gbR, joypadInputFirst);
    while (true) {
      if (add == 0 || gbR.stepCount - initialSteps > limit)
        return 0;
      lastVframe = gbR.stepCount - 1; // subtract started frame
      if (dualStepUntilR(gbL, gbR, 0, 0, joypadInputLast) == 0) { // Assumption: joypadInputLast will come before curGb.rom.readJoypadAddress
        System.out.println("WARNING: joypadInputFirst not is same frame as joypadInputLast! Need two-frame press.");
        ret = 2;
      } else {
        ret = 1;
        add = dualStepUntilR(gbL, gbR, 0, 0, gbR.rom.readJoypadAddress);
        if (add != 0)
          break;
      }
      add = runToAddressR(limit, gbL, gbR, joypadInputFirst, gbR.rom.readJoypadAddress);
      if (add == gbR.rom.readJoypadAddress)
        break;
    }

    load(gbL, gbR, initial);
    runFor(gbL, gbR, lastVframe - initialSteps);
    return ret;
  }

  /** return 2 if button needs to be held for two frames (hi and lo as separated by a frame boundary), else 1; 0 if limit reached */
  public static int runToNextInputFrameL(Gameboy gbL, Gameboy gbR, DualState initial, int limit, int move) {
    if ((move & 0b00001111) == 0) {
      return runToNextInputFrameHiOrLoL(gbL, gbR, initial, limit, gbL.rom.readJoypadInputHi);
    } else if ((move & 0b11110000) == 0) {
      return runToNextInputFrameHiOrLoL(gbL, gbR, initial, limit, gbL.rom.readJoypadInputLo);
    } else {
      return runToNextInputFrameHiLoL(gbL, gbR, initial, limit);
    }
  }
  public static int runToNextInputFrameR(Gameboy gbL, Gameboy gbR, DualState initial, int limit, int move) {
    if ((move & 0b00001111) == 0) {
      return runToNextInputFrameHiOrLoR(gbL, gbR, initial, limit, gbR.rom.readJoypadInputHi);
    } else if ((move & 0b11110000) == 0) {
      return runToNextInputFrameHiOrLoR(gbL, gbR, initial, limit, gbR.rom.readJoypadInputLo);
    } else {
      return runToNextInputFrameHiLoR(gbL, gbR, initial, limit);
    }
  }
}
