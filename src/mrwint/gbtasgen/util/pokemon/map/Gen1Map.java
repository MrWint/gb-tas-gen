package mrwint.gbtasgen.util.pokemon.map;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class Gen1Map extends Map {
	public static class Factory implements MapFactory {
		@Override
		public Map create(boolean blockAllWarps, boolean ignoreTrainers) {
			return new Gen1Map(blockAllWarps, ignoreTrainers);
		}
	}
	
	private boolean checkIsPassable(int x, int y) {
		int c = collisionAddress;
		while(memory[c] != 0xFF) {
			if(memory[c++] == mapSteps[x][y])
				return true;
		}
		return false;
	}
	
	private boolean isPassable(int x, int y) {
		return !collisionMap[x][y];
	}
	
	private boolean isPassableFromTo(int fromx,int fromy, int tox,int toy) {
		int tileFrom = mapSteps[fromx][fromy];
		int tileTo= mapSteps[tox][toy];
		int curTileSet = Gb.readMemory(RomInfo.pokemon.curTilesetAddress);
		for(int c = RomInfo.pokemon.tilePairCollisionsLandAddress; memory[c] != 0xFF; c+=3) {
			if(memory[c] != curTileSet)
				continue;
			if(memory[c+1] == tileFrom && memory[c+2] == tileTo)
				return false;
			if(memory[c+2] == tileFrom && memory[c+1] == tileTo)
				return false;
		}
		return true;
	}

	@Override
	public boolean isPassable(int fromx, int fromy, int tox, int toy, int dir) {
		return isPassable(tox,toy) && isPassableFromTo(fromx, fromy, tox, toy);
	}

	@Override
	public boolean isInversePassable(int fromx, int fromy, int tox, int toy, int dir) {
		return isPassable(fromx,fromy) && isPassableFromTo(fromx, fromy, tox, toy);
	}

	private int[][] mapTiles; // in tiles; including 3 border blocks in each direction
	private int[][] mapSteps; // in steps; including 3 border blocks in each direction
	private boolean[][] collisionMap;
	
	private int[] rom;
	private int[] memory;
	private int curMapID;

	private int mapHeaderBank;
	private int curMapHeaderAddress;
//	private int trainerHeaderPointers;
	private int mapHeight; // in blocks
	private int mapWidth;
	private int blockTilesAddress;
	private int grassTile;
	private int textDataPointer;
	private int collisionAddress;
	
	private boolean blockAllWarps;
	private boolean ignoreTrainers;
	
	public int getStepWidth() {
		return (mapWidth+6)*2;
	}
	public int getStepHeight() {
		return (mapHeight+6)*2;
	}
	
	public Gen1Map(boolean blockAllWarps, boolean ignoreTrainers) {
		this.rom = State.getROM();
		this.memory = State.getCurrentMemory();
		this.blockAllWarps = blockAllWarps;
		this.ignoreTrainers = ignoreTrainers;

		curMapID = memory[0xD35E];

		loadMap();
	}
	
	public Gen1Map(int curMapID) {
		this.rom = State.getROM();
		this.memory = State.getCurrentMemory();

		this.curMapID = curMapID;

		loadMap();
	}
	
	private void loadMap() {
		rom = State.getROM();
		memory = State.getCurrentMemory();

		mapHeaderBank = rom[RomInfo.pokemon.mapHeaderBanksAddress+curMapID];
		curMapHeaderAddress = Util.getRomWordLE(RomInfo.pokemon.mapHeaderPointersAddress+2*curMapID) + (mapHeaderBank-1)*0x4000;

//		System.out.println("Map ID "+Integer.toHexString(curMapID)+" header at "+Util.toHex(curMapHeaderAddress));

//		trainerHeaderPointers = Util.getMemoryBigEndian(RomInfo.rom.trainerHeaderPointerAddress);

//		System.out.println("Trainer header at "+Util.toHex(trainerHeaderPointers));

		mapHeight = rom[curMapHeaderAddress+1];
		mapWidth = rom[curMapHeaderAddress+2];

//		System.out.println("Map dimensions "+mapWidth+"x"+mapHeight);

		blockTilesAddress = Util.getMemoryWordLE(RomInfo.pokemon.blockTilesPointerAddress+1) + (memory[RomInfo.pokemon.blockTilesPointerAddress]-1)*0x4000;
		grassTile = memory[RomInfo.pokemon.grassTileAddress];
		
		// build tile map
		mapTiles = new int[(mapWidth+6)*4][(mapHeight+6)*4];
		for(int blockX=0;blockX<mapWidth+6;blockX++) {
			for(int blockY=0;blockY<mapHeight+6;blockY++) {
				int block = memory[RomInfo.pokemon.curBlockDataAddress + blockY*(mapWidth+6) + blockX];
				for(int y=0;y<4;y++) {
					for(int x=0;x<4;x++) {
						mapTiles[blockX*4 + x][blockY*4 + y] = rom[blockTilesAddress + block*0x10 + y*4 + x];
					}
				}
			}
		}
		
		// build step map
		mapSteps = new int[(mapWidth+6)*2][(mapHeight+6)*2];
		collisionMap = new boolean[(mapWidth+6)*2][(mapHeight+6)*2];
		collisionAddress = Util.getMemoryWordLE(RomInfo.pokemon.collisionDataAddress);
		for(int y=0;y<(mapHeight+6)*2;y++) {
			for(int x=0;x<(mapWidth+6)*2;x++) {
				mapSteps[x][y] = mapTiles[2*x][2*y+1];
				collisionMap[x][y] = !checkIsPassable(x,y);
			}
		}
		
		// parse map header
		int curAdd = curMapHeaderAddress + 5;
		
		textDataPointer = Util.getRomWordLE(curAdd) + (mapHeaderBank-1)*0x4000;
		curAdd += 4; // skip script pointer
		
		int cons = rom[curAdd++];
//		System.out.println("map connections: "+Integer.toHexString(cons));
		while(cons > 0) {
			if((cons & 1) != 0)
				curAdd += 11;
			cons/=2;
		}
		int objectDataPointer = Util.getRomWordLE(curAdd) + (mapHeaderBank-1)*0x4000;
//		System.out.println("map object data pointer: "+Integer.toHexString(objectDataPointer));
//		System.out.println("map text pointer: "+Integer.toHexString(textDataPointer));
		objectDataPointer++; // skip border tile
		int numWarps = rom[objectDataPointer++];
		for(int i=0;i<numWarps;i++) {
			int wy = rom[objectDataPointer++];
			int wx = rom[objectDataPointer++];
			objectDataPointer++; // destPoint
			objectDataPointer++; // destMap
			if(blockAllWarps)
				collisionMap[wx+6][wy+6] = true;
		}
		int numSigns = rom[objectDataPointer++];
		objectDataPointer += 3*numSigns;
		int numSprites = rom[objectDataPointer++];
//		System.out.println("map contains "+numWarps+" warps, "+numSigns+" signs and "+numSprites+" sprites");
		int numMovingSprites = 0;
		for(int i=0;i<numSprites;i++) {
			objectDataPointer++; // skip sprite ID
			int y = rom[objectDataPointer++] - 4 + 6;
			int x = rom[objectDataPointer++] - 4 + 6;
			int mov1 = rom[objectDataPointer++];
			int mov2 = rom[objectDataPointer++];
			int textID = rom[objectDataPointer++];
			//System.out.println("sprite "+i+": ("+x+","+y+") "+mov1+" "+mov2+" "+textID);
			
			if(mov1 == 0xFF) {
				if(!isSpriteHidden(i+1))
					collisionMap[x][y] = true; // stationary sprite blocks step
//				else
//					System.out.println("sprite "+(i+1)+" is hidden");
			} else
				numMovingSprites++;
			
			if((textID & 0x40) != 0) { // trainer
				objectDataPointer+=2;
				
				if(ignoreTrainers || isSpriteHidden(i+1))
					continue;
				
				textID &= 0x3f;
				int textAdd = rom[textDataPointer+2*(textID-1)] + (rom[textDataPointer+2*(textID-1)+1] << 8) + (mapHeaderBank-1)*0x4000;
//				System.out.println("trainer text pointer: "+Integer.toHexString(textAdd));
				if(rom[textAdd] != 0x08
						|| rom[textAdd+1] != 0x21
						|| rom[textAdd+4] != 0xCD
						|| rom[textAdd+5] != 0xCC
						|| rom[textAdd+6] != 0x31
						|| rom[textAdd+7] != 0xC3
						|| rom[textAdd+8] != 0xD7
						|| rom[textAdd+9] != 0x24) {
					System.out.println("ignoring irregular trainer text!");
				} else {
					int headerPtr = Util.getRomWordLE(textAdd+2) + (mapHeaderBank-1)*0x4000;
//					System.out.println("trainer header pointer: "+Integer.toHexString(headerPtr));
					int flagBit = rom[headerPtr];
					int engageDist = rom[headerPtr+1];
					int flagByte = Util.getRomWordLE(headerPtr+2);
					System.out.println("trainer flag: "+Integer.toHexString(flagByte)+":"+flagBit);
					flagByte += flagBit/8;
					flagBit %= 8;
					boolean alreadFought = (Gb.readMemory(flagByte) & (1<<flagBit)) != 0;
					if(alreadFought)
						System.out.println("trainer already fought!");
					else {
						System.out.println("trainer engage distance: "+engageDist);
						engageDist /= 0x10;
						if(mov1 == 0xFF) {
							switch(mov2) {
							case 0xD0: // down
								while(engageDist --> 0)
									collisionMap[x][++y] = true;
								break;
							case 0xD1: // up
								while(engageDist --> 0)
									collisionMap[x][--y] = true;
								break;
							case 0xD2: // left
								while(engageDist --> 0)
									collisionMap[--x][y] = true;
								break;
							case 0xD3: // right
								while(engageDist --> 0)
									collisionMap[++x][y] = true;
								break;
							default:
								System.out.println("unknown facing direction "+mov2);
								break;
							}
						}
					}
				}
			} else if((textID & 0x80) != 0) { // item
				objectDataPointer+=1;
			}
		}
//		if(numMovingSprites > 0)
//			System.out.println("map contains "+numMovingSprites+" moving sprites");
	}
	
	private static boolean isSpriteHidden(int spriteIndex) {
		int[] memory = State.getCurrentMemory();
		int curAdd = RomInfo.pokemon.missableObjectListAddress; // W_MISSABLEOBJECTLIST
		int bitIndex = -1;
		while(memory[curAdd] != 0xFF) {
			if(memory[curAdd] == spriteIndex)
				bitIndex = memory[curAdd+1];
			curAdd+=2;
		}
		if(bitIndex == -1)
			return false;
		
		curAdd = RomInfo.pokemon.missableObjectFlagsAddress + (bitIndex/8); // W_MISSABLEOBJECTFLAGS
		return (memory[curAdd] & (1 << (bitIndex%8))) != 0;
	}

	public static void printDistMap(int[][] dist) {
		for(int y=0;y<dist[0].length;y++) {
			for(int x=0;x<dist.length;x++)
				System.out.print("#0123456789ABCDEF".charAt(Math.min(dist[x][y]+1,16)));
			System.out.println();
		}
		System.out.println();
	}

	public void printMap() {
		for(int y=0;y<mapSteps[0].length;y++) {
			for(int x=0;x<mapSteps.length;x++)
				System.out.print("#.~ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(collisionMap[x][y]?0:(mapSteps[x][y]==grassTile?2:1)));
			System.out.println();
		}
		System.out.println();
	}
}
