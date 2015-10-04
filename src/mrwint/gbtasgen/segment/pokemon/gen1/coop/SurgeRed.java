package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
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
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class SurgeRed extends SeqSegment {

	@Override
	public void execute() {
    seqEflButton(Move.A); // continue game
    seqEflButton(Move.START);
    seqEflButton(Move.A);
    seqEflButton(Move.START);
    seqEflButton(Move.A);

    seq(new EflWalkToSegment(4, 6)); // leave center
    seq(new EflWalkToSegment(4, 8, false)); // leave center

//    {
//      seq(new EflWalkToSegment(19, 26)); // go to bush
//      seq(new EflWalkToSegment(19, 27)); // go to bush
//
//      {
//        seqEflButton(START, PRESSED);
//        seqEflScrollA(1); // mon
//        seqEflButton(A, PRESSED); // dux
//        seqEflSkipInput(1);
//        seqEflButton(A); // cut
//        seqEflButton(B); // hacked away (to text scroll)?
//      }
//      seq(new EflWalkToSegment(19, 33, false)); // ledge
//      seq(new EflWalkToSegment(19, 36)); // R5
//      seq(new EflWalkToSegment(9, 3, false)); // ledge
//      seq(new EflWalkToSegment(9, 7, false)); // ledge
//      seq(new EflWalkToSegment(9, 11, false)); // ledge
//      seq(new EflWalkToSegment(9, 15, false)); // ledge
//      seq(new EflWalkToSegment(10, 21)); // Pension
//      seq(new EflWalkToSegment(2, 4)); // man
//      seqMove(new EflOverworldInteract(1)); // man
//      seq(new EflSkipTextsSegment(2)); // raise one
//      seq(new EflSkipTextsSegment(1, true)); // of your mon
//      seq(new EflSkipTextsSegment(1)); // which one
//      seqEflScrollAF(2); // Magikarp
//      seq(new EflSkipTextsSegment(3)); // look after magikarp for a while, come back
//      seq(new EflWalkToSegment(2, 8, false)); // leave house
//      {
//        seqEflButton(START, PRESSED);
//        seqEflButton(Move.A); // mon
//        seqEflSkipInput(1);
//        seqEflScrollAF(-1); // Abra
//        seqEflButton(A, PRESSED); // teleport
//        seq(new EflTextSegment()); // teleport back
//        seqEflSkipInput(1);
//      }
//    }

    {
      seqEflButton(START, PRESSED);
      seqEflScrollA(1); // mon
      seqEflSkipInput(1);
      seqEflScrollAF(-1); // Abra
      seqEflButton(A, PRESSED); // teleport
      seq(new EflTextSegment()); // teleport back
      seqEflSkipInput(1);
    }
    seq(new EflWalkToSegment(27, 4)); // bush
    {
      seqEflButton(START, PRESSED);
      seqEflButton(A); // mon
      seqEflScrollAF(-2); // Dux
      seqEflButton(A, PRESSED); // cut
      seqEflButton(B); // cut away
    }
    seq(new EflWalkToSegment(19, 5)); // enter
    seq(new EflWalkToSegment(15, 4)); // amber
    seq(new EflWalkToSegment(15, 3)); // amber
    seqMove(new EflOverworldInteract(3)); // talk
    seq(new EflSkipTextsSegment(11)); // get amber
    seq(new EflWalkToSegment(16, 6)); // leave
    seq(new EflWalkToSegment(16, 8, false)); // leave

    save("su1");
    load("su1");

    seq(new EflWalkToSegment(19, 7, false)); // ledge
    seq(new EflWalkToSegment(18, 36)); // route 2

    seq(new EflWalkToSegment(5, 8)); // bush
    seq(new EflWalkToSegment(5, 9)); // bush
    {
      seqEflButton(START, PRESSED);
      seqEflButton(A); // mon
      seqEflButton(A, PRESSED); // Dux
      seqEflButton(A, PRESSED); // cut
      seqEflButton(B); // cut away
    }

    {
      seq(new EflWalkToSegment(15, 19)); // enter
      seq(new EflWalkToSegment(4, 2)); // trade
      seq(new EflWalkToSegment(4, 1, false)); // trade
      seqMove(new EflOverworldInteract(2)); // trade
      seq(new EflSkipTextsSegment(1));
      seq(new EflSkipTextsSegment(1, true)); // yes
      seqMetric(new OutputParty());
      seqEflScrollAF(2); // abra
      seq(new EflSkipTextsSegment(1)); // connect cables
      seq(new EflSkipTextsSegment(3)); // traded x for y, thanks
      seq(new EflWalkToSegment(3, 6)); // leave trade house
      seq(new EflWalkToSegment(3, 8, false)); // leave trade house

    }

//    { // cost: 2733 f
//      seq(new EflWalkToSegment(15, 20)); // bush
//      seq(new EflWalkToSegment(15, 21)); // bush
//      {
//        seqEflButton(START, PRESSED);
//        seqEflButton(A); // mon
//        seqEflButton(A, PRESSED); // Dux
//        seqEflButton(A, PRESSED); // cut
//        seqEflButton(B); // cut away
//      }
//      seq(new EflWalkToSegment(16, 34)); // house
//      seq(new EflWalkToSegment(16, 36, false)); // enter house
//      seq(new EflWalkToSegment(4, 8, false)); // leave house
//
//      seq(new EflWalkToSegment(15, 54)); // Moon Stone
//      seq(new EflWalkToSegment(14, 54)); // Moon Stone
//      seqMove(new EflOverworldInteract(1)); // Moon Stone
//      seq(new EflTextSegment()); // get Moon Stone
//      seq(new EflWalkToSegment(15, 39)); // enter house
//      seq(new EflWalkToSegment(5, 0)); // leave house
//      seq(new EflWalkToSegment(15, 24)); // bush
//      seq(new EflWalkToSegment(15, 23)); // bush
//      {
//        seqEflButton(START, PRESSED);
//        seqEflButton(A); // mon
//        seqEflButton(A, PRESSED); // Dux
//        seqEflButton(A, PRESSED); // cut
//        seqEflButton(B); // cut away
//      }
//    }

    seq(new EflWalkToSegment(12, 9)); // enter diglett's cave
    seq(new EflWalkToSegment(4, 4)); // enter diglett's cave

    seqUnbounded(new EflWalkToSegment(24, 31)); // align
    seqUnbounded(new EflWalkToSegment(25, 31)); // align

    seq(new EflEncounterSegment(0x76, Move.RIGHT)); // Dugtrio
    seq(new EflCatchMonSegment(0).withBufferSize(0));

    save("tmp");
//    load("tmp");

    seqUnbounded(new EflWalkToSegment(28, 31)); // align

    seq(new EflEncounterSegment(0x3b, Move.RIGHT)); // Dugtrio
    seq(new EflCatchMonSegment(0).withBufferSize(0));

    save("tmp2");
//    load("tmp2");

    seqUnbounded(new EflWalkToSegment(37, 31)); // leave cave
    seqUnbounded(new EflWalkToSegment(3, 8, false)); // leave cave

    seqUnbounded(new EflWalkToSegment(11, 6)); // grass

    seqUnbounded(new EflEncounterSegment(0x30, Move.RIGHT)); // Drowzee
    seq(new EflCatchMonSegment(0));
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
        seq(new EflBuyItemSegment(0, 24, true)); // 24 balls
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
        seqEflButton(Move.DOWN);
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
    {
      seqEflButton(START, PRESSED);
      seqEflScrollA(1); // mon
      seqEflScrollAF(-2); // meowth
      seqEflSkipInput(1);
      seqEflScrollAF(1); // switch
      seqEflSkipInput(1);
      seqEflScrollAF(2); // charmeleon
      seqEflSkipInput(1);
      seqEflScrollAF(3); // dux
      seqEflSkipInput(1);
      seqEflButton(A); // cut
      seqEflButton(B); // hacked away (to text scroll)?
    }
    seq(new EflWalkToSegment(12, 19)); // enter gym
    {
      seq(new EflWalkToSegment(4, 11)); // go to trash can
      seqEflButton(Move.RIGHT); // turn left
    }
    delayEfl(new SeqSegment() { // activate first can
      @Override
      protected void execute() {
        seqEflButton(Move.A);
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
      seqEflButton(Move.RIGHT);
    }
    seqEflButton(Move.A);
    seq(new EflSkipTextsSegment(2)); // opened second lock
    seq(new EflWalkToSegment(5, 3)); // go to surge
    seq(new EflWalkToSegment(5, 2)); // go to surge
    seqMove(new EflOverworldInteract(1)); // talk to surge

    save("su3");
    load("su3");

    seq(new EflInitFightSegment(10)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
      kems.attackCount[2][0] = 1; // bite
      kems.attackCount[2][1] = 1; // bite crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // voltorb
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(84)}; // thundershock
      kems.attackCount[2][1] = 1; // bite crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // pikachu
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(85)}; // thunderbolt
      kems.attackCount[2][0] = 1; // bite
      kems.attackCount[2][1] = 2; // bite crit
      kems.numExpGainers = 3; // boosted, lvlup to 21
      seq(kems); // raichu
    }

    save("su4");
    load("su4");

    seq(new EflEndFightSegment(3)); // player defeated enemy

    seq(new EflSkipTextsSegment(8)); // after battle texts
    seq(new EflWalkToSegment(5, 18, false)); // leave gym
	}
}
