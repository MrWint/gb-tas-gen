package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.CheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.MissMetricSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.SwitchPokemonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class PewterNido extends SeqSegment {

	@Override
	public void execute() {
//    seq(Segment.press(Move.START)); // open menu
//    seq(Segment.scrollA(1)); // pokemon
//    seq(Segment.repress(Move.A)); // open menu
//    seqSkipInput(1);
//    seq(Segment.scrollAF(1)); // switch
//    seqSkipInput(1);
//    seq(Segment.scrollAF(1)); // switch
//    seq(Segment.press(Move.B)); // close menu
//    seq(Segment.press(Move.START)); // close menu
//
//    seq(new WalkToSegment(16, 17)); // enter gym
//    seq(new WalkToSegment(4, 3));
//    seq(new WalkToSegment(4, 2));
//    seq(new MoveSegment(new OverworldInteract(1)));
//
//    seq(new InitFightSegment(9)); // start fight
////    save("tmp");
////    load("tmp");
//	  seq(new SwitchPokemonSegment(1, EnemyMoveDesc.missWith(33), true));
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[2][0] = 2; // 2x bubble
//      kems.numExpGainers = 4; // nido level up to 6, squirtle level up to 9
//      kems.onlyPrintInfo = false;
//      seq(kems); // Geodude
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(NewEnemyMonSegment.any()); // next mon
//    seq(new SwitchPokemonSegment(-1, EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103), false));
//    seq(new SwitchPokemonSegment(1, EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103), false));
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
//      kems.attackCount[2][0] = 2; // 2x bubble
//      kems.numExpGainers = 5; // nido level up to 8, learn horn attack, squirtle level up to 10
//      kems.onlyPrintInfo = false;
//      seq(kems); // Onix
//    }
//    seq(new EndFightSegment(10)); // player defeated enemy
//    seq(new SkipTextsSegment(14)); // after battle talk
//    seq(new WalkToSegment(4, 14, false)); // exit gym
//    save("p1");
    load("p1");
    seq(new WalkToSegment(23, 17)); // enter pewter mart

    seq(new WalkToSegment(3, 5));
    seq(new WalkToSegment(2, 5));
    seq(new MoveSegment(new OverworldInteract(1)));
    {
      seq(new SkipTextsSegment(1, true)); // buy
      seq(new TextSegment());
      {
        seq(Segment.scrollFastAF(2)); // escape rope
        seq(Segment.scrollA(-4)); // buy x5
        seq(new SkipTextsSegment(1)); // confirmation text
        seq(new SkipTextsSegment(1, true)); // "yes"
        seq(new SkipTextsSegment(1)); // thank you text
      }
      seq(Segment.repress(Move.B)); // cancel
      seq(new SkipTextsSegment(2)); // cancel + bye
    }
    seq(new WalkToSegment(3,8,false)); // leave mart

    seq(new WalkToSegment(40, 17)); // leave pewter
	}
}
