package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import static mrwint.gbtasgen.move.Move.A;
import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.pokemon.PressMemoryMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.PokecenterSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.CancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.State;

public class TowerSquirtle extends SeqSegment {

	@Override
	public void execute() {
	  {
//      seq(new WalkToSegment(31, 27)); // enter
//      seq(new WalkToSegment(1, 1)); // coin case
//      seqMove(new OverworldInteract(5));
//      seq(new SkipTextsSegment(7));
//      seq(new WalkToSegment(3,  8, false));
//      seq(new WalkToSegment(28, 19)); // enter

      {
//        seq(new WalkToSegment(15, 9));
//        seqButton(A);
//        seq(new SkipTextsSegment(1));
//        seq(new WalkToSegment(17, 14));
//        seqButton(A);
//
//        save("tmp");
        load("tmp");

        seq(new SkipTextsSegment(1, true)); // plays
        seqSkipInput(1);
        delay(new SeqSegment() {
          @Override
          protected void execute() {
            seqMetric(new PressMemoryMetric(A, 0xcd4c, 0x80));
            seqButton(A);
          }
        });
        delay(new SeqSegment() {
          @Override
          protected void execute() {
            seqMetric(new PressMemoryMetric(A, 0xcd42, 0x2));
            seqButton(A);
          }
        });
        delay(new SeqSegment() {
          @Override
          protected void execute() {
            seqMetric(new PressMemoryMetric(A, 0xcd45, 0x2));
            seqButton(A);
          }
        });
        delay(new SeqSegment() {
          @Override
          protected void execute() {
            seqMetric(new PressMemoryMetric(A, 0xcd49, 0x2));
            seqButton(A);
          }
        });
        seqSkipInput(1);
        seqButton(Move.B);
        seqButton(Move.A); // again?
        seq(new TextSegment(Move.B)); // how many?
        delay(new SeqSegment() {
          @Override
          protected void execute() {
            seqMetric(new PressMemoryMetric(A, 0xcd4c, 0x80));
            seqButton(A);
          }
        });
        seq(new TextSegment(Move.B));
        seqSkipInput(1);
        seqButton(Move.A);
        seqSkipInput(7);
        seqButton(Move.A);
        seqSkipInput(23);
        seqButton(Move.A);
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
        seq(new SkipTextsSegment(1));
        seq(new SkipTextsSegment(1, true));
        seq(new SkipTextsSegment(1, true)); // one more?
        seq(new TextSegment(Move.B)); // how many?
//        seq(new SkipTextsSegment(1, true)); // again
      }
//      {
//        seq(new WalkToSegment(5,  7, false));
//        seqMove(new OverworldInteract(2));
//        seq(new SkipTextsSegment(3));
//        seq(new SkipTextsSegment(1, true));
//        seq(new SkipTextsSegment(1));
//        seqMove(new OverworldInteract(2));
//        seq(new SkipTextsSegment(3));
//        seq(new SkipTextsSegment(1, true));
//        seq(new SkipTextsSegment(1));
//        seqMove(new OverworldInteract(2));
//        seq(new SkipTextsSegment(3));
//        seq(new SkipTextsSegment(1, true));
//        seq(new SkipTextsSegment(1));
//        seqMove(new OverworldInteract(2));
//        seq(new SkipTextsSegment(3));
//        seq(new SkipTextsSegment(1, true));
//        seq(new SkipTextsSegment(1));
//        seqMove(new OverworldInteract(2));
//        seq(new SkipTextsSegment(3));
//        seq(new SkipTextsSegment(1, true));
//        seq(new SkipTextsSegment(1));
//        seqMove(new OverworldInteract(2));
//        seq(new SkipTextsSegment(3));
//        seq(new SkipTextsSegment(1, true));
//        seq(new SkipTextsSegment(1));
//        seqMove(new OverworldInteract(2));
//        seq(new SkipTextsSegment(3));
//        seq(new SkipTextsSegment(1, true));
//        seq(new SkipTextsSegment(1));
//      }
	  }

//    seq(new WalkToSegment(10, 13)); // enter mart
//    seq(new WalkToSegment(12, 1)); // 2nd floor
//    seq(new WalkToSegment(16, 1)); // 3rd floor
//    seq(new WalkToSegment(12, 1)); // 4th floor
//    seq(new WalkToSegment(16, 1)); // 5th floor
//    seq(new WalkToSegment(12, 1)); // roof
//    seq(new WalkToSegment(12,2,false)); // go to vending machine
//    seqMove(new OverworldInteract(5));
//    seq(new SkipTextsSegment(1)); // vending machine
//    seqButton(Move.A); // buy fresh water
//    seq(new SkipTextsSegment(1)); // popped out
//    seqMove(new OverworldInteract(5));
//    seq(new SkipTextsSegment(1)); // vending machine
//    seqButton(Move.A); // buy fresh water
//    seq(new TextSegment(Move.A, true, 0)); // popped out
//    seqMoveUnbounded(new PressButton(Move.B));
//    seq(new WalkToSegment(7,4).setMaxBufferSize(0)); // TODO: optimize position
//    seq(new WalkToSegment(6,4, false).setMaxBufferSize(0)); // TODO: optimize position
//    delay(new SeqSegment() {
//
//      @Override
//      protected void execute() {
//        seqMetric(new OverworldInteract.OverworldInteractMetric(2));
//        seqButton(Move.A);
//      }
//    });
//    seq(new SkipTextsSegment(2));
//    seq(new SkipTextsSegment(1, true)); // give drink
//    seq(Segment.repress(Move.A));
//    seq(new SkipTextsSegment(7)); // get ice beam
//    seq(new WalkToSegment(15,2)); // 5th
////    {
////      seq(new WalkToSegment(5, 4, false)); // left cashier
////      seqMove(new OverworldInteract(3));
////      seq(new SkipTextsSegment(1, true)); // buy
////      seq(new TextSegment());
////      {
////        seq(Segment.scrollFastA(5)); // X Speed
////        seq(Segment.scrollA(-1)); // buy x2
////        seq(new SkipTextsSegment(1)); // confirmation text
////        seq(new SkipTextsSegment(1, true)); // "yes"
////        seq(new SkipTextsSegment(1)); // thank you text
////      }
////      seq(Segment.repress(Move.B)); // cancel
////      seq(new SkipTextsSegment(2)); // cancel + bye
////    }
//    seq(new WalkToSegment(16,1)); // 4th
//    {
//      seq(new WalkToSegment(5, 6, false)); // left cashier
//      seqMove(new OverworldInteract(1));
//      seq(new SkipTextsSegment(1, true)); // buy
//      seq(new TextSegment());
//      {
//        seqSkipInput(1);
//        seqButton(Move.A); // poke doll
//        seq(Segment.repress(Move.A)); // buy x1
//        seq(new SkipTextsSegment(1)); // confirmation text
//        seq(new SkipTextsSegment(1, true)); // "yes"
//        seq(new SkipTextsSegment(1)); // thank you text
//      }
//      seq(Segment.repress(Move.B)); // cancel
//      seq(new SkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new WalkToSegment(12,1)); // 3rd
//    seq(new WalkToSegment(16,1)); // 2nd
//    {
//      seq(new WalkToSegment(9,3)); // right cashier
//      seq(new WalkToSegment(8,3)); // right cashier
//      seqMove(new OverworldInteract(2));
//      seq(new SkipTextsSegment(1, true)); // buy
//      seq(new TextSegment());
//      {
//        seq(Segment.scrollFastA(6)); // TM05
//        seq(Segment.scrollA(-1)); // buy x2 (1 extra for item management)
//        seq(new SkipTextsSegment(1)); // confirmation text
//        seq(new SkipTextsSegment(1, true)); // "yes"
//        seq(new SkipTextsSegment(1)); // thank you text
//      }
//      seq(Segment.repress(Move.B)); // cancel
//      seq(new SkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new WalkToSegment(12,1)); // 1st
//    seq(new WalkToSegment(16,8,false)); // out
//
//    save("t1");
//    load("t1");
//
//    seq(new UseBikeSegment(0, 0));
//    seq(new WalkToSegment(-1, 18)); // leave celadon
//    seq(new WalkToSegment(34, 9, false)); // to bush
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(-1)); // mon
//      seqSkipInput(1);
//      seq(Segment.scrollAF(-1)); // sandshrew
//      seq(Segment.repress(Move.A)); // cut
//      seqButton(Move.B); // hacked away (to text scroll)?
//    }
//    seq(new WalkToSegment(23, 4, false)); // house
//    seq(new WalkToSegment(-1, 2, false)); // leave house
////    seq(new UseBikeSegment(1, 0));
//    seq(new WalkToSegment(7, 5)); // fly house
//    seq(new WalkToSegment(2, 4)); // fly girl
//    seqMove(new OverworldInteract(1));
//    seq(new SkipTextsSegment(5)); // get fly
//    seq(new WalkToSegment(2, 8, false)); // leave house
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(1)); // items
//      seq(Segment.scrollFastA(14)); // HM02
//      seq(Segment.repress(Move.A)); // use
//      seq(new SkipTextsSegment(2)); // booted up HM, contains xyz
//      seq(new SkipTextsSegment(1, true)); // learn
//      seqSkipInput(1);
//      seq(Segment.scrollAF(-1)); // spearow
//      seq(new SkipTextsSegment(1)); // learned HM
//
//      seq(Segment.scrollFastAF(-1)); // TM05
//      seq(Segment.repress(Move.A)); // use
//      seq(new SkipTextsSegment(2)); // booted up TM, contains xyz
//      seq(new SkipTextsSegment(1, true)); // learn
//      seqSkipInput(1);
//      seq(Segment.scrollAF(-1)); // wartortle
//      seq(new SkipTextsSegment(5)); // learned TM
//      seq(new SkipTextsSegment(1, true)); // yes, overwrite
//      seq(new TextSegment(Move.B));
//      seqButton(Move.A); // mega punch
//      seq(new TextSegment(Move.A, false)); // 1,2 and
//      seqButton(Move.B); // skip 30f
//      seqButton(Move.B);
//      seq(new TextSegment()); // poof
//      seqButton(Move.A); // skip 30f
//      seqButton(Move.B);
//      seq(new SkipTextsSegment(3)); // learned TM
//
//      seq(Segment.scrollFastA(-2)); // TM13
//      seq(Segment.repress(Move.A)); // use
//      seq(new SkipTextsSegment(2)); // booted up TM, contains xyz
//      seq(new SkipTextsSegment(1, true)); // learn
//      seq(Segment.repress(Move.A)); // wartortle
//      seq(new SkipTextsSegment(5)); // learned TM
//      seq(new SkipTextsSegment(1, true)); // yes, overwrite
//      seq(new TextSegment());
//      seq(Segment.scrollAF(2)); // bubble
//      seq(new TextSegment(Move.A, false)); // 1,2 and
//      seqButton(Move.B); // skip 30f
//      seqButton(Move.B);
//      seq(new TextSegment()); // poof
//      seqButton(Move.A); // skip 30f
//      seqButton(Move.B);
//      seq(new SkipTextsSegment(2)); // learned TM
//      seq(new SkipTextsSegment(1, true)); // learned TM
//
//      seq(Segment.repress(Move.B)); // exit item menu
//      seq(Segment.scrollA(-1)); // mon
//      seqSkipInput(1);
//      seq(Segment.scrollAF(1)); // spearow
//      seq(Segment.repress(Move.A)); // fly
//      seq(Segment.scrollA(3)); // lavender
//    }
//
//    save("t2");
//    load("t2");
//
//    seqSkipInput(1);
//    seq(new WalkToSegment(14, 5)); // enter tower
//    seq(new WalkToSegment(18, 9)); // l2
//    seq(new WalkToSegment(15, 5)); // engage rival
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
//		seq(new SkipTextsSegment(10)); // after battle texts
//
//	  save("t3");
//	  load("t3");
//
//    seq(new WalkToSegment(3, 9)); // l3
//    seq(new WalkToSegment(18, 9)); // l4
//
//    seq(new WalkToSegment(17, 7)); // engage
//    seq(new WalkToSegment(16, 7)); // engage
//    seqMove(new OverworldInteract(2)); // talk to trainer
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
//      seq(new WalkToSegment(13, 10)); // elixer
//      seqMove(new OverworldInteract(4)); // elixer
//      seq(new TextSegment());
//    }
//    seq(new WalkToSegment(3, 9)); // l5
////    {
////      seq(new WalkToSegment(4, 11)); // elixer
////      seqButton(Move.A); // elixer
////      seq(new TextSegment());
////    }
//    seq(new WalkToSegment(11, 9)); // heal
//    seq(new SkipTextsSegment(2)); // healed
//    seq(new WalkToSegment(18, 9)); // l6
//    seq(new WalkToSegment(15, 5)); // engage
//    seq(new InitFightSegment(1)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.numExpGainers = 2; // L32;
//      seq(kems); // gastly
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//    seq(new WalkToSegment(11, 5)); // engage
//    seq(new WalkToSegment(10, 5)); // engage
//    seqMove(new OverworldInteract(2)); // talk to trainer
//    seq(new InitFightSegment(1)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      seq(kems); // gastly
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//    seq(new WalkToSegment(6, 6)); // rare candy
//    seq(new WalkToSegment(6, 7)); // rare candy
//    seqMove(new OverworldInteract(4)); // rare candy
//    seq(new TextSegment());
//    seq(new WalkToSegment(10, 16)); // marowak
//    seq(new SkipTextsSegment(1)); // begone
//    seq(new SkipTextsSegment(2)); // ghost appeared
//    seq(new TextSegment()); // go
//    seqButton(Move.DOWN); // items
//    seqButton(Move.A); // items
//    seq(Segment.scrollFastA(11)); // poke doll
//    seq(new SkipTextsSegment(1)); // used poke doll
//    seq(new SkipTextsSegment(4)); // ghost gone
//    seq(new WalkToSegment(9, 16)); // l7
//
//    save("t4");
//    load("t4");
//
//    seq(new WalkToSegment(10, 11, false)); // engage trainer
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
//		seq(new SkipTextsSegment(1)); // no forget this
//    seq(new WalkToSegment(10, 9, false)); // engage trainer
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
//    seq(new SkipTextsSegment(3)); // no forget this
//    seq(new WalkToSegment(10, 7, false)); // engage trainer
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
//    seq(new SkipTextsSegment(1)); // no forget this
//    seq(new WalkToSegment(10, 5)); // engage old man
//    seq(new WalkToSegment(10, 4)); // engage old man
//    seqMove(new OverworldInteract(4)); // talk to old man
//    seq(new SkipTextsSegment(12));
//
//    save("t5");
////    load("t5");
//
//    seq(new WalkToSegment(2, 1)); // engage old man
//    seq(new WalkToSegment(3, 1, false)); // engage old man
//    seqMove(new OverworldInteract(5)); // talk to old man
//    seq(new SkipTextsSegment(10)); // get flute
//    seq(new WalkToSegment(2, 8, false)); // leave house
	}
}
