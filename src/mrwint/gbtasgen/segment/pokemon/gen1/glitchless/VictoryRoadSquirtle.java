package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.START;
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

public class VictoryRoadSquirtle extends SeqSegment {

	@Override
	public void execute() {
    seq(new WalkToSegment(15, 5)); // engage
    seq(new InitFightSegment(1)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // bubblebeam
      kems.numExpGainers = 2; // L43
      seq(kems); // rhyhorn
    }
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(10, 4)); // engage
    seq(new InitFightSegment(2)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // bubblebeam crit
      seq(kems); // machoke
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // bubblebeam crit
      seq(kems); // machop
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // bubblebeam crit
      seq(kems); // machoke
    }
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(17, 18, false)); // leave gym
    seq(new WalkToSegment(32, 7)); // enter gym
    seq(new WalkToSegment(15, 5)); // engage

    seq(new WalkToSegment(2, 3)); // engage
    seq(new WalkToSegment(2, 2)); // engage giovanni
    seqMove(new OverworldInteract(1));
    seq(new InitFightSegment(10)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // bubblebeam
      seq(kems); // rhyhorn
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment(); // TODO: consider Guard spec
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 45)}; // growl
      kems.attackCount[3][0] = 1; // bubblebeam
      kems.numExpGainers = 2; // L44
      seq(kems); // dugtrio
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // bubblebeam crit
      seq(kems); // nidoqueen
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // bubblebeam crit
      seq(kems); // nidoking
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // bubblebeam
      kems.numExpGainers = 2; // L45
      seq(kems); // rhydon
    }

    seq(new SkipTextsSegment(1 + 4)); // defeat, texts
    seq(new TextSegment());
    seq(new SkipTextsSegment(1)); // got money
    seq(new SkipTextsSegment(14)); // after battle texts

    seq(new WalkToSegment(15, 5)); // avoid spins
    seq(new WalkToSegment(17, 18, false)); // leave gym
//
//    save("vr1");
//    load("vr1");
//
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(2)); // items
//      seq(Segment.scrollFastA(16)); // HM04
//      seq(Segment.repress(Move.A)); // use
//      seq(new SkipTextsSegment(2)); // booted up HM, contains xyz
//      seq(new SkipTextsSegment(1, true)); // learn
//      seqSkipInput(1);
//      seq(Segment.scrollAF(-1)); // sandshrew
//      seq(new SkipTextsSegment(1, true)); // learned HM
//
//      seq(Segment.scrollFastA(3)); // TM27
//      seq(Segment.repress(Move.A)); // use
//      seq(new SkipTextsSegment(2)); // booted up TM, contains xyz
//      seq(new SkipTextsSegment(1, true)); // learn
//      seqSkipInput(1);
//      seq(Segment.scrollAF(1)); // blastoise
//      seq(new SkipTextsSegment(5)); // learned TM
//      seq(new SkipTextsSegment(1, true)); // yes, overwrite
//      seq(new TextSegment());
//      seq(Segment.scrollAF(3)); // bubblebeam
//      seq(new TextSegment(Move.A, false)); // 1,2 and
//      seqButton(Move.B); // skip 30f
//      seqButton(Move.B);
//      seq(new TextSegment()); // poof
//      seqButton(Move.A); // skip 30f
//      seqButton(Move.B);
//      seq(new SkipTextsSegment(3)); // learned TM
//
//      seqButton(Move.A);
//      seq(new SkipTextsSegment(1)); // get on bike
//    }
//    seq(new WalkToSegment(32, 9, false)); // jump ledge
//    seq(new WalkToSegment(-1, 17)); // leave viridian
//    seq(new WalkToSegment(29, 5)); // engage
//    seq(new InitFightSegment(10)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // surf crit
////      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // pidgeot
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // ice beam
//      seq(kems); // rhyhorn
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//    kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43)}; // leer
//      kems.attackCount[2][1] = 2; // ice beam crit
//      kems.numExpGainers = 2; // L46
//      seq(kems); // gyarados
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // growlithe
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
////      kems.attackCount[0][0] = 2; // mega punch
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE, 115)}; // reflect
////      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(60)}; // psybeam
//      kems.attackCount[0][1] = 1; // mega kick crit
////      kems.attackCount[2][1] = 1; // ice beam crit
////      kems.attackCount[1][1] = 1; // surf crit
//      seq(kems); // alakazam
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // fissure
////      kems.attackCount[2][0] = 1; // ice beam
////      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // venusaur
//    }
//    seq(new EndFightSegment(2)); // player defeated enemy
//    seq(new SkipTextsSegment(5)); // after battle text
//
//    save("vr2");
//    load("vr2");
//
//    seq(new WalkToSegment(21, 7, false)); // jump ledge
//    seq(new WalkToSegment(8, 5)); // enter house
//    seq(new WalkToSegment(4, 2)); // badge check
//    seq(new SkipTextsSegment(2)); // badge check
//    seq(new WalkToSegment(4, 0)); // leave house
//    seq(new UseBikeSegment(2, 0));
//    seq(new WalkToSegment(7, 136)); // badge check
//    seq(new SkipTextsSegment(4)); // badge check
//    seq(new WalkToSegment(9, 119)); // badge check
//    seq(new SkipTextsSegment(4)); // badge check
//    seq(new WalkToSegment(10, 105)); // badge check
//    seq(new SkipTextsSegment(4)); // badge check
//    seq(new WalkToSegment(10, 104)); // water
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(-1)); // mons
//      seq(Segment.repress(Move.A)); // blastoise
//      seq(Segment.repress(Move.A)); // surf
//      seq(new SkipTextsSegment(1)); // got on
//    }
//    for(int i=0;i<7;i++)
//      seqMove(new WalkStep(Move.UP, true));
//    seq(new SkipTextsSegment(4)); // badge check
//    for(int i=0;i<3;i++)
//      seqMove(new WalkStep(Move.UP, true));
//    for(int i=0;i<3;i++)
//      seqMove(new WalkStep(Move.LEFT, true));
//    for(int i=0;i<8;i++)
//      seqMove(new WalkStep(Move.UP, true));
//    seq(new SkipTextsSegment(4)); // badge check
//    for(int i=0;i<10;i++)
//      seqMove(new WalkStep(Move.UP, true));
//    seqMove(new WalkStep(Move.RIGHT, true));
//    for(int i=0;i<4;i++)
//      seqMove(new WalkStep(Move.UP, true));
//    seq(new UseBikeSegment(1, 0));
//    seq(new WalkToSegment(12, 57)); // badge check
//    seq(new WalkToSegment(12, 56)); // badge check
//    seq(new SkipTextsSegment(4)); // badge check
//    seq(new WalkToSegment(7, 35)); // badge check
//    seq(new SkipTextsSegment(4)); // badge check
//    seq(new WalkToSegment(4, 31)); // enter victory road
//
//    save("vr3");
//    load("vr3");
//    {
//      seq(repress(START));
//      seq(scrollA(-1)); // mons
//      seqSkipInput(1);
//      seq(scrollAF(-1)); // sandshrew
//      seqSkipInput(1);
//      seq(scrollAF(1)); // strength
//      seq(new TextSegment());
//      seq(new SkipTextsSegment(1)); // move boulders
//    }
//    seq(new WalkToSegment(5, 14));
//    seq(new WalkToSegment(5, 15, false));
//    seq(new WalkToSegment(5, 15, false));
//    seq(new WalkToSegment(4, 16));
//    seq(new WalkToSegment(5, 16, false));
//    seq(new WalkToSegment(5, 16, false));
//    seq(new WalkToSegment(6, 16, false));
//    seq(new WalkToSegment(7, 16, false));
//    seq(new WalkToSegment(8, 16, false));
//    seq(new WalkToSegment(9, 17));
//    seq(new WalkToSegment(9, 16, false));
//    seq(new WalkToSegment(9, 16, false));
//    seq(new WalkToSegment(9, 15, false));
//    seq(new WalkToSegment(8, 14));
//    seq(new WalkToSegment(9, 14, false));
//    seq(new WalkToSegment(9, 14, false));
//    seq(new WalkToSegment(10, 14, false));
//    seq(new WalkToSegment(11, 14, false));
//    seq(new WalkToSegment(12, 14, false));
//    seq(new WalkToSegment(13, 14, false));
//    seq(new WalkToSegment(14, 14, false));
//    seq(new WalkToSegment(15, 14, false));
//    seq(new WalkToSegment(16, 15));
//    seq(new WalkToSegment(16, 14, false));
//    seq(new WalkToSegment(16, 14, false));
//    seq(new WalkToSegment(16, 13, false));
//    seq(new WalkToSegment(14, 14));
//    seq(new WalkToSegment(15, 12));
//    seq(new WalkToSegment(16, 12, false));
//    seq(new WalkToSegment(17, 11));
//    seq(new WalkToSegment(17, 12, false));
//    seq(new WalkToSegment(17, 12, false));
//    seq(new WalkToSegment(1, 1)); // ladder up
//
//    {
//      seq(repress(START));
//      seq(repress(A)); // mons
//      seq(repress(A)); // sandshrew
//      seqSkipInput(1);
//      seq(scrollAF(1)); // strength
//      seq(new TextSegment());
//      seq(new SkipTextsSegment(1)); // move boulders
//    }
//    seq(new WalkToSegment(5, 14));
//    seq(new WalkToSegment(4, 14, false));
//    seq(new WalkToSegment(4, 14, false));
//    seq(new WalkToSegment(3, 13));
//    seq(new WalkToSegment(3, 14, false));
//    seq(new WalkToSegment(3, 14, false));
//    seq(new WalkToSegment(3, 15, false));
//    seq(new WalkToSegment(4, 16));
//    seq(new WalkToSegment(3, 16, false));
//    seq(new WalkToSegment(3, 16, false));
//    seq(new WalkToSegment(2, 16, false));
//    seq(new WalkToSegment(23, 7)); // ladder up
//
//
//    save("vr4");
//    load("vr4");
//
//    {
//      seq(repress(START));
//      seq(repress(A)); // mons
//      seq(repress(A)); // sandshrew
//      seqSkipInput(1);
//      seq(scrollAF(1)); // strength
//      seq(new TextSegment());
//      seq(new SkipTextsSegment(1)); // move boulders
//    }
//    seq(new WalkToSegment(22, 5));
//    seq(new WalkToSegment(22, 3, false));
//    seq(new WalkToSegment(22, 3, false));
//    seq(new WalkToSegment(22, 2, false));
//    seq(new WalkToSegment(23, 1));
//    seq(new WalkToSegment(22, 1, false));
//    seq(new WalkToSegment(22, 1, false));
//    seq(new WalkToSegment(21, 1, false));
//    seq(new WalkToSegment(20, 1, false));
//    seq(new WalkToSegment(19, 1, false));
//    seq(new WalkToSegment(18, 1, false));
//    seq(new WalkToSegment(17, 1, false));
//    seq(new WalkToSegment(16, 1, false));
//    seq(new WalkToSegment(15, 1, false));
//    seq(new WalkToSegment(14, 1, false));
//    seq(new WalkToSegment(13, 1, false));
//    seq(new WalkToSegment(12, 1, false));
//    seq(new WalkToSegment(11, 1, false));
//    seq(new WalkToSegment(10, 1, false));
//    seq(new WalkToSegment(9, 1, false));
//    seq(new WalkToSegment(8, 1, false));
//    seq(new WalkToSegment(7, 1, false));
//    seq(new WalkToSegment(6, 0));
//    seq(new WalkToSegment(6, 1, false));
//    seq(new WalkToSegment(6, 1, false));
//    seq(new WalkToSegment(7, 2));
//    seq(new WalkToSegment(6, 2, false));
//    seq(new WalkToSegment(6, 2, false));
//    seq(new WalkToSegment(5, 2, false));
//    seq(new WalkToSegment(4, 2, false));
//    seq(new WalkToSegment(3, 2, false));
//    seq(new WalkToSegment(2, 1));
//    seq(new WalkToSegment(2, 2, false));
//    seq(new WalkToSegment(2, 2, false));
//    seq(new WalkToSegment(2, 3, false));
//    seq(new WalkToSegment(2, 4, false));
//    seq(new WalkToSegment(1, 5));
//    seq(new WalkToSegment(2, 5, false));
//    seq(new WalkToSegment(2, 5, false));
//    seq(new WalkToSegment(20, 15));
//    seq(new WalkToSegment(22, 15, false));
//    seq(new WalkToSegment(22, 15, false));
//    seq(new WalkToSegment(23, 15)); // fall down
//
//    seqSkipInput(1);
//    {
//      seq(repress(START));
//      seq(repress(A)); // mons
//      seq(repress(A)); // sandshrew
//      seqSkipInput(1);
//      seq(scrollAF(1)); // strength
//      seq(new TextSegment());
//      seq(new SkipTextsSegment(1)); // move boulders
//    }
//    seq(new UseBikeSegment(1, 0));
//    seq(new WalkToSegment(24, 16));
//    seq(new WalkToSegment(23, 16, false));
//    seq(new WalkToSegment(23, 16, false));
//    seq(new WalkToSegment(23, 16, false));
//    seq(new WalkToSegment(22, 16, false));
//    seq(new WalkToSegment(21, 16, false));
//    seq(new WalkToSegment(20, 16, false));
//    seq(new WalkToSegment(19, 16, false));
//    seq(new WalkToSegment(18, 16, false));
//    seq(new WalkToSegment(17, 16, false));
//    seq(new WalkToSegment(16, 16, false));
//    seq(new WalkToSegment(15, 16, false));
//    seq(new WalkToSegment(14, 16, false));
//    seq(new WalkToSegment(13, 16, false));
//    seq(new WalkToSegment(12, 16, false));
//    seq(new WalkToSegment(11, 16, false));
//    seq(new WalkToSegment(10, 16, false));
//    seq(new WalkToSegment(25, 14)); // ladder up
//    seq(new WalkToSegment(26, 8)); // ladder down
//    seq(new WalkToSegment(30, 7, false)); // leave victory road
//    seq(new WalkToSegment(10, -1)); // enter indigo plateau
//    seq(new WalkToSegment(10, 5)); // enter indigo plateau
	}
}
