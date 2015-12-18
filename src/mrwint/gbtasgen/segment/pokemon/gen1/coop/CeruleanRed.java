package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BEEDRILL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BUBBLE_BEAM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMELEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EKANS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.JIGGLYPUFF;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KAKUNA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGIKARP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MOON_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIKACHU;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RATTATA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WATER_GUN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WEEDLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WIGGLYTUFF;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class CeruleanRed extends SeqSegment {

	@Override
	public void execute() {

//    seq(new EflWalkToSegment(9, 11)); // go to house
//    seq(new EflWalkToSegment(2, 0)); // go through house
//    seq(new EflWalkToSegment(13, 8)); // Rare Candy
//    seq(new EflWalkToSegment(14, 8)); // Rare Candy
//    seqEflButton(A); // Rare Candy
//    seq(new EflTextSegment()); // Rare Candy
//    seq(new EflWalkToSegment(9, 10, false)); // go to house
//    seq(new EflWalkToSegment(2, 8, false)); // go through house
//
//    seq(new EflWalkToSegment(30, 19)); // enter gym
//    seq(new EflSelectItemSegment(MOON_STONE).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(JIGGLYPUFF));
//    seq(new EflEvolutionSegment()); // Wigglytuff
//    seqEflButton(B); // cancel
//    seq(new EflSelectMonSegment(CHARMELEON).fromMainMenu().andSwitchWith(WEEDLE));
//    seqEflButton(B); // close menu
//    seqEflButton(START); // close menu
//
//    save("ce1");
//    load("ce1");
//
//    seq(new EflWalkToSegment(5, 3)); // engage
//    seq(new EflInitFightSegment(2)); // start fight
//    seq(new EflSwitchPokemonSegment(CHARMELEON, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//      kems.attackCount[3][0] = 1; // mega punch
//      kems.numExpGainers = 5; // Weedle, lvlup to 7, Charmeleon, boosted, lvlup to 33
//      seq(kems); // Goldeen
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    seq(new EflEvolutionSegment()); // Kakuna
//
//    save("ce2");
//    load("ce2");
//
//    seq(new EflWalkToSegment(4, 2, false)); // walk up to misty
//    seqMove(new EflOverworldInteract(1)); // talk to misty
//    seq(new EflInitFightSegment(9)); // start fight
//    seq(new EflSwitchPokemonSegment(CHARMELEON, EflEnemyMoveDesc.missWith(TACKLE, WATER_GUN)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE, WATER_GUN)};
//      kems.attackCount[3][0] = 1; // mega punch
//      kems.numExpGainers = 4; // Kakuna, lvlup to 8, Charmeleon, boosted
//      seq(kems); // staryu
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//
//    save("ce3");
//    load("ce3");
//
//    seq(new EflSwitchPokemonSegment(KAKUNA, EflEnemyMoveDesc.missWith(TACKLE)));
//    seq(new EflSwitchPokemonSegment(CHARMELEON, EflEnemyMoveDesc.missWith(TACKLE, WATER_GUN, BUBBLE_BEAM)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE, WATER_GUN, BUBBLE_BEAM)};
//      kems.attackCount[3][1] = 1; // mega punch crit
//      kems.numExpGainers = 4; // Kakuna, lvlup to 10, Charmeleon, boosted
//      seq(kems); // starmie
//    }
//    seq(new EflEndFightSegment(4)); // player defeated enemy
//
//    seq(new EflEvolutionSegment()); // Beedrill
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
      seqEflButton(A); // use PC
      seq(new EflSkipTextsSegment(1)); // turned on
      seqEflButton(A); // someone's PC
      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
      seqMetric(new OutputParty());
      seqMetric(new OutputBoxMons());
      seq(new EflDepositMonSegment(BEEDRILL));
      seq(new EflDepositMonSegment(WIGGLYTUFF));
      seq(new EflWithdrawMonSegment(EKANS));
      seq(new EflWithdrawMonSegment(MAGIKARP));
      seqEflButton(B, MENU); // cancel
      seqEflButton(B, MENU); // cancel
    }

    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
	}
}
