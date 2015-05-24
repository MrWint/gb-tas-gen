package mrwint.gbtasgen.move.pokemon;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SequenceMove;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class EflChangeOptionsMove {
	public static SequenceMove get(boolean inMainMenu) {
    EflUtil.assertEfl();

    if(inMainMenu)
			return new SequenceMove(
				new EflPressButton(Move.DOWN,PressMetric.PRESSED),
				new EflPressButton(Move.A,PressMetric.PRESSED), // options

				new EflPressButton(Move.LEFT,PressMetric.PRESSED), // text speed
				new EflPressButton(Move.DOWN,PressMetric.PRESSED),
				new EflPressButton(Move.LEFT,PressMetric.PRESSED), // battle scene
				new EflPressButton(Move.DOWN,PressMetric.PRESSED),
				new EflPressButton(Move.LEFT,PressMetric.PRESSED), // battle style

				new EflPressButton(Move.B,PressMetric.PRESSED)
				);
		else
			return new SequenceMove(
				new EflPressButton(Move.START),
				new EflPressButton(Move.UP,PressMetric.PRESSED),
				new EflPressButton(Move.UP,PressMetric.PRESSED),
				new EflPressButton(Move.A), // options

				new EflPressButton(Move.LEFT), // text speed
				new EflPressButton(Move.DOWN),
				new EflPressButton(Move.LEFT), // battle scene
				new EflPressButton(Move.DOWN),
				new EflPressButton(Move.LEFT), // battle style

				new EflPressButton(Move.B),
				new EflPressButton(Move.START));
	}
}
