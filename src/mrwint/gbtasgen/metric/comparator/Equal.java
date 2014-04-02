package mrwint.gbtasgen.metric.comparator;

public class Equal implements Comparator {

	@Override
	public boolean compare(int base, int alt) {
		return base == alt;
	}

}
