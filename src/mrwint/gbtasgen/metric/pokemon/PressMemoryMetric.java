package mrwint.gbtasgen.metric.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;

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
	  curGb.step(move);
	  return curGb.readMemory(address) == goalValue ? 1 : 0;
	}
}
