package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflSelectMainMenuSegment extends SeqSegment {

  int menu;
  int mainPosition;
	public EflSelectMainMenuSegment(int menu) {
	  EflUtil.assertEfl();

		this.menu = menu;
	}

  protected boolean debugOutput = false;
  public EflSelectMainMenuSegment withDebugOutput() {
    debugOutput = true;
    return this;
  }

  protected boolean fromOverworld = false;
  public EflSelectMainMenuSegment fromOverworld() {
    fromOverworld = true;
    return this;
  }

	@Override
	public void execute() {
	  if (fromOverworld)
	    seqEflButton(START, PRESSED); // open main menu
    seqSample(() -> {
      mainPosition = curGb.readMemory(curGb.pokemon.mainMenuIndexAddress);

      if (debugOutput) {
        System.out.println(String.format("menu pos: %s", mainPosition));
      }
    });
    int menuScroll = menu - mainPosition;
    if (menuScroll == 0)
      seqEflButton(A, PRESSED); // open menu
    else
      seqEflScrollA(menuScroll);
	}
}
