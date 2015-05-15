package mrwint.gbtasgen.segment.pokemon.gen1.catchemall;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckConfusionEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.CatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.PokecenterSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.gen1.common.DepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.UseEvoStoneSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class MtMoonNormal extends SeqSegment {
	
	@Override
	public void execute() {
//		seq(new WalkToSegment(18,5));
//
//		seq(new WalkToSegment(33,31).setBlockAllWarps(true)); // go to Rare Candy
//		seq(new WalkToSegment(34,31)); // go to Rare Candy
//		seq(new OverworldInteract(10)); // pick up Rare Candy
//		seq(new TextSegment()); // pick up Rare Candy
//
//		seq(new WalkToSegment(17,11,false).setBlockAllWarps(true)); // l2
//		seq(new WalkToSegment(17,11)); // l3
//
//		seq(new WalkToSegment(29,6));
//		seq(Segment.press(Move.UP)); // face item
//		seq(new MoveSegment(new OverworldInteract(9))); // collect TM01
//		seq(new TextSegment()); // found TM01
//		seq(Segment.skip(1));
//		seq(Segment.repress(Move.START));
//		
//		seq(Segment.scroll(2));
//		seq(Segment.press(Move.A)); // items
//		{ // learn TM01
//			seq(Segment.scrollFast(3));
//			seq(Segment.press(Move.A)); // select TM01
//			seq(Segment.repress(Move.A)); // use
//			{
//				seq(new SkipTextsSegment(2)); // booted up TM, contains xyz
//				seq(new SkipTextsSegment(1, true)); // learn
//				seq(Segment.repress(Move.A)); // select charmander
//				seq(new SkipTextsSegment(1)); // learned TM
//			}
//		}
//		{
//			seq(Segment.repress(Move.B));
//			seq(Move.START);
//			seq(new WalkToSegment(25,9));
//			seq(new WalkToSegment(25,9));
//		}
//
//		seq(new WalkToSegment(4,2).setBlockAllWarps(true)); // go to Moon Stone
//		seq(new WalkToSegment(3,2)); // go to Moon Stone
//		seq(new OverworldInteract(9)); // pick up Moon Stone
//		seq(new TextSegment()); // pick up Moon Stone
//
//		seq(new WalkToSegment(5,5,false).setBlockAllWarps(true)); // go to MtMoon2
//		seq(new WalkToSegment(21,17)); // go to MtMoon3
//
//
//		seq(new WalkToSegment(11,16,false)); // go to rocket
//		seq(new OverworldInteract(2)); // talk to rocket
//		
//		save("mm1c");
//		load("mm1c");
//		
//		seq(new InitFightSegment(3)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.onlyPrintInfo = false;
//			seq(kems); // Rattata
//		}
//		seq(NewEnemyMonSegment.any());
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(141)}; // leech life
////			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckConfusionEffectMisses(), 48)}; // supersonic
//			kems.attackCount[3][0] = 2; // 2x mega punch
//			kems.onlyPrintInfo = false;
//			seq(kems); // Zubat
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(17,12)); // go to Moon Stone
//		seq(Move.A); // pick up Moon Stone
//		seq(new TextSegment()); // pick up Moon Stone

//		save("mm2c");
//		load("mm2b");
//		
//		seq(new WalkToSegment(13,8));
//		seq(new InitFightSegment(3)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(1)}; // pound
////			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//			kems.attackCount[3][0] = 1; // 1x mega punch
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.numExpGainers = 2;
//			kems.onlyPrintInfo = false;
//			seq(kems); // Grimer
//		}
//		{ // cancel learning leer
//			seq(new SkipTextsSegment(6));
//			seq(new SkipTextsSegment(1, true));
//			seq(new SkipTextsSegment(2));
//		}
//		seq(NewEnemyMonSegment.any());
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.numExpGainers = 1;
//			kems.onlyPrintInfo = false;
//			seq(kems); // Voltorb
//		}
//
//		save("mm3b");
//		load("mm3b");
//
//		seq(NewEnemyMonSegment.any());
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
////			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(123)}; // smog
//			kems.attackCount[0][0] = 1; // 1x ember
//			kems.attackCount[0][1] = 1; // 1x ember crit
//			kems.numExpGainers = 2;
//			kems.onlyPrintInfo = false;
//			seq(kems); // Koffing
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//		seq(new TextSegment()); // evolution
//		seq(new TextSegment()); // evolution
//
//		
//		save("mm4b");
//		load("mm4a");
//
//		seq(new WalkToSegment(13,7)); // go to fossil
//		seq(new MoveSegment(new OverworldInteract(7))); // grab fossil
//		seq(new SkipTextsSegment(1, true)); // grab fossil
//		seq(new SkipTextsSegment(1)); // got fossil
//		seq(new TextSegment()); // put fossil in bag
//		seq(new WalkToSegment(13,6)); // go upwards (avoid running into moved nerd)
//
//		seq(new WalkToSegment(3, 6)); // go in grass
//		seq(new EncounterAndCatchSegment(4, Move.RIGHT)); // Clefairy
//
//		seq(new WalkToSegment(5,7)); // go to MtMoon2
//		seq(new WalkToSegment(27,3)); // leave MtMoon
//
//		save("mm5a");
		load("mm5a");

		seq(new WalkToSegment(76,9,false));
		seq(new WalkToSegment(90,10)); // enter Cerulean
		seq(new WalkToSegment(19,17)); // enter Center
		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(2)); // items
		seq(Segment.scrollFast(3)); // moon stone
		seq(new UseEvoStoneSegment(-1)); // Clefable
		seq(Segment.repress(Move.B));
		seqButton(Move.START);
		seq(new WalkToSegment(13,4));
		seq(new WalkToSegment(13,3, false)); // correct facing direction
		seq(Segment.press(Move.A)); // use PC
		seq(new SkipTextsSegment(1)); // booted PC
		seq(Segment.press(Move.A)); // someones PC
		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
		seq(Segment.scroll(1)); // deposit
		seq(new DepositMonSegment(1)); // Jigglypuff
		seq(new DepositMonSegment(2)); // Clefable
		seq(Segment.menu(Move.B)); // leave
		seq(Segment.menu(Move.B)); // leave
		seq(new WalkToSegment(3, 4));
		seq(new WalkToSegment(3, 3));
		seq(new MoveSegment(new OverworldInteract(1))); // talk to nurse
		seq(new SkipTextsSegment(3));
		seq(new TextSegment(Move.B)); // extra line on first visit
		seq(Segment.press(Move.A)); // confirm healing mons
		seq(new TextSegment()); // need your mons
		seq(new SkipTextsSegment(3));
		seq(new WalkToSegment(3, 8, false)); // leave center
//		seq(new PokecenterSegment(true)); // set warp point in center
		seq(new WalkToSegment(20,6)); // go to rival fight
	}
}
