package mrwint.gbtasgen.rom.pokemon.gen2;

public class GoldRomInfo extends GenIIRomInfo {
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
		encounterPreCheckAddresses = new int[] {encounterCheckMainFuncAddress};
		encounterPostCheckAddresses = new int[] {encounterCheckMainFuncNoEncounterAddress, encounterCheckMainFuncEncounterAddress};

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
		fightBattleCommand0a = new int[] {0x3503e};
		fightDamageCalc = 0x35753;
		fightDamageVariation = 0x34e4d;
		fightEndTurnAddresses = new int[] {printLetterDelayJoypadAddress, fightEndTurnResetFlags};

		listMoveNamesAddress = 0x1b1574;
		listMovesAddress = 0x41afe;
		listTypeNamesAddress = 0x509ae;
		listTypeMatchupAddress = 0x34d01;
	}
}