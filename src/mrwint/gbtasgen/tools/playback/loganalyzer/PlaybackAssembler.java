package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.AccessibilityGbState;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackAddresses;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Record;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.SetVram;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Wait;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteBgPaletteDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteByteDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteHByteDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteOamDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteObjPaletteDirect;
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
        if (action.from == 0 && action.getNextAction().type == Type.HRAM && action.getNextAction().position == GbConstants.LCDC) {
          if (initialLcdc != -1)
            throw new RuntimeException("found multiple initial LCDC operations!");
          initialLcdc = (Integer)action.getNextAction().value;

          if (action.consumeNextAction())
            actions.remove(action);
          i--;
        }
      }
      if (initialLcdc == -1)
        throw new RuntimeException("found no initial LCDC operations!");

      actions.sort(new TimedActionFromComparator());
//      Collections.reverse(actions);
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

  public ArrayList<PlaybackOperation> assemble(ArrayList<? extends AccessibilityGbState> accessibilityGbStates) {
    ArrayList<PlaybackOperation> operations = new ArrayList<>();
    
    for (Integer scene : sceneActionMap.descendingKeySet()) {
      System.out.println("Assembling scene " + scene + " (of " + sceneActionMap.size() + ")");
      assembleScene(scene, sceneActionMap.get(scene), operations, accessibilityGbStates.get(scene));
    }

    Collections.reverse(operations);
    return operations;
  }
  
  private int curVramBank = -1;
  private static final int MAX_COMMAND_STACK_SIZE = 1000;
  private static final int FULL_COMMAND_STACK_SIZE = 5;
  private static final int FULL_COMMAND_STACK_SIZE_BUFER_CYCLES = 800;
  private static final int TRIVIAL_COMMAND_STACK_SIZE = 1; // too small to empty
  
  int numDelays = 0;

  private void assembleScene(Integer scene, SceneActions sceneActions, ArrayList<PlaybackOperation> operations, AccessibilityGbState accessibilityGbState) {
    ArrayList<TimedAction> actions = sceneActions.actions;
    System.out.println("Assembling scene " + scene + " with " + actions.size() + " actions");
    ArrayList<Integer> commandStack = new ArrayList<>();
    commandStack.add(PlaybackAddresses.RECORD); // Add Record call at the end of the scene.

    // Determine TimeStamp after the last possible operation.
    long curTime = Long.MIN_VALUE;
    for (TimedAction action : actions) {
      PlaybackOperation operation = generateOperation(action.getNextAction());
      long actionStartTime = operation.getLatestStartingCycleBefore(action.to - operation.getEndOutputCycle(), accessibilityGbState);
      long actionEndTime = actionStartTime + operation.getCycleCount();
      if (curTime < actionEndTime)
        curTime = actionEndTime;
    }
    
    PlaybackOperation endLcdc = new WriteHByteDirect(GbConstants.LCDC, 0, true);
    { // Adjust curTime to be in VBlank, to make sure the last frame is fully rendered.
      long frame = curTime / GbConstants.FRAME_CYCLES;
      long frameCycle = curTime % GbConstants.FRAME_CYCLES;
      if (frameCycle > GbConstants.FRAME_CYCLES - endLcdc.getEndOutputCycle()) {
        frame++;
        frameCycle = 0;
      }
      if (frameCycle < GbConstants.LINE_CYCLES * 150)
        frameCycle = GbConstants.LINE_CYCLES * 150;
      curTime = frame * GbConstants.FRAME_CYCLES + frameCycle;
    }

    // Add LCD off operation at the end of the scene.
    // This ignores vblank, which can damage real hardware but is fine by gambatte.
    {
      System.out.println("Info: add disable LCDC operation " + endLcdc + " at " + curTime);
      operations.add(endLcdc);
      commandStack.add(endLcdc.getJumpAddress());
    }

    // Select next operation
    long minWaitTime;
    TimedAction minAction;
    selectOperation: while (!actions.isEmpty() && actions.get(actions.size() - 1).from > 0) {

      // Force add Record statement to prevent overflow.
      if (commandStack.size() >= MAX_COMMAND_STACK_SIZE) {
        Collections.reverse(commandStack);
        PlaybackOperation recordOperation = Record.forStackFrames(commandStack);
        System.err.println("Warning: force empty command stack " + recordOperation + " at " + curTime);
        operations.add(recordOperation);
        commandStack.clear();
        commandStack.add(PlaybackAddresses.RECORD);
        
        curTime -= recordOperation.getCycleCount();
        continue selectOperation;
      }

      // Add Record statement if we have time.
      if (commandStack.size() >= FULL_COMMAND_STACK_SIZE && actions.get(actions.size() - 1).from + FULL_COMMAND_STACK_SIZE_BUFER_CYCLES < curTime - Record.getCycleCount(2 * commandStack.size())) {
        Collections.reverse(commandStack);
        PlaybackOperation recordOperation = Record.forStackFrames(commandStack);
        System.out.println("Info: empty full command stack " + recordOperation + " at " + curTime);
        operations.add(recordOperation);
        commandStack.clear();
        commandStack.add(PlaybackAddresses.RECORD);
        
        curTime -= recordOperation.getCycleCount();
        continue selectOperation;
      }

      // Search for operation with lowest wait time.
      minWaitTime = Long.MAX_VALUE;
      minAction = null;
      for (int i = actions.size() - 1; i >= 0; i--) {
        TimedAction action = actions.get(i);
        PlaybackOperation operation = generateOperation(action.getNextAction());
        long maxTime = curTime - operation.getCycleCount();
        long startTime = Math.min(action.to - operation.getEndOutputCycle(), maxTime);
        if (maxTime - startTime >= minWaitTime) // can't possibly be better anymore
          continue;
        startTime = operation.getLatestStartingCycleBefore(startTime, accessibilityGbState);
        if (maxTime - startTime >= minWaitTime) // can't possibly be better anymore
          continue;
        if (startTime < maxTime && startTime + Wait.MIN_WAIT_CYCLES > maxTime) // wait time too small
          startTime = operation.getLatestStartingCycleBefore(maxTime - Wait.MIN_WAIT_CYCLES, accessibilityGbState);

        if (actions.get(actions.size() - 1).from + FULL_COMMAND_STACK_SIZE_BUFER_CYCLES >= startTime) { // some time-critical action awaiting
          if (action.from <= 0 || action.from + FULL_COMMAND_STACK_SIZE_BUFER_CYCLES < startTime) { // action not time-critical itself
//            System.err.println("ignore action: too close to buffer");
            continue;
          }
        }
        
        if (action.from > 0 && startTime < action.from - operation.getStartOutputCycle()) { // operation can't fit anymore, need lag frame.
          System.err.println("Warning: action " + action + " requires lag frame at " + curTime + " -> " + (curTime + GbConstants.FRAME_CYCLES));
          numDelays++;
          try {
            Thread.sleep(8000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          curTime += GbConstants.FRAME_CYCLES;
          continue selectOperation;
        }
  
        long waitTime = maxTime - startTime;
        if (waitTime < minWaitTime) {
          minWaitTime = waitTime;
          minAction = action;
          
          if (minWaitTime == 0)
            break;
        }
      }
      
      // Can fit in Record statement
      if (commandStack.size() > TRIVIAL_COMMAND_STACK_SIZE && minWaitTime >= Record.getCycleCount(2 * commandStack.size())) {
        Collections.reverse(commandStack);
        PlaybackOperation recordOperation = Record.forStackFrames(commandStack);
        System.out.println("Info: empty command stack " + recordOperation + " at " + curTime);
        operations.add(recordOperation);
        commandStack.clear();
        commandStack.add(PlaybackAddresses.RECORD);
        
        curTime -= recordOperation.getCycleCount();
        continue selectOperation;
      }

      if (minWaitTime > 0) {
        int waitCycles = (int) (minWaitTime > Wait.MAX_WAIT_CYCLES ? Math.min(Wait.MAX_WAIT_CYCLES, minWaitTime - Wait.MIN_WAIT_CYCLES) : minWaitTime);
        System.out.println("Info: add " + waitCycles + " wait cycles at " + curTime);
        PlaybackOperation waitOperation = new Wait(waitCycles);
        operations.add(waitOperation);
        commandStack.add(waitOperation.getJumpAddress());
        
        curTime -= waitOperation.getCycleCount();
        continue selectOperation;
      }

      PlaybackOperation operation = generateOperation(minAction.getNextAction());
      System.out.println("Info: add action " + minAction + " operation " + operation + " at " + curTime);
      operations.add(operation);
      commandStack.add(operation.getJumpAddress());
      curTime -= operation.getCycleCount();
      updateStateAfterAction(minAction.getNextAction());
      if (minAction.consumeNextAction())
        actions.remove(minAction);
      continue selectOperation;
    }

    // Add initial LCDC operation
    {
      PlaybackOperation enableLcdc = new WriteHByteDirect(GbConstants.LCDC, sceneActions.initialLcdc, true);
      int enableLcdcTime = enableLcdc.getCycleCount() - enableLcdc.getEndOutputCycle();
      if (curTime < enableLcdcTime || (curTime > enableLcdcTime && curTime < enableLcdcTime + Wait.MIN_WAIT_CYCLES)) {
        System.err.println("Warning: instert lag frame for initial LCDC at " + curTime + " -> " + (curTime + GbConstants.FRAME_CYCLES));
        numDelays++;
        try {
          Thread.sleep(8000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        curTime += GbConstants.FRAME_CYCLES;
      }
  
      int waitTime = (int) (curTime - enableLcdcTime);
      
      // Can fit in Record statement
      if (commandStack.size() > TRIVIAL_COMMAND_STACK_SIZE && waitTime >= Record.getCycleCount(2 * commandStack.size())) {
        Collections.reverse(commandStack);
        PlaybackOperation recordOperation = Record.forStackFrames(commandStack);
        System.out.println("Info: empty command stack for initial LCDC " + recordOperation + " at " + curTime);
        operations.add(recordOperation);
        commandStack.clear();
        commandStack.add(PlaybackAddresses.RECORD);
        
        curTime -= recordOperation.getCycleCount();
        waitTime -= recordOperation.getCycleCount();
      }
  
      while (waitTime > 0) {
        int waitCycles = (int) (waitTime > Wait.MAX_WAIT_CYCLES ? Math.min(Wait.MAX_WAIT_CYCLES, waitTime - Wait.MIN_WAIT_CYCLES) : waitTime);
        System.out.println("Info: add " + waitCycles + " wait cycles for initial LCDC at " + curTime);
        PlaybackOperation waitOperation = new Wait(waitCycles);
        operations.add(waitOperation);
        commandStack.add(waitOperation.getJumpAddress());
        
        curTime -= waitOperation.getCycleCount();
        waitTime -= waitOperation.getCycleCount();
      }
  
      System.out.println("Info: add initial LCDC " + enableLcdc + " at " + curTime);
      operations.add(enableLcdc);
      commandStack.add(enableLcdc.getJumpAddress());
    }
    
    System.out.println("Info: add " + actions.size() + " initial operations");
    for (TimedAction action : actions) {
      do {
        // Force add Record statement to prevent overflow.
        if (commandStack.size() >= MAX_COMMAND_STACK_SIZE) {
          Collections.reverse(commandStack);
          PlaybackOperation recordOperation = Record.forStackFrames(commandStack);
          System.out.println("Info: empty command stack " + recordOperation);
          operations.add(recordOperation);
          commandStack.clear();
          commandStack.add(PlaybackAddresses.RECORD);
        }

        PlaybackOperation operation = generateOperation(action.getNextAction());
        System.out.println("Info: add initial action " + action + " operation " + operation);
        operations.add(operation);
        commandStack.add(operation.getJumpAddress());
        updateStateAfterAction(action.getNextAction());
      } while (!action.consumeNextAction());
    }
    
    // Set VRAM bank to initial value
    if (scene == 0 && curVramBank != -1) {
      System.out.println("Info: Set initial VRAM bank to " + curVramBank);
      SetVram setVram = new SetVram(curVramBank);
      operations.add(setVram);
      commandStack.add(setVram.getJumpAddress());
    }

    if (commandStack.size() > 1) {
      Collections.reverse(commandStack);
      PlaybackOperation recordOperation = Record.forStackFrames(commandStack);
      System.out.println("Info: final empty command stack " + recordOperation);
      operations.add(recordOperation);
    }
    System.out.println("assembled with " + numDelays + " delays necessary.");
  }

  private <T> void updateStateAfterAction(Action<T> action) {
    switch (action.type) {
    case BGPALETTE:
    case OBJPALETTE:
    case HRAM:
    case OAM:
      return;
    case TILE:
      curVramBank = action.position / 0x180;
      return;
    case WRAM:
      curVramBank = action.position / 0x800;
      return;
    default:
      throw new RuntimeException("Unknown action type " + action.type);
    }
  }
  
  private <T> PlaybackOperation generateOperation(Action<T> action) {
    switch (action.type) {
    case BGPALETTE:
      return new WriteBgPaletteDirect(action.position, (Palette)action.value, true);
    case OBJPALETTE:
      return new WriteObjPaletteDirect(action.position, (Palette)action.value, true);
    case HRAM:
      return new WriteHByteDirect(action.position, (Integer)action.value, true);
    case TILE:
      int tileAddress = 0x8000 + (action.position % 0x180) * 0x10;
      int tileVramBank = action.position / 0x180;
      return new WriteTileDirect(tileAddress, (Tile)action.value, curVramBank != tileVramBank ? curVramBank : -1, true);
    case WRAM:
      int wramAddress = 0x9800 + action.position % 0x800;
      int wramVramBank = action.position / 0x800;
      return new WriteByteDirect(wramAddress, (Integer)action.value, curVramBank != wramVramBank ? curVramBank : -1, true);
    case OAM:
      return new WriteOamDirect(action.position, (OamEntry)action.value, true);
    default:
      throw new RuntimeException("Unknown action type " + action.type);
    }
  }
}
