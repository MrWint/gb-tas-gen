package mrwint.gbtasgen.segment.playback;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckCatchMonMetric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.move.pokemon.gen2.OverworldInteract;
import mrwint.gbtasgen.rom.pokemon.gen2.CrystalRomInfo;
import mrwint.gbtasgen.segment.SingleGbSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen2.common.NamingSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.SingleGbRunner;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class S03CrystalGoldenrod extends SingleGbSegment {

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
//        seq(new SkipTextsSegment(1, true)); // pick boy
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
//        if(PokemonUtil.isCrystal()) {
//          seqSkipInput(1);
//          seq(new WalkToSegment(8, 3)); // align
//          seq(new WalkToSegment(8, 4, false)); // talk to mom
//        }
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
//        seq(new WalkToSegment(6, 3, false)); // enter elm's lab
//
//        if(PokemonUtil.isCrystal()) {
//          seq(new SkipTextsSegment(12));
//          seq(new SkipTextsSegment(1, true)); // agree help elm
//          seq(new SkipTextsSegment(26));
//        } else {
//          seq(new SkipTextsSegment(16));
//        }
//
//        seq(new WalkToSegment(7, 4)); // go to ball
//        seqButton(Move.UP); // face ball
//        seqButton(Move.UP); // face ball
//
//        seqMove(new OverworldInteract()); // grab totodile
//        seqButton(Move.B); // cancel totodile screen
//        seq(new SkipTextsSegment(2, true)); // yes, I want totodile
//        seq(new SkipTextsSegment(4)); // great choice, receive
//
//        seq(new SkipTextsSegment(1, false)); // name it? no
//        if(PokemonUtil.isCrystal())
//          seq(new SkipTextsSegment(11));
//        else
//          seq(new SkipTextsSegment(9));
//
//        seq(new WalkToSegment(4, 7));
//        seq(new WalkToSegment(4, 8, false));
//        seq(new SkipTextsSegment(7)); // talk to assistant
//        seq(new WalkToSegment(4, 12, false));
//      }
//    });
//    save("getStarter");
//
//    load("getStarter");
//    seq(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqUnbounded(new WalkToSegment(11, 13, false)); // go to Route 34
//        seqSkipInputUnbounded(1);
//        {
////          seq(new WalkToSegment(7, 10)); // align
//          seqUnbounded(new WalkToSegment(7, 9)); // align
//          seqUnbounded(new WalkToSegment(7, 8)); // step into grass
//          seq(new DelayMoveSegment(
//              new PressButtonFactory(Move.UP),
//              new CheckMetricSegment(new CheckEncounterMetric(251 /*celebi*/, null).withStartMove(Move.UP))));
//
//          seq(new SkipTextsSegment(2));
//          seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // pack
//          seq(new MoveSegment(new PressButton(Move.A))); // pack
//          seq(new MoveSegment(new SkipInput(3))); // balls
//          seq(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY))); // balls
//          seq(new MoveSegment(new SkipInput(2))); // use
//          seq(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // use
//          seq(new DelayMoveSegment(new PressButtonFactory(Move.A, Metric.PRESSED_JOY), new SeqSegment() {
//
//            @Override
//            public void execute() {
//              seqUnbounded(new TextSegment(Move.A, false));
//              seqMetric(new CheckCatchMonMetric());
//              seqWait(1);
//            }
//          }));
//          seq(new SkipTextsSegment(2));          // no nick
//        }
//        seq(new WalkToSegment(8, -1)); // go to Goldenrod
//        seqSkipInput(1);
//        seq(new WalkToSegment(15, 27, false)); // go to Center
//        seqSkipInput(2);
//        seqButton(Move.UP);
//        seq(new WalkToSegment(3, 3)); // go to GB kid
//        seq(new WalkToSegment(6, 2)); // go to GB kid
//        seqButtonNoDelay(Move.A); // talk to GB kid
//        seqSkipInput(78);
//        seqButtonNoDelay(Move.A); // text 1
//        seqSkipInput(42);
//        seqButtonNoDelay(Move.A); // text 2
//        seqSkipInput(70);
//        seqButtonNoDelay(Move.A); // text 3
//        seq(new WalkToSegment(4, 6)); // leave
//        seq(new WalkToSegment(4, 7, false)); // leave
//        seqButton(Move.DOWN);
//        seqButton(Move.DOWN);
//      }
//    });
//    save("playback_s03");
    load("playback_s03");
	}

	public static void main(String[] args) {
    SingleGbRunner.run(new CrystalRomInfo() {{ romFileName = "roms/crystal_hack.gbc"; }}, new S03CrystalGoldenrod(), false);
	}
}
