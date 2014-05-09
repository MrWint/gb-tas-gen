package mrwint.gbtasgen.move;



public class PrepMove extends Move {
	
	Move move = null;
	public PrepMove(Move move) {
		this.move = move;
	}
	@Override
	public boolean doMove() {
		return true;
	}
	@Override
	public int getInitialKey() {
		return move.getInitialKey();
	}
	@Override
	public void clearCache() {
		move.clearCache();
	}
	@Override
	public boolean isDelayable() {
		return move.isDelayable();
	}
	@Override
	public boolean isCachable() {
		return move.isCachable();
	}
	@Override
	public boolean isStateAltering() {
		return move.isStateAltering();
	}
	@Override
	public void prepareInternal(int skips, boolean assumeOnSkip) {
		move.prepareInternal(skips, assumeOnSkip);
	}
}
