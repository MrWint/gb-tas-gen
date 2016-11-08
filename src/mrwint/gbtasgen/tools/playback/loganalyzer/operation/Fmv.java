package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;

public class Fmv implements PlaybackOperation {
  
  private TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  
  public Fmv(byte[][][] framePixels, long[][] framePalettes, int[] samples, int[] soValues) {
    // TODO Auto-generated constructor stub
  }

  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getCycleCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getJumpAddress() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getStartOutputCycle() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getEndOutputCycle() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Accessibility getAccessibility() {
    // TODO Auto-generated method stub
    return null;
  }

}
