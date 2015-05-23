package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class EliteFourSquirtle extends SeqSegment {

	@Override
	public void execute() {

	  {
      seq(new WalkToSegment(15, 9)); // PC
      seq(new WalkToSegment(15, 8)); // PC
      seqButton(A); // PC
      seq(new SkipTextsSegment(1)); // turned on PC
      seqButton(A); // Bills PC
      seq(new SkipTextsSegment(2)); // access Bills PC
      seq(Segment.scrollAF(1)); // deposit
      seqSkipInput(1);
      seq(Segment.scrollAF(1)); // spearow
      seq(Segment.repress(A)); // deposit
      seq(new SkipTextsSegment(1)); // deposited
      seq(Segment.menu(A)); // deposit
      seqSkipInput(1);
      seq(Segment.scrollAF(1)); // sandshrew
      seq(Segment.repress(A)); // deposit
      seq(new SkipTextsSegment(1)); // deposited
      seq(Segment.menu(B)); // exit
      seq(Segment.menu(B)); // exit
	  }

    seq(new WalkToSegment(8, 0)); // enter elite four
//
//    seq(new WalkToSegment(4, 2)); // engage lorelei
//    seq(new WalkToSegment(5, 2, false)); // engage lorelei
//    seqMove(new OverworldInteract(1));
//    seq(new InitFightSegment(9)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // fissure
//      kems.numExpGainers = 2; // L47
//      seq(kems); // dewgong
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // fissure
//      seq(kems); // cloyster
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // fissure
//      seq(kems); // slowbro
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(37)}; // thrash
//      kems.attackCount[0][1] = 1; // mega kick crit
//      seq(kems); // jynx
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // fissure
//      kems.numExpGainers = 2; // L48
//      seq(kems); // lapras
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//    seq(new SkipTextsSegment(4)); // after battle texts
//    seq(new WalkToSegment(4, -1, false));
//
//    save("ef1");
//    load("ef1");
//
//    seqSkipInput(6);
//    {
//      seq(repress(START));
//      seq(scrollA(2)); // items
//      seq(Segment.scrollFastA(8)); // elixer
//      seq(repress(A)); // use
//      seq(repress(A)); // blastoise
//      seq(new SkipTextsSegment(1, true)); // pp restored
//      seqButton(B); // cancel
//      seqButton(START); // cancel
//    }
//
//    seq(new WalkToSegment(4, 2)); // walk up to trainer
//    seq(new WalkToSegment(5, 2, false)); // walk up to trainer
//    seq(new MoveSegment(new OverworldInteract(1))); // talk to trainer
//    seq(new InitFightSegment(10)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // ice beam
//      seq(kems); // onix
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // hitmonchan
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
//      kems.numExpGainers = 2; // L49
//      seq(kems); // hitmonlee
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // ice beam
//      seq(kems); // onix
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // fissure
//      seq(kems); // machamp
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//    seq(new SkipTextsSegment(2)); // after battle texts
//    seq(new WalkToSegment(4, -1, false));
//
//    save("ef2");
//    load("ef2");
//
//    seq(new WalkToSegment(4, 2)); // walk up to trainer
//    seq(new WalkToSegment(5, 2, false)); // walk up to trainer
//    seq(new MoveSegment(new OverworldInteract(1))); // talk to trainer
//    seq(new InitFightSegment(12)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckSleepEffectMisses(), 95)}; // hypnosis
//      kems.attackCount[1][1] = 2; // surf crit
//      seq(kems); // gengar
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
//      kems.numExpGainers = 2; // L50
//      seq(kems); // golbat
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // fissure
//      seq(kems); // haunter
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // surf crit
//      seq(kems); // arbok
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), 92)}; // toxic
//      kems.attackCount[1][1] = 2; // surf crit
//      seq(kems); // gengar
//    }
//    seq(new EndFightSegment(2)); // player defeated enemy
//    seq(new SkipTextsSegment(4)); // after battle texts
//    seq(new WalkToSegment(4, -1, false));
//
//    save("ef3");
//    load("ef3");
//
//    seq(new WalkToSegment(5, 11)); // walk up to trainer
//    seq(new WalkToSegment(5, 1)); // walk up to trainer
////    seqMove(new WriteMemory(0xD16D, 1)); // redbar
//    seq(new InitFightSegment(13)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43)}; // leer
//      kems.attackCount[0][1] = 2; // mega kick crit
//      kems.numExpGainers = 2; // L51
//      seq(kems); // gyarados
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // dragonair
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // dragonair
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckConfusionEffectMisses(), 48)}; // supersonic
//      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // aerodactyl
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
//      kems.numExpGainers = 2; // L52
//      seq(kems); // dragonite
//    }
//    seq(new CancelMoveLearnSegment()); // hydro pump
//    seq(new EndFightSegment(3)); // player defeated enemy
//    seq(new SkipTextsSegment(14)); // after battle texts
//    seq(new WalkToSegment(5, -1, false));
//
//    save("ef4");
//    load("ef4");
//
//    seq(new InitFightSegment(18)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // pidgeot
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE, 115)}; // reflect
//      kems.attackCount[0][1] = 1; // mega kick crit
//      seq(kems); // alakazam
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[1][0] = 1; // surf
//      kems.numExpGainers = 2; // L53
//      seq(kems); // rhydon
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43)}; // leer
//      kems.attackCount[0][1] = 2; // mega kick crit
//      seq(kems); // gyarados
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckSwitchAndTeleportEffectUsed(), 46)}; // roar
//      kems.attackCount[1][1] = 1; // surf crit
//      seq(kems); // arcanine
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // fissure
//      kems.numExpGainers = 2; // L54
//      seq(kems); // venusaur
//    }
//    seq(new EndFightSegment(6)); // player defeated enemy
//
//    save("ef5");
//    load("ef5");
//
//    seq(new SkipTextsSegment(6+28+15)); // Oak HoF speech
//    seq(new TextSegment());
//    seq(new TextSegment());
//    seq(new SkipTextsSegment(2));
	}
}
