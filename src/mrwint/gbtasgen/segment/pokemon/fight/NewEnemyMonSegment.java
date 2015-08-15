package mrwint.gbtasgen.segment.pokemon.fight;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class NewEnemyMonSegment implements Segment {

	SequenceSegment sequence;

	private NewEnemyMonSegment(int... enemyInitialMove) {
		List<Segment> segments = new ArrayList<Segment>();

		if (PokemonUtil.isGen1()) {
			segments.add(new TextSegment()); // trainer sent out mon
		} else {
			segments.add(new TextSegment()); // trainer sent out ...
			segments.add(new DelayMoveSegment(new DelayMoveSegment.PressButtonFactory(Move.B), // scroll
					new SeqSegment() {
    			  public void execute() {
              seqUnbounded(new TextSegment(Move.A,false)); // mon!
              seqMetric(KillEnemyMonSegment.CheckEnemyMoveMetric.noKeys(enemyInitialMove));
              seqWait(1); // skip last frame of text box
    			  }
    			}));
		}

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}

	public static NewEnemyMonSegment any(int... enemyInitialMove) {
		return new NewEnemyMonSegment(enemyInitialMove);
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
