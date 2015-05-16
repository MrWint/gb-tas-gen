package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.WalkStep;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.Util;

public class SurgeSquirtleAlt extends SeqSegment {

	@Override
	public void execute() {
//    seq(new WalkToSegment(3, 0)); // leave house
//    seq(new WalkToSegment(30, 9)); // engage rocket
//		seq(new InitFightSegment(4)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam
//      kems.numExpGainers = 2; // level up to 24
//			seq(kems); // machop
//		}
//    {
//      seq(new SkipTextsSegment(5)); // trying to learn
//      seq(new SkipTextsSegment(1, true)); // yes, overwrite
//      seq(new TextSegment(Move.B));
//      seq(Segment.repress(Move.DOWN | Move.A)); // leer
//      seq(new TextSegment(Move.A, false)); // 1,2 and
//      seqButton(Move.B); // skip 30f
//      seqButton(Move.B);
//      seq(new TextSegment()); // poof
//      seqButton(Move.A); // skip 30f
//      seqButton(Move.B);
//      seq(new SkipTextsSegment(3)); // learned Bite
//    }
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[1][1] = 1; // bite crit
//			seq(kems); // drowzee
//		}
//		seq(new EndFightSegment(2)); // player defeated enemy
//		seq(new SkipTextsSegment(3)); // after rival battle texts
//
//    seq(new WalkToSegment(28, 36)); // leave cerulean
//    seq(new WalkToSegment(17, 27)); // enter passage
//    seq(new WalkToSegment(4, 4)); // enter passage
//    seq(new WalkToSegment(2, 41)); // walk passage
//    seq(new WalkToSegment(4, 8, false)); // leave passage
//    seq(new WalkToSegment(11, 28)); // engage trainer
//    seq(new WalkToSegment(11, 29)); // engage trainer
//    seqMove(new OverworldInteract(5)); // talk to trainer
//
//    save("sua1");
////    load("sua1");
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[1][1] = 1; // 1x bite crit
//			seq(kems); // pidgey
//		}
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // 1x bite crit
//      seq(kems); // pidgey
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // 1x bite crit
//      seq(kems); // pidgey
//    }
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(10, 31)); // walk up to trainer
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // 1x bite crit
//			seq(kems); // spearow
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.numExpGainers = 2; // level up to 25
//			seq(kems); // raticate
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//    save("sua2");
////    load("sua2");
//
//    seq(new WalkToSegment(9, 36)); // enter vermilion
//
//    seq(new WalkToSegment(18, 30)); // ss anne
//    seq(new SkipTextsSegment(4)); // flash ticket
//    seq(new WalkToSegment(18, 32, false)); // enter ss anne
//    seq(new WalkToSegment(14, 3, false)); // enter ss anne
//    seq(new WalkToSegment(7, 7)); // stairs
//    seq(new WalkToSegment(2, 6)); // stairs
//    seq(new WalkToSegment(36, 8, false).setBlockAllWarps(true)); // engage rival
//
//		seq(new InitFightSegment(7)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
////      kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.attackCount[0][1] = 1; // mega punch crit
//			seq(kems); // pidgeotto
//		}
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // raticate
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // kadabra
//    }
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[1][0] = 1; // 1x bite
//      kems.attackCount[1][1] = 1; // 1x bite crit
//      kems.numExpGainers = 2; // level up to 26
//			seq(kems); // ivysaur
//		}
//		seq(new EndFightSegment(3)); // player defeated enemy
//    seq(new SkipTextsSegment(5)); // after battle text
//    seq(new WalkToSegment(36, 4)); // stairs
//    seq(new WalkToSegment(4, 4)); // engage captain
//    seq(new WalkToSegment(4, 3)); // engage captain
//    seqMove(new OverworldInteract(1)); // talk to captain
//    seq(new SkipTextsSegment(4)); // captain
//    seq(new TextSegment()); // rub
//    seq(new SkipTextsSegment(9)); // captain
//    seq(new WalkToSegment(0, 7)); // stairs
//    seq(new WalkToSegment(2, 4, false).setBlockAllWarps(true)); // stairs
//    seq(new WalkToSegment(7, 7)); // leave ss.anne
//
//    save("sua3");
//    load("sua3");
//
//    seq(new WalkToSegment(26, 0, false)); // leave ss.anne
//    delay(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqButton(Move.UP);
//        seqMetric(new StateResettingMetric() {
//          @Override
//          public int getMetricInternal() {
//            Util.runToAddressNoLimit(0, 0, 0x197ca); // after setting first trash can
//            return Gb.readMemory(0xd743); // first trash can
//          }
//        }, Comparator.EQUAL, 4);
//      }
//    });
//
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(2)); // items
//      seq(Segment.scrollFastAF(6 + 1)); // HM01
//      seq(Segment.repress(Move.A));
//      seq(new SkipTextsSegment(2)); // booted up HM, contains xyz
//      seq(new SkipTextsSegment(1, true)); // learn
//      seq(Segment.scrollAF(2)); // sandshrew
//      seq(new SkipTextsSegment(1, true)); // learned HM
//      seqButton(Move.B); // close menu
//      seqButton(Move.START); // close menu
//    }
//    seq(new WalkToSegment(15, 16)); // go to bush
//    seq(new WalkToSegment(15, 17)); // go to bush
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(-1)); // mon
//      seq(Segment.repress(Move.A)); // sandshrew
//      seq(Segment.repress(Move.A)); // cut
//      seqButton(Move.B); // hacked away (to text scroll)?
//    }
//    seq(new WalkToSegment(12, 19)); // enter gym
//    {
//      seq(new WalkToSegment(4, 9)); // go to trash can
//      seqButton(Move.LEFT); // turn left
//    }
////    {
////      seq(new WalkToSegment(4, 11)); // go to trash can
////      seqButton(Move.RIGHT); // turn left
////    }
//    delay(new SeqSegment() { // activate first can
//      @Override
//      protected void execute() {
//        seqButton(Move.A);
//        seqMetric(new StateResettingMetric() {
//          @Override
//          public int getMetricInternal() {
//            Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.printLetterDelayJoypadAddress); // after setting second trash can
//            return Gb.readMemory(0xd744); // first trash can
//          }
//        }, Comparator.EQUAL, 7);
//      }
//    });
//    seq(new SkipTextsSegment(4)); // opened first lock
//    {
//      seqMove(new WalkStep(Move.RIGHT, false, true), 0); // turn right
//      seqSkipInput(1); // allow update
//    }
////    {
////      seq(new WalkToSegment(4, 9)); // go to surge
////      seqButton(Move.RIGHT);
////    }
//    seqButton(Move.A);
//    seq(new SkipTextsSegment(2)); // opened second lock
//    seq(new WalkToSegment(5, 3)); // go to surge
//    seq(new WalkToSegment(5, 2)); // go to surge
//    seqMove(new OverworldInteract(1)); // talk to surge
//
//		seq(new InitFightSegment(10)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
////      kems.attackCount[3][1] = 1; // bubblebeam crit
//			kems.attackCount[0][1] = 1; // mega punch crit
//			seq(kems); // voltorb
//		}
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[1][0] = 1; // bite
//      seq(kems); // pikachu
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(85)}; // thunderbolt
//      kems.attackCount[3][1] = 1; // bubblebeam crit // TODO: speed fall
//      kems.attackCount[1][0] = 1; // bite
//      kems.numExpGainers = 2; // level up to 27
//      seq(kems); // raichu
//    }
//		seq(new EndFightSegment(3)); // player defeated enemy
//    seq(new SkipTextsSegment(8)); // after battle texts
//    seq(new WalkToSegment(5, 18, false)); // leave gym
	}
}
