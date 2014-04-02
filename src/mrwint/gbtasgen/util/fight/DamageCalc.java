package mrwint.gbtasgen.util.fight;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class DamageCalc {
	
	public static final int STAT_ATK = 0;
	public static final int STAT_DEF = 1;
	public static final int STAT_SPD = 2;
	public static final int STAT_SPCATK = 3;
	public static final int STAT_SPCDEF = 4;
	
	public static int getMove(boolean player, int moveNum) {
		return Gb.readMemory((player ? RomInfo.rom.fightBattleMonMovesAddress : RomInfo.rom.fightEnemyMonMovesAddress) + moveNum);
	}

	public static int getStat(boolean player, int statNum, boolean crit) {
		if(!crit)
			return Util.getMemoryBigEndian((player ? RomInfo.rom.fightBattleMonStatsAddress : RomInfo.rom.fightEnemyMonStatsAddress) + 2*statNum);
		else
			return Util.getMemoryBigEndian((player ? RomInfo.rom.fightBattleMonOriginalStatsAddress : RomInfo.rom.fightEnemyMonOriginalStatsAddress) + 2*statNum);
	}

	public static void printMoveDebugInfo(boolean player, int moveNum) {
		getDamage(player, moveNum, false, false, true);
	}

	public static int getDamage(boolean player, int moveNum, boolean crit) {
		return getDamage(player, moveNum, crit, false, false);
	}
	
	public static int getDamage(boolean player, int moveNum, boolean crit, boolean min) {
		return getDamage(player, moveNum, crit, min, false);
	}
	
	public static int getDamage(boolean player, int moveNum, boolean crit, boolean min, boolean debugPrint) {
		
		int move = getMove(player,moveNum);
		if(debugPrint)
			System.out.println((player?"Own":"Enemy")+" move "+(moveNum+1)+": "+(move == 0 ? "(not present)" : Util.getStringFromList(RomInfo.rom.listMoveNamesAddress, move-1) + "("+move+")"));
		if(move <= 0)
			return -1;
		int baseDmg = State.getROM()[RomInfo.rom.listMovesAddress + (move-1)*7 + 2];
		int atkType = State.getROM()[RomInfo.rom.listMovesAddress + (move-1)*7 + 3];
		if(debugPrint)
			System.out.println("  base damage: "+baseDmg+", type: "+Util.getStringFromPointerList(RomInfo.rom.listTypeNamesAddress, atkType));
		if(baseDmg <= 0)
			return 0;

		int attackerLvl = Gb.readMemory(player ? RomInfo.rom.fightBattleMonLevelAddress : RomInfo.rom.fightEnemyMonLevelAddress);
		int atkStat = 0, atkStatCrit = 0, defStat = 0;
		if(atkType >= 0x14) {
			if(debugPrint)System.out.print("  [SPECIAL]");
			atkStat = getStat(player, STAT_SPCATK, false);
			atkStatCrit = getStat(player, STAT_SPCATK, true);
			defStat = getStat(!player, STAT_SPCDEF, false);
		} else {
			if(debugPrint)System.out.print("  [PHYSICAL]");
			atkStat = getStat(player, STAT_ATK, false);
			atkStatCrit = getStat(player, STAT_ATK, true);
			defStat = getStat(!player, STAT_DEF, false);
		}
		int imDamage = ((((attackerLvl * 2)/5 + 2) * atkStat * baseDmg)/50)/defStat;
		int imCritDamage = ((((attackerLvl * 2)/5 + 2) * atkStatCrit * baseDmg)/50)/defStat;
		int maxDamage = adjustTypeEffects(player, imDamage + 2,atkType,debugPrint,false);
		int minDamage = maxDamage < 2 ? maxDamage : (maxDamage * 217) / 255;
		int maxCritDamage = adjustTypeEffects(player, imCritDamage*2 + 2,atkType,false,false);
		int minCritDamage = maxCritDamage < 2 ? maxCritDamage : (maxCritDamage * 217) / 255;
		
		if(debugPrint)
			System.out.println("  "+minDamage+"-"+maxDamage+" damage ("+minCritDamage+"-"+maxCritDamage+" crit)");

		return min ? (crit ? minCritDamage : minDamage) : (crit ? maxCritDamage : maxDamage);
	}

	public static int getEffectiveness(boolean player, int moveNum) {
		int move = getMove(player,moveNum);
		if(move <= 0)
			return -1;
		int atkType = State.getROM()[RomInfo.rom.listMovesAddress + (move-1)*7 + 3];
		return adjustTypeEffects(player, 10, atkType, false, true);
	}

	public static int adjustTypeEffects(boolean player, int inDamage, int atkType, boolean debugPrint, boolean noStab) {
		int attackerTypeAddress = player ? RomInfo.rom.fightBattleMonTypesAddress : RomInfo.rom.fightEnemyMonTypesAddress;
		int defenderTypeAddress = player ? RomInfo.rom.fightEnemyMonTypesAddress : RomInfo.rom.fightBattleMonTypesAddress;
		if(!noStab)
			if(atkType == Gb.readMemory(attackerTypeAddress) || atkType == Gb.readMemory(attackerTypeAddress+1)) { //STAB
				if(debugPrint)System.out.print("  [STAB]");
				inDamage += inDamage/2;
			}
		
		int add = RomInfo.rom.listTypeMatchupAddress;
		int dt1 = Gb.readMemory(defenderTypeAddress);
		int dt2 = Gb.readMemory(defenderTypeAddress+1);
		while(true) {
			int at = State.getROM()[add++];
			if(at == 0xFE) continue;
			if(at == 0xFF) break;
			int dt = State.getROM()[add++];
			int eff = State.getROM()[add++];
			if(at == atkType && (dt == dt1 || dt == dt2)) {
				if(debugPrint)System.out.print("  [EFF"+eff+"]");
				inDamage = (inDamage * eff)/10;
			}
		}
		return inDamage;
	}
}
