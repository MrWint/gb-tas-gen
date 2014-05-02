package mrwint.gbtasgen.move;

import mrwint.gbtasgen.Gb;

public class WriteMemory extends Move {

	private int address;
	private int value;
	
	public WriteMemory(int address, int value) {
		this.address = address;
		this.value = value;
	}

	@Override
	public int execute() throws Throwable {
		Gb.writeMemory(address, value);
		return 0;
	}
	
	@Override
	public int getInitialKey() {
		return 0;
	}
}
