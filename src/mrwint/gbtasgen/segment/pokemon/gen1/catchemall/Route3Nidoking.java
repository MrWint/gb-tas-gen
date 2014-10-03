package mrwint.gbtasgen.segment.pokemon.gen1.catchemall;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.CatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class Route3Nidoking extends SeqSegment {

	@Override
	public void execute() {
		
//		seq(new WalkToSegment(11, 6)); // walk up to trainer
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x tackle
//			kems.onlyPrintInfo = false;
//			seq(kems); // Caterpie
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.thrashContinues = true;
//			kems.attackCount[3][0] = 1; // 1x tackle
//			kems.onlyPrintInfo = false;
//			seq(kems); // Weedle
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.thrashContinues = true;
//			kems.attackCount[3][0] = 1; // 1x tackle
//			kems.onlyPrintInfo = false;
//			seq(kems); // Caterpie
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//		
//		seq(new WalkToSegment(12, 4)); // walk up to shorts guy
//		seq(new WalkToSegment(13, 4)); // walk up to shorts guy
//		seq(new MoveSegment(new OverworldInteract(3))); // talk to shorts guy
//		
//		save("r31n");
//		load("r31n");
//
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.onlyPrintInfo = false;
//			seq(kems); // Rattata
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.thrashContinues = true;
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.onlyPrintInfo = false;
//			seq(kems); // Ekans
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(19, 4)); // walk up to trainer
//
//		save("r32n");
//		load("r32n");
//
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.onlyPrintInfo = false;
//			seq(kems); // Weedle
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.thrashContinues = true;
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.onlyPrintInfo = false;
//			seq(kems); // Kakuna
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(-1, 9)); // walk back to pewter
//		seq(new WalkToSegment(40, 17)); // walk back to Route 3
//		seq(new WalkToSegment(24, 6, false)); // walk up to trainer
//		seq(new MoveSegment(new OverworldInteract(8))); // talk to trainer
//
//		save("r33n");
//		load("r33n");
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.onlyPrintInfo = false;
//			seq(kems); // Caterpie
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.thrashContinues = true;
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.onlyPrintInfo = false;
//			seq(kems); // Metapod
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(33, 11)); // go in grass
//		delay(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(Move.RIGHT);
//				seq(new CheckEncounterMetric(100, 0)); // Jigglypuff
//			}
//		});
//		seq(new CatchMonSegment(0));
//
//		seq(new WalkToSegment(36, 11)); // go in grass
//		delay(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(Move.RIGHT);
//				seq(new CheckEncounterMetric(5, 0)); // Spearow
//			}
//		});
//		seq(new CatchMonSegment(0));
//
//		seq(new WalkToSegment(59, -1, false)); // leave route 3
	}
}
