package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.START;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflChangeOptionsSegment {
  public static SeqSegment fromOverworld() {
    EflUtil.assertEfl();

    return new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButton(START);
        seqEflScrollA(-2); // options

        seqEflButton(LEFT); // text speed
        seqEflButton(DOWN);
        seqEflButton(LEFT); // battle scene
        seqEflButton(DOWN);
        seqEflButton(LEFT); // battle style

        seqEflButton(B);
        seqEflButton(START);
      }
    };
  }

  public static SeqSegment fromTitleMenu() {
    EflUtil.assertEfl();

    return new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButton(DOWN);
        seqEflButton(A); // options

        seqEflButton(LEFT); // text speed
        seqEflButton(DOWN);
        seqEflButton(LEFT); // battle scene
        seqEflButton(DOWN);
        seqEflButton(LEFT); // battle style

        seqEflButton(B);
      }
    };
  }
}
