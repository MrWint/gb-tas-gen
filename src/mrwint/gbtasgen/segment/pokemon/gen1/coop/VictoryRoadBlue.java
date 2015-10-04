package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.SELECT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMELEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.COMET_PUNCH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CONFUSION;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DOUBLE_KICK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DOUBLE_SLAP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGONAIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGON_RAGE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ELIXER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FIRE_PUNCH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FURY_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.IVYSAUR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOTTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_POWDER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_STING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PSYBEAM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.REFLECT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROLLING_KICK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SAND_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACOOL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.THRASH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.X_SPEED;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMoveNew;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckSwitchAndTeleportEffectUsed;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflCatchSafariMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflOverrideMoveSegment;
import mrwint.gbtasgen.segment.pokemon.EflPokecenterSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflUseBattleItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflFishSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSellItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBike2Segment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class VictoryRoadBlue extends SeqSegment {

	@Override
	public void execute() {
//    seq(new EflSelectMonSegment(PIDGEOTTO).fromOverworld().andSwitchWith(IVYSAUR));
//    seq(new EflSelectMonSegment(PIDGEOTTO).andFlyTo(-1)); // Viridian
//    seqEflSkipInput(1);
//    seq(new EflUseBike2Segment().fromOverworld());
//    seq(new EflWalkToSegment(32, 7)); // enter gym
//
//    seq(new EflWalkToSegment(15, 5)); // engage
//    seq(new EflInitFightSegment(1)); // start fight
//    seq(new EflSwitchPokemonSegment(TENTACOOL, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // Water Gun
//      kems.numExpGainers = 4; // Ivysaur, boosted, Tentacool, lvlup to 31
//      seq(kems); // rhyhorn
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflEvolutionSegment()); // Tentacruel
//
//    save("vr1");
//    load("vr1");
//
//    seq(new EflSelectMonSegment(IVYSAUR).fromOverworld().andSwitchWith(PIDGEOTTO));
//    seqEflButton(B);
//    seqEflButton(START);
//    seq(new EflWalkToSegment(10, 4)); // engage
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[3][1] = 2; // wing attack crit
//      seq(kems); // machoke
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[3][0] = 1; // wing attack
//      kems.attackCount[3][1] = 1; // wing attack crit
//      seq(kems); // machop
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[3][1] = 2; // wing attack crit
//      kems.numExpGainers = 2; // pidgeotto lvlup to 36
//      seq(kems); // machoke
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflEvolutionSegment()); // Pidgeot
//
//    save("vr2");
//    load("vr2");
//
//    seq(new EflSelectMonSegment(PIDGEOT).fromOverworld().andSwitchWith(IVYSAUR));
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(17, 18, false)); // leave gym
//    seq(new EflWalkToSegment(32, 7)); // enter gym
//    seq(new EflWalkToSegment(15, 5)); // engage
//
//    seq(new EflWalkToSegment(2, 3)); // engage
//    seq(new EflWalkToSegment(2, 2)); // engage giovanni
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflInitFightSegment(10)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // vine whip
//      kems.numExpGainers = 3; // ivysaur, boosted, lvlup to 27
//      seq(kems); // rhyhorn
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment(); // TODO: consider Guard spec
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), GROWL)};
//      kems.attackCount[3][1] = 2; // vine whip
//      kems.numExpGainers = 3; // ivysaur, boosted, lvlup to 28
//      seq(kems); // dugtrio
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//      kems.attackCount[1][0] = 1; // ice beam
//      kems.attackCount[1][1] = 1; // ice beam crit
//      kems.numExpGainers = 4; // dragonair, ivysaur, boosted, lvlup to 29
//      seq(kems); // nidoqueen
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(IVYSAUR, EflEnemyMoveDesc.missWith(POISON_STING)));
//    save("tmp4");
//    load("tmp4");
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(TACKLE)));
//    save("tmp5");
//    load("tmp5");
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
//      kems.attackCount[1][0] = 1; // ice beam
//      kems.attackCount[1][1] = 1; // ice beam crit
//      kems.numExpGainers = 3; // dragonair, ivysaur, boosted
//      seq(kems); // nidoking
//    }
//    save("tmp6");
//    load("tmp6");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seqMetric(new OutputParty());
//    seq(new EflSwitchPokemonSegment(IVYSAUR, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//      kems.attackCount[3][0] = 1; // vine whip
//      kems.attackCount[3][1] = 1; // vine whip
//      kems.numExpGainers = 3; // ivysaur, boosted, lvlup to 30
//      seq(kems); // rhydon
//    }
//
//    save("vr3");
//    load("vr3");
//
//    seq(new EflCancelMoveLearnSegment()); // razor leaf
//    seq(new EflSkipTextsSegment(2)); // dragonair, lvlup to 36
//
//    seq(new EflSkipTextsSegment(1 + 4)); // defeat, texts
//    seq(new EflTextSegment()); // earth badge
//    seq(new EflSkipTextsSegment(1)); // got money
//    seq(new EflSkipTextsSegment(9)); // after battle texts (no room)
////    seq(new EflSkipTextsSegment(14)); // after battle texts
//
//    seq(new EflWalkToSegment(15, 5)); // avoid spins
//    seq(new EflWalkToSegment(17, 16)); // leave gym
//    seq(new EflWalkToSegment(17, 18, false)); // leave gym
//
//    seqMetric(new OutputItems());
//    seq(new EflSelectMonSegment(IVYSAUR).fromOverworld().andSwitchWith(DRAGONAIR));
//    seqEflButton(B); // cancel
//    seq(new EflSelectItemSegment(Constants.TM07).fromMainMenu().andUse());
//    seq(new EflLearnTMSegment(Constants.DRAGONAIR, 0));
//    seqEflSkipInput(1);
//    seq(new EflUseBike2Segment());
//
//    seq(new EflWalkToSegment(32, 9, false)); // ledge
//    seq(new EflWalkToSegment(-1, 17)); // leave viridian
//    seq(new EflWalkToSegment(29, 5)); // engage
//    seq(new EflInitFightSegment(10)); // start fight
//    save("tmp");
//    load("tmp");
//    seq(new EflUseBattleItemSegment(X_SPEED, EflEnemyMoveDesc.missWith(Metric.TRUE, Constants.WHIRLWIND)));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflUseBattleItemSegment(X_SPEED, EflEnemyMoveDesc.missWith(Metric.TRUE, Constants.WHIRLWIND)));
//    seqEflButton(UP | A, PRESSED); // fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.skipFirstMainBattleMenu = true;
//      kems.attackCount[0][1] = 1; // horn drill
//      seq(kems); // pidgeot
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][0] = 1; // ice beam
//      seq(kems); // rhyhorn
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // horn drill
//      kems.numExpGainers = 2; // dragonair, lvlup to 37
//      seq(kems); // gyarados
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // horn drill
//      seq(kems); // growlithe
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // horn drill
//      seq(kems); // alakazam
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // horn drill
//      kems.numExpGainers = 2; // dragonair, lvlup to 38
//      seq(kems); // venusaur
//    }
//    seq(new EflEndFightSegment(2)); // player defeated enemy
//    seq(new EflSkipTextsSegment(5)); // after battle text
//
//    save("vr4");
    load("vr4");
  }
}
