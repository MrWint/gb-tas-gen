package mrwint.gbtasgen.metric.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.Util;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;
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
		if(EflUtil.runToAddressLimit(0, startMove, 500, curGb.pokemon.encounterPreCheckAddresses) == 0) {
			System.out.println("Warning: couldn't find encounterPreCheckAddresses call for 500 steps, assuming no encounter!");
			return 0;
		}
		int add = EflUtil.runToAddressNoLimit(0,0, curGb.pokemon.encounterPostCheckAddresses);
		int curMon = curGb.readMemory(curGb.pokemon.encounterMonSpeciesAddress);
		String curMonName = PokemonUtil.getMonName(curMon);
		int curLvl = curGb.readMemory(curGb.pokemon.encounterMonLevelAddress);

		int dsum = (curGb.readMemory(curGb.pokemon.rngAddress) + curGb.readMemory(curGb.pokemon.rngAddress + 1)) & 0xff;

		if (add == curGb.pokemon.encounterCheckMainFuncEncounterAddress) {
			System.out.println("encounter with dsum "+Util.toHex(dsum, 2)+ " ["+curMonName + "("+curMon+"):"+curLvl+"]");
		}

		if(add != curGb.pokemon.encounterCheckMainFuncEncounterAddress || (lvl != null && !Util.arrayContains(lvl, curLvl)) || (mon > 0 && mon != curMon) || minDSum > dsum || maxDSum < dsum)
			return 0;
		if(specialDefStat != null) {
		  EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.printLetterDelayJoypadAddress);
			if(!Util.arrayContains(specialDefStat, DamageCalc.getStat(false, DamageCalc.STAT_SPCDEF, false)))
				return 0;
		}
    if(atkDV != null) {
      EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.printLetterDelayJoypadAddress);
      int curAtkDV = (curGb.readMemory(curGb.pokemon.fightEnemyDVsAddress) & 0xF0) >> 4;
      if(!Util.arrayContains(atkDV, curAtkDV))
        return 0;
    }
    if(spdDV != null) {
      EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.printLetterDelayJoypadAddress);
      int curSpdDV = (curGb.readMemory(curGb.pokemon.fightEnemyDVsAddress + 1) & 0xF0) >> 4;
      if(!Util.arrayContains(spdDV, curSpdDV))
        return 0;
    }
    if(specialDV != null) {
      EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.printLetterDelayJoypadAddress);
      int curSpcDV = curGb.readMemory(curGb.pokemon.fightEnemyDVsAddress + 1) & 0xF;
      if(!Util.arrayContains(specialDV, curSpcDV))
        return 0;
    }
		if(defStat != null) {
		  EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.printLetterDelayJoypadAddress);
			if(!Util.arrayContains(defStat, DamageCalc.getStat(false, DamageCalc.STAT_DEF, false)))
				return 0;
		}
		if(hpStat != null) {
		  EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.printLetterDelayJoypadAddress);
			if(!Util.arrayContains(hpStat, Util.getMemoryWordBE(curGb.pokemon.fightEnemyMonHPAddress)))
				return 0;
		}
		System.out.println("found suitable encounter with DVs: " + Util.toHex(Util.getMemoryWordBE(curGb.pokemon.fightEnemyDVsAddress)));
		return 1;
	}
}