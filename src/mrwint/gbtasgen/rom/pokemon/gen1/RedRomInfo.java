package mrwint.gbtasgen.rom.pokemon.gen1;

public class RedRomInfo extends GenIRomInfo {
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
			curBlockDataAddress = 0xc6e8;
//			curMapGroupAddress = 0xda00;
			curMapIDAddress = 0xd35e;
//			eventFlagsAddress = 0xd7b7;
			mapHeaderBanksAddress = 0xc23d;
			mapHeaderPointersAddress = 0x1ae;
			trainerHeaderPointerAddress = 0xda30;
			blockTilesPointerAddress = 0xd52b;
			grassTileAddress = 0xd535;
			collisionDataAddress = 0xd530;
			missableObjectListAddress = 0xd5ce;
			missableObjectFlagsAddress = 0xd5a6;
			tilePairCollisionsLandAddress = 0xc7e;
			tilePairCollisionsWaterAddress = 0xca0;
			curTilesetAddress = 0xd367;
//
			optionsAddress = 0xd355; // determines text speed
//
			afterDVGenerationAddress = 0xf3b3;
			afterTrainerIDGenerationAddress = 0xf860;
			trainerIDAddress = 0xd359;
//
			doPlayerMovementFuncAddress = 0x51d; // .handleDirectionButtonPress
//			doPlayerMovementFuncEndAddress = 0x10016;
			playerMovingIndicatorAddress = 0xcc4b;
			playerDirectionAddress = 0xd529;
//			movementAnimationAddress = 0xcf2d;
			doTurnPreEncounterCheckAddress = 0x575;
			doTurnPostEncounterCheckAddress = 0x578;
			doWalkPreEncounterCheckAddress = 0x62c;
			doWalkPostEncounterCheckAddress = 0x62f;
			walkSuccessAddress = 0x5ae; // .noCollision
			walkFailAddress = 0x593; // failed jump to .noCollision
//
			curPositionXAddress = 0xd362;
			curPositionYAddress = 0xd361;
//
//			encounterCheckMainFuncAddress = 0x97af1;
//			encounterCheckMainFuncNoEncounterAddress = 0x97b13;
			encounterCheckMainFuncEncounterAddress = 0x13916;
			encounterMonLevelAddress = 0xd127;
			encounterMonSpeciesAddress = 0xcfd8;
			catchSuccessAddress = 0xd868;
			catchFailureAddress = 0xd922;
			encounterPreCheckAddresses = new int[] {doTurnPreEncounterCheckAddress, doWalkPreEncounterCheckAddress};
			encounterPostCheckAddresses = new int[] {encounterCheckMainFuncEncounterAddress, doTurnPostEncounterCheckAddress, doWalkPostEncounterCheckAddress};

			owPlayerInputCheckAAddress = 0x477;
			owInteractionTargetAddress = 0xff8c;
			owInteractionSuccessfulAddress = 0x496;
			owLoopAddress = 0x3ff;
//			owPlayerInputNoActionAddress = 0x968c0;
//			owPlayerInputActionAddress = 0x968c2;
//
			fightBattleMonIndex = 0xcc2f;
			fightBattleMonMovesAddress = 0xd01c;
			fightBattleMonLevelAddress = 0xd022;
			fightBattleMonHPAddress = 0xd015;
			fightBattleMonStatsAddress = 0xd025;
			fightBattleMonTypesAddress = 0xd019;
			fightCriticalHitAddress = 0xD05E;
			fightAttackMissedAddress = 0xD05F;
//			fightEffectMissedAddress = 0xcbeb;
			fightBattleMonOriginalStatsAddress = 0xd18f;
			fightEnemyMonOriginalStatsAddress = 0xD8C8;
			fightCurEnemyMoveAddress = 0xccdd;
			fightCurMoveNumAddress = 0xCC2E;
			fightEnemyMonSpeciesAddress = 0xcfd8;
			fightEnemyMonIndex = 0xcfe8;
			fightEnemyMonMovesAddress = 0xcfed;
			fightEnemyMonLevelAddress = 0xcff3;
			fightEnemyMonHPAddress = 0xcfe6;
			fightEnemyMonStatsAddress = 0xcff6;
			fightEnemyMonTypesAddress = 0xcfea;
			fightEnemyDVsAddress = 0xcff1;
			fightCurDamageAddress = 0xD0D7;
			thrashNumTurnsAddress = 0xd06a;
			numPartyMonAddress = 0xd163;
//
			fightDetermineAttackOrder = 0x3c2a9;
			fightDetermineAttackOrderPlayerFirst = 0x3c37d;
			fightDetermineAttackOrderEnemyFirst = 0x3c33d;
			fightAfterAIChooseMove = 0x3c2a9;
//			fightEndTurnResetFlags = 0x3c695;
			fightBattleCommand0a = new int[] {0x3d705, 0x3e782}; // after MoveHitTest (player, enemy)
//			fightDamageCalc = 0x35753;
//			fightDamageVariation = 0x34e4d;
			fightEndTurnAddresses = new int[] {printLetterDelayJoypadAddress, 0x3c364, 0x3c380, 0x3c34e, 0x3c3a4}; // after both calls of ExecutePlayerMove; after both calls of ExecuteEnemyMove
//			fightAIMoveCheck = new int[] {printLetterDelayJoypadAddress, 0x3c349, 0x3c39f};
			fightAIMoveCheck = new int[] {printLetterDelayJoypadAddress/*, 0x3e6bc*/, 0x3a808};
			fightAIExecuteMove = 0x3e6bc;

			listMoveNamesAddress = 0xb0000;
			listMovesAddress = 0x38000;
			listTypeNamesAddress = 0x27dae;
			listTypeMatchupAddress = 0x3e474;
		}
	}