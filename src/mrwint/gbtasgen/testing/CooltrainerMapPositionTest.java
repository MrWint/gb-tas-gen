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

public class CooltrainerMapPositionTest extends SeqSegment {

	public static final int MIN_MAP = 18;//0;
	public static final int MAX_MAP = 18;//247;
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
//			seq(new SleepSegment(200));
			int[][] mapTiles;
			int mapHeight;
			int mapWidth;
			{
				int[] rom = curGb.getROM();
				int[] memory = curGb.getCurrentMemory();
				int mapHeaderBank = rom[curGb.pokemon.mapHeaderBanksAddress+map];
				int curMapHeaderAddress = Util.getRomWordLE(curGb.pokemon.mapHeaderPointersAddress+2*map) + (mapHeaderBank-1)*0x4000;

				System.out.println("Map ID "+Integer.toHexString(map)+" header at "+Util.toHex(curMapHeaderAddress));

				mapHeight = rom[curMapHeaderAddress+1];
				mapWidth = rom[curMapHeaderAddress+2];

				System.out.println("Map dimensions "+mapWidth+"x"+mapHeight);

				int blockTilesAddress = Util.getMemoryWordLE(curGb.pokemon.blockTilesPointerAddress+1) + (memory[curGb.pokemon.blockTilesPointerAddress]-1)*0x4000;

				// build tile map
				mapTiles = new int[(mapWidth+6)*4][(mapHeight+6)*4];
				for(int blockX=0;blockX<mapWidth+6;blockX++) {
					for(int blockY=0;blockY<mapHeight+6;blockY++) {
						int block = memory[curGb.pokemon.curBlockDataAddress + blockY*(mapWidth+6) + blockX];
						for(int y=0;y<4;y++) {
							for(int x=0;x<4;x++) {
								mapTiles[blockX*4 + x][blockY*4 + y] = rom[blockTilesAddress + block*0x10 + y*4 + x];
							}
						}
					}
				}
			}
//			printMap(mapTiles);
			for(int dx = 0;dx < 4*(mapWidth+6)-20; dx+=2) {
				for(int dy = 0;dy < 4*(mapHeight+6)-18; dy+=2) {
					int tx = dx/2-2;
					int ty = dy/2-2;
					if (tx < 0 || ty < 0 || tx >= 2*mapWidth || ty >= 2*mapHeight)
						continue;
//					System.out.println("try "+dx+","+dy);
					int[][] tiles = new int[20][18];
					for(int x=0;x<20;x++)
						for(int y=0;y<18;y++)
							tiles[x][y] = mapTiles[x+dx][y+dy];
//					printMap(tiles);
					int first50 = get50(tiles);
					if (first50 >= 61) {
						int mon = tiles[1][6];
						int lvl = first50 >= 78 ? tiles[8][7] : -1;
						boolean goodSpriteLoad = tiles[1][5] % 2 == 0;
						String desc = ""+Util.toHex(map,2)+"("+tx+","+ty+") L"+lvl+(goodSpriteLoad?"G":"B");
//						if (mon == 88 || mon == 89)
//							if (lvl > 30)
								if (!ONLY_GOOD || goodSpriteLoad)
									add(mon, desc, m);
					}
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
    curGb = new Gameboy(new RedRomInfo(), 0, false);
//    curGb = new Gameboy(new BlueRomInfo());

		StateBuffer in = StateBuffer.load("test_1", "");
		new CooltrainerMapPositionTest().execute(in);
	}

	public static void add(int v, String pos, Map<Integer, Set<String>> m) {
		if (!m.containsKey(v))
			m.put(v, new TreeSet<String>());
		m.get(v).add(pos);
	}

	private static int get50(int[][] tiles) {
		for (int i=0; i<128; i++)
			if (tiles[i%10][i/10] == 0x50)
				return i;
		return -1;
	}

	private static void printMap(int[][] tiles) {
		for (int y=0;y<tiles[0].length;y++) {
			for (int x=0;x<tiles.length;x++) {
				System.out.print(" " + Util.toHex(tiles[x][y], 2));
			}
			System.out.println();
		}
	}
}
