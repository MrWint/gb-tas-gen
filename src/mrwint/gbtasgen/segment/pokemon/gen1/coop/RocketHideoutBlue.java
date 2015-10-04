package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class RocketHideoutBlue extends SeqSegment {

	@Override
	public void execute() {
    seqEflButton(Move.A); // continue game
    seqEflButton(Move.START);
    seqEflButton(Move.A);
    seqEflButton(Move.START);
    seqEflButton(Move.A);

    seq(new EflWalkToSegment(4, 6)); // leave center
    seq(new EflWalkToSegment(4, 8, false)); // leave center

    {
      seqMetric(new OutputParty());
      seqMetric(new OutputItems());
      seqEflButton(START, PRESSED);
      seqEflScrollA(1); // mon
      seqEflButton(A, PRESSED); // Squirtle
      seqEflSkipInput(1);
      seqEflScrollAF(1); // swap
      seqEflSkipInput(1);
      seqEflScrollAF(2); // dratini
      seqEflButton(B); // cancel
      seqEflScrollA(1); // items
      seqEflScrollFastAF(9+1); // HM01
      seqEflButton(A, PRESSED); // use
      seq(new EflLearnTMSegment(-1)); // Cut
      seqEflScrollFastAF(1+1); // TM24
      seqEflButton(A, PRESSED); // use
      seq(new EflLearnTMSegment(-1, 3)); // agility -> Thunderbolt
      seqEflScrollFastAF(12+1); // HM02
      seqEflButton(A, PRESSED); // use
      seq(new EflLearnTMSegment(3)); // Fly
      seqEflScrollFastAF(2+1); // TM13
      seqEflButton(A, PRESSED); // use
      seq(new EflLearnTMSegment(-3, 1)); // leer -> Ice Beam
      seqEflButton(A, PRESSED); // Bike
      seq(new EflSkipTextsSegment(1)); // got on bike
    }

    seq(new EflWalkToSegment(28, 19)); // enter game corner

    seq(new EflWalkToSegment(9, 7)); // rocket
    seq(new EflWalkToSegment(9, 6)); // rocket

    seqMove(new EflOverworldInteract(11)); // talk to rocket
    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
      kems.attackCount[3][1] = 1; // Thunderbolt crit
      kems.attackCount[0][0] = 1; // Wrap
      kems.lastAttack = 0; // Wrap last
      seq(kems); // Raticate
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // Thunderbolt
      seq(kems); // Zubat
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflSkipTextsSegment(2)); // after battle texts
    seq(new EflWalkToSegment(9, 5)); // poster
    seqMove(new EflOverworldInteract(12)); // poster
    seq(new EflSkipTextsSegment(2)); // poster
    seq(new EflTextSegment()); // poster
    seq(new EflWalkToSegment(17, 4)); // enter rocket hideout

    save("rh1");
    load("rh1");

    seq(new EflWalkToSegment(23, 2)); // l2
    seq(new EflWalkToSegment(21, 8)); // l3

    seq(new EflWalkToSegment(13, 10)); // spinner
    seq(new EflWalkToSegment(12, 11)); // spinner
    seqEflSkipInput(2); // spinning
    seq(new EflWalkToSegment(10, 13)); // spinner
    seqEflSkipInput(4); // spinning
    seq(new EflWalkToSegment(12, 15)); // spinner
    seq(new EflWalkToSegment(9, 17)); // spinner
    seq(new EflWalkToSegment(11, 18)); // spinner
    seqEflSkipInput(8); // spinning
    seq(new EflWalkToSegment(16, 25)); // avoid spinners
    seq(new EflWalkToSegment(19, 18)); // l4

    seq(new EflWalkToSegment(13, 2)); // rocket
    seq(new EflWalkToSegment(12, 2)); // rocket
    seqMove(new EflOverworldInteract(4)); // rocket
    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(123)}; // Smog
      kems.attackCount[3][1] = 1; // Thunderbolt crit
      kems.attackCount[0][1] = 1; // Wrap
      kems.lastAttack = 0; // Wrap last
      seq(kems); // Raticate
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // Thunderbolt
      seq(kems); // Zubat
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("rh2");
    load("rh2");

    seqMove(new EflOverworldInteract(4)); // rocket
    seq(new EflSkipTextsSegment(1)); // dropped lift key
    seq(new EflWalkToSegment(10, 2, false)); // lift key
    seqMove(new EflOverworldInteract(9)); // lift key
    seq(new EflTextSegment()); // found Lift Key
    seq(new EflWalkToSegment(19, 10)); // l3

    seq(new EflWalkToSegment(18, 16)); // spinner
    seqEflSkipInput(1); // spinning
    seq(new EflWalkToSegment(20, 14, false)); // rare candy
    seqMove(new EflOverworldInteract(4)); // rare candy
    seq(new EflTextSegment()); // found rare candy
    seq(new EflWalkToSegment(16, 13)); // spinner
    seqEflSkipInput(2); // spinning
    seq(new EflWalkToSegment(25, 6)); // l2

    {
      seq(new EflWalkToSegment(18, 11)); // belt
      seq(new EflWalkToSegment(17, 11)); // belt
      seqEflSkipInput(17); // spinning
      {
        seq(new EflWalkToSegment(2, 7)); // go to nugget
        seq(new EflWalkToSegment(8, 7)); // go to nugget
        seq(new EflWalkToSegment(16, 8, false)); // Nugget
        seqMove(new EflOverworldInteract(3)); // Nugget
        seq(new EflTextSegment()); // Nugget
        seq(new EflWalkToSegment(7, 8)); // go back
        seq(new EflWalkToSegment(4, 9)); // go back
        seqEflSkipInput(2); // spinning
      }
      seq(new EflWalkToSegment(5, 14)); // spinner
      seqEflSkipInput(6); // spinning
      seq(new EflWalkToSegment(11, 16)); // spinner
      seqEflSkipInput(6); // spinning
      seq(new EflWalkToSegment(13, 18)); // spinner
      seqEflSkipInput(4); // spinning
      seq(new EflWalkToSegment(13, 22)); // spinner
      seqEflSkipInput(6); // spinning
      seq(new EflWalkToSegment(10, 25)); // spinner
      seqEflSkipInput(4); // spinning
      seq(new EflWalkToSegment(24, 20, false)); // elevator
  }

    seq(new EflWalkToSegment(1, 2)); // elevator
    seq(new EflWalkToSegment(1, 1, false)); // elevator
    seqMove(new EflOverworldInteract(1)); // elevator
    seq(new EflTextSegment()); // which floor
    seqEflScrollFastAF(2); // B4F
    seq(new EflWalkToSegment(2, 1)); // leave elevator

    seq(new EflWalkToSegment(25, 12)); // rocket
    seq(new EflWalkToSegment(24, 12)); // rocket
    seqMove(new EflOverworldInteract(2)); // rocket
    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][0] = 1; // Ice Beam
      seq(kems); // Sandshrew
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // Ice Beam crit
      seq(kems); // Ekans
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 28)}; // Sand-Attack
      kems.attackCount[1][1] = 1; // Ice Beam crit
      kems.numExpGainers = 2; // lvlup to 25
      seq(kems); // Sandslash
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("rh3");
    load("rh3");

    seq(new EflWalkToSegment(25, 12)); // rocket
    seqMove(new EflOverworldInteract(3)); // rocket
    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // Ice Beam crit
      seq(kems); // Ekans
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][0] = 1; // Ice Beam
      seq(kems); // Sandshrew
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43)}; // leer
      kems.attackCount[3][0] = 1; // Thunderbolt
      kems.attackCount[3][1] = 1; // Thunderbolt crit
      seq(kems); // Arbok
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("rh4");
    load("rh4");

    seq(new EflWalkToSegment(24, 3)); // giovanni
    seqMove(new EflOverworldInteract(1)); // rocket
    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
      kems.attackCount[1][0] = 1; // Ice Beam
      kems.numExpGainers = 2; // lvlup to 26
      seq(kems); // Onix
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][0] = 1; // Ice Beam
      seq(kems); // Rhyhorn
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(44, 99)}; // bite, rage
      kems.attackCount[3][1] = 2; // Thunderbolt crit
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
    seqEflButton(START, PRESSED);
    seqEflScrollA(2); // items
    seqEflScrollFastAF(2); // Escape Rope
    seqEflButton(A, PRESSED); // use
    seqEflSkipInput(2);
    seqEflButton(START, PRESSED);
    seqEflScrollA(-1); // mon
    seqEflScrollAF(3); // pidgey
    seqEflButton(A, PRESSED); // fly
    seqEflScrollA(3); // lavender
    seqEflSkipInput(1);
  }
}
