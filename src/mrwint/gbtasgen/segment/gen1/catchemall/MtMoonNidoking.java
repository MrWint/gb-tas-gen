package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.CatchMonSegment;
import mrwint.gbtasgen.segment.PokecenterSegment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.ThrashEnemyMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class MtMoonNidoking extends SeqSegment {
	
	@Override
	public void execute() {
		seq(new WalkToSegment(18,5));

		seq(new WalkToSegment(33,31).setBlockAllWarps(true)); // go to Rare Candy
		seq(new WalkToSegment(34,31)); // go to Rare Candy
		seq(new OverworldInteract(10)); // pick up Rare Candy
		seq(new TextSegment()); // pick up Rare Candy

		seq(new WalkToSegment(34,23).setBlockAllWarps(true)); // go to Escape Rope
		seq(new WalkToSegment(35,23)); // go to Escape Rope
		seq(new OverworldInteract(11)); // pick up Escape Rope
		seq(new TextSegment()); // pick up Escape Rope

		seq(new WalkToSegment(4,2).setBlockAllWarps(true)); // go to Moon Stone
		seq(new WalkToSegment(3,2)); // go to Moon Stone
		seq(new OverworldInteract(9)); // pick up Moon Stone
		seq(new TextSegment()); // pick up Moon Stone

		seq(new WalkToSegment(5,5,false)); // go to MtMoon2
		seq(new WalkToSegment(21,17)); // go to MtMoon3

		seq(new WalkToSegment(11,16,false)); // go to rocket
		seq(new OverworldInteract(2)); // talk to rocket
		seq(new InitFightSegment(3)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[3][0] = 1; // 1x thrash
			kems.thrashNumAdditionalTurns = 1;
			kems.onlyPrintInfo = false;
			seq(kems); // Rattata
		}
		seq(new ThrashEnemyMonSegment());
		seq(Move.B);
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(17,12)); // go to Moon Stone
		seq(Move.A); // pick up Moon Stone
		seq(new TextSegment()); // pick up Moon Stone

		seq(new WalkToSegment(13,8));
		seq(new InitFightSegment(3)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[3][0] = 1; // 1x thrash
			kems.thrashNumAdditionalTurns = 2;
			kems.onlyPrintInfo = false;
			seq(kems); // Grimer
		}
		seq(new ThrashEnemyMonSegment());
		seq(new ThrashEnemyMonSegment());
		seq(Move.B);
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(13,7)); // go to fossil
		seq(new MoveSegment(new OverworldInteract(7))); // grab fossil
		seq(new SkipTextsSegment(1, true)); // grab fossil
		seq(new SkipTextsSegment(1)); // got fossil
		seq(new TextSegment()); // put fossil in bag
		seq(new WalkToSegment(13,5)); // go upwards (avoid running into moved nerd)

		seq(new WalkToSegment(10, 4)); // go in grass
		delay(new SeqSegment() {
			@Override
			protected void execute() {
				seq(Move.LEFT);
				seq(new CheckEncounterMetric(4, 0)); // Clefairy
			}
		});
		seq(new CatchMonSegment(0));

		seq(new WalkToSegment(5,7)); // go to MtMoon2
		seq(new WalkToSegment(27,3)); // leave MtMoon
		seq(new WalkToSegment(76,9,false));
		seq(new WalkToSegment(90,10)); // enter Cerulean
		seq(new WalkToSegment(19,17)); // enter Center
		seq(new PokecenterSegment(false)); // set warp point in center
		seq(new WalkToSegment(20,6)); // go to rival fight
	}
}
