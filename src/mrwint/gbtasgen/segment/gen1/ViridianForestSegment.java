package mrwint.gbtasgen.segment.gen1;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Gen1OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class ViridianForestSegment extends Segment {

	SequenceSegment sequence;
	
	public ViridianForestSegment() {
		List<Segment> segments = new ArrayList<Segment>();
		segments.add(new WalkToSegment(2, 19)); // walk up to trainer
		segments.add(new MoveSegment(new Gen1OverworldInteract(4))); // talk to trainer

		segments.add(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(40)}; // poison sting
			kems.attackCount[0][1] = 4; // 4x scratch crit
			kems.numExpGainers = 2; // level up to 7
			kems.onlyPrintInfo = false;
			segments.add(kems); // Weedle
		}
		segments.add(new EndFightSegment(2)); // player defeated enemy
		
		/*{ // collect hidden potion
			segments.add(new WalkToSegment(1, 19)); // walk up to hidden item
			segments.add(new MoveSegment(new WaitUntilNextInputFrame()));
			segments.add(new MoveSegment(new Wait(1)));
			segments.add(new MoveSegment(new PressButton(Move.UP)));
			segments.add(new MoveSegment(new CollectHiddenItem()));
			segments.add(new TextSegment());
		}*/
		
		segments.add(new WalkToSegment(1, -1, false)); // leave forest
		segments.add(new WalkToSegment(5, 0)); // leave forest house
		segments.add(new WalkToSegment(8, -1)); // enter pewter city

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
