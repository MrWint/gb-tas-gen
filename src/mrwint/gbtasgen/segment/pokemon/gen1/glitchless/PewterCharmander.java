package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class PewterCharmander extends SeqSegment {

	@Override
	public void execute() {
//    seq(new WalkToSegment(16, 17)); // enter gym
//    seq(new WalkToSegment(4, 3));
//    seq(new WalkToSegment(4, 2));
//    seq(new MoveSegment(new OverworldInteract(1)));
//
//    seq(new InitFightSegment(9)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[2][1] = 5; // 5x ember crit
//      kems.numExpGainers = 2; // level up to 10
//      kems.onlyPrintInfo = false;
//      seq(kems); // Geodude
//    }
//    save("tmp");
    load("tmp");
//    seq(new TextSegment()); // trainer sent out mon
//    seqButton(Move.A);
//    seqMove(new PressButton(Move.A, Metric.PRESSED_JOY));
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
      kems.attackCount[2][0] = 1; // 1x ember
      kems.attackCount[2][1] = 4; // 4x ember crit
      kems.numExpGainers = 2; // level up to 12
      kems.onlyPrintInfo = false;
      seq(kems); // Onix
    }
    save("tmp2");
    seq(new EndFightSegment(10)); // player defeated enemy
    seq(new SkipTextsSegment(14)); // after battle talk
    seq(new WalkToSegment(4, 14, false)); // exit gym
    seq(new WalkToSegment(40, 17)); // leave pewter
	}
}
