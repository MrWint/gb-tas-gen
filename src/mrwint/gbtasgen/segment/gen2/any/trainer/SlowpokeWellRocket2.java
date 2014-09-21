package mrwint.gbtasgen.segment.gen2.any.trainer;

import static mrwint.gbtasgen.metric.comparator.Comparator.EQUAL;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.move.DelayUntil;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.WithMetric;
import mrwint.gbtasgen.move.gen2.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Util;


public class SlowpokeWellRocket2 extends Segment {

	SequenceSegment sequence;

	public SlowpokeWellRocket2() {
		List<Segment> segments = new ArrayList<Segment>();

		if(Util.isCrystal())
			segments.add(new WalkToSegment(14, 4, false));	// engage rocket 2
		else {
//			segments.add(new WalkToSegment(14, 3));					// align
//			segments.add(new WalkToSegment(13, 3, false));					// align
			segments.add(new WalkToSegment(14, 2));					// align
			segments.add(new MoveSegment(new PressButton(Move.LEFT)));	// face rocket
			segments.add(new MoveSegment(new DelayUntil(new WithMetric(new PressButton(Move.LEFT), true, new CheckEncounterMetric().withStartMove(Move.LEFT), EQUAL, 0))));	// face rocket
			segments.add(new MoveSegment(new OverworldInteract()));	// engage rocket
		}

		segments.add(new InitFightSegment(2));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // zubat
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith()};
			kems.maxOwnDamage = 0;
			kems.attackCount[3][1] = 1; // 1x water gun crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(NewEnemyMonSegment.any(35));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // ekans
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith(35 /*wrap*/)};
			kems.maxOwnDamage = 0;
			kems.attackCount[3][0] = 1; // 1x water gun
			kems.attackCount[3][1] = 1; // 1x water gun crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(new EndFightSegment(1));
		segments.add(new WalkToSegment(14, 3));			// align
		segments.add(new WalkToSegment(12, 3));			// align
		segments.add(new WalkToSegment(7, 6, false));	// engage rocket 3

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
