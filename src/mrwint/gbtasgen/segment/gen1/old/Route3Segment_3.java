package mrwint.gbtasgen.segment.gen1.old;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class Route3Segment_3 extends Segment {

	SequenceSegment sequence;
	
	public Route3Segment_3() {
		List<Segment> segments = new ArrayList<Segment>();


		segments.add(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[2][0] = 1; // ember
			kems.numExpGainers = 2; // level up to 12
			kems.onlyPrintInfo = false;
			segments.add(kems); // Weedle
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[2][0] = 1; // ember
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems); // Kakuna
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
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
			kems.numExpGainers = 2; // level up to 13
			kems.onlyPrintInfo = false;
			segments.add(kems); // Metapod
		}
		segments.add(new EndFightSegment(2)); // player defeated enemy
		
		segments.add(new WalkToSegment(24, 6, false)); // walk up to trainer
		segments.add(new MoveSegment(new OverworldInteract(8))); // talk to trainer

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
