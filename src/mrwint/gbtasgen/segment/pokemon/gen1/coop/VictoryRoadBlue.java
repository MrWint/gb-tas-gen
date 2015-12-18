package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.SELECT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.AURORA_BEAM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BLASTOISE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARIZARD;
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
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FEAROW;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FIRE_PUNCH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FURY_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GEODUDE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GLOOM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GOLBAT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GRAVELER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM04;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HYPNOSIS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.IVYSAUR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MACHOP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MANKEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAROWAK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ODDISH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ONIX;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOTTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_POWDER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_STING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWAG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWHIRL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PRIMEAPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PSYBEAM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RARE_CANDY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RATTATA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.REFLECT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROCK_THROW;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROLLING_KICK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SANDSHREW;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SANDSLASH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SAND_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCREECH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SUPERSONIC;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACOOL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACRUEL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.THRASH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM07;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TOXIC;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VENOMOTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WHIRLWIND;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WING_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.X_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.X_SPEED;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ZUBAT;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckConfusionEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMoveNew;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckSleepEffectMisses;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeMonBoxSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflFishSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSellItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
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
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(32, 7)); // enter gym
//
//    seq(new EflWalkToSegment(15, 5)); // engage
//    seq(new EflInitFightSegment(1)); // start fight
//    seq(new EflSwitchPokemonSegment(TENTACOOL, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // Water Gun
//      kems.numExpGainers = 5; // Ivysaur, boosted, lvlup to 26, Tentacool, lvlup to 31
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
//      kems.attackCount[0][1] = 1; // fly crit
//      kems.keepLastEnemyAttack = true;
//      seq(kems); // machoke
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[0][1] = 1; // fly crit
//      kems.keepLastEnemyAttack = true;
//      seq(kems); // machop
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[0][1] = 1; // fly crit
//      kems.keepLastEnemyAttack = true;
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
////      kems.attackCount[1][0] = 1; // ice beam
//      kems.attackCount[1][1] = 2; // ice beam crit
//      kems.numExpGainers = 3; // dragonair, ivysaur, boosted
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
//      kems.numExpGainers = 4; // dragonair, ivysaur, boosted, lvlup to 29
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
//    seq(new EflSkipTextsSegment(1)); // dragonair
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
//    seq(new EflSelectItemSegment(TM07).fromMainMenu().andUse());
//    seq(new EflLearnTMSegment(DRAGONAIR, 1)); // ice beam -> horn drill
//    seqEflSkipInput(1);
//    seq(new EflUseBikeSegment());
//
//    seq(new EflWalkToSegment(32, 9, false)); // ledge
//
//    seq(new EflWalkToSegment(23, 25)); // enter center
//    save("tmp");
//    load("tmp");
//    {
//      seq(new EflWalkToSegment(13, 4)); // PC
//      seq(new EflWalkToSegment(13, 3, false)); // PC
//      seq(new EflWalkToSegment(13, 3, false)); // PC
//
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflDepositMonSegment(BLASTOISE));
//        seq(new EflDepositMonSegment(TENTACRUEL));
//        seq(new EflWithdrawMonSegment(MANKEY));
//        seq(new EflWithdrawMonSegment(ODDISH));
//        seq(new EflChangeMonBoxSegment(3)); // box 4
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, PRESSED); // cancel
//      }
//    }
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//
//    seqUnbounded(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(-1, 17)); // leave viridian
//    seq(new EflWalkToSegment(29, 5)); // engage
//    seq(new EflInitFightSegment(10)); // start fight
//    save("tmp1");
//    load("tmp1");
//    seq(new EflUseBattleItemSegment(X_SPEED, EflEnemyMoveDesc.missWith(Metric.TRUE, Constants.WHIRLWIND)));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflUseBattleItemSegment(X_SPEED, EflEnemyMoveDesc.missWith(Metric.TRUE, Constants.WHIRLWIND)));
//    seqEflButton(UP | A, PRESSED); // fight
//    seqEflButton(DOWN); // horn drill
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.skipFirstMainBattleMenu = true;
//      kems.attackCount[1][1] = 1; // horn drill
//      kems.numExpGainers = 2; // dragonair, lvlup to 34
//      seq(kems); // pidgeot
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // surf
//      seq(kems); // rhyhorn
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // gyarados
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      kems.numExpGainers = 2; // dragonair, lvlup to 35
//      seq(kems); // growlithe
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // alakazam
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // venusaur
//    }
//    seq(new EflEndFightSegment(2)); // player defeated enemy
//
//    save("vr4");
//    load("vr4");
//
//    seq(new EflSkipTextsSegment(3)); // after battle text
//    seq(new EflSkipTextsSegment(2)); // after battle text
//
//    seq(new EflWalkToSegment(25, 6, false)); // water
//    seqEflSkipInput(1);
//    seq(new EflFishSegment(POLIWAG));
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(21, 7, false)); // jump ledge
//    seqUnbounded(new EflWalkToSegment(20, 9)); // grass
//    seq(new EflEncounterSegment(RATTATA, LEFT));
//    seq(new EflCatchMonSegment());
//    seq(new EflWalkToSegment(8, 5)); // enter house
//    seq(new EflWalkToSegment(4, 2)); // badge check
//    seq(new EflSkipTextsSegment(2)); // badge check
//    seq(new EflWalkToSegment(4, 0)); // leave house
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(7, 136)); // badge check
//    seq(new EflSkipTextsSegment(4)); // badge check
//    seq(new EflWalkToSegment(9, 119)); // badge check
//    seq(new EflSkipTextsSegment(4)); // badge check
//    seq(new EflWalkToSegment(10, 105)); // badge check
//    seq(new EflSkipTextsSegment(4)); // badge check
//    seq(new EflWalkToSegment(10, 104)); // water
//    seq(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSurf());
//    seqEflSkipInput(1);
//
//    save("vr5");
//    load("vr5");
//
//    for(int i=0;i<7;i++)
//      seqMove(new EflWalkStep(UP, true));
//    seq(new EflSkipTextsSegment(4)); // badge check
//    for(int i=0;i<3;i++)
//      seqMove(new EflWalkStep(UP, true));
//    for(int i=0;i<2;i++)
//      seqMove(new EflWalkStep(LEFT, true));
//    for(int i=0;i<2;i++)
//      seqMove(new EflWalkStep(UP, true));
//    seqEflButton(A); // max ether
//    seq(new EflTextSegment()); // max ether
//    seqMove(new EflWalkStep(UP, true));
//    seq(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSurf());
//    seqEflSkipInput(1);
//    for(int i=0;i<1;i++)
//      seqMove(new EflWalkStep(LEFT, true));
//    for(int i=0;i<4;i++)
//      seqMove(new EflWalkStep(UP, true));
//    seqUnbounded(new EflSkipTextsSegment(4)); // badge check
//    for(int i=0;i<10;i++)
//      seqMoveUnboundedNoDelay(new EflWalkStep(UP, true));
//    seqMoveUnboundedNoDelay(new EflWalkStep(RIGHT, true));
//    for(int i=0;i<4;i++)
//      seqMoveUnboundedNoDelay(new EflWalkStep(UP, true));
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    {
//      seqUnbounded(new EflWalkToSegment(8, 68)); // grass
////      seq(new EflEncounterSegment(SANDSHREW, UP));
////      save("tmp");
//////      load("tmp");
////      seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(30));
////      seqUnbounded(new EflWalkToSegment(10, 62)); // grass
//      seq(new EflEncounterSegment(FEAROW, UP));
//      save("tmp2");
////      load("tmp2");
//      seq(new EflCatchMonSegment());
//    }
//
//    seq(new EflWalkToSegment(12, 57)); // badge check
//    seq(new EflWalkToSegment(12, 56)); // badge check
//    seq(new EflSkipTextsSegment(4)); // badge check
//    seq(new EflWalkToSegment(7, 35)); // badge check
//    seq(new EflSkipTextsSegment(4)); // badge check
//    seq(new EflWalkToSegment(4, 31)); // enter victory road
//
//    save("vr6");
//    load("vr6");
//
//    seq(new EflSelectItemSegment(HM04).fromOverworld().andUse());
//    seq(new EflLearnTMSegment(MANKEY, 1)); // leer -> strength
//    seqEflButton(B);
//    seqUnbounded(new EflSelectMonSegment(MANKEY).fromMainMenu().andStrength());
//    seqUnbounded(new EflWalkToSegment(5, 14));
//    seqUnbounded(new EflWalkToSegment(5, 15, false));
//    seqUnbounded(new EflWalkToSegment(5, 15, false));
//    seqUnbounded(new EflWalkToSegment(4, 16));
//    seqUnbounded(new EflWalkToSegment(5, 16, false));
//    seqUnbounded(new EflWalkToSegment(5, 16, false));
//    seqUnbounded(new EflWalkToSegment(6, 16, false));
//    seqUnbounded(new EflWalkToSegment(7, 16, false));
//    seqUnbounded(new EflWalkToSegment(8, 16, false));
//    seqUnbounded(new EflWalkToSegment(9, 17));
//    seqUnbounded(new EflWalkToSegment(9, 16, false));
//    seqUnbounded(new EflWalkToSegment(9, 16, false));
//    seqUnbounded(new EflWalkToSegment(9, 15, false));
//    seqUnbounded(new EflWalkToSegment(8, 14));
//    seqUnbounded(new EflWalkToSegment(9, 14, false));
//    seqUnbounded(new EflWalkToSegment(9, 14, false));
//    seqUnbounded(new EflWalkToSegment(10, 14, false));
//    seqUnbounded(new EflWalkToSegment(11, 14, false));
//    seqUnbounded(new EflWalkToSegment(12, 14, false));
//    seqUnbounded(new EflWalkToSegment(13, 14, false));
//    seqUnbounded(new EflWalkToSegment(14, 14, false));
//    seqUnbounded(new EflWalkToSegment(15, 14, false));
//    seqUnbounded(new EflWalkToSegment(16, 15));
//    seqUnbounded(new EflWalkToSegment(16, 14, false));
//    seqUnbounded(new EflWalkToSegment(16, 14, false));
//    seqUnbounded(new EflWalkToSegment(16, 13, false));
//    seqUnbounded(new EflWalkToSegment(14, 14));
//    seqUnbounded(new EflWalkToSegment(15, 12));
//    seqUnbounded(new EflWalkToSegment(16, 12, false));
//    seqUnbounded(new EflWalkToSegment(17, 11));
//    seqUnbounded(new EflWalkToSegment(17, 12, false));
//    seqUnbounded(new EflWalkToSegment(17, 12, false));
//
//    seqUnbounded(new EflWalkToSegment(13, 14)); // align
//    save("tmp");
//    load("tmp");
//    seq(new EflEncounterSegment(MAROWAK, LEFT));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(10, 14)); // align
//    seq(new EflEncounterSegment(ZUBAT, LEFT));
//    save("tmp3");
//	  load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(8, 15)); // align
//    seq(new EflEncounterSegment(MACHOP, LEFT));
//    save("tmp4");
//    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(6, 16)); // align
//    seq(new EflEncounterSegment(ONIX, LEFT));
//    save("tmp5");
//    load("tmp5");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(5, 15));
//    seqUnbounded(new EflWalkToSegment(5, 14)); // align
//    seq(new EflEncounterSegment(GEODUDE, UP));
//    save("tmp6");
//    load("tmp6");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(40));
//    seqUnbounded(new EflWalkToSegment(6, 12)); // align
//    seq(new EflEncounterSegment(GRAVELER, RIGHT));
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(17, 2)); // align
//    seqUnbounded(new EflWalkToSegment(16, 2)); // align
//    seq(new EflEncounterSegment(GOLBAT, LEFT));
//    seq(new EflCatchMonSegment());
//
//    seq(new EflWalkToSegment(15, 2));
//    seq(new EflWalkToSegment(14, 2, false));
//    seq(new EflWalkToSegment(14, 2, false));
//    seq(new EflWalkToSegment(13, 2, false));
//    seq(new EflWalkToSegment(12, 2, false));
//    seq(new EflWalkToSegment(11, 3));
//    seq(new EflWalkToSegment(11, 2, false));
//    seq(new EflWalkToSegment(11, 2, false));
//    seq(new EflWalkToSegment(10, 2));
//    seqMove(new EflOverworldInteract(4)); // Rare Candy
//    seq(new EflTextSegment()); // Rare Candy
//    seq(new EflWalkToSegment(14, 2));
//
//    seq(new EflWalkToSegment(1, 1)); // ladder up
//
//    save("vr7");
//    load("vr7");
//
//    seq(new EflSelectMonSegment(MANKEY).fromOverworld().andStrength());
//    seq(new EflWalkToSegment(5, 14));
//    seq(new EflWalkToSegment(4, 14, false));
//    seq(new EflWalkToSegment(4, 14, false));
//    seq(new EflWalkToSegment(3, 13));
//    seq(new EflWalkToSegment(3, 14, false));
//    seq(new EflWalkToSegment(3, 14, false));
//    seq(new EflWalkToSegment(3, 15, false));
//    seq(new EflWalkToSegment(4, 16));
//    seq(new EflWalkToSegment(3, 16, false));
//    seq(new EflWalkToSegment(3, 16, false));
//    seq(new EflWalkToSegment(2, 16, false));
//    seq(new EflWalkToSegment(23, 7)); // ladder up
//
//    seq(new EflSelectMonSegment(MANKEY).fromOverworld().andStrength());
//    seq(new EflWalkToSegment(22, 5));
//    seq(new EflWalkToSegment(22, 3, false));
//    seq(new EflWalkToSegment(22, 3, false));
//    seq(new EflWalkToSegment(22, 2, false));
//    seq(new EflWalkToSegment(23, 1));
//    seq(new EflWalkToSegment(22, 1, false));
//    seq(new EflWalkToSegment(22, 1, false));
//    seq(new EflWalkToSegment(21, 1, false));
//    seq(new EflWalkToSegment(20, 1, false));
//    seq(new EflWalkToSegment(19, 1, false));
//    seq(new EflWalkToSegment(18, 1, false));
//    seq(new EflWalkToSegment(17, 1, false));
//    seq(new EflWalkToSegment(16, 1, false));
//    seq(new EflWalkToSegment(15, 1, false));
//    seq(new EflWalkToSegment(14, 1, false));
//    seq(new EflWalkToSegment(13, 1, false));
//    seq(new EflWalkToSegment(12, 1, false));
//    seq(new EflWalkToSegment(11, 1, false));
//    seq(new EflWalkToSegment(10, 1, false));
//    seq(new EflWalkToSegment(9, 1, false));
//    seq(new EflWalkToSegment(8, 1, false));
//    seq(new EflWalkToSegment(7, 1, false));
//    seq(new EflWalkToSegment(6, 0));
//    seq(new EflWalkToSegment(6, 1, false));
//    seq(new EflWalkToSegment(6, 1, false));
//    seq(new EflWalkToSegment(7, 2));
//    seq(new EflWalkToSegment(6, 2, false));
//    seq(new EflWalkToSegment(6, 2, false));
//    seq(new EflWalkToSegment(5, 2, false));
//    seq(new EflWalkToSegment(4, 2, false));
//    seq(new EflWalkToSegment(3, 2, false));
//    seq(new EflWalkToSegment(2, 1));
//    seq(new EflWalkToSegment(2, 2, false));
//    seq(new EflWalkToSegment(2, 2, false));
//    seq(new EflWalkToSegment(2, 3, false));
//    seq(new EflWalkToSegment(2, 4, false));
//    seq(new EflWalkToSegment(1, 5));
//    seq(new EflWalkToSegment(2, 5, false));
//    seq(new EflWalkToSegment(2, 5, false));
//    {
//      seq(new EflWalkToSegment(2, 0)); // ladder down
//      seq(new EflWalkToSegment(11, 3)); // moltres
//      seq(new EflWalkToSegment(11, 4)); // moltres
//      seqMove(new EflOverworldInteract(6)); // moltres
//      seq(new EflSkipTextsSegment(1));
//      seq(new EflCatchMonSegment()); // moltres
//      seq(new EflWalkToSegment(1, 1)); // ladder up
//    }
//
//    save("vr8");
//    load("vr8");
//
//    seq(new EflSelectMonSegment(MANKEY).fromOverworld().andStrength());
//    seq(new EflWalkToSegment(18, 1));
//    seq(new EflWalkToSegment(19, 1));
//    seq(new EflWalkToSegment(20, 15));
//    seq(new EflWalkToSegment(22, 15, false));
//    seq(new EflWalkToSegment(22, 15));
//    seq(new EflWalkToSegment(23, 15)); // fall down
//    seqEflSkipInput(1);
//
//    seq(new EflSelectMonSegment(MANKEY).fromOverworld().andStrength());
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(24, 16));
//    seq(new EflWalkToSegment(23, 16, false));
//    seq(new EflWalkToSegment(23, 16, false));
//    seq(new EflWalkToSegment(23, 16));
//    seq(new EflWalkToSegment(22, 16, false));
//    seq(new EflWalkToSegment(21, 16, false));
//    seq(new EflWalkToSegment(20, 16, false));
//    seq(new EflWalkToSegment(19, 16, false));
//    seq(new EflWalkToSegment(18, 16, false));
//    seq(new EflWalkToSegment(17, 16, false));
//    seq(new EflWalkToSegment(16, 16, false));
//    seq(new EflWalkToSegment(15, 16, false));
//    seq(new EflWalkToSegment(14, 16, false));
//    seq(new EflWalkToSegment(13, 16, false));
//    seq(new EflWalkToSegment(12, 16, false));
//    seq(new EflWalkToSegment(11, 16, false));
//    seq(new EflWalkToSegment(10, 16, false));
//    seq(new EflWalkToSegment(25, 14)); // ladder up
//    seq(new EflWalkToSegment(26, 8)); // ladder down
//    seq(new EflWalkToSegment(30, 7, false)); // leave victory road
//    seq(new EflWalkToSegment(10, -1)); // enter indigo plateau
//    seq(new EflWalkToSegment(10, 5)); // enter indigo plateau
//
//    save("vr9");
//    load("vr9");
//
////    seq(new EflWalkToSegment(15, 9)); // PC
////    seq(new EflWalkToSegment(15, 8)); // PC
////    {
////      seqEflButton(A); // use PC
////      seq(new EflSkipTextsSegment(1)); // turned on
////      seqEflButton(A); // someone's PC
////      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
////      seq(new EflChangeMonBoxSegment(0)); // box 1
////      seq(new EflDepositMonSegment(TENTACRUEL));
////      seq(new EflWithdrawMonSegment(ODDISH));
////      seq(new EflChangeMonBoxSegment(3)); // box 4
////      seqEflButton(B, MENU); // cancel
////      seqEflButton(B, PRESSED); // cancel
////    }
////    seqMetric(new OutputParty());
////    seqMetric(new OutputBoxMons());
////
////    seqEflSkipInput(1);
//    seq(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSwitchWith(ODDISH));
//    seqEflButton(B);
//    seq(new EflSelectItemSegment(ELIXER).fromMainMenu().andUse());
//    seq(new EflSelectMonSegment(DRAGONAIR));
//    seq(new EflSkipTextsSegment(1, true)); // PP restored
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seqMetric(new OutputParty());
//
//    save("vr10");
//    load("vr10");
//
//    seqMetric(new OutputParty());
//
//    seq(new EflWalkToSegment(8, 0)); // Elite Four
//    seq(new EflWalkToSegment(4, 2)); // Lorelei
//    seq(new EflWalkToSegment(5, 2, false)); // Lorelei
//    seqMove(new EflOverworldInteract(1));
//
//    seq(new EflInitFightSegment(9)); // start fight
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(AURORA_BEAM)));
//    seq(new EflUseBattleItemSegment(X_SPEED, EflEnemyMoveDesc.missWith(AURORA_BEAM)));
//    seqEflButton(UP | A, PRESSED); // fight
//    seqEflButton(DOWN, PRESSED); // fight
//    save("tmp");
//    load("tmp");
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.skipFirstMainBattleMenu = true;
//      kems.attackCount[1][1] = 1; // horn drill
//      kems.numExpGainers = 5; // oddish, boosted, lvlup to 23, dragonair, lvlup to 36
//      seq(kems); // dewgong
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // cloyster
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      kems.numExpGainers = 2; // dragonair, lvlup to 37
//      seq(kems); // slowbro
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seqMetric(new OutputParty());
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // jynx
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // lapras
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflEvolutionSegment()); // Gloom
//    seq(new EflSkipTextsSegment(4)); // after battle text
//
//    save("vr11");
//    load("vr11");
//
//    seq(new EflSelectMonSegment(GLOOM).fromOverworld().andSwitchWith(DRAGONAIR));
//    seqEflButton(B);
//    seq(new EflSelectItemSegment(ELIXER).fromMainMenu().andUse());
//    seq(new EflSelectMonSegment(DRAGONAIR));
//    seq(new EflSkipTextsSegment(1, true)); // PP restored
//    seqEflButton(B);
//    seqEflButton(START);
//    seq(new EflWalkToSegment(4, -1, false));
//    seq(new EflWalkToSegment(4, 2)); // Bruno
//    seq(new EflWalkToSegment(5, 2, false)); // Bruno
//    seqMove(new EflOverworldInteract(1));
//
//    seq(new EflInitFightSegment(10)); // start fight
//    seq(new EflUseBattleItemSegment(X_SPEED, EflEnemyMoveDesc.missWith(ROCK_THROW)));
//    seqEflButton(UP | A, PRESSED); // fight
//    seqEflButton(DOWN, PRESSED); // fight
//    save("tmp");
//    load("tmp");
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.skipFirstMainBattleMenu = true;
//      kems.attackCount[1][1] = 1; // horn drill, lvlup to 38
//      kems.numExpGainers = 2; // dragonair, lvlup to 38
//      seq(kems); // onix
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // hitmonchan
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // hitmonlee
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // onix
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      kems.numExpGainers = 2; // dragonair, lvlup to 39
//      seq(kems); // machamp
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflSkipTextsSegment(2)); // after battle text
//
//    save("vr12");
//    load("vr12");
//
//    seq(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSwitchWith(MANKEY));
//    seqEflButton(B);
//    seqEflSkipInput(4); // TODO: Fix
//    seq(new EflSelectItemSegment(ELIXER).fromMainMenu().andUse().withDebugOutput());
//    seq(new EflSelectMonSegment(DRAGONAIR));
//    seq(new EflSkipTextsSegment(1, true)); // PP restored
//    seqEflButton(B);
//    seqEflButton(START);
//    seqMetric(new OutputParty());
//    seq(new EflWalkToSegment(4, -1, false));
//    seq(new EflWalkToSegment(4, 2)); // Agatha
//    seq(new EflWalkToSegment(5, 2, false)); // Agatha
//    seqMove(new EflOverworldInteract(1));
//
//    seq(new EflInitFightSegment(12)); // start fight
//    seq(new EflUseBattleItemSegment(X_ATTACK, EflEnemyMoveDesc.missWith(new CheckSleepEffectMisses(), HYPNOSIS)));
//    seq(new EflUseBattleItemSegment(X_ATTACK, EflEnemyMoveDesc.missWith(new CheckSleepEffectMisses(), HYPNOSIS)));
//    seq(new EflUseBattleItemSegment(X_ATTACK, EflEnemyMoveDesc.missWith(new CheckSleepEffectMisses(), HYPNOSIS)));
//    seq(new EflUseBattleItemSegment(X_ATTACK, EflEnemyMoveDesc.missWith(new CheckSleepEffectMisses(), HYPNOSIS)));
//    seqEflButton(UP | A, PRESSED); // fight
//    save("tmp");
//    load("tmp");
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckSleepEffectMisses(), HYPNOSIS)};
//      kems.skipFirstMainBattleMenu = true;
//      kems.attackCount[0][0] = 2; // dig
//      kems.numExpGainers = 3; // mankey, boosted, lvlup to 22
//      seq(kems); // gengar
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckConfusionEffectMisses(), SUPERSONIC)};
////      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(WING_ATTACK)};
//      kems.attackCount[3][0] = 3; // rock slide
//      kems.numExpGainers = 3; // mankey, boosted, lvlup to 24
//      seq(kems); // golbat
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckSleepEffectMisses(), HYPNOSIS)};
//      kems.attackCount[0][0] = 1; // dig
//      kems.numExpGainers = 3; // mankey, boosted, lvlup to 25
//      seq(kems); // haunter
//    }
//    save("tmp4");
//    load("tmp4");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SCREECH)};
//      kems.attackCount[0][0] = 2; // dig
//      kems.numExpGainers = 3; // mankey, boosted, lvlup to 26
//      seq(kems); // arbok
//    }
//    save("tmp5");
//    load("tmp5");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), TOXIC)};
//      kems.attackCount[0][0] = 2; // dig
//      kems.numExpGainers = 3; // mankey, boosted, lvlup to 28
//      seq(kems); // gengar
//    }
//    seq(new EflEndFightSegment(2)); // player defeated enemy
//    seq(new EflEvolutionSegment()); // Primeape
//    seq(new EflSkipTextsSegment(4)); // after battle text
//
//    save("vr13");
//    load("vr13");
//
//    seq(new EflSelectMonSegment(PRIMEAPE).fromOverworld().andSwitchWith(IVYSAUR));
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(4, -1, false)); // Lance
//    seq(new EflWalkToSegment(6, 10)); // Lance
//    seq(new EflWalkToSegment(5, 10)); // Lance
//    seq(new EflWalkToSegment(5, 1)); // Lance
//
//    seq(new EflInitFightSegment(13)); // start fight
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(DRAGON_RAGE)));
//    seq(new EflUseBattleItemSegment(X_SPEED, EflEnemyMoveDesc.missWith(DRAGON_RAGE)));
//    seq(new EflUseBattleItemSegment(X_SPEED, EflEnemyMoveDesc.missWith(DRAGON_RAGE)));
//    seqEflButton(UP | A, PRESSED); // fight
//    seqEflButton(DOWN, PRESSED); // fight
//    save("tmp");
//    load("tmp");
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.skipFirstMainBattleMenu = true;
//      kems.attackCount[1][1] = 1; // horn drill
//      kems.numExpGainers = 3; // ivysaur, boosted, dragonair
//      seq(kems); // gyarados
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // dragonair
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      kems.numExpGainers = 2; // dragonair, lvlup to 40
//      seq(kems); // dragonair
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // aerodactyl
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      kems.numExpGainers = 2; // dragonair, lvlup to 41
//      seq(kems); // dragonite
//    }
//    seq(new EflEndFightSegment(3)); // player defeated enemy
//    seq(new EflSkipTextsSegment(14)); // after battle text
//
//    save("vr14");
//    load("vr14");
//
////    seq(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSwitchWith(IVYSAUR));
////    seqEflButton(B);
//    seq(new EflSelectItemSegment(Constants.MAX_ETHER).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(DRAGONAIR));
//    seq(new EflTextSegment(B)); // which move?
//    seqEflButton(DOWN); // Horn Drill
//    seqEflButton(A); // Horn Drill
//    seq(new EflSkipTextsSegment(1, true)); // PP restored
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(5, -1, false));
//
//    seq(new EflInitFightSegment(18)); // start fight
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(Metric.TRUE, WHIRLWIND)));
//    seq(new EflUseBattleItemSegment(X_SPEED, EflEnemyMoveDesc.missWith(Metric.TRUE, WHIRLWIND)));
//    seq(new EflUseBattleItemSegment(X_SPEED, EflEnemyMoveDesc.missWith(Metric.TRUE, WHIRLWIND)));
//    seqEflButton(UP | A, PRESSED); // fight
//    seqEflButton(DOWN, PRESSED); // fight
//    save("tmp");
    load("tmp");
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.skipFirstMainBattleMenu = true;
      kems.attackCount[1][1] = 1; // horn drill
      kems.numExpGainers = 4; // ivysaur, boosted, lvlup to 31, dragonair
      seq(kems); // pidgeot
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // horn drill
      seq(kems); // alakazam
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // surf crit
      seq(kems); // rhydon
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // horn drill
      kems.numExpGainers = 2; // dragonair, lvlup to 42
      seq(kems); // gyarados
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // horn drill
      seq(kems); // arcanine
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // horn drill
      kems.numExpGainers = 2; // dragonair, lvlup to 43
      seq(kems); // venusaur
    }
    seq(new EflEndFightSegment(6)); // player defeated enemy

    save("vr15");
    load("vr15");

    seq(new EflSkipTextsSegment(6+28+15)); // Oak HoF
    seq(new EflTextSegment());
    seq(new EflTextSegment());
    seq(new EflSkipTextsSegment(2));
  }
}
