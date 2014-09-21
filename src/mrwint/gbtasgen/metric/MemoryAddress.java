package mrwint.gbtasgen.metric;

import mrwint.gbtasgen.Gb;

public class MemoryAddress implements Metric {
	
	private int address;
	
	public MemoryAddress(int address) {
		this.address = address;
	}

	@Override
	public int getMetric() {
		return Gb.readMemory(address);
	}

}
