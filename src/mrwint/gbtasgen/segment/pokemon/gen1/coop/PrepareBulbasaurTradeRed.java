package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.B;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class PrepareBulbasaurTradeRed extends SeqSegment {

	@Override
	public void execute() {
  	  
    seq(new EflWalkToSegment(29, 19)); // enter mart
  
    seq(new EflWalkToSegment(3, 5)); // counter
    seq(new EflWalkToSegment(2, 5)); // counter
    seqMove(new EflOverworldInteract(1)); // shopkeep
    {
      seq(new EflSkipTextsSegment(1, true)); // buy
      seq(new EflTextSegment(B));
      seq(new EflBuyItemSegment(0, 15, true)); // 15x poke ball
      seqEflButton(B); // cancel
      seq(new EflSkipTextsSegment(2)); // cancel + bye
    }
    seq(new EflWalkToSegment(3, 8, false)); // leave mart

    seqUnbounded(new EflWalkToSegment(23, 25)); // enter center
    seqUnbounded(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
//    seq(new EflWalkToSegment(11, 2, false)); // cable club // TODO: fix bump
	}
}
