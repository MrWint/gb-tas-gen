package mrwint.gbtasgen.tools.playback.util.video;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class SimpleAvi {
  public int dwMicroSecPerFrame;
  public int dwScale;
  public int dwRate;

  public int totalFrames;
  public int width;
  public int height;

  public long[][][] frames;

  public static SimpleAvi fromFile(String file) throws IOException {
    DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));

    SimpleAvi avi = new SimpleAvi();

    assertEqual(readFourCC(in), "RIFF");
    readInt(in); // riffSize
    assertEqual(readFourCC(in), "AVI ");

    int headerListSize = findList(in, "hdrl");
    avi.parseHeaderList(in, headerListSize);

    int moviListSize = findList(in, "movi");
    avi.parseMoviList(in, moviListSize);

    in.close();
    return avi;
  }
  public void writeTo(String file) throws IOException {
    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

    out.writeBytes("RIFF");
    out.writeInt(Integer.reverseBytes(0xcc + 0xc + totalFrames * (width * height * 3 + 8) + 8 + totalFrames * 0x10));
    out.writeBytes("AVI ");
    writeHeaderList(out);
    writeMoviList(out);
    writeIndex(out);

    out.close();

  }
  public void parseMoviList(DataInputStream in, int moviListSize) throws IOException {
    int frameSize = width * height * 3;
    frames = new long[totalFrames][height][width];
    for (int i = 0; i < totalFrames; i++) {
      assertEqual(readFourCC(in), "00db");
      assertEqual(readInt(in), frameSize);
      for (int y = height - 1; y >= 0; y--) {
        for (int x = 0; x < width; x++) {
          frames[i][y][x] = parseColor(in);
        }
      }
    }
  }
  public long parseColor(DataInputStream in) throws IOException {
    long b = (in.readByte() & 0xff) << 8;
    long g = (in.readByte() & 0xff) << 8;
    long r = (in.readByte() & 0xff) << 8;
    return (r << 32) + (g << 16) + b;
  }
  public void writeMoviList(DataOutputStream out) throws IOException {
    out.writeBytes("LIST");
    out.writeInt(Integer.reverseBytes(4 + totalFrames * (width * height * 3 + 8)));
    out.writeBytes("movi");
    for (int i = 0; i < totalFrames; i++) {
      out.writeBytes("00db");
      out.writeInt(Integer.reverseBytes(width * height * 3));
      for (int y = height - 1; y >= 0; y--) {
        for (int x = 0; x < width; x++) {
          writeColor(out, frames[i][y][x]);
        }
      }
    }
  }
  public void writeColor(DataOutputStream out, long color) throws IOException {
    out.writeByte((int) ((color >> 8) & 0xff));
    out.writeByte((int) ((color >> 24) & 0xff));
    out.writeByte((int) ((color >> 40) & 0xff));
  }

  public void parseHeaderList(DataInputStream in, int headerListSize) throws IOException {
    headerListSize -= 4; // "hdrl"
    parseMainHeader(in);
    headerListSize -= 0x40; // main header done

    assertEqual(readFourCC(in), "LIST");
    int streamListSize = readInt(in);
    headerListSize -= streamListSize;
    assertEqual(readFourCC(in), "strl");
    streamListSize -= 4; // "strl"
    parseStreamHeader(in);
    streamListSize -= 0x40; // stream header done
    parseStreamFormat(in);
    streamListSize -= 0x30; // format header done

    // Skip over rest of header
    if (streamListSize > 0)
      in.skip(streamListSize);
    if (headerListSize > 0)
      in.skip(headerListSize);
  }
  public void writeHeaderList(DataOutputStream out) throws IOException {
    out.writeBytes("LIST");
    out.writeInt(Integer.reverseBytes(0xc0)); // length
    out.writeBytes("hdrl");
    writeMainHeader(out);

    out.writeBytes("LIST");
    out.writeInt(Integer.reverseBytes(0x74)); // length
    out.writeBytes("strl");
    writeStreamHeader(out);
    writeStreamFormat(out);
  }

  public void parseMainHeader(DataInputStream in) throws IOException {
    assertEqual(readFourCC(in), "avih");
    assertEqual(readInt(in), 0x38); // header size
    this.dwMicroSecPerFrame = readInt(in);
    readInt(in); // dwMaxBytesPerSec
    readInt(in); // dwPaddingGranularity
    readInt(in); // dwFlags
    this.totalFrames = readInt(in);
    readInt(in); // dwInitialFrames
    assertEqual(readInt(in), 1); // dwStreams
    readInt(in); // dwSuggestedBufferSize
    this.width = readInt(in);
    this.height = readInt(in);
    in.skip(16); // reserved
  }
  public void writeMainHeader(DataOutputStream out) throws IOException {
    out.writeBytes("avih");
    out.writeInt(Integer.reverseBytes(0x38)); // length
    out.writeInt(Integer.reverseBytes(dwMicroSecPerFrame));
    out.writeInt(Integer.reverseBytes(0)); // dwMaxBytesPerSec
    out.writeInt(Integer.reverseBytes(0)); // dwPaddingGranularity
    out.writeInt(Integer.reverseBytes(0x10)); // dwFlags: hasIndex
    out.writeInt(Integer.reverseBytes(totalFrames));
    out.writeInt(Integer.reverseBytes(0)); // dwInitialFrames
    out.writeInt(Integer.reverseBytes(1)); // dwStreams
    out.writeInt(Integer.reverseBytes(0)); // dwSuggestedBufferSize
    out.writeInt(Integer.reverseBytes(width));
    out.writeInt(Integer.reverseBytes(height));
    out.writeInt(Integer.reverseBytes(0)); // reserved
    out.writeInt(Integer.reverseBytes(0)); // reserved
    out.writeInt(Integer.reverseBytes(0)); // reserved
    out.writeInt(Integer.reverseBytes(0)); // reserved
  }

  public void parseStreamHeader(DataInputStream in) throws IOException {
    assertEqual(readFourCC(in), "strh");
    assertEqual(readInt(in), 0x38); // header size
    assertEqual(readFourCC(in), "vids"); // video stream
    assertEqual(readFourCC(in), "DIB "); // uncompressed
    readInt(in); // dwFlags
    readInt(in); // wPriority and wLanguage
    readInt(in); // dwInitialFrames
    this.dwScale = readInt(in);
    this.dwRate = readInt(in);
    readInt(in); // dwStart
    assertEqual(readInt(in), this.totalFrames); // dwLength
    readInt(in); // dwSuggestedBufferSize
    readInt(in); // dwQuality
    readInt(in); // dwSampleSize
    assertEqual(Integer.valueOf(readShort(in)), 0); // left
    assertEqual(Integer.valueOf(readShort(in)), 0); // top
    assertEqual(Integer.valueOf(readShort(in)), width); // right
    assertEqual(Integer.valueOf(readShort(in)), height); // bottom
  }
  public void writeStreamHeader(DataOutputStream out) throws IOException {
    out.writeBytes("strh");
    out.writeInt(Integer.reverseBytes(0x38)); // length
    out.writeBytes("vids");
    out.writeBytes("DIB ");
    out.writeInt(Integer.reverseBytes(0)); // dwFlags
    out.writeInt(Integer.reverseBytes(0)); // wPriority and wLanguage
    out.writeInt(Integer.reverseBytes(0)); // dwInitialFrames
    out.writeInt(Integer.reverseBytes(dwScale));
    out.writeInt(Integer.reverseBytes(dwRate));
    out.writeInt(Integer.reverseBytes(0)); // dwStart
    out.writeInt(Integer.reverseBytes(totalFrames));
    out.writeInt(Integer.reverseBytes(width * height * 3)); // dwSuggestedBufferSize
    out.writeInt(Integer.reverseBytes(-1)); // dwQuality
    out.writeInt(Integer.reverseBytes(0)); // dwSampleSize
    out.writeShort(Short.reverseBytes((short) 0)); // left
    out.writeShort(Short.reverseBytes((short) 0)); // top
    out.writeShort(Short.reverseBytes((short) width)); // right
    out.writeShort(Short.reverseBytes((short) height)); // bottom
  }

  public void parseStreamFormat(DataInputStream in) throws IOException {
    assertEqual(readFourCC(in), "strf");
    assertEqual(readInt(in), 0x28); // format size
    assertEqual(readInt(in), 0x28); // biSize
    assertEqual(readInt(in), width);
    assertEqual(readInt(in), height);
    assertEqual(Integer.valueOf(readShort(in)), 1); // planes
    assertEqual(Integer.valueOf(readShort(in)), 24); // bitCount
    assertEqual(readInt(in), 0); // compression
    assertEqual(readInt(in), width * height * 3); // biSizeImage
    readInt(in); // biXPelsPerMeter
    readInt(in); // biYPelsPerMeter
    readInt(in); // biClrUsed
    readInt(in); // biClrImportant
  }
  public void writeStreamFormat(DataOutputStream out) throws IOException {
    out.writeBytes("strf");
    out.writeInt(Integer.reverseBytes(0x28)); // length
    out.writeInt(Integer.reverseBytes(0x28)); // length
    out.writeInt(Integer.reverseBytes(width));
    out.writeInt(Integer.reverseBytes(height));
    out.writeShort(Short.reverseBytes((short) 1)); // planes
    out.writeShort(Short.reverseBytes((short) 24)); // bitCount
    out.writeInt(Integer.reverseBytes(0)); // compression
    out.writeInt(Integer.reverseBytes(width * height * 3)); // biSizeImage
    out.writeInt(Integer.reverseBytes(0)); // biXPelsPerMeter
    out.writeInt(Integer.reverseBytes(0)); // biYPelsPerMeter
    out.writeInt(Integer.reverseBytes(0)); // biClrUsed
    out.writeInt(Integer.reverseBytes(0)); // biClrImportant
  }

  public void writeIndex(DataOutputStream out) throws IOException {
    out.writeBytes("idx1");
    out.writeInt(Integer.reverseBytes(totalFrames * 0x10)); // length
    for (int i = 0; i < totalFrames; i++) {
      out.writeBytes("00db");
      out.writeInt(Integer.reverseBytes(0x10)); // flags: isKeyFrame
      out.writeInt(Integer.reverseBytes(4 + i * (width * height * 3 + 8))); // offset
      out.writeInt(Integer.reverseBytes(width * height * 3)); // size
    }
  }


  static int readInt(DataInputStream in) throws IOException {
    int ret = (in.readByte() & 0xff);
    ret += (in.readByte() & 0xff) * 0x100;
    ret += (in.readByte() & 0xff) * 0x10000;
    ret += (in.readByte() & 0xff) * 0x1000000;
    return ret;
  }
  static short readShort(DataInputStream in) throws IOException {
    int ret = (in.readByte() & 0xff);
    ret += (in.readByte() & 0xff) * 0x100;
    return (short)ret;
  }
  static String readFourCC(DataInputStream in) throws IOException {
    String ret = "";
    ret += (char)in.readByte();
    ret += (char)in.readByte();
    ret += (char)in.readByte();
    ret += (char)in.readByte();
    return ret;
  }
  static void assertEqual(Object a, Object b) {
    if (!Objects.equals(a, b))
      throw new IllegalArgumentException(a + " is not equal to " +b);
  }

  static int findList(DataInputStream in, String name) throws IOException {
    String fourCC;
    int size;
    // Find header list
    while (true) {
      fourCC = readFourCC(in);
      size = readInt(in);
      if (!fourCC.equals("LIST")) {
        in.skip(size);
        continue;
      }
      fourCC = readFourCC(in);
      if (!fourCC.equals(name)) {
        in.skip(size - 4);
        continue;
      }
      return size;
    }
  }
}