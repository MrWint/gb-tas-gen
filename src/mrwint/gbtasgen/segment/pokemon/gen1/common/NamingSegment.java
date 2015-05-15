package mrwint.gbtasgen.segment.pokemon.gen1.common;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class NamingSegment extends SeqSegment {
	
	private String name;
	private List<Integer> moveList;
	
	public NamingSegment(String name) {
		this.name = name;
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
	
	String[] charMap = {
			"ABCDEFGHI",
			"abcdefghi",
			"JKLMNOPQR",
			"jklmnopqr",
			"STUVWXYZ ",
			"stuvwxyz ",
			"*():;[]π ",
			"*():;[]π ",
			"-?!´♀/., ",
			"-?! ♀/., "};
	
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
	
	private List<Integer> generateMovesFromTo(int curX,int curY,int newX,int newY) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int dx = (newX + 9 - curX)%9;
		if(dx > 4) dx-=9;
		int dy = newY - curY;
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
			if(dx>0) {
				dx--;
				ret.add(Integer.valueOf(Move.RIGHT));
			} else if(dx < 0) {
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
	
	private List<Integer> generateMovesToCaseSwitch(int curX,int curY,int goalY) {
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
	}
	
	private void generateMoves(int curX,int curY,int newX,int newY,boolean caseSwitchNeeded) {
		ArrayList<Integer> caseSwitch = new ArrayList<Integer>();
		caseSwitch.addAll(generateMovesToCaseSwitch(curX, curY,newY));
		caseSwitch.addAll(generateMovesFromCaseSwitch(newX, newY,curY));
		
		ArrayList<Integer> direct = new ArrayList<Integer>();
		direct.addAll(generateMovesFromTo(curX, curY, newX, newY));
		
		int caseSwitchPenalty = 0;
		for(int i=0;i<caseSwitch.size()-1;i++)
			if(caseSwitch.get(i) == caseSwitch.get(i+1))
				caseSwitchPenalty++;
		if(caseSwitchNeeded && caseSwitchPenalty == 0)
			caseSwitchPenalty++;

		int directPenalty = 0;
		for(int i=0;i<direct.size()-1;i++)
			if(direct.get(i) == direct.get(i+1))
				directPenalty++;
		if(caseSwitchNeeded && directPenalty == 0)
			directPenalty++;
		
		if(caseSwitch.size() + caseSwitchPenalty < direct.size() + directPenalty)
			direct = caseSwitch;
		
		int lastMove = -1;
		//System.out.println("Naming Movement from ("+curX+","+curY+") to ("+newX+","+newY+"):");
		for(Integer move : direct) {
			if(move == lastMove && caseSwitchNeeded) {
				moveList.add(Move.SELECT);
				//System.out.println("select");
				caseSwitchNeeded = false;
			}
			moveList.add(move);
			//System.out.println(move);
			lastMove = move;
		}
		if (caseSwitchNeeded)
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
			boolean caseSwitchNeeded = (c >= 'A' && c <= 'Z' && !upperCase) || (c >= 'a' && c <= 'z' && upperCase);
			generateMoves(curX, curY, newX, newY, caseSwitchNeeded);
			moveList.add(Move.A);
			if(caseSwitchNeeded)
				upperCase = !upperCase;
			curX = newX;
			curY = newY;
		}
	}

	@Override
	public void execute() {
		for(Integer i : moveList)
			seqMove(new PressButton(i, Metric.PRESSED_JOY), 0);
	}
}
