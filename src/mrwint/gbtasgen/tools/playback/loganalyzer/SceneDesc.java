package mrwint.gbtasgen.tools.playback.loganalyzer;

public class SceneDesc {
  public int inputLog;
  public int inputScene;
  public int firstInputFrame;
  public int numInputFrames;
  public int numOutputFrames;
  public boolean gbMode;

  public boolean noBackground = false;
  public boolean noSprites = false;
  public boolean noAudio = false;

  public SceneDesc(int inputScene, int firstInputFrame, int numFrames) {
    this(0, inputScene, firstInputFrame, numFrames, numFrames, false);
  }
  public SceneDesc(int inputLog, int inputScene, int firstInputFrame, int numFrames) {
    this(inputLog, inputScene, firstInputFrame, numFrames, numFrames, false);
  }
  public SceneDesc(int inputLog, int inputScene, int firstInputFrame, int numInputFrames, int numOutputFrames, boolean gbMode) {
    this.inputLog = inputLog;
    this.inputScene = inputScene;
    this.firstInputFrame = firstInputFrame;
    this.numInputFrames = numInputFrames;
    this.numOutputFrames = numOutputFrames;
    this.gbMode = gbMode;
  }

  public SceneDesc noBackground() {
    noBackground = true;
    return this;
  }
  public SceneDesc noSprites() {
    noSprites = true;
    return this;
  }
  public SceneDesc noAudio() {
    noAudio = true;
    return this;
  }
}