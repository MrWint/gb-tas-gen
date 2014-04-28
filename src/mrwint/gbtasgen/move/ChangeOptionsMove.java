package mrwint.gbtasgen.move;

import mrwint.gbtasgen.metric.Metric;

public class ChangeOptionsMove {
	public static SequenceMove get(boolean inMainMenu) {
		if(inMainMenu)
			return new SequenceMove(
				new PressButton(Move.DOWN,Metric.PRESSED_JOY),
				new PressButton(Move.A,Metric.PRESSED_JOY), // options

				new PressButton(Move.LEFT,Metric.PRESSED_JOY), // text speed
				new PressButton(Move.DOWN,Metric.PRESSED_JOY),
				new PressButton(Move.LEFT,Metric.PRESSED_JOY), // battle scene
				new PressButton(Move.DOWN,Metric.PRESSED_JOY),
				new PressButton(Move.LEFT,Metric.PRESSED_JOY), // battle style

				new PressButton(Move.B,Metric.PRESSED_JOY)
				);
		else
			return new SequenceMove(
				new PressButton(Move.START),
				new PressButton(Move.UP,Metric.PRESSED_JOY),
				new PressButton(Move.UP,Metric.PRESSED_JOY),
				new PressButton(Move.A), // options
	
				new PressButton(Move.LEFT), // text speed
				new PressButton(Move.DOWN),
				new PressButton(Move.LEFT), // battle scene
				new PressButton(Move.DOWN),
				new PressButton(Move.LEFT), // battle style
	
				new PressButton(Move.B),
				new PressButton(Move.START));
	}
}
