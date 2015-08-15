package mrwint.gbtasgen.segment.pokemon.gen2.any;

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
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class ToAzalea1Segment extends SeqSegment {

	public void execute() {
		seq(new WalkToSegment(4, 5));			// align
		seq(new WalkToSegment(5, 16, false));	// leave arena

		seq(new SkipTextsSegment(5));			// elm phone
		for(int i=0;i<4;i++)
			seq(new TextSegment());			// click, ..., ..., ...

		seq(new WalkToSegment(31, 25, false));	// enter center
//		{
//			seq(new WalkToSegment(3, 3));			// align
//			seq(new MoveSegment(new PressButton(Move.RIGHT)));
//			seq(new MoveSegment(new PressButton(Move.RIGHT)));
//			seq(new MoveSegment(new OverworldInteract()));	// talk to assistant
//			seq(new SkipTextsSegment(3));
//			seq(new SkipTextsSegment(1,true)); // take egg
//			seq(new TextSegment()); 			// received egg
//			seq(new SkipTextsSegment(8));
//		}

		seq(new WalkToSegment(9, 3));			// align
		seq(new WalkToSegment(9, 2));			// face PC
		seq(new MoveSegment(new OverworldInteract()));
		seq(new SkipTextsSegment(1)); // accessed pc
		seq(new MoveSegment(new PressButton(Move.A)));
		seq(new SkipTextsSegment(2)); // bills pc
		seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));
		seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));
		seq(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // change box
		{
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 2
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 3
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 4
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 5
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 6
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 7
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 8
			seq(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // box 8
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // name
			seq(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // name
			seq(new NamingSegment("ªe4g9µª2", true));
		}
		{
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 2
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 3
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 4
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 5
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 6
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 7
			seq(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // box 7
			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // name
			seq(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // name
			seq(new NamingSegment("k€72kªł*", true));
		}
//		{
//			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 2
//			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 3
//			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 4
//			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 5
//			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 6
//			seq(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // box 6
//			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // name
//			seq(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // name
//			seq(new NamingSegment("T€22T€72", true));
//		}
//		{
//			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 2
//			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 3
//			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 4
//			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // box 5
//			seq(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // box 5
//			seq(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // name
//			seq(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // name
//			seq(new NamingSegment("„O€12Diª", true));
//		}
		seq(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY))); // back
		seq(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY))); // back
		seq(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY))); // back

		{
			seq(new WalkToSegment(5, 3));			// align
			seq(new MoveSegment(new OverworldInteract()));	// talk to assistant
			seq(new SkipTextsSegment(3));

			seqUnbounded(new TextSegment(Move.B, true)); // 3rd text
			//seq(new MoveSegment(new SkipInput(0))); // move to next input frame
			seq(new DelayMoveSegment(new PressButtonFactory(Move.A), new Segment() {
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
			seq(new TextSegment()); 			// received egg
			seq(new SkipTextsSegment(8));
		}

		seq(new WalkToSegment(4, 6));					// align
		seq(new WalkToSegment(4, 8, false));			// exit center

		seq(new WalkToSegment(14, 36));				// violet -> route32

		if(!PokemonUtil.isCrystal()) {
			seq(new WalkToSegment(15, 23));				// align
			seq(new WalkToSegment(14, 23));				// face youngster albert
			seq(new MoveSegment(new OverworldInteract()));	// engage youngster albert
		}
	}
}
