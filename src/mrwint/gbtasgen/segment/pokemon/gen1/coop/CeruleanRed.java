package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class CeruleanRed extends SeqSegment {

	@Override
	public void execute() {

//    seq(new EflWalkToSegment(9, 11)); // go to house
//    seq(new EflWalkToSegment(2, 0)); // go through house
//    seq(new EflWalkToSegment(13, 8)); // Rare Candy
//    seq(new EflWalkToSegment(14, 8)); // Rare Candy
//    seqEflButton(Move.A); // Rare Candy
//    seq(new EflTextSegment()); // Rare Candy
//    seq(new EflWalkToSegment(9, 10, false)); // go to house
//    seq(new EflWalkToSegment(2, 8, false)); // go through house
//
//    seq(new EflWalkToSegment(30, 19)); // enter gym
//    {
//      seqEflButton(Move.START, PressMetric.PRESSED);
//      seqEflScrollA(2); // items
//      seqEflScrollFastAF(3+1); // Moon stone
//      seqEflSkipInput(1);
//      seqEflButton(Move.A); // use
//      seqEflScrollAF(-2); // jigglypuff
//      seq(new EflEvolutionSegment());
//      seqEflButton(Move.B); // cancel
//      seqEflScrollA(-1); // mon
//      seqEflScrollAF(2); // charmander
//      seqEflSkipInput(1);
//      seqEflScrollAF(2); // switch
//      seqEflSkipInput(1);
//      seqEflScrollAF(1); // weedle
//      seqEflButton(Move.B); // close menu
//      seqEflButton(Move.START); // close menu
//    }
//
//    save("ce1");
//    load("ce1");
//
//    seq(new EflWalkToSegment(5, 3)); // engage
//    seq(new EflInitFightSegment(2)); // start fight
//    seq(new EflSwitchPokemonSegment(1, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39))); // switch to Charmander
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
//      kems.attackCount[3][0] = 1; // mega punch
//      kems.numExpGainers = 5; // boosted, lvlup to 33, weedle l7
//      seq(kems); // Goldeen
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    seq(new EflEvolutionSegment()); // weedle
//
//    save("ce2");
//    load("ce2");
//
//    seq(new EflWalkToSegment(4, 2, false)); // walk up to misty
//    seqMove(new EflOverworldInteract(1)); // talk to misty
//    seq(new EflInitFightSegment(9)); // start fight
//    seq(new EflSwitchPokemonSegment(1, EflEnemyMoveDesc.missWith(55))); // switch to Charmander
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(55)}; // water gun
//      kems.attackCount[3][0] = 1; // mega punch
//      kems.numExpGainers = 4; // boosted, kakuna l8
//      seq(kems); // staryu
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//
//    save("ce3");
//    load("ce3");
//
//    seq(new EflSwitchPokemonSegment(-1, EflEnemyMoveDesc.missWith(33))); // switch to Kakuna
//    seq(new EflSwitchPokemonSegment(1, EflEnemyMoveDesc.missWith(55))); // switch to Charmander
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(55)}; // water gun
//      kems.attackCount[3][1] = 1; // mega punch crit
//      kems.numExpGainers = 4; // boosted, kakuna l10
//      seq(kems); // starmie
//    }
//    seq(new EflEndFightSegment(4)); // player defeated enemy
//
//    seq(new EflEvolutionSegment()); // kakuna
//
//    seq(new EflSkipTextsSegment(9)); // after battle speech
//    seq(new EflWalkToSegment(5, 14, false)); // leave gym
//
//    save("ce4");
    load("ce4");

    seq(new EflWalkToSegment(19, 17)); // enter Center
    seq(new EflWalkToSegment(13, 4)); // PC
    seq(new EflWalkToSegment(13, 3, false)); // PC
    {
      seqEflButton(Move.A); // use PC
      seq(new EflSkipTextsSegment(1)); // turned on
      seqEflButton(Move.A); // someone's PC
      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
      seqMetric(new OutputParty());
      seqMetric(new OutputBoxMons());
      seq(new EflDepositMonSegment(1, 0)); // beedrill
      seq(new EflDepositMonSegment(0, 3 + 1)); // wigglytuff
      seq(new EflWithdrawMonSegment(-1, 1)); // rattata
      seq(new EflWithdrawMonSegment(0, 1)); // ekans
      seqEflButton(Move.B, PressMetric.MENU); // cancel
      seqEflButton(Move.B, PressMetric.MENU); // cancel
    }

    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
	}
}
