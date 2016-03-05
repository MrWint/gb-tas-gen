package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.metric.comparator.Comparator.GREATER_EQUAL;
import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCRATCH;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckAttackMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveDamage;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class Rival1FightAgainRed extends SeqSegment {

	@Override
  public void execute() {
		seq(new EflInitFightSegment(0));
//		{
//			EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), GROWL)};
//      kems.attackCount[0][0] = 2; // tackle
//      kems.attackCount[0][1] = 2; // tackle crit
//			kems.numExpGainers = 2; // Bulbasaur, level up to 6
//			seq(kems); // Charmander
//		}
//
//		seq(new EflEndFightSegment(3)); // player defeated enemy
//		seq(new EflSkipTextsSegment(4)); // rival after battle texts


//    seqEflButton(A, PRESSED); // Fight
////    seqEflSkipInput(1);
//    seqEflButton(DOWN);
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButton(A); // select move
//        seqMetric(new EflCheckMoveOrderMetric(false, SCRATCH));
//        seqUnbounded(new EflTextSegment(A)); // enemy uses scratch
//        seqMetric(new EflCheckMoveDamage(false, false, 0, 5, 5, false), GREATER_EQUAL, 5);
//        seqUnbounded(new EflTextSegment(A)); // Bulbasaur uses
//        seqMetric(new CheckLowerStatEffectMisses());
////        seqMetric(new CheckAttackMisses());
//      }
//    });
//    seq(new EflSkipTextsSegment(1)); // but it failed
    for (int i = 0; i < 3; i++) {
      save("tmp" + i);
//      load("tmp" + i);
      final int fi = i;
      seqEflButton(A, PRESSED); // Fight
      if (i == 0)
//        seqEflSkipInput(1);
        seqEflButton(DOWN);
      else
        seqEflSkipInput(1);
      delayEfl(new SeqSegment() {
        @Override
        protected void execute() {
          seqEflButton(A); // select move
          seqMetric(new EflCheckMoveOrderMetric(false, SCRATCH));
          seqUnbounded(new EflTextSegment(A)); // enemy uses scratch
          if (fi == 2)
            seqMetric(new EflCheckMoveDamage(false, false, 0, 5, 5, false), GREATER_EQUAL, 5);
          else
            seqMetric(new EflCheckMoveDamage(true, false, 0, 7, 7, false), GREATER_EQUAL, 7);
        }
      });
      if (i == 2) // dead
        break;
      seq(new EflTextSegment(A)); // critical hit
      delayEfl(new SeqSegment() {
        @Override
        protected void execute() {
          seqEflButton(B); // skip text
          seqUnbounded(new EflTextSegment(A)); // Bulbasaur uses
          seqMetric(new CheckLowerStatEffectMisses());
//          seqMetric(new CheckAttackMisses());
        }
      });
      seq(new EflSkipTextsSegment(1)); // but it failed
    }
//    seqEflButton(B); // skip text
    save("tmp4");
    load("tmp4");
    seq(new EflSkipTextsSegment(2)); // Bulbasaur fainted, Blue won.
    seq(new EflSkipTextsSegment(4)); // rival after battle texts
	}
}
