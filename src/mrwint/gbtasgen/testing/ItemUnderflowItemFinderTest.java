package mrwint.gbtasgen.testing;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.move.WriteMemory;
import mrwint.gbtasgen.rom.pokemon.gen1.RedRomInfo;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Util;

public class ItemUnderflowItemFinderTest extends SeqSegment {

	public static final int MIN_MAP = 0;
	public static final int MAX_MAP = 247;
	public static final boolean ONLY_GOOD = false;

	public static final List<Integer> excludedMaps = Arrays.asList(11, 105, 106, 107, 109, 110, 111, 112, 114, 115, 116, 117, 204, 205, 206, 231, 237, 238, 241, 242, 243, 244);

	@Override
	protected void execute() {
		Map<Integer, Set<String>> m = new TreeMap<Integer, Set<String>>();

		seq(new WalkToSegment(19, 17));
		seq(Segment.skip(1));
		seqMove(new WriteMemory(0xd3b1, 0x00)); // set index to 0
		qsave();
		for (int map=MIN_MAP;map<=MAX_MAP;map++) {
			if(excludedMaps.contains(map))
				continue;
			qload();
			seqMove(new WriteMemory(0xd3b2, map)); // set map
			seq(new WalkToSegment(3,8, false));
			System.out.println("Map "+map);
			seq(Segment.skip(10));
			int[] memory = curGb.getCurrentMemory();
			for (int i=20;i<0x80;i++) {
				int add = 0xd31e+2*i;
				if (/*memory[add] == 0x48 || memory[add] == 0x49 || memory[add] == 0x4e || memory[add] == 0x29 ||memory[add] == 0xc5 || */memory[add] == 0x1f) {
					String desc = ""+Util.toHex(map,2)+"("+map+"):"+i+"("+Util.toHex(add,4)+")";
					add(memory[add], desc, m);
				}
			}
		}

		for (Entry<Integer, Set<String>> e : m.entrySet()) {
			System.out.print(e.getKey()+":");
			for (String pos : e.getValue())
				System.out.print(" | "+pos);
			System.out.println();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
    Gb.loadGambatte(1);

		// select ROM to use
    curGb = new Gameboy(new RedRomInfo(), 0);
//    curGb = new Gameboy(new BlueRomInfo());

		StateBuffer in = StateBuffer.load("test_1", "");
		new ItemUnderflowItemFinderTest().execute(in);
	}

	public static void add(int v, String pos, Map<Integer, Set<String>> m) {
		if (!m.containsKey(v))
			m.put(v, new TreeSet<String>());
		m.get(v).add(pos);
	}
}
