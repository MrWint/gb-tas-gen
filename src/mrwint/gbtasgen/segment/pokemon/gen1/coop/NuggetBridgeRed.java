package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflOverrideMoveSegment;
import mrwint.gbtasgen.segment.pokemon.EflPokecenterSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class NuggetBridgeRed extends SeqSegment {

	@Override
	public void execute() {
    seq(new EflWalkToSegment(30, 19)); // enter gym
    seq(new EflWalkToSegment(5, 3)); // engage
    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
      kems.attackCount[3][0] = 2; // mega punch
      kems.attackCount[3][1] = 1; // mega punch crit
      kems.numExpGainers = 2; // level up to 18
      seq(kems); // Goldeen
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflEvolutionSegment(true)); // cancel evolution

    seq(new EflWalkToSegment(4, 2, false)); // walk up to misty
    seqMove(new EflOverworldInteract(1)); // talk to misty
    seq(new EflInitFightSegment(9)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(55)}; // water gun
      kems.attackCount[3][0] = 1; // mega punch
      kems.attackCount[3][1] = 1; // mega punch crit
      seq(kems); // staryu
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(55)}; // water gun
      kems.attackCount[3][0] = 2; // mega punch
      kems.attackCount[3][1] = 2; // mega punch crit
      kems.numExpGainers = 2; // level up to 19
      seq(kems); // starmie
    }
    seq(new EflEndFightSegment(4)); // player defeated enemy

    seq(new EflEvolutionSegment(true)); // cancel evolution

    seq(new EflSkipTextsSegment(9)); // after battle speech
    seq(new EflWalkToSegment(5, 14, false)); // leave gym
    save("nb1");
    load("nb1");

    seq(new EflWalkToSegment(19, 17)); // enter Center
    seq(new EflPokecenterSegment(true)); // set warp point in center

    seq(new EflWalkToSegment(9, 11)); // go to house
    seq(new EflWalkToSegment(2, 0)); // go through house
    seq(new EflWalkToSegment(13, 8)); // Rare Candy
    seq(new EflWalkToSegment(14, 8)); // Rare Candy
    seqEflButton(Move.A); // Rare Candy
    seq(new EflTextSegment()); // Rare Candy
    seq(new EflWalkToSegment(9, 10, false)); // go to house
    seq(new EflWalkToSegment(2, 8, false)); // go through house

    seq(new EflWalkToSegment(20,6)); // go to rival fight
		seq(new EflInitFightSegment(8)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 28)}; // sand attack
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[3][0] = 1; // mega punch
      kems.attackCount[3][1] = 1; // mega punch crit
      kems.numExpGainers = 2; // level up to 20
			seq(kems); // pidgeotto
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE)}; // teleport
      kems.attackCount[3][0] = 1; // mega punch
//      kems.attackCount[0][1] = 1; // scratch crit
			seq(kems); // abra
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[3][0] = 1; // mega punch
			seq(kems); // rattata
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
//      kems.attackCount[0][1] = 1; // scratch crit
      kems.attackCount[3][0] = 1; // mega punch
      kems.attackCount[3][1] = 1; // mega punch crit
			seq(kems); // squirtle
		}
		seq(new EflEndFightSegment(2)); // player defeated enemy

    seq(new EflEvolutionSegment(true)); // cancel evolution

		seq(new EflSkipTextsSegment(14)); // after rival battle texts

		seq(new EflWalkToSegment(21, -1)); // walk up
		seq(new EflWalkToSegment(11, 32)); // walk up to trainer
		seqMove(new EflOverworldInteract(7)); // talk to trainer 1

		save("nb2");
		load("nb2");

		seq(new EflInitFightSegment(4)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // level up to 21
			seq(kems); // caterpie
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ember
			seq(kems); // weedle
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflEvolutionSegment(true)); // cancel evolution

		seq(new EflWalkToSegment(10, 29)); // walk up to trainer
		seqMove(new EflOverworldInteract(6)); // talk to trainer 2

		seq(new EflInitFightSegment(1)); // start fight
		{
			EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
			seq(kems); // pidgey
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
			seq(kems); // nidoF
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

		seq(new EflWalkToSegment(11, 26)); // walk up to trainer
		seqMove(new EflOverworldInteract(5)); // talk to trainer 3

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[3][0] = 1; // mega punch
			seq(kems); // rattata
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
			seq(kems); // ekans
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[3][0] = 1; // mega punch
			seq(kems); // zubat
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

		seq(new EflWalkToSegment(10, 23)); // walk up to trainer
		seqMove(new EflOverworldInteract(4)); // talk to trainer 4

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
      kems.numExpGainers = 2; // level up to 22
			seq(kems); // pidgey
		}
    seq(new EflOverrideMoveSegment(1)); // override leer with rage
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // mega punch crit
			seq(kems); // nidoF
		}

    save("nb3");
    load("nb3");

    seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflEvolutionSegment(true)); // cancel evolution

		seq(new EflWalkToSegment(11, 20)); // walk up to trainer
		seqMove(new EflOverworldInteract(3)); // talk to trainer 5

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43)}; // leer
			kems.attackCount[3][1] = 1; // 1x mega punch crit
			seq(kems); // mankey
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

		seq(new EflWalkToSegment(10, 15)); // walk up to rocket

		seq(new EflInitFightSegment(15)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
			seq(kems); // Ekans
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[3][0] = 1; // mega punch
			seq(kems); // Zubat
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

		save("nb4");
		load("nb4");

		seq(new EflSkipTextsSegment(3)); // after rocket battle texts

		seq(new EflWalkToSegment(20, 9)); // enter route 25

		seq(new EflWalkToSegment(14, 7)); // engage hiker
		seq(new EflInitFightSegment(2)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
      kems.attackCount[2][1] = 2; // ember crit
      kems.numExpGainers = 2; // level up to 23
			seq(kems); // Onix
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflEvolutionSegment(true)); // cancel evolution

		seq(new EflWalkToSegment(18,7)); // walk up to trainer 2
		seq(new EflWalkToSegment(18,8,false)); // walk up to trainer 2
		seqMove(new EflOverworldInteract(4)); // talk to trainer 2

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
			seq(kems); // nidoM
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
			seq(kems); // nidoF
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

		seq(new EflWalkToSegment(24, 6)); // walk up to trainer 3

		seq(new EflInitFightSegment(2)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // 1x mega punch
//      kems.attackCount[0][1] = 1; // scratch crit
			seq(kems); // rattata
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[3][0] = 1; // 1x mega punch
			seq(kems); // ekans
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

		seq(new EflWalkToSegment(35,4)); // walk up to trainer 4
		seq(new EflWalkToSegment(36,4)); // walk up to trainer 4
		seqMove(new EflOverworldInteract(6)); // talk to trainer 4

		seq(new EflInitFightSegment(2)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // 1x ember
			seq(kems); // oddish
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // mega punch
//      kems.attackCount[0][1] = 1; // scratch crit
			seq(kems); // pidgey
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // 1x ember
      kems.numExpGainers = 2; // level up to 24
			seq(kems); // oddish
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflEvolutionSegment(true)); // cancel evolution

    save("nb6");
    load("nb6");

//    seq(new EflWalkToSegment(38, 4)); // ether
//    seqEflButton(Move.A); // ether
//    seq(new EflTextSegment()); // ether


    seq(new EflWalkToSegment(45,3)); // enter bills house

		seq(new EflWalkToSegment(4,5)); // walk up to bill
		seq(new EflWalkToSegment(5,5)); // walk up to bill
		seqMove(new EflOverworldInteract(1)); // talk to bill

		seq(new EflSkipTextsSegment(10));
		seq(new EflSkipTextsSegment(1, true)); // help
		seq(new EflSkipTextsSegment(4));

		seq(new EflWalkToSegment(1,4,false)); // walk up to desk
		seqEflButton(Move.A); // talk to desk
		seq(new EflSkipTextsSegment(2));

		seq(new EflWalkToSegment(4,5)); // walk up to bill
		seq(new EflWalkToSegment(4,4,false)); // walk up to bill
		seqMove(new EflOverworldInteract(2)); // talk to bill
		seq(new EflSkipTextsSegment(18));

		seqEflButton(Move.START, PressMetric.PRESSED);
		seqEflScrollA(2); // items

//    seqEflScrollFastAF(7); // ether
//    seqEflSkipInput(1);
//    seqEflButton(Move.A); // use
//    seqEflSkipInput(1);
//    seqEflButton(Move.A); // charmander
//    seq(new EflTextSegment(Move.B));
//    seqEflScrollA(-1); // mega punch
//    seq(new EflSkipTextsSegment(1)); // PP restored

		seqEflScrollFastAF(2); // escape rope
		seqEflSkipInput(1);
		seqEflButton(Move.A);

    seqEflSkipInput(2);
    seq(new EflWalkToSegment(27, 11)); // enter dig house
	}
}
