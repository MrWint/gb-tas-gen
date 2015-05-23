package mrwint.gbtasgen.segment.pokemon.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.gen2.Gen2CheckDVMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.pokemon.gen2.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen2.common.NamingSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class ToAzalea1Segment implements Segment {

	SequenceSegment sequence;

	public ToAzalea1Segment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new WalkToSegment(4, 5));			// align
		segments.add(new WalkToSegment(5, 16, false));	// leave arena

		segments.add(new SkipTextsSegment(5));			// elm phone
		for(int i=0;i<4;i++)
			segments.add(new TextSegment());			// click, ..., ..., ...

		segments.add(new WalkToSegment(31, 25, false));	// enter center
//		{
//			segments.add(new WalkToSegment(3, 3));			// align
//			segments.add(new MoveSegment(new PressButton(Move.RIGHT)));
//			segments.add(new MoveSegment(new PressButton(Move.RIGHT)));
//			segments.add(new MoveSegment(new OverworldInteract()));	// talk to assistant
//			segments.add(new SkipTextsSegment(3));
//			segments.add(new SkipTextsSegment(1,true)); // take egg
//			segments.add(new TextSegment()); 			// received egg
//			segments.add(new SkipTextsSegment(8));
//		}

		segments.add(new WalkToSegment(9, 3));			// align
		segments.add(new WalkToSegment(9, 2));			// face PC
		segments.add(new MoveSegment(new OverworldInteract()));
		segments.add(new SkipTextsSegment(1)); // accessed pc
		segments.add(new MoveSegment(new PressButton(Move.A)));
		segments.add(new SkipTextsSegment(2)); // bills pc
		segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));
		segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));
		segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // change box
		{
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 2
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 3
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 4
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 5
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 6
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 7
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 8
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // box 8
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // name
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // name
			segments.add(new NamingSegment("ªe4g9µª2", true));
		}
		{
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 2
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 3
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 4
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 5
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 6
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 7
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // box 7
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // name
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // name
			segments.add(new NamingSegment("k€72kªł*", true));
		}
//		{
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 2
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 3
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 4
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 5
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 6
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // box 6
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // name
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // name
//			segments.add(new NamingSegment("T€22T€72", true));
//		}
//		{
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 2
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 3
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 4
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 5
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // box 5
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // name
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // name
//			segments.add(new NamingSegment("„O€12Diª", true));
//		}
		segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY))); // back
		segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY))); // back
		segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY))); // back

		{
			segments.add(new WalkToSegment(5, 3));			// align
			segments.add(new MoveSegment(new OverworldInteract()));	// talk to assistant
			segments.add(new SkipTextsSegment(3));

			segments.add(new TextSegment(Move.B, true, 0)); // 3rd text
			//segments.add(new MoveSegment(new SkipInput(0))); // move to next input frame
			segments.add(new DelayMoveSegment(new PressButtonFactory(Move.A), new Segment() {
				@Override
				public StateBuffer execute(StateBuffer in) {
					in = new CheckMetricSegment(new Gen2CheckDVMetric(0/*Move.B*/, 0, 0, 0, 0,
							0x1891, 0x1892, 0x1893, 0x1894, 0x1895, 0x1896, 0x1897, 0x1898, 0x1899, 0x189A, 0x189B, 0x189C, 0x189D, 0x189E, 0x189F,
							0x18A0, 0x18A1, 0x18A2, 0x18A3, 0x18A4, 0x18A5, 0x18A6, 0x18A7, 0x18A8, 0x18A9, 0x18AA, 0x18BB, 0x18AC, 0x18AD, 0x18AE, 0x18AF,
							0x18B0, 0x18B1, 0x18B2, 0x18B3, 0x18B4, 0x18B5, 0x18B6, 0x18B7, 0x18B8, 0x18B9, 0x18BA, 0x18BB,
							0x2091, 0x2092, 0x2093, 0x2094, 0x2095, 0x2096, 0x2097, 0x2098, 0x2099, 0x209A, 0x209B, 0x209C, 0x209D, 0x209E, 0x209F,
							0x20A0, 0x20A1, 0x20A2, 0x20A3, 0x20A4, 0x20A5, 0x20A6, 0x20A7, 0x20A8, 0x20A9, 0x20AA, 0x20BB, 0x20AC, 0x20AD, 0x20AE, 0x20AF,
							0x20B0, 0x20B1, 0x20B2, 0x20B3, 0x20B4, 0x20B5, 0x20B6, 0x20B7, 0x20B8, 0x20B9, 0x20BA, 0x20BB,
							0x3891, 0x3892, 0x3893, 0x3894, 0x3895, 0x3896, 0x3897, 0x3898, 0x3899, 0x389A, 0x389B, 0x389C, 0x389D, 0x389E, 0x389F,
							0x38A0, 0x38A1, 0x38A2, 0x38A3, 0x38A4, 0x38A5, 0x38A6, 0x38A7, 0x38A8, 0x38A9, 0x38AA, 0x38BB, 0x38AC, 0x38AD, 0x38AE, 0x38AF,
							0x38B0, 0x38B1, 0x38B2, 0x38B3, 0x38B4, 0x38B5, 0x38B6, 0x38B7, 0x38B8, 0x38B9, 0x38BA, 0x38BB
					)).execute(in);
					return in;
				}
			}, 5, 10));
			segments.add(new TextSegment()); 			// received egg
			segments.add(new SkipTextsSegment(8));
		}

		segments.add(new WalkToSegment(4, 6));					// align
		segments.add(new WalkToSegment(4, 8, false));			// exit center

		segments.add(new WalkToSegment(14, 36));				// violet -> route32

		if(!PokemonUtil.isCrystal()) {
			segments.add(new WalkToSegment(15, 23));				// align
			segments.add(new WalkToSegment(14, 23));				// face youngster albert
			segments.add(new MoveSegment(new OverworldInteract()));	// engage youngster albert
		}

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
