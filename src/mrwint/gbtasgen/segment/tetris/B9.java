package mrwint.gbtasgen.segment.tetris;

import java.util.HashMap;
import java.util.Map.Entry;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.tetris.TetrisRomInfo;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.tetris.b9.Intro;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SleepSegment;
import mrwint.gbtasgen.state.ListStateBuffer;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.state.tetris.TetrisStateBuffer;
import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.LockPiece;
import mrwint.gbtasgen.tools.tetris.Piece;
import mrwint.gbtasgen.tools.tetris.SBLockPiece;
import mrwint.gbtasgen.tools.tetris.TetrisUtil;
import mrwint.gbtasgen.util.Runner;

public class B9 extends SeqSegment {

  private short[] getBoard() {
    short[] board = new short[Board.HEIGHT];

    int startAddress = 0xc802;
    for (int y = 0; y < Board.HEIGHT; y++)
      for (int x = 0; x < Board.WIDTH; x++)
        if (Gb.readMemory(startAddress + y*0x20 + x) != 0x2f)
          board[y] |= 1 << x;

    return board;
  }

  @Override
  protected void execute() {
    seq(new Intro());
//    seq(new MoveSegment(new PressButton(Move.RIGHT), 0));
//    for(int i=0;i<108;i++)
//      seq(new MoveSegment(new PressButton(0), 0));
//    for(int i=0;i<2;i++)
//      seq(new MoveSegment(new PressButton(0), 0));
//    seq(new MoveSegment(new PressButton(Move.LEFT), 0));

    seq(new Segment() {
      @Override
      public StateBuffer execute(StateBuffer in) {
        State s = in.getStates().iterator().next();
        s.restore();
        short[] board = getBoard();

        new Board(board).print();

        SBLockPiece sbLockPiece = new SBLockPiece(board, 1, LockPiece.NINE_DROP_DELAY, LockPiece.ZERO_DROP_DELAY, in);
        HashMap<Board, ListStateBuffer> result = sbLockPiece.lockPiece();
        for (Entry<Board, ListStateBuffer> e : result.entrySet()) {
          if ((e.getKey().board[7] & 0x100) != 0 &&
              (e.getKey().board[6] & 0x100) != 0 &&
              (e.getKey().board[5] & 0x100) != 0 &&
              (e.getKey().board[5] & 0x200) != 0) {
            e.getKey().print();
            StateBuffer out = e.getValue().toStateBuffer(in);
            TetrisStateBuffer outs = e.getValue().toTetrisStateBuffer(in);
            System.out.println("List " + e.getValue() + " ");
            System.out.println("in: " + in + ", out: " + out + ", outs: " + outs);
            for (Entry<Short, StateBuffer> e2 : outs.getMap().entrySet()) {
              TetrisUtil.printNextPieces(e2.getKey());
              for (State ss : e2.getValue().getStates()) {
                try {

                  ss.restore();
                  State.steps(1);
                  System.out.println("before " + ss.rngState);
                  Thread.sleep(200);
                  State.steps(2);
                  System.out.println("after " + Piece.PIECE_NAMES[Gb.readMemory(RomInfo.tetris.hNextPreviewPiece) >> 2]);
                  Thread.sleep(200);
                } catch (InterruptedException e1) {
                  e1.printStackTrace();
                }
              }
            }
            return out;
          }
        }
        System.out.println("NOT FOUND!");
        return in;
      }
    });
//  seq(new Wait(1));
//    seq(0);
//    seq(Move.LEFT);
//    seq(new Wait(10));
    seq(new SleepSegment(1000));
    seq(new Wait(10));
    seq(new SleepSegment(1000));
  }

  public static void main(String[] args) {
    RomInfo.setRom(new TetrisRomInfo());

    Runner.run(new B9());
  }
}
