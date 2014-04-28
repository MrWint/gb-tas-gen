package mrwint.gbtasgen.segment.gen1;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class NuggetBridgeSegment_6 extends Segment {

	SequenceSegment sequence;
	
	public NuggetBridgeSegment_6() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[3][1] = 1; // 1x mega punch crit (needs 14 Atk IV)
			kems.numExpGainers = 2; // level up to 19
			kems.onlyPrintInfo = false;
			segments.add(kems); // mankey
		}
		segments.add(new EndFightSegment(1)); // player defeated enemy

		segments.add(new WalkToSegment(10, 15)); // walk up to rocket
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
