package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;

import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;import mrwint.gbtasgen.segment.pokemon.gen1.common.BuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class CeladonBlue extends SeqSegment {

  @Override
  public void execute() {
//    seq(new EflWalkToSegment(-1, 18)); // leave celadon
//    seq(new EflWalkToSegment(34, 9, false)); // to bush
//    seqMetric(new OutputParty());
//    {
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(1); // mon
//      seqEflScrollAF(3); // dux
//      seqEflButton(A, PRESSED); // cut
//      seqEflButton(B); // hacked away (no text scroll)?
//    }
//    seq(new EflWalkToSegment(23, 4, false)); // house
//    seq(new EflWalkToSegment(-1, 2, false)); // leave house
//    seq(new EflWalkToSegment(7, 5)); // fly house
//    seq(new EflWalkToSegment(2, 4)); // fly girl
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflSkipTextsSegment(5)); // get fly
//    seq(new EflWalkToSegment(2, 8, false)); // leave house
//    seq(new EflWalkToSegment(18, 5, false)); // house
//    seq(new EflWalkToSegment(8, 2, false)); // leave house
//    seq(new EflUseBikeSegment(1, 0));
//    seq(new EflWalkToSegment(34, 7)); // to bush
//    seq(new EflWalkToSegment(34, 8)); // to bush
//    {
//      seqEflButton(START, PRESSED);
//      seqEflButton(A); // items
//      seqEflScrollFastAF(13 + 1); // HM02
//      seqEflButton(A, PRESSED); // use
//      seq(new EflSkipTextsSegment(2)); // booted up HM, contains xyz
//      seq(new EflSkipTextsSegment(1, true)); // learn
//      seqEflSkipInput(1);
//      seqEflButton(A); // dux
//      seq(new EflSkipTextsSegment(1)); // learned HM
//  
//      seqEflButton(B, PRESSED); // exit item menu
//      seqEflScrollA(-1); // mon
//      seqEflSkipInput(1);
//      seqEflButton(A); // dux
//      seqEflButton(A, PRESSED); // cut
//      seqEflButton(B); // hacked away (no text scroll)?
//    }
//    seq(new EflWalkToSegment(40, 10)); // enter celadon
//
//    save("ce1");
//    load("ce1");
//
//    seq(new EflWalkToSegment(10, 13)); // enter mart
//    seq(new EflWalkToSegment(12, 1)); // 2nd floor
//    seq(new EflWalkToSegment(16, 1)); // 3rd floor
//    seq(new EflWalkToSegment(12, 1)); // 4th floor
//    seq(new EflWalkToSegment(16, 1)); // 5th floor
//    seq(new EflWalkToSegment(12, 1)); // roof
//    seq(new EflWalkToSegment(12, 2, false)); // go to vending machine
//    seqMove(new EflOverworldInteract(5));
//    seq(new EflSkipTextsSegment(1)); // vending machine
//    seqEflButton(A); // buy fresh water
//    seq(new EflSkipTextsSegment(1)); // popped out
//    seqMove(new EflOverworldInteract(5));
//    seq(new EflSkipTextsSegment(1)); // vending machine
//    seqEflButton(A); // buy fresh water
//    seq(new EflSkipTextsSegment(1)); // popped out
//    seqMove(new EflOverworldInteract(5));
//    seq(new EflSkipTextsSegment(1)); // vending machine
//    seqEflButton(DOWN | A); // buy soda pop
//    seqUnbounded(new EflSkipTextsSegment(1)); // popped out
//    seqUnbounded(new EflWalkToSegment(7, 4)); // TODO: optimize position
//    seqUnbounded(new EflWalkToSegment(6, 4, false)); // TODO: optimize position
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(A);
//        seqMetric(new EflOverworldInteract.OverworldInteractMetric(2));
//      }
//    });
//    
//    save("tmp");
//    load("tmp");
//    
//    seq(new EflSkipTextsSegment(2));
//    seq(new EflSkipTextsSegment(1, true)); // give drink
//    seqEflButton(A, PRESSED); // fresh water
//    seq(new EflSkipTextsSegment(7)); // get ice beam
//    seqMove(new EflOverworldInteract(2));
//    seq(new EflSkipTextsSegment(2));
//    seq(new EflSkipTextsSegment(1, true)); // give drink
//    seqEflButton(DOWN | A, PRESSED); // fresh water
//    seq(new EflSkipTextsSegment(7)); // get rock slide
//    seq(new EflWalkToSegment(15,2)); // 5th
//    {
//      seq(new EflWalkToSegment(5, 4, false)); // left cashier
//      seqMove(new EflOverworldInteract(3));
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment());
//      {
//        seq(new EflBuyItemSegment(5, 8)); // X Speed x8
//        seq(new EflBuyItemSegment(0, 8, true)); // X Atk x4
//      }
//      seqEflButton(B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(16,1)); // 4th
//
//    save("tmp");
    load("tmp");

    {
      seq(new EflWalkToSegment(5, 6, false)); // left cashier
      seqMove(new EflOverworldInteract(1));
      {
        seq(new EflTextSegment(B));
        seqEflButton(DOWN | A); // sell
        seq(new EflTextSegment(B)); // what to sell
        
        seqMetric(new OutputItems());
      }
      
      
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment(B));
//      {
//        seq(new EflBuyItemSegment(4, 3)); // Leaf Stone x3
//        seq(new EflBuyItemSegment(0, 2)); // Thunder Stone x2
//        seq(new EflBuyItemSegment(1, 3, true)); // Water Stone x3
//      }
//      seqEflButton(B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
    }
//    seq(new EflWalkToSegment(12,1)); // 3rd
//    seq(new EflWalkToSegment(16,1)); // 2nd
//    {
//      seq(new EflWalkToSegment(9,3)); // right cashier
//      seq(new EflWalkToSegment(8,3)); // right cashier
//      seqMove(new EflOverworldInteract(2));
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment());
//      {
//        seq(Segment.scrollFastA(6)); // TM05
//        seq(Segment.scrollA(-1)); // buy x2 (1 extra for item management)
//        seq(new EflSkipTextsSegment(1)); // confirmation text
//        seq(new EflSkipTextsSegment(1, true)); // "yes"
//        seq(new EflSkipTextsSegment(1)); // thank you text
//      }
//      seq(Segment.repress(Move.B)); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(12,1)); // 1st
//    seq(new EflWalkToSegment(16,8,false)); // out

	  
	  
	  
	  
	  
	  
	  
//	  {
//      seq(new EflWalkToSegment(31, 27)); // enter
//      seq(new EflWalkToSegment(1, 1)); // coin case
//      seqMove(new EflOverworldInteract(5));
//      seq(new EflSkipTextsSegment(7));
//      seq(new EflWalkToSegment(3,  8, false));
//      seq(new EflWalkToSegment(28, 19)); // enter
//
//      {
//        seq(new EflWalkToSegment(15, 9));
//        seqButton(A);
//        seq(new EflSkipTextsSegment(1));
//        seq(new EflWalkToSegment(17, 14));
//        seqButton(A);
//
//        save("tmp");
//        load("tmp");
//
//        seq(new EflSkipTextsSegment(1, true)); // plays
//        seqSkipInput(1);
//        delay(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqMetric(new PressMemoryMetric(A, 0xcd4c, 0x80));
//            seqButton(A);
//          }
//        });
//        delay(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqMetric(new PressMemoryMetric(A, 0xcd42, 0x2));
//            seqButton(A);
//          }
//        });
//        delay(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqMetric(new PressMemoryMetric(A, 0xcd45, 0x2));
//            seqButton(A);
//          }
//        });
//        delay(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqMetric(new PressMemoryMetric(A, 0xcd49, 0x2));
//            seqButton(A);
//          }
//        });
//        seqSkipInput(1);
//        seqButton(Move.B);
//        seqButton(Move.A); // again?
//        seq(new EflTextSegment(Move.B)); // how many?
//        delay(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqMetric(new PressMemoryMetric(A, 0xcd4c, 0x80));
//            seqButton(A);
//          }
//        });
//        seq(new EflTextSegment(Move.B));
//        seqSkipInput(1);
//        seqButton(Move.A);
//        seqSkipInput(7);
//        seqButton(Move.A);
//        seqSkipInput(23);
//        seqButton(Move.A);
//        seqSkipInput(10);
//        delay(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqMetric(new PressMemoryMetric(A, 0xcd42, 0x2));
//            seqButton(A);
//          }
//        });
//        delay(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqMetric(new PressMemoryMetric(A, 0xcd46, 0x2));
//            seqButton(A);
//          }
//        });
//        delay(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqMetric(new PressMemoryMetric(A, 0xcd49, 0x2));
//            seqButton(A);
//          }
//        });
//        seq(new EflSkipTextsSegment(1));
//        seq(new EflSkipTextsSegment(1, true));
//        seq(new EflSkipTextsSegment(1, true)); // one more?
//        seq(new EflTextSegment(Move.B)); // how many?
//        seq(new EflSkipTextsSegment(1, true)); // again
//      }
//      {
//        seq(new EflWalkToSegment(5,  7, false));
//        seqMove(new EflOverworldInteract(2));
//        seq(new EflSkipTextsSegment(3));
//        seq(new EflSkipTextsSegment(1, true));
//        seq(new EflSkipTextsSegment(1));
//        seqMove(new EflOverworldInteract(2));
//        seq(new EflSkipTextsSegment(3));
//        seq(new EflSkipTextsSegment(1, true));
//        seq(new EflSkipTextsSegment(1));
//        seqMove(new EflOverworldInteract(2));
//        seq(new EflSkipTextsSegment(3));
//        seq(new EflSkipTextsSegment(1, true));
//        seq(new EflSkipTextsSegment(1));
//        seqMove(new EflOverworldInteract(2));
//        seq(new EflSkipTextsSegment(3));
//        seq(new EflSkipTextsSegment(1, true));
//        seq(new EflSkipTextsSegment(1));
//        seqMove(new EflOverworldInteract(2));
//        seq(new EflSkipTextsSegment(3));
//        seq(new EflSkipTextsSegment(1, true));
//        seq(new EflSkipTextsSegment(1));
//        seqMove(new EflOverworldInteract(2));
//        seq(new EflSkipTextsSegment(3));
//        seq(new EflSkipTextsSegment(1, true));
//        seq(new EflSkipTextsSegment(1));
//        seqMove(new EflOverworldInteract(2));
//        seq(new EflSkipTextsSegment(3));
//        seq(new EflSkipTextsSegment(1, true));
//        seq(new EflSkipTextsSegment(1));
//      }
//	  }

//
//    save("t1");
//    load("t1");
//
//    seq(new UseBikeSegment(0, 0));
//
//    save("t2");
//    load("t2");
//
//    seqSkipInput(1);
//    seq(new EflWalkToSegment(14, 5)); // enter tower
//    seq(new EflWalkToSegment(18, 9)); // l2
//    seq(new EflWalkToSegment(15, 5)); // engage rival
//		seq(new InitFightSegment(6)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // ice beam crit
//			seq(kems); // pidgeotto
//		}
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43)}; // leer
//      kems.attackCount[1][1] = 1; // bite crit
//      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // gyarados
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
////      kems.attackCount[1][1] = 1; // bite crit
//      kems.numExpGainers = 2; // L31;
//      seq(kems); // growlithe
//    }
//    seq(new CancelMoveLearnSegment()); // Withdraw
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
////      kems.attackCount[1][0] = 1; // bite
//      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // kadabra
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // ivysaur
//    }
//		seq(new EndFightSegment(2)); // player defeated enemy
//		seq(new EflSkipTextsSegment(10)); // after battle texts
//
//	  save("t3");
//	  load("t3");
//
//    seq(new EflWalkToSegment(3, 9)); // l3
//    seq(new EflWalkToSegment(18, 9)); // l4
//
//    seq(new EflWalkToSegment(17, 7)); // engage
//    seq(new EflWalkToSegment(16, 7)); // engage
//    seqMove(new EflOverworldInteract(2)); // talk to trainer
//    seq(new InitFightSegment(1)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
////      kems.attackCount[3][1] = 1; // bubblebeam crit
//      seq(kems); // gastly
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
////      kems.attackCount[3][1] = 1; // bubblebeam crit
//      seq(kems); // gastly
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//    {
//      seq(new EflWalkToSegment(13, 10)); // elixer
//      seqMove(new EflOverworldInteract(4)); // elixer
//      seq(new EflTextSegment());
//    }
//    seq(new EflWalkToSegment(3, 9)); // l5
////    {
////      seq(new EflWalkToSegment(4, 11)); // elixer
////      seqButton(Move.A); // elixer
////      seq(new EflTextSegment());
////    }
//    seq(new EflWalkToSegment(11, 9)); // heal
//    seq(new EflSkipTextsSegment(2)); // healed
//    seq(new EflWalkToSegment(18, 9)); // l6
//    seq(new EflWalkToSegment(15, 5)); // engage
//    seq(new InitFightSegment(1)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.numExpGainers = 2; // L32;
//      seq(kems); // gastly
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//    seq(new EflWalkToSegment(11, 5)); // engage
//    seq(new EflWalkToSegment(10, 5)); // engage
//    seqMove(new EflOverworldInteract(2)); // talk to trainer
//    seq(new InitFightSegment(1)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      seq(kems); // gastly
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//    seq(new EflWalkToSegment(6, 6)); // rare candy
//    seq(new EflWalkToSegment(6, 7)); // rare candy
//    seqMove(new EflOverworldInteract(4)); // rare candy
//    seq(new EflTextSegment());
//    seq(new EflWalkToSegment(10, 16)); // marowak
//    seq(new EflSkipTextsSegment(1)); // begone
//    seq(new EflSkipTextsSegment(2)); // ghost appeared
//    seq(new EflTextSegment()); // go
//    seqButton(Move.DOWN); // items
//    seqButton(Move.A); // items
//    seq(Segment.scrollFastA(11)); // poke doll
//    seq(new EflSkipTextsSegment(1)); // used poke doll
//    seq(new EflSkipTextsSegment(4)); // ghost gone
//    seq(new EflWalkToSegment(9, 16)); // l7
//
//    save("t4");
//    load("t4");
//
//    seq(new EflWalkToSegment(10, 11, false)); // engage trainer
//
//		seq(new InitFightSegment(1)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//      seq(kems); // zubat
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//      seq(kems); // zubat
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ice beam crit
//      seq(kems); // golbat
//    }
//		seq(new EndFightSegment(1)); // player defeated enemy
//		seq(new EflSkipTextsSegment(1)); // no forget this
//    seq(new EflWalkToSegment(10, 9, false)); // engage trainer
//    seq(new InitFightSegment(4)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam
//      kems.numExpGainers = 2;
//      seq(kems); // koffing
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[0][0] = 1; // mega kick
//      seq(kems); // drowzee
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//    seq(new EflSkipTextsSegment(3)); // no forget this
//    seq(new EflWalkToSegment(10, 7, false)); // engage trainer
//    seq(new InitFightSegment(1)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//      seq(kems); // zubat
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//      seq(kems); // rattata
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//      seq(kems); // raticate
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//      seq(kems); // zubat
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//    seq(new EflSkipTextsSegment(1)); // no forget this
//    seq(new EflWalkToSegment(10, 5)); // engage old man
//    seq(new EflWalkToSegment(10, 4)); // engage old man
//    seqMove(new EflOverworldInteract(4)); // talk to old man
//    seq(new EflSkipTextsSegment(12));
//
//    save("t5");
////    load("t5");
//
//    seq(new EflWalkToSegment(2, 1)); // engage old man
//    seq(new EflWalkToSegment(3, 1, false)); // engage old man
//    seqMove(new EflOverworldInteract(5)); // talk to old man
//    seq(new EflSkipTextsSegment(10)); // get flute
//    seq(new EflWalkToSegment(2, 8, false)); // leave house
	}
}
