package mrwint.gbtasgen.metric.pokemon;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.util.Util;
import mrwint.gbtasgen.util.pokemon.fight.DamageCalc;

public class CheckEncounterMetric implements StateResettingMetric {

	int mon;
	int[] lvl;
	int startMove;
	int[] specialDefStat;
	int[] specialDV;
  int[] atkDV;
  int[] spdDV;
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
  public CheckEncounterMetric withAtkDV(int... atkDV) {
    this.atkDV = atkDV;
    return this;
  }
  public CheckEncounterMetric withSpdDV(int... spdDV) {
    this.spdDV = spdDV;
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
		if(Util.runToAddressLimit(0, startMove, 500, RomInfo.pokemon.encounterPreCheckAddresses) == 0) {
			System.out.println("Warning: couldn't find encounterPreCheckAddresses call for 500 steps, assuming no encounter!");
			return 0;
		}
		int add = Util.runToAddressNoLimit(0,0, RomInfo.pokemon.encounterPostCheckAddresses);
		int curMon = Gb.readMemory(RomInfo.pokemon.encounterMonSpeciesAddress);
		int curLvl = Gb.readMemory(RomInfo.pokemon.encounterMonLevelAddress);

		int dsum = (Gb.readMemory(RomInfo.pokemon.rngAddress) + Gb.readMemory(RomInfo.pokemon.rngAddress + 1)) & 0xff;

		if (add == RomInfo.pokemon.encounterCheckMainFuncEncounterAddress) {
			System.out.println("encounter with dsum "+Util.toHex(dsum, 2)+ " ["+curMon+":"+curLvl+"]");
		}

		if(add != RomInfo.pokemon.encounterCheckMainFuncEncounterAddress || (lvl != null && !Util.arrayContains(lvl, curLvl)) || (mon > 0 && mon != curMon) || minDSum > dsum || maxDSum < dsum)
			return 0;
		if(specialDefStat != null) {
			Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.printLetterDelayJoypadAddress);
			if(!Util.arrayContains(specialDefStat, DamageCalc.getStat(false, DamageCalc.STAT_SPCDEF, false)))
				return 0;
		}
    if(atkDV != null) {
      Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.printLetterDelayJoypadAddress);
      int curAtkDV = (Gb.readMemory(RomInfo.pokemon.fightEnemyDVsAddress) & 0xF0) >> 4;
      if(!Util.arrayContains(atkDV, curAtkDV))
        return 0;
    }
    if(spdDV != null) {
      Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.printLetterDelayJoypadAddress);
      int curSpdDV = (Gb.readMemory(RomInfo.pokemon.fightEnemyDVsAddress + 1) & 0xF0) >> 4;
      if(!Util.arrayContains(spdDV, curSpdDV))
        return 0;
    }
    if(specialDV != null) {
      Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.printLetterDelayJoypadAddress);
      int curSpcDV = Gb.readMemory(RomInfo.pokemon.fightEnemyDVsAddress + 1) & 0xF;
      if(!Util.arrayContains(specialDV, curSpcDV))
        return 0;
    }
		if(defStat != null) {
			Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.printLetterDelayJoypadAddress);
			if(!Util.arrayContains(defStat, DamageCalc.getStat(false, DamageCalc.STAT_DEF, false)))
				return 0;
		}
		if(hpStat != null) {
			Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.printLetterDelayJoypadAddress);
			if(!Util.arrayContains(hpStat, Util.getMemoryWordBE(RomInfo.pokemon.fightEnemyMonHPAddress)))
				return 0;
		}
		System.out.println("found suitable encounter with DVs: " + Util.toHex(Util.getMemoryWordBE(RomInfo.pokemon.fightEnemyDVsAddress)));
		return 1;
	}
}