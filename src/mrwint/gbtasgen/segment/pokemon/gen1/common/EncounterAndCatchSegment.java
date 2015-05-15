package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.move.pokemon.gen1.WalkStep;
import mrwint.gbtasgen.segment.pokemon.CatchMonSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class EncounterAndCatchSegment extends SeqSegment {
	
	public static int defaultBallIndex = 0;

	int move, ballIndex;
	String nick;
	int withCoolTrainerMon;
	int extraBPresses, extraBPresses2;
	int extraSkips = 0;
	
	CheckEncounterMetric metric;
	
	public EncounterAndCatchSegment(CheckEncounterMetric metric, int move) {
		this(metric, move, 0);
	}
	public EncounterAndCatchSegment(CheckEncounterMetric metric, int move, int withCoolTrainerMon) {
		this.metric = metric;
		this.move = move;
		this.withCoolTrainerMon = withCoolTrainerMon;
		this.ballIndex = defaultBallIndex;
	}
	public EncounterAndCatchSegment(int mon, int move) {
		this(mon, move, null, false);
	}
	public EncounterAndCatchSegment(int mon, int move, boolean withCoolTrainer) {
		this(mon, move, null, withCoolTrainer, 0, 0);
	}
	public EncounterAndCatchSegment(int mon, int move, boolean withCoolTrainer, int extraBPresses, int extraBPresses2) {
		this(mon, move, null, withCoolTrainer, extraBPresses, extraBPresses2);
	}
	public EncounterAndCatchSegment(int mon, int move, String nick) {
		this(mon, move, nick, false);
	}
	public EncounterAndCatchSegment(int mon, int move, String nick, boolean withCoolTrainer) {
		this(mon, move, nick, withCoolTrainer, 0, 0);
	}
	public EncounterAndCatchSegment(int mon, int move, String nick, boolean withCoolTrainer, int extraBPresses, int extraBPresses2) {
		this.metric = new CheckEncounterMetric(withCoolTrainer ? 0 : mon);
		this.move = move;
		this.ballIndex = defaultBallIndex;
		this.extraBPresses = extraBPresses;
		this.extraBPresses2 = extraBPresses2;
		this.nick = nick;
		this.withCoolTrainerMon = withCoolTrainer ? mon : 0;
	}
	
	public EncounterAndCatchSegment withExtraSkips(int extraSkips) {
		this.extraSkips = extraSkips;
		return this;
	}
	
	@Override
	public void execute() {
		delay(new SeqSegment() {
			@Override
			protected void execute() {
				if(move != 0)
					seqMove(new WalkStep(move, false));
				seqMetric(metric);
			}
		});
		seq(new CatchMonSegment(ballIndex, withCoolTrainerMon, extraBPresses, extraBPresses2).withExtraSkips(extraSkips));
	}
}
