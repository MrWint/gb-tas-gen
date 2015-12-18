package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ABRA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMELEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DIGLETT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DROWZEE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DUGTRIO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MEOWTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCREECH;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class SurgeRed extends SeqSegment {

	@Override
	public void execute() {
//    seqEflButton(A); // continue game
//    seqEflButton(START);
//    seqEflButton(A);
//    seqEflButton(START);
//    seqEflButton(A);
//
//    seq(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//
//    seq(new EflSelectMonSegment(ABRA).fromOverworld().andTeleport());
//    seqEflSkipInput(1);
//
////    {
////      seq(new EflWalkToSegment(27, 4)); // bush
////      seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
////      seq(new EflWalkToSegment(19, 5)); // enter
////      seq(new EflWalkToSegment(15, 4)); // amber
////      seq(new EflWalkToSegment(15, 3)); // amber
////      seqMove(new EflOverworldInteract(3)); // talk
////      seq(new EflSkipTextsSegment(11)); // get amber
////      seq(new EflWalkToSegment(16, 6)); // leave
////      seq(new EflWalkToSegment(16, 8, false)); // leave
////      seq(new EflWalkToSegment(19, 7, false)); // ledge
////    }
//
//    save("su1");
//    load("su1");
//
//    seq(new EflWalkToSegment(18, 36)); // route 2
//
//    seq(new EflWalkToSegment(5, 8)); // bush
//    seq(new EflWalkToSegment(5, 9)); // bush
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
//
//    {
//      seq(new EflWalkToSegment(15, 19)); // enter
//      seq(new EflWalkToSegment(4, 2)); // trade
//      seq(new EflWalkToSegment(4, 1, false)); // trade
//      seqMove(new EflOverworldInteract(2)); // trade
//      seq(new EflSkipTextsSegment(1));
//      seq(new EflSkipTextsSegment(1, true)); // yes
//      seq(new EflSelectMonSegment(ABRA));
//      seq(new EflSkipTextsSegment(1)); // connect cables
//      seq(new EflSkipTextsSegment(3)); // traded x for y, thanks
//      seq(new EflWalkToSegment(3, 6)); // leave trade house
//      seq(new EflWalkToSegment(3, 8, false)); // leave trade house
//    }
//
//    seqUnbounded(new EflWalkToSegment(12, 9)); // enter diglett's cave
//    seqUnbounded(new EflWalkToSegment(4, 4)); // enter diglett's cave
//
//    save("tmp");
    load("tmp");

    seqUnbounded(new EflWalkToSegment(12, 20)); // align
    seqUnbounded(new EflWalkToSegment(12, 21)); // align
    seq(new EflEncounterSegment(DUGTRIO, DOWN));
    save("tmp2");
    load("tmp2");
   seq(new EflCatchMonSegment().withBufferSize(0));


    seqUnbounded(new EflWalkToSegment(33, 31)); // align

    seq(new EflEncounterSegment(DIGLETT, RIGHT));
    save("tmp3");
//    load("tmp3");
    seq(new EflCatchMonSegment().withBufferSize(0));


    seqUnbounded(new EflWalkToSegment(37, 31)); // leave cave
    seqUnbounded(new EflWalkToSegment(3, 8, false)); // leave cave

    seqUnbounded(new EflWalkToSegment(11, 6)); // grass

    seqUnbounded(new EflEncounterSegment(DROWZEE, RIGHT));
    seq(new EflCatchMonSegment());
    seq(new EflWalkToSegment(-1, 6)); // enter vermilion

    save("su2");
    load("su2");

    {
      seq(new EflWalkToSegment(23, 13)); // enter mart

      seq(new EflWalkToSegment(3, 5));
      seq(new EflWalkToSegment(2, 5));
      seq(new MoveSegment(new EflOverworldInteract(1)));
      {
        seq(new EflSkipTextsSegment(1, true)); // buy
        seq(new EflTextSegment(B));
        seq(new EflBuyItemSegment(0, 20, true)); // 20 balls // TODO: less
        seqEflButton(B); // cancel
        seq(new EflSkipTextsSegment(1)); // cancel
        seq(new EflSkipTextsSegment(1)); // bye
      }
      seq(new EflWalkToSegment(3,8,false)); // leave mart
    }

    seq(new EflWalkToSegment(9, 13)); // enter fan club
    seq(new EflWalkToSegment(2, 1)); // go to leader
    seqMove(new EflOverworldInteract(5)); // talk to leader
    seq(new EflSkipTextsSegment(6));
    seq(new EflSkipTextsSegment(1, true)); // hear about mon
    seq(new EflSkipTextsSegment(25));
    seq(new EflWalkToSegment(2, 6));
    seq(new EflWalkToSegment(2, 7, false)); // leave house
    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButton(DOWN);
        seqMetric(new StateResettingMetric() {
          @Override
          public int getMetricInternal() {
            EflUtil.runToAddressNoLimit(0, 0, 0x197ca); // after setting first trash can
            return curGb.readMemory(0xd743); // first trash can
          }
        }, Comparator.EQUAL, 8);
      }
    });
    seq(new EflWalkToSegment(15, 16)); // go to bush
    seq(new EflWalkToSegment(15, 17)); // go to bush
    seq(new EflSelectMonSegment(CHARMELEON).fromOverworld().andSwitchWith(MEOWTH));
    seqEflSkipInput(0);
    seq(new EflSelectMonSegment(FARFETCHD).andCut());
    seq(new EflWalkToSegment(12, 19)); // enter gym
    {
      seq(new EflWalkToSegment(4, 11)); // go to trash can
      seqEflButton(Move.RIGHT); // turn left
    }
    delayEfl(new SeqSegment() { // activate first can
      @Override
      protected void execute() {
        seqEflButton(A);
        seqMetric(new StateResettingMetric() {
          @Override
          public int getMetricInternal() {
            EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.printLetterDelayJoypadAddress); // after setting second trash can
            return curGb.readMemory(0xd744); // first trash can
          }
        }, Comparator.EQUAL, 7);
      }
    });
    seq(new EflSkipTextsSegment(4)); // opened first lock
    {
      seq(new EflWalkToSegment(4, 9)); // go to surge
      seqEflButton(RIGHT);
    }
    seqEflButton(A);
    seq(new EflSkipTextsSegment(2)); // opened second lock
    seq(new EflWalkToSegment(5, 3)); // go to surge
    seq(new EflWalkToSegment(5, 2)); // go to surge
    seqMove(new EflOverworldInteract(1)); // talk to surge

    save("su3");
    load("su3");

    seq(new EflInitFightSegment(10)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SCREECH)};
      kems.attackCount[2][0] = 1; // bite
      kems.attackCount[2][1] = 1; // bite crit
      kems.numExpGainers = 2; // Meowth, boosted
      seq(kems); // voltorb
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), GROWL)};
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(THUNDER_SHOCK)};
      kems.attackCount[2][1] = 1; // bite crit
      kems.numExpGainers = 2; // Meowth, boosted
      seq(kems); // pikachu
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), GROWL)};
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(THUNDERBOLT)};
      kems.attackCount[2][0] = 1; // bite
      kems.attackCount[2][1] = 2; // bite crit
      kems.numExpGainers = 3; // Meowth, boosted, lvlup to 21
      seq(kems); // raichu
    }

    save("su4");
    load("su4");

    seq(new EflEndFightSegment(3)); // player defeated enemy

    seq(new EflSkipTextsSegment(8)); // after battle texts
    seq(new EflWalkToSegment(5, 18, false)); // leave gym
	}
}
