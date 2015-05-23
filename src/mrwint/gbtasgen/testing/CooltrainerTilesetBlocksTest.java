package mrwint.gbtasgen.testing;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.rom.pokemon.gen1.RedRomInfo;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.util.Util;

public class CooltrainerTilesetBlocksTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

    Gb.loadGambatte(1);

		// select ROM to use
    curGb = new Gameboy(new RedRomInfo(), 0, false);

		Map<Integer, Set<String>> m = new TreeMap<Integer, Set<String>>();

		int ptr = 0xc7be;
		int[] rom = curGb.getROM();
		Map<Integer, Set<String>> sentinels = new TreeMap<Integer, Set<String>>();

		Set<Integer> knownAddresses = new HashSet<Integer>();
		for (int n=0;n<24;n++) {
			Map<Integer, Set<String>> tmp = new TreeMap<Integer, Set<String>>();
			int add = Util.getRomWordLE(ptr+(12*n)+1);
			add += (rom[ptr+(12*n)]-1)*0x4000;
			if (knownAddresses.add(add)) {
				System.out.println(n+": "+Util.toHex(add));
				int endAddress = ((add/0x4000)+1)*0x4000;
				for (int i = 0; i < 0x100; i++) {
					if (endAddress <= add)
						break;
					add(rom[add+1], Util.toHex(n)+":"+i+":tl", tmp);
					add(rom[add+3], Util.toHex(n)+":"+i+":tr", tmp);
					add(rom[add+9], Util.toHex(n)+":"+i+":bl"+(rom[add+5]%2 == 0 ? "0" : "1"), tmp);
					add(rom[add+11], Util.toHex(n)+":"+i+":br"+(rom[add+7]%2 == 0 ? "0" : "1"), tmp);

					for(int j=0;j<16;j++) {
						if (rom[add+j] == 0x50) {
							add(n,Util.toHex(i,2)+":("+(j/4)+":"+(j%4)+")", sentinels);
						}
					}

					add+=16;
				}
			}

			if (!sentinels.containsKey(n))
				add(n,"(none)",sentinels);
			else
				for (Entry<Integer, Set<String>> e : tmp.entrySet())
					for (String pos : e.getValue())
						add(e.getKey(), pos, m);
		}
		for (Entry<Integer, Set<String>> e : m.entrySet()) {
			System.out.print(e.getKey()+":");
			for (String pos : e.getValue())
				System.out.print(" "+pos);
			System.out.println();
		}

		System.out.println();
		System.out.println("Impossible:");
		for(int i=0;i<0x100;i++) {
			if (!m.containsKey(i)) {
				System.out.println(i);
			}
		}

		System.out.println();
		System.out.println("50s:");
		for (Entry<Integer, Set<String>> e : sentinels.entrySet()) {
			System.out.print(Util.toHex(e.getKey())+":");
			for (String pos : e.getValue())
				System.out.print(" "+pos);
			System.out.println();
		}
	}

	public static void add(int v, String pos, Map<Integer, Set<String>> m) {
		if (!m.containsKey(v))
			m.put(v, new TreeSet<String>());
		m.get(v).add(pos);
	}
}
