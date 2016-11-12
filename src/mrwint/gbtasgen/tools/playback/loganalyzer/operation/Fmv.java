package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.AlwaysAccessible;
import mrwint.gbtasgen.tools.playback.util.audio.GbAudio;
import mrwint.gbtasgen.tools.playback.util.video.GbVideo;

public class Fmv implements PlaybackOperation {
  
  private TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  
  public int numFrames;
  
  public Fmv(GbVideo video, GbAudio audio) {
    numFrames = video.framePaletteMap.length;
    
    // Initialization: Common tiles, 3840i (3840i)
    for (int c = 0; c < 120; c++) {
      int tx = (c % 20) * 8;
      int ty = (c / 20 + (c >= 40 ? 12 : 0)) * 8;
      for (int b = 0; b < 0x10; b++) {
        int value = 0;
        for (int i = 0; i < 8; i++)
          value |= ((video.framePixels[0][ty + b / 2][tx + i] >> (b & 1)) & 1) << (7-i);
        inputMap.put(29704 + 40 + c * 2324 + b * 144, toJoypadInput1(value));
        inputMap.put(29704 + 56 + c * 2324 + b * 144, toJoypadInput2(value));
      }
    }

    // Initialization: First frame tiles, 7680i (11520i)
    for (int c = 0; c < 240; c++) {
      int tx = (c % 20) * 8;
      int ty = (c / 20 + 2) * 8;
      for (int b = 0; b < 0x10; b++) {
        int value = 0;
        for (int i = 0; i < 8; i++)
          value |= ((video.framePixels[0][ty + b / 2][tx + i] >> (b & 1)) & 1) << (7-i);
        inputMap.put(308612 + 28 + c * 916 + b * 56, toJoypadInput1(value));
        inputMap.put(308612 + 44 + c * 916 + b * 56, toJoypadInput2(value));
      }
    }

    // Initialization: Tile attributes, 360i (11880i)
    for (int c = 0; c < 18; c++) {
      for (int b = 0; b < 20; b++) {
        int value = video.framePaletteMap[0][c][b] + (c >= 2 && c < 14 ? 0 : 8);
        inputMap.put(528468 + 48 + c * 1888 + b * 92, value ^ 0xf);
      }
    }

    // Initialization: BG palettes, 4704i (16584i)
    for (int c = 0; c < 0x93; c++) {
      for (int b = 0; b < 0x10; b++) {
        int value = (int) ((video.framePalettes[0][c*2 + b/8] >> ((b & 7) << 3)) & 0xff);
        inputMap.put(562488 + 48 + c * 1684 + b * 104, toJoypadInput1(value));
        inputMap.put(562488 + 64 + c * 1684 + b * 104, toJoypadInput2(value));
      }
    }
    
    // Initialization: Sound RAM, 31i (16615i)
    inputMap.put(811528 + 8, getSampleOrPadding(audio, 0) ^ 0xf);
    for (int i = 0; i < 15; i++) {
      inputMap.put(811528 + 36 + 52*i, toJoypadInput2(getSampleOrPadding(audio, 2*i + 1)));
      inputMap.put(811528 + 52 + 52*i, toJoypadInput2(getSampleOrPadding(audio, 2*i + 2)));
    }

    // Main loop
    for (int frame = 0; frame < 4*numFrames; frame++) { // 3251i
      for (int line = 0; line < 143; line++) { // 3157i
        // Update sound, 3i
        inputMap.put(812896 + 24 + frame * 140448 + line * 912, toJoypadInput2(getSoValueOrPadding(audio, frame * 154 + line)));
        inputMap.put(812896 + 60 + frame * 140448 + line * 912, toJoypadInput2(getSampleOrPadding(audio, 2*(frame * 154 + line) + 31)));
        inputMap.put(812896 + 76 + frame * 140448 + line * 912, toJoypadInput2(getSampleOrPadding(audio, 2*(frame * 154 + line) + 32)));
        
        if (line < 124) { // 22i
          // Write tile data
          int tileNum = (frame & 3) * 62 + line / 2;
          int tx = (tileNum % 20) * 8;
          int ty = (tileNum / 20) * 8 + (line & 1) * 4;
          for (int b = 0; b < 8; b++) {
            int value = 0;
            if (tileNum < 240 && frame / 4 + 1 < numFrames)
              for (int i = 0; i < 8; i++)
                value |= ((video.framePixels[frame / 4 + 1][ty + b / 2][tx + i] >> (b & 1)) & 1) << (7-i);
            inputMap.put(812896 + 376 + frame * 140448 + line * 912 + b * 36, toJoypadInput1(value));
            inputMap.put(812896 + 392 + frame * 140448 + line * 912 + b * 36, toJoypadInput2(value));
          }
          
          // Write new palette data
          for (int i = 0; i < 3; i++) {
            int paletteByteNum = (frame & 3) * 124 * 3 + line * 3 + i;
            int value = 0;
            if (frame / 4 + 1 < numFrames)
              value = (int) ((video.framePalettes[frame / 4 + 1][paletteByteNum/8] >> ((paletteByteNum & 7) << 3)) & 0xff);

            inputMap.put(812896 + 716 + frame * 140448 + line * 912 + i * 40, toJoypadInput1(value));
            inputMap.put(812896 + 732 + frame * 140448 + line * 912 + i * 40, toJoypadInput2(value));
          }
        }
      }

      // VBlank, 4i/94i
      {
        // Update sound, 3i
        inputMap.put(812896 + 143 * 912 + 24 + frame * 140448, toJoypadInput2(getSoValueOrPadding(audio, frame * 154 + 143)));
        inputMap.put(812896 + 143 * 912 + 60 + frame * 140448, toJoypadInput2(getSampleOrPadding(audio, 2*(frame * 154 + 143) + 31)));
        inputMap.put(812896 + 143 * 912 + 76 + frame * 140448, toJoypadInput2(getSampleOrPadding(audio, 2*(frame * 154 + 143) + 32)));

        if (frame + 1 >= 4 * numFrames) { // 1i/91i
          // Exit now, 1i
          inputMap.put(812896 + 143 * 912 + 476 + frame * 140448, 0);
        } else {
          // Don't exit, 1i
          inputMap.put(812896 + 143 * 912 + 476 + frame * 140448, 1);
          
          for (int c = 0; c < 6; c++) { // 78i
            // Update sound
            inputMap.put(812896 + 143 * 912 + 936 + frame * 140448 + c * 912, toJoypadInput2(getSoValueOrPadding(audio, frame * 154 + 144 + c)));
            inputMap.put(812896 + 143 * 912 + 972 + frame * 140448 + c * 912, toJoypadInput2(getSampleOrPadding(audio, 2*(frame * 154 + 144 + c) + 31)));
            inputMap.put(812896 + 143 * 912 + 988 + frame * 140448 + c * 912, toJoypadInput2(getSampleOrPadding(audio, 2*(frame * 154 + 144 + c) + 32)));
            
            // Update attribute map
            for (int b = 0; b < 10; b++) {
              int value = 0;
              if (frame / 4 + 1 < numFrames)
                value = video.framePaletteMap[frame / 4 + 1][(frame&3) * 3 + c/2][b + (c%2) * 10] + 8 - ((frame & 4) << 1);
              inputMap.put(812896 + 143 * 912 + 1024 + frame * 140448 + c * 912 + b * 44, value ^ 0xf);
            }
          }

          for (int c = 0; c < 4; c++) { // 12i
            // Update sound
            inputMap.put(812896 + 143 * 912 + 6408 + frame * 140448 + c * 912, toJoypadInput2(getSoValueOrPadding(audio, frame * 154 + 150 + c)));
            inputMap.put(812896 + 143 * 912 + 6444 + frame * 140448 + c * 912, toJoypadInput2(getSampleOrPadding(audio, 2*(frame * 154 + 150 + c) + 31)));
            inputMap.put(812896 + 143 * 912 + 6460 + frame * 140448 + c * 912, toJoypadInput2(getSampleOrPadding(audio, 2*(frame * 154 + 150 + c) + 32)));
          }
        }
      }
    }
    System.out.println("size: " + inputMap.size() + " predict " + (16615 + numFrames * 4 * 3251 - 90));
  }
  private int getSampleOrPadding(GbAudio audio, int index) {
    return index < audio.samples.length ? audio.samples[index] : 0;
  }
  private int getSoValueOrPadding(GbAudio audio, int index) {
    return index < audio.soValues.length ? audio.soValues[index] : 0;
  }

  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }

  @Override
  public int getCycleCount() {
    return 812896 + (4*numFrames - 1) * 140448 + 912*143 + 508;
  }

  @Override
  public int getJumpAddress() {
    return PlaybackAddresses.FMV;
  }

  @Override
  public int getStartOutputCycle() {
    return -1;
  }

  @Override
  public int getEndOutputCycle() {
    return -1;
  }

  @Override
  public Accessibility getAccessibility() {
    return new AlwaysAccessible();
  }

}
