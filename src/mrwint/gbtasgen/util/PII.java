package mrwint.gbtasgen.util;

public class PII {
	public int a,b;
	public PII(int a, int b) {
		this.a = a;
		this.b = b;
	}
	@Override
	public String toString() {
		return "["+a+","+b+"]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PII other = (PII) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		return true;
	}
}
