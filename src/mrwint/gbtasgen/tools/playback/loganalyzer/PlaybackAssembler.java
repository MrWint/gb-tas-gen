package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation;

public class PlaybackAssembler {
  
  private static class TimedActionFromComparator implements Comparator<TimedAction> {
    @Override
    public int compare(TimedAction o1, TimedAction o2) {
      return o1.from.compareTo(o2.from);
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
        if (action.from.frame == -1 && action.action.type == Type.HRAM && action.action.position == GbConstants.LCDC) {
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
    }
  }

  private final TreeMap<Integer, SceneActions> sceneActionMap = new TreeMap<>();
  
  public PlaybackAssembler(ArrayList<TimedAction> actions) {
    for (TimedAction action : actions) {
      if (!sceneActionMap.containsKey(action.from.scene))
        sceneActionMap.put(action.from.scene, new SceneActions());
      sceneActionMap.get(action.from.scene).addAction(action);
    }
    
    for (SceneActions scenes : sceneActionMap.values()) {
      scenes.init();
    }
  }

  public ArrayList<PlaybackOperation> assemble() {
    ArrayList<PlaybackOperation> operations = new ArrayList<>();
    
    for (Integer scene : sceneActionMap.descendingKeySet()) {
      assembleScene(sceneActionMap.get(scene), operations);
    }
    return operations;
  }

  private void assembleScene(SceneActions sceneActions, ArrayList<PlaybackOperation> operations) {
    // TODO Auto-generated method stub
    
  }
}
