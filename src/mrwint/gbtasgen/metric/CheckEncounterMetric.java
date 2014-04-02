package mrwint.gbtasgen.metric;

import java.util.Arrays;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;
import mrwint.gbtasgen.util.fight.DamageCalc;

public class CheckEncounterMetric extends Metric {
	
	int mon;
	int lvl;
	int startMove;
	Integer[] specialDefStat;
	Integer[] defStat;
	Integer[] hpStat;
	
	public CheckEncounterMetric(int startMove) {
		this(0,0,null,startMove,null,null);
	}
	
	public CheckEncounterMetric(int mon, int lvl) {
		this(mon,lvl,null,0,null,null);
	}
	
	public CheckEncounterMetric(int mon, int lvl, Integer[] specialDefStat, int startMove) {
		this(mon,lvl,specialDefStat,startMove,null,null);
	}
	
	public CheckEncounterMetric(int mon, int lvl, Integer[] specialDefStat, int startMove, Integer[] defStat, Integer[] hpStat) {
		this.mon = mon;
		this.lvl = lvl;
		this.specialDefStat = specialDefStat;
		this.defStat = defStat;
		this.hpStat = hpStat;
		this.startMove = startMove;
	}
	
	@Override
	public int getMetric() {
		State s = new State();
		if(Util.runToAddress2Limit(0, startMove, 500, RomInfo.rom.encounterCheckMainFuncAddress) == 0) {
			System.out.println("Warning: couldn't find a encounterCheckMainFuncAddress call for 500 steps, assuming no encounter!");
			s.restore();
			return 0;
		}
		int add = Util.runToAddress2(0,0, RomInfo.rom.encounterCheckMainFuncEncounterAddress, RomInfo.rom.encounterCheckMainFuncNoEncounterAddress);
		int curMon = Gb.readMemory(RomInfo.rom.encounterMonSpeciesAddress);
		int curLvl = Gb.readMemory(RomInfo.rom.encounterMonLevelAddress);
		
		if(add != RomInfo.rom.encounterCheckMainFuncEncounterAddress || (lvl > 0 && lvl != curLvl) || (mon > 0 && mon != curMon)) {
			s.restore();
			return 0;
		}
		if(specialDefStat != null) {
			Util.runToAddress(0, 0, RomInfo.rom.printLetterDelayJoypadAddress);
			if(!Arrays.asList(specialDefStat).contains(DamageCalc.getStat(false, DamageCalc.STAT_SPCDEF, false))) {
				s.restore();
				return 0;
			}
		}
		if(defStat != null) {
			Util.runToAddress(0, 0, RomInfo.rom.printLetterDelayJoypadAddress);
			if(!Arrays.asList(defStat).contains(DamageCalc.getStat(false, DamageCalc.STAT_DEF, false))) {
				s.restore();
				return 0;
			}
		}
		if(hpStat != null) {
			Util.runToAddress(0, 0, RomInfo.rom.printLetterDelayJoypadAddress);
			if(!Arrays.asList(hpStat).contains(Util.getMemoryBigEndian(RomInfo.rom.fightEnemyMonHPAddress))) {
				s.restore();
				return 0;
			}
		}
		s.restore();
		return 1;
	}
}