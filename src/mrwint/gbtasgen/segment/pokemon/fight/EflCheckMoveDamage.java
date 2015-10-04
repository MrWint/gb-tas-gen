package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.Util;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

// Checks whether damage dealt is between minDamage and maxDamage, and if conditions are met.
// Returns damage dealt, or MIN_VALUE if missed or conditions not met.
public class EflCheckMoveDamage implements StateResettingMetric {
	final boolean criticalHit;
	final boolean effectMiss;
	final int thrashAdditionalTurns;
  final int minDamage;
  final int maxDamage;
  final boolean negateOutputDamage;

	public EflCheckMoveDamage(boolean criticalHit, boolean effectMiss, int thrashAdditionalTurns, int minDamage, int maxDamage, boolean negateOutputDamage) {
		this.criticalHit = criticalHit;
		this.effectMiss = effectMiss;
		this.thrashAdditionalTurns = thrashAdditionalTurns;
    this.minDamage = minDamage;
    this.maxDamage = maxDamage;
    this.negateOutputDamage = negateOutputDamage;
	}
	@Override
	public int getMetricInternal() {
		//Util.runToAddress(0, 0, RomInfo.rom.fightDamageCalc);
		//int atk = State.getRegister(Register.BC) >> 8;
		//int def = State.getRegister(Register.BC) % 256;
		//int pow = State.getRegister(Register.DE) >> 8;
		//int lvl = State.getRegister(Register.DE) % 256;

		//Util.runToAddress(0, 0, RomInfo.rom.fightDamageVariation);
		//int maxdmg = Util.getMemoryBigEndian(RomInfo.rom.fightCurDamageAddress);

//      Util.runToAddressNoLimit(0, 0, 0x3d6fc, 0x3e779);
//      int missed_ = curGb.readMemory(curGb.pokemon.fightAttackMissedAddress);
//      System.out.println("missed: " + missed_);
//      Util.runToAddressNoLimit(0, 0, 0x3d702, 0x3e77f);
//      missed_ = curGb.readMemory(curGb.pokemon.fightAttackMissedAddress);
//      System.out.println("missed: " + missed_);

		EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.fightBattleCommand0a);
		int crit = curGb.readMemory(curGb.pokemon.fightCriticalHitAddress);
		int missed = curGb.readMemory(curGb.pokemon.fightAttackMissedAddress);
//		System.out.println("EflCheckMoveDamage crit: " + crit + " missed: " + missed);
		if (missed != 0 || criticalHit != (crit != 0))
		  return Integer.MIN_VALUE;
		if (thrashAdditionalTurns > 0 && curGb.readMemory(curGb.pokemon.thrashNumTurnsAddress) < thrashAdditionalTurns) {
			System.out.println("caught bad thrash "+ curGb.readMemory(curGb.pokemon.thrashNumTurnsAddress));
      return Integer.MIN_VALUE;
		}
		if (PokemonUtil.isGen2()) {
			int effectMissed = curGb.readMemory(curGb.pokemon.fightEffectMissedAddress);
			if (this.effectMiss != (effectMissed != 0))
			  return Integer.MIN_VALUE;
		}
		int dmg = Util.getMemoryWordBE(curGb.pokemon.fightCurDamageAddress);
//    System.out.println("EflCheckMoveDamage dmg: " + dmg);
		if (dmg < minDamage || dmg > maxDamage)
      return Integer.MIN_VALUE;
//			System.out.println(crit+" "+missed+" "+effectMissed+" "+dmg);
//		System.out.println("atk: "+atk+", def: "+def+", pow: "+pow+", lvl: "+lvl);
//		System.out.println("max damage: "+maxdmg+", dmg: "+dmg);
		return negateOutputDamage ? -dmg : dmg;
	}
}