package mrwint.gbtasgen.segment.pokemon.gen1.noww;

import static mrwint.gbtasgen.metric.comparator.Comparator.GREATER_EQUAL;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.CheckMoveDamage;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.CheckMoveOrderMetric;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class RivalFight extends SeqSegment {

	@Override
	public void execute() {
		seq(new InitFightSegment(0));
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
//			kems.attackCount[0][0] = 2; // 2x scratch
//			kems.attackCount[0][1] = 2; // 2x scratch crit
//			kems.numExpGainers = 2; // level up to 6
//			kems.onlyPrintInfo = false;
//			seq(kems); // Squirtle
//		}

		save("tmp");
//		load("tmp");
		for (int i=0;i<4;i++) {
			final boolean last = i == 3;
			seq(Segment.repress(Move.A));
			if (i == 0)
				seqButton(Move.DOWN);
			else
				seq(Segment.skip(1));
			seq(new DelayMoveSegment(new SeqSegment() {
				@Override
				protected void execute() {
					seqMetric(new CheckMoveOrderMetric(true, new int[]{33}, Move.A));
					seqButton(Move.A);
					seq(new TextSegment(Move.A, false, 0));
					seqMetric(new Metric() {
						@Override
						public int getMetric() {
							return curGb.readMemory(curGb.pokemon.fightCurEnemyMoveAddress) == 33 ? 1 : 0;
						}
					});
					seqMetric(new CheckLowerStatEffectMisses());
					seqMove(new Wait(1));
					seq(new TextSegment()); // it failed
				}
			}));
			seq(new DelayMoveSegment(new SeqSegment() {
				@Override
				protected void execute() {
					seqButton(Move.B);
					seq(new TextSegment(Move.A, false, 0));
					seqMetric(new CheckMoveDamage(false, false, true, false, false, false, 0), GREATER_EQUAL, last ? 4 : 5);
					seqMove(new Wait(1));
				}
			}));
		}
//		for (int i=0;i<4;i++) {
//			final boolean last = i == 3;
//			seq(Segment.repress(Move.A));
//			seq(Segment.skip(1));
//			seq(new DelayMoveSegment(new SeqSegment() {
//				@Override
//				protected void execute() {
//					seq(new CheckMoveOrderMetric(true, new int[]{33}, Move.A));
//					seq(Move.A);
//					seq(new TextSegment(Move.A, false, 0));
//					seq(new Metric() {
//						@Override
//						public int getMetric() {
//							return Gb.readMemory(RomInfo.rom.fightCurEnemyMoveAddress) == 33 ? 1 : 0;
//						}
//					});
//					seq(new CheckMoveDamage(false, false, true, false, false, false, 0), new Equal(), 3);
//					seq(new MoveSegment(new Wait(1), 0, 0));
//					seq(new TextSegment(Move.A, false, 0));
//					seq(new CheckMoveDamage(false, false, true, false, false, false, 0), new GreaterEqual(), last ? 4 : 5);
//					seq(new Wait(1));
//				}
//			}));
//		}
		seq(new SkipTextsSegment(2)); // Charmander fainted, Blue won.
//
//		seq(new EndFightSegment(3)); // player defeated enemy
		seq(new SkipTextsSegment(4)); // rival after battle texts
	}
}
