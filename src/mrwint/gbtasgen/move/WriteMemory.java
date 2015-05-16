package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.state.Gameboy.curGb;

public class WriteMemory extends Move {

	private int address;
	private int value;

	public WriteMemory(int address, int value) {
		this.address = address;
		this.value = value;
	}

	@Override
	public boolean doMove() {
	  curGb.writeMemory(address, value);
		return true;
	}

	@Override
	public int getInitialKey() {
		return 0;
	}
}
