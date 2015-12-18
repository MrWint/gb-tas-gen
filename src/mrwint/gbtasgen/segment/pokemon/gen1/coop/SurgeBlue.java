package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.metric.comparator.Comparator.EQUAL;
import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ABRA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CLEFABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EMBER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM01;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.JIGGLYPUFF;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KAKUNA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGIKARP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.METAPOD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCREECH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SQUIRTLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.THUNDERBOLT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.THUNDER_SHOCK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VINE_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WEEDLE;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeMonBoxSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class SurgeBlue extends SeqSegment {

	@Override
	public void execute() {
//    {
//      seq(new EflWalkToSegment(19, 17)); // center
//      seq(new EflWalkToSegment(13, 4)); // PC // TODO
//      seq(new EflWalkToSegment(13, 3, false)); // PC
//
//      seqMetric(new OutputParty());
//      seqMetric(new OutputBoxMons());
//
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflDepositMonSegment(JIGGLYPUFF));
//        seq(new EflDepositMonSegment(MAGIKARP));
//        seq(new EflDepositMonSegment(CLEFABLE));
//        seq(new EflWithdrawMonSegment(WEEDLE));
//        seq(new EflWithdrawMonSegment(METAPOD));
//        seq(new EflWithdrawMonSegment(ABRA));
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, PRESSED); // cancel
//      }
//      seq(new EflWalkToSegment(4, 6));
//      seq(new EflWalkToSegment(4, 8, false));
//    }

    seq(new EflWalkToSegment(27, 11)); // enter dig house
    seq(new EflWalkToSegment(3, 0)); // leave house
    seq(new EflWalkToSegment(30, 9)); // engage rocket
		seq(new EflInitFightSegment(4)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // bubblebeam crit
      kems.numExpGainers = 2; // Squirtle, level up to 24
			seq(kems); // machop
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][1] = 1; // mega punch crit
			seq(kems); // drowzee
		}
		seq(new EflEndFightSegment(2)); // player defeated enemy

    save("su1");
    load("su1");

		seq(new EflEvolutionSegment(true));

		seq(new EflSkipTextsSegment(3)); // after rival battle texts

    seq(new EflWalkToSegment(28, 36)); // leave cerulean
    seq(new EflWalkToSegment(17, 27)); // enter passage
    seq(new EflWalkToSegment(4, 4)); // enter passage
    seq(new EflWalkToSegment(2, 41)); // walk passage
    seq(new EflWalkToSegment(4, 8, false)); // leave passage
    seq(new EflWalkToSegment(11, 28)); // engage trainer
    seq(new EflWalkToSegment(11, 29)); // engage trainer
    seqMove(new EflOverworldInteract(5)); // talk to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.attackCount[1][1] = 1; // bite crit
			seq(kems); // pidgey
		}
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // bite crit
      seq(kems); // pidgey
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // bite crit
      seq(kems); // pidgey
    }
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("su2");
    load("su2");

		seq(new EflWalkToSegment(10, 31)); // walk up to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // 1x bite crit
			seq(kems); // spearow
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
			kems.attackCount[3][1] = 1; // bubblebeam crit
      kems.numExpGainers = 2; // Squirtle, level up to 25
			seq(kems); // raticate
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("su3");
    load("su3");

    seq(new EflEvolutionSegment(true));

    seq(new EflWalkToSegment(9, 36)); // enter vermilion

    seq(new EflWalkToSegment(18, 30)); // ss anne
    seq(new EflSkipTextsSegment(4)); // flash ticket
    seq(new EflWalkToSegment(18, 32, false)); // enter ss anne
    seq(new EflWalkToSegment(14, 3, false)); // enter ss anne
    seq(new EflWalkToSegment(7, 7)); // stairs
    seq(new EflWalkToSegment(2, 6)); // stairs

    seq(new EflWalkToSegment(36, 8, false).setBlockAllWarps(true)); // engage rival

		seq(new EflInitFightSegment(7)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.attackCount[0][1] = 1; // mega punch crit // Wartortle
			seq(kems); // pidgeotto
		}
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
      kems.attackCount[0][1] = 1; // mega punch crit
//      kems.attackCount[1][1] = 1; // bite crit // Wartortle
//      kems.numExpGainers = 2; // level up to 26
      seq(kems); // raticate
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    seq(new EflSwitchPokemonSegment(METAPOD, EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)));
    seq(new EflSwitchPokemonSegment(SQUIRTLE, EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)));
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
      kems.attackCount[1][1] = 1; // bite crit
      kems.numExpGainers = 3; // Squirtle, Metapod, level up to 10
      seq(kems); // kadabra
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    seq(new EflSwitchPokemonSegment(WEEDLE, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), GROWL)));
    seq(new EflSwitchPokemonSegment(SQUIRTLE, EflEnemyMoveDesc.missWith(VINE_WHIP)));
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 2; // bite crit
//      kems.numExpGainers = 3; // weedle level up to 9
      kems.numExpGainers = 4; // Squirtle, level up to 26, Weedle, level up to 9
			seq(kems); // ivysaur
		}
		seq(new EflEndFightSegment(3)); // player defeated enemy

    save("su4");
    load("su4");

    seq(new EflEvolutionSegment(true)); // Squirtle evolution
    seq(new EflEvolutionSegment()); // Metapod evolution
    seq(new EflEvolutionSegment()); // Weedle evolution

    seq(new EflSkipTextsSegment(5)); // after battle text
    seq(new EflWalkToSegment(36, 4)); // stairs
    seq(new EflWalkToSegment(4, 4)); // engage captain
    seq(new EflWalkToSegment(4, 3)); // engage captain
    seqMove(new EflOverworldInteract(1)); // talk to captain
    seq(new EflSkipTextsSegment(4)); // captain
    seq(new EflTextSegment()); // rub
    seq(new EflSkipTextsSegment(9)); // captain
    seq(new EflWalkToSegment(0, 7)); // stairs

//    {
//      seq(new EflWalkToSegment(21, 11, false).setBlockAllWarps(true)); // enter rare candy room
//      seq(new EflWalkToSegment(0, 14, false)); // go to gentleman
//      seqMove(new EflOverworldInteract(3)); // engage gentleman
//      seq(new EflInitFightSegment(2)); // start fight
//      {
//        EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//        kems.attackCount[2][1] = 1; // bubble crit
//        seq(kems); // growlithe
//      }
//      seq(EflNewEnemyMonSegment.any()); // next mon
//      {
//        EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//        kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(EMBER)};
//        kems.attackCount[3][0] = 1; // bubblebeam
//        seq(kems); // ponyta
//      }
//      seq(new EflEndFightSegment(1)); // player defeated enemy
//
//      save("su5");
//      load("su5");
//
//      seq(new EflWalkToSegment(0, 12, false)); // go to rare candy
//      seqMove(new EflOverworldInteract(9)); // collect rare candy
//      seq(new EflTextSegment()); // found rare candy
//      seq(new EflWalkToSegment(2, 16, false)); // leave rare candy room
//    }

    seq(new EflWalkToSegment(2, 4, false).setBlockAllWarps(true)); // stairs
    seq(new EflWalkToSegment(7, 7)); // leave ss.anne

    save("su6");
    load("su6");

    seq(new EflWalkToSegment(26, -1, false)); // leave ss.anne
    seqEflSkipInput(5); // Watch SS Anne

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
        seqEflButton(DOWN);
        seqMetric(new StateResettingMetric() {
          @Override
          public int getMetricInternal() {
            EflUtil.runToAddressNoLimit(0, 0, 0x197ca); // after setting first trash can
            return curGb.readMemory(0xd743); // first trash can
          }
        }, Comparator.EQUAL, 8);
      }
    });

    seq(new EflWalkToSegment(15, 17)); // go to bush
    {
      seq(new EflSelectMonSegment(SQUIRTLE).fromOverworld().andSwitchWith(KAKUNA));
      seqEflButton(Move.B); // close menu
      seq(new EflSelectItemSegment(HM01).fromMainMenu().andUse());
      seq(new EflLearnTMSegment(FARFETCHD));
      seqEflButton(Move.B); // close menu
      seq(new EflSelectMonSegment(FARFETCHD).fromMainMenu().andCut());
    }
    seq(new EflWalkToSegment(12, 19)); // enter gym
//    {
//      seq(new EflWalkToSegment(4, 9)); // go to trash can
//      seqEflButton(Move.LEFT); // turn left
//    }
    {
      seq(new EflWalkToSegment(4, 11)); // go to trash can
      seqEflButton(RIGHT); // turn left
    }
    delayEfl(new SeqSegment() { // activate first can
      @Override
      protected void execute() {
        seqEflButton(A);
        seqMetric(new StateResettingMetric() {
          @Override
          public int getMetricInternal() {
            EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.printLetterDelayJoypadAddress); // after setting second trash can
            return curGb.readMemory(0xd744); // first trash can
          }
        }, EQUAL, 7);
      }
    });
    seq(new EflSkipTextsSegment(4)); // opened first lock
//    {
//      seqMove(new EflWalkStep(Move.RIGHT, false, true), 0); // turn right
//      seqEflSkipInput(1); // allow update
//    }
    {
      seq(new EflWalkToSegment(4, 9)); // go to surge
      seqEflButton(RIGHT);
    }
    seqEflButton(A);
    seq(new EflSkipTextsSegment(2)); // opened second lock
    seq(new EflWalkToSegment(5, 3)); // go to surge
    seq(new EflWalkToSegment(5, 2)); // go to surge
    seqMove(new EflOverworldInteract(1)); // talk to surge

    save("tmp");
    load("tmp");

		seq(new EflInitFightSegment(10)); // start fight
    seq(new EflSwitchPokemonSegment(SQUIRTLE, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SCREECH)));
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SCREECH)};
//      kems.attackCount[3][1] = 1; // bubblebeam crit
			kems.attackCount[0][1] = 1; // mega punch crit
      kems.numExpGainers = 3; // Squirtle, Kakuna, level up to 10
			seq(kems); // voltorb
		}
    save("tmp2");
    load("tmp2");
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(THUNDER_SHOCK)};
      kems.attackCount[1][1] = 1; // bite crit
      seq(kems); // pikachu
    }
    save("tmp3");
    load("tmp3");
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(THUNDERBOLT)};
      kems.attackCount[3][1] = 1; // bubblebeam crit // TODO: speed fall
      kems.attackCount[1][1] = 1; // bite crit
//      kems.numExpGainers = 2; // Squirtle, lvlup to 27
      seq(kems); // raichu
    }
		seq(new EflEndFightSegment(3)); // player defeated enemy

    seq(new EflEvolutionSegment()); // Beedrill
//    seq(new EflEvolutionSegment(true)); // Squirtle

    seq(new EflSkipTextsSegment(8)); // after battle texts
    seq(new EflWalkToSegment(5, 18, false)); // leave gym
	}
}
