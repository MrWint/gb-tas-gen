package mrwint.gbtasgen.util.map;

import java.util.Arrays;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class Gen2Map extends Map {
	public static class Factory implements MapFactory {
		@Override
		public Map create(boolean blockAllWarps, boolean ignoreTrainers) {
			return new Gen2Map();
		}
	}
	public boolean isPassable(int x, int y) {
		if(mapStepCollisionOverride[x][y])
			return false;
		if((rom[RomInfo.rom.tileCollisionTableAddress + mapStepCollision[x][y]] & 0xF) == 0)
			return true;
		return false;
	}
	
	public Integer[][] forbiddenEntryDir = {
			{0xb2,0xb6,0xb7,0xc2,0xc6,0xc7}, // down
			{0xb1,0xb5,0xb7,0xc1,0xc5,0xc7}, // right
			{0xb3,0xb4,0xb5,0xc3,0xc4,0xc5}, // up
			{0xb0,0xb4,0xb6,0xc0,0xc4,0xc6}, // left
	};

	public boolean canGoDir(int tox, int toy, int dir) {
		return !Arrays.asList(forbiddenEntryDir[dir]).contains(mapStepCollision[tox][toy]);
	}

	@Override
	public boolean isPassable(int fromx, int fromy, int tox, int toy, int dir) {
		return isPassable(tox,toy) && canGoDir(tox,toy,dir);
	}

	@Override
	public boolean isInversePassable(int fromx, int fromy, int tox, int toy, int dir) {
		return isPassable(fromx,fromy) && canGoDir(tox,toy,dir);
	}
	
	private int mapHeaderBank;
	
	private int[][] mapBlocks; // in blocks; including 3 border blocks in each direction
	private int[][] mapStepCollision; // in steps; including 3 border blocks in each direction
	private boolean[][] mapStepCollisionOverride; // in steps; including 3 border blocks in each direction
	
	private int[] rom;
	private int[] memory;

	private int curMapGroup;
	private int curMapID;

	private int curMapHeaderAddress;
	private int curMapSecondHeaderAddress;

	//private int borderBlock;
	private int mapHeight; // in blocks
	private int mapWidth;
	//private int blockDataAddress;
	
	private int curMapTileset;
	private int tileSetCollisionDataAddress;

	public int getStepWidth() {
		return (mapWidth+6)*2;
	}
	public int getStepHeight() {
		return (mapHeight+6)*2;
	}
	
	public Gen2Map() {
		this.rom = State.getROM();
		this.memory = State.getCurrentMemory();
		
		this.curMapGroup = memory[RomInfo.rom.curMapGroupAddress];
		this.curMapID = memory[RomInfo.rom.curMapIDAddress];

		loadMap();
	}
	
	public Gen2Map(int curMapGroup, int curMapID) {
		this.rom = State.getROM();
		this.memory = State.getCurrentMemory();
		
		this.curMapGroup = curMapGroup;
		this.curMapID = curMapID;

		loadMap();
	}
	
	private void loadMap() {
		System.out.println("Loading map "+Util.toHex(curMapGroup)+":"+Util.toHex(curMapID)+"...");

		mapHeaderBank = RomInfo.rom.mapGroupPointersAddress / 0x4000;
		curMapHeaderAddress = Util.getWordAt(RomInfo.rom.mapGroupPointersAddress+2*(curMapGroup-1)) +
				(curMapID-1) * 9 + // each header is 9 bytes long; 1-indexed
				(mapHeaderBank-1)*0x4000; // adjust bank

		System.out.println("Map header at "+Util.toHex(curMapHeaderAddress));

		curMapSecondHeaderAddress = rom[curMapHeaderAddress+3] + (rom[curMapHeaderAddress+4]<<8) +
				(rom[curMapHeaderAddress]-1)*0x4000; // adjust bank

		System.out.println("Second Map header at "+Util.toHex(curMapSecondHeaderAddress));

		//borderBlock = rom[curMapSecondHeaderAddress];
		mapHeight = rom[curMapSecondHeaderAddress+1];
		mapWidth = rom[curMapSecondHeaderAddress+2];

		System.out.println("Map dimensions "+mapWidth+"x"+mapHeight);

		//blockDataAddress = rom[curMapSecondHeaderAddress+4] + (rom[curMapSecondHeaderAddress+5]<<8) +
		//		(rom[curMapSecondHeaderAddress+3]-1)*0x4000; // adjust bank
		
		mapBlocks = new int[mapWidth+6][mapHeight+6];
		//for(int i=0;i<mapWidth+6;i++)
		//	for(int j=0;j<mapHeight+6;j++)
		//		mapBlocks[i][j] = borderBlock;
		int curAddress = RomInfo.rom.curBlockDataAddress; //blockDataAddress;
		for(int j=0;j<mapHeight+6;j++)
			for(int i=0;i<mapWidth+6;i++)
				mapBlocks[i][j] = memory[curAddress++];
		
		curMapTileset = rom[curMapHeaderAddress+1];
		
		tileSetCollisionDataAddress = Util.getWordAt(RomInfo.rom.tilesetsAddress + 0xf*curMapTileset + 7) +
				(rom[RomInfo.rom.tilesetsAddress + 0xf*curMapTileset + 6]-1)*0x4000; // adjust bank
				
		
		mapStepCollision = new int[(mapWidth+6)*2][(mapHeight+6)*2];
		mapStepCollisionOverride = new boolean[(mapWidth+6)*2][(mapHeight+6)*2];
		for(int i=0;i<mapWidth+6;i++)
			for(int j=0;j<mapHeight+6;j++) {
				mapStepCollision[2*i  ][2*j  ] = mapBlocks[i][j] == 0 ? 0xFF : rom[tileSetCollisionDataAddress + 4*mapBlocks[i][j]];
				mapStepCollision[2*i+1][2*j  ] = mapBlocks[i][j] == 0 ? 0xFF : rom[tileSetCollisionDataAddress + 4*mapBlocks[i][j] + 1];
				mapStepCollision[2*i  ][2*j+1] = mapBlocks[i][j] == 0 ? 0xFF : rom[tileSetCollisionDataAddress + 4*mapBlocks[i][j] + 2];
				mapStepCollision[2*i+1][2*j+1] = mapBlocks[i][j] == 0 ? 0xFF : rom[tileSetCollisionDataAddress + 4*mapBlocks[i][j] + 3];
			}
		
		
		// parse map objects
		int mapEventHeaderAddress = rom[curMapSecondHeaderAddress+9] +
				(rom[curMapSecondHeaderAddress+10]<<8) + 
				(rom[curMapSecondHeaderAddress+6]-1)*0x4000; // adjust bank
		
		
		curAddress = mapEventHeaderAddress + 2; // skip filler
		int numWarps = rom[curAddress++];
		curAddress += numWarps * 5; // skip warps
		int numTriggers = rom[curAddress++];
		curAddress += numTriggers * 8; // skip triggers
		int numSigns = rom[curAddress++];
		curAddress += numSigns * 5; // skip signs
		
		int numSprites = rom[curAddress++];
		System.out.println("map contains "+numWarps+" warps, "+numTriggers+" triggers, "+numSigns+" signs and "+numSprites+" sprites");
		
		int numMovingSprites = 0;
		for(int i=0;i<numSprites;i++) {
			curAddress++; // skip sprite ID
			int y = rom[curAddress++] - 4 + 6;
			int x = rom[curAddress++] - 4 + 6;
			int facingDirection = rom[curAddress++];
			int movement = rom[curAddress++];
			curAddress++; // clockHour
			curAddress++; // clockDaytime
			int colorFunction = rom[curAddress++];
			int range = rom[curAddress++];
			int scriptPointer = rom[curAddress] + (rom[curAddress+1]<<8) + (rom[curMapSecondHeaderAddress+6]-1)*0x4000; // adjust bank
			curAddress+=2;
			int bitIndex = rom[curAddress] + (rom[curAddress+1]<<8);
			curAddress+=2;
			
			System.out.println("found object at "+(x-6)+":"+(y-6)+" (raw "+x+":"+y+") movement "+movement);
			
			if(movement == 0 /*|| (facingDirection >= 3 && facingDirection <= 9)*/) {
				if(!isEventFlagSet(bitIndex)) {
					mapStepCollisionOverride[x][y] = true; // stationary sprite blocks step
					System.out.println("object blocks tile "+(x-6)+":"+(y-6));
				}
				else
					System.out.println("sprite "+(i+1)+" is hidden");
			} else
				numMovingSprites++;
			
			if((colorFunction & 0xF) == 2) { // trainer
				
				int trainerBit = rom[scriptPointer] + (rom[scriptPointer+1]<<8);
				
				boolean alreadFought = isEventFlagSet(trainerBit);
				if(alreadFought)
					System.out.println("trainer already fought!");
				else {
					System.out.println("trainer engage distance: "+range);
					switch(facingDirection) {
					case 0x6: // down
						while(range --> 0)
							mapStepCollisionOverride[x][++y] = true;
						break;
					case 0x7: // up
						while(range --> 0)
							mapStepCollisionOverride[x][--y] = true;
						break;
					case 0x8: // left
						while(range --> 0)
							mapStepCollisionOverride[--x][y] = true;
						break;
					case 0x9: // right
						while(range --> 0)
							mapStepCollisionOverride[++x][y] = true;
						break;
					case 0x3: // rotato (slow)
						System.out.println("trainer is slow rotato");
						break;
					case 0xa: // rotato (fast)
						System.out.println("trainer is fast rotato");
						break;
					case 0x1f: // rotato (clockwise)
						System.out.println("trainer is clockwise rotato");
						break;
					default:
						System.out.println("unknown facing direction "+facingDirection);
						break;
					}
				}
			}
		}
		if(numMovingSprites > 0)
			System.out.println("map contains "+numMovingSprites+" moving sprites");
	}
	
	private boolean isEventFlagSet(int bitIndex) {
		return (Gb.readMemory(RomInfo.rom.eventFlagsAddress + bitIndex/8) & (1<<(bitIndex%8))) != 0;
	}
		
	public void printBlockMap() {
		for(int y=0;y<mapBlocks[0].length;y++) {
			for(int x=0;x<mapBlocks.length;x++)
				System.out.print(" " + Util.toHex(mapBlocks[x][y],2));
			System.out.println();
		}
		System.out.println();
	}

	public void printCollisionMap() {
		for(int y=0;y<mapStepCollision[0].length;y++) {
			for(int x=0;x<mapStepCollision.length;x++)
				System.out.print(" " + Util.toHex(mapStepCollision[x][y],2));
			System.out.println();
		}
		System.out.println();
	}
	
	@Override
	public void printMap() {
		printCollisionMap2();
	}

	public void printCollisionMap2() {
		for(int y=0;y<mapStepCollision[0].length;y++) {
			for(int x=0;x<mapStepCollision.length;x++)
				System.out.print(isPassable(x,y)?".":"X");
			System.out.println();
		}
		System.out.println();
	}
}
