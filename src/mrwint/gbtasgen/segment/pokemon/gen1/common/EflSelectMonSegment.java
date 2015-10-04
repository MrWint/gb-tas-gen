package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CUT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DIG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FLY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STRENGTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SURF;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TELEPORT;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflSelectMonSegment extends SeqSegment {

  int mon;
  int monPosition;
  int extraScrolls = 0;
  int cutScrolls = -1;
  int flyScrolls = -1;
  int surfScrolls = -1;
  int strengthScrolls = -1;
  int digScrolls = -1;
  int teleportScrolls = -1;
  int numMons;
  int monGoalPosition = -1;
	public EflSelectMonSegment(int mon) {
	  EflUtil.assertEfl();

		this.mon = mon;
	}

  protected boolean debugOutput = false;
  public EflSelectMonSegment withDebugOutput() {
    debugOutput = true;
    return this;
  }

  protected boolean fromOverworld = false;
  public EflSelectMonSegment fromOverworld() {
    fromOverworld = true;
    return this;
  }

  protected boolean fromMainMenu = false;
  public EflSelectMonSegment fromMainMenu() {
    fromMainMenu = true;
    return this;
  }

  protected int switchWithMon = -1;
  public EflSelectMonSegment andSwitchWith(int mon) {
    switchWithMon = mon;
    return this;
  }

  protected int flyTo = Integer.MIN_VALUE;
  public EflSelectMonSegment andFlyTo(int flyTo) {
    this.flyTo = flyTo;
    return this;
  }

	@Override
	public void execute() {
	  if (fromOverworld)
	    seq(new EflSelectMainMenuSegment(Constants.MENU_MON).fromOverworld()); // open mon menu
	  else if (fromMainMenu)
      seq(new EflSelectMainMenuSegment(Constants.MENU_MON)); // open mon menu

	  seqSample(() -> {
      monPosition = curGb.readMemory(curGb.pokemon.monMenuIndexAddress);
      numMons = curGb.readMemory(curGb.pokemon.numPartyMonAddress);
      for (int i = 0; i < numMons; i++) {
        int curMon = curGb.readMemory(curGb.pokemon.numPartyMonAddress + i + 1);
        if (curMon == mon) {
          monGoalPosition = i;
          break;
        }
      }
      if (monGoalPosition == -1)
        throw new IllegalStateException("Mon " + mon + " not found.");

      for (int j = 0; j < 4; j++) {
        int baseAdd = curGb.pokemon.numPartyMonAddress + monGoalPosition * 44 + 8;
        int move = curGb.readMemory(baseAdd + 8 + j);
        if (move == CUT)
          cutScrolls = extraScrolls;
        if (move == FLY)
          flyScrolls = extraScrolls;
        if (move == SURF)
          surfScrolls = extraScrolls;
        if (move == STRENGTH)
          strengthScrolls = extraScrolls;
        if (move == DIG)
          digScrolls = extraScrolls;
        if (move == TELEPORT)
          teleportScrolls = extraScrolls;
        if (Constants.FIELD_MOVES.contains(move))
          extraScrolls++;
      }

      if (debugOutput) {
        System.out.println(String.format("mon pos: %s to %s extraScrolls %s", monPosition, monGoalPosition, extraScrolls));
      }
    });

    int monScrollDown = monGoalPosition - monPosition;
    int monScrollUp = monPosition + numMons - monGoalPosition;
    if (monScrollDown == 0)
      seqEflButton(A, PRESSED); // select mon
    else if (monScrollDown < monScrollUp)
      seqEflScrollAF(monScrollDown);
    else
      seqEflScrollAF(-monScrollUp);
    if (switchWithMon != -1 ) {
      seqEflScrollAF(1 + extraScrolls);
      seq(new EflSelectMonSegment(switchWithMon));
    }
    if (flyTo != Integer.MIN_VALUE) {
      seqEflScrollAF(flyScrolls);
      seqEflScrollA(flyTo);
    }
	}
}
