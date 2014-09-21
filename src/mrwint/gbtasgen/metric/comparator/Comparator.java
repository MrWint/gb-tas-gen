package mrwint.gbtasgen.metric.comparator;

public interface Comparator {
	boolean compare(int base, int alt);
	
	static Comparator EQUAL = (base, alt) -> {return base == alt;};
	static Comparator GREATER_EQUAL = (base, alt) -> {return base >= alt;};
	static Comparator LESSER_EQUAL = (base, alt) -> {return base <= alt;};
	static Comparator POSITIVE = (base, alt) -> {return base > 0;};
	static Comparator UNEQUAL = not(EQUAL);
	static Comparator LESSER = not(GREATER_EQUAL);
	static Comparator GREATER = not(LESSER_EQUAL);

	static Comparator not(Comparator comparator) {
		return (base, alt) -> {return !comparator.compare(base, alt);};
	}
	
	static Comparator and(Comparator c1, Comparator c2) {
		return (base, alt) -> {return c1.compare(base, alt) && c2.compare(base, alt);};
	}
}
