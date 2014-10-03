package mrwint.gbtasgen.testing;

public class CalcTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int in = 0xeb;
		
		System.out.println("B: "+Integer.toString((in ^ 0x42) & 0xFF, 16));
		System.out.println("C: "+Integer.toString((in ^ 0x4A) & 0xFF, 16));
		System.out.println("H: "+Integer.toString((in ^ 0x62) & 0xFF, 16));
		System.out.println("L: "+Integer.toString((in ^ 0x6A) & 0xFF, 16));
		System.out.println("(hl): "+Integer.toString((in ^ 0x72) & 0xFF, 16));
		System.out.println("A: "+Integer.toString((in ^ 0x7A) & 0xFF, 16));
	}

}
