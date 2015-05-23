package mrwint.gbtasgen.rom.pokemon;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.util.pokemon.map.Map.MapFactory;

public abstract class PokemonRomInfo extends RomInfo {
	public abstract Move getWalkStep(int dir, boolean check, boolean skipStandStillTest);

	@Override
	public int getRngState(Gb gb) {
		return (gb.readMemory(rngAddress) << 22) + (gb.readMemory(rngAddress+1) << 14) + gb.getDivState();
	}

	public static final int CRYSTAL = 0;
	public static final int GOLD = 1;
	public static final int SILVER = 2;

	public static final int RED = 3;
	public static final int BLUE = 4;
	public static final int YELLOW = 5;

	public static final int BLUE_J = 6;

	public int type;
	public MapFactory mapFactory;

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
	// Gen 1 only
	public int mapHeaderBanksAddress;
	public int mapHeaderPointersAddress;
	public int trainerHeaderPointerAddress;
	public int blockTilesPointerAddress; // bank:address
	public int grassTileAddress;
	public int collisionDataAddress;
	public int missableObjectListAddress;
	public int missableObjectFlagsAddress;
	public int tilePairCollisionsLandAddress;
	public int tilePairCollisionsWaterAddress;
	public int curTilesetAddress;

	// options
	public int optionsAddress;
	public int optionsTextSpeedMask;

	// stats
	public int afterDVGenerationAddress;
	public int afterTrainerIDGenerationAddress;
	public int trainerIDAddress;

	//walking
  public int overworldJoypadCallAddress;
  public int doPlayerMovementFuncAddress;
	public int doPlayerMovementFuncEndAddress;
	public int playerMovingIndicatorAddress;
	public int playerDirectionAddress;
	public int movementAnimationAddress;
	// Gen 1 only
	public int doTurnPreEncounterCheckAddress;
	public int doTurnPostEncounterCheckAddress;
	public int doWalkPreEncounterCheckAddress;
	public int doWalkPostEncounterCheckAddress;
	public int walkSuccessAddress;
	public int walkFailAddress;

	public int curPositionXAddress;
	public int curPositionYAddress;
	public int curPositionOffset;

	public int encounterCheckMainFuncAddress;
	public int encounterCheckMainFuncNoEncounterAddress;
	public int encounterCheckMainFuncEncounterAddress;
	public int encounterMonLevelAddress;
	public int encounterMonSpeciesAddress;
	public int catchSuccessAddress;
	public int catchFailureAddress;
	public int[] encounterPreCheckAddresses;
	public int[] encounterPostCheckAddresses;

	// interaction
	public int owPlayerInputCheckAAddress;
	public int owPlayerInputNoActionAddress;
	public int owPlayerInputActionAddress;
	// Gen 1
	public int owInteractionTargetAddress;
	public int owInteractionSuccessfulAddress;
	public int owLoopAddress;

	// battle
	public int fightBattleMonLevelAddress;
	public int fightBattleMonMovesAddress;
	public int fightBattleMonStatsAddress;
	public int fightBattleMonOriginalStatsAddress;
	public int fightBattleMonTypesAddress;
	public int fightBattleMonHPAddress;
	// Gen 1
	public int fightBattleMonIndex;
	public int thrashNumTurnsAddress;
	public int numPartyMonAddress;

	public int fightEnemyMonSpeciesAddress;
	public int fightEnemyMonLevelAddress;
	public int fightEnemyMonMovesAddress;
	public int fightEnemyMonStatsAddress;
	public int fightEnemyMonOriginalStatsAddress;
	public int fightEnemyMonTypesAddress;
	public int fightEnemyMonHPAddress;
	public int fightEnemyDVsAddress;
	// Gen 1
	public int fightEnemyMonIndex;

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
	public int[] fightBattleCommand0a;
	public int fightDamageCalc;
	public int fightDamageVariation;
	public int[] fightEndTurnAddresses;
	// Gen 1
	public int[] fightAIMoveCheck;
	public int fightAIExecuteMove;


	// lists
	public int listMoveNamesAddress;
	public int listMovesAddress;
	public int listMovesEntryLength;
	public int listTypeNamesAddress;
	public int listTypeMatchupAddress;

	public int rngAddress;
}
