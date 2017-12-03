package mrwint.gbtasgen.tools.playback.util.audio;

import static mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation.toJoypadNibble;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Fmv;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaySound;

public class AudioUtil {

  public static GbAudio rewriteTo4bitSimple(short[] samples) {
    int len = samples.length / 2;
    int[] outSamples = new int[len * 2];
    int[] soValues = new int[len];

    for (int pos = 0; pos < len; pos++) {
      int so = 7;
      for (int offset = 0; offset < 2; offset++) {
        int minError = Integer.MAX_VALUE;
        int minErrorLevel = 0;
        for (int level = 0; level < 16; level++) {
          int error = Math.abs(samples[2 * pos + offset] - getSample(level, so));
          if (error < minError) {
            minError = error;
            minErrorLevel = level;
          }
        }
        outSamples[2 * pos + offset] = minErrorLevel;
      }
      soValues[pos] = so;
    }

    return new GbAudio(outSamples, soValues);
  }

  public static GbAudio rewriteTo4bitFancy(short[] samples) {
    int len = samples.length / 2;
    int[] outSamples = new int[len * 2];
    int[] soValues = new int[len];

    for (int pos = 0; pos < len; pos++) {
      int minSoError = Integer.MAX_VALUE;
      int minSoErrorSo = 0;
      for (int so = 0; so < 8; so++) {
        int curSoError = 0;
        for (int offset = 0; offset < 2; offset++) {
          int minError = Integer.MAX_VALUE;
          for (int level = 0; level < 16; level++) {
            int error = Math.abs(samples[2 * pos + offset] - getSample(level, so));
            error *= error;
            minError = Math.min(minError, error);
          }
          curSoError += minError;
        }
        if (curSoError < minSoError) {
          minSoError = curSoError;
          minSoErrorSo = so;
        }
      }

      for (int offset = 0; offset < 2; offset++) {
        int minError = Integer.MAX_VALUE;
        int minErrorLevel = 0;
        for (int level = 0; level < 16; level++) {
          int error = Math.abs(samples[2 * pos + offset] - getSample(level, minSoErrorSo));
          if (error < minError) {
            minError = error;
            minErrorLevel = level;
          }
        }
        outSamples[2 * pos + offset] = minErrorLevel;
      }
      soValues[pos] = minSoErrorSo;
    }

    return new GbAudio(outSamples, soValues);
  }

  private static short[] resample(short[] samples, int fromFreq, int toFreq) {
    int len = (int) ((long)samples.length * toFreq / fromFreq);
    short[] ret = new short[len];

    for (int pos = 0; pos < len; pos++) {
      int fromPos = (int) (((long)pos * fromFreq) / toFreq);
      int offset = (int) (((long)pos * fromFreq) - ((long)fromPos * toFreq));
      if (offset == 0)
        ret[pos] = samples[fromPos];
      else
        ret[pos] = (short) (((long)samples[fromPos] * (toFreq - offset) + (long)samples[fromPos + 1] * offset) / toFreq);
    }

    return ret;
  }

  private static short getSample(int level, int so) {
    return (short) ((level * 2 - 15) * 64 * (so + 1));
  }

  public static int FREQ_SCALE = 2;
  
  public static short[] read16bitPcmMonoAudio(String file) throws IOException {
    return read16bitPcmMonoAudio(file, 1, 1);
  }
  public static short[] read16bitPcmMonoAudio(String file, int volumeMult, int volumeDiv) throws IOException {
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
    in.skip(24); // skip header
    int freq = in.read();
    freq += in.read() * 0x100;
    freq += in.read() * 0x10000;
    freq += in.read() * 0x1000000;
    in.skip(12); // skip header
    int length = in.read();
    length += in.read() * 0x100;
    length += in.read() * 0x10000;
    length += in.read() * 0x1000000;
    length /= 2; // in frames

    short[] samples = new short[length];

    for (int i = 0; i < length; i++) {
      samples[i] = (short) in.read();
      samples[i] += in.read() * 0x100;
      int sample = samples[i] * volumeMult / volumeDiv;
      if (sample > Short.MAX_VALUE)
        sample = Short.MAX_VALUE;
      if (sample < Short.MIN_VALUE)
        sample = Short.MIN_VALUE;
      samples[i] = (short) sample;
    }

    in.close();
    return resample(samples, freq * 57 * FREQ_SCALE, 1 << 21);
  }
  
  public static GbAudio toGbAudio(PlaySound playSound) throws IOException {
    ArrayList<Integer> inputs = new ArrayList<>(playSound.getInputMap().values());
    int cycles = (playSound.getInputMap().size() - 31) / 4;
    int[] samples = new int[cycles * 2];
    int[] soValues = new int[cycles];
    
    samples[0] = inputs.get(0) ^ 0xf;
    soValues[0] = toJoypadNibble(inputs.get(31));
    for (int i = 1; i < 31; i++) {
      samples[i] = toJoypadNibble(inputs.get(i));
      soValues[i/2] = toJoypadNibble(inputs.get(31 + (i/2)*4));
    }
    for (int i = 31; i < cycles * 2; i++) {
      samples[i] = toJoypadNibble(inputs.get(31 + ((i-31)/2) * 4 + 1 + (i-31)%2));
      soValues[i/2] = toJoypadNibble(inputs.get(31 + (i/2)*4));
    }
    
    return new GbAudio(samples, soValues);
  }
  public static GbAudio toGbAudio(Fmv fmv) throws IOException {
    ArrayList<Integer> inputs = new ArrayList<>(fmv.getInputMap().values());
    int cycles = fmv.numFrames * 4 * 154 - 10;
    int[] samples = new int[cycles * 2];
    int[] soValues = new int[cycles];
    samples[0] = inputs.get(getSampleInputIndex(0)) ^ 0xf;
    for (int i = 1; i < cycles * 2; i++) {
      samples[i] = toJoypadNibble(inputs.get(getSampleInputIndex(i)));
      soValues[i/2] = toJoypadNibble(inputs.get(getSoValueInputIndex(i)));
    }
    
    return new GbAudio(samples, soValues);
  }
  public static void write16bitPcmMonoAudio(String file, GbAudio gbAudio) throws IOException {
    short[] samples = new short[gbAudio.samples.length];
    for (int i = 0; i < gbAudio.samples.length; i++)
      samples[i] = getSample(gbAudio.samples[i], gbAudio.soValues[i/2]);

    write16bitPcmMonoAudio(file, samples, (1 << 21)/57/FREQ_SCALE);
  }

  public static void write16bitPcmMonoAudio(String file, PlaySound playSound) throws IOException {
    ArrayList<Integer> inputs = new ArrayList<>(playSound.getInputMap().values());
    int cycles = (playSound.getInputMap().size() - 31) / 4;
    short[] samples = new short[cycles * 2];
    samples[0] = getSample(inputs.get(0) ^ 0xf, toJoypadNibble(inputs.get(31)));
    for (int i = 1; i < 31; i++) {
      samples[i] = getSample(toJoypadNibble(inputs.get(i)), toJoypadNibble(inputs.get(31 + (i/2)*4)));
    }
    for (int i = 31; i < cycles * 2; i++) {
      samples[i] = getSample(toJoypadNibble(inputs.get(31 + ((i-31)/2) * 4 + 1 + (i-31)%2)), toJoypadNibble(inputs.get(31 + (i/2)*4)));
    }
    
    write16bitPcmMonoAudio(file, samples, (1 << 21)/57/FREQ_SCALE);
  }
  
  public static void write16bitPcmMonoAudio(String file, Fmv fmv) throws IOException {
    ArrayList<Integer> inputs = new ArrayList<>(fmv.getInputMap().values());
    int cycles = fmv.numFrames * 4 * 154 - 10;
    short[] samples = new short[cycles * 2];
    for (int i = 0; i < cycles * 2; i++)
      samples[i] = getSample(inputs.get(getSampleInputIndex(i)), inputs.get(getSoValueInputIndex(i)));

    write16bitPcmMonoAudio(file, samples, (1 << 21)/57/FREQ_SCALE);
  }
  private static int getSampleInputIndex(int sampleIndex) {
    if (sampleIndex < 31)
      return 16584 + sampleIndex;
    sampleIndex -= 31;
    return getSoValueInputIndex(sampleIndex) + 1 + (sampleIndex & 1);
  }
  private static int getSoValueInputIndex(int sampleIndex) {
    int soIndex = sampleIndex / 2;
    int inputIndex = 16615 + (soIndex / 154) * 3251;
    soIndex %= 154;
    if (soIndex < 124)
      return inputIndex + soIndex * 25;
    else if (soIndex < 143)
      return inputIndex + 3100 + (soIndex-124) * 3;
    else if (soIndex == 143)
      return inputIndex + 3157;
    else if (soIndex < 150)
      return inputIndex + 3161 + (soIndex-144) * 13;
    else
      return inputIndex + 3239 + (soIndex-150) * 3;      
  }

  public static void write16bitPcmMonoAudio(String file, short[] samples, int freq) throws IOException {
    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

    out.writeBytes("RIFF");
    out.writeInt(Integer.reverseBytes(samples.length * 2 + 36));
    out.writeBytes("WAVE");
    out.writeBytes("fmt ");
    out.writeInt(Integer.reverseBytes(16)); // fmt length
    out.writeShort(Short.reverseBytes((short) 1)); // PCM
    out.writeShort(Short.reverseBytes((short) 1)); // Mono
    out.writeInt(Integer.reverseBytes(freq)); // samples/s
    out.writeInt(Integer.reverseBytes(2*freq)); // bytes/s
    out.writeShort(Short.reverseBytes((short) 2)); // bytes/sample
    out.writeShort(Short.reverseBytes((short) 16)); // bits/sample
    out.writeBytes("data");
    out.writeInt(Integer.reverseBytes(samples.length * 2));
    for (short sample : samples)
      out.writeShort(Short.reverseBytes(sample));

    out.close();
  }
}
