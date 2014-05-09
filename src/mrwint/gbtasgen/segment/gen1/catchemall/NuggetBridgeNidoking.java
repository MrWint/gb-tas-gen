package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.CatchMonSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.ThrashEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class NuggetBridgeNidoking extends SeqSegment {

	@Override
	public void execute() {
//		seq(new InitFightSegment(8)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.thrashNumAdditionalTurns = 3;
//			kems.onlyPrintInfo = false;
//			seq(kems); // pidgeotto
//		}
//		seq(new ThrashEnemyMonSegment()); // abra
//		seq(new ThrashEnemyMonSegment()); // rattata
//		seq(new ThrashEnemyMonSegment()); // squirtle
//		seq(Move.B);
//		seq(new EndFightSegment(2)); // player defeated enemy
//
//		seq(new SkipTextsSegment(14)); // after rival battle texts
//
//		seq(new WalkToSegment(21, -1)); // walk up
//		seq(new WalkToSegment(11, 32)); // walk up to trainer
//		seq(new MoveSegment(new OverworldInteract(7))); // talk to trainer 1
//		
//		save("nb1n");
//		load("nb1n");
//
//		seq(new InitFightSegment(4)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.thrashNumAdditionalTurns = 1;
//			kems.onlyPrintInfo = false;
//			seq(kems); // caterpie
//		}
//		seq(new ThrashEnemyMonSegment()); // weedle
//		seq(Move.B);
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(10, 29)); // walk up to trainer
//		seq(new MoveSegment(new OverworldInteract(6))); // talk to trainer 2
//
//		save("nb2n");
//		load("nb2n");
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.thrashNumAdditionalTurns = 1;
//			kems.onlyPrintInfo = false;
//			seq(kems); // pidgey
//		}
//		seq(new ThrashEnemyMonSegment()); // nidoF
//		seq(Move.B);
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(11, 26)); // walk up to trainer
//		seq(new MoveSegment(new OverworldInteract(5))); // talk to trainer 3
//
//		save("nb3n");
//		load("nb3n");
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.thrashNumAdditionalTurns = 2;
//			kems.onlyPrintInfo = false;
//			seq(kems); // rattata
//		}
//		seq(new ThrashEnemyMonSegment()); // ekans
//		seq(new ThrashEnemyMonSegment()); // zubat
//		seq(Move.B);
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(10, 23)); // walk up to trainer
//		seq(new MoveSegment(new OverworldInteract(4))); // talk to trainer 4
//
//		save("nb4n");
//		load("nb4n");
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.thrashNumAdditionalTurns = 1;
//			kems.onlyPrintInfo = false;
//			seq(kems); // pidgey
//		}
//		seq(new ThrashEnemyMonSegment()); // nidoF
//		seq(Move.B);
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(11, 20)); // walk up to trainer
//		seq(new MoveSegment(new OverworldInteract(3))); // talk to trainer 5
//		
//		save("nb5n");
//		load("nb5n");
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][0] = 1; // 1x tackle
//			kems.onlyPrintInfo = false;
//			seq(kems); // mankey
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(10, 15)); // walk up to rocket
//		
//		save("nb6n");
//		load("nb6n");
//
//		seq(new InitFightSegment(15)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.thrashNumAdditionalTurns = 1;
//			kems.onlyPrintInfo = false;
//			seq(kems); // Rattata
//		}
//		seq(new ThrashEnemyMonSegment()); // zubat
//		seq(Move.B);
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		save("nb7n");
//		load("nb7n");
//
//		seq(new SkipTextsSegment(3)); // after rocket battle texts
//
//		seq(new WalkToSegment(4, 17)); // walk into grass
//		delay(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(Move.DOWN);
//				seq(new CheckEncounterMetric(148, 0)); // Abra
//			}
//		});
//		seq(new CatchMonSegment(0));
//
//		seq(new WalkToSegment(5, 15)); // prepare tfly
//		seq(new WalkToSegment(5, 16)); // prepare tfly
//		seq(new PressButton(Move.START), 0);
//		seq(Segment.scroll(1));
//		seq(Move.A);
//		seq(Move.UP);
//		seq(Move.A);
//		seq(Segment.repress(Move.A));
//		seq(new TextSegment()); // go to last center
//		seq(new WalkToSegment(20, -1)); // enter route 24
//		seq(new WalkToSegment(20, 9)); // enter route 25
//
//		save("nb8n");
//		load("nb8n");
//
//		seq(new WalkToSegment(9, 5)); // align with grass
//		seq(new WalkToSegment(8, 5)); // align with grass
//		seq(new EncounterAndCatchSegment(112, 8, Move.LEFT)); // weedle
//		seq(new WalkToSegment(7, 4)); // align with grass
//		seq(new WalkToSegment(6, 4)); // align with grass
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(123, 8).withSpcDV(0,1,2,3,4), Move.DOWN)); // caterpie
//
//		save("nb9n");
//		load("nb9n");
//
//		seq(new WalkToSegment(15, 7)); // engage hiker
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][0] = 1; // 1x tackle
//			kems.onlyPrintInfo = false;
//			seq(kems); // Onix
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(18,7)); // walk up to trainer 2
//		seq(new WalkToSegment(18,8,false)); // walk up to trainer 2
//		seq(new OverworldInteract(4)); // talk to trainer 2
//
//		save("nb10n");
//		load("nb10n");
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.thrashNumAdditionalTurns = 1;
//			kems.onlyPrintInfo = false;
//			seq(kems); // nidoM
//		}
//		seq(new ThrashEnemyMonSegment()); // nidoF
//		seq(Move.B);
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(24,6)); // walk up to trainer 3
//
//		save("nb11n");
//		load("nb11n");
//
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.thrashNumAdditionalTurns = 1;
//			kems.onlyPrintInfo = false;
//			seq(kems); // rattata
//		}
//		seq(new ThrashEnemyMonSegment()); // ekans
//		seq(Move.B);
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(35,4)); // walk up to trainer 4
//		seq(new WalkToSegment(36,4)); // walk up to trainer 4
//		seq(new OverworldInteract(6)); // talk to trainer 4
//
//		save("nb12n");
//		load("nb12n");
//
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.thrashNumAdditionalTurns = 1;
//			kems.onlyPrintInfo = false;
//			seq(kems); // oddish
//		}
//		seq(new ThrashEnemyMonSegment()); // pidgey
//		seq(new ThrashEnemyMonSegment()); // oddish
//		seq(Move.B);
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(45,3)); // enter bills house
//
//		seq(new WalkToSegment(4,5)); // walk up to bill
//		seq(new WalkToSegment(5,5)); // walk up to bill
//		seq(new OverworldInteract(1)); // talk to bill
//
//		seq(new SkipTextsSegment(10));
//		seq(new SkipTextsSegment(1, true)); // help
//		seq(new SkipTextsSegment(4));
//
//		seq(new WalkToSegment(1,4,false)); // walk up to desk
//		seq(Move.A); // talk to desk
//		seq(new SkipTextsSegment(2));
//
//		seq(new WalkToSegment(4,5)); // walk up to bill
//		seq(new WalkToSegment(4,4,false)); // walk up to bill
//		seq(new OverworldInteract(2)); // talk to bill
//		seq(new SkipTextsSegment(18));
//
//		seq(new WalkToSegment(3,8,false)); // leave
	}
}
