package mrwint.gbtasgen.metric.comparator;

public class GreaterEqual implements Comparator {

	@Override
	public boolean compare(int base, int alt) {
		return base >= alt;
	}

}
