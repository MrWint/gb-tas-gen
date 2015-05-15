package mrwint.gbtasgen.segment.pokemon.gen1.noww;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.DepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.WithdrawMonSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class BrockToEliteFourTM extends SeqSegment {

	@Override
	public void execute() {
		seq(new WalkToSegment(0, 8));
		seq(new WalkToSegment(-1, 8));
		seq(new WalkToSegment(36, 16));
		seqMove(new OverworldInteract(5));
		seq(new SkipTextsSegment(4)); // pewter skip text
		for (int i=0;i<38;i++) {
			seqMove(new PressButton(Move.RIGHT | Move.LEFT), 0);
		}
		for (int i=0;i<100;i++) {
			seqMove(new PressButton(Move.RIGHT | Move.DOWN), 0);
		}
		seqMove(new PressButton(Move.RIGHT | Move.LEFT), 0);
		seqMove(new PressButton(Move.RIGHT | Move.UP), 0);
		for (int i=0;i<44;i++) {
			seqMove(new PressButton(Move.RIGHT | Move.UP), 0);
		}
		for (int i=0;i<4;i++) {
			seqMove(new PressButton(Move.RIGHT | Move.LEFT), 0);
		}
		seq(new WalkToSegment(15,9));
		seq(new WalkToSegment(15,8, false)); // correct facing direction
		seq(Segment.press(Move.A)); // use PC
		seq(new SkipTextsSegment(1)); // booted PC
		seq(Segment.press(Move.A)); // someones PC
		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
		seq(Segment.scroll(1)); // deposit
		seq(new DepositMonSegment(1)); // Charmander
		seq(Segment.menu(Move.B)); // leave
		seq(Segment.menu(Move.B)); // leave
		seq(new WalkToSegment(8,0));
	}
}
