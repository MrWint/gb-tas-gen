package mrwint.gbtasgen.move;


public abstract class DelayableCachableMove extends DelayableMove {

	@Override
	public boolean isCachable() {
		return true;
	}
}
