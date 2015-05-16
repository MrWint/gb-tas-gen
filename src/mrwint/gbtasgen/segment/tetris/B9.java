package mrwint.gbtasgen.segment.tetris;

import static mrwint.gbtasgen.tools.tetris.TetrisUtil.reverseBoardBits;
import mrwint.gbtasgen.rom.tetris.TetrisRomInfo;
import mrwint.gbtasgen.segment.SingleGbSegment;
import mrwint.gbtasgen.segment.tetris.b9.Intro;
import mrwint.gbtasgen.tools.tetris.LockPiece;
import mrwint.gbtasgen.tools.tetris.heuristic.CompositeHeuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.FieldEmptyHeuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.Heuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.NoOverhangHeuristic;
import mrwint.gbtasgen.util.SingleGbRunner;

public class B9 extends SingleGbSegment {

  @Override
  protected void execute() {
    seq(new Intro());
//    seq(new MoveSegment(new PressButton(Move.RIGHT), 0));
//    for(int i=0;i<108;i++)
//      seq(new MoveSegment(new PressButton(0), 0));
//    for(int i=0;i<2;i++)
//      seq(new MoveSegment(new PressButton(0), 0));
//    seq(new MoveSegment(new PressButton(Move.LEFT), 0));

    seq(new TetrisWrapperSegment(new TetrisSeqSegment() {
      @Override
      protected void execute() {
//        seq(new TetrisDfsSegment(4, LockPiece.NINE_DROP_DELAY, new short[] {
//          reverseBoardBits(0b0000000000),
//          reverseBoardBits(0b1111011111),
//          reverseBoardBits(0b1111011111),
//          reverseBoardBits(0b1111011111),
//          reverseBoardBits(0b1111011111),
//          reverseBoardBits(0b1111011111),
//          reverseBoardBits(0b0001110010),
//          reverseBoardBits(0b0000010010),
//        }, new CompositeHeuristic(new Heuristic[] {
//          new NoOverhangHeuristic(6),
//          new FieldEmptyHeuristic(0, 0b0000100000),
//          new FieldEmptyHeuristic(1, 0b0000100000),
//          new FieldEmptyHeuristic(2, 0b0000100000),
//          new FieldEmptyHeuristic(3, 0b0000100000),
//          new FieldEmptyHeuristic(4, 0b0000100000),
//          new FieldEmptyHeuristic(5, 0b0000100000),
//          new FieldEmptyHeuristic(6, 0b1110001101),
//          new FieldEmptyHeuristic(7, 0b1111101101),
//        }), new short[] {
//          2, 66, 130, 194, 258, 386, 10, 74, 138, 202, 330, 394, 18, 82, 146, 210, 402, 26, 90, 154, 218, 410, 50, 178, 242, 434, 114
//        },true));
//        save("b9_4");
//        load("b9_4");
//        seq(new TetrisWaitSegment(91));
//        seq(new TetrisDfsSegment(4, LockPiece.NINE_DROP_DELAY, new short[] {
//            reverseBoardBits(0b0000000000),
//            reverseBoardBits(0b0000000000),
//            reverseBoardBits(0b0000000000),
//            reverseBoardBits(0b0000000000),
//            reverseBoardBits(0b0000000000),
//            reverseBoardBits(0b1111011111),
//          }, new CompositeHeuristic(new Heuristic[] {
//            new NoOverhangHeuristic(6),
//            new FieldEmptyHeuristic(0, 0b0000100000),
//            new FieldEmptyHeuristic(1, 0b0000100000),
//            new FieldEmptyHeuristic(2, 0b0000100000),
//            new FieldEmptyHeuristic(3, 0b0000100000),
//            new FieldEmptyHeuristic(4, 0b0000100000),
//            new FieldEmptyHeuristic(5, 0b0000100000),
//          }), null, false));
//        save("b9_8");
//        load("b9_8");
//        seq(new TetrisDfsSegment(8, LockPiece.NINE_DROP_DELAY, new short[] {
//            reverseBoardBits(0b0000000000),
//            reverseBoardBits(0b1111011111),
//            reverseBoardBits(0b1111011111),
//            reverseBoardBits(0b1111011111),
//            reverseBoardBits(0b1111011111),
//            reverseBoardBits(0b1111011111),
//          }, new CompositeHeuristic(new Heuristic[] {
//            new NoOverhangHeuristic(6, 1),
//            new FieldEmptyHeuristic(0, 0b0000100000),
//            new FieldEmptyHeuristic(1, 0b0000100000),
//            new FieldEmptyHeuristic(2, 0b0000100000),
//            new FieldEmptyHeuristic(3, 0b0000100000),
//            new FieldEmptyHeuristic(4, 0b0000100000),
//            new FieldEmptyHeuristic(5, 0b0000100000),
//          }), new short[] {
//          2, 66, 130, 194, 258, 386, 10, 74, 138, 202, 330, 394, 18, 82, 146, 210, 402, 26, 90, 154, 218, 410, 50, 178, 242, 434, 114,
//          }, false));
//        save("b9_12");
        load("b9_12");
        seq(new TetrisDfsSegment(8, LockPiece.NINE_DROP_DELAY, new short[] {
            reverseBoardBits(0b0000000000),
            reverseBoardBits(0b1111001111),
            reverseBoardBits(0b1111001111),
            reverseBoardBits(0b1111001111),
            reverseBoardBits(0b1111001111),
            reverseBoardBits(0b1111011111),
          }, new CompositeHeuristic(new Heuristic[] {
            new NoOverhangHeuristic(6, 1),
            new FieldEmptyHeuristic(0, 0b0000100000),
            new FieldEmptyHeuristic(1, 0b0000100000),
            new FieldEmptyHeuristic(2, 0b0000100000),
            new FieldEmptyHeuristic(3, 0b0000100000),
            new FieldEmptyHeuristic(4, 0b0000100000),
            new FieldEmptyHeuristic(5, 0b0000100000),
          }), new short[] {
            0633,
          }, false));
        save("b9_20");
      }
    }));
//    seq(new Segment() {
//      @Override
//      public StateBuffer execute(StateBuffer in) {
//        State s = in.getStates().iterator().next();
//        s.restore();
//        short[] board = TetrisUtil.getBoard(Board.HEIGHT);
//
//        new Board(board).print();
//
//        SBLockPiece sbLockPiece = new SBLockPiece(board, 1, LockPiece.NINE_DROP_DELAY, LockPiece.ZERO_DROP_DELAY, in);
//        HashMap<Board, ListStateBuffer> result = sbLockPiece.lockPiece();
//        for (Entry<Board, ListStateBuffer> e : result.entrySet()) {
//          if ((e.getKey().board[7] & 0x100) != 0 &&
//              (e.getKey().board[6] & 0x100) != 0 &&
//              (e.getKey().board[5] & 0x100) != 0 &&
//              (e.getKey().board[5] & 0x200) != 0) {
//            e.getKey().print();
//            StateBuffer out = e.getValue().toStateBuffer(in);
//            TetrisStateBuffer outs = e.getValue().toTetrisStateBuffer(in);
//            System.out.println("List " + e.getValue() + " ");
//            System.out.println("in: " + in + ", out: " + out + ", outs: " + outs);
//            for (Entry<Short, StateBuffer> e2 : outs.getMap().entrySet()) {
//              TetrisUtil.printNextPieces(e2.getKey());
//              for (State ss : e2.getValue().getStates()) {
//                try {
//
//                  ss.restore();
//                  State.steps(1);
//                  System.out.println("before " + ss.rngState);
//                  Thread.sleep(200);
//                  State.steps(2);
//                  System.out.println("after " + Piece.PIECE_NAMES[Gb.readMemory(RomInfo.tetris.hNextPreviewPiece) >> 2]);
//                  Thread.sleep(200);
//                } catch (InterruptedException e1) {
//                  e1.printStackTrace();
//                }
//              }
//            }
//            return out;
//          }
//        }
//        System.out.println("NOT FOUND!");
//        return in;
//      }
//    });
//  seq(new Wait(1));
//    seq(0);
//    seq(Move.LEFT);
//    seq(new Wait(10));
//    seq(new SleepSegment(10000));
//    seq(new Wait(10));
//    seq(new SleepSegment(1000));
  }

  public static void main(String[] args) {
    SingleGbRunner.run(new TetrisRomInfo(), new B9());
  }
}
