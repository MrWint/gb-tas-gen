package mrwint.gbtasgen.tools.playback;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import mrwint.gbtasgen.tools.playback.loganalyzer.Palette;
import mrwint.gbtasgen.tools.playback.loganalyzer.Tile;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaySoundSetBg.BgOp;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.StateMap.ExtraBg;

public class ExtraTiles {
  private static <T> T[] concatenate (T[] a, T[] b) {
    int aLen = a.length;
    int bLen = b.length;

    @SuppressWarnings("unchecked")
    T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen+bLen);
    System.arraycopy(a, 0, c, 0, aLen);
    System.arraycopy(b, 0, c, aLen, bLen);

    return c;
  }
  public ExtraBg extraBg;
  public ExtraTiles() {
    Tile[] tiles = concatenate(concatenate(CHAR_TILES, LOGO_BORDER_TILES), CAKE_BORDER_TILES);
    Palette[] palettes = new Palette[] {CHAR_PALETTE, APERTURE_PALETTE};
    
    extraBg = new ExtraBg(tiles, palettes);
  }
  
  public BgOp getEmptyOp(int x, int y) {
    return new BgOp(getScreenPosition(x, y), getCharTile(' '), getCharAttributes(' '));
  }
  public BgOp getOp(char c, int x, int y) {
    return new BgOp(getTextPosition(x, y), getCharTile(c), getCharAttributes(c));
  }
  public BgOp getApertureOp(int index, int x, int y) {
    return new BgOp(getScreenPosition(x, y), getApertureTile(index), getApertureAttributes(index));
  }
  public int getCharTile(char c) {
    int index = CHAR_CODES.indexOf(c);
    if (index < 0)
      throw new RuntimeException("Character " + c + " not found in codes " + CHAR_CODES);
    return (extraBg.getTilePosition(index) % 0x180) & 0xff;
  }
  public int getCharAttributes(char c) {
    int index = CHAR_CODES.indexOf(c);
    if (index < 0)
      throw new RuntimeException("Character " + c + " not found in codes " + CHAR_CODES);
    return extraBg.getPalettePosition(0)
        | ((extraBg.getTilePosition(index) >= 0x180 ? 1 : 0) << 3)
        | ((extraBg.tileResults[index].flipHorizontally ? 1 : 0) << 5)
        | ((extraBg.tileResults[index].flipVertically ? 1 : 0) << 6);
  }
  public int getApertureTile(int index) {
    index += CHAR_CODES.length();
    return (extraBg.getTilePosition(index) % 0x180) & 0xff;
  }
  public int getApertureAttributes(int index) {
    index += CHAR_CODES.length();
    return extraBg.getPalettePosition(1)
        | ((extraBg.getTilePosition(index) >= 0x180 ? 1 : 0) << 3)
        | ((extraBg.tileResults[index].flipHorizontally ? 1 : 0) << 5)
        | ((extraBg.tileResults[index].flipVertically ? 1 : 0) << 6);
  }

  public int getScreenPosition(int x, int y) {
    return 0x9c00 + y * 0x20 + x;
  }
  public int getTextPosition(int x, int y) {
    return 0x9da1 + y * 0x20 + x;
  }
  
  private Map<Integer, BgOp> ops;
  public void addOp(int time, BgOp op) {
    int offset = 0;
    while (true) {
      if (time - offset < 0)
        throw new RuntimeException("negative time");
      if (!ops.containsKey(time - offset)) {
        ops.put(time - offset, op);
        return;
      }
      offset++;
    }
  }
  private int frame(int frame, int y) {
    return Math.max(0, frame * 154 + 8*y - 6);
  }
  private void renderText(String text, int x, int y, int startFrame, int frameDelay) {
    for (int i = 0; i < text.length(); i++)
      if (text.charAt(i) != ' ')
        addOp(frame(startFrame + i*frameDelay, 13 + y), getOp(text.charAt(i), x + i, y));
  }
  private void renderText(String text, int x, int y, int startFrame, int frameDelay, int... nextEvent) {
    renderText(text, x, y, startFrame, frameDelay);
    for (int i = 0; i < nextEvent.length && nextEvent[i] >= 0; i++) {
      clearText(text, x, y-i, startFrame + nextEvent[i]);
      if (i < nextEvent.length - 1 && y-(i+1) >= 0)
        renderText(text, x, y-(i+1), startFrame + nextEvent[i], 0);
    }
  }
  private void renderTwoLineText(String text1, String text2, int startFrame, int frameDelay, int clearAfter) {
    renderText(text1, 0, 1, startFrame, frameDelay, clearAfter);
    renderText(text2, 0, 3, startFrame + text1.length()*frameDelay, frameDelay, clearAfter - text1.length()*frameDelay);
  }
  private void renderScrollingText(int startFrame, int frameDelay, int clearAfter, String... texts) {
    renderText(texts[0], 0, 1, startFrame, frameDelay, (texts[0].length() + texts[1].length()) * frameDelay, (texts[0].length() + texts[1].length()) * frameDelay + 5);
    int sumDelay = texts[0].length() * frameDelay;
    for (int i = 1; i < texts.length - 2; i++) {
      renderText(texts[i], 0, 3, startFrame + sumDelay, frameDelay,
          texts[i].length()*frameDelay,
          texts[i].length()*frameDelay + 5,
          texts[i].length()*frameDelay + 5 + texts[i+1].length()*frameDelay,
          texts[i].length()*frameDelay + 5 + texts[i+1].length()*frameDelay + 5);
      sumDelay += texts[i].length()*frameDelay + 5;
    }
    int i = texts.length - 2;
    renderText(texts[i], 0, 3, startFrame + sumDelay, frameDelay,
        texts[i].length()*frameDelay,
        texts[i].length()*frameDelay + 5,
        clearAfter - sumDelay);
    sumDelay += texts[i].length()*frameDelay + 5;
    i++;
    renderText(texts[i], 0, 3, startFrame + sumDelay, frameDelay,
        clearAfter - sumDelay);
  }
  
  private void clearText(String text, int x, int y, int frame) {
    for (int i = 0; i < text.length(); i++)
      if (text.charAt(i) != ' ')
        addOp(frame(frame, 13 + y), getOp(' ', x + i, y));
  }

  private void renderApertureLogo(int x, int y, int frame) {
    for (int dy = 0; dy < 6; dy++)
      for (int dx = 0; dx < 6; dx++)
        addOp(frame(frame, y + dy), getApertureOp(dy*6 + dx, x + dx, y + dy));
  }

  private void renderCake(int x, int y, int frame) {
    for (int dy = 0; dy < 6; dy++)
      for (int dx = 0; dx < 6; dx++)
        addOp(frame(frame, y + dy), getApertureOp(36 + dy*6 + dx, x + dx, y + dy));
  }

  private void renderBlankBorder(int x, int y, int frame) {
    for (int dy = 0; dy < 8; dy++)
      for (int dx = 0; dx < 8; dx++)
        if (dy == 0 || dy == 7 || dx == 0 || dx == 7)
          addOp(frame(frame, y + dy), getApertureOp(64 + dy*8 + dx, x + dx, y + dy));
        else
          addOp(frame(frame, y + dy), getEmptyOp(x + dx, y + dy));
  }

  private void renderLogoBorder(int x, int y, int frame) {
    for (int dy = 0; dy < 8; dy++)
      for (int dx = 0; dx < 8; dx++)
        addOp(frame(frame + (dx + dy) * 2, y + dy), getApertureOp(dy*8 + dx, x + dx, y + dy));
  }

  private void renderCakeBorder(int x, int y, int frame) {
    for (int dy = 0; dy < 8; dy++)
      for (int dx = 0; dx < 8; dx++)
        addOp(frame(frame + (dx + dy) * 2, y + dy), getApertureOp(64 + dy*8 + dx, x + dx, y + dy));
  }

  private void clearLine(int y, int frame) {
    for (int x = 0; x < 20; x++)
      addOp(frame(frame + x*2, y), getEmptyOp(x, y));
  }

  private void clearLineReverse(int y, int frame) {
    for (int x = 0; x < 20; x++)
      addOp(frame(frame + x*2, y), getEmptyOp(19 - x, y));
  }


  public Map<Integer, BgOp> getPortalOps() {
    ops = new HashMap<>();

    renderTwoLineText("This was a", "triumph.", 20, 4, 210);

    renderTwoLineText("I'm making a", "note here:", 240, 4, 110);

    renderText("HUGE SUCCESS.", 2, 2, 360, 6, 170);
    
    renderScrollingText(540, 7, 420, "It's hard to", "overstate", "my satisfaction.");

    renderText("Aperture Science", 1, 2, 970, 4, 220);
    renderBlankBorder(0, 3, 965);
    renderLogoBorder(0, 3, 970);

    renderScrollingText(1200, 6, 300, "We do", "what we must", "because we can.");

    renderTwoLineText("For the good of", "all of us.", 1510, 7, 190);

    renderTwoLineText("Except the ones", "who are dead.", 1710, 4, 130);

    renderScrollingText(1850, 4, 220, "But there's no", "sense crying over", "every mistake.");

    renderScrollingText(2080, 4, 220, "You just keep on", "trying till you", "run out of cake.");
    renderBlankBorder(12, 3, 2275);
    renderCakeBorder(12, 3, 2280);

    renderScrollingText(2310, 4, 220, "And the Science", "gets done.", "And you make", "a neat gun.");

    renderTwoLineText("For the people who", "are still alive.", 2540, 5, -1);

    for (int i = 0; i < 10; i++) {
      clearLine(i, 2780 + 30*i);
      clearLineReverse(19 - i, 2780 + 30*i);
    }

    return ops;
  }














  public static final String SHADES = " .xX";
  
  public static Tile from1Bpp(int[] rows) {
    byte[] data = new byte[16];
    for (int i = 0; i < 8; i++)
      data[2*i] = data[2*i+1] = (byte) rows[i];
    return new Tile(data);
  }
  public static Tile fromShades(String... rows) {
    return fromShades(rows, 0);
  }
  public static Tile fromShades(String[] rows, int offset) {
    byte[] data = new byte[16];
    for (int i = 0; i < 8; i++) {
      byte valH = 0;
      byte valL = 0;
      for (int j = 0; j < 8; j++) {
        int shade = SHADES.indexOf(rows[i].charAt(offset + 7-j));
        valL |= (shade & 1) << j;
        valH |= ((shade >> 1) & 1) << j;
      }
      data[2*i] = valL;
      data[2*i+1] = valH;
    }
    return new Tile(data);
  }
  public static Tile[] fromShadesArray(String... rows) {
    int numRows = rows.length / 8;
    int len = rows[0].length() / 8;
    Tile[] result = new Tile[numRows * len];
    for (int row = 0; row < numRows; row++) {
      for (int i = 0; i < len; i++) {
        result[row * len + i] = fromShades(Arrays.copyOfRange(rows, row * 8, row * 8 + 8), i * 8);
      }
    }
    return result;
  }
  
  public static String[] toShades(Tile t) {
    String[] res = new String[8];
    for (int i = 0; i < 8; i++) {
      res[i] = "";
      for (int j = 0; j < 8; j++)
        res[i] += SHADES.charAt((((t.get(2*i) >> (7-j)) & 1) << 1) + ((t.get(2*i+1) >> (7-j)) & 1));
    }
    return res;
  }
  public static String[] toShadesArray(Tile[] ts) {
    String[] res = new String[] {"","","","","","","",""};
    for (Tile t : ts) {
      String[] tmp = toShades(t);
      for (int i = 0; i < 8; i++)
        res[i] += tmp[i];
    }
    return res;
  }
  public static void printShades(String[] rows) {
    for (int i = 0; i < rows.length; i++)
      System.out.println(rows[i]);
    System.out.println();
  }
  
  public static void main(String[] args) {
//    System.out.println(CHAR_TILES.length);
//    System.out.println(CHAR_CODES.length());
//    System.out.println(APERTURE_TILES.length);
//    printShades(toShadesArray(CHAR_TILES));
//    printShades(toShadesArray(APERTURE_TILES));
    printRawData();
  }

  private static final Palette CHAR_PALETTE = new Palette(new byte[] { (byte) 0xff, 0x7f, 0x37, 0x7e, (byte) 0xeb, 0x7e, 0x63, 0x0c });
  private static final Palette APERTURE_PALETTE = new Palette(new byte[] { (byte)0xff, 0x7f, (byte)0x94, 0x52, (byte)0x4a, 0x29, 0x0, 0x0 });
  
  private static final String CHAR_CODES = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.:0123456789";
  private static final String[] CHAR_SHADES = new String[] {
    "           X    XXXXX     XXXX  XXXXX   XXXXXXX XXXXXXX   XXXX  X     X  XXXXX   XXXXXX X    X  X       X     X X     X   XXX   XXXXXX    XXX   XXXXXX   XXXX   XXXXXXX X     X X     X X     X XX   XX X     X XXXXXXX          X                    X             XX           X                       X         X                                                                                                                       XX                                                                                                   ",
    "          X X   X    X   X    X X    X  X       X        X    X X     X    X        X   X   X   X       XX   XX XX    X  X   X  X     X  X   X  X     X X    X     X    X     X X     X X  X  X  X   X   X   X       X           X                    X            X  X          X         X        X    X         X                                                               X                                                       XX              XX     XXX      XX    XXXXX   XXXXXX    XXX  XXXXXX   XXXXX  XXXXXXX  XXXXX   XXXXX  ",
    "          X X   X    X  X       X     X X       X       X       X     X    X        X   X  X    X       X X X X X X   X X     X X     X X     X X     X X          X    X     X  X   X  X X X X   X X     X X       X     XXX    X        XXXX        X   XXXX     X      XXXXX  X                       X   XX    X    XXX XX    XXXX    XXXX   XXXXX    XXXXX  X  XXX   XXXX   XXXXX   X   X   X   X  X     X XX   X   X   X   XXXXXX     X              XX    X  XX    XXX   XX   XX     XX    XXXX  XX      XX      XX   XX XX   XX XX   XX ",
    "         X   X  XXXXXX  X       X     X XXXXXX  XXXXXX  X  XXXX XXXXXXX    X        X   X XX    X       X  X  X X  X  X X     X XXXXXX  X     X XXXXXX   XXXXX     X    X     X  X   X  X X X X    X       X       X         X   XXXXX   X    X   XXXXX  X    X  XXXXXX  X    X  XXXX      X        X    X XX      X    X  X  X   X   X  X    X  X    X  X    X  X X     X         X     X   X   X   X  X  X  X   X X    X   X       X     X                    XX   XX    XX       XXX   XXX    XX XX  XXXXXX  XXXXXX      XX   XXXXX  XX   XX ",
    "         XXXXX  X     X X       X     X X       X       X     X X     X    X    X   X   XX  X   X       X     X X   X X X     X X       X   X X X   X         X    X    X     X   X X   XX   XX   X X      X      X       XXXX   X    X  X       X    X  XXXXXX    X     X    X  X   X     X        X    XX        X    X  X  X   X   X  X    X  X    X  X    X  XX       XXXX     X     X   X   X   X  X  X  X    X     X   X     XX                           XX   XX    XX    XXXX        XX XX  XX       XX XX   XX    XX   XX   XX  XXXXXX ",
    "        X     X X     X  X    X X    X  X       X        X    X X     X    X    X   X   X    X  X       X     X X    XX  X   X  X        X   X  X    X  X     X    X     X    X   X X   XX   XX  X   X     X     X       X   X   X    X  X    X  X    X  X         X      XXXXX  X   X     X        X    X XX      X    X  X  X   X   X  X    X  XXXXX    XXXXX  X            X    X     X   X    X X   X X X X   X X     XXXX    X              XX        XX    XX  X     XX   XXX     XX   XX XXXXXXX XX   XX XX   XX   XX    XX   XX      XX ",
    "        X     X XXXXXX    XXXX  XXXXX   XXXXXXX X         XXXX  X     X  XXXXX   XXX    X     X XXXXXXX X     X X     X   XXX   X         XXX X X     X  XXXXX     X      XXXXX    X    X     X XX   XX    X    XXXXXXX   XXXXX  XXXXX    XXXX    XXXXX   XXXXX    X          X  X   X     X        X    X   XX    X    X  X  X   X   X   XXXX   X            X  X       XXXXX      XX    XXXX     X     X   X   X   XX      X   XXXXXX          XX        XX     XXX    XXXXXX XXXXXXX  XXXXX      XX   XXXXX   XXXXX    XX     XXXXX   XXXXX  ",
    "                                                                                                                                                                                                                                                                         XXXXX                    XX                                             X            X                                                          XXXX                                                                                                                   ",
  };
  private static final Tile[] CHAR_TILES = fromShadesArray(CHAR_SHADES);

  private static final String[] APERTURE_SHADES = new String[] {
      "                     xxxxxx..                   ",
      "                xXXX .XXXXXXXXXx                ",
      "              XXXXXXX .XXXXXXXXXXX.             ",
      "            XXXXXXXXXX .XXXXXXXXXXXX            ",
      "          .XXXXXXXXXXXX .XXXXXXXXXXXX           ",
      "         xXXXXXXXXXXXXXX .XXXXXXXXXXX x         ",
      "        XXXXXXXXXXXXXXXXX .XXXXXXXXXX XX        ",
      "       XXXXXXXXXXXXXXXXXXX .XXXXXXXXX XXX       ",
      "      XXXXXXXXXXXXXXXXXXXXX .XXXXXXXX XXXX      ",
      "     xXXXXXXXXXXXXXXXXXXXXXX .XXXXXXX XXXXX     ",
      "                              .XXXXXX XXXXXx    ",
      "    XXXXXXXXXXXXX              .XXXXX XXXXXX    ",
      "   XXXXXXXXXXXXX                .XXXX XXXXXXX   ",
      "  .XXXXXXXXXXXX                  xXXX XXXXXXX.  ",
      "  XXXXXXXXXXXX                    xXX XXXXXXXX  ",
      "  XXXXXXXXXXX                      xX XXXXXXXX  ",
      " xXXXXXXXXXX                        x XXXXXXXXx ",
      " XXXXXXXXXX                           XXXXXXXXX ",
      " XXXXXXXXX                            XXXXXXXXX ",
      " XXXXXXXX                             XXXXXXXXX ",
      ".XXXXXXX x.                           XXXXXXXX  ",
      "xXXXXXX xX                            XXXXXXX .x",
      "xXXXXX xXX                            XXXXXX .Xx",
      "xXXXX xXXX                            XXXXX .XXx",
      "xXXX xXXXX                            XXXX .XXXx",
      "xXX xXXXXX                            XXX .XXXXx",
      "xX xXXXXXX                            XX .XXXXXx",
      ". xXXXXXXX                            X .XXXXXXx",
      " xXXXXXXXX                             .XXXXXXX.",
      " XXXXXXXXX                            .XXXXXXXX ",
      " XXXXXXXXX                           .XXXXXXXXX ",
      " xXXXXXXXX x                        .XXXXXXXXXx ",
      "  XXXXXXXX XX                      .XXXXXXXXXX  ",
      "  XXXXXXXX XXX                    .XXXXXXXXXXX  ",
      "  .XXXXXXX XXXX                  .XXXXXXXXXXX.  ",
      "   XXXXXXX XXXXX                .XXXXXXXXXXXX   ",
      "    XXXXXX XXXXXX              xXXXXXXXXXXXX    ",
      "    xXXXXX XXXXXXX                              ",
      "     XXXXX XXXXXXXX xXXXXXXXXXXXXXXXXXXXXXX     ",
      "      XXXX XXXXXXXXX xXXXXXXXXXXXXXXXXXXXX      ",
      "       XXX XXXXXXXXXX xXXXXXXXXXXXXXXXXXX       ",
      "        XX XXXXXXXXXXX xXXXXXXXXXXXXXXXX        ",
      "         X XXXXXXXXXXXX xXXXXXXXXXXXXXX         ",
      "           XXXXXXXXXXXXX xXXXXXXXXXXXx          ",
      "            XXXXXXXXXXXXX xXXXXXXXXX            ",
      "             .XXXXXXXXXXXX xXXXXXX.             ",
      "                xXXXXXXXXXX xXXx                ",
      "                   .xxxxxxx.                    ",
  };
  private static final Tile[] APERTURE_TILES = fromShadesArray(APERTURE_SHADES);
  
  private static final String[] CAKE_SHADES = new String[] {
      "                  x                             ",
      "                 .X                             ",
      "                  xX                            ",
      "                   xx                           ",
      "                    Xx                          ",
      "                   xX.                          ",
      "                  xX                            ",
      "                 Xx                             ",
      "           xXXXx .                          .xxx",
      "          XXXXX.xXx                  ..xxXXXXXX.",
      "       X..XXXXXXXXXX            .xXXXXXXxxXXX.  ",
      "      Xx xXXXXXXXXXX.    .xXXXXXxx.    .XXx    x",
      "    .Xx  xXXXXXXXXXXx.XXXxx..        .XXx   .xXX",
      "    xX   xXXXXXXXXXXx .XX.         xXX.   .XXXXX",
      "   ..Xx  .XXXXXXXXXX.   XXX     .XXX.   xXXXXXXX",
      ".XXXx Xx  XXXXXXXXXX   .XX.   .XXx.   xXXXXXXXXX",
      "Xx     X . XXXXXXXX   .Xx   .XXx    XXXXXXXXXXXX",
      "XX       .X. ....    xX   xXX.   .XXXXXXXXXXXXx ",
      "XXX.      xX. .x.    . .xXX.   xXXXXXXXXXXXXx   ",
      "X.xXX.     xXXX.     .XXX.   xXXXXXXXXXXXX.     ",
      "X.  xXXXx.  x.     xXXx   .XXXXXXXXXXXXx      .X",
      "X.    .xXXXXXXXXXXXXx   .XXXXXXXXXXXXx      .XXX",
      "X.         ....xxx.   xXXXXXXXXXXXXx      .XXXXX",
      "X.                 .XXXXXXXXXXXXX.      xXXXXXXX",
      "X.                xXXXXXXXXXXXx.     .xXXXXXXXXX",
      "X.                XXXXXXXXXXx      .XXXXXXXXXXXX",
      "X.                XXXXXXXX.      xXXXXXXXXXXXX. ",
      "X.                XXXXXX.     .xXXXXXXXXXXXx.   ",
      "X.                XXXX.     .XXXXXXXXXXXXx.     ",
      "X.                Xx      xXXXXXXXXXXXXx      .X",
      "X.                      xXXXXXXXXXXXX.      xXXX",
      "X.                   .xXXXXXXXXXXXX.      xXXXXX",
      "X.                 .XXXXXXXXXXXXx      .XXXXXXXX",
      "X.                xXXXXXXXXXXXx      .XXXXXXXXXX",
      "X.                XXXXXXXXXXx      .XXXXXXXXXXXX",
      "X.                XXXXXXXX.      xXXXXXXXXXXXX. ",
      "X.                XXXXXx.     .xXXXXXXXXXXXx.   ",
      "X.                XXXx      .XXXXXXXXXXXXx      ",
      "X.                x.      xXXXXXXXXXXXXx        ",
      "X.                     .xXXXXXXXXXXXX.          ",
      "X.                   .XXXXXXXXXXXXX.            ",
      "X.                 xXXXXXXXXXXXXX.              ",
      "Xx                XXXXXXXXXXXXx.                ",
      ".Xx               XXXXXXXXXXx                   ",
      " xXXx             XXXXXXXX.                     ",
      "   xXXx.          XXXXXX.                       ",
      "     xxXXXxx......XXXx.                         ",
      "         .xxXXXXXXX.                            ",
  };
  private static final Tile[] CAKE_TILES = fromShadesArray(CAKE_SHADES);

  private static void printRawData() {
    for (int y = 0; y < 64; y++) {
      for (int x = 0; x < 64; x++)
        System.out.print(SHADES.charAt(3-rawData[64*y + x]));
      System.out.println();
    }
  }
  
  private static final String[] CAKE_BORDER_SHADES = new String[] {
      ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.",
      "XXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXX",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                        X                                   .X",
      "Xx                        Xx                                  .X",
      "Xx                         X.                                 .X",
      "Xx                         .X                                 .X",
      "Xx                          X.                                .X",
      "Xx                         X.                                 .X",
      "Xx                        X                                   .X",
      "Xx                  xXXx x                                    .X",
      "Xx                 XXXXX x.                      ..xXX        .X",
      "Xx                xXXXX XXXX               .xXXXXXXX.         .X",
      "Xx             .X XXXXxxXXXX.        ..xXXXXx..xXX.  ..       .X",
      "Xx            xX..XXXXXXXXXXX  .xXXXXXx.     xXX.  xXX.       .X",
      "Xx           .Xx .XXXXXXXXXXX XXx..        xXx.  xXXXX.       .X",
      "Xx           .Xx  XXXXXXXXXXx  XXx       xXx   xXXXXXX.       .X",
      "Xx            XX  XXXXXXXXXX   xXX    .XX.   xXXXXXXXX.       .X",
      "Xx       .XXX..X  .XXXXXXXX.   XX.  .XX.   xXXXXXXXXXX.       .X",
      "Xx       XX    x. x xXXXXx   .Xx  xXX.   xXXXXXXXXXXXX        .X",
      "Xx       XXx    . .X         x. xXx.   xXXXXXXXXXXXx.         .X",
      "Xx       X.XX      XX.xXx     XXx    XXXXXXXXXXXXX            .X",
      "Xx       X  .XX.    xXx.   .XXx   .xXXXXXXXXXXXx.    x.       .X",
      "Xx       X    .XXXXxx...xxXX.    XXXXXXXXXXXXx     XXX.       .X",
      "Xx       X        ...xxxx..   .xXXXXXXXXXXXx    .xXXXX.       .X",
      "Xx       X                   XXXXXXXXXXXXx     XXXXXXX.       .X",
      "Xx       X                .XXXXXXXXXXXXx    .XXXXXXXXX.       .X",
      "Xx       X                XXXXXXXXXXXx    .XXXXXXXXXXX.       .X",
      "Xx       X                XXXXXXXXX.    .XXXXXXXXXXXXx        .X",
      "Xx       X                XXXXXXXx    .XXXXXXXXXXXXx          .X",
      "Xx       X                XXXXX.    .XXXXXXXXXXXXx   ..       .X",
      "Xx       X                XXX.    .XXXXXXXXXXXX.   .XX.       .X",
      "Xx       X                X.    .XXXXXXXXXXXXx   .XXXX.       .X",
      "Xx       X                    xXXXXXXXXXXXX.   xXXXXXX.       .X",
      "Xx       X                  .XXXXXXXXXXXX.   .XXXXXXXX.       .X",
      "Xx       X                .XXXXXXXXXXXX.   xXXXXXXXXXX.       .X",
      "Xx       X                XXXXXXXXXXX.   .XXXXXXXXXXXX        .X",
      "Xx       X                XXXXXXXXX.   xXXXXXXXXXXXX.         .X",
      "Xx       X                XXXXXXX.   xXXXXXXXXXXXx.           .X",
      "Xx       X                XXXXX    xXXXXXXXXXXXX.             .X",
      "Xx       X                XXx.  .xXXXXXXXXXXXx.               .X",
      "Xx       X                x    xXXXXXXXXXXXX                  .X",
      "Xx       X                  .XXXXXXXXXXXXx                    .X",
      "Xx       X                 XXXXXXXXXXXXx                      .X",
      "Xx       x.               XXXXXXXXXXXx                        .X",
      "Xx       .X               XXXXXXXXXx                          .X",
      "Xx        xX              XXXXXXXx                            .X",
      "Xx         xX.            XXXXXx                              .X",
      "Xx           XXX.         XXXx                                .X",
      "Xx             .xXXXXXXXXXX.                                  .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "XX............................................................xX",
      ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.",
  };
  private static final Tile[] CAKE_BORDER_TILES = fromShadesArray(CAKE_BORDER_SHADES);
  
  private static final String[] LOGO_BORDER_SHADES = new String[] {
      ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.",
      "XXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXX",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                        .xXXXXXXXXx.                        .X",
      "Xx                     xXXXXXXXXXXXXXXXX                      .X",
      "Xx                     .XXXXXXXXXXXXXXXX  X.                  .X",
      "Xx                 xXX   XXXXXXXXXXXXXXX. XXX                 .X",
      "Xx               .XXXXX.  xXXXXXXXXXXXXXx XXXXx               .X",
      "Xx              xXXXXXXXx  .XXXXXXXXXXXXx XXXXXX              .X",
      "Xx             xXXXXXXXXXX.  xXXXXXXXXXXX xXXXXXX             .X",
      "Xx            XXXXXXXXXXXXX.  .XXXXXXXXXX .XXXXXXX            .X",
      "Xx           xXXXXXXXXXXXXXXx   XXXXXXXXX. XXXXXXXX           .X",
      "Xx          .XXXXXXXXXXXXXXXXX.  xXXXXXXX. XXXXXXXXX          .X",
      "Xx         .XXXXXXXXXXXXXXXXXXXx  .XXXXXXx XXXXXXXXXx         .X",
      "Xx         XXXXXXXXXXXXXXXXXXXXx.   xXXXXx xXXXXXXXXX         .X",
      "Xx        xXXXXXXXXXXXXXXXx.         .XXXX .XXXXXXXXXX        .X",
      "Xx        XXXXXXXXXXxx..               XXX .XXXXXXXXXX        .X",
      "Xx       .XXXXXXx.                      xX .XXXXXXXXX         .X",
      "Xx       Xxx.     .xxXX                  .  XXXXXXXX. ..      .X",
      "Xx           ..xXXXXXX                      XXXXXXXX  Xx      .X",
      "Xx      .xXXXXXXXXXXXx                      XXXXXXX  XXX      .X",
      "Xx      XXXXXXXXXXXXX                       xXXXXX. .XXX      .X",
      "Xx      XXXXXXXXXXXX.                       .XXXXx  XXXX.     .X",
      "Xx     .XXXXXXXXXXX.                         XXXX  XXXXXx     .X",
      "Xx     .XXXXXXXXXXx                          XXX  xXXXXXX     .X",
      "Xx     xXXXXXXXXXX                           XX. .XXXXXXX     .X",
      "Xx     xXXXXXXXXX.                           xX  XXXXXXXX     .X",
      "Xx     XXXXXXXXXX .                          x  xXXXXXXXX     .X",
      "Xx     XXXXXXXXX  X.                           .XXXXXXXXX     .X",
      "Xx     xXXXXXXX. XX.                           XXXXXXXXXX     .X",
      "Xx     xXXXXXXx xXXx                          XXXXXXXXXXX     .X",
      "Xx     .XXXXXX .XXXX                         xXXXXXXXXXXX     .X",
      "Xx      XXXXX. XXXXX                        .XXXXXXXXXXXx     .X",
      "Xx      XXXXx xXXXXX.                       XXXXXXXXXXXX.     .X",
      "Xx      XXXX .XXXXXX.                      xXXXXXXXXXXx.      .X",
      "Xx      xXX. XXXXXXX.                      XXXXXXXx.          .X",
      "Xx       Xx xXXXXXXXx                     XXXx.      .x.      .X",
      "Xx       x .XXXXXXXXX xx                 ..      .xXXXX       .X",
      "Xx         XXXXXXXXXX xXx                   .xXXXXXXXXx       .X",
      "Xx        xXXXXXXXXXX .XXX.            ..xXXXXXXXXXXXX        .X",
      "Xx        .XXXXXXXXXX. XXXXX       .xXXXXXXXXXXXXXXXXx        .X",
      "Xx         xXXXXXXXXXx XXXXXX.  xXXXXXXXXXXXXXXXXXXXX         .X",
      "Xx          XXXXXXXXXX XXXXXXXx  xXXXXXXXXXXXXXXXXXX.         .X",
      "Xx           XXXXXXXXX xXXXXXXXX. .XXXXXXXXXXXXXXXX.          .X",
      "Xx           .XXXXXXXX .XXXXXXXXX.  XXXXXXXXXXXXXXx           .X",
      "Xx            .XXXXXXX. XXXXXXXXXXX  .XXXXXXXXXXXx            .X",
      "Xx             .XXXXXX. XXXXXXXXXXXX. .XXXXXXXXXx             .X",
      "Xx               XXXXXx XXXXXXXXXXXXXx  xXXXXXX.              .X",
      "Xx                xXXXX XXXXXXXXXXXXXXX. .XXXx                .X",
      "Xx                  XXX xXXXXXXXXXXXXXXX.  X.                 .X",
      "Xx                   .x .XXXXXXXXXXXXXXXXx                    .X",
      "Xx                       XXXXXXXXXXXXXXx                      .X",
      "Xx                          ..xxxxx.                          .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "Xx                                                            .X",
      "XX............................................................xX",
      ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.",
  };
  private static final Tile[] LOGO_BORDER_TILES = fromShadesArray(LOGO_BORDER_SHADES);

  static byte rawData[] = {
    };

}

