package mrwint.gbtasgen.segment.playback;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.rom.pokemon.gen2.GoldRomInfo;
import mrwint.gbtasgen.segment.SingleGbSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen2.common.NamingSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.SingleGbRunner;

public class S02GoldIntro extends SingleGbSegment {

	@Override
	protected void execute() {
//    seq(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqButton(Move.A);    // skip intro cutscene
//        seqButton(Move.START);  // skip title screen
//        seqButton(Move.A);   // start new game
//      }
//    });
//		save("intro");
//
//		load("intro");
//    seq(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seq(new SkipTextsSegment(3));
//        seq(new SkipTextsSegment(4, true)); // 10:00 confirmed
//        seq(new SkipTextsSegment(18));
//        seqButton(Move.A); // choose own name
//        seq(new NamingSegment("A"));
//        seq(new SkipTextsSegment(7));
//        seq(new TextSegment()); // seeing you soon text
//      }
//    });
//		save("oakSpeech");
//
//		load("oakSpeech");
//		seq(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seq(new WalkToSegment(7, 0, false)); // downstairs
//
//        seq(new SkipTextsSegment(7));
//        seq(new TextSegment()); // pokegear
//        seq(new SkipTextsSegment(5));
//        seq(new SkipTextsSegment(2, true)); // sunday
//        seq(new SkipTextsSegment(1)); // no dst
//        seq(new SkipTextsSegment(1, true)); // confirm
//        seq(new SkipTextsSegment(3));
//        seq(new SkipTextsSegment(1, true)); // know phone
//        seq(new SkipTextsSegment(5));
//
//        seq(new WalkToSegment(7, 6)); // leave house
//        seq(new WalkToSegment(7, 8, false)); // leave house
//        seqSkipInput(2);
//        seq(new WalkToSegment(13, 5, false)); // re-enter house
//        seqSkipInput(1);
//        seq(new WalkToSegment(9, 0, false)); // go upstaris
//        
//        // save and quit
//        seqSkipInput(3);
//        seqButtonNoDelay(Move.START);
//        seqButton(Move.UP);
//        seqSkipInput(1);
//        seqButton(Move.UP);
//        seqSkipInput(1);
//        seqButton(Move.UP);
//        seqButton(Move.A); // save
//        seq(new SkipTextsSegment(1, true)); // save
//        seqSkipInput(150);
//        seqButtonNoDelay(Move.A | Move.B | Move.SELECT | Move.START); // reset
//        seqSkipInput(1050); // let intro curscene play for a bit
//        seqButton(Move.A);    // skip intro cutscene
//        seqSkipInput(200); // let title screen play for a bit
//        seqButton(Move.START);  // skip title screen
//        seqButton(Move.A);   // continue
//        seqSkipInput(1);
//        seqButton(Move.A);   // continue
//      }
//    });
//    save("playback_s02");
    load("playback_s02");
	}

	public static void main(String[] args) {
    SingleGbRunner.run(new GoldRomInfo(), new S02GoldIntro(), false);
	}
}
