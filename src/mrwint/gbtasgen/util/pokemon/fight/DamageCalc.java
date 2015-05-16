package mrwint.gbtasgen.util.pokemon.fight;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.util.Util;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class DamageCalc {

	public static final int STAT_ATK = 0;
	public static final int STAT_DEF = 1;
	public static final int STAT_SPD = 2;
	public static final int STAT_SPCATK = 3;
	public static final int STAT_SPCDEF = 4;

	public static int getMove(boolean player, int moveNum) {
		return curGb.readMemory((player ? curGb.pokemon.fightBattleMonMovesAddress : curGb.pokemon.fightEnemyMonMovesAddress) + moveNum);
	}

	public static int getStat(boolean player, int statNum, boolean crit) {
		if (PokemonUtil.isGen1() && statNum == STAT_SPCDEF)
			statNum = STAT_SPCATK;
		if (!crit)
			return Util.getMemoryWordBE((player ? curGb.pokemon.fightBattleMonStatsAddress : curGb.pokemon.fightEnemyMonStatsAddress) + 2*statNum);
		else {
			int add = player ? curGb.pokemon.fightBattleMonOriginalStatsAddress : curGb.pokemon.fightEnemyMonOriginalStatsAddress;
			if (PokemonUtil.isGen1())
				add += 0x2c * curGb.readMemory(player ? curGb.pokemon.fightBattleMonIndex : curGb.pokemon.fightEnemyMonIndex);
			return Util.getMemoryWordBE(add + 2*statNum);
		}
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
			System.out.println((player?"Own":"Enemy")+" move "+(moveNum+1)+": "+(move == 0 ? "(not present)" : PokemonUtil.getStringFromList(curGb.pokemon.listMoveNamesAddress, move-1) + "("+move+")"));
		if(move <= 0)
			return -1;
		int baseDmg = curGb.getROM()[curGb.pokemon.listMovesAddress + (move-1)*curGb.pokemon.listMovesEntryLength + 2];
		int atkType = curGb.getROM()[curGb.pokemon.listMovesAddress + (move-1)*curGb.pokemon.listMovesEntryLength + 3];
		if(debugPrint)
			System.out.println("  base damage: "+baseDmg+", type: "+PokemonUtil.getStringFromPointerList(curGb.pokemon.listTypeNamesAddress, atkType));
		if(baseDmg <= 0)
			return 0;

		int attackerLvl = curGb.readMemory(player ? curGb.pokemon.fightBattleMonLevelAddress : curGb.pokemon.fightEnemyMonLevelAddress);
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
		int maxDamage = adjustTypeEffects(player, imDamage + 2,atkType,debugPrint,false);
		int minDamage = maxDamage < 2 ? maxDamage : (maxDamage * 217) / 255;
		int imCritDamage, maxCritDamage, minCritDamage;
		if (PokemonUtil.isGen2()) {
			imCritDamage = ((((attackerLvl * 2)/5 + 2) * atkStatCrit * baseDmg)/50)/defStat;
			maxCritDamage = adjustTypeEffects(player, imCritDamage*2 + 2,atkType,false,false);
			minCritDamage = maxCritDamage < 2 ? maxCritDamage : (maxCritDamage * 217) / 255;
		} else {
			imCritDamage = ((((attackerLvl * 4)/5 + 2) * atkStatCrit * baseDmg)/50)/defStat;
			maxCritDamage = adjustTypeEffects(player, imCritDamage + 2,atkType,false,false);
			minCritDamage = maxCritDamage < 2 ? maxCritDamage : (maxCritDamage * 217) / 255;
		}

		if(debugPrint)
			System.out.println("  "+minDamage+"-"+maxDamage+" damage ("+minCritDamage+"-"+maxCritDamage+" crit)");

		return min ? (crit ? minCritDamage : minDamage) : (crit ? maxCritDamage : maxDamage);
	}

	public static int getEffectiveness(boolean player, int moveNum) {
		int move = getMove(player,moveNum);
		if(move <= 0)
			return -1;
		int atkType = curGb.getROM()[curGb.pokemon.listMovesAddress + (move-1)*curGb.pokemon.listMovesEntryLength + 3];
		return adjustTypeEffects(player, 10, atkType, false, true);
	}

	public static boolean isEffective(boolean player, int moveNum) {
		int move = getMove(player,moveNum);
		if(move <= 0)
			return false;
		int atkType = curGb.getROM()[curGb.pokemon.listMovesAddress + (move-1)*curGb.pokemon.listMovesEntryLength + 3];

		int defenderTypeAddress = player ? curGb.pokemon.fightEnemyMonTypesAddress : curGb.pokemon.fightBattleMonTypesAddress;
		int add = curGb.pokemon.listTypeMatchupAddress;
		int dt1 = curGb.readMemory(defenderTypeAddress);
		int dt2 = curGb.readMemory(defenderTypeAddress+1);
		while(true) {
			int at = curGb.getROM()[add++];
			if(at == 0xFE) continue;
			if(at == 0xFF) break;
			int dt = curGb.getROM()[add++];
			/*int eff = State.getROM()[add++];*/add++;
			if(at == atkType && (dt == dt1 || dt == dt2))
				return true;
		}
		return false;
	}

	public static int adjustTypeEffects(boolean player, int inDamage, int atkType, boolean debugPrint, boolean noStab) {
		int attackerTypeAddress = player ? curGb.pokemon.fightBattleMonTypesAddress : curGb.pokemon.fightEnemyMonTypesAddress;
		int defenderTypeAddress = player ? curGb.pokemon.fightEnemyMonTypesAddress : curGb.pokemon.fightBattleMonTypesAddress;
		if(!noStab)
			if(atkType == curGb.readMemory(attackerTypeAddress) || atkType == curGb.readMemory(attackerTypeAddress+1)) { //STAB
				if(debugPrint)System.out.print("  [STAB]");
				inDamage += inDamage/2;
			}

		int add = curGb.pokemon.listTypeMatchupAddress;
		int dt1 = curGb.readMemory(defenderTypeAddress);
		int dt2 = curGb.readMemory(defenderTypeAddress+1);
		while(true) {
			int at = curGb.getROM()[add++];
			if(at == 0xFE) continue;
			if(at == 0xFF) break;
			int dt = curGb.getROM()[add++];
			int eff = curGb.getROM()[add++];
			if(at == atkType && (dt == dt1 || dt == dt2)) {
				if(debugPrint)System.out.print("  [EFF"+eff+"]");
				inDamage = (inDamage * eff)/10;
			}
		}
		return inDamage;
	}
}
