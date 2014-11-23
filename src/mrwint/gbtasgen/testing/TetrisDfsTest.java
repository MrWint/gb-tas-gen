package mrwint.gbtasgen.testing;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.RunUntil;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.tetris.TetrisRomInfo;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.LockPiece;
import mrwint.gbtasgen.tools.tetris.Piece;
import mrwint.gbtasgen.tools.tetris.heuristic.NullHeuristic;
import mrwint.gbtasgen.tools.tetris.search.AStar;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.SearchState;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.StateDist;
import mrwint.gbtasgen.util.Runner;

public class TetrisDfsTest extends SeqSegment {

  @Override
  protected void execute() {
    seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0x35 ? 1 : 0);}));
    seq(Move.START);
    seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0x7 ? 1 : 0);}));
    seq(Move.START | Move.DOWN);
    seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0xe ? 1 : 0);}));
    seq(Move.RIGHT);
    seq(Move.START);
    seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0x13 ? 1 : 0);}));
    seq(Move.DOWN);
    seq(Move.LEFT);
    seq(Move.DOWN);
    seq(Move.A);
    seq(Move.RIGHT);
    seq(Move.DOWN);
    seq(Move.RIGHT);
    //    seq(Move.A);
    seq(Move.START);
    seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0x0 ? 1 : 0);}));
    //    seq(new Wait(10));
    //    seq(() -> {
    //      short[] board = new short[Board.HEIGHT];
    //
    //      int startAddress = 0xc802;
    //      for (int y = 0; y < Board.HEIGHT; y++)
    //        for (int x = 0; x < Board.WIDTH; x++)
    //          if (Gb.readMemory(startAddress + y*0x20 + x) != 0x2f)
    //            board[y] |= 1 << x;
    //
    //      int curPiece = (Gb.readMemory(RomInfo.tetris.wCurPiece) >> 2);
    //      int previewPiece = (Gb.readMemory(RomInfo.tetris.wPreviewPiece) >> 2);
    //      int nextPreviewPiece = (Gb.readMemory(RomInfo.tetris.hNextPreviewPiece) >> 2);
    //      testTetrisDfs(board, new int[] {curPiece, previewPiece, nextPreviewPiece});
    //      return 1;
    //    });
    seq(new Wait(10));
    seq(new Move() {
      @Override public int getInitialKey() { return 0; }
      @Override
      public boolean doMove() {
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return true;
      }
    });
  }

  private static void testTetrisDfs(short[] board, int[] forcedPieces) {
    Board.print(board);
    SearchAlgorithm tetrisDFS = new AStar(board, forcedPieces, 1, LockPiece.NINE_HEART_DROP_DELAY, null, new NullHeuristic());
    StateDist stateDist = tetrisDFS.search();
    System.out.println("Dist: " + stateDist.dist);
    printDfsState(stateDist.state);
  }

  private static void printDfsState(SearchState state) {
    if (state.prevState != null) {
      printDfsState(state.prevState);
      System.out.println();
      System.out.println("Used piece: " + Piece.PIECE_NAMES[state.prevPieceIndex]);
    }
    state.board.print();
  }

  public static void main(String[] args) {
    RomInfo.setRom(new TetrisRomInfo());

    Runner.run(new TetrisDfsTest());
  }
}
