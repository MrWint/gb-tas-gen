package mrwint.gbtasgen.segment.gen1;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class Route3Segment_4 extends Segment {

	SequenceSegment sequence;
	
	public Route3Segment_4() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new InitFightSegment(1)); // start fight

		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[2][0] = 1; // ember
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems); // Caterpie
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[2][0] = 1; // ember
			kems.numExpGainers = 2; // level up to 14
			kems.onlyPrintInfo = false;
			segments.add(kems); // Metapod
		}
		segments.add(new EndFightSegment(1)); // player defeated enemy

		segments.add(new WalkToSegment(59, -1, false)); // leave route 3
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
