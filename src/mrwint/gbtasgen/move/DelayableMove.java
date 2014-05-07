package mrwint.gbtasgen.move;


public abstract class DelayableMove extends Move {

	@Override
	public boolean isDelayable() {
		return true;
	}
}
