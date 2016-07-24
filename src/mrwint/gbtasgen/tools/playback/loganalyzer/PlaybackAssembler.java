package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.AccessibilityGbState;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.FixedAccessibilityGbState;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackAddresses;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteBgPaletteDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteByteDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteHByteDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteTileDirect;

public class PlaybackAssembler {
  
  private static class TimedActionFromComparator implements Comparator<TimedAction> {
    @Override
    public int compare(TimedAction o1, TimedAction o2) {
      return Long.compare(o1.from, o2.from);
    }
  }

  private static class SceneActions {
    private int initialLcdc = -1;
    private ArrayList<TimedAction> actions = new ArrayList<>();
    public void addAction(TimedAction action) {
      actions.add(action);
    }

    public void init() {
      for (int i = 0; i < actions.size(); i++) {
        TimedAction action = actions.get(i);
        if (action.from == -1 && action.action.type == Type.HRAM && action.action.position == GbConstants.LCDC) {
          if (initialLcdc != -1)
            throw new RuntimeException("found multiple initial LCDC operations!");
          initialLcdc = (Integer)action.action.value;

          actions.remove(action);
          i--;
        }
      }
      if (initialLcdc == -1)
        throw new RuntimeException("found no initial LCDC operations!");

      actions.sort(new TimedActionFromComparator());
      Collections.reverse(actions);
    }
  }

  private final TreeMap<Integer, SceneActions> sceneActionMap = new TreeMap<>();
  
  public PlaybackAssembler(ArrayList<TimedAction> actions) {
    for (TimedAction action : actions) {
      if (!sceneActionMap.containsKey(action.scene))
        sceneActionMap.put(action.scene, new SceneActions());
      sceneActionMap.get(action.scene).addAction(action);
    }
    
    for (SceneActions scenes : sceneActionMap.values()) {
      scenes.init();
    }
  }

  public ArrayList<PlaybackOperation> assemble() {
    ArrayList<PlaybackOperation> operations = new ArrayList<>();
    
    for (Integer scene : sceneActionMap.descendingKeySet()) {
      System.out.println("Assembling scene " + scene + " (of " + sceneActionMap.size() + ")");
      assembleScene(scene, sceneActionMap.get(scene), operations);
    }
    return operations;
  }
  
  private int curVramBank = -1;
  private final AccessibilityGbState gbState = new FixedAccessibilityGbState(0, 7, true);

  private void assembleScene(Integer scene, SceneActions sceneActions, ArrayList<PlaybackOperation> operations) {
    ArrayList<TimedAction> actions = sceneActions.actions;
    System.out.println("Assembling scene with " + actions.size() + " actions");
    ArrayList<Integer> commandStack = new ArrayList<>();
    commandStack.add(PlaybackAddresses.RECORD); // Add Record call at the end of the scene.

    // Determine TimeStamp after the last possible operation.
    long curTime = Long.MIN_VALUE;
    for (TimedAction action : actions) {
      PlaybackOperation operation = generateOperation(action.action);
      long actionStartTime = operation.getLatestStartingCycleBefore(action.to - operation.getEndOutputCycle(), gbState);
      long actionEndTime = actionStartTime + operation.getCycleCount();
      if (curTime < actionEndTime)
        curTime = actionEndTime;
    }

    // Add LCD off operation at the end of the scene.
    // This ignores vblank, which can damage real hardware but is fine by gambatte.
    {
      PlaybackOperation endLcdc = new WriteHByteDirect(GbConstants.LCDC, 0);
      operations.add(endLcdc);
      commandStack.add(endLcdc.getJumpAddress());
    }

    for (int i = 0; i < actions.size(); i++) {
      TimedAction action = actions.get(i);
      PlaybackOperation operation = generateOperation(action.action);
      long maxOperationStartTime = curTime - operation.getCycleCount();
      Accessibility accessibility = operation.getAccessibility();
      int tailLength = operation.getCycleCount() - operation.getEndOutputCycle();
    }
    
    Collections.reverse(operations);
  }
  
  private <T> PlaybackOperation generateOperation(Action<T> action) {
    switch (action.type) {
    case BGPALETTE:
      return new WriteBgPaletteDirect(action.position, (Palette)action.value);
    case HRAM:
      return new WriteHByteDirect(action.position, (Integer)action.value);
    case TILE:
      int tileAddress = 0x8000 + (action.position % 0x180) * 0x10;
      int tileVramBank = action.position / 0x180;
      return new WriteTileDirect(tileAddress, (Tile)action.value, curVramBank != tileVramBank ? curVramBank : -1);
    case WRAM:
      int wramAddress = 0x9800 + action.position % 0x800;
      int wramVramBank = action.position / 0x800;
      return new WriteByteDirect(wramAddress, (Integer)action.value, curVramBank != wramVramBank ? curVramBank : -1);
    default:
      throw new RuntimeException("Unknown action type " + action.type);
    }
  }
}
