package mrwint.gbtasgen.tools.deasm.specialCallHandler;

import static mrwint.gbtasgen.tools.deasm.CPUState.A;
import static mrwint.gbtasgen.tools.deasm.CPUState.H;
import static mrwint.gbtasgen.tools.deasm.CPUState.L;
import mrwint.gbtasgen.tools.deasm.CPUState;
import mrwint.gbtasgen.tools.deasm.OpCode;
import mrwint.gbtasgen.tools.deasm.ROM;
import mrwint.gbtasgen.util.Util;

public class SML2_10SpecialCallHandler extends SpecialCallHandler {

  @Override
  public boolean vetoContinueAfterCall(int currentAddress, int jumpAddress) {
    if(jumpAddress == 0x28) // messes with stack, reads return address as jumptable
      return true;
    return false;
  }

  @Override
  public void handleDFSInit() {
    // rst $28
    dfs.addJumpTable(0x2b0, 36);
    dfs.addJumpTable(0x92e8, 29);
    dfs.addJumpTable(0x934e, 3);
    dfs.addJumpTable(0x93f0, 7);
    dfs.addJumpTable(0x966a, 3);
    dfs.addJumpTable(0x96be, 3);
    dfs.addJumpTable(0x9754, 7);
    dfs.addJumpTable(0x9864, 4);
    dfs.addJumpTable(0x990a, 4);
    dfs.addJumpTable(0x994c, 4);
    dfs.addJumpTable(0x9ca2, 5);
    dfs.addJumpTable(0x9d79, 5);
    dfs.addJumpTable(0x9e5d, 5);
    dfs.addJumpTable(0x9f38, 7);
    dfs.addJumpTable(0xa05e, 3);
    dfs.addJumpTable(0xa105, 9);
    dfs.addJumpTable(0xa297, 2);
    dfs.addJumpTable(0xa2e5, 6);
    dfs.addJumpTable(0xa3c1, 31);
    dfs.addJumpTable(0xa90e, 9);
    dfs.addJumpTable(0xaaa8, 4);
    dfs.addJumpTable(0xab33, 2);
    dfs.addJumpTable(0xab71, 2);
    dfs.addJumpTable(0xac04, 11);
    dfs.addJumpTable(0xadfd, 13);
    dfs.addJumpTable(0xafe6, 3);
    dfs.addJumpTable(0xb0c5, 5);
    dfs.addJumpTable(0x30132, 7);
    dfs.addJumpTable(0x3046f, 4);
    dfs.addJumpTable(0x30697, 4);
    dfs.addJumpTable(0x58040, 96);
    dfs.addJumpTable(0x58112, 4);
    dfs.addJumpTable(0x5821e, 4);
    dfs.addJumpTable(0x582f7, 2);
    dfs.addJumpTable(0x583b4, 4);
    dfs.addJumpTable(0x58461, 1);
    dfs.addJumpTable(0x5855e, 4);
    dfs.addJumpTable(0x58646, 4);
    dfs.addJumpTable(0x58807, 4);
    dfs.addJumpTable(0x5885d, 4);
    dfs.addJumpTable(0x589be, 6);
    dfs.addJumpTable(0x58a7a, 4);
    dfs.addJumpTable(0x58b54, 2);
    dfs.addJumpTable(0x58bde, 4);
    dfs.addJumpTable(0x58c7d, 4);
    dfs.addJumpTable(0x58cfd, 2);
    dfs.addJumpTable(0x58d4e, 4);
    dfs.addJumpTable(0x58de1, 2);
    dfs.addJumpTable(0x58e32, 3);
    dfs.addJumpTable(0x58e9b, 6);
    dfs.addJumpTable(0x58f5f, 5);
    dfs.addJumpTable(0x5907a, 4);
    dfs.addJumpTable(0x5918b, 2);
    dfs.addJumpTable(0x591ce, 2);
    dfs.addJumpTable(0x591f1, 1);
    dfs.addJumpTable(0x59216, 4);
    dfs.addJumpTable(0x5928c, 6);
    dfs.addJumpTable(0x59354, 4);
    dfs.addJumpTable(0x5938e, 3);
    dfs.addJumpTable(0x593f4, 2);
    dfs.addJumpTable(0x594a0, 2);
    dfs.addJumpTable(0x5950e, 2);
    dfs.addJumpTable(0x595d3, 2);
    dfs.addJumpTable(0x5961d, 2);
    dfs.addJumpTable(0x5968a, 2);
    dfs.addJumpTable(0x596e8, 3);
    dfs.addJumpTable(0x597de, 8);
    dfs.addJumpTable(0x599b6, 7);
    dfs.addJumpTable(0x59ac6, 2);
    dfs.addJumpTable(0x59b31, 2);
    dfs.addJumpTable(0x59bc1, 2);
    dfs.addJumpTable(0x59bcb, 3);
    dfs.addJumpTable(0x59be4, 2);
    dfs.addJumpTable(0x59c07, 112);
    dfs.addJumpTable(0x59d01, 2);
    dfs.addJumpTable(0x59d72, 4);
    dfs.addJumpTable(0x59e28, 2);
    dfs.addJumpTable(0x59e72, 4);
    dfs.addJumpTable(0x59f20, 2);
    dfs.addJumpTable(0x59f7f, 2);
    dfs.addJumpTable(0x59ff2, 3);
    dfs.addJumpTable(0x5a051, 2);
    dfs.addJumpTable(0x5a0cd, 4);
    dfs.addJumpTable(0x5a140, 4);
    dfs.addJumpTable(0x5a18f, 4);
    dfs.addJumpTable(0x5a206, 3);
    dfs.addJumpTable(0x5a248, 1);
    dfs.addJumpTable(0x5a275, 3);
    dfs.addJumpTable(0x5a2ed, 5);
    dfs.addJumpTable(0x5a32e, 2);
    dfs.addJumpTable(0x5a574, 2);
    dfs.addJumpTable(0x5a5e1, 1);
    dfs.addJumpTable(0x5a607, 4);
    dfs.addJumpTable(0x5a67b, 4);
    dfs.addJumpTable(0x5a693, 4);
    dfs.addJumpTable(0x5a6fb, 2);
    dfs.addJumpTable(0x5a74d, 2);
    dfs.addJumpTable(0x5a78b, 4);
    dfs.addJumpTable(0x5a836, 4);
    dfs.addJumpTable(0x5a873, 4);
    dfs.addJumpTable(0x5a915, 4);
    dfs.addJumpTable(0x5a977, 1);
    dfs.addJumpTable(0x5a9b2, 2);
    dfs.addJumpTable(0x5aa16, 5);
    dfs.addJumpTable(0x5aa89, 3);
    dfs.addJumpTable(0x5aabc, 5);
    dfs.addJumpTable(0x5ab4e, 5);
    dfs.addJumpTable(0x5ab7f, 5);
    dfs.addJumpTable(0x5ac04, 2);
    dfs.addJumpTable(0x5ac3f, 4);
    dfs.addJumpTable(0x5ac80, 4);
    dfs.addJumpTable(0x5ace7, 3);
    dfs.addJumpTable(0x5ad30, 3);
    dfs.addJumpTable(0x5ad50, 5);
    dfs.addJumpTable(0x5ade3, 5);
    dfs.addJumpTable(0x5aefa, 5);
    dfs.addJumpTable(0x5afda, 5);
    dfs.addJumpTable(0x5b070, 4);
    dfs.addJumpTable(0x5b0db, 5);
    dfs.addJumpTable(0x5b17d, 3);
    dfs.addJumpTable(0x5b1cb, 4);
    dfs.addJumpTable(0x5b244, 3);
    dfs.addJumpTable(0x5b28f, 3);
    dfs.addJumpTable(0x6801a, 20);

    // jp [hl]
    dfs.addJumpTable(0x1007e, 24);
    dfs.addJumpTable(0x100ae, 24);
    dfs.addJumpTable(0x106fa, 24);
    dfs.addJumpTable(0x1072a, 24);
    dfs.addJumpTable(0x10e64, 11);
    dfs.addJumpTable(0x10e7a, 11);
    dfs.addJumpTable(0x60037, 12);
    dfs.addJumpTable(0x60521, 12);

    dfs.addFunction(0x2058, "DMARoutine");

    // data
    dfs.addByteArray(0x3e1, 4, "Unknown_3e1",
        ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
    dfs.rom.payloadAsAddress[0x6fc] = 0x6f000;
    dfs.addPointerTable(0x763, 4, "BlockBellGoalGFXTable", 7, false);
    dfs.addByteArray(0x7ea, 5, "BottomBarTiles",
        ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);

    dfs.addRawBytes(0x18af, 0x18fb-0x18af, "MarioSpeedTableV");
    dfs.addRawBytes(0x18fb, 0x1947-0x18fb, "MarioSpeedTableVGoo");
    dfs.addRawBytes(0x1947, 0x1993-0x1947, "MarioSpeedTableVWaterSpaceBubble");
    dfs.addRawBytes(0x19ff, 0x100, "MarioSpeedTableH");
    dfs.addRawBytes(0x1aff, 0x100, "MarioSpeedTableHWaterSpaceBubble");

    dfs.addRawBytes(0x1f51, 0x20, "LevelPropertiesScrollUpThreshold");
    dfs.addRawBytes(0x1f71, 0x20, "LevelPropertiesAutoScroll");
    dfs.addRawBytes(0x1f91, 0x20, "LevelPropertiesSpace");
    dfs.addRawBytes(0x21e5, 0x3, ".palettes");
    dfs.addRawBytes(0x268b, 0x2699 - 0x268b, "Unknown_268b");
    dfs.addRawBytes(0x2699, 0x26c3 - 0x2699, "Unknown_2699");
    dfs.addRawBytes(0x29bd, 0x29ca - 0x29bd, "GameClearedText");
    dfs.addRawBytes(0x31ef, 0x324f - 0x31ef, "MarioSpeedTableDead");
    dfs.addRawBytes(0x3439, 0x3451 - 0x3439, "Unknown_3439");
    dfs.addRawBytes(0x35ea, 0x20, "LevelIndexToAnimatedTileModeMap");
    dfs.addRawBytes(0x360a, 0x20, "LevelIndexToGFXSetMap");
    dfs.addByteArray(0x362a, 0x20, "LevelIndexToEnemyGFXSetMap",
        ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
    dfs.addByteArray(0x36aa, 0x20, "LevelIndexToTileGFXSetMap",
        ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
    dfs.addRawBytes(0x3e50e, 0x82, "LocationToLevelSaveIndexMap");
    dfs.addRawBytes(0x3c218, 0x82, "LocationToLevelDataIndexMap");
    dfs.addByteArray(0x61ee6, 0x83, "Unknown_61ee6",
        ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
        ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
    dfs.rom.payloadAsAddress[0x3eea7] = 0x61ee6;
    dfs.rom.payloadAsBank[0x3eeba] = 0x61ee6;

    dfs.addRawBytes(0x3012a, 4, "BombSpriteIndexOffsetTable");

    dfs.addPointerTable(0x14125, 0x20, "PipeFuncTable");
    for (int i = 0; i < 0x20; i++) {
      int add = dfs.rom.payloadAsAddress[0x14125 + 2*i];
      dfs.rom.label[add] = "PipeFunc"+Util.toHex(i, 2);
      while (true) {
        if (dfs.rom.data[add] == 0x40) {
          dfs.addRawBytes(add, 2);
          add += 2;
          continue;
        }
        dfs.addRawByte(add);
        if (dfs.rom.data[add] == (byte)0xff)
          break;
        add++;
      }
    }
    dfs.rom.payloadAsBank[0x20bd] = 0x14125;
    dfs.rom.payloadAsAddress[0x20cf] = 0x14125;

    // Level Headers
    dfs.addPointerTable(0x55cb, 0x20, "LevelHeaderTable");
    for (int i=0;i<0x20; i++) {
      int add = dfs.rom.payloadAsAddress[0x55cb+2*i];
      dfs.rom.label[add] = "LevelHeader"+Util.toHex(i, 2);
      dfs.addRawWord(add+0, "Mario Starting Y");
      dfs.addRawWord(add+2, "Mario Starting X");
      dfs.addRawWord(add+4, "Camera Starting Y");
      dfs.addRawWord(add+6, "Camera Starting X");
      dfs.addRawByte(add+8);
      dfs.addRawByte(add+9);
      dfs.addRawByte(add+10, "Level Number");
      dfs.addRawWord(add+11, "Tileset pointer");
      dfs.addRawByte(add+13, "Level Bank");
      dfs.addRawByte(add+14, "Level Music");
      dfs.addRawByte(add+15, "BGP");
      dfs.addRawByte(add+16, "OBP0");
      dfs.addRawByte(add+17, "OBP1");
      dfs.addRawByte(add+18, "Level Number (in bank)");
      dfs.addRawByte(add+19, "Time");

      int tilesetAddress = (8-1)*0x4000 + (dfs.rom.data[add+12] << 8) + dfs.rom.data[add+11];
      dfs.rom.payloadAsAddress[add+11] = tilesetAddress;
      if (dfs.rom.label[tilesetAddress] == null)
        dfs.rom.label[tilesetAddress] = "Level"+Util.toHex(i, 2)+"Tileset";

      int levelBank = dfs.rom.data[add+13];
      int levelInBank = dfs.rom.data[add+18];
      dfs.addPointerTable(levelBank*0x4000, 1, "LevelBank"+Util.toHex(levelBank)+"ScrollArrayPtr");
      int scrollArrayPtr = dfs.rom.payloadAsAddress[levelBank*0x4000] + (levelInBank * 0x30);
      dfs.addByteArray(scrollArrayPtr, 0x3, "Level"+Util.toHex(i, 2)+"ScrollArray",
          ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
          ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
          ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
          ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
      dfs.addPointerTable(levelBank*0x4000+2, 1, "LevelBank"+Util.toHex(levelBank)+"PipeFuncArrayPtr");
      int unk1Ptr = dfs.rom.payloadAsAddress[levelBank*0x4000+2] + (levelInBank * 0x30);
      dfs.addByteArray(unk1Ptr, 0x3, "Level"+Util.toHex(i, 2)+"PipeFuncArray",
          ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
          ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
          ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX,
          ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX, ROM.FORMAT_HEX);
    }
//    dfs.addRawBytes(0x377b, 0x3873 - 0x377b, "Unknown_377b");
    for (int i=0;i<0x1f; i++) {
      int add = 0x377b+8*i;
      dfs.rom.label[add] = "LevelHeaderBell"+Util.toHex(i, 2);
      dfs.addRawWord(add+0, "Mario Starting Y");
      dfs.addRawWord(add+2, "Mario Starting X");
      dfs.addRawWord(add+4, "Camera Starting Y");
      dfs.addRawWord(add+6, "Camera Starting X");
    }

    // unused code
    dfs.addUnusedFunction(0x33f);
    dfs.addUnusedFunction(0x7fe);
    dfs.addUnusedFunction(0x804);
    dfs.addUnusedFunction(0x816);
    dfs.addUnusedFunction(0x1382);
    dfs.addFunction(0x1415, ".unused_1415", false);
    dfs.addFunction(0x16af, ".unused_16af", false);
    dfs.addUnusedFunction(0x17da);
    dfs.addFunction(0x1ef5, ".unused_1ef5", false);
    dfs.addUnusedFunction(0x1fb1);
    dfs.addUnusedFunction(0x202f);
    dfs.addUnusedFunction(0x2097);
    dfs.addUnusedFunction(0x2098);
    dfs.addUnusedFunction(0x22df);
    dfs.addUnusedFunction(0x2aff);
    dfs.addUnusedFunction(0x2c39);
    dfs.addUnusedFunction(0x2c4d);
    dfs.addUnusedFunction(0x2c8d);
    dfs.addUnusedFunction(0x2d20);
    dfs.addUnusedFunction(0x2d2b);
    dfs.addUnusedFunction(0x2d36);
    dfs.addUnusedFunction(0x2d4c);
    dfs.addUnusedFunction(0x31e0);
    dfs.addUnusedFunction(0x3a3c);
    dfs.addUnusedFunction(0x3ac8);

  }

  @Override
  public void handleBeforeOp(int currentAddress, OpCode opCode, int opData, CPUState s) {
    if (OpCode.loadFromHl.contains(opCode.opCode))
      considerHlAsData(currentAddress, s);
  }

  @Override
  public boolean handleAfterCall(int currentAddress, int callAddress, CPUState s, boolean reachable) {
    if(callAddress == 0x3e00) { // FarCall a:hl
      if((s.rMask[A] & s.rMask[H] & s.rMask[L]) == 0xFF) {
        int jumpAddress;
        if(s.r[H] < 0x40)
          jumpAddress = ((s.r[H]*0x100) | s.r[L]);
        else if(s.r[H] < 0x80)
          jumpAddress = (s.r[A]-1)*0x4000 + ((s.r[H]*0x100) | s.r[L]);
        else {
          System.err.println("ERROR: invalid FarCall to "+Integer.toHexString((s.r[H]*0x100) | s.r[L]));
          return false;
        }

        // set up indirect labels
        if(s.r[H] >= 0x40)
          if(s.rLoadedFromAddress[A] != -1)
            dfs.rom.payloadAsBank[s.rLoadedFromAddress[A]] = jumpAddress;
        if(s.rLoadedFromAddress[H] == s.rLoadedFromAddress[L] && s.rLoadedFromAddress[H] != -1)
          dfs.rom.payloadAsAddress[s.rLoadedFromAddress[H]] = jumpAddress;

        dfs.addIndirectJump(currentAddress, jumpAddress, new CPUState(), 3, reachable);
      }
      return false;
    }
    if(callAddress == 0x336) { // CopyHLtoDE
      considerHlAsData(currentAddress, s);
      s.invalidateRegisters();
      return true;
    }
    if(callAddress == 0x2bfb) { // CopyHLtoDEfromf
      if((s.rMask[A] & s.rMask[H] & s.rMask[L]) == 0xFF) {
        int dataAddress;
        if(s.r[H] < 0x40)
          dataAddress = ((s.r[H]*0x100) | s.r[L]);
        else if(s.r[H] < 0x80)
          dataAddress = (s.r[A]-1)*0x4000 + ((s.r[H]*0x100) | s.r[L]);
        else {
          s.invalidateRegisters();
          return true;
        }

        // set up indirect labels
        if (dfs.rom.label[dataAddress] == null)
          dfs.rom.labelType[dataAddress] = ROM.LABEL_ABSOLUTE;
        if(s.r[H] >= 0x40)
          if(s.rLoadedFromAddress[A] != -1)
            dfs.rom.payloadAsBank[s.rLoadedFromAddress[A]] = dataAddress;
        if(s.rLoadedFromAddress[H] == s.rLoadedFromAddress[L] && s.rLoadedFromAddress[H] != -1)
          dfs.rom.payloadAsAddress[s.rLoadedFromAddress[H]] = dataAddress;
      }
    }
    s.invalidateRegisters();
    return true;
  }

  private void considerHlAsData(int currentAddress, CPUState s) {
    if((s.rMask[H] & s.rMask[L]) == 0xFF) {
      int dataAddress;
      if(s.r[H] < 0x40)
        dataAddress = ((s.r[H]*0x100) | s.r[L]);
      else if(s.r[H] < 0x80 && currentAddress > 0x4000)
        dataAddress = (currentAddress/0x4000-1)*0x4000 + ((s.r[H]*0x100) | s.r[L]);
      else if(s.r[H] < 0x80 && s.loadedBank > 0)
        dataAddress = (s.loadedBank-1)*0x4000 + ((s.r[H]*0x100) | s.r[L]);
      else {
        return;
      }

      // set up indirect labels
      if (dfs.rom.label[dataAddress] == null)
        dfs.rom.labelType[dataAddress] = ROM.LABEL_ABSOLUTE;
      if(s.rLoadedFromAddress[H] == s.rLoadedFromAddress[L] && s.rLoadedFromAddress[H] != -1)
        dfs.rom.payloadAsAddress[s.rLoadedFromAddress[H]] = dataAddress;
    }
  }
}
