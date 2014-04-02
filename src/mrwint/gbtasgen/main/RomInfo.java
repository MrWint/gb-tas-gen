package mrwint.gbtasgen.main;

public class RomInfo {
	public static RomInfo rom;
	
	public static final int CRYSTAL = 0;
	public static final int GOLD = 1;
	public static final int SILVER = 2;

	public static final int RED = 3;
	public static final int BLUE = 4;

	public int type;
	
	public String fileNameSuffix;
	public String romFileName;
	public String romName;
	public String romSHA1;
	
	// input
	public int readJoypadAddress;
	public int hJoypadReleasedAddress;
	public int hJoypadPressedAddress;
	public int hJoypadDownAddress;
	public int hJoypadMenuAddress;

	// text
	public int printLetterDelayJoypadAddress;
	public int printLetterDelayDelayFrameAddress;
	public int printLetterDelayDoneAddress;

	// map
	public int tileCollisionTableAddress;
	public int mapGroupPointersAddress;
	public int tilesetsAddress;
	public int curBlockDataAddress;
	public int curMapGroupAddress;
	public int curMapIDAddress;
	public int eventFlagsAddress;
	
	// options
	public int optionsAddress;
	public int optionsTextSpeedMask;
	
	// stats
	public int afterDVGenerationAddress;
	public int afterTrainerIDGenerationAddress;
	public int trainerIDAddress;
	
	//walking
	public int doPlayerMovementFuncAddress;
	public int doPlayerMovementFuncEndAddress;
	public int playerMovingIndicatorAddress;
	public int playerDirectionAddress;
	public int movementAnimationAddress;
	
	public int curPositionXAddress;
	public int curPositionYAddress;
	
	public int encounterCheckMainFuncAddress;
	public int encounterCheckMainFuncNoEncounterAddress;
	public int encounterCheckMainFuncEncounterAddress;
	public int encounterMonLevelAddress;
	public int encounterMonSpeciesAddress;
	public int catchSuccessAddress;
	public int catchFailureAddress;
	
	// interaction
	public int owPlayerInputCheckAAddress;
	public int owPlayerInputNoActionAddress;
	public int owPlayerInputActionAddress;
	
	// battle
	public int fightBattleMonLevelAddress;
	public int fightBattleMonMovesAddress;
	public int fightBattleMonStatsAddress;
	public int fightBattleMonOriginalStatsAddress;
	public int fightBattleMonTypesAddress;
	public int fightBattleMonHPAddress;
	
	public int fightEnemyMonSpeciesAddress;
	public int fightEnemyMonLevelAddress;
	public int fightEnemyMonMovesAddress;
	public int fightEnemyMonStatsAddress;
	public int fightEnemyMonOriginalStatsAddress;
	public int fightEnemyMonTypesAddress;
	public int fightEnemyMonHPAddress;
	
	public int fightCurMoveNumAddress;
	public int fightCriticalHitAddress;
	public int fightAttackMissedAddress;
	public int fightEffectMissedAddress;
	public int fightCurDamageAddress;

	public int fightCurEnemyMoveAddress;
	public int fightDetermineAttackOrder;
	public int fightDetermineAttackOrderPlayerFirst;
	public int fightDetermineAttackOrderEnemyFirst;
	public int fightAfterAIChooseMove;
	public int fightEndTurnResetFlags;
	public int fightBattleCommand0a;
	public int fightDamageCalc;
	public int fightDamageVariation;
	
	// lists
	public int listMoveNamesAddress;
	public int listMovesAddress;
	public int listTypeNamesAddress;
	public int listTypeMatchupAddress;

	public int rngAddress;
	
	public static class GenIIRomInfo extends RomInfo {
		public GenIIRomInfo() {
			optionsTextSpeedMask = 0x7;
		}
	}
	
	public static class CrystalRomInfo extends GenIIRomInfo {
		public CrystalRomInfo() {
			super();
			
			type = CRYSTAL;
			
			fileNameSuffix = "Crystal";
			
			romFileName = "roms/crystal.gbc";
			romName = "Pokemon - Crystal Version (USA, Europe)";
			romSHA1 = "F4CD194BDEE0D04CA4EAC29E09B8E4E9D818C133";
			
			rngAddress = 0xFFE1;
			
			readJoypadAddress = 0x98f; // line that reads hJoypadDown
			hJoypadReleasedAddress = 0xffa6;
			hJoypadPressedAddress = 0xffa7;
			hJoypadDownAddress = 0xffa8;
			hJoypadMenuAddress = 0xffa7;
			printLetterDelayJoypadAddress = 0x3165;
			printLetterDelayDelayFrameAddress = 0x317a;
			printLetterDelayDoneAddress = 0x3185;
			
			tileCollisionTableAddress = 0x4ce1f;
			mapGroupPointersAddress = 0x94000;
			tilesetsAddress = 0x4d596;
			curBlockDataAddress = 0xc800;
			curMapGroupAddress = 0xdcb5;
			curMapIDAddress = 0xdcb6;
			eventFlagsAddress = 0xda72;
			
			optionsAddress = 0xcfcc; // determines text speed
			
			afterDVGenerationAddress = 0xd9b5;
			afterTrainerIDGenerationAddress = 0x5be4;
			trainerIDAddress = 0xd47b;
			
			doPlayerMovementFuncAddress = 0x80000;
			doPlayerMovementFuncEndAddress = 0x80016;
			playerMovingIndicatorAddress = 0xd04e;
			playerDirectionAddress = 0xd4de;
			movementAnimationAddress = 0xd042;
			
			curPositionXAddress = 0xd4e6;
			curPositionYAddress = 0xd4e7;
			
			encounterCheckMainFuncAddress = 0x97cc0;
			encounterCheckMainFuncNoEncounterAddress = 0x97ce2;
			encounterCheckMainFuncEncounterAddress = 0x97cf4;
			encounterMonLevelAddress = 0xd143;
			encounterMonSpeciesAddress = 0xd22e;
			catchSuccessAddress = 0xe99c;
			catchFailureAddress = 0xe99f;
			
			owPlayerInputCheckAAddress = 0x96983;
			owPlayerInputNoActionAddress = 0x9698d;
			owPlayerInputActionAddress = 0x9698f;
			
			fightBattleMonLevelAddress = 0xc639;
			fightBattleMonMovesAddress = 0xc62e;
			fightBattleMonStatsAddress = 0xc640;
			fightBattleMonOriginalStatsAddress = 0xc6b6;
			fightBattleMonTypesAddress = 0xc64a;
			fightBattleMonHPAddress = 0xc63c;
			fightEnemyMonSpeciesAddress = 0xd206;
			fightEnemyMonMovesAddress = 0xd208;
			fightEnemyMonLevelAddress = 0xd213;
			fightEnemyMonStatsAddress = 0xd21a;
			fightEnemyMonOriginalStatsAddress = 0xc6c1;
			fightEnemyMonTypesAddress = 0xd224;
			fightEnemyMonHPAddress = 0xd216;
			fightCurMoveNumAddress = 0xd0d5;
			fightCurEnemyMoveAddress = 0xc6e4;
			fightCriticalHitAddress = 0xc666;
			fightAttackMissedAddress = 0xc667;
			fightEffectMissedAddress = 0xc70d;
			fightCurDamageAddress = 0xd256;
			
			fightDetermineAttackOrder = 0x3c314;
			fightDetermineAttackOrderPlayerFirst = 0x3c3f1;
			fightDetermineAttackOrderEnemyFirst = 0x3c3f3;
			fightAfterAIChooseMove = 0x3c174;
			fightEndTurnResetFlags = 0x3c6ed;
			fightBattleCommand0a = 0x34eee;
			fightDamageCalc = 0x35612;
			fightDamageVariation = 0x34cfd;
			
			listMoveNamesAddress = (0x72 -1) * 0x4000 + 0x5F29;
			listMovesAddress = 0x41afb;
			listTypeNamesAddress = 0x5097b;
			listTypeMatchupAddress = 0x34bb1;
		}
	}
	public static class GoldRomInfo extends GenIIRomInfo {
		public GoldRomInfo() {
			super();
			
			type = GOLD;
			
			fileNameSuffix = "Gold";
			
			romFileName = "roms/gold.gbc";
			romName = "Pokemon - Gold Version (USA, Europe)";
			romSHA1 = "D8B8A3600A465308C9953DFA04F0081C05BDCB94";
			
			rngAddress = 0xFFE3;

			readJoypadAddress = 0x940; // line that reads hJoypadDown
			hJoypadReleasedAddress = 0xffa8;
			hJoypadPressedAddress = 0xffa9;
			hJoypadDownAddress = 0xffaa;
			hJoypadMenuAddress = 0xffa9;
			printLetterDelayJoypadAddress = 0x320a;
			printLetterDelayDelayFrameAddress = 0x321f;
			printLetterDelayDoneAddress = 0x322a;

			tileCollisionTableAddress = (0x3e -1) * 0x4000 + 0x74be;
			mapGroupPointersAddress = (0x25 -1) * 0x4000 + 0x40ed;
			tilesetsAddress = (0x5 -1) * 0x4000 + 0x56be;
			curBlockDataAddress = 0xc700;
			curMapGroupAddress = 0xda00;
			curMapIDAddress = 0xda01;
			eventFlagsAddress = 0xd7b7;

			optionsAddress = 0xd199; // determines text speed

			afterDVGenerationAddress = 0xd9bb;
			afterTrainerIDGenerationAddress = 0x5c6d;
			trainerIDAddress = 0xd1a1;

			doPlayerMovementFuncAddress = 0x10000;
			doPlayerMovementFuncEndAddress = 0x10016;
			playerMovingIndicatorAddress = 0xcf39;
			playerDirectionAddress = 0xd205;
			movementAnimationAddress = 0xcf2d;

			curPositionXAddress = 0xd20d;
			curPositionYAddress = 0xd20e;

			encounterCheckMainFuncAddress = 0x97af1;
			encounterCheckMainFuncNoEncounterAddress = 0x97b13;
			encounterCheckMainFuncEncounterAddress = 0x97b25;
			encounterMonLevelAddress = 0xd040;
			encounterMonSpeciesAddress = 0xd117;
			catchSuccessAddress = 0xea20;
			catchFailureAddress = 0xea23;

			owPlayerInputCheckAAddress = 0x968b6;
			owPlayerInputNoActionAddress = 0x968c0;
			owPlayerInputActionAddress = 0x968c2;

			fightBattleMonMovesAddress = 0xcb0e;
			fightBattleMonLevelAddress = 0xcb19;
			fightBattleMonHPAddress = 0xcb1c;
			fightBattleMonStatsAddress = 0xcb20;
			fightBattleMonTypesAddress = 0xcb2a;
			fightCriticalHitAddress = 0xcb44;
			fightAttackMissedAddress = 0xcb45;
			fightEffectMissedAddress = 0xcbeb;
			fightBattleMonOriginalStatsAddress = 0xcb94;
			fightEnemyMonOriginalStatsAddress = 0xcb9f;
			fightCurEnemyMoveAddress = 0xcbc2;
			fightCurMoveNumAddress = 0xcfc7;
			fightEnemyMonSpeciesAddress = 0xd0cf;
			fightEnemyMonMovesAddress = 0xd0f1;
			fightEnemyMonLevelAddress = 0xd0fc;
			fightEnemyMonHPAddress = 0xd0ff;
			fightEnemyMonStatsAddress = 0xd103;
			fightEnemyMonTypesAddress = 0xd10d;
			fightCurDamageAddress = 0xd141;

			fightDetermineAttackOrder = 0x3c2cb;
			fightDetermineAttackOrderPlayerFirst = 0x3c3a8;
			fightDetermineAttackOrderEnemyFirst = 0x3c3aa;
			fightAfterAIChooseMove = 0x3c148;
			fightEndTurnResetFlags = 0x3c695;
			fightBattleCommand0a = 0x3503e;
			fightDamageCalc = 0x35753;
			fightDamageVariation = 0x34e4d;

			listMoveNamesAddress = 0x1b1574;
			listMovesAddress = 0x41afe;
			listTypeNamesAddress = 0x509ae;
			listTypeMatchupAddress = 0x34d01;
		}
	}
	
	public static class SilverRomInfo extends GoldRomInfo {
		public SilverRomInfo() {
			super();
			
			type = SILVER;
			
			fileNameSuffix = "Silver";
			
			romFileName = "roms/silver.gbc";
			romName = "Pokemon - Silver Version (USA, Europe)";
			romSHA1 = "49B163F7E57702BC939D642A18F591DE55D92DAE";
			
			catchSuccessAddress = 0xea1e;
			catchFailureAddress = 0xea21;
		}
	}
	
	public static class GenIRomInfo extends RomInfo {
		public GenIRomInfo() {
			optionsTextSpeedMask = 0xF;
		}
	}
	
	public static class RedRomInfo extends GenIRomInfo {
		public RedRomInfo() {
			super();
			
			type = RED;
			
			fileNameSuffix = "Red";
			
			romFileName = "roms/red.gb";
			romName = "Pokemon - Red Version (USA, Europe)";
			romSHA1 = "EA9BCAE617FDF159B045185467AE58B2E4A48B9A";
			
			rngAddress = 0xFFD3;

			readJoypadAddress = 0xc000; // line that reads hJoypadDown
			hJoypadReleasedAddress = 0xffb2;
			hJoypadPressedAddress = 0xffb3;
			hJoypadDownAddress = 0xffb4;
			hJoypadMenuAddress = 0xffb5;
			printLetterDelayJoypadAddress = 0x38f6;
			printLetterDelayDelayFrameAddress = 0x3905;
			printLetterDelayDoneAddress = 0x390f;

//			tileCollisionTableAddress = (0x3e -1) * 0x4000 + 0x74be;
//			mapGroupPointersAddress = (0x25 -1) * 0x4000 + 0x40ed;
//			tilesetsAddress = (0x5 -1) * 0x4000 + 0x56be;
//			curBlockDataAddress = 0xc700;
//			curMapGroupAddress = 0xda00;
//			curMapIDAddress = 0xda01;
//			eventFlagsAddress = 0xd7b7;
//
			optionsAddress = 0xd355; // determines text speed
//
//			afterDVGenerationAddress = 0xd9bb;
//			afterTrainerIDGenerationAddress = 0x5c6d;
//			trainerIDAddress = 0xd1a1;
//
//			doPlayerMovementFuncAddress = 0x10000;
//			doPlayerMovementFuncEndAddress = 0x10016;
//			playerMovingIndicatorAddress = 0xcf39;
//			playerDirectionAddress = 0xd205;
//			movementAnimationAddress = 0xcf2d;
//
//			curPositionXAddress = 0xd20d;
//			curPositionYAddress = 0xd20e;
//
//			encounterCheckMainFuncAddress = 0x97af1;
//			encounterCheckMainFuncNoEncounterAddress = 0x97b13;
//			encounterCheckMainFuncEncounterAddress = 0x97b25;
//			encounterMonLevelAddress = 0xd040;
//			encounterMonSpeciesAddress = 0xd117;
//			catchSuccessAddress = 0xea20;
//			catchFailureAddress = 0xea23;
//
//			owPlayerInputCheckAAddress = 0x968b6;
//			owPlayerInputNoActionAddress = 0x968c0;
//			owPlayerInputActionAddress = 0x968c2;
//
//			fightBattleMonMovesAddress = 0xcb0e;
//			fightBattleMonLevelAddress = 0xcb19;
//			fightBattleMonHPAddress = 0xcb1c;
//			fightBattleMonStatsAddress = 0xcb20;
//			fightBattleMonTypesAddress = 0xcb2a;
//			fightCriticalHitAddress = 0xcb44;
//			fightAttackMissedAddress = 0xcb45;
//			fightEffectMissedAddress = 0xcbeb;
//			fightBattleMonOriginalStatsAddress = 0xcb94;
//			fightEnemyMonOriginalStatsAddress = 0xcb9f;
//			fightCurEnemyMoveAddress = 0xcbc2;
//			fightCurMoveNumAddress = 0xcfc7;
//			fightEnemyMonSpeciesAddress = 0xd0cf;
//			fightEnemyMonMovesAddress = 0xd0f1;
//			fightEnemyMonLevelAddress = 0xd0fc;
//			fightEnemyMonHPAddress = 0xd0ff;
//			fightEnemyMonStatsAddress = 0xd103;
//			fightEnemyMonTypesAddress = 0xd10d;
//			fightCurDamageAddress = 0xd141;
//
//			fightDetermineAttackOrder = 0x3c2cb;
//			fightDetermineAttackOrderPlayerFirst = 0x3c3a8;
//			fightDetermineAttackOrderEnemyFirst = 0x3c3aa;
//			fightAfterAIChooseMove = 0x3c148;
//			fightEndTurnResetFlags = 0x3c695;
//			fightBattleCommand0a = 0x3503e;
//			fightDamageCalc = 0x35753;
//			fightDamageVariation = 0x34e4d;

			listMoveNamesAddress = 0xb0000;
			listMovesAddress = 0x38000;
			listTypeNamesAddress = 0x27dae;
			listTypeMatchupAddress = 0x3e474;
		}
	}

	public static class BlueRomInfo extends RedRomInfo {
		public BlueRomInfo() {
			super();

			type = BLUE;

			fileNameSuffix = "Blue";

			romFileName = "roms/blue.gb";
			romName = "Pokemon - Blue Version (USA, Europe)";
			romSHA1 = "D7037C83E1AE5B39BDE3C30787637BA1D4C48CE2";
		}
	}
}
