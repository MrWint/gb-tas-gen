package mrwint.gbtasgen.segment.pokemon.gen2.common;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class NamingSegment extends Segment {
	
	public static String[] CHAR_MAP = {
		"ABCDEFGHI",
		"abcdefghi",
		"JKLMNOPQR",
		"jklmnopqr",
		"STUVWXYZ ",
		"stuvwxyz ",
		"-?!/.,   ",
		"*():;[]πµ"};

	public static String[] EXTENDED_CHAR_MAP = {
		"ABCDEFGHI",
		"abcdefghi",
		"JKLMNOPQR",
		"jklmnopqr",
		"STUVWXYZ ",
		"stuvwxyz ",
		"*():;[]π”",
		"€ðłµ¶ſŧ„0",
		"-?!ºª/.,&",
		"123456789",
	};

	private String name;
	private List<Integer> moveList;
	private String[] charMap;
	private int numRows;
	private int numCols;

	public NamingSegment(String name) {
		this(name, false);
	}

	public NamingSegment(String name, boolean extended) {
		this.name = name;
		this.charMap = extended ? EXTENDED_CHAR_MAP : CHAR_MAP;
		this.numRows = charMap.length/2;
		this.numCols = charMap[0].length();
		generateMoveList();
		
		debugPrintMoveList();
	}

	private void debugPrintMoveList() {
		for(int move : moveList) {
			switch(move) {
			case Move.RIGHT:
				System.out.println("right");
				break;
			case Move.DOWN:
				System.out.println("down");
				break;
			case Move.UP:
				System.out.println("up");
				break;
			case Move.LEFT:
				System.out.println("left");
				break;
			case Move.START:
				System.out.println("start");
				break;
			case Move.SELECT:
				System.out.println("select");
				break;
			case Move.A:
				System.out.println("a");
				break;
			}
		}
		System.out.println();
	}
	
	private int getCharX(char c) {
		for(int i=0;i<charMap.length;i++)
			if(charMap[i].contains(""+c))
				return charMap[i].indexOf(c);
		return -1;
	}
	
	private int getCharY(char c) {
		for(int i=0;i<charMap.length;i++)
			if(charMap[i].contains(""+c))
				return i/2;
		return -1;
	}
	
	private boolean isLowerCase(char c) {
		for(int i=0;i<charMap.length;i++)
			if(charMap[i].contains(""+c))
				return (i%2) != 0;
		return false;
	}
	
	private List<Integer> generateMovesFromTo(int curX,int curY,int newX,int newY) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int dx = (newX + numCols - curX)%numCols;
		if(dx > numCols/2) dx-=numCols;
		int dy = (newY + (numRows+1) - curY)%(numRows+1);
		if(dy > (numRows+1)/2) dy-=(numRows+1);
		if(Math.abs(dy) > Math.abs(dx)) {
			if(dy>0) {
				dy--;
				ret.add(Integer.valueOf(Move.DOWN));
			} else if(dy < 0) {
				dy++;
				ret.add(Integer.valueOf(Move.UP));
			}
		}
		while(dx != 0 || dy != 0) {
			if(dx>0 && (newY - dy != numRows) && (newY - dy != -1)) {
				dx--;
				ret.add(Integer.valueOf(Move.RIGHT));
			} else if(dx < 0 && (newY - dy != numRows) && (newY - dy != -1)) {
				dx++;
				ret.add(Integer.valueOf(Move.LEFT));
			}
			if(dy>0) {
				dy--;
				ret.add(Integer.valueOf(Move.DOWN));
			} else if(dy < 0) {
				dy++;
				ret.add(Integer.valueOf(Move.UP));
			}
		}
		return ret;
	}
	
	/*private List<Integer> generateMovesToCaseSwitch(int curX,int curY,int goalY) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if(curY < 2 || (curY == 2 && goalY >= 2))
			while(curY-- >= 0)
				ret.add(Integer.valueOf(Move.UP));
		else
			while(curY++ < 5)
				ret.add(Integer.valueOf(Move.DOWN));
		return ret;
	}
	
	private List<Integer> generateMovesFromCaseSwitch(int newX,int newY,int pastY) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if(newY < 2 || (newY == 2 && pastY < 2)) {
			ret.add(Integer.valueOf(Move.DOWN));
			ret.addAll(generateMovesFromTo(0, 0, newX, newY));
		}
		else {
			ret.add(Integer.valueOf(Move.UP));
			ret.addAll(generateMovesFromTo(0, 4, newX, newY));
		}
		return ret;
	}*/
	
	private int calcCost(List<Integer> moves, boolean caseSwitchNeeded) {
		int cost = 0;
		for(int i=0;i<moves.size()-1;i++)
			if(moves.get(i).equals(moves.get(i+1)))
				cost++;
		if(caseSwitchNeeded && cost == 0)
			cost++;
		return cost + moves.size();
	}
	
	private void generateMoves(int curX,int curY,int newX,int newY,boolean caseSwitchNeeded) {
		//ArrayList<Integer> caseSwitch = new ArrayList<Integer>();
		//caseSwitch.addAll(generateMovesToCaseSwitch(curX, curY,newY));
		//caseSwitch.addAll(generateMovesFromCaseSwitch(newX, newY,curY));
		
		ArrayList<Integer> direct = new ArrayList<Integer>();
		direct.addAll(generateMovesFromTo(curX, curY, newX, newY));
		int directCosts = calcCost(direct, caseSwitchNeeded);
		
		List<Integer> minRoute = direct;
		int minCosts = directCosts;
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<3;j++) {
				if(i/3 == j)
					continue;
				for(int k=0;k<2;k++) {
					ArrayList<Integer> cur = new ArrayList<Integer>();
					cur.addAll(generateMovesFromTo(curX, curY, i, k==0 ? 0 : numRows-1));
					cur.add(k == 0 ? Move.UP : Move.DOWN);
					int dx = (j+3 - (i/3))%3;
					cur.add(dx == 2 ? Move.LEFT : Move.RIGHT);
					cur.addAll(generateMovesFromTo(j*3, numRows, newX, newY));
					int curCosts = calcCost(cur, caseSwitchNeeded);
					if(curCosts < minCosts) {
						System.out.println("Use special route from ("+curX+","+curY+") to ("+newX+","+newY+")!");
						minCosts = curCosts;
						minRoute = cur;
					}
				}
			}
		}

		int lastMove = -1;
		for(Integer move : minRoute) {
			if(move == lastMove && caseSwitchNeeded) {
				moveList.add(Move.SELECT);
				caseSwitchNeeded = false;
			}
			moveList.add(move);
			lastMove = move;
		}
		if(caseSwitchNeeded)
			moveList.add(Move.SELECT);
	}
	
	private void generateMoveList() {
		moveList = new ArrayList<Integer>();
		int curX = 0, curY = 0;
		boolean upperCase = true;
		for(int i=0;i<name.length();i++) {
			char c = name.charAt(i);
			int newX = getCharX(c);
			int newY = getCharY(c);
			if(newX == -1 || newY == -1)
				throw new RuntimeException("invalid character "+c+" found");
			boolean newUpperCase = !isLowerCase(c);
			boolean caseSwitchNeeded = (newUpperCase && !upperCase) || (!newUpperCase && upperCase);
			generateMoves(curX, curY, newX, newY, caseSwitchNeeded);
			moveList.add(Move.A);
			if(caseSwitchNeeded)
				upperCase = !upperCase;
			curX = newX;
			curY = newY;
		}
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		for(Integer i : moveList)
			in = new MoveSegment(new PressButton(i,Metric.PRESSED_JOY),0,0).execute(in);
		if(name.length() < 8)
			in = new MoveSegment(new PressButton(Move.START,Metric.PRESSED_JOY),0,0).execute(in);
//		in = new SleepSegment(10000).execute(in);
		in = new MoveSegment(new PressButton(Move.A,Metric.PRESSED_JOY),0,0).execute(in);
		return in;
	}
}
