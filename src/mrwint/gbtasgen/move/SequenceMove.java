package mrwint.gbtasgen.move;

public class SequenceMove extends Move {
	
	private Move[] moves;
	
	public SequenceMove(Move... moves) {
		this.moves = moves;
	}
	
	@Override
	public boolean isDelayable() {
		return moves.length > 0 && moves[moves.length-1].isDelayable();
	}
	@Override
	public boolean isCachable() {
		return moves.length > 0 && moves[moves.length-1].isCachable();
	}
	@Override
	public boolean isStateAltering() {
		for(Move move : moves)
			if(move.isStateAltering())
				return true;
		return false;
	}

	@Override
	public void clearCache() {
		if (moves.length > 0)
			moves[moves.length-1].clearCache();
	}

	@Override
	public void prepareInternal(int skips, boolean assumeOnSkip) {
		if(moves.length == 0)
			return;
		if(!assumeOnSkip) {
			for(int i=0;i<moves.length-1;i++)
				if(!moves[i].execute())
					throw new RuntimeException("execution of move "+i+" failed");
		}
		moves[moves.length-1].prepareInternal(skips, assumeOnSkip);
	}

	@Override
	public boolean doMove() {
		if(moves.length == 0)
			return true;
		return moves[moves.length-1].doMove();
	}

	@Override
	public int getInitialKey() {
		if(moves.length == 0)
			return 0;
		return moves[0].getInitialKey();
	}
}
