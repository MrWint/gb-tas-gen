package mrwint.gbtasgen.rom.pokemon.gen2;

public class CrystalRomInfo extends GenIIRomInfo {
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
		encounterPreCheckAddresses = new int[] {encounterCheckMainFuncAddress};
		encounterPostCheckAddresses = new int[] {encounterCheckMainFuncNoEncounterAddress, encounterCheckMainFuncEncounterAddress};

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
		fightBattleCommand0a = new int[] {0x34eee};
		fightDamageCalc = 0x35612;
		fightDamageVariation = 0x34cfd;
		fightEndTurnAddresses = new int[] {printLetterDelayJoypadAddress, fightEndTurnResetFlags};

    listMonNamesAddress = 0x14*0x4000 + 0x3384;
		listMoveNamesAddress = (0x72 -1) * 0x4000 + 0x5F29;
		listMovesAddress = 0x41afb;
		listTypeNamesAddress = 0x5097b;
		listTypeMatchupAddress = 0x34bb1;
	}
}