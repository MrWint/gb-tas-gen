package mrwint.gbtasgen.segment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.WalkStep;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Map;
import mrwint.gbtasgen.util.Util;

public class WalkToSegment extends Segment {
	
	public static class Pos {
		int x,y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
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
			Pos other = (Pos) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
	
	private int destX, destY;
	private boolean checkLastStep;
	private int maxBufferSize;
		
	public WalkToSegment setMaxBufferSize(int maxBufferSize) {
		this.maxBufferSize = maxBufferSize;
		return this;
	}
	
	private static final int[] dx = {0,1,0,-1};
	private static final int[] dy = {1,0,-1,0};
	
	private static final Segment[] walkSegment = {
		new MoveSegment(new WalkStep(Move.DOWN,true),0,0),
		new MoveSegment(new WalkStep(Move.RIGHT,true),0,0),
		new MoveSegment(new WalkStep(Move.UP,true),0,0),
		new MoveSegment(new WalkStep(Move.LEFT,true),0,0)};
	
	private static final Segment[] walkSegmentNoCheck = {
		new MoveSegment(new WalkStep(Move.DOWN,false),0,0),
		new MoveSegment(new WalkStep(Move.RIGHT,false),0,0),
		new MoveSegment(new WalkStep(Move.UP,false),0,0),
		new MoveSegment(new WalkStep(Move.LEFT,false),0,0)};
	
	
	public WalkToSegment(int destX, int destY) {
		this(destX,destY,true);
	}

	public WalkToSegment(int destX, int destY, boolean checkLastStep) {
		this.destX = destX;
		this.destY = destY;
		this.checkLastStep = checkLastStep;
		this.maxBufferSize = StateBuffer.MAX_BUFFER_SIZE;
	}

	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		if(in.isEmpty())
			return new StateBuffer(maxBufferSize);
		
		State s = in.getStates().iterator().next();
		s.restore();
		Util.runToFirstDifference(0, Move.DOWN, Metric.DOWN_JOY);
		//Util.runToAddress(Move.UP, Move.UP, 0x51D); // .handleDirectionButtonPress
		for(int i = 0; i < 100; i++)
			State.step(); // skip until map loading is finished
		
		Map map = new Map();
		int width = map.getStepWidth();
		int height = map.getStepHeight();
		int[][] d = new int[width][height];
		for(int x=0;x<width;x++)
			for(int y=0;y<height;y++)
				d[x][y] = -1;
		d[destX+6][destY+6] = 0;
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(destX+6, destY+6));
		
		//System.out.println("planning path to ("+destX+","+destY+") on map "+curMapID+" with dimensions "+width+"x"+height);

		while(!q.isEmpty()) {
			Pos p = q.poll();
			for(int dir=0;dir<4;dir++) {
				int nx = p.x+dx[dir];
				int ny = p.y+dy[dir];
				if(nx < 0 || nx >= width || ny < 0 || ny >= height)
					continue;
				if(d[nx][ny] != -1)
					continue;
				d[nx][ny] = d[p.x][p.y] + 1;
				if(map.isInversePassable(nx, ny, p.x, p.y, (dir+2)%4))
					q.add(new Pos(nx,ny));
			}
		}
		
		int playerX = (State.getCurrentMemory()[RomInfo.rom.curPositionXAddress]-4)+6;
		int playerY = (State.getCurrentMemory()[RomInfo.rom.curPositionYAddress]-4)+6;
		int destX = this.destX + 6;
		int destY = this.destY + 6;
		Pos initialPos = new Pos(playerX, playerY);
		System.out.println("planning path from ("+playerX+","+playerY+") to ("+destX+","+destY+")");
		System.out.println("map:");
		map.printCollisionMap2();
		System.out.println("distances:");
		Map.printDistMap(d);
		
		HashMap<Pos, StateBuffer> posBuffers = new HashMap<Pos, StateBuffer>();
		posBuffers.put(initialPos, in);
		
		int startDist = d[playerX][playerY];
		if(startDist == -1) {
			map.printCollisionMap2();
			Map.printDistMap(d);
			throw new RuntimeException("destination unreachable: ("+destX+","+destY+") from ("+playerX+","+playerY+")");
		}
		System.out.println("distance: "+startDist);
		
		for(int curDist = startDist; curDist > 0; curDist--) {
			//System.out.println("handling distance "+curDist);
			for(int x=0;x<width;x++)
				for(int y=0;y<height;y++)
					if(d[x][y] == curDist) {
						StateBuffer curBuffer = popBuffer(new Pos(x,y), posBuffers);
						if(curBuffer.size() == 0)
							continue;
						//System.out.println("buffer at ("+x+","+y+") has "+curBuffer.size()+" entries");
						for(int dir=0;dir<4;dir++) {
							int nx = x+dx[dir];
							int ny = y+dy[dir];
							if(d[nx][ny] == curDist-1 && (map.isPassable(nx, ny, dir) || curDist == 1)) {
								//System.out.println("going dir "+dir);
								StateBuffer newBuffer;
								if(checkLastStep || curDist > 1)
									newBuffer = walkSegment[dir].execute(curBuffer);
								else
									newBuffer = walkSegmentNoCheck[dir].execute(curBuffer);
								StateBuffer nextBuffer = getBuffer(new Pos(nx, ny), posBuffers);
								//System.out.println("adding buffer with "+newBuffer.size()+" entries to buffer with "+nextBuffer.size()+" entries");
								nextBuffer.addAll(newBuffer);
							}
						}
					}
		}
		StateBuffer goalBuffer = getBuffer(new Pos(destX, destY), posBuffers);
		System.out.println("found "+goalBuffer.size()+" successful states");
		return getBuffer(new Pos(destX, destY), posBuffers);
	}
	
	private StateBuffer popBuffer(Pos p, HashMap<Pos, StateBuffer> posBuffers) {
		if(!posBuffers.containsKey(p))
			posBuffers.put(p, new StateBuffer(maxBufferSize));
		StateBuffer ret = posBuffers.get(p);
		posBuffers.remove(p);
		return ret;
	}
	
	private StateBuffer getBuffer(Pos p, HashMap<Pos, StateBuffer> posBuffers) {
		if(!posBuffers.containsKey(p))
			posBuffers.put(p, new StateBuffer(maxBufferSize));
		return posBuffers.get(p);
	}

}
