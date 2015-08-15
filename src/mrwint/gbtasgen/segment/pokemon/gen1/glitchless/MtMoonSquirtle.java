package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
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

public class MtMoonSquirtle extends SeqSegment {

	@Override
	public void execute() {
		seq(new WalkToSegment(18,5));

		seq(new WalkToSegment(17,11,false).setBlockAllWarps(true)); // l2
		seq(new WalkToSegment(17,11)); // l3

		seq(new WalkToSegment(29,6));
		seq(Segment.press(Move.UP)); // face item
		seq(new MoveSegment(new OverworldInteract(9))); // collect TM01
		seq(new TextSegment()); // found TM01

		seq(Segment.skip(1));
		seq(Segment.repress(Move.START));
		seq(Segment.scroll(2));
		seq(Segment.press(Move.A)); // items
		{ // learn TM01
			seq(Segment.scrollFastAF(4));
			seq(Segment.repress(Move.A)); // use
			{
				seq(new SkipTextsSegment(2)); // booted up TM, contains xyz
				seq(new SkipTextsSegment(1, true)); // learn
				seq(Segment.repress(Move.A)); // select squirtle
        seq(new SkipTextsSegment(5)); // learned TM
        seq(new SkipTextsSegment(1, true)); // yes, overwrite
        seq(new TextSegment());
        seq(Segment.repress(Move.A)); // tackle
        seq(new TextSegment(Move.A, false)); // 1,2 and
        seqButton(Move.B); // skip 30f
        seqButton(Move.B);
        seq(new TextSegment()); // poof
        seqButton(Move.A); // skip 30f
        seqButton(Move.B);
        seq(new SkipTextsSegment(3)); // learned TM
			}
		}
		seq(Segment.repress(Move.B));
		seqButton(Move.START);
		seq(new WalkToSegment(25,9));
		seq(new WalkToSegment(25,9));

		seq(new WalkToSegment(5,5,false).setBlockAllWarps(true)); // go to MtMoon2
		seq(new WalkToSegment(21,17)); // go to MtMoon3

		seq(new WalkToSegment(11,16,false)); // go to rocket
		seqMove(new OverworldInteract(2)); // talk to rocket

		save("mm1");
//		load("mm1");

		seq(new InitFightSegment(3)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
			kems.attackCount[0][1] = 1; // 1x mega punch crit
			seq(kems); // Rattata
		}
		seq(NewEnemyMonSegment.any());
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][1] = 1; // 1x mega punch crit
			kems.numExpGainers = 2; // level up to 16
			seq(kems); // Zubat
		}
		seq(new EndFightSegment(1)); // player defeated enemy

    save("mm2");
//    load("mm2");

    seq(new TextSegment()); // evolution
    seq(new TextSegment());

		seq(new WalkToSegment(13,8));
		seq(new InitFightSegment(3)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(1)}; // pound
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
			kems.attackCount[0][0] = 2; // 2x mega punch
			seq(kems); // Grimer
		}
		seq(NewEnemyMonSegment.any());
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
			kems.attackCount[0][1] = 1; // 1x mega punch crit
			seq(kems); // Voltorb
		}
		seq(NewEnemyMonSegment.any());
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(123)}; // smog
			kems.attackCount[0][0] = 1; // 1x mega punch
			kems.attackCount[0][1] = 1; // 1x mega punch crit
			kems.numExpGainers = 2; // level up to 17
			seq(kems); // Koffing
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		save("mm4");
//		load("mm4");

		seq(new WalkToSegment(13,7)); // go to fossil
		seq(new MoveSegment(new OverworldInteract(7))); // grab fossil
		seq(new SkipTextsSegment(1, true)); // grab fossil
		seq(new SkipTextsSegment(1)); // got fossil
		seq(new TextSegment()); // put fossil in bag
		seq(new WalkToSegment(13,6)); // go upwards (avoid running into moved nerd)

		seq(new WalkToSegment(5,7)); // go to MtMoon2
		seq(new WalkToSegment(27,3)); // leave MtMoon

		save("mm5");
//		load("mm5");

		seqUnbounded(new WalkToSegment(64, 9, false)); // hop into grass
		seqUnbounded(new WalkToSegment(64, 12)); // hop into grass

    EncounterAndCatchSegment.defaultBallIndex = 2; // TODO: fix to 1
		seq(new EncounterAndCatchSegment(96, Move.DOWN)); // sandshrew

		seqUnbounded(new WalkToSegment(90,11)); // enter Cerulean
	}
}
