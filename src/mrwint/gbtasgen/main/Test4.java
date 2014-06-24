package mrwint.gbtasgen.main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class Test4 {

	public static final int MIN_MAP = 0;
	public static final int MAX_MAP = 0xff;
	public static final int MIN_LOC = 0;
	public static final int MAX_LOC = 12;//0x3f;
	public static final int MAX_RADIUS = 5;
	public static final boolean USE_JACK = false;
	public static final boolean GOOD_TILESET = true;
	public static final List<Integer> goodTilesets = Arrays.asList(0x00,0x03,0x0f,0x10,0x11,0x16,0x17);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// select ROM to use
		RomInfo.rom = new RomInfo.RedRomInfo();
//		RomInfo.rom = new RomInfo.BlueRomInfo();

		Gb.loadGambatte();
		State.init(RomInfo.rom.romFileName);
		
		int[] rom = State.getROM();
		
		int mapHeaderBanks = 0xc23d;
		int mapHeaderPointers = 0x01ae;
		int tilesetHeaders = 0xc7be;
		
		Map<Integer, Set<String>> m = new TreeMap<Integer, Set<String>>();

		for (int map = MIN_MAP; map <= MAX_MAP; map++) {
			int mapBankOffset = (rom[mapHeaderBanks + map]-1) * 0x4000;
			int mapHeader = mapBankOffset + Util.getWordAt(mapHeaderPointers + 2*map);
			System.out.println("map "+map+" header at "+Util.toHex(mapHeader));
			if (mapHeader >= rom.length)
				continue;
			int tileset = rom[mapHeader];
			if(GOOD_TILESET && !goodTilesets.contains(tileset))
				continue;
			int tilesetBankOffset = (rom[tilesetHeaders + tileset*12]-1) * 0x4000;
			int tilesetAdd = tilesetBankOffset + Util.getWordAt(tilesetHeaders + tileset*12 + 1);
			int tilesetColl = Util.getWordAt(tilesetHeaders + tileset*12 + 5);
			System.out.println("map "+map+" tileset blocks at "+Util.toHex(tilesetAdd));
			System.out.println("map "+map+" tileset collision at "+Util.toHex(tilesetColl));
			int height = rom[mapHeader+1];
			int width = rom[mapHeader+2];
			int blocks = Util.getWordAt(mapHeader+3);
			int conn = rom[mapHeader+9];
			int add = mapHeader+10;
			while(conn > 0) {
				if ((conn % 2) != 0) add += 11;
				conn /= 2;
			}
			add = mapBankOffset + Util.getWordAt(add);
			System.out.println("map "+map+" object header at "+Util.toHex(add));
			add++; // border tile
			add += rom[add]*4 + 1; // warps
			add += rom[add]*3 + 1; // signs
			int numpeople = rom[add++];
			for (int i=0;i<numpeople; i++) {
				if ((rom[add+5] & (1 << 7)) != 0) add += 7; // item
				else if ((rom[add+5] & (1 << 6)) != 0) add += 8; // trainer
				else add += 6; // person
			}
			add += MIN_LOC*4;
			for (int loc=MIN_LOC;loc<=MAX_LOC;loc++) {
				if (add / 0x4000 != mapHeader / 0x4000)
					break;
				int blockAdd = Util.getWordAt(add);
				oy = rom[add+2];
				ox = rom[add+3];
				add += 4;
				init(ox,oy, 1); // down
//				System.out.println("check "+map+":"+loc+" at ("+ox+","+oy+") add "+Util.toHex(add-4));
				while(!queue.isEmpty()) {
					int cx = queue.peek().x;
					int cy = queue.peek().y;
					int cd = queue.peek().d;
					String cWay = queue.poll().way;
//					System.out.println("visit ("+cx+","+cy+")");
					int cAdd = blockAdd + (cx/2 - ox/2) + (cy/2 - oy/2)*(width+6);
					int[][] tiles = getTiles(cx,cy,width,cAdd,tilesetAdd, tilesetBankOffset);
					{
						int first50 = get50(tiles);
						if (first50 >= 61) {
							int mon = tiles[1][6];
							int lvl = first50 >= 78 ? tiles[8][7] : -1;
							boolean goodSpriteLoad = tiles[1][5] % 2 == 0;
//							if (lvl < 50) continue;
//							printMap(tiles);
							String desc = "" + Util.toHex(map,2)+":"+Util.toHex(loc,2)+" L"+lvl+(goodSpriteLoad?"G":"B")+" at ("+ox+","+oy+") ["+cx+","+cy+","+cWay+"] pointer "+Util.toHex(blockAdd, 4)+" ["+Util.toHex(cAdd, 4)+"]";
							if (mon == 170)
//								if (lvl > 30)
									if(knownHit.add(new DfsItem(cx,cy, 0, "")))
										add(mon, desc, m);
//							System.out.println("found mon "+mon+"!");
						}
					}
					boolean fixedDir = tiles[8][9] >= 0x60;
					for(int d=0;d<4;d++) {
						if (fixedDir && d != cd)
							continue;
						if (checkIsPassable(tiles[8 + 2*dx[d]][9 + 2*dy[d]], tilesetColl))
							add(cx+dx[d], cy+dy[d], d, cWay+dw[d]);
					}
					if (USE_JACK || fixedDir) {
						for(int d=0;d<4;d++) {
							if (fixedDir && d != cd)
								continue;
							if (checkIsPassable(tiles[8 + 2*dx[d]][9 + 2*dy[d]], tilesetColl))
								for(int i=0;i<4;i++)
									add(cx+dx[i], cy+dy[i], d, cWay+dw[i]+"("+dw[d]+")");
						}
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
	public static int[] dx = {1,0,-1,0};
	public static int[] dy = {0,1,0,-1};
	public static String[] dw = {"R","D","L","U"};

	public static Set<DfsItem> knownHit = new HashSet<DfsItem>();

	public static int ox,oy;
	public static Set<DfsItem> known = new HashSet<DfsItem>();
	public static Queue<DfsItem> queue = new LinkedList<DfsItem>();
	public static void init(int x, int y, int d) {
		known.clear();
		queue.clear();
		knownHit.clear();
		add(x, y, d, "("+x+","+y+")");
	}
	public static void add(int x, int y, int d, String way) {
		DfsItem item = new DfsItem(x,y, d, way);
		if(known.contains(item) || x >= 256 || x<0 || y<0 || y>=256 || Math.abs(ox-x)>MAX_RADIUS || Math.abs(oy-y)>MAX_RADIUS)
			return;
		known.add(item);
		queue.add(item);
	}
	
	public static class DfsItem {
		public int x;
		public int y;
		public int d;
		public String way;
		public DfsItem(int x, int y, int d, String way) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.way = way;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + d;
			result = prime * result + x;
			result = prime * result + y;
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
			DfsItem other = (DfsItem) obj;
			if (d != other.d)
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
	
	private static boolean checkIsPassable(int tile, int collisionAddress) {
//		System.out.println("Checking tile "+tile);
		int[] rom = State.getROM();
		int c = collisionAddress;
		while(rom[c] != 0xFF) {
//			System.out.println(" tile "+rom[c]);
			if(rom[c++] == tile)
				return true;
		}
		return false;
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
		for (int y=0;y<18;y++) {
			for (int x=0;x<20;x++) {
				System.out.print(" " + Util.toHex(tiles[x][y], 2));
			}
			System.out.println();
		}
	}

	public static int[][] getTiles(int dx, int dy, int width, int blockAdd, int tilesetAdd, int tilesetBankOffset) {
		int[] rom = State.getROM();
		int[][] tmp = new int[24][20];
		int add = blockAdd;
		for (int y=0;y<5;y++) {
			for (int x=0;x<6;x++) {
				int block = getBlockVal(tilesetBankOffset, rom, add);
				for (int yy=0;yy<4;yy++)
					for (int xx=0;xx<4;xx++)
						tmp[x*4 + xx][y*4 + yy] = getTileVal(tilesetAdd, rom, 16*block + 4*yy + xx);
				add++;
			}
			add += (width + 6) - 6;
		}
		int[][] ret = new int[20][18];
		for (int y=0;y<18;y++)
			for (int x=0;x<20;x++)
				ret[x][y] = tmp[x + 2*(dx&1)][y + 2*(dy&1)];
		return ret;
	}

	private static int getBlockVal(int tilesetBankOffset, int[] rom, int add) {
		if (add >= 0 && add < 0x4000)
			return rom[add];
		if (add >= 0 && add < 0x8000
				&& add + tilesetBankOffset < rom.length)
			return rom[add + tilesetBankOffset];
		return 0x10000; // huge
	}

	private static int getTileVal(int tilesetAdd, int[] rom, int off) {
		if ((tilesetAdd + off) / 0x4000 == tilesetAdd / 0x4000
				&& tilesetAdd + off < rom.length && tilesetAdd + off >= 0)
			return rom[tilesetAdd + off];
		return 0;
	}
}
