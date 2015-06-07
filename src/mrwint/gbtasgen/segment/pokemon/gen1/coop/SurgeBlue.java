package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class SurgeBlue extends SeqSegment {

	@Override
	public void execute() {
//    seq(new EflWalkToSegment(3, 0)); // leave house
//    seq(new EflWalkToSegment(30, 9)); // engage rocket
//		seq(new EflInitFightSegment(4)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.numExpGainers = 2; // level up to 24
//			seq(kems); // machop
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // mega punch crit
////      kems.attackCount[1][1] = 1; // bite crit // Wartortle
//			seq(kems); // drowzee
//		}
//		seq(new EflEndFightSegment(2)); // player defeated enemy
//
//		seq(new EflEvolutionSegment(true));
//
//		seq(new EflSkipTextsSegment(3)); // after rival battle texts
//
//    seq(new EflWalkToSegment(28, 36)); // leave cerulean
//    seq(new EflWalkToSegment(17, 27)); // enter passage
//    seq(new EflWalkToSegment(4, 4)); // enter passage
//    seq(new EflWalkToSegment(2, 41)); // walk passage
//    seq(new EflWalkToSegment(4, 8, false)); // leave passage
//    seq(new EflWalkToSegment(11, 28)); // engage trainer
//    seq(new EflWalkToSegment(11, 29)); // engage trainer
//    seqMove(new EflOverworldInteract(5)); // talk to trainer
//
//    save("su1");
//    load("su1");
//
//		seq(new EflInitFightSegment(1)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.attackCount[1][1] = 1; // 1x bite crit
//			seq(kems); // pidgey
//		}
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // 1x bite crit
//      seq(kems); // pidgey
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // 1x bite crit
//      seq(kems); // pidgey
//    }
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//		seq(new EflWalkToSegment(10, 31)); // walk up to trainer
//
//		seq(new EflInitFightSegment(1)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // 1x bite crit
//			seq(kems); // spearow
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
//			kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.numExpGainers = 2; // level up to 25
//			seq(kems); // raticate
//		}
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    seq(new EflEvolutionSegment(true));
//
//    save("su2");
//    load("su2");
//
//    seq(new EflWalkToSegment(9, 36)); // enter vermilion
//
//    seq(new EflWalkToSegment(18, 30)); // ss anne
//    seq(new EflSkipTextsSegment(4)); // flash ticket
//    seq(new EflWalkToSegment(18, 32, false)); // enter ss anne
//    seq(new EflWalkToSegment(14, 3, false)); // enter ss anne
//    seq(new EflWalkToSegment(7, 7)); // stairs
//    seq(new EflWalkToSegment(2, 6)); // stairs
//    seq(new EflWalkToSegment(36, 8, false).setBlockAllWarps(true)); // engage rival
//
//		seq(new EflInitFightSegment(7)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
////      kems.attackCount[0][1] = 1; // mega punch crit // Wartortle
//			seq(kems); // pidgeotto
//		}
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
//      kems.attackCount[0][1] = 1; // mega punch crit
////      kems.attackCount[1][1] = 1; // bite crit // Wartortle
//      seq(kems); // raticate
//    }
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//
//    seq(new EflSwitchPokemonSegment(-2, EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50))); // switch to metapod
//    seq(new EflSwitchPokemonSegment(2, EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50))); // switch to squirtle
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[1][1] = 1; // bite crit
//      kems.numExpGainers = 3; // metapod level up to 10
//      seq(kems); // kadabra
//    }
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(-1, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 45))); // switch to weedle
//    seq(new EflSwitchPokemonSegment(1, EflEnemyMoveDesc.missWith(22))); // switch to squirtle
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 2; // bite crit // Wartortle
////      kems.attackCount[1][0] = 1; // bite // Wartortle
////      kems.attackCount[1][1] = 1; // bite crit // Wartortle
//      kems.numExpGainers = 4; // level up to 26, weedle level up to 9
//			seq(kems); // ivysaur
//		}
//		seq(new EflEndFightSegment(3)); // player defeated enemy
//
//    seq(new EflEvolutionSegment(true)); // Squirtle evolution
//    seq(new EflEvolutionSegment()); // Metapod evolution
//    seq(new EflEvolutionSegment()); // Weedle evolution
//
//    seq(new EflSkipTextsSegment(5)); // after battle text
//    seq(new EflWalkToSegment(36, 4)); // stairs
//    seq(new EflWalkToSegment(4, 4)); // engage captain
//    seq(new EflWalkToSegment(4, 3)); // engage captain
//    seqMove(new EflOverworldInteract(1)); // talk to captain
//    seq(new EflSkipTextsSegment(4)); // captain
//    seq(new EflTextSegment()); // rub
//    seq(new EflSkipTextsSegment(9)); // captain
//    seq(new EflWalkToSegment(0, 7)); // stairs
//    seq(new EflWalkToSegment(2, 4, false).setBlockAllWarps(true)); // stairs
//    seq(new EflWalkToSegment(7, 7)); // leave ss.anne
//
//    save("su3");
    load("su3");

    seq(new EflWalkToSegment(26, -1, false).setMaxBufferSize(0)); // leave ss.anne
    seqEflSkipInputUnbounded(5); // Watch SS Anne
    seq(new EflWalkToSegment(40, 15).setMaxBufferSize(0)); // route 11

    seq(new EflWalkToSegment(10, 6).setMaxBufferSize(0)); // grass
    seq(new EflWalkToSegment(11, 6).setMaxBufferSize(0)); // grass

    seq(new EflEncounterSegment(0x30, Move.RIGHT)); // Drowzee
    seq(new EflCatchMonSegment(2).withBufferSize(0));

    save("tmp");
    load("tmp");

    seq(new EflWalkToSegment(4, 5).setMaxBufferSize(0)); // diglett's cave
    seq(new EflWalkToSegment(4, 4).setMaxBufferSize(0)); // diglett's cave
    seq(new EflWalkToSegment(36, 31).setMaxBufferSize(0)); // cave

//    seq(new EflEncounterSegment(0x76, Move.LEFT)); // Dugtrio
    seq(new EflEncounterSegment(0x3b, Move.LEFT)); // Diglett
    seq(new EflCatchMonSegment(2).withBufferSize(0));

    save("tmp2");
    load("tmp2");

    seq(new EflWalkToSegment(35, 32).setMaxBufferSize(0)); // cave
    seq(new EflWalkToSegment(36, 32).setMaxBufferSize(0)); // cave

//    seq(new EflEncounterSegment(0x3b, Move.RIGHT)); // Diglett
    seq(new EflEncounterSegment(0x76, Move.RIGHT)); // Dugtrio
    seq(new EflCatchMonSegment(2).withBufferSize(0));

    seq(new EflWalkToSegment(37, 31).setMaxBufferSize(0)); // leave diglett's cave
    seq(new EflWalkToSegment(3, 8, false).setMaxBufferSize(0)); // leave diglett's cave

    seq(new EflWalkToSegment(-1, 6).setMaxBufferSize(0)); // enter vermilion

    save("su4");
    load("su4");

    seq(new EflWalkToSegment(15, 13)); // trade house
    seq(new EflWalkToSegment(3, 7)); // trade
    seq(new EflWalkToSegment(3, 6)); // trade
    seqMove(new EflOverworldInteract(1)); // trade
    seq(new EflSkipTextsSegment(1));
    seq(new EflSkipTextsSegment(1, true)); // yes
    seqEflSkipInput(1);
    seqEflScrollAF(1); // spearow
    seq(new EflSkipTextsSegment(1)); // connect cables
    seq(new EflSkipTextsSegment(3)); // traded x for y, thanks
    seq(new EflWalkToSegment(3, 7, false)); // leave trade house

    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButton(Move.DOWN);
        seqMetric(new StateResettingMetric() {
          @Override
          public int getMetricInternal() {
            EflUtil.runToAddressNoLimit(0, 0, 0x197ca); // after setting first trash can
            return curGb.readMemory(0xd743); // first trash can
          }
        }, Comparator.EQUAL, 8);
      }
    });

    {
      seqEflButton(Move.START, PressMetric.PRESSED);
      seqEflScrollA(2); // items
      seqEflScrollFastAF(9 + 1); // HM01
      seqEflSkipInput(1);
      seqEflButton(Move.A);
      seq(new EflSkipTextsSegment(2)); // booted up HM, contains xyz
      seq(new EflSkipTextsSegment(1, true)); // learn
      seqEflScrollAF(-2); // sandshrew
      seq(new EflSkipTextsSegment(1, true)); // learned HM
      seqEflButton(Move.B); // close menu
      seqEflButton(Move.START); // close menu
    }
    seq(new EflWalkToSegment(15, 17)); // go to bush
    {
      seqEflButton(Move.START, PressMetric.PRESSED);
      seqEflScrollA(-1); // mon
      seqEflSkipInput(1);
      seqEflButton(Move.A); // sandshrew
      seqEflSkipInput(1);
      seqEflButton(Move.A); // cut
      seqEflButton(Move.B); // hacked away (to text scroll)?
    }
    seq(new EflWalkToSegment(12, 19)); // enter gym
//    {
//      seq(new EflWalkToSegment(4, 9)); // go to trash can
//      seqEflButton(Move.LEFT); // turn left
//    }
    {
      seq(new EflWalkToSegment(4, 11)); // go to trash can
      seqEflButton(Move.RIGHT); // turn left
    }
    delayEfl(new SeqSegment() { // activate first can
      @Override
      protected void execute() {
        seqEflButton(Move.A);
        seqMetric(new StateResettingMetric() {
          @Override
          public int getMetricInternal() {
            EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.printLetterDelayJoypadAddress); // after setting second trash can
            return curGb.readMemory(0xd744); // first trash can
          }
        }, Comparator.EQUAL, 7);
      }
    });
    seq(new EflSkipTextsSegment(4)); // opened first lock
//    {
//      seqMove(new EflWalkStep(Move.RIGHT, false, true), 0); // turn right
//      seqEflSkipInput(1); // allow update
//    }
    {
      seq(new EflWalkToSegment(4, 9)); // go to surge
      seqEflButton(Move.RIGHT);
    }
    seqEflButton(Move.A);
    seq(new EflSkipTextsSegment(2)); // opened second lock
    seq(new EflWalkToSegment(5, 3)); // go to surge
    seq(new EflWalkToSegment(5, 2)); // go to surge
    seqMove(new EflOverworldInteract(1)); // talk to surge

    save("tmp");
    load("tmp");

		seq(new EflInitFightSegment(10)); // start fight
    seq(new EflSwitchPokemonSegment(-2, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103))); // switch to Kakuna
    seq(new EflSwitchPokemonSegment(2, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103))); // switch to squirtle
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
//      kems.attackCount[3][1] = 1; // bubblebeam crit
			kems.attackCount[0][1] = 1; // mega punch crit
      kems.numExpGainers = 3; // kakuna level up to 10
			seq(kems); // voltorb
		}
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(84)}; // thundershock
      kems.attackCount[1][1] = 1; // bite crit
//      kems.attackCount[1][0] = 1; // bite // Wartortle
      seq(kems); // pikachu
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(85)}; // thunderbolt
      kems.attackCount[3][1] = 1; // bubblebeam crit // TODO: speed fall
      kems.attackCount[1][1] = 1; // bite crit
//      kems.attackCount[1][0] = 1; // bite // Wartortle
      seq(kems); // raichu
    }
		seq(new EflEndFightSegment(3)); // player defeated enemy

		seq(new EflEvolutionSegment()); // Kakuna

    seq(new EflSkipTextsSegment(8)); // after battle texts
    seq(new EflWalkToSegment(5, 18, false)); // leave gym
	}
}
