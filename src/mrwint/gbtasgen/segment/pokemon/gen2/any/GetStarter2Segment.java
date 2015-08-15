package mrwint.gbtasgen.segment.pokemon.gen2.any;

import mrwint.gbtasgen.metric.pokemon.gen2.Gen2CheckDVMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen2.common.NamingSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class GetStarter2Segment extends SeqSegment {

	public void execute() {
		seq(new SkipTextsSegment(2));
		seqUnbounded(new TextSegment(Move.A, true)); // 3rd text
		//seq(new MoveSegment(new SkipInput(0))); // move to next input frame
		seq(new DelayMoveSegment(new PressButtonFactory(Move.B), new Segment() {
			@Override
			public StateBuffer execute(StateBuffer in) {
//				in = new TextSegment(Move.A,true,0).execute(in);
//				in = new MoveSegment(new PressButton(Move.B), 2, 0).execute(in);
				in = new CheckMetricSegment(new Gen2CheckDVMetric(0/*Move.B*/, 14, 6, 8, 15)).execute(in);
//						0xded8, 0xdfd8, 0xe0d8, 0xe1d8, 0xe2d8, 0xe3d8,
//						0xdef8, 0xdff8, 0xe0f8, 0xe1f8, 0xe2f8, 0xe3f8)).execute(in);
				return in;
			}
		}, 5, 10));
		seq(new SkipTextsSegment(1));

		seq(new SkipTextsSegment(1, true)); // name it
		seq(new NamingSegment("I"));
		if(PokemonUtil.isCrystal())
			seq(new SkipTextsSegment(11));
		else
			seq(new SkipTextsSegment(9));

		seq(new WalkToSegment(4, 7));
		seq(new WalkToSegment(4, 8, false));
		seq(new SkipTextsSegment(7)); // talk to assistant
		seq(new WalkToSegment(4, 12, false));
	}
}
