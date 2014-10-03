package mrwint.gbtasgen.segment.pokemon.gen1.noww;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.move.pokemon.SelectMoveInList;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.BallSuccessSegment;
import mrwint.gbtasgen.segment.pokemon.CatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.PokecenterSegment;
import mrwint.gbtasgen.segment.pokemon.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.CheckMoveDamage;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.CheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;
import mrwint.gbtasgen.util.pokemon.fight.DamageCalc;

public class ViridianForestNidoking extends SeqSegment {

	@Override
	public void execute() {

//		seq(new WalkToSegment(23, 25)); // enter center
//		seq(new PokecenterSegment(true));
//
//		seq(new WalkToSegment(29, 19)); // enter pewter mart
//
//		seq(new WalkToSegment(3, 5));
//		seq(new WalkToSegment(2, 5));
//		seq(new MoveSegment(new OverworldInteract(1)));
//		{
//			seq(new SkipTextsSegment(1, true)); // buy
//			seq(new TextSegment(Move.B));
//			{
//				seq(Segment.skip(1));
//				seq(Segment.press(Move.A)); // pokeball
//				seq(Segment.repress(Move.A)); // x1
//				seq(new SkipTextsSegment(1)); // confirmation text
//				seq(new SkipTextsSegment(1, true)); // "yes"
//				seq(new SkipTextsSegment(1)); // thank you text
//			}
//			seq(Segment.repress(Move.B)); // cancel
//			seq(new SkipTextsSegment(2)); // cancel + bye
//		}
//
//		seq(new WalkToSegment(3, 8, false)); // leave mart
//
//		seq(new WalkToSegment(18, -1)); // leave viridian
//		seq(new WalkToSegment(3, 43)); // enter viridian forest house
//		seq(new WalkToSegment(5, 0)); // enter viridian forest
//
//		save("vfn1");
//		load("vfn1");
//
//		seq(new WalkToSegment(23, 9)); // walk up to encounter
//		seq(new DelayMoveSegment(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(Move.LEFT);
//				seq(new CheckEncounterMetric(112 /* Weedle */));
//			}
//		}));
//
////		save("tmp2");
////		load("tmp2");
//
//		seq(new SkipTextsSegment(1));
//		seq(new TextSegment(Move.B));
//		seq(Segment.press(Move.A));
////		seq(Move.SELECT);
////		seq(Move.DOWN);
////		seq(Move.SELECT); // swap growl with tackle
//		seq(new SelectMoveInList(1, 2)); // select growl
//
//		seq(new DelayMoveSegment(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(new CheckMoveOrderMetric(true, new int[] { 40 }, Move.A));
//				seq(Move.A);
//				seq(new TextSegment(Move.A, false, 0));
//				seq(new Metric() {
//					@Override
//					public int getMetric() {
//						return Gb.readMemory(RomInfo.rom.fightCurEnemyMoveAddress) == 40 ? 1 : 0;
//					}
//				});
//				seq(new CheckLowerStatEffectMisses());
//				seq(new Wait(1));
//			}
//		}));
////		seq(new TextSegment(Move.A)); // used
//		seq(new TextSegment(Move.A)); // fell/missed (whatever)
//		seq(new DelayMoveSegment(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(Move.B);
//				seq(new TextSegment(Move.A, false));
//				seq(new CheckMoveDamage(false, false, true, true, false, false,
//						0), new Equal(), 2);
//				seq(new Wait(1));
//			}
//		}));
//		seq(new SkipTextsSegment(1)); // got poisoned
//		seq(Move.DOWN); // item
//		seq(Move.RIGHT); // run
//		seq(Move.A); // run
//		seq(new SkipTextsSegment(1)); // got away safely
//
//		save("vfn2a");
////		load("vfn2a");
//
//		seq(new WalkToSegment(1, 20)); // walk up to trainer
//		seq(new WalkToSegment(1, 19)); // walk up to trainer
//		seq(new DelayMoveSegment(new SeqSegment() { // get an encounter
//			@Override
//			protected void execute() {
//				seq(Move.UP);
//				seq(new CheckEncounterMetric(0 /* any */));
//			}
//		}));
//		seq(new SkipTextsSegment(1));
//		seq(new TextSegment(Move.B));
//		seq(Segment.press(Move.A));
//		seq(new SelectMoveInList(1, 2)); // select growl
//		save("tmp");
//		seq(new DelayMoveSegment(new SeqSegment() {
//			@Override
//			protected void execute() {
////				seq(new CheckMoveOrderMetric(true, new int[] { 81 }, Move.A));
//				seq(Move.A);
//				seq(new TextSegment(Move.A, false, 0));
//				seq(new CheckLowerStatEffectMisses());
//				seq(new Wait(1));
//			}
//		}));
////		seq(new TextSegment(Move.A)); // used
//		seq(new SkipTextsSegment(5)); // fell/missed + hurt by poison + fainted
//										// + out of mons + blacked out
//
//		save("vfn3b");
//		load("vfn3b");
//
//		{
//			seq(new WalkToSegment(23, 25)); // into pokecenter
//			seq(new WalkToSegment(13, 4));
//			seq(new WalkToSegment(13, 3, false)); // correct facing direction
//			seq(Segment.repress(Move.A));
//			seq(new TextSegment());
//			seq(Segment.repress(Move.B)); // confirm dialog
//			seq(Segment.repress(Move.A)); // someones PC
//			seq(new TextSegment());
//			seq(Segment.repress(Move.B)); // confirm dialog
//			seq(new TextSegment());
//			seq(Segment.repress(Move.B)); // confirm dialog
//			seq(Segment.repress(Move.DOWN));
//			seq(Segment.repress(Move.DOWN));
//			seq(Segment.repress(Move.DOWN)); // change box
//			seq(Segment.repress(Move.A)); // change box
//			// seq(new TextSegment()); // text segments will be skipped (bug?)
//			seq(Segment.repress(Move.B)); // confirm dialog
//			seq(Segment.repress(Move.B)); // confirm dialog
//			seq(Segment.repress(Move.A)); // save
//			// seq(new MoveSegment(new MenuInteraction(Move.DOWN)));
//			seq(Segment.menu(Move.A)); // switch to box 1
//			seq(new ResetAndContinueSegment()); // quit and continue
//
//			seq(new WalkToSegment(4, 8, false));
//		}
//
//		seq(new WalkToSegment(18, -1)); // leave viridian
//		seq(new WalkToSegment(4, 52)); // align
//		seq(new DelayMoveSegment(new SeqSegment() { // get an encounter
//					@Override
//					protected void execute() {
//						seq(Move.UP);
//						seq(new CheckEncounterMetric(165).withSpcDefStat(7)); // rattata
//					}
//				}));
//
//		save("vfn4");
//		load("vfn4");
//
//		seq(new SkipTextsSegment(1));
//		seq(new TextSegment(Move.B));
//
//		for (int i = 0; i < 6; i++) {
//			seq(Segment.press(Move.A));
////			seq(Segment.skip(1));
//			seq(new SelectMoveInList(1, 2)); // select growl
//			seq(new DelayMoveSegment(new PressButtonFactory(Move.A,
//					Metric.PRESSED_JOY), new SeqSegment() {
//				@Override
//				protected void execute() {
//					seq(new CheckMoveOrderMetric(true, new int[] { 39 }, Move.A));
//					seq(Move.A);
//				}
//			}, 60, 2, true));
//			seq(new TextSegment(Move.A)); // used
//			seq(new TextSegment(Move.A)); // fell
//			seq(new DelayMoveSegment(new SeqSegment() {
//				@Override
//				protected void execute() {
//					seq(Move.B);
//					seq(new TextSegment(Move.A, false));
//					// seq(new CheckAttackMisses());
//					seq(new CheckLowerStatEffectMisses());
//					seq(new Wait(1));
//				}
//			}));
//			seq(new SkipTextsSegment(1));
//		}
//
//		seq(Move.DOWN); // item
//		// {
//		// seq(Move.A); // item
//		// seq(new BallSuccessSegment());
//		//
//		// seq(new SkipTextsSegment(4)); // cought, new dex data
//		// seq(Segment.press(Move.A)); // skip dex
//		// seq(Segment.press(Move.B)); // skip dex
//		// seq(new SkipTextsSegment(2)); // no nickname
//		// }
//		{
//			seq(Move.RIGHT); // run
//			seq(Move.A); // run
//			seq(new SkipTextsSegment(1)); // got away safely
//		}
//
//		seq(new WalkToSegment(3, 43)); // enter viridian forest house
//		seq(new WalkToSegment(5, 0)); // enter viridian forest
//
//		save("vfn5");
//		load("vfn5");
//
//		delay(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(Move.START);
//				seq(new StateResettingMetric() {
//					@Override
//					public int getMetricInternal() {
//						Util.runToAddress(0, 0, RomInfo.rom.printLetterDelayJoypadAddress);
//						return Gb.readMemory(RomInfo.rom.fightEnemyDVsAddress) >= 0xF0 ? 1 : 0;
//					}
//				});
//			}
//		});
////		seq(Move.START);
//		seq(new CatchMonSegment(0, "A"));
//
//		save("vfn6");
//		load("vfn6");
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scroll(1));
//		seq(Move.A);
//		seq(Segment.repress(Move.A));
//		seq(Move.DOWN);
//		seq(Move.A);
//		seq(Move.DOWN);
//		seq(Move.A);
//		seq(Move.B);
//		seq(Move.START);
//
//		seq(new WalkToSegment(18, 10)); // walk up to encounter
//
//		save("tmp");
//		load("tmp");
//
//		seq(new WalkToSegment(16, 10)); // walk up to encounter
//		seq(new WalkToSegment(16, 14)); // walk up to encounter
//
//		seq(new DelayMoveSegment(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(Move.DOWN);
//				seq(new CheckEncounterMetric(112 /* Weedle */, 3).withDefStat(6));
//			}
//		}));
//
//		save("tmp2");
////		load("tmp2");
////
//		seq(new SkipTextsSegment(1));
//		seq(new TextSegment());
////		seq(Move.A);
////		seq(Move.UP);
////		seq(new DelayMoveSegment(new SeqSegment() {
////			@Override
////			protected void execute() {
////				seq(new CheckMoveOrderMetric(false, new int[] { 81 }, Move.A));
////				seq(Move.A);
////				seq(new TextSegment(Move.A, false, 0));
////				seq(new CheckLowerStatEffectMisses());
////				seq(new Wait(1));
////			}
////		}));
////		for (int i = 0; i < 2; i++) {
////			seq(new TextSegment(Move.A)); // it missed
////			seq(new DelayMoveSegment(new SeqSegment() {
////				@Override
////				protected void execute() {
////					seq(Move.B);
////					seq(new TextSegment(Move.A, false, 0));
////					seq(new CheckMoveDamage(false, false, true, false, false, false, 0), new Equal(), 5);
////					seq(new CheckMoveOrderMetric(false, new int[] { 81 }, 0));
////					seq(new MoveSegment(new Wait(1), 0, 0));
////					seq(new TextSegment(Move.A, false, 0));
////					seq(new CheckLowerStatEffectMisses());
////					seq(new Wait(1));
////				}
////			}));
////		}
////		seq(new TextSegment(Move.A)); // it missed
////		seq(new DelayMoveSegment(new SeqSegment() {
////			@Override
////			protected void execute() {
////				seq(Move.B);
////				seq(new TextSegment(Move.A, false, 0));
////				seq(new CheckMoveDamage(false, false, true, false, false, false, 0), new Equal(), 5);
////				seq(new Wait(1));
////			}
////		}));
////		seq(new SkipTextsSegment(3)); // fainted, xp, l100
//////		seq(Segment.press(Move.A));
//////		seq(new SelectMoveInList(1, 2)); // select growl
//////		seq(new DelayMoveSegment(new PressButtonFactory(Move.A,
//////				Metric.PRESSED_JOY), new SeqSegment() {
//////			@Override
//////			protected void execute() {
//////				seq(new CheckMoveOrderMetric(true, new int[] { 39 }, Move.A));
//////				seq(Move.A);
//////			}
//////		}, 60, 2, true));
//////		seq(new TextSegment(Move.A)); // used
//////		seq(new TextSegment(Move.A)); // fell
//////		seq(new DelayMoveSegment(new SeqSegment() {
//////			@Override
//////			protected void execute() {
//////				seq(Move.B);
//////				seq(new TextSegment(Move.A, false));
//////				// seq(new CheckAttackMisses());
//////				seq(new CheckLowerStatEffectMisses());
//////				seq(new Wait(1));
//////			}
//////		}));
//////		seq(new SkipTextsSegment(1));
//////	}
//		seq(Move.RIGHT);
//		seq(Move.A);
//		seq(Move.DOWN);
//		seq(Move.A);
//		seq(new DelayMoveSegment(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(Segment.repress(Move.A));
//				seq(new CheckMoveOrderMetric(true, new int[] { 81 }, Move.A));
//				seq(new TextSegment()); // come back
//				seq(new TextSegment()); // go
//				seq(new TextSegment(Move.A, false)); // used
//				seq(new Metric() {
//					@Override
//					public int getMetric() {
//						return Gb.readMemory(RomInfo.rom.fightCurEnemyMoveAddress) == 81 ? 1 : 0;
//					}
//				});
//				seq(new CheckLowerStatEffectMisses());
//				seq(new Wait(1));
//				seq(new SkipTextsSegment(1));
//			}
//		}));
//		save("tmp3");
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[] { EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81) }; // string shot
//			kems.attackCount[0][0] = 1; // 1x scratch
//			kems.attackCount[0][1] = 1; // 1x scratch crit
//			kems.numExpGainers = 3; // no level up, level up to 100
//			kems.onlyPrintInfo = false;
//			seq(kems); // Weedle
//		}
//////
//		save("tmp4");
		load("tmp4");

		seq(new WalkToSegment(2, 19)); // walk up to trainer
		seq(new MoveSegment(new OverworldInteract(4))); // talk to trainer

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][0] = 1; // 1x tackle
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Weedle
		}
		seq(new EndFightSegment(2)); // player defeated enemy

		seq(new WalkToSegment(1, -1, false)); // leave forest
		seq(new WalkToSegment(5, 0)); // leave forest house
		seq(new WalkToSegment(8, -1)); // enter pewter city
	}
}
