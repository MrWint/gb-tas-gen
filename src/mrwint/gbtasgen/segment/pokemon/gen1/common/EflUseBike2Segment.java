package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BICYCLE;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;

public class EflUseBike2Segment extends EflSelectItemSegment {

  public EflUseBike2Segment() {
    super(BICYCLE);
  }

  @Override
	public void execute() {
    super.execute();
		seq(new EflSkipTextsSegment(1)); // got on bike
	}
}
