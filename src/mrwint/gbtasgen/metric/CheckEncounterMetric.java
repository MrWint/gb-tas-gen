package mrwint.gbtasgen.metric;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.util.Util;
import mrwint.gbtasgen.util.fight.DamageCalc;

public class CheckEncounterMetric extends StateResettingMetric {
	
	int mon;
	int[] lvl;
	int startMove;
	int[] specialDefStat;
	int[] specialDV;
	int[] defStat;
	int[] hpStat;
	int minDSum = 0x00;
	int maxDSum = 0xff;
	
	public CheckEncounterMetric() {
		this(0);
	}
	public CheckEncounterMetric(int mon) {
		this.mon = mon;
	}
	public CheckEncounterMetric(int mon, int... lvl) {
		this.mon = mon;
		this.lvl = lvl;
	}
	public CheckEncounterMetric withStartMove(int startMove) {
		this.startMove = startMove;
		return this;
	}
	public CheckEncounterMetric withSpcDefStat(int... specialDefStat) {
		this.specialDefStat = specialDefStat;
		return this;
	}
	public CheckEncounterMetric withDefStat(int... defStat) {
		this.defStat = defStat;
		return this;
	}
	public CheckEncounterMetric withHPStat(int... hpStat) {
		this.hpStat = hpStat;
		return this;
	}
	public CheckEncounterMetric withSpcDV(int... specialDV) {
		this.specialDV = specialDV;
		return this;
	}
	public CheckEncounterMetric withMinDSum(int minDSum) {
		this.minDSum = minDSum;
		return this;
	}
	public CheckEncounterMetric withMaxDSum(int maxDSum) {
		this.maxDSum = maxDSum;
		return this;
	}
	
	@Override
	public int getMetricInternal() {
		if(Util.runToAddress2Limit(0, startMove, 500, RomInfo.rom.encounterPreCheckAddresses) == 0) {
			System.out.println("Warning: couldn't find encounterPreCheckAddresses call for 500 steps, assuming no encounter!");
			return 0;
		}
		int add = Util.runToAddress2(0,0, RomInfo.rom.encounterPostCheckAddresses);
		int curMon = Gb.readMemory(RomInfo.rom.encounterMonSpeciesAddress);
		int curLvl = Gb.readMemory(RomInfo.rom.encounterMonLevelAddress);
		
		int dsum = (Gb.readMemory(RomInfo.rom.rngAddress) + Gb.readMemory(RomInfo.rom.rngAddress + 1)) & 0xff;
		
		if (add == RomInfo.rom.encounterCheckMainFuncEncounterAddress) {
			System.out.println("encounter with dsum "+Util.toHex(dsum, 2)+ " ["+curMon+":"+curLvl+"]");
		}
		
		if(add != RomInfo.rom.encounterCheckMainFuncEncounterAddress || (lvl != null && !Util.arrayContains(lvl, curLvl)) || (mon > 0 && mon != curMon) || minDSum > dsum || maxDSum < dsum)
			return 0;
		if(specialDefStat != null) {
			Util.runToAddress(0, 0, RomInfo.rom.printLetterDelayJoypadAddress);
			if(!Util.arrayContains(specialDefStat, DamageCalc.getStat(false, DamageCalc.STAT_SPCDEF, false)))
				return 0;
		}
		if(specialDV != null) {
			Util.runToAddress(0, 0, RomInfo.rom.printLetterDelayJoypadAddress);
			int curSpcDV = Gb.readMemory(RomInfo.rom.fightEnemyDVsAddress + 1) & 0xF;
			if(!Util.arrayContains(specialDV, curSpcDV))
				return 0;
		}
		if(defStat != null) {
			Util.runToAddress(0, 0, RomInfo.rom.printLetterDelayJoypadAddress);
			if(!Util.arrayContains(defStat, DamageCalc.getStat(false, DamageCalc.STAT_DEF, false)))
				return 0;
		}
		if(hpStat != null) {
			Util.runToAddress(0, 0, RomInfo.rom.printLetterDelayJoypadAddress);
			if(!Util.arrayContains(hpStat, Util.getMemoryBigEndian(RomInfo.rom.fightEnemyMonHPAddress)))
				return 0;
		}
		return 1;
	}
}