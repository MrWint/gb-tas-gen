package mrwint.gbtasgen.metric.comparator;

public class PositiveLesserEqual implements Comparator {

	@Override
	public boolean compare(int base, int alt) {
		return (base <= alt) && (base >= 1);
	}

}
