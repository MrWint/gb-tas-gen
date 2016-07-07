package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation;

public class PlaybackWriter {
  
  private static final int FRAME_CYCLES = 70224*2;
  
  private ArrayList<PlaybackOperation> playback;
  private int cycleOffset;

  public PlaybackWriter(ArrayList<PlaybackOperation> playback, int cycleOffset) {
    this.playback = playback;
    this.cycleOffset = cycleOffset;
  }

  public void write(String filename) throws IOException {
    ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(filename));

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
    writeInputs(out);
    out.flush();
    writeZipEntry("input", stringWriter.toString(), zip);
    
    zip.close();
  }
  
  private void writeZipEntry(String filename, String data, ZipOutputStream zip) throws IOException {
    writeZipEntry(filename, data.getBytes(StandardCharsets.UTF_8), zip);
  }
  
  private void writeZipEntry(String filename, byte[] data, ZipOutputStream zip) throws IOException {
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
  
  private int countInputs() {
    int numInputs = 0;
    for (PlaybackOperation op : playback)
      numInputs += op.getInputMap().size();
    return numInputs;
  }
  
  private void writeInputs(PrintWriter out) {
    int curCycle = cycleOffset;
    for (PlaybackOperation op : playback) {
      for (Entry<Integer, Integer> input : op.getInputMap().entrySet()) {
        boolean frameBoundary = curCycle + input.getKey() >= FRAME_CYCLES;
        if (frameBoundary) {
          curCycle -= FRAME_CYCLES;
          while (curCycle + input.getKey() >= FRAME_CYCLES) {
            writeInput(out, 0, true);
            curCycle -= FRAME_CYCLES;
          }
        }
        writeInput(out, input.getValue(), frameBoundary);
      }
      curCycle += op.getCycleCount();
    }
  }

  private void writeInput(PrintWriter out, int input, boolean frameBoundary) {
    out.print(frameBoundary ? "F" : ".");
    out.print(".|");
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
