package mrwint.gbtasgen.state;

import java.util.ArrayList;

import mrwint.gbtasgen.Gb;

public class DualGbHelper {

  public static class InputBuilder {
    private ArrayList<Integer> inputs = new ArrayList<Integer>();

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
}
