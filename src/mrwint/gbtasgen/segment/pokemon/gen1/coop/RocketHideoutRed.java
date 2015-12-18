package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BITE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CATERPIE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMELEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CLEFABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CLEFAIRY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CUBONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GASTLY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MEOWTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MOON_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NINETALES;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RAGE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SAND_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCREECH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SMOG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SQUIRTLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VULPIX;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class RocketHideoutRed extends SeqSegment {

	@Override
	public void execute() {
//    seqEflButton(A); // continue game
//    seqEflButton(START);
//    seqEflButton(A);
//    seqEflButton(START);
//    seqEflButton(A);
//
//    {
//      seq(new EflWalkToSegment(13, 4)); // PC
//      seq(new EflWalkToSegment(13, 3, false)); // PC
//
//      seq(new EflSelectItemSegment(MOON_STONE).fromOverworld().andUse());
//      seq(new EflSelectMonSegment(CLEFAIRY));
//      seq(new EflEvolutionSegment()); // Clefable
//      seqEflButton(B);
//      seqEflButton(START);
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seqMetric(new OutputParty());
//        seqMetric(new OutputBoxMons());
//        seq(new EflDepositMonSegment(CLEFABLE));
//        seq(new EflDepositMonSegment(VULPIX));
//        seq(new EflWithdrawMonSegment(CHARMELEON));
//        seq(new EflWithdrawMonSegment(CATERPIE));
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, MENU); // cancel
//        seqMetric(new OutputParty());
//      }
//    }
//
//    seq(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//
//    seqMetric(new OutputItems());
//    seqMetric(new OutputParty());
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andSwitchWith(SQUIRTLE));
//    seqEflButton(B);
//    seq(new EflUseBikeSegment().fromMainMenu());
//
//    seq(new EflWalkToSegment(28, 19)); // enter game corner
//
//    seq(new EflWalkToSegment(9, 7)); // rocket
//    seq(new EflWalkToSegment(9, 6)); // rocket
//
//    seqMove(new EflOverworldInteract(11)); // talk to rocket
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
//      kems.attackCount[3][1] = 1; // Bubblebeam crit
//      kems.numExpGainers = 3; // Squirtle, boosted, lvlup to 30
//      seq(kems); // Raticate
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // Bubblebeam
//      kems.numExpGainers = 2; // Squirtle, boosted
//      seq(kems); // Zubat
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflEvolutionSegment()); // Wartortle
//
//    seq(new EflSkipTextsSegment(2)); // after battle texts
//    seq(new EflWalkToSegment(9, 5)); // poster
//    seqMove(new EflOverworldInteract(12)); // poster
//    seq(new EflSkipTextsSegment(2)); // poster
//    seq(new EflTextSegment()); // poster
//    seq(new EflWalkToSegment(17, 4)); // enter rocket hideout
//
//    save("rh1");
//    load("rh1");
//
//    seq(new EflWalkToSegment(23, 2)); // l2
//    seq(new EflWalkToSegment(21, 8)); // l3
//
//    seq(new EflWalkToSegment(13, 10)); // spinner
//    seq(new EflWalkToSegment(12, 11)); // spinner
//    seqEflSkipInput(2); // spinning
//    seq(new EflWalkToSegment(10, 13)); // spinner
//    seqEflSkipInput(4); // spinning
//    seq(new EflWalkToSegment(12, 15)); // spinner
//    seq(new EflWalkToSegment(9, 17)); // spinner
//    seq(new EflWalkToSegment(11, 18)); // spinner
//    seqEflSkipInput(8); // spinning
//    seq(new EflWalkToSegment(16, 25)); // avoid spinners
//    seq(new EflWalkToSegment(19, 18)); // l4
//
//    seq(new EflWalkToSegment(13, 2)); // rocket
//    seq(new EflWalkToSegment(12, 2)); // rocket
//    seqMove(new EflOverworldInteract(4)); // rocket
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(SMOG)};
//      kems.attackCount[3][1] = 1; // Bubblebeam crit
//      kems.numExpGainers = 2; // Squirtle, boosted
//      seq(kems); // Koffing
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // Bubblebeam
//      kems.numExpGainers = 2; // Squirtle, boosted
//      seq(kems); // Zubat
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rh2");
//    load("rh2");
//
//    seqMove(new EflOverworldInteract(4)); // rocket
//    seq(new EflSkipTextsSegment(1)); // dropped lift key
//    seq(new EflWalkToSegment(10, 2, false)); // lift key
//    seqMove(new EflOverworldInteract(9)); // lift key
//    seq(new EflTextSegment()); // found Lift Key
//    seq(new EflWalkToSegment(19, 10)); // l3
//
//    seq(new EflWalkToSegment(18, 16)); // spinner
//    seqEflSkipInput(1); // spinning
//    seq(new EflWalkToSegment(20, 14, false)); // rare candy
//    seqMove(new EflOverworldInteract(4)); // rare candy
//    seq(new EflTextSegment()); // found rare candy
//    seq(new EflWalkToSegment(16, 13)); // spinner
//    seqEflSkipInput(2); // spinning
//    seq(new EflWalkToSegment(25, 6)); // l2
//
//    {
//      seq(new EflWalkToSegment(18, 11)); // belt
//      seq(new EflWalkToSegment(17, 11)); // belt
//      seqEflSkipInput(17); // spinning
//      {
//        seq(new EflWalkToSegment(2, 7)); // go to nugget
//        seq(new EflWalkToSegment(8, 7)); // go to nugget
//        seq(new EflWalkToSegment(16, 8, false)); // Nugget
//        seqMove(new EflOverworldInteract(3)); // Nugget
//        seq(new EflTextSegment()); // Nugget
//        seq(new EflWalkToSegment(7, 8)); // go back
//        seq(new EflWalkToSegment(4, 9)); // go back
//        seqEflSkipInput(2); // spinning
//      }
//      {
//        seq(new EflWalkToSegment(1, 12)); // go to moon stone
//        seqMove(new EflOverworldInteract(2)); // moon stone
//        seq(new EflTextSegment()); // moon stone
//      }
//      seq(new EflWalkToSegment(5, 14)); // spinner
//      seqEflSkipInput(6); // spinning
//      seq(new EflWalkToSegment(11, 16)); // spinner
//      seqEflSkipInput(6); // spinning
//      seq(new EflWalkToSegment(13, 18)); // spinner
//      seqEflSkipInput(4); // spinning
//      seq(new EflWalkToSegment(13, 22)); // spinner
//      seqEflSkipInput(6); // spinning
//      seq(new EflWalkToSegment(10, 25)); // spinner
//      seqEflSkipInput(4); // spinning
//      seq(new EflWalkToSegment(24, 20, false)); // elevator
//    }
//
//    seq(new EflWalkToSegment(1, 2)); // elevator
//    seq(new EflWalkToSegment(1, 1, false)); // elevator
//    seqMove(new EflOverworldInteract(1)); // elevator
//    seq(new EflTextSegment()); // which floor
//    seqEflScrollFastAF(2); // B4F
//    seq(new EflWalkToSegment(2, 1)); // leave elevator
//
//    seq(new EflWalkToSegment(25, 12)); // rocket
//    seq(new EflWalkToSegment(26, 12, false)); // rocket
//    seqMove(new EflOverworldInteract(3)); // rocket
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // Bubblebeam
//      kems.numExpGainers = 3; // Wartortle, boosted, lvlup to 31
//      seq(kems); // Ekans
//    }
//    seq(new EflCancelMoveLearnSegment()); // withdraw
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // Bubblebeam
//      kems.numExpGainers = 2; // Wartortle, boosted
//      seq(kems); // Sandshrew
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[3][1] = 1; // Bubblebeam crit
//      kems.numExpGainers = 2; // Wartortle, boosted
//      seq(kems); // Arbok
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rh3");
//    load("rh3");
//
//    seq(new EflWalkToSegment(24, 12)); // rocket
//    seqMove(new EflOverworldInteract(2)); // rocket
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // Bubblebeam
//      kems.numExpGainers = 2; // Wartortle, boosted
//      seq(kems); // Sandshrew
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // Bubblebeam
//      kems.numExpGainers = 3; // Wartortle, boosted, lvlup to 32
//      seq(kems); // Ekans
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SAND_ATTACK)};
//      kems.attackCount[3][0] = 1; // Bubblebeam
//      kems.numExpGainers = 2; // Wartortle, boosted
//      seq(kems); // Sandslash
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rh4");
    load("rh4");

    seqEflButton(UP);
    seq(new EflWalkToSegment(24, 3)); // giovanni
    seqMove(new EflOverworldInteract(1)); // rocket
    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SCREECH)};
      kems.attackCount[3][0] = 1; // Bubblebeam
      kems.numExpGainers = 2; // Wartortle, boosted
      seq(kems); // Onix
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // Bubblebeam
      kems.numExpGainers = 3; // Wartortle, boosted, lvlup to 33
      seq(kems); // Rhyhorn
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(BITE, RAGE)};
      kems.attackCount[3][1] = 1; // Bubblebeam
      kems.numExpGainers = 2; // Wartortle, boosted
      seq(kems); // Kangaskhan
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("rh5");
    load("rh5");

    seq(new EflSkipTextsSegment(7)); // After battle texts
    seq(new EflWalkToSegment(25, 2, false)); // silph scope
    seqMove(new EflOverworldInteract(8)); // silph scope
    seq(new EflTextSegment()); // silph scope

    seqEflSkipInput(1);
    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
    seqEflSkipInput(2);
  }
}
