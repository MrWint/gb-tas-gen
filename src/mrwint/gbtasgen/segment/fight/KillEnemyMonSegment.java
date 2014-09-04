package mrwint.gbtasgen.segment.fight;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.gen1.CheckNoAIMove;
import mrwint.gbtasgen.move.DelayUntil;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SelectMoveInList;
import mrwint.gbtasgen.move.WithMetric;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.DelayUntilFactory;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.DelayableMoveFactory;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Util;
import mrwint.gbtasgen.util.fight.DamageCalc;
import mrwint.gbtasgen.util.fight.PII;




public class KillEnemyMonSegment extends Segment {

	public static final int MAX_SKIPS = -1;
	
	public static final int FULL_BUFFER_CUTOFF_DELAY = 0;
	public static final int NONEMPTY_BUFFER_CUTOFF_DELAY = 0;

	
	public static class CheckEnemyMoveMetric extends Metric {
		final int[] move;
		final int keys;
		public static CheckEnemyMoveMetric noKeys(int...move) {
			return new CheckEnemyMoveMetric(0, move);
		}
		public static CheckEnemyMoveMetric withKeys(int keys, int...move) {
			return new CheckEnemyMoveMetric(keys, move);
		}
		private CheckEnemyMoveMetric(int keys, int[] move) {
			this.move = move;
			this.keys = keys;
		}
		@Override
		public int getMetric() {
			State s = new State();
			Util.runToAddress(0, keys, RomInfo.rom.fightAfterAIChooseMove);
			int enemyMove = Gb.readMemory(RomInfo.rom.fightCurEnemyMoveAddress); // selected enemy move
			s.restore();
			System.out.println("found enemy move "+enemyMove);
			return (((move.length == 0 && enemyMove != 98/*Quick Attack*/) || Util.arrayContains(move,enemyMove)) ? 1 : 0);
		}
	}
	
	public static class CheckMoveOrderMetric extends StateResettingMetric {
		final int[] move;
		final int keys;
		final boolean faster;
		public CheckMoveOrderMetric(boolean faster,int[] move,int keys) {
			this.faster = faster;
			this.move = move;
			this.keys = keys;
		}
		@Override
		public int getMetricInternal() {
			//System.out.println("runToAddress");
			//try {Thread.sleep(2000);} catch (InterruptedException e) {}
			Util.runToAddress(0, keys, RomInfo.rom.fightDetermineAttackOrder);
			//System.out.println("ranToAddress");
			//try {Thread.sleep(2000);} catch (InterruptedException e) {}
			int enemyMove = Gb.readMemory(RomInfo.rom.fightCurEnemyMoveAddress); // selected enemy move
			if(move.length > 0 && !Util.arrayContains(move, enemyMove))
				return 0;
			int add = Util.runToAddress2(0,0,RomInfo.rom.fightDetermineAttackOrderPlayerFirst,RomInfo.rom.fightDetermineAttackOrderEnemyFirst);
			if ((add == RomInfo.rom.fightDetermineAttackOrderPlayerFirst) != faster)
				return 0;
//			System.out.println("move "+enemyMove);
			if (!faster)
				add = Util.runToAddress2(0,0,RomInfo.rom.fightAIMoveCheck); // Check for AI moves (item uses etc.)
			else
				return 1;
			return (add == RomInfo.rom.fightAIExecuteMove) ? 1 : 0;
		}
	}
	
	public static class CheckAdditionalTexts extends Metric {
		
		final int initialMove;
		final boolean resetState;
		
		public CheckAdditionalTexts(int initialMove, boolean resetState) {
			this.initialMove = initialMove;
			this.resetState = resetState;
		}
		
		@Override
		public int getMetric() {
			State s = null;
			if(resetState) s = new State();
			int add = Util.runToAddress2(0, initialMove, RomInfo.rom.fightEndTurnAddresses);
			if(add == RomInfo.rom.printLetterDelayJoypadAddress) {
				System.out.println("CheckAdditionalTexts: found additional PrintText!");
//				try { Thread.sleep(2000); } catch (InterruptedException e) { }
//				for(int i=0;i<40;i++) {
//					State.step();
//					try { Thread.sleep(100); } catch (InterruptedException e) { }
//				}
//				try { Thread.sleep(2000); } catch (InterruptedException e) { }
				if(resetState) s.restore();
				return 0;
			}
			if(resetState) s.restore();
			return 1;
		}
	}
	
	// returns damage dealt, or Integer.MIN_VALUE if attack missed crit status doesn't match
	public static class CheckMoveDamage extends Metric {
		final boolean criticalHit;
		final boolean effectMiss;
		final CheckAdditionalTexts cat;
		final boolean negateDamage;
		final boolean ignoreDamage;
		final boolean expectAdditionalTexts;
		final int thrashAdditionalTurns;
		public CheckMoveDamage(boolean criticalHit, boolean effectMiss, boolean checkForAdditionalTexts, boolean expectAdditionalTexts, boolean negateDamage, boolean ignoreDamage, int thrashAdditionalTurns) {
			this.criticalHit = criticalHit;
			this.effectMiss = effectMiss;
			this.cat = checkForAdditionalTexts ? new CheckAdditionalTexts(0,false) : null;
			this.expectAdditionalTexts = expectAdditionalTexts;
			this.negateDamage = negateDamage;
			this.ignoreDamage = ignoreDamage;
			this.thrashAdditionalTurns = thrashAdditionalTurns;
		}
		@Override
		public int getMetric() {
			State s = new State();
			//Util.runToAddress(0, 0, RomInfo.rom.fightDamageCalc);
			//int atk = State.getRegister(Register.BC) >> 8;
			//int def = State.getRegister(Register.BC) % 256;
			//int pow = State.getRegister(Register.DE) >> 8;
			//int lvl = State.getRegister(Register.DE) % 256;
			
			//Util.runToAddress(0, 0, RomInfo.rom.fightDamageVariation);
			//int maxdmg = Util.getMemoryBigEndian(RomInfo.rom.fightCurDamageAddress);
			
			Util.runToAddress(0, 0, RomInfo.rom.fightBattleCommand0a);
			int crit = Gb.readMemory(RomInfo.rom.fightCriticalHitAddress);
			int missed = Gb.readMemory(RomInfo.rom.fightAttackMissedAddress);
			boolean failure = missed != 0 || criticalHit != (crit != 0);
			if (thrashAdditionalTurns > 0 && Gb.readMemory(RomInfo.rom.thrashNumTurnsAddress) < thrashAdditionalTurns) {
				failure = true;
				System.out.println("caught bad thrash "+ Gb.readMemory(RomInfo.rom.thrashNumTurnsAddress));
			}
			if (Util.isGen2()) {
				int effectMissed = Gb.readMemory(RomInfo.rom.fightEffectMissedAddress);
				failure = failure || this.effectMiss != (effectMissed != 0);
			}
			if(failure) {
				s.restore();
				return Integer.MIN_VALUE;
			}
			int dmg = Util.getMemoryBigEndian(RomInfo.rom.fightCurDamageAddress);
			if(dmg > 0 && cat != null)
				if(cat.getMetric() == (expectAdditionalTexts ? 1 : 0)) { // found additional texts
					s.restore();
					return Integer.MIN_VALUE;
				}
			s.restore();
//			System.out.println(crit+" "+missed+" "+effectMissed+" "+dmg);
			//System.out.println("atk: "+atk+", def: "+def+", pow: "+pow+", lvl: "+lvl);
			//System.out.println("max damage: "+maxdmg+", dmg: "+dmg);
			return ignoreDamage ? 0 : negateDamage ? -dmg : dmg;
		}
	}

	public static class FightState {
		int[][] atkCnt = new int[4][2];
		int spareDamage;
		int spareEnemyDamage;
		
		public FightState(int[][] atkCnt, int spareDamage, int spareEnemyDamage) {
			this.atkCnt = atkCnt;
			this.spareDamage = spareDamage;
			this.spareEnemyDamage = spareEnemyDamage;
		}
		
		public FightState(int[][] atkCnt, int spareDamage, int spareEnemyDamage, int usedAtkIdx, int usedAtkCrit) {
			this.atkCnt = new int[4][2];
			for (int i = 0; i < 4; i++)
				for(int j=0;j<2;j++)
					this.atkCnt[i][j] = atkCnt[i][j];
			this.atkCnt[usedAtkIdx][usedAtkCrit]--;
			if(this.atkCnt[usedAtkIdx][usedAtkCrit] < 0) System.out.println("ERROR "+usedAtkIdx+" "+usedAtkCrit+" below zero");
			this.spareDamage = spareDamage;
			this.spareEnemyDamage = spareEnemyDamage;
		}
		
		@Override
		public String toString() {
			String ret = "(";
			for(int i=0;i<4;i++)
				for(int j=0;j<2;j++)
					ret+=atkCnt[i][j]+",";

			return ret+" dmg: "+spareDamage+" OwnHP: "+spareEnemyDamage+")";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.deepHashCode(atkCnt);
			result = prime * result + spareDamage;
			result = prime * result + spareEnemyDamage;
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
			FightState other = (FightState) obj;
			if (!Arrays.deepEquals(atkCnt, other.atkCnt))
				return false;
			if (spareDamage != other.spareDamage)
				return false;
			if (spareEnemyDamage != other.spareEnemyDamage)
				return false;
			return true;
		}
	}
	
	public int[][] attackCount = new int[4][2];

	public static class EnemyMoveDesc {
		public int[] move;
		public AttackActionSegment segment;
		public boolean noDamage;
		public EnemyMoveDesc(AttackActionSegment segment, boolean noDamage, int[] move) {
			this.move = move;
			this.segment = segment;
			this.noDamage = noDamage;
		}
		public static EnemyMoveDesc missWith(int... move) {
			return new EnemyMoveDesc(new MissMetricSegment(false), true, move);
		}
		public static EnemyMoveDesc missWith(Metric metric, int... move) {
			return new EnemyMoveDesc(new MissMetricSegment(false, metric), true, move);
		}
		public static EnemyMoveDesc hitWith(int move, boolean isCrit, boolean isEffective) {
			return new EnemyMoveDesc(new HitMetricSegment(isCrit, false, isEffective, false, 0, false, false), false, new int[]{move});
		}
		public static EnemyMoveDesc hitWith(int move, boolean isCrit, boolean isEffective, int additionalTexts) {
			return new EnemyMoveDesc(new HitMetricSegment(isCrit, false, isEffective, false, additionalTexts, false, false), false, new int[]{move});
		}
		public static EnemyMoveDesc hitWith(int move, boolean isCrit, boolean isEffective, int additionalTexts, boolean ignoreDamage) {
			return new EnemyMoveDesc(new HitMetricSegment(isCrit, false, isEffective, false, additionalTexts, false, ignoreDamage), false, new int[]{move});
		}
		public static EnemyMoveDesc hitWith(int move, boolean isCrit, boolean effectMiss, boolean isEffective) {
			return new EnemyMoveDesc(new HitMetricSegment(isCrit, effectMiss,isEffective, false, 0, false, false), false, new int[]{move});
		}
		public static EnemyMoveDesc hitWith(int move, boolean isCrit, boolean effectMiss, boolean isEffective, int additionalTexts) {
			return new EnemyMoveDesc(new HitMetricSegment(isCrit, effectMiss, isEffective, false, additionalTexts, false, false), false, new int[]{move});
		}
		public static EnemyMoveDesc hitWith(int move, boolean isCrit, boolean effectMiss, boolean isEffective, int additionalTexts, boolean ignoreDamage) {
			return new EnemyMoveDesc(new HitMetricSegment(isCrit, effectMiss, isEffective, false, additionalTexts, false, ignoreDamage), false, new int[]{move});
		}
	}
	public EnemyMoveDesc[] enemyMoveDesc = {EnemyMoveDesc.missWith()};
	public int[] numEndOfTurnTexts = new int[0];
	public int[] numEndOfAttackTexts = new int[0];

	public int numExpGainers = 1;				// number of text lines to close for XP (level ups, multi-mons etc)
	public boolean onlyPrintInfo = false;		// only print own and enemy fighting stats
	public int maxOwnDamage = 0;
	public boolean skipFirstMainBattleMenu = false;
	public int thrashNumAdditionalTurns = 0;
	public boolean nonCritsFirst = false;

	public int rageInitialVal = 0;
	public int rageMaxVal = 0;
	
	public int nextMonSpecies = -1;
	public int nextMonLevel = -1;
	
	public int lastAttack = -1;


	private int[][] attackDmg = new int[4][2];	// max damage a noncrit/crit can do
	private boolean[] attackEffective = new boolean[4];	// attack will display an effectivity text
	private boolean faster;						// own mon can be faster than enemy mon

	private int[][] enemyDmg = new int[4][2];	// max damage a noncrit/crit can do
	private int[] enemyMove = new int[4];		// move ID

	private HashMap<FightState, StateBuffer>[] bufs;
	private StateBuffer goalBuf;
	private int numTurns;

	@SuppressWarnings("unchecked")
	@Override
	public StateBuffer execute(StateBuffer in) {
		printInfo(in);

		int enemySpeed = DamageCalc.getStat(false, DamageCalc.STAT_SPD, false);
		int playerSpeed = DamageCalc.getStat(true, DamageCalc.STAT_SPD, false);
		
		if(enemySpeed > playerSpeed) {
			System.out.println("enemy is faster ("+playerSpeed+" to "+enemySpeed+")");
			faster = false;
		} else {
			System.out.println("player is faster ("+playerSpeed+" to "+enemySpeed+")");
			faster = true;
		}
		
		if(onlyPrintInfo)
			return in;

		int enemyHP = Util.getMemoryBigEndian(RomInfo.rom.fightEnemyMonHPAddress);
		int ownHP = Util.getMemoryBigEndian(RomInfo.rom.fightBattleMonHPAddress);
		
		if(maxOwnDamage < 0)
			maxOwnDamage = ownHP -1;

		if(maxOwnDamage >= ownHP) {
			System.err.println("maxOwnDamage "+maxOwnDamage+" larger than ownHP "+ownHP);
			maxOwnDamage = ownHP -1;
		}

		int sumDamage = 0;
		int numAttacks = 0;
		int maxAttackDamage = -1;
		int numOwnMoves = 0;
		
		// calc number of attacks/sum of damage/max damage
		for(int i=0;i<4;i++) { // moves
			attackDmg[i][0] = DamageCalc.getDamage(true, i, false);
			attackDmg[i][1] = DamageCalc.getDamage(true, i, true);
			enemyDmg[i][0] = DamageCalc.getDamage(false, i, false, true);
			enemyDmg[i][1] = DamageCalc.getDamage(false, i, true, true);
			enemyMove[i] = DamageCalc.getMove(false,i);

			if(attackDmg[i][0] < 0) {
				if(attackCount[i][0] + attackCount[i][1] > 0) System.err.println("ERROR: used invalid move "+i);
			} else {
				numOwnMoves++;
				if(attackCount[i][0] + attackCount[i][1] > 0) {
					if(attackDmg[i][0] == 0) System.err.println("ERROR: used non-damage move "+i);
					if(rageInitialVal > 0) {
						if(DamageCalc.getMove(true, i) != 99) System.err.println("Not used Rage but rageInitialVal set!");
						int rageSumCount = 0;
						for(int j=0;j<attackCount[i][0]; j++) rageSumCount += Math.min(rageInitialVal+j, rageMaxVal > 0 ? rageMaxVal : Integer.MAX_VALUE);
						sumDamage += rageSumCount * attackDmg[i][0];
						rageSumCount = 0;
						for(int j=0;j<attackCount[i][1]; j++) rageSumCount += Math.min(rageInitialVal+attackCount[i][0]+j, rageMaxVal > 0 ? rageMaxVal : Integer.MAX_VALUE);
						sumDamage += rageSumCount * attackDmg[i][1];
					} else {
						if(DamageCalc.getMove(true, i) == 99) System.err.println("Used Rage but no rageInitialVal set!");
						sumDamage += attackCount[i][0] * attackDmg[i][0];
						sumDamage += attackCount[i][1] * attackDmg[i][1];
					}
					numAttacks += attackCount[i][0];
					numAttacks += attackCount[i][1];
				}
				attackEffective[i] = Util.isGen1() ? DamageCalc.isEffective(true, i) : DamageCalc.getEffectiveness(true, i) != 10;
				maxAttackDamage = Math.max(maxAttackDamage, attackDmg[i][0]);
				maxAttackDamage = Math.max(maxAttackDamage, attackDmg[i][1]);
			}
		}
		
		numTurns = numAttacks;
		
		// fix enemyMoveDesc array length
		int numEnemyAttacks = faster ? numAttacks - 1 : numAttacks;
		if(numEnemyAttacks > 0 && enemyMoveDesc.length > numEnemyAttacks)
			System.out.println("WARNING: enemyMoveDesc contains more elements than used");

		EnemyMoveDesc[] tmp = new EnemyMoveDesc[numEnemyAttacks];
		for(int i=0;i<numEnemyAttacks;i++)
			tmp[i] = enemyMoveDesc[Math.min(i, enemyMoveDesc.length-1)];
		enemyMoveDesc = tmp;
		
		// calc min enemy damage
		int sumEnemyDamage = 0;
		for(int i=0; i<numEnemyAttacks; i++) {
			if(enemyMoveDesc[i].noDamage)
				continue;
			if(enemyMoveDesc[i].move.length != 1) {
				System.err.println("At enemy move "+i+": wildcard move can't hit");
				continue;
			}
			int moveIndex = getEnemyMoveIndex(enemyMoveDesc[i].move[0]);
			sumEnemyDamage += enemyDmg[moveIndex][0];
		}

		// sanity checks
		if(sumDamage < enemyHP) System.err.println("ERROR: sum damage "+sumDamage+" won't kill mon with HP "+enemyHP);
		if(sumEnemyDamage > maxOwnDamage) System.err.println("ERROR: min sum enemy damage "+sumEnemyDamage+" greater than maxOwnDamage "+maxOwnDamage);
		if(rageInitialVal == 0 && maxAttackDamage*(numAttacks-1) >= enemyHP) System.out.println("WARNING: non-optimal attacks chosen, can kill with fewer moves");

		// calc spare damage
		int spareDamage = sumDamage - enemyHP;
		int spareEnemyDamage = maxOwnDamage - sumEnemyDamage;
		System.out.println("applying "+numAttacks+" attacks with "+sumDamage+" total damage on "+enemyHP+" HP, having "+spareDamage+" spare damage");
		System.out.println("Enemy applying "+numEnemyAttacks+" attacks with "+sumEnemyDamage+" total damage on "+maxOwnDamage+" maxOwnDamage, having "+spareEnemyDamage+" spare damage");

		// init StateBuffers
		bufs = new HashMap[numAttacks];
		for(int i=0;i<numAttacks;i++)
			bufs[i] = new HashMap<FightState, StateBuffer>();
		goalBuf = new StateBuffer();
		
		getBuffer(new FightState(attackCount, spareDamage, spareEnemyDamage),bufs[numAttacks-1]).addAll(in); // add initial states
		
		// simulate turns
		for(int n=numAttacks-1;n>=0;n--) {
			System.out.println("with "+(n+1)+" remaining turns, there are "+bufs[n].size()+" different FightStates:");
			for(Entry<FightState, StateBuffer> e : bufs[n].entrySet())
				System.out.println("  "+e.getKey()+" - "+e.getValue());
			for(Entry<FightState, StateBuffer> e : bufs[n].entrySet()) {
				FightState fs = e.getKey();
				System.out.println("FightState loaded: "+fs);
				for(int ai = 0; ai < 4; ai++)
					for(int ac = 0; ac < 2; ac++) {
						if(ac == 1 && nonCritsFirst && fs.atkCnt[0][0]+fs.atkCnt[1][0]+fs.atkCnt[2][0]+fs.atkCnt[3][0] > 0) {
							System.out.println("SKIP CRIT FOR NONCRITS!");
							continue;
						}
						if (ai == lastAttack) {
							boolean found = false;
							for (int ii = 0; ii < 4; ii++)
								if (ii != ai && fs.atkCnt[ii][0]+fs.atkCnt[ii][1] > 0)
									found = true;
							if (found)
								continue;
						}
						if(fs.atkCnt[ai][ac] > 0) { // for every attack yet to go
							if(rageInitialVal > 0 && ac == 1) {
								fs.spareDamage -= (attackDmg[ai][1] - attackDmg[ai][0])*fs.atkCnt[ai][0];
							}
							if(fs.spareDamage >= 0) {
								StateBuffer sb = e.getValue();
								if(!skipFirstMainBattleMenu || n != numAttacks-1) {
									sb = new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)).execute(sb); // select "FIGHT"
									sb = new MoveSegment(new SelectMoveInList(ai,numOwnMoves)).execute(sb);
								}
								executeTurn(fs,sb, n, ai, ac);
							}
							if(rageInitialVal > 0 && ac == 1) {
								fs.spareDamage += (attackDmg[ai][1] - attackDmg[ai][0])*fs.atkCnt[ai][0];
							}
						}
					}
				e.setValue(null); // free memory
			}
		}
		
		StateBuffer ret = goalBuf;
		goalBuf = null; // free memory
		bufs = null;
		return ret;
	}

	private int getEnemyMoveIndex(int move) {
		int moveIndex = -1;
		for(int j=0;j<4;j++)
			if(enemyMove[j] == move)
				moveIndex = j;
		if(moveIndex == -1)
			System.err.println("Can't find enemy move "+move+" in move list");
		return moveIndex;
	}

	private StateBuffer getBuffer(FightState p, HashMap<FightState, StateBuffer> posBuffers) {
		if(!posBuffers.containsKey(p))
			posBuffers.put(p, new StateBuffer());
		return posBuffers.get(p);
	}
	
	private AttackActionSegment getEnemyMoveSegment(int curTurn) {
		return curTurn < enemyMoveDesc.length ? enemyMoveDesc[curTurn].segment : new MissMetricSegment(false);
	}
	private int[] getEnemyMove(int curTurn) {
		return curTurn < enemyMoveDesc.length ? enemyMoveDesc[curTurn].move : new int[0];
	}
	private boolean isEnemyMoveNoDamage(int curTurn) {
		return curTurn < enemyMoveDesc.length ? enemyMoveDesc[curTurn].noDamage : true;
	}
	private int getNumEndOfTurnTexts(int curTurn) {
		return curTurn < numEndOfTurnTexts.length ? numEndOfTurnTexts[curTurn] : 0;
	}
	private int getNumEndOfAttackTexts(int curTurn) {
		return curTurn < numEndOfAttackTexts.length ? numEndOfAttackTexts[curTurn] : 0;
	}
	
	private void executeTurn(FightState fs, StateBuffer in, int n, int ai, int ac) {
		final int curTurn = numTurns - 1 - n;

		System.out.println("executeTurn "+curTurn+" using attack "+ai+":"+ac+" in FightState "+fs+" frame "+in.getStates().iterator().next().stepCount);

		// determine turn type
		final boolean playerCrit = (ac == 1);
		final boolean playerEffective = attackEffective[ai];
		final boolean lastTurn = (n==0);
		final boolean pauseAfterPlayerAttack = playerEffective || playerCrit;
		final boolean pauseAfterEnemyAttack = getEnemyMoveSegment(curTurn).getFinishMove() != null;
		final boolean separateAttacks = (faster && pauseAfterPlayerAttack) || (faster && lastTurn) || (!faster && pauseAfterEnemyAttack);
		
		int sumCrits = 0;
		for(int i=0;i<4;i++)
			sumCrits += fs.atkCnt[i][1];
		final boolean playerForgoMaxCrit = (MAX_SKIPS < 0) && (sumCrits <= fs.spareDamage);
		
		final AttackActionSegment curEnemyMoveSegment = getEnemyMoveSegment(curTurn);
		setAppendEnemyMoveMetric(curEnemyMoveSegment, curTurn, faster && !pauseAfterEnemyAttack && getNumEndOfTurnTexts(curTurn) == 0);
		final int[] curEnemyMove = getEnemyMove(curTurn);
		final int curEnemyMoveMinDamage = (curEnemyMove.length  == 0) ? 0 : enemyDmg[getEnemyMoveIndex(curEnemyMove[0])][0];
		
		final AttackActionSegment curPlayerMoveSegment = new HitMetricSegment(playerCrit, false, playerEffective, true, getNumEndOfAttackTexts(curTurn), getNumEndOfAttackTexts(curTurn) > 0, false, n == 0 ? thrashNumAdditionalTurns : 0);
		setAppendEnemyMoveMetric(curPlayerMoveSegment, curTurn, !faster && !pauseAfterPlayerAttack && !lastTurn && getNumEndOfTurnTexts(curTurn) == 0);
		setAppendNoAIMoveMetric(curPlayerMoveSegment, faster && !pauseAfterPlayerAttack && !lastTurn);
		
		// init damage values
		int goalEnemyDamage = -curEnemyMoveMinDamage;
		int minEnemyDamage = goalEnemyDamage - fs.spareEnemyDamage;
		if(lastTurn)
			goalEnemyDamage = minEnemyDamage;
		if(isEnemyMoveNoDamage(curTurn))
			goalEnemyDamage = minEnemyDamage = 0;
		int maxPlayerDamage = attackDmg[ai][ac];
		if(rageInitialVal > 0)
			maxPlayerDamage *= Math.min(curTurn+rageInitialVal, rageMaxVal > 0 ? rageMaxVal : Integer.MAX_VALUE);
		int goalPlayerDamage = maxPlayerDamage;
		int minPlayerDamage = goalPlayerDamage - fs.spareDamage;
		if(lastTurn)
			goalPlayerDamage -= fs.spareDamage; // if last turn, just killing it suffices
		else if(playerForgoMaxCrit) {
			System.out.println("executeTurn: forgoing max crit!");
			minPlayerDamage+= sumCrits; // save 1 damage for each crit
			if(playerCrit) {
				goalPlayerDamage--; // don't require max crit
				minPlayerDamage--; // 1 damage was saved for us!
			}
		}

		
		System.out.println("executeTurn goal "+new PII(goalPlayerDamage,goalEnemyDamage)+" min "+new PII(minPlayerDamage, minEnemyDamage));

		// clear damage attributes
		for(State s : in.getStates()) {
			s.setAttributeInt(PLAYER_ATTRIBUTE, Integer.MIN_VALUE);
			s.setAttributeInt(ENEMY_ATTRIBUTE, Integer.MIN_VALUE);
		}
		
		final Map<PII,StateBuffer> im = new HashMap<PII, StateBuffer>();
		DelayableMoveFactory initialMoveFactory = new DelayUntilFactory(new PressButtonFactory(Move.A, Metric.PRESSED_JOY), new CheckMoveOrderMetric(faster,curEnemyMove,Move.A));

		DelayableMoveFactory moveFactory = initialMoveFactory;

		// attacks handled separately
		if(separateAttacks) {
			Map<PII,StateBuffer> enemyIm;
			if(faster)
				System.out.println("handle enemy move second and separately");
			else
				System.out.println("handle enemy move first and separately");

			if(!faster) {
				enemyIm = executeSingleAttack(in, moveFactory, curEnemyMoveSegment, goalEnemyDamage, minEnemyDamage, false, true);
				moveFactory = curEnemyMoveSegment.getFinishMove();
			} else {
				enemyIm = new HashMap<PII, StateBuffer>();
				enemyIm.put(new PII(Integer.MIN_VALUE, goalEnemyDamage), in);
			}
			DelayableMoveFactory tmpFactory = moveFactory;
			for(Entry<PII,StateBuffer> e : enemyIm.entrySet()) {
				Map<PII,StateBuffer> playerIm = executeSingleAttack(e.getValue(), tmpFactory, curPlayerMoveSegment, goalPlayerDamage, minPlayerDamage, false, false);
				moveFactory = curPlayerMoveSegment.getFinishMove();

				if(!faster || lastTurn) {
					im.putAll(playerIm);
				} else {
					moveFactory = new DelayUntilFactory(moveFactory, new CheckNoAIMove(Move.B));
					for(Entry<PII,StateBuffer> e2 : playerIm.entrySet())
						im.putAll(executeSingleAttack(e2.getValue(), moveFactory, curEnemyMoveSegment, goalEnemyDamage, minEnemyDamage, false, true));
					moveFactory = curEnemyMoveSegment.getFinishMove();
				}
			}
		} else { // attacks handled simultaneously
			im.putAll(executeAttack(in, initialMoveFactory, curPlayerMoveSegment, curEnemyMoveSegment, goalPlayerDamage, minPlayerDamage, goalEnemyDamage, minEnemyDamage, false, false, !faster));
			if(faster)
				moveFactory = curEnemyMoveSegment.getFinishMove();
			else
				moveFactory = curPlayerMoveSegment.getFinishMove();
		}

		// handle last turn
		if(lastTurn) for(Entry<PII, StateBuffer> e : im.entrySet()) {
			StateBuffer sb = e.getValue();
			if(moveFactory != null)
				sb = new MoveSegment(moveFactory.create()).execute(sb);
			sb = new SkipTextsSegment(numExpGainers).execute(sb); // enemy mon fainted + mon gained xp (-1)
			sb = new TextSegment(Move.A).execute(sb);
			if (thrashNumAdditionalTurns == 0) {
				sb = new DelayMoveSegment(new PressButtonFactory(Move.B), new CheckMetricSegment(new Metric() {
					@Override
					public int getMetric() { // check for next mon
						if(nextMonSpecies > 0 || nextMonLevel > 0) {
							State s = new State();
							Util.runToAddress(0, 0, RomInfo.rom.printLetterDelayJoypadAddress);
							int mon = Gb.readMemory(RomInfo.rom.fightEnemyMonSpeciesAddress);
							int level = Gb.readMemory(RomInfo.rom.fightEnemyMonLevelAddress);
							s.restore();
							if(nextMonSpecies > 0 && nextMonSpecies != mon)
								return 0;
							if(nextMonLevel > 0 && nextMonLevel != level)
								return 0;
						}
						return 1;
					}
				})).execute(sb);
			}
			goalBuf.addAll(sb);
			return;
		}

		// sort resulting states in respective buckets
		for(Entry<PII, StateBuffer> e : im.entrySet()) {
			int playerDamage = e.getKey().a;
			int enemyDamage = e.getKey().b;
			StateBuffer imm = e.getValue();
			
			// handle end of turn texts
			if(getNumEndOfTurnTexts(curTurn) > 0) {
				System.out.println("executing "+getNumEndOfTurnTexts(curTurn)+" end of turn texts!");
				if(moveFactory != null)
					imm = new MoveSegment(moveFactory.create()).execute(imm);
				imm = new TextSegment().execute(imm);
				for(int i=1;i <getNumEndOfTurnTexts(curTurn); i++) {
					imm = new MoveSegment(new PressButton(Move.B)).execute(imm);
					imm = new TextSegment().execute(imm);
				}
				moveFactory = new PressButtonFactory(Move.B);
			}
			
			// handle remaining finishing move, ensuring next enemy move
			if(moveFactory != null) {
				if (Util.isGen1()) {
					imm = new MoveSegment(moveFactory.create()).execute(imm);
				} else {
					Move move = moveFactory.create();
					move = new DelayUntil(new WithMetric(move, true, CheckEnemyMoveMetric.withKeys(move.getInitialKey(), getEnemyMove(curTurn+1))));
					imm = new MoveSegment(move).execute(imm);
				}
			}

			// save states to buffer
			int newSpareDamage = fs.spareDamage - (maxPlayerDamage - playerDamage);
			int newSpareEnemyDamage = fs.spareEnemyDamage - ((-curEnemyMoveMinDamage) - enemyDamage);
			getBuffer(new FightState(fs.atkCnt, newSpareDamage, newSpareEnemyDamage, ai, ac), bufs[n-1]).addAll(imm);
		}
	}
	
	private void setAppendEnemyMoveMetric(AttackActionSegment ems, int curTurn, boolean set) {
		if (Util.isGen1())
			ems.appendSegment = null;
		else
			ems.appendSegment = set ? new CheckMetricSegment(CheckEnemyMoveMetric.noKeys(getEnemyMove(curTurn+1))) : null;
	}
	
	private void setAppendNoAIMoveMetric(AttackActionSegment ems, boolean set) {
		if (Util.isGen1())
			ems.appendSegment = set ? new CheckMetricSegment(new CheckNoAIMove(0)) : null;
		else
			ems.appendSegment = null;
	}

	public void printInfo(StateBuffer in) {
		if(in.size() == 0) {
			System.out.println("StateBuffer EMPTY!");
			return;
		}
		in.getStates().iterator().next().restore(); // restore some state
		Util.runToNextInputFrame(); // skip to menu, ensuring mons are loaded
		
		int enemyHP = Util.getMemoryBigEndian(RomInfo.rom.fightEnemyMonHPAddress);
		int playerHP = Util.getMemoryBigEndian(RomInfo.rom.fightBattleMonHPAddress);

		System.out.println("Enemy HP: "+enemyHP);
		System.out.print("Enemy stats:");
		for(int i=0;i<5;i++) {
			int stat = DamageCalc.getStat(false, i, false);
			int ostat = DamageCalc.getStat(false, i, true);
			System.out.print(" " + stat);
			if(ostat != stat)
				System.out.print("("+ostat+")");
		}
		System.out.println();
		for(int i=0;i<4;i++)
			DamageCalc.printMoveDebugInfo(false, i);

		System.out.println();

		System.out.println("Own HP: "+playerHP);
		System.out.print("Own stats:");
		for(int i=0;i<5;i++) {
			int stat = DamageCalc.getStat(true, i, false);
			int ostat = DamageCalc.getStat(true, i, true);
			System.out.print(" " + stat);
			if(ostat != stat)
				System.out.print("("+ostat+")");
		}
		System.out.println();
		for(int i=0;i<4;i++)
			DamageCalc.printMoveDebugInfo(true, i);
	}
	
	
	public Map<PII,StateBuffer> executeSingleAttack(StateBuffer in, DelayableMoveFactory moveFactory, AttackActionSegment actionSegment, int goalValue, int minValue, boolean noBins, boolean isB) {
		AttackActionSegment dummyActionSegment = null;
		AttackActionSegment aActionSegment = isB ? dummyActionSegment : actionSegment;
		AttackActionSegment bActionSegment = isB ? actionSegment : dummyActionSegment;
		int goalAValue = isB ? Integer.MIN_VALUE : goalValue;
		int goalBValue = isB ? goalValue : Integer.MIN_VALUE;
		int minAValue = isB ? Integer.MIN_VALUE : minValue;
		int minBValue = isB ? minValue : Integer.MIN_VALUE;
		boolean noABins = isB ? false : noBins;
		boolean noBBins = isB ? noBins : false;
		return executeAttack(in, moveFactory, aActionSegment, bActionSegment, goalAValue, minAValue, goalBValue, minBValue, noABins, noBBins, isB);
	}
	
	
	public static final String PLAYER_ATTRIBUTE = "playerDamage";
	public static final String ENEMY_ATTRIBUTE = "enemyDamage";
	
	public Map<PII,StateBuffer> executeAttack(StateBuffer in, DelayableMoveFactory moveFactory, AttackActionSegment aActionSegment, AttackActionSegment bActionSegment, int goalAValue, int minAValue, int goalBValue, int minBValue, boolean noABins, boolean noBBins, boolean bFirst) {
		Map<PII,StateBuffer> im = new HashMap<PII,StateBuffer>();
		Map<PII,Integer> imActiveFrame = new HashMap<PII,Integer>();
		
		System.out.println("executeAttack goal "+new PII(goalAValue,goalBValue)+" min "+new PII(minAValue, minBValue));

		boolean[] active = new boolean[in.size()];
		Move[] dus = new Move[in.size()];
		int numActive = in.size();
		for(int cs = 0; cs < in.size(); cs++) {
			active[cs] = true;
			dus[cs] = moveFactory.create();
		}
		
		int skips = -1;
		while(numActive > 0) {
			skips++;
			System.out.println("executeAttack: processing "+numActive+" active states at "+skips+" skips");
			int cs = -1;
			for(State s : in.getStates()) {
				cs++;
				if(!active[cs])
					continue;
				if(MAX_SKIPS >= 0 && skips > MAX_SKIPS) {
					System.out.println("reached MAX_SKIPS limit!");
					active[cs] = false;
					numActive--;
					continue;
				}
				
				s.restore();
				dus[cs].prepare(skips,true);
				dus[cs].doMove();
								
				int curActiveFrame = State.currentStepCount;

				StateBuffer sb = new StateBuffer();
				sb.addState(new State());
				sb = bFirst ? 
						executeAttackInternal(sb, curActiveFrame, goalAValue, minBValue, imActiveFrame, bActionSegment, goalBValue, false):
						executeAttackInternal(sb, curActiveFrame, minAValue, goalBValue, imActiveFrame, aActionSegment, goalAValue, true);
				if(sb == null) {
					active[cs] = false;
					numActive--;
					continue;
				}

				Map<Integer,StateBuffer> imFirst = split(sb, bFirst ? ENEMY_ATTRIBUTE : PLAYER_ATTRIBUTE);
				
				for(Entry<Integer,StateBuffer> eFirst : imFirst.entrySet()) {
					int firstValue = eFirst.getKey();
					sb = bFirst ?
							executeAttackInternal(eFirst.getValue(), curActiveFrame, minAValue, firstValue, imActiveFrame, aActionSegment, goalAValue, true):
							executeAttackInternal(eFirst.getValue(), curActiveFrame, firstValue, minBValue, imActiveFrame, bActionSegment, goalBValue, false);
					if(sb == null) {
						System.err.println("WARNING: executeAttack: That shouldn't be possible: firstValue "+firstValue+" gave no valid secondValues");
						continue;
					}
					Map<Integer,StateBuffer> imSecond = split(sb, bFirst ? PLAYER_ATTRIBUTE : ENEMY_ATTRIBUTE);
					for(Entry<Integer,StateBuffer> eSecond : imSecond.entrySet()) {
						int secondValue = eSecond.getKey();
						int aValue = bFirst ? secondValue : firstValue;
						int bValue = bFirst ? firstValue : secondValue;
						PII value = new PII(aValue,bValue);
						int aBin = noABins ? 0 : aValue;
						int bBin = noBBins ? 0 : bValue;
						PII bin = new PII(aBin,bBin);
						
						if(!im.containsKey(bin))
							im.put(bin, new StateBuffer());
						im.get(bin).addAll(eSecond.getValue()); // add states to im buffer
						System.out.println("add states to im: value: "+value+","+bin+"; size: "+im.get(bin).size());
						System.out.println("imActiveFrame: "+imActiveFrame);
						if(im.get(bin).isFull()) {
							if(!imActiveFrame.containsKey(value) || imActiveFrame.get(value) > curActiveFrame+FULL_BUFFER_CUTOFF_DELAY) {
								imActiveFrame.put(value, curActiveFrame+FULL_BUFFER_CUTOFF_DELAY);
								System.out.println("setting limit for value="+value+" to "+(curActiveFrame+FULL_BUFFER_CUTOFF_DELAY)+" (full)");
							}
						}
						if(im.get(bin).size() >= 1) {
							if(!imActiveFrame.containsKey(value) || imActiveFrame.get(value) > curActiveFrame+NONEMPTY_BUFFER_CUTOFF_DELAY) {
								imActiveFrame.put(value, curActiveFrame+NONEMPTY_BUFFER_CUTOFF_DELAY);
								System.out.println("setting limit for value="+value+" to "+(curActiveFrame+NONEMPTY_BUFFER_CUTOFF_DELAY)+" (nonempty)");
							}
						}
					}
				}
			}
		}
		return im;
	}

	private StateBuffer executeAttackInternal(StateBuffer sb, int curActiveFrame, int minAValue, int minBValue, Map<PII,Integer> imActiveFrame, AttackActionSegment actionSegment, int goalValue, boolean isA) {
		if(actionSegment == null) // dummy, don't execute
			return sb;
		
		int curMinValueNeeded = calcMinValueNeeded(curActiveFrame,minAValue,minBValue,imActiveFrame,isA);
		
//		System.out.println("executeAttackInternal curMinValueNeeded "+curMinValueNeeded+", goalValue "+goalValue);

		if(curMinValueNeeded > goalValue) {
			System.out.println("executeAttack: interrupting search: "+(isA?"A":"B")+" value "+curMinValueNeeded+" over limit "+goalValue);
			return null;
		}
		
		return actionSegment.execute(sb, curMinValueNeeded);
	}
	
	private Map<Integer,StateBuffer> split(StateBuffer in, String splitAttribute) {
		Map<Integer,StateBuffer> ret = new HashMap<Integer, StateBuffer>();
		for(State s : in.getStates()) {
			int val = s.getAttributeInt(splitAttribute);
			if(!ret.containsKey(val))
				ret.put(val, new StateBuffer(in.maxBufferSize));
			ret.get(val).addState(s);
		}
		return ret;
	}
	
	private static int calcMinValueNeeded(int curActiveFrame,int minAValueNeeded,int minBValueNeeded, Map<PII, Integer> imActiveFrame, boolean optimizeA) {
		int minValueNeeded = (optimizeA ? minAValueNeeded : minBValueNeeded);
		for(Entry<PII, Integer> e : imActiveFrame.entrySet())
			if(curActiveFrame > e.getValue() && (optimizeA ? e.getKey().b >= minBValueNeeded : e.getKey().a >= minAValueNeeded))
				minValueNeeded = Math.max(minValueNeeded, (optimizeA ? e.getKey().a : e.getKey().b) + 1);
		return minValueNeeded;
	}
}
