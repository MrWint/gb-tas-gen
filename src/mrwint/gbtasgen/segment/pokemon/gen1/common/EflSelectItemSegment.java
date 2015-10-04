package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflSelectItemSegment extends SeqSegment {

  int item;
  int itemPointerPosition, itemScrollPosition;
  int itemGoalPosition = -1;
	public EflSelectItemSegment(int item) {
	  EflUtil.assertEfl();

		this.item = item;
	}

  protected boolean debugOutput = false;
  public EflSelectItemSegment withDebugOutput() {
    debugOutput = true;
    return this;
  }

  protected boolean use = false;
  public EflSelectItemSegment andUse() {
    use = true;
    return this;
  }

  protected boolean fromOverworld = false;
  public EflSelectItemSegment fromOverworld() {
    fromOverworld = true;
    return this;
  }

  protected boolean fromMainMenu = false;
  public EflSelectItemSegment fromMainMenu() {
    fromMainMenu = true;
    return this;
  }

	@Override
	public void execute() {
	  if (fromOverworld)
	    seq(new EflSelectMainMenuSegment(Constants.MENU_ITEM).fromOverworld()); // open item menu
	  else if (fromMainMenu)
      seq(new EflSelectMainMenuSegment(Constants.MENU_ITEM)); // open item menu

	  seqSample(() -> {
      itemPointerPosition = curGb.readMemory(curGb.pokemon.itemMenuPointerIndexAddress);
      itemScrollPosition = curGb.readMemory(curGb.pokemon.itemMenuScrollIndexAddress);
      int numItems = curGb.readMemory(curGb.pokemon.numItemsAddress);
      for (int i = 0; i < numItems; i++) {
        int curItem = curGb.readMemory(curGb.pokemon.numItemsAddress + 2*i + 1);
        if (curItem == item) {
          itemGoalPosition = i;
          break;
        }
      }
      if (itemGoalPosition == -1)
        throw new IllegalStateException("Item " + item + " not found.");

      if (debugOutput) {
        System.out.println(String.format("item pos: %s+%s", itemScrollPosition, itemPointerPosition));
      }
    });

    int itemScroll = itemGoalPosition - itemPointerPosition - itemScrollPosition;
    if (itemScroll == 0)
      seqEflButton(A, PRESSED); // select item
    else if (itemScroll + itemPointerPosition >= 0 && itemScroll + itemPointerPosition < 3)
      seqEflScrollFastAF(itemScroll);
    else
      seqEflScrollFastA(itemScroll);
    if (use)
      seqEflButton(A, PRESSED); // use item
	}
}
