package mrwint.gbtasgen.metric.pokemon;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.state.State;

public class PressMemoryMetric implements StateResettingMetric {

  int move;
  int address;
  int goalValue;

	public PressMemoryMetric(int move, int address, int goalValue) {
    this.move = move;
    this.address = address;
    this.goalValue = goalValue;
	}

	@Override
	public int getMetricInternal() {
	  State.step(move);
	  return Gb.readMemory(address) == goalValue ? 1 : 0;
	}
}
