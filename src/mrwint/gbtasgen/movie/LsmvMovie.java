package mrwint.gbtasgen.movie;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.state.SingleGbState;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.State.InputNode;

public class LsmvMovie {
  public static void exportMovie(GbMovie movie, String filename) {
    try {
      String path = "movies/" + filename + movie.rom.fileNameSuffix + ".lsmv";

      ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(path));

      writeZipEntry("gametype", "ggbc\n", zip);
      writeZipEntry("authors", "MrWint\n", zip);
      writeZipEntry("systemid", "lsnes-rr1\n", zip);
      writeZipEntry("controlsversion", "0\n", zip);
      writeZipEntry("coreversion", "libgambatte r537\n", zip);
      writeZipEntry("projectid", "2a443ca41fe4e6d8456c2e0e403adfccd2c5a1b1\n", zip);
      writeZipEntry("rerecords", "0\n", zip);
      writeZipEntry("rrdata", "", zip);


      StringWriter stringWriter = new StringWriter();
      PrintWriter out = new PrintWriter(stringWriter);
      writeInputs(movie, out);
      out.flush();
      writeZipEntry("input", stringWriter.toString(), zip);

      zip.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void writeZipEntry(String filename, String data, ZipOutputStream zip) throws IOException {
    writeZipEntry(filename, data.getBytes(StandardCharsets.UTF_8), zip);
  }
  
  private static void writeZipEntry(String filename, byte[] data, ZipOutputStream zip) throws IOException {
    ZipEntry entry = new ZipEntry(filename);
    long uncompressed = data.length;
    int method = entry.getMethod();
    CRC32 crc = new CRC32();
    Deflater def;
    byte[] drain;
    if(method != ZipEntry.STORED) {
      def   = new Deflater(Deflater.DEFAULT_COMPRESSION, true);
      drain = new byte[1024];
    }
    else {
      def   = null;
      drain = null;
    }
    crc.update(data);
    if(def != null) {
      def.setInput(data);
      while(!def.needsInput()) def.deflate(drain, 0, drain.length);
    }

    entry.setSize(uncompressed);
    if(def != null) {
      def.finish();
      while(!def.finished()) def.deflate(drain, 0, drain.length);
      entry.setCompressedSize(def.getBytesWritten());
    }
    entry.setCrc(crc.getValue());
    
    zip.putNextEntry(entry);
    zip.write(data);
    zip.closeEntry();
  }

  private static void writeInputs(GbMovie movie, PrintWriter out) {
    for(int input : movie.inputs)
      writeInput(out, input);
  }

  private static void writeInput(PrintWriter out, int input) {
    out.print((input & Move.FRAME_START) != 0 ? "F" : ".");
    out.print((input & Move.RESET) != 0 ? "R" : ".");
    out.print("|");
    out.print((input & Move.A) != 0 ? "A" : ".");
    out.print((input & Move.B) != 0 ? "B" : ".");
    out.print((input & Move.SELECT) != 0 ? "s" : ".");
    out.print((input & Move.START) != 0 ? "S" : ".");
    out.print((input & Move.RIGHT) != 0 ? "r" : ".");
    out.print((input & Move.LEFT) != 0 ? "l" : ".");
    out.print((input & Move.UP) != 0 ? "u" : ".");
    out.println((input & Move.DOWN) != 0 ? "d" : ".");
  }
}
