package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class TowerRed extends SeqSegment {

	@Override
	public void execute() {
    seqEflButton(Move.A); // continue game
    seqEflButton(Move.START);
    seqEflButton(Move.A);
    seqEflButton(Move.START);
    seqEflButton(Move.A);

    {
      seq(new EflWalkToSegment(13, 4)); // PC
      seq(new EflWalkToSegment(13, 3, false)); // PC
      {
        seqMetric(new OutputItems());
        seqMetric(new OutputParty());
        seqEflSkipInput(1);
        seqEflButton(START, PRESSED);
        seqEflScrollA(2); // items
        seqEflScrollFastAF(3+1); // Moon Stone
        seqEflButton(A, PRESSED); // use
        seqEflSkipInput(1);
        seqEflButton(A, PRESSED); // Clefairy
        seq(new EflEvolutionSegment()); // Clefable
        seqEflScrollFastAF(10+1); // Fire Stone
        seqEflButton(A, PRESSED); // use
        seqEflSkipInput(1);
        seqEflScrollAF(-1); // Vulpix
        seq(new EflEvolutionSegment()); // Ninetales
        seqEflButton(B);
        seqEflButton(START);
      }
      {
        seqEflButton(A); // use PC
        seq(new EflSkipTextsSegment(1)); // turned on
        seqEflButton(A); // someone's PC
        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
        seqMetric(new OutputParty());
        seqMetric(new OutputBoxMons());
        seq(new EflDepositMonSegment(1, 0)); // Clefable
        seq(new EflDepositMonSegment(0, 1)); // Cubone
        seq(new EflDepositMonSegment(0, 1)); // Gastly
        seq(new EflDepositMonSegment(0, 2)); // Ninetales
        seq(new EflWithdrawMonSegment(-1, 6+1)); // charmeleon
        seq(new EflWithdrawMonSegment(0, 6+1)); // Caterpie
        seq(new EflWithdrawMonSegment(0, 6+1)); // Dux
        seq(new EflWithdrawMonSegment(0, 6+1)); // Meowth
        seqEflButton(B, MENU); // cancel
        seqEflButton(B, MENU); // cancel
        seqMetric(new OutputParty());
      }
    }

    seq(new EflWalkToSegment(4, 6)); // leave center
    seq(new EflWalkToSegment(4, 8, false)); // leave center

    save("t0");
    load("t0");

    {
      seqEflButton(START, PRESSED);
      seqEflScrollA(-1); // mon
      seqEflButton(A, PRESSED); // squirtle
      seqEflSkipInput(1);
      seqEflScrollAF(1); // switch
      seqEflSkipInput(1);
      seqEflScrollAF(2); // charmeleon
      seqEflButton(B); // cancel
      seqEflButton(START); // cancel
      seqMetric(new OutputParty());
    }

    seq(new EflWalkToSegment(14, 5)); // enter tower
    seq(new EflWalkToSegment(18, 9)); // l2
    seq(new EflWalkToSegment(15, 5)); // engage rival
    seq(new EflInitFightSegment(6)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 28)}; // Sand-Attack
      kems.attackCount[1][1] = 1; // Slash crit
      kems.numExpGainers = 2; // boosted;
      seq(kems); // pidgeotto
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // Ember
      kems.numExpGainers = 3; // boosted, lvl up 34;
      seq(kems); // Exeggcute
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    seq(new EflSwitchPokemonSegment(-1, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43))); // switch to meowth
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][1] = 1; // Thunderbolt crit
      kems.numExpGainers = 5; // boosted, meowth boosted lvlup to 27;
      seq(kems); // gyarados
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[2][1] = 1; // bite crit
      kems.numExpGainers = 2; // boosted;
      seq(kems); // kadabra
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43)}; // Leer
      kems.attackCount[2][0] = 1; // bite
      kems.attackCount[2][1] = 1; // bite crit
      kems.numExpGainers = 3; // boosted, l28;
      seq(kems); // Charmeleon
    }
    seq(new EflEndFightSegment(2)); // player defeated enemy
    seq(new EflEvolutionSegment()); // persian
    seq(new EflSkipTextsSegment(10)); // after battle texts

    save("t1");
	  load("t1");

    {
      seqEflButton(START, PRESSED);
      seqEflScrollA(1); // mon
      seqEflButton(A, PRESSED); // squirtle
//      seqEflSkipInput(1);
      seqEflScrollAF(2); // switch
      seqEflSkipInput(1);
      seqEflScrollAF(2); // charmeleon
      seqEflButton(B); // cancel
      seqEflButton(START); // cancel
      seqMetric(new OutputParty());
    }

	  seqMetric(new OutputParty());

    seq(new EflWalkToSegment(3, 9)); // l3
    seq(new EflWalkToSegment(18, 9)); // l4

    seq(new EflWalkToSegment(17, 7)); // engage
    seq(new EflWalkToSegment(16, 7)); // engage
    seqMove(new EflOverworldInteract(2)); // talk to trainer
    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(122)}; // lick
      kems.attackCount[3][0] = 2; // Bubblebeam
      kems.numExpGainers = 2; // boosted;
      seq(kems); // gastly
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(122)}; // lick
      kems.attackCount[3][0] = 2; // Bubblebeam
      kems.numExpGainers = 2; // boosted;
      seq(kems); // gastly
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("t2");
    load("t2");

    {
      seq(new EflWalkToSegment(13, 10)); // elixer
      seqMove(new EflOverworldInteract(4)); // elixer
      seq(new EflTextSegment());
    }
    seq(new EflWalkToSegment(3, 9)); // l5
    {
      seq(new EflWalkToSegment(4, 11)); // elixer
      seqEflButton(Move.A); // elixer
      seq(new EflTextSegment());
    }
    seq(new EflWalkToSegment(11, 9)); // heal
    seq(new EflSkipTextsSegment(2)); // healed
    {
      seq(new EflWalkToSegment(7, 14)); // nugget
      seqMove(new EflOverworldInteract(6)); // nugget
      seq(new EflTextSegment());
    }
    seq(new EflWalkToSegment(18, 9)); // l6
    seq(new EflWalkToSegment(15, 5)); // engage
    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(122)}; // lick
      kems.attackCount[3][0] = 1; // Bubblebeam
      kems.attackCount[3][1] = 1; // Bubblebeam crit
      kems.numExpGainers = 3; // boosted; lvl31
      seq(kems); // gastly
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy
    seq(new EflEvolutionSegment()); // wartortle
    seq(new EflCancelMoveLearnSegment()); // withdraw
    seq(new EflWalkToSegment(11, 5)); // engage
    seq(new EflWalkToSegment(10, 5)); // engage

    save("t3");
    load("t3");

    seqMove(new EflOverworldInteract(2)); // talk to trainer
    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(122)}; // lick
      kems.attackCount[3][1] = 1; // Bubblebeam crit
      kems.numExpGainers = 2; // boosted;
      seq(kems); // gastly
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("t4");
    load("t4");

    seq(new EflWalkToSegment(6, 6)); // rare candy
    seq(new EflWalkToSegment(6, 7)); // rare candy
    seqMove(new EflOverworldInteract(4)); // rare candy
    seq(new EflTextSegment());
    seq(new EflWalkToSegment(10, 16)); // marowak
    seq(new EflSkipTextsSegment(1)); // begone
    seq(new EflSkipTextsSegment(2)); // ghost appeared
    seq(new EflTextSegment()); // go
    seqEflButton(Move.DOWN); // items
    seqEflButton(Move.A); // items
    seqEflScrollFastAF(11+1); // poke doll
    seq(new EflSkipTextsSegment(1)); // used poke doll
    seq(new EflSkipTextsSegment(4)); // ghost gone
    seq(new EflWalkToSegment(9, 16)); // l7

    save("t5");
    load("t5");

    seqMetric(new OutputParty());

    seq(new EflWalkToSegment(10, 11, false)); // engage trainer

    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // Bite
      kems.numExpGainers = 2; // boosted
      seq(kems); // zubat
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // Bite
      kems.numExpGainers = 2; // boosted
      seq(kems); // zubat
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    seq(new EflSwitchPokemonSegment(3, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103))); // caterpie
    seq(new EflSwitchPokemonSegment(3, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103))); // wartortle
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
      kems.attackCount[1][1] = 2; // Bite
      kems.numExpGainers = 5; // boosted; lvlup to 32, caterpie lvlup to 7
      seq(kems); // golbat
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy
    seq(new EflEvolutionSegment()); // metapod
    seq(new EflSkipTextsSegment(1)); // no forget this

    save("t6");
    load("t6");

    {
      seqEflButton(START, PRESSED);
      seqEflScrollA(1); // mons
      seqEflButton(A, PRESSED); // meowth
      seqEflSkipInput(1);
      seqEflScrollAF(1); // switch
      seqEflScrollAF(-3); // metapod
      seqEflButton(B);
      seqEflButton(START);
    }

    seq(new EflWalkToSegment(10, 9, false)); // engage trainer
    seq(new EflInitFightSegment(4)); // start fight
//    seq(new EflSwitchPokemonSegment(3, EflEnemyMoveDesc.missWith(33, 123))); // metapod
    seq(new EflSwitchPokemonSegment(3, EflEnemyMoveDesc.missWith(33, 123))); // wartortle
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33, 123)}; // tackle, smog
      kems.attackCount[3][1] = 1; // Bubblebeam crit
      kems.numExpGainers = 4; // boosted, metapod lvlup to 9
      seq(kems); // koffing
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    seq(new EflSwitchPokemonSegment(3, EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50))); // caterpie
    seq(new EflSwitchPokemonSegment(3, EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50))); // wartortle
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[0][1] = 1; // Mega Punch crit
      kems.numExpGainers = 4; // boosted, metapod lvlup to 10
      seq(kems); // drowzee
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy
    seq(new EflEvolutionSegment()); // butterfree
    seq(new EflSkipTextsSegment(3)); // no forget this

    {
      seqEflButton(START, PRESSED);
      seqEflScrollA(1); // mons
      seqEflButton(A, PRESSED); // meowth
      seqEflSkipInput(1);
      seqEflScrollAF(1); // switch
      seqEflScrollAF(-3); // metapod
      seqEflButton(B);
      seqEflButton(START);
    }

    seq(new EflWalkToSegment(10, 7, false)); // engage trainer

    save("t7");
    load("t7");

    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // Bubblebeam
      kems.numExpGainers = 2; // boosted
      seq(kems); // zubat
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // Bubblebeam
      kems.numExpGainers = 2; // boosted
      seq(kems); // rattata
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
      kems.attackCount[3][1] = 1; // Bubblebeam crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // raticate
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // Bubblebeam
      kems.numExpGainers = 3; // boosted; lvlup to 33
      seq(kems); // zubat
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy
    seq(new EflSkipTextsSegment(1)); // no forget this
    seq(new EflWalkToSegment(10, 5)); // engage old man
    seq(new EflWalkToSegment(10, 4)); // engage old man
    seqMove(new EflOverworldInteract(4)); // talk to old man
    seq(new EflSkipTextsSegment(12));

    save("t8");
    load("t8");

    seq(new EflWalkToSegment(2, 1)); // engage old man
    seq(new EflWalkToSegment(3, 1, false)); // engage old man
    seqMove(new EflOverworldInteract(5)); // talk to old man
    seq(new EflSkipTextsSegment(10)); // get flute
    seq(new EflWalkToSegment(2, 8, false)); // leave house

  }
}
