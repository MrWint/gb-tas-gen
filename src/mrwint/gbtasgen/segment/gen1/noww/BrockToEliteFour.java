package mrwint.gbtasgen.segment.gen1.noww;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.gen1.common.DepositMonSegment;
import mrwint.gbtasgen.segment.gen1.common.WithdrawMonSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class BrockToEliteFour extends SeqSegment {

	@Override
	public void execute() {
		seq(new WalkToSegment(0, 8));
		seq(new WalkToSegment(-1, 8));
		seq(new WalkToSegment(36, 16));
		seq(new OverworldInteract(5));
		seq(new SkipTextsSegment(4)); // pewter skip text
		for (int i=0;i<36;i++) {
			seq(new PressButton(Move.LEFT), 0);
		}
		for (int i=0;i<100;i++) {
			seq(new PressButton(Move.DOWN), 0);
		}
		seq(new PressButton(Move.LEFT), 0);
		seq(new PressButton(Move.UP), 0);
		for (int i=0;i<44;i++) {
			seq(new PressButton(Move.UP), 0);
		}
		for (int i=0;i<4;i++) {
			seq(new PressButton(Move.LEFT), 0);
		}
//		seq(new WalkToSegment(15,9));
//		seq(new WalkToSegment(15,8, false)); // correct facing direction
//		seq(Segment.press(Move.A)); // use PC
//		seq(new SkipTextsSegment(1)); // booted PC
//		seq(Segment.press(Move.A)); // someones PC
//		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
//		seq(Segment.scroll(1)); // deposit
//		seq(new DepositMonSegment(1)); // Charmander
//		seq(Segment.menu(Move.B)); // leave
//		seq(Segment.menu(Move.B)); // leave
		seq(new WalkToSegment(8,0));
	}
}
