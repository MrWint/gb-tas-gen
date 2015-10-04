package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BITE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FISSURE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HORN_DRILL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.QUICK_ATTACK;
import static mrwint.gbtasgen.state.Gameboy.curGb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMove;
import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.EflSelectMoveInList;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.EflDelayMoveSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.EflUtil.PressMetric;
import mrwint.gbtasgen.util.PII;
import mrwint.gbtasgen.util.Util;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;
import mrwint.gbtasgen.util.pokemon.fight.DamageCalc;




public class EflKillEnemyMonSegment implements Segment {

	public static final int MAX_SKIPS = -1;

	public static final int FULL_BUFFER_CUTOFF_DELAY = 0;
	public static final int NONEMPTY_BUFFER_CUTOFF_DELAY = 0;


	public static class CheckEnemyMoveMetric implements StateResettingMetric {
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
		public int getMetricInternal() {
			Util.runToAddressNoLimit(0, keys, curGb.pokemon.fightAfterAIChooseMove);
			int enemyMove = curGb.readMemory(curGb.pokemon.fightCurEnemyMoveAddress); // selected enemy move
			System.out.println("found enemy move "+enemyMove);
			return (((move.length == 0 && enemyMove != 98/*Quick Attack*/) || Util.arrayContains(move,enemyMove)) ? 1 : 0);
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

	public static class EflEnemyMoveDesc {
		public int[] move;
		public EflAttackActionSegment segment;
		public boolean noDamage;
		public EflEnemyMoveDesc(EflAttackActionSegment segment, boolean noDamage, int[] move) {
			this.move = move;
			this.segment = segment;
			this.noDamage = noDamage;
		}
		public static EflEnemyMoveDesc missWith(int... move) {
			return new EflEnemyMoveDesc(new EflMissMetricSegment(false), true, move);
		}
		public static EflEnemyMoveDesc missWith(Metric metric, int... move) {
			return new EflEnemyMoveDesc(new EflMissMetricSegment(false, metric), true, move);
		}
		public static EflEnemyMoveDesc hitWith(int move, boolean isCrit, boolean isEffective) {
			return new EflEnemyMoveDesc(new EflHitMetricSegment(isCrit, false, isEffective, false, 0, false), false, new int[]{move});
		}
    public static EflEnemyMoveDesc hitWith(int move, boolean isCrit, boolean isEffective, int additionalTexts) {
      return new EflEnemyMoveDesc(new EflHitMetricSegment(isCrit, false, isEffective, false, additionalTexts, false), false, new int[]{move});
    }
    public static EflEnemyMoveDesc customHitWith(int move, boolean isCrit, boolean isEffective, int minDamage, int maxDamage) {
      return new EflEnemyMoveDesc(new EflCustomHitMetricSegment(false, isCrit, false, isEffective, minDamage, maxDamage, false), true, new int[]{move});
    }
	}
	public EflEnemyMoveDesc[] enemyMoveDesc = {EflEnemyMoveDesc.missWith()};
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
	private boolean spdFaster;						// own mon can be faster than enemy mon

	private int[][] enemyDmg = new int[4][2];	// max damage a noncrit/crit can do
	private int[] enemyMove = new int[4];		// move ID

	private HashMap<FightState, StateBuffer>[] bufs;
	private StateBuffer goalBuf;
	private int numTurns;

	public EflKillEnemyMonSegment() {
    EflUtil.assertEfl();
  }

	@SuppressWarnings("unchecked")
	@Override
	public StateBuffer execute(StateBuffer in) {
		printInfo(in);

		int enemySpeed = DamageCalc.getStat(false, DamageCalc.STAT_SPD, false);
		int playerSpeed = DamageCalc.getStat(true, DamageCalc.STAT_SPD, false);

		if(enemySpeed > playerSpeed) {
			System.out.println("enemy is faster ("+playerSpeed+" to "+enemySpeed+")");
			spdFaster = false;
		} else {
			System.out.println("player is faster ("+playerSpeed+" to "+enemySpeed+")");
			spdFaster = true;
		}

		if(onlyPrintInfo)
			return in;

		int enemyHP = Util.getMemoryWordBE(curGb.pokemon.fightEnemyMonHPAddress);
		int ownHP = Util.getMemoryWordBE(curGb.pokemon.fightBattleMonHPAddress);

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

			if (spdFaster && (DamageCalc.getMove(true, i) == FISSURE || DamageCalc.getMove(true, i) == HORN_DRILL)) { // adjust for OHKO
			  attackDmg[i][0] = attackDmg[i][1] = enemyHP;
			}

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
				attackEffective[i] = PokemonUtil.isGen1() ? DamageCalc.isEffective(true, i) : DamageCalc.getEffectiveness(true, i) != 10;
				maxAttackDamage = Math.max(maxAttackDamage, attackDmg[i][0]);
				maxAttackDamage = Math.max(maxAttackDamage, attackDmg[i][1]);
			}
		}

		numTurns = numAttacks;

		// fix enemyMoveDesc array length
		int numEnemyAttacks = spdFaster ? numAttacks - 1 : numAttacks;
		if(numEnemyAttacks > 0 && enemyMoveDesc.length > numEnemyAttacks)
			System.out.println("WARNING: enemyMoveDesc contains more elements than used");

		EflEnemyMoveDesc[] tmp = new EflEnemyMoveDesc[numEnemyAttacks];
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
									sb = new MoveSegment(new EflPressButton(Move.A, PressMetric.PRESSED), 0).execute(sb); // select "FIGHT"
									sb = new MoveSegment(new EflSelectMoveInList(ai,numOwnMoves), 0).execute(sb);
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

	private EflAttackActionSegment getEnemyMoveSegment(int curTurn) {
		return curTurn < enemyMoveDesc.length ? enemyMoveDesc[curTurn].segment : new EflMissMetricSegment(false);
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
		final boolean faster = spdFaster || DamageCalc.getMove(true, ai) == QUICK_ATTACK;
		final boolean playerCrit = (ac == 1);
		final boolean playerEffective = attackEffective[ai];
		final boolean lastTurn = (n==0);
    final boolean makeEnemyFlinch = PokemonUtil.isGen1() && faster && DamageCalc.getMove(true, ai) == BITE;
		final boolean pauseAfterPlayerAttack = playerEffective || playerCrit;
		final boolean pauseAfterEnemyAttack = getEnemyMoveSegment(curTurn).getFinishSegment() != null || makeEnemyFlinch;
		final boolean separateAttacks = (faster && pauseAfterPlayerAttack) || (faster && lastTurn) || (!faster && pauseAfterEnemyAttack);

		int sumCrits = 0;
		for(int i=0;i<4;i++)
			sumCrits += fs.atkCnt[i][1];
		final boolean playerForgoMaxCrit = (MAX_SKIPS < 0) && (sumCrits <= fs.spareDamage);

    final EflAttackActionSegment curEnemyMoveSegment = makeEnemyFlinch ? new EflEnemyFlinchSegment() : getEnemyMoveSegment(curTurn);
		setAppendEnemyMoveMetric(curEnemyMoveSegment, curTurn, faster && !pauseAfterEnemyAttack && getNumEndOfTurnTexts(curTurn) == 0);
		final int[] curEnemyMove = getEnemyMove(curTurn);
		final int curEnemyMoveMinDamage = (curEnemyMove.length  == 0) ? 0 : enemyDmg[getEnemyMoveIndex(curEnemyMove[0])][0];

		final EflAttackActionSegment curPlayerMoveSegment = new EflHitMetricSegment(playerCrit, false, playerEffective, true, getNumEndOfAttackTexts(curTurn), getNumEndOfAttackTexts(curTurn) > 0, n == 0 ? thrashNumAdditionalTurns : 0);
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
		Segment initialSegment = new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButtonUnboundedNoDelay(Move.A, PressMetric.PRESSED);
        seqMetric(new EflCheckMoveOrderMetric(faster, curEnemyMove));
      }
    };

    Segment moveSegment = initialSegment;

		// attacks handled separately
		if(separateAttacks) {
			Map<PII,StateBuffer> enemyIm;
			if(faster)
				System.out.println("handle enemy move second and separately");
			else
				System.out.println("handle enemy move first and separately");

			if(!faster) {
				enemyIm = executeSingleAttack(in, moveSegment, curEnemyMoveSegment, goalEnemyDamage, minEnemyDamage, false, true);
				moveSegment = curEnemyMoveSegment.getFinishSegment();
			} else {
				enemyIm = new HashMap<PII, StateBuffer>();
				enemyIm.put(new PII(Integer.MIN_VALUE, goalEnemyDamage), in);
			}
			Segment tmpSegment = moveSegment;
			for(Entry<PII,StateBuffer> e : enemyIm.entrySet()) {
				Map<PII,StateBuffer> playerIm = executeSingleAttack(e.getValue(), tmpSegment, curPlayerMoveSegment, goalPlayerDamage, minPlayerDamage, false, false);
				moveSegment = curPlayerMoveSegment.getFinishSegment();

				if(!faster || lastTurn) {
					im.putAll(playerIm);
				} else {
	        final Segment tmpMoveSegment = moveSegment;
				  moveSegment = new SeqSegment() {
            @Override
            protected void execute() {
              seq(tmpMoveSegment);
              seqMetric(new CheckNoAIMove(0));
            }
          };
					for(Entry<PII,StateBuffer> e2 : playerIm.entrySet())
						im.putAll(executeSingleAttack(e2.getValue(), moveSegment, curEnemyMoveSegment, goalEnemyDamage, minEnemyDamage, false, true));
					moveSegment = curEnemyMoveSegment.getFinishSegment();
				}
			}
		} else { // attacks handled simultaneously
			im.putAll(executeAttack(in, initialSegment, curPlayerMoveSegment, curEnemyMoveSegment, goalPlayerDamage, minPlayerDamage, goalEnemyDamage, minEnemyDamage, false, false, !faster));
			if(faster)
			  moveSegment = curEnemyMoveSegment.getFinishSegment();
			else
			  moveSegment = curPlayerMoveSegment.getFinishSegment();
		}

		// handle last turn
		if(lastTurn) for(Entry<PII, StateBuffer> e : im.entrySet()) {
			StateBuffer sb = e.getValue();
			if(moveSegment != null)
				sb = moveSegment.execute(sb);
			sb = new EflSkipTextsSegment(numExpGainers).execute(sb); // enemy mon fainted + mon gained xp (-1)
			sb = new EflTextSegment(Move.A).execute(sb);
			if (thrashNumAdditionalTurns == 0) {
			  sb = new EflDelayMoveSegment(new SeqSegment() {
          @Override
          protected void execute() {
            seqEflButton(Move.B);
            seqMetric(new Metric() {
              @Override
              public int getMetric() { // check for next mon
                if(nextMonSpecies > 0 || nextMonLevel > 0) {
                  State s = curGb.newState();
                  EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.printLetterDelayJoypadAddress);
                  int mon = curGb.readMemory(curGb.pokemon.fightEnemyMonSpeciesAddress);
                  int level = curGb.readMemory(curGb.pokemon.fightEnemyMonLevelAddress);
                  curGb.restore(s);
                  if(nextMonSpecies > 0 && nextMonSpecies != mon)
                    return 0;
                  if(nextMonLevel > 0 && nextMonLevel != level)
                    return 0;
                }
                return 1;
              }
            });
          }
        }).execute(sb);
			}
			goalBuf.addAll(sb);
			return;
		}

		// sort resulting states in respective buckets
		Segment tmpSegment = moveSegment;
		for(Entry<PII, StateBuffer> e : im.entrySet()) {
		  moveSegment = tmpSegment;
			int playerDamage = e.getKey().a;
			int enemyDamage = e.getKey().b;
			StateBuffer imm = e.getValue();

			// handle end of turn texts
			if(getNumEndOfTurnTexts(curTurn) > 0) {
				System.out.println("executing "+getNumEndOfTurnTexts(curTurn)+" end of turn texts!");
				if(moveSegment != null)
					imm = new EflDelayMoveSegment(moveSegment).execute(imm);
				imm = new EflTextSegment().execute(imm);
				for(int i=1;i <getNumEndOfTurnTexts(curTurn); i++) {
					imm = new MoveSegment(new EflPressButton(Move.B)).execute(imm);
					imm = new EflTextSegment().execute(imm);
				}
				moveSegment = new SeqSegment() {
	        @Override
	        protected void execute() {
	          seqEflButtonUnboundedNoDelay(Move.B);
	        }
	      };
			}

			// handle remaining finishing move, ensuring next enemy move
			if(moveSegment != null) {
				if (PokemonUtil.isGen1()) {
					imm = new EflDelayMoveSegment(moveSegment).execute(imm);
				} else {
	        final Segment tmpMoveSegment = moveSegment;
				  imm = new EflDelayMoveSegment(new SeqSegment() {
            @Override
            protected void execute() {
              seq(tmpMoveSegment);
              seqMetric(CheckEnemyMoveMetric.noKeys(getEnemyMove(curTurn+1)));
            }
          }).execute(imm);
				}
			}

      if (imm.size() == 0)
        throw new RuntimeException();

			// save states to buffer
			int newSpareDamage = fs.spareDamage - (maxPlayerDamage - playerDamage);
			int newSpareEnemyDamage = fs.spareEnemyDamage - ((-curEnemyMoveMinDamage) - enemyDamage);
			getBuffer(new FightState(fs.atkCnt, newSpareDamage, newSpareEnemyDamage, ai, ac), bufs[n-1]).addAll(imm);
		}
	}

	private void setAppendEnemyMoveMetric(EflAttackActionSegment aas, int curTurn, boolean set) {
		if (set && !PokemonUtil.isGen1()) {
      final Segment curSuffixSegment = aas.suffixSegment;
		  aas.suffixSegment = new SeqSegment() {
        @Override
        protected void execute() {
          seq(curSuffixSegment);
          seq(new CheckMetricSegment(CheckEnemyMoveMetric.noKeys(getEnemyMove(curTurn+1))));
        }
      };
		}
	}

	private void setAppendNoAIMoveMetric(EflAttackActionSegment aas, boolean set) {
		if (set && PokemonUtil.isGen1()) {
		  final Segment curSuffixSegment = aas.suffixSegment;
      aas.suffixSegment = new SeqSegment() {
        @Override
        protected void execute() {
          seq(curSuffixSegment);
          seq(new CheckMetricSegment(new CheckNoAIMove(0)));
        }
      };
		}
	}

	public void printInfo(StateBuffer in) {
		if(in.size() == 0) {
			System.out.println("StateBuffer EMPTY!");
			return;
		}
		curGb.restore(in.getStates().iterator().next()); // restore some state
		Util.runToNextInputFrame(); // skip to menu, ensuring mons are loaded

		int enemyHP = Util.getMemoryWordBE(curGb.pokemon.fightEnemyMonHPAddress);
		int playerHP = Util.getMemoryWordBE(curGb.pokemon.fightBattleMonHPAddress);

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


	public Map<PII,StateBuffer> executeSingleAttack(StateBuffer in, Segment moveSegment, EflAttackActionSegment actionSegment, int goalValue, int minValue, boolean noBins, boolean isB) {
	  EflAttackActionSegment dummyActionSegment = null;
	  EflAttackActionSegment aActionSegment = isB ? dummyActionSegment : actionSegment;
	  EflAttackActionSegment bActionSegment = isB ? actionSegment : dummyActionSegment;
		int goalAValue = isB ? Integer.MIN_VALUE : goalValue;
		int goalBValue = isB ? goalValue : Integer.MIN_VALUE;
		int minAValue = isB ? Integer.MIN_VALUE : minValue;
		int minBValue = isB ? minValue : Integer.MIN_VALUE;
		boolean noABins = isB ? false : noBins;
		boolean noBBins = isB ? noBins : false;
		return executeAttack(in, moveSegment, aActionSegment, bActionSegment, goalAValue, minAValue, goalBValue, minBValue, noABins, noBBins, isB);
	}


	public static final String PLAYER_ATTRIBUTE = "playerDamage";
	public static final String ENEMY_ATTRIBUTE = "enemyDamage";

	public Map<PII,StateBuffer> executeAttack(StateBuffer in, Segment moveSegment, EflAttackActionSegment aActionSegment, EflAttackActionSegment bActionSegment, int goalAValue, int minAValue, int goalBValue, int minBValue, boolean noABins, boolean noBBins, boolean bFirst) {
		Map<PII,StateBuffer> im = new HashMap<PII,StateBuffer>();
		Map<PII,Integer> imActiveFrame = new HashMap<PII,Integer>();

		System.out.println("executeAttack goal "+new PII(goalAValue,goalBValue)+" min "+new PII(minAValue, minBValue));

    StateBuffer next = new StateBuffer(0); // intermediate buffers are unbounded

		int skips = -1;
		while(in.size() > 0) {
			skips++;
			System.out.println("executeAttack: processing "+in.size()+" active states at "+skips+" skips");
			for(State s : in.getStates()) {
				if(MAX_SKIPS >= 0 && skips > MAX_SKIPS) {
					System.out.println("reached MAX_SKIPS limit!");
					continue;
				}

				curGb.restore(s);
        EflUtil.runToNextInputFrameNoLimit(0b11111111); // for any input

				int curActiveFrame = curGb.stepCount;

				StateBuffer sb = new StateBuffer();
				sb.addState(curGb.createState(false));

        curGb.step(); // skip this input frame
        State nextState = curGb.createState(true);

        sb = moveSegment.execute(sb);
        sb = bFirst ?
						executeAttackInternal(sb, curActiveFrame, goalAValue, minBValue, imActiveFrame, bActionSegment, goalBValue, false):
						executeAttackInternal(sb, curActiveFrame, minAValue, goalBValue, imActiveFrame, aActionSegment, goalAValue, true);
				if(sb == null) {
					continue;
				}

        next.addState(nextState);

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

      in = next; // use next states;
      next = new StateBuffer(0); // intermediate buffers are unbounded
		}
		return im;
	}

	private StateBuffer executeAttackInternal(StateBuffer sb, int curActiveFrame, int minAValue, int minBValue, Map<PII,Integer> imActiveFrame, EflAttackActionSegment actionSegment, int goalValue, boolean isA) {
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
