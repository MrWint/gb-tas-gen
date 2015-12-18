package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.SELECT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ARCANINE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BELLSPROUT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMELEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.COMET_PUNCH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CONFUSION;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DITTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DOUBLE_KICK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DOUBLE_SLAP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGONAIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGON_RAGE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ELIXER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGUTOR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FIRE_PUNCH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FIRE_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FURY_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GLOOM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWLITHE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM04;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.IVYSAUR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.JOLTEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.JYNX;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KABUTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGMAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDOKING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.OMANYTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOTTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_GAS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_POWDER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PONYTA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PSYBEAM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PSYCHIC;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.REFLECT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROLLING_KICK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SAND_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STUN_SPORE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACOOL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACRUEL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM07;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMoveNew;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckParalyzeEffectMisses;
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
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.CancelMoveLearnSegment;
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
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class SilphCoRed extends SeqSegment {

	@Override
	public void execute() {
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//    seqMetric(new OutputBoxMons());
//
//    {
//      seq(new EflWalkToSegment(13, 25)); // enter center
//      seq(new EflWalkToSegment(13, 5)); // PC
//      seq(new EflWalkToSegment(13, 4)); // PC
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflDepositMonSegment(NIDOKING));
//        seq(new EflDepositMonSegment(JYNX));
//        seq(new EflWithdrawMonSegment(GROWLITHE));
//        seq(new EflWithdrawMonSegment(PONYTA));
//        seq(new EflChangeMonBoxSegment(3)); // box 4
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, PRESSED); // cancel
//      }
//
//      seq(new EflWalkToSegment(4, 6)); // leave center
//      seq(new EflWalkToSegment(4, 8, false)); // leave center
//    }
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(2)); // Fuchsia
//    seqEflSkipInput(1);
//
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(23, 28, false)); // ledge
//    seq(new EflWalkToSegment(27, 27)); // warden house
//    seq(new EflWalkToSegment(2, 5)); // warden
//    seq(new EflWalkToSegment(2, 4)); // warden
//    seqMove(new EflOverworldInteract(1)); //warden
//    seq(new EflSkipTextsSegment(11)); // get HM04
//    seq(new EflSelectItemSegment(FIRE_STONE).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(GROWLITHE));
//    seq(new EflEvolutionSegment()); // Arcanine
//    seq(new EflSelectItemSegment(HM04).andUse());
//    seq(new EflLearnTMSegment(EXEGGUTOR));
//    seq(new EflSelectItemSegment(TM07).andUse());
//    seq(new EflLearnTMSegment(PONYTA, 1)); // Tail Whip -> Horn Drill
//    seqEflButton(B); // cancel
//    seq(new EflSelectMonSegment(EXEGGUTOR).fromMainMenu().andStrength());
//    seq(new EflWalkToSegment(8, 4, false)); // boulder
//    seq(new EflWalkToSegment(8, 4, false)); // boulder
//    seq(new EflWalkToSegment(8, 3, false)); // boulder
//    seqMove(new EflOverworldInteract(2)); // Rare Candy
//    seq(new EflTextSegment()); // Rare Candy
//    seq(new EflWalkToSegment(5, 6)); // leave
//    seq(new EflWalkToSegment(5, 8, false)); // leave
//
//    save("si1");
//    load("si1");
//
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seqUnbounded(new EflWalkToSegment(40, 17)); // leave fuchsia
//    seqUnbounded(new EflWalkToSegment(8, 9, false)); // enter house
//    seqUnbounded(new EflWalkToSegment(8, 5, false)); // leave house
//    save("tmp");
////    load("tmp");
//    seqUnbounded(new EflWalkToSegment(17, 9)); // grass
//    seq(new EflEncounterSegment(DITTO, RIGHT));
//    save("tmp2");
////    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(10));
//
//    seqUnbounded(new EflWalkToSegment(20, 9)); // grass
//    seq(new EflEncounterSegment(GLOOM, RIGHT));
//    seq(new EflCatchMonSegment());
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(3)); // Celadon
//    seqEflSkipInput(1);
//
//    save("si2");
//    load("si2");
//
//    seqMetric(new OutputParty());
//    seq(new EflSelectMonSegment(KABUTO).fromOverworld().andSwitchWith(OMANYTE));
//    seqEflButton(B);
//    seq(new EflUseBikeSegment().fromMainMenu());
//
//    seq(new EflWalkToSegment(50, 10)); // leave celadon
//    seq(new EflWalkToSegment(12, 10, false)); // enter house
//
//    seq(new EflWalkToSegment(3, 4)); // talk to guard
//    seq(new EflSkipTextsSegment(15)); // give water
//    seq(new EflWalkToSegment(6, 4, false)); // leave house
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(20, 10)); // enter saffron
//    seq(new EflWalkToSegment(18, 21)); // enter silph co
//
//    seq(new EflWalkToSegment(20, 0)); // elevator
//    seq(new EflWalkToSegment(3, 2)); // elevator
//    seq(new EflWalkToSegment(3, 1)); // elevator
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflTextSegment());
//    seqEflScrollFastA(9); // 10F
//    seq(new EflWalkToSegment(2, 2)); // elevator
//    seq(new EflWalkToSegment(2, 4, false)); // elevator
//
//    seq(new EflWalkToSegment(4, 9)); // rocket
//
//    seq(new EflInitFightSegment(2)); // start fight
////    seq(new EflSwitchPokemonSegment(OMANYTE, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[2][1] = 1; // surf crit
//      kems.numExpGainers = 2; // Omanyte, boosted
//      seq(kems); // machoke
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("si3");
//    load("si3");
//
//    seq(new EflSelectMonSegment(OMANYTE).fromOverworld().andSwitchWith(PONYTA));
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(4, 14, false)); // rare candy
//    seqMove(new EflOverworldInteract(5)); // rare candy
//    seq(new EflTextSegment()); // rare candy
//
//    seq(new EflWalkToSegment(8, 0)); // 9f
//    seq(new EflWalkToSegment(16, 0)); // 8f
//    seq(new EflWalkToSegment(14, 0)); // 7f
//    seq(new EflWalkToSegment(22, 0)); // 6f
//    seq(new EflWalkToSegment(14, 0)); // 5f
//    {
//      seq(new EflWalkToSegment(14, 3)); // elixer
//      seq(new EflWalkToSegment(13, 3)); // elixer
//      seqEflButton(Move.A);
//      seq(new EflTextSegment()); // elixer
//    }
//    seq(new EflWalkToSegment(8, 15).setBlockAllWarps(true));
//    seqMove(new EflOverworldInteract(2));
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
////      kems.attackCount[2][0] = 1; // stomp // possible with 11+ atk
//      kems.attackCount[2][1] = 2; // stomp crit
//      seq(kems); // arbok
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflWalkToSegment(9, 15)); // warp
//    seq(new EflWalkToSegment(16, 15));
//    seq(new EflWalkToSegment(17, 15)); // warp back
//    seq(new EflWalkToSegment(20, 16));
//    seqMove(new EflOverworldInteract(8)); // card key
//    seq(new EflTextSegment());
//
//    save("si4");
//    load("si4");
//
//    seq(new EflWalkToSegment(9, 15)); // warp
//    seq(new EflWalkToSegment(16, 15));
//    seq(new EflWalkToSegment(17, 15)); // warp back
//    seq(new EflWalkToSegment(9, 13));
//    seq(new EflWalkToSegment(8, 13));
//    seqEflButton(Move.A); // use card key
//    seq(new EflTextSegment());
//    seq(new EflSkipTextsSegment(2)); // open door
//    seq(new EflWalkToSegment(3, 15)); // warp
//    seq(new EflWalkToSegment(18, 9));
//    seq(new EflWalkToSegment(17, 9, false));
//    seqEflButton(Move.A); // use card key
//    seq(new EflTextSegment());
//    seq(new EflSkipTextsSegment(2)); // open door
//    seq(new EflWalkToSegment(11, 11)); // warp
//    seq(new EflWalkToSegment(4, 2));
//    seq(new EflWalkToSegment(3, 2)); // engage rival
//
//    seq(new EflInitFightSegment(10)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // pidgeot
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      seq(kems); // Exeggcute
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // horn drill
//      kems.numExpGainers = 2; // ponyta, lvlup to 37
//      seq(kems); // Gyarados
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
//      seq(kems); // Charizard
//    }
//    seq(new EflEndFightSegment(2)); // player defeated enemy
//    seq(new EflSkipTextsSegment(14)); // after battle texts
//
//    save("si5");
//    load("si5");
//
//    seqMetric(new OutputItems());
//    seq(new EflSelectItemSegment(ELIXER).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(OMANYTE));
//    seq(new EflSkipTextsSegment(1, true)); // PP restored
//    seqEflSkipInput(0);
//    seq(new EflSelectItemSegment(ELIXER).andUse());
//    seq(new EflSelectMonSegment(PONYTA));
//    seq(new EflSkipTextsSegment(1, true)); // PP restored
//    seqEflButton(Move.B);
//    seq(new EflSelectMonSegment(PONYTA).fromMainMenu().andSwitchWith(KABUTO));
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(3, 5)); // lapras
//    seq(new EflWalkToSegment(2, 5)); // lapras
//    seqMove(new EflOverworldInteract(1)); // lapras
//    seq(new EflSkipTextsSegment(5));
//    seq(new EflTextSegment()); // get lapras
//    seq(new EflSkipTextsSegment(6)); // no nickname, no room
//    seq(new EflSkipTextsSegment(8)); // intelligent, bla bla
//
//    seq(new EflWalkToSegment(5, 7)); // warp
//    seq(new EflWalkToSegment(3, 16, false)); // engage
//    seqMove(new EflOverworldInteract(4));
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // surf
//      seq(kems); // cubone
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), POISON_GAS)};
//      kems.attackCount[2][1] = 2; // surf crit
//      seq(kems); // drowzee
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // surf
//      seq(kems); // marowak
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("si6");
//    load("si6");
//
//    seqMetric(new OutputParty());
//    seq(new EflSelectMonSegment(KABUTO).fromOverworld().andSwitchWith(OMANYTE));
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(6, 15));
//    seq(new EflWalkToSegment(6, 14));
//    seqEflButton(Move.A); // use card key
//    seq(new EflTextSegment());
//    seq(new EflSkipTextsSegment(2)); // open door
//    seq(new EflWalkToSegment(6, 13));
//    seq(new EflInitFightSegment(7)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(FURY_ATTACK)};
//      kems.attackCount[2][1] = 1; // Surf crit
//      kems.numExpGainers = 2; // omanyte, boosted
//      seq(kems); // nidorino
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//      kems.attackCount[3][1] = 1; // ice beam crit
////      kems.attackCount[2][1] = 1; // Surf crit
//      kems.numExpGainers = 3; // omanyte, boosted, lvlup to 37
//      seq(kems); // kangaskhan
//    }
//    save("tmp2");
//    load("tmp2");
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
////      kems.attackCount[0][0] = 1; // Water Gun
//      kems.attackCount[3][0] = 1; // Ice Beam // if PP
//      kems.numExpGainers = 2; // omanyte, boosted
//      seq(kems); // rhyhorn
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//      kems.attackCount[3][1] = 1; // ice beam crit
//      kems.numExpGainers = 3; // omanyte, boosted, lvlup to 38
//      seq(kems); // nidoqueen
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflSkipTextsSegment(7)); // after battle texts
//
//    save("si7");
//    load("si7");
//
//    seqMetric(new OutputParty());
//    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
//    seqEflSkipInput(2);
//    seq(new EflSelectMonSegment(OMANYTE).fromOverworld().andSwitchWith(KABUTO));
//    seqEflSkipInput(0);
//    seq(new EflSelectMonSegment(FARFETCHD).andFlyTo(1)); // Saffron
//    seqEflSkipInput(1);
//    seq(new EflUseBikeSegment().fromOverworld());
//
//    seq(new EflWalkToSegment(26, 3)); // dojo
//    seq(new EflWalkToSegment(2, 4)); // blackbelt
//    seqMove(new EflOverworldInteract(2)); // blackbelt
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[2][1] = 1; // Surf crit
//      seq(kems); // mankey
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[2][1] = 1; // Surf crit
//      kems.numExpGainers = 2; // kabuto, lvlup to 33
//      seq(kems); // mankey
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[2][0] = 2; // Surf crit
//      seq(kems); // primeape
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("si8");
//    load("si8");
//
//    seq(new EflSelectItemSegment(ELIXER).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(KABUTO));
//    seq(new EflSkipTextsSegment(1, true)); // PP restored
//    seqEflButton(B);
//    seqEflButton(START);
//    seqMetric(new OutputParty());
//
//    seq(new EflWalkToSegment(4, 3)); // blackbelt
//    seq(new EflInitFightSegment(6)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(DOUBLE_KICK, ROLLING_KICK)};
//      kems.attackCount[2][1] = 1; // Surf crit
//      seq(kems); // hitmonlee
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(COMET_PUNCH, FIRE_PUNCH)};
//      kems.attackCount[2][1] = 1; // Surf crit
//      kems.numExpGainers = 3; // kabuto, lvlup tp 34, learn absorb
//      seq(kems); // hitmonchan
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("si9");
    load("si9");

    seq(new EflSkipTextsSegment(7)); // after battle texts
    seq(new EflWalkToSegment(5, 1, false)); // hitmonchan
    seqMove(new EflOverworldInteract(7)); // hitmonchan
    seqEflButton(B); // skip dex
    seqEflButton(A); // skip dex
    seq(new EflSkipTextsSegment(1)); // do you want?
    seq(new EflSkipTextsSegment(1, true)); // want!
    seq(new EflTextSegment()); // got hitmonlee
    seq(new EflSkipTextsSegment(6)); // no nickname, no room
    seq(new EflWalkToSegment(4, 12, false)); // leave

    seq(new EflWalkToSegment(34, 3)); // gym
    seq(new EflWalkToSegment(11, 15)); // warp
    seq(new EflWalkToSegment(15, 15)); // warp
    seq(new EflWalkToSegment(15, 5)); // warp
    seq(new EflWalkToSegment(1, 5)); // warp
    seq(new EflWalkToSegment(9, 10));
    seq(new EflWalkToSegment(9, 9));
    seqMove(new EflOverworldInteract(1)); // talk to sabrina

    seq(new EflInitFightSegment(8)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(PSYBEAM, PSYCHIC)};
      kems.attackCount[0][1] = 2; // Scratch crit
      seq(kems); // kadabra
    }
    save("tmp");
    load("tmp");
    seq(EflNewEnemyMonSegment.any()); // next mon
    seq(new EflSwitchPokemonSegment(OMANYTE, EflEnemyMoveDesc.missWith(CONFUSION, DOUBLE_SLAP)));
    save("tmp2");
    load("tmp2");
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(CONFUSION, DOUBLE_SLAP)};
      kems.attackCount[2][1] = 1; // Surf crit
      kems.numExpGainers = 3; // kabuto, omanyte, boosted
      seq(kems); // mr.mime
    }
    save("tmp3");
    load("tmp3");
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckParalyzeEffectMisses(), STUN_SPORE)};
      kems.attackCount[3][0] = 1; // Ice Beam
      kems.attackCount[3][1] = 1; // Ice Beam crit
      kems.numExpGainers = 2; // omanyte, boosted
      seq(kems); // venomoth
    }
    save("tmp4");
    load("tmp4");
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, REFLECT)};
      kems.attackCount[2][1] = 1; // Surf crit
      kems.attackCount[3][1] = 1; // Ice Beam crit
      kems.numExpGainers = 3; // omanyte, boosted, lvlup to 39
      seq(kems); // alakazam
    }
    seq(new EflCancelMoveLearnSegment()); // Leer
    seq(new EflEndFightSegment(6)); // player defeated enemy

    save("si10");
    load("si10");

    seq(new EflSkipTextsSegment(9)); // after battle texts (no room)
    seq(new EflSkipTextsSegment(3)); // after battle texts (room)

    seq(new EflWalkToSegment(11, 11)); // warp
    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
    seqEflSkipInput(2);
  }
}
