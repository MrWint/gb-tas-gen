package mrwint.gbtasgen.tools.tetris;

public class Piece {
  public static final int WIDTH = 4;
  public static final int HEIGHT = 4;

  private final byte[][][] data;
  private final short[] board;
  private int curRotation;
  private int offsetY;
  private int offsetX;

  public static Piece fromIndex(int index, short[] board) {
    return new Piece(indexToData[index], board);
  }
  private Piece(byte[][][] data, short[] board) {
    this.data = data;
    this.board = board;
    this.curRotation = 0;
    this.offsetY = -1;
    this.offsetX = 3;
  }
  public Piece(Piece piece) {
    this.data = piece.data;
    this.board = piece.board;
    this.curRotation = piece.curRotation;
    this.offsetY = piece.offsetY;
    this.offsetX = piece.offsetX;
  }

  public byte[][] getData() {
    return data[curRotation];
  }

  private boolean checkCollision() {
    byte[][] curData = getData();
    for (int y = 0; y < HEIGHT; y++) {
      for (int x = 0; x < WIDTH; x++) {
        if (curData[y][x] == 1) {
          int boardX = x + offsetX;
          int boardY = y + offsetY;
          if (boardX < 0 || boardX >= Board.WIDTH || boardY >= Board.HEIGHT)
            return false;
          if (boardY < 0 || ((board[boardY] >> boardX) & 1) == 0)
            continue;
          return false;
        }
      }
    }
    return true;
  }

  public boolean canMoveLeft() {
    moveLeft();
    boolean collision = checkCollision();
    moveRight();
    return collision;
  }
  public Piece moveLeft() {
    offsetX--;
    return this;
  }

  public boolean canMoveRight() {
    moveRight();
    boolean collision = checkCollision();
    moveLeft();
    return collision;
  }
  public Piece moveRight() {
    offsetX++;
    return this;
  }

  public boolean canMoveDown() {
    moveDown();
    boolean collision = checkCollision();
    offsetY--; // moveUp();
    return collision;
  }
  public Piece moveDown() {
    offsetY++;
    return this;
  }

  public boolean canRotateA() {
    rotateA();
    boolean collision = checkCollision();
    rotateB();
    return collision;
  }
  public Piece rotateA() {
    curRotation = (curRotation + data.length - 1) % data.length;
    return this;
  }

  public boolean canRotateB() {
    rotateB();
    boolean collision = checkCollision();
    rotateA();
    return collision;
  }
  public Piece rotateB() {
    curRotation = (curRotation + 1) % data.length;
    return this;
  }

  public short[] lock() {
    short[] newBoard = board.clone();
    byte[][] curData = getData();
    for (int y = 0; y < HEIGHT; y++) {
      for (int x = 0; x < WIDTH; x++) {
        if (curData[y][x] == 1) {
          int boardX = x + offsetX;
          int boardY = y + offsetY;
          if (boardX < 0 || boardX >= Board.WIDTH || boardY >= Board.HEIGHT || boardY < 0)
            continue;
          newBoard[boardY] |= (1 << boardX);
        }
      }
    }
    return newBoard;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + curRotation;
    result = prime * result + offsetX;
    result = prime * result + offsetY;
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Piece other = (Piece) obj;
    if (curRotation != other.curRotation)
      return false;
    if (offsetX != other.offsetX)
      return false;
    if (offsetY != other.offsetY)
      return false;
    return true;
  }
  @Override
  public String toString() {
    return "Piece [curRotation=" + curRotation + ", offsetY=" + offsetY
        + ", offsetX=" + offsetX + "]";
  }


  private static final byte[][][] L = {
    {
      {0,0,0,0},
      {0,0,0,0},
      {1,1,1,0},
      {1,0,0,0},
    }, {
      {0,0,0,0},
      {0,1,0,0},
      {0,1,0,0},
      {0,1,1,0},
    }, {
      {0,0,0,0},
      {0,0,1,0},
      {1,1,1,0},
      {0,0,0,0},
    }, {
      {0,0,0,0},
      {1,1,0,0},
      {0,1,0,0},
      {0,1,0,0},
    }
  };

  private static final byte[][][] J = {
    {
      {0,0,0,0},
      {0,0,0,0},
      {1,1,1,0},
      {0,0,1,0},
    }, {
      {0,0,0,0},
      {0,1,1,0},
      {0,1,0,0},
      {0,1,0,0},
    }, {
      {0,0,0,0},
      {1,0,0,0},
      {1,1,1,0},
      {0,0,0,0},
    }, {
      {0,0,0,0},
      {0,1,0,0},
      {0,1,0,0},
      {1,1,0,0},
    }
  };

  private static final byte[][][] I = {
    {
      {0,0,0,0},
      {0,0,0,0},
      {1,1,1,1},
      {0,0,0,0},
    }, {
      {0,1,0,0},
      {0,1,0,0},
      {0,1,0,0},
      {0,1,0,0},
    }
  };

  private static final byte[][][] O = {
    {
      {0,0,0,0},
      {0,0,0,0},
      {0,1,1,0},
      {0,1,1,0},
    }
  };

  private static final byte[][][] Z = {
    {
      {0,0,0,0},
      {0,0,0,0},
      {1,1,0,0},
      {0,1,1,0},
    }, {
      {0,0,0,0},
      {0,1,0,0},
      {1,1,0,0},
      {1,0,0,0},
    }
  };

  private static final byte[][][] S = {
    {
      {0,0,0,0},
      {0,0,0,0},
      {0,1,1,0},
      {1,1,0,0},
    }, {
      {0,0,0,0},
      {1,0,0,0},
      {1,1,0,0},
      {0,1,0,0},
    }
  };

  private static final byte[][][] T = {
    {
      {0,0,0,0},
      {0,0,0,0},
      {1,1,1,0},
      {0,1,0,0},
    }, {
      {0,0,0,0},
      {0,1,0,0},
      {0,1,1,0},
      {0,1,0,0},
    }, {
      {0,0,0,0},
      {0,1,0,0},
      {1,1,1,0},
      {0,0,0,0},
    }, {
      {0,0,0,0},
      {0,1,0,0},
      {1,1,0,0},
      {0,1,0,0},
    }
  };
  private static byte[][][][] indexToData = {L, J, I, O, Z, S, T};
  public static String[] PIECE_NAMES = {"L", "J", "I", "O", "Z", "S", "T"};
}
