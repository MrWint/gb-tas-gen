package mrwint.gbtasgen.state;

import java.util.ArrayList;

import mrwint.gbtasgen.Gb;

public class DualGbHelper {

  public static class InputBuilder {
    private ArrayList<Integer> inputs = new ArrayList<Integer>();

    public InputBuilder withButton(int move) {
      return withButton(move, 0);
    }
    public InputBuilder withButton(int move, int delay) {
      for (int i = 0; i < delay; i++)
        inputs.add(0);
      inputs.add(move);
      return this;
    }
    public InputBuilder reset(int delay) {
      for (int i = 0; i < delay; i++)
        inputs.add(0);
      for (int i = 0; i < 16; i++)
        inputs.add(0b00001111);
      return this;
    }
    public InputBuilder lookAndSee() {
      return withButton(0, 200);
    }

    public int[] build() {
      int[] ret = new int[inputs.size()];
      for (int i = 0; i < inputs.size(); i++)
        ret[i] = inputs.get(i);
      return ret;
    }
  }

  public static void initDualGb(Gameboy gbL, Gameboy gbR) {
    Gb.initDualGb(gbL.gb, gbR.gb);
  }

  public static void executeDualGb(Gameboy gbL, Gameboy gbR, int[] movesL, int[] movesR) {
    // finish unfinished frames
    if (!gbL.onFrameBoundaries)
      gbL.step();
    if (!gbR.onFrameBoundaries)
      gbR.step();

    System.out.println("executeDualGb: initial steps " + gbL.stepCount + " - " + gbR.stepCount);

    // align to same frame
    while(gbL.stepCount < gbR.stepCount)
      gbL.step();
    while(gbR.stepCount < gbL.stepCount)
      gbR.step();

    // do and log moves
    for (int i = 0; i < Math.max(movesL.length, movesR.length); i++) {
      int moveL = i < movesL.length ? movesL[i] : 0;
      int moveR = i < movesR.length ? movesR[i] : 0;
      Gb.stepDual(gbL.gb, gbR.gb, moveL, moveR);
      if (Math.max(movesL.length, movesR.length) - i < 200)
        try {
          Thread.sleep(5);
        } catch (InterruptedException e) {}
      gbL.logInput(moveL);
      gbR.logInput(moveR);
    }

    // clean up gb state
    gbL.clearCache();
    gbR.clearCache();
  }

  private static final int MIN_LIMIT = 4;
  private static final int LIMIT = 4000;
  public static void executeDualGbInputs(Gameboy gbL, Gameboy gbR, int[] movesL, int[] movesR) {
    // finish unfinished frames
    if (!gbL.onFrameBoundaries)
      gbL.step();
    if (!gbR.onFrameBoundaries)
      gbR.step();

    System.out.println("executeDualGbInputs: initial steps " + gbL.stepCount + " - " + gbR.stepCount);

    // align to same frame
    while(gbL.stepCount < gbR.stepCount)
      gbL.step();
    while(gbR.stepCount < gbL.stepCount)
      gbR.step();

    // do and log moves
    int curL = 0;
    int curR = 0;
    while (curL < movesL.length && curR < movesR.length) {
      int limit = MIN_LIMIT;
      while (true) {
        DualState initial = DualGbUtil.save(gbL, gbR);
        int twoFrameL = DualGbUtil.runToNextInputFrameL(gbL, gbR, initial, limit, movesL[curL]);
        int frameL = twoFrameL == 0 ? Integer.MAX_VALUE : gbL.stepCount;
        DualGbUtil.load(gbL, gbR, initial);
        int twoFrameR = DualGbUtil.runToNextInputFrameR(gbL, gbR, initial, limit, movesR[curR]);
        int frameR = twoFrameR == 0 ? Integer.MAX_VALUE : gbR.stepCount;
        if (twoFrameL == 0 && twoFrameR == 0) {
          limit *= 2;
          System.out.println("back off limit to " + limit);
          DualGbUtil.load(gbL, gbR, initial);
          continue;
        }
  
        if (frameR < frameL) {
          DualGbUtil.dualStep(gbL, gbR, 0, movesR[curR]);
          if (twoFrameR == 2) {
            if (frameR + 1 == frameL) {
              DualGbUtil.dualStep(gbL, gbR, movesL[curL], movesR[curR]);
              if (twoFrameL == 2) {
                DualGbUtil.dualStep(gbL, gbR, movesL[curL], 0);
              }
              curL++;
            } else {
              DualGbUtil.dualStep(gbL, gbR, 0, movesR[curR]);
            }
          }
          curR++;
        } else if (frameR == frameL) {
          DualGbUtil.dualStep(gbL, gbR, movesL[curL], movesR[curR]);
          if (twoFrameL == 2 || twoFrameR == 2) {
            DualGbUtil.dualStep(gbL, gbR, twoFrameL == 2 ? movesL[curL] : 0, twoFrameR == 2 ? movesR[curR] : 0);
          }
          curL++;
          curR++;
        } else {
          DualGbUtil.load(gbL, gbR, initial);
          DualGbUtil.runFor(gbL, gbR, frameL - gbL.stepCount);
          DualGbUtil.dualStep(gbL, gbR, movesL[curL], 0);
          if (twoFrameL == 2) {
            if (frameL + 1 == frameR) {
              DualGbUtil.dualStep(gbL, gbR, movesL[curL], movesR[curR]);
              if (twoFrameR == 2) {
                DualGbUtil.dualStep(gbL, gbR, 0, movesR[curR]);
              }
              curR++;
            } else {
              DualGbUtil.dualStep(gbL, gbR, movesL[curL], 0);
            }
          }
          curL++;
        }
        break;
      }
    }
    while (curL < movesL.length) {
      DualState initial = DualGbUtil.save(gbL, gbR);
      int twoFrameL = DualGbUtil.runToNextInputFrameL(gbL, gbR, initial, LIMIT, movesL[curL]);
      if (twoFrameL == 0)
        throw new RuntimeException("Limit reached while executing excess L moves");
      DualGbUtil.dualStep(gbL, gbR, movesL[curL], 0);
      if (twoFrameL == 2)
        DualGbUtil.dualStep(gbL, gbR, movesL[curL], 0);
      curL++;
    }
    while (curR < movesR.length) {
      DualState initial = DualGbUtil.save(gbL, gbR);
      int twoFrameR = DualGbUtil.runToNextInputFrameR(gbL, gbR, initial, LIMIT, movesR[curR]);
      if (twoFrameR == 0)
        throw new RuntimeException("Limit reached while executing excess R moves");
      DualGbUtil.dualStep(gbL, gbR, 0, movesR[curR]);
      if (twoFrameR == 2)
        DualGbUtil.dualStep(gbL, gbR, 0, movesR[curR]);
      curR++;
    }
  }
}
