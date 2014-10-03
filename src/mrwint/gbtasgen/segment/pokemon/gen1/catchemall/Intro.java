package mrwint.gbtasgen.segment.pokemon.gen1.catchemall;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.pokemon.TrainerIDMetric;
import mrwint.gbtasgen.move.DelayableMove;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.FindShortestSequenceSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Intro extends SeqSegment {

	@Override
	protected void execute() {
		List<Integer> validTrainerIDs = new ArrayList<>();
		for (int i=0;i<256;i++)
			validTrainerIDs.add((i << 8) | 0x06);
		
		seq(new FindShortestSequenceSegment(new DelayableMove[]{
			Move.press(Move.START),
			Move.press(Move.A),
			Move.press(Move.START),
			Move.press(Move.A)
		}, new TrainerIDMetric(validTrainerIDs.toArray(new Integer[validTrainerIDs.size()]))));
	}
}
