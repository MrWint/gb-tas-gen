package mrwint.gbtasgen.segment.tetris;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.RunUntil;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.tetris.TetrisRomInfo;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SleepSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Runner;
import mrwint.gbtasgen.util.Util;

public class Tetris extends SeqSegment {

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
		seq(new PressButton(Move.A), 0);
		seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0xa ? 1 : 0);}));
		StateBuffer sb = save();
		for (int i=0;i<0x4000; i++) {
			final int fi = i;
			if (fi % 0x100 == 0)
				System.out.println(Util.toHex(i));
			load(sb);
			seq(new Move() {
				@Override public int getInitialKey() { return 0; }
				@Override
				public boolean doMove() {
					Gb.offsetRNG(fi);
					return true;
				}
			});
			seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0x0 ? 1 : 0);}));
			seq(new Metric() {
				@Override
				public int getMetric() {
					int numFull = 0;
					int numRows = 10;
					int startAddress = 0xc902;
					for (int r = 0; r < numRows; r++)
						for (int a = startAddress + r*0x20; a < startAddress + r*0x20 + 10; a++)
							if (Gb.readMemory(a) != 0x2f)
								numFull++;
					if (numFull >= 65)
						System.out.println("found: " + numFull);
					return (numFull < 66 ? 0 : 1);
				}
			});
			seq(new Wait(10));
			seq(new Move() {
				@Override public int getInitialKey() { return 0; }
				@Override
				public boolean doMove() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}
			});
		}


//		delay(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(new PressButton(Move.A), 0);
//				seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0x0 ? 1 : 0);}));
//				seq(new Metric() {
//					@Override
//					public int getMetric() {
//						for (int a = 0xca04; a < 0xca0c; a++)
//							if (Gb.readMemory(a) == 0x2f)
//								return 0;
//						for (int a = 0xca24; a < 0xca2c; a++)
//							if (Gb.readMemory(a) == 0x2f)
//								return 0;
//						return 1;
//					}
//				});
//			}
//		});
//		seq(new Wait(10));
	}

	public static void main(String[] args) {
		RomInfo.setRom(new TetrisRomInfo());

		Runner.run(new Tetris());
	}
}
