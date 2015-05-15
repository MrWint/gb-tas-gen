package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.PokecenterSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class MtMoonNido extends SeqSegment {

	@Override
	public void execute() {
//		seq(new WalkToSegment(18,5));
//
//		seq(new WalkToSegment(5,5,false).setBlockAllWarps(true)); // go to MtMoon2
//		seq(new WalkToSegment(21,17)); // go to MtMoon3
//
//		seq(new WalkToSegment(11,16,false)); // go to rocket
//		seqMove(new OverworldInteract(2)); // talk to rocket
//
//		save("mm1");
////		load("mm1");
//
//		seq(new InitFightSegment(3)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
//			kems.attackCount[1][1] = 2; // 2x tackle crit
//			seq(kems); // Rattata
//		}
//		seq(NewEnemyMonSegment.any());
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(141)}; // leech life
////			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckConfusionEffectMisses(), 48)}; // supersonic
//      kems.attackCount[1][0] = 1; // 1x tackle
//      kems.attackCount[2][1] = 1; // 1x horn crit
//			kems.numExpGainers = 2; // level up to 15
//			seq(kems); // Zubat
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//    save("mm2");
////    load("mm2");
//    //
//
//    seq(new WalkToSegment(17,12)); // go to Moon Stone
//    seqButton(Move.A); // pick up Moon Stone
//    seq(new TextSegment()); // pick up Moon Stone
//
//		seq(new WalkToSegment(13,8));
//		seq(new InitFightSegment(3)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[2][0] = 1; // 1x horn
//      kems.attackCount[2][1] = 1; // 1x horn crit
//			seq(kems); // Grimer
//		}
//		seq(NewEnemyMonSegment.any());
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
//      kems.attackCount[2][0] = 2; // 2x horn
//      kems.numExpGainers = 2; // level up to 16
//			seq(kems); // Voltorb
//		}
//		seq(NewEnemyMonSegment.any());
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(123)}; // smog
//      kems.attackCount[2][0] = 1; // 1x horn
//      kems.attackCount[2][1] = 1; // 1x horn crit
//			seq(kems); // Koffing
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//    seq(new TextSegment()); // evolution
//    seq(new TextSegment());
//
//		save("mm4");
		load("mm4");

		seq(new WalkToSegment(13,7)); // go to fossil
		seq(new MoveSegment(new OverworldInteract(7))); // grab fossil
		seq(new SkipTextsSegment(1, true)); // grab fossil
		seq(new SkipTextsSegment(1)); // got fossil
		seq(new TextSegment(Move.B, true, 0)); // put fossil in bag
		seq(new WalkToSegment(13,6).setMaxBufferSize(0)); // go upwards (avoid running into moved nerd)

		{
	    seq(new WalkToSegment(6,4).setMaxBufferSize(0)); // align
	    seq(new EncounterAndCatchSegment(109, Move.LEFT)); // paras
		}

		seq(new WalkToSegment(5,7).setMaxBufferSize(0)); // go to MtMoon2
		seq(new WalkToSegment(27,3).setMaxBufferSize(0)); // leave MtMoon
//
		save("mm5");
//		load("mm5");

		seq(new WalkToSegment(76,9,false).setMaxBufferSize(0));
		seq(new WalkToSegment(90,10).setMaxBufferSize(0)); // enter Cerulean
		seq(new WalkToSegment(19,17).setMaxBufferSize(0)); // enter Center
		seq(new PokecenterSegment(true)); // set warp point in center
		seq(new WalkToSegment(30,19)); // go to gym
	}
}
