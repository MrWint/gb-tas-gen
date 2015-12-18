package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BULBASAUR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHANSEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DODUO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EKANS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGCUTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FLAREON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWLITHE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM01;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KANGASKHAN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LIFT_KEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MR_MIME;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORANF;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORANM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ODDISH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PARAS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PARASECT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PC_MON_DEPOSIT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PINSIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POKE_FLUTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RHYHORN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SANDSHREW;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SILPH_SCOPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SS_TICKET;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAUROS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VENOMOTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VENONAT;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMoveNew;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflCatchSafariMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeMonBoxSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectPcMonOperationSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.sml2.Sandbox;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class SafariBlue extends SeqSegment {

	@Override
	public void execute() {
    seqEflButton(A); // continue game
    seqEflButton(START);
    seqEflButton(A);
    seqEflButton(START);
    seqEflButton(A);

    {
      seq(new EflWalkToSegment(13, 4)); // PC
      seq(new EflWalkToSegment(13, 3, false)); // PC
      {
        seqEflButton(A); // use PC
        seq(new EflSkipTextsSegment(1)); // turned on
        seqEflButton(A); // someone's PC
        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
        seqMetric(new OutputParty());
        seqMetric(new OutputBoxMons());
        seq(new EflDepositMonSegment(MR_MIME));
        seq(new EflDepositMonSegment(GROWLITHE));
        seq(new EflWithdrawMonSegment(SANDSHREW));
        seq(new EflChangeMonBoxSegment(1)); // box 2
        seqEflButton(B, MENU); // cancel
        seqMetric(new OutputItems());
        {
          seqEflButton(DOWN | A); // items
          seq(new EflSkipTextsSegment(2)); // accessed, item storage
          seq(new EflSelectPcMonOperationSegment(PC_MON_DEPOSIT));
          seqEflSkipInput(1);
          seq(new EflDepositItemSegment(SS_TICKET));
          seq(new EflDepositItemSegment(HM01));
          seq(new EflDepositItemSegment(POKE_FLUTE));
          seqEflButtonUnbounded(B, MENU); // cancel deposit
          seqEflButtonUnbounded(B, MENU); // cancel
        }
        seqEflButtonUnbounded(B, MENU); // cancel
      }
    }

    seqUnbounded(new EflWalkToSegment(4, 6)); // leave center
    seq(new EflWalkToSegment(4, 8, false)); // leave center

    save("sa0");
    load("sa0");

    seq(new EflUseBikeSegment().fromOverworld());
    seq(new EflWalkToSegment(23, 28, false)); // ledge

    seq(new EflWalkToSegment(18, 3)); // enter safari house
    seq(new EflWalkToSegment(3, 2)); // go pay
    seq(new EflSkipTextsSegment(4)); // welcome to safari
    seq(new EflSkipTextsSegment(1, true)); // yes, go on safari
    seq(new EflSkipTextsSegment(7)); // welcome to safari
    seqUnbounded(new EflUseBikeSegment().fromOverworld());

    seqUnbounded(new EflWalkToSegment(19, 23));
    seq(new EflEncounterSegment(PINSIR, RIGHT));

    save("tmp");
//    load("tmp");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(22, 23));
    seq(new EflEncounterSegment(RHYHORN, UP));
    save("tmp2");
//    load("tmp2");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(23, 21));
    seq(new EflEncounterSegment(EXEGGCUTE, UP));
    save("tmp3");
//    load("tmp3");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(26, 20));
    seq(new EflEncounterSegment(NIDORANF, UP));
    save("tmp4");
//    load("tmp4");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(26, 17));
    seq(new EflEncounterSegment(VENONAT, UP));
    save("tmp5");
//    load("tmp5");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(30, 11, false)); // east

    save("sa5");
    load("sa5");

    seqUnbounded(new EflWalkToSegment(13, 24));
    seq(new EflEncounterSegment(KANGASKHAN, RIGHT));
    save("tmp");
//    load("tmp");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(16, 24));
    seq(new EflEncounterSegment(PARAS, RIGHT));
    save("tmp2");
//    load("tmp2");
    seq(new EflCatchSafariMonSegment().withBufferSize(0).withExtraSkips(80));

    seqUnbounded(new EflWalkToSegment(19, 24));
    seq(new EflEncounterSegment(PARASECT, RIGHT));
    save("tmp3");
//    load("tmp3");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(9, 15));
    seq(new EflEncounterSegment(NIDORANM, UP));
    save("tmp4");
//    load("tmp4");
    seq(new EflCatchSafariMonSegment().withBufferSize(0).withExtraSkips(30));

    seqUnbounded(new EflWalkToSegment(9, 10));
    seq(new EflEncounterSegment(DODUO, UP));
    save("tmp5");
//    load("tmp5");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(10, 3));
    seq(new EflEncounterSegment(NIDORINA, LEFT));
    save("tmp6");
//    load("tmp6");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(-1, 5, false)); // north

    save("sa6");
    load("sa6");

    seqUnbounded(new EflWalkToSegment(24, 25));
    seq(new EflEncounterSegment(TAUROS, UP));
    save("tmp");
//    load("tmp");
    seq(new EflCatchSafariMonSegment().withBufferSize(0).withExtraSkips(10));

    seqUnbounded(new EflWalkToSegment(13, 27));
    seq(new EflEncounterSegment(CHANSEY, UP));
    save("tmp2");
//    load("tmp2");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(15, 12));
    seq(new EflEncounterSegment(VENOMOTH, UP));
    save("tmp3");
//    load("tmp3");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(27, 6));
    seq(new EflEncounterSegment(NIDORINO, RIGHT));
    save("tmp4");
//    load("tmp4");
    seq(new EflCatchSafariMonSegment().withBufferSize(0));

    seq(new EflWalkToSegment(3, 36, false)); // west

    save("sa7");
    load("sa7");

    seq(new EflWalkToSegment(19, 5)); // gold teeth
    seq(new EflWalkToSegment(19, 6)); // gold teeth
    seqMove(new EflOverworldInteract(4)); // pick up gold teeth
    seq(new EflTextSegment());
    seq(new EflWalkToSegment(3, 3)); // enter surf house
    seq(new EflWalkToSegment(3, 5)); // surf guy
    seq(new EflWalkToSegment(3, 4)); // surf guy
    seqMove(new EflOverworldInteract(1)); // surf guy
    seq(new EflSkipTextsSegment(8)); // get surf
    seq(new EflWalkToSegment(3, 8, false)); // leave surf house
    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
    seqEflSkipInput(2);
  }
}
