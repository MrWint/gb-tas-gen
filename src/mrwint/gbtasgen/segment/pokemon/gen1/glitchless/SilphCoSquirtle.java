package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckSwitchAndTeleportEffectUsed;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.move.WriteMemory;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.WalkStep;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.BallSuccessSegment;
import mrwint.gbtasgen.segment.pokemon.CatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.PokecenterSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.gen1.common.CancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.SwapWithSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.Util;
import mrwint.gbtasgen.util.pokemon.map.Gen1Map;

public class SilphCoSquirtle extends SeqSegment {

	@Override
	public void execute() {
//    {
//      seq(Segment.repress(Move.START));
//
//      seq(Segment.scrollA(-1)); // mon
//      seqSkipInput(1);
//      seq(Segment.scrollAF(1)); // spearow
//      seq(Segment.repress(Move.A)); // fly
//      seq(Segment.repress(Move.A)); // pallet
//    }
//    seqSkipInput(1);
//    seq(new WalkToSegment(2, 9)); // TODO: remove
//    seq(new WalkToSegment(4, 12));
//    seq(new WalkToSegment(4, 13));
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.repress(Move.A)); // mon
//      seqSkipInput(1);
//      seq(Segment.scrollAF(-1)); // blastoise
//      seq(Segment.repress(Move.A)); // surf
//    }
//    seq(new SkipTextsSegment(1)); // got on
//    for(int i=0;i<18;i++)
//      seqMove(new WalkStep(Move.DOWN, true));
//    seqMove(new WalkStep(Move.LEFT, true));
//    for(int i=0;i<80;i++)
//      seqMove(new WalkStep(Move.DOWN, true));
//    seqMove(new WalkStep(Move.RIGHT, true));
//    seq(new WalkToSegment(6, 3)); // enter mansion
//    seq(new WalkToSegment(5, 10)); // l2
//    seq(new WalkToSegment(6, 1, false).setBlockAllWarps(true)); // l3
//    seq(new WalkToSegment(10, 6)); // button
//    seq(new WalkToSegment(10, 5, false).setBlockAllWarps(true)); // button
//    seqButton(Move.A);
//    seq(new SkipTextsSegment(1));
//    seq(new SkipTextsSegment(1, true)); // press button
//    seq(new SkipTextsSegment(1));
//    seq(new WalkToSegment(16, 14, false)); // l1
//    seq(new WalkToSegment(21, 23)); // l0
//    seq(new WalkToSegment(18, 26)); // button
//    seq(new WalkToSegment(18, 25, false).setBlockAllWarps(true)); // button
//    seqButton(Move.A);
//    seq(new SkipTextsSegment(1));
//    seq(new SkipTextsSegment(1, true)); // press button
//    seq(new SkipTextsSegment(1));
//    seq(new WalkToSegment(20, 4)); // button
//    seq(new WalkToSegment(20, 3, false).setBlockAllWarps(true)); // button
//    seqButton(Move.A);
//    seq(new SkipTextsSegment(1));
//    seq(new SkipTextsSegment(1, true)); // press button
//    seq(new SkipTextsSegment(1));
//    seq(new WalkToSegment(5, 12)); // button
//    seqMove(new OverworldInteract(8));
//    seq(new TextSegment());
//    {
//      seqSkipInput(1);
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(1)); // items
//      seqSkipInput(1);
//      seq(Segment.scrollFastAF(1)); // escape rope
//      seq(Segment.repress(Move.A)); // use
//    }
//
//    save("sc1");
//    load("sc1");
//
//    {
//      seqSkipInput(2);
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(-1)); // mons
//      seqSkipInput(1);
//      seq(Segment.scrollAF(1)); // spearow
//      seq(Segment.repress(Move.A)); // fly
//      seq(Segment.scrollA(1)); // cinnamon
//    }
//    seqSkipInput(1);
//    seq(new UseBikeSegment(1, -1));
//    seq(new WalkToSegment(18, 3)); // enter gym
//
//    seq(new WalkToSegment(15, 9));
//    seq(new WalkToSegment(15, 8));
//    seqButton(Move.A);
//    seq(new SkipTextsSegment(8));
//    seq(new SkipTextsSegment(1, true)); // yes
//    seq(new SkipTextsSegment(2));
//
//    seq(new WalkToSegment(10, 2));
//    seq(new WalkToSegment(10, 1, false));
//    seqButton(Move.A);
//    seq(new SkipTextsSegment(9));
//    seq(new SkipTextsSegment(1, false)); // no
//    seq(new SkipTextsSegment(2));
//
//    seq(new WalkToSegment(9, 8));
//    seq(new WalkToSegment(9, 7, false));
//    seqButton(Move.A);
//    seq(new SkipTextsSegment(8));
//    seq(new SkipTextsSegment(1, false)); // no
//    seq(new SkipTextsSegment(2));
//
//    seq(new WalkToSegment(9, 14));
//    seq(new WalkToSegment(9, 13, false));
//    seqButton(Move.A);
//    seq(new SkipTextsSegment(10));
//    seq(new SkipTextsSegment(1, false)); // no
//    seq(new SkipTextsSegment(2));
//
//    seq(new WalkToSegment(1, 15));
//    seq(new WalkToSegment(1, 14));
//    seqButton(Move.A);
//    seq(new SkipTextsSegment(10));
//    seq(new SkipTextsSegment(1, true)); // yes
//    seq(new SkipTextsSegment(2));
//
//    seq(new WalkToSegment(1, 9));
//    seq(new WalkToSegment(1, 8));
//    seqButton(Move.A);
//    seq(new SkipTextsSegment(8));
//    seq(new SkipTextsSegment(1, false)); // no
//    seq(new SkipTextsSegment(2));
//
//    seq(new WalkToSegment(3, 5)); // blaine
//    seq(new WalkToSegment(3, 4)); // blaine
//    seqMove(new OverworldInteract(1));
//		seq(new InitFightSegment(6)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//			seq(kems); // growlithe
//		}
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      seq(kems); // ponita
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment(); // TODO: consider blaine super potion
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 45)}; // tail whip
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.numExpGainers = 2; // L38
//      seq(kems); // rapidash
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckSwitchAndTeleportEffectUsed(), 46)}; // roar
//      kems.attackCount[1][1] = 1; // surf crit
//      seq(kems); // arcanine
//    }
//		seq(new EndFightSegment(2)); // player defeated enemy
//		seq(new SkipTextsSegment(6)); // after battle texts (no room)
//
//    save("sc2");
//    load("sc2");
//
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(2)); // items
//
//      seq(Segment.scrollFastA(9)); // elixer
//      seq(Segment.repress(Move.A)); // use
//      seq(Segment.repress(Move.A)); // blastoise
//      seq(new SkipTextsSegment(1, true)); // pp restored
//
//      seq(Segment.scrollFastA(-8)); // escape rope
//      seq(Segment.repress(Move.A)); // use
//      seqSkipInput(2);
//    }
//	  {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(-1)); // mons
//      seqSkipInput(1);
//      seq(Segment.scrollAF(1)); // spearow
//      seq(Segment.repress(Move.A)); // fly
//      seq(Segment.scrollA(3)); // celadon
//    }
//    seqSkipInput(1);
////    {
////      seq(new WalkToSegment(41,  9)); // enter center
////      seq(new PokecenterSegment(false));
////    }
//    seq(new UseBikeSegment(1, -1-1));
//    seq(new WalkToSegment(50, 10)); // leave celadon
//    seq(new WalkToSegment(12, 10, false)); // enter house
//    seq(new WalkToSegment(3, 4)); // talk to guard
//    seq(new SkipTextsSegment(15)); // give water
//    seq(new WalkToSegment(6, 4, false)); // leave house
//    seq(new UseBikeSegment(0, 0));
//    seq(new WalkToSegment(20, 10)); // enter saffron
//    seq(new WalkToSegment(18, 21)); // enter silph co
//
//    save("sc3");
//    load("sc3");
//
//    seq(new WalkToSegment(26, 0)); // 2f
//    seq(new WalkToSegment(26, 0)); // 3f
//    seq(new WalkToSegment(24, 0)); // 4f
//    seq(new WalkToSegment(26, 0)); // 5f
////    {
////      seq(new WalkToSegment(14, 3)); // elixer
////      seq(new WalkToSegment(13, 3)); // elixer
////      seqButton(Move.A);
////      seq(new TextSegment()); // elixer
////    }
//    seq(new WalkToSegment(8, 15).setBlockAllWarps(true));
//    seqMove(new OverworldInteract(2));
//    seq(new InitFightSegment(1)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
////      kems.attackCount[2][1] = 1; // ice beam crit
//      kems.numExpGainers = 2; // L39
//      seq(kems); // arbok
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//    seq(new WalkToSegment(9, 15)); // warp
//    seq(new WalkToSegment(16, 15));
//    seq(new WalkToSegment(17, 15)); // warp back
//    seq(new WalkToSegment(20, 16));
//    seqMove(new OverworldInteract(8)); // card key
//    seq(new TextSegment());
//
//    save("sc4");
    load("sc4");

    seq(new WalkToSegment(9, 15)); // warp
    seq(new WalkToSegment(16, 15));
    seq(new WalkToSegment(17, 15)); // warp back
    seq(new WalkToSegment(9, 13));
    seq(new WalkToSegment(8, 13));
    seqButton(Move.A); // use card key
    seq(new TextSegment());
    seq(new SkipTextsSegment(2)); // open door
    seq(new WalkToSegment(3, 15)); // warp
    seq(new WalkToSegment(18, 9));
    seq(new WalkToSegment(17, 9, false));
    seqButton(Move.A); // use card key
    seq(new TextSegment());
    seq(new SkipTextsSegment(2)); // open door
    seq(new WalkToSegment(11, 11)); // warp
    seq(new WalkToSegment(4, 2));
    seq(new WalkToSegment(3, 2)); // engage rival

    seq(new InitFightSegment(10)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
      kems.attackCount[1][1] = 1; // surf crit
      seq(kems); // pidgeot
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43)}; // leer
      kems.attackCount[2][1] = 2; // ice beam crit
      seq(kems); // gyarados
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // bubblebeam
//      kems.attackCount[1][0] = 1; // surf
      seq(kems); // growlithe
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[0][0] = 1; // mega kick
      kems.numExpGainers = 2; // L40
      seq(kems); // alakazam
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ice beam crit
      seq(kems); // venusaur
    }
    seq(new EndFightSegment(2)); // player defeated enemy
    seq(new SkipTextsSegment(14)); // after battle texts

    save("sc5");
    load("sc5");

    seq(new WalkToSegment(5, 7)); // warp
    seq(new WalkToSegment(3, 16, false)); // engage
    seqMove(new OverworldInteract(4));
    seq(new InitFightSegment(1)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // bubblebeam
      seq(kems); // cubone
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // bubblebeam crit
      seq(kems); // drowzee
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // bubblebeam
      kems.numExpGainers = 2; // L41
      seq(kems); // marowak
    }
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(6, 15));
    seq(new WalkToSegment(6, 14));
    seqButton(Move.A); // use card key
    seq(new TextSegment());
    seq(new SkipTextsSegment(2)); // open door
    seq(new WalkToSegment(6, 13));
		seq(new InitFightSegment(7)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
      kems.attackCount[2][1] = 1; // ice beam crit
      seq(kems); // nidorino
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
      kems.attackCount[2][1] = 1; // ice beam crit
      seq(kems); // kangaskhan
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
      kems.attackCount[2][0] = 1; // ice beam
      seq(kems); // rhyhorn
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.attackCount[2][1] = 1; // ice beam crit
      kems.attackCount[1][0] = 1; // surf
      kems.numExpGainers = 2; // L42
      seq(kems); // nidoqueen
    }
    seq(new CancelMoveLearnSegment()); // Skull bash
		seq(new EndFightSegment(1)); // player defeated enemy
		seq(new SkipTextsSegment(7)); // after battle texts
    {
      seq(Segment.repress(Move.START));
      seq(Segment.scrollA(2)); // items
      seqSkipInput(1);
      seq(Segment.scrollFastAF(1)); // escape rope
      seq(Segment.repress(Move.A)); // use
      seqSkipInput(2);
    }

    save("sc6");
    load("sc6");

//    {
//      seq(new UseBikeSegment(0, -1));
//      seq(new WalkToSegment(50, 10)); // leave celadon
//      seq(new WalkToSegment(12, 10, false)); // enter house
//      seq(new WalkToSegment(6, 4, false)); // leave house
//      seq(new UseBikeSegment(0, 0));
//      seq(new WalkToSegment(20, 10)); // enter saffron
//    }
    {
      seq(Segment.repress(Move.START));
      seq(Segment.scrollA(-1)); // mons
      seqSkipInput(1);
      seq(Segment.scrollAF(1)); // spearow
      seq(Segment.repress(Move.A)); // fly
      seq(Segment.scrollA(1)); // saffron
      seqSkipInput(1);
      seq(new UseBikeSegment(1, -1));
      seq(new WalkToSegment(9, 31));
    }
    seq(new WalkToSegment(34, 3)); // enter gym
    seq(new WalkToSegment(11, 15)); // warp
    seq(new WalkToSegment(15, 15)); // warp
    seq(new WalkToSegment(15, 5)); // warp
    seq(new WalkToSegment(1, 5)); // warp
    seq(new WalkToSegment(9, 10));
    seq(new WalkToSegment(9, 9));
    seqMove(new OverworldInteract(1)); // talk to sabrina

    seq(new InitFightSegment(8)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[0][0] = 1; // mega kick
      seq(kems); // kadabra
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // surf crit
      seq(kems); // mr.mime
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // surf crit
      seq(kems); // venomoth
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE, 115)}; // reflect
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(60)}; // psybeam
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE, 105)}; // recover
      kems.attackCount[0][1] = 1; // mega kick crit
      seq(kems); // alakazam
    }
    seq(new EndFightSegment(6)); // player defeated enemy
    seq(new SkipTextsSegment(9)); // after battle texts (no room)

    save("sc7");
    load("sc7");

    seq(new WalkToSegment(11, 11)); // warp

    {
      seq(Segment.repress(Move.START));
      seq(Segment.scrollA(2)); // items
      seqSkipInput(1);
      seq(Segment.scrollFastAF(1)); // escape rope
      seq(Segment.repress(Move.A)); // use
      seqSkipInput(2);
    }
    {
      seq(Segment.repress(Move.START));
      seq(Segment.scrollA(-1)); // mons
      seqSkipInput(1);
      seq(Segment.scrollAF(1)); // spearow
      seq(Segment.repress(Move.A)); // fly
      seq(Segment.scrollA(-1)); // veridian
      seqSkipInput(1);
      seq(new UseBikeSegment(1, 0));
      seq(new WalkToSegment(32, 7)); // enter gym
    }
	}
}
