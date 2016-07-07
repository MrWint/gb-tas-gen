package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import static org.junit.Assert.*;

import org.junit.Test;

import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Wait;

public class WaitTest {

  @Test
  public void minShort() {
    Wait wait = new Wait(16);
    assertEquals(Wait.OFFSET_SHORT + 21, wait.getJumpAddress());
    assertEquals(0, wait.getInputMap().size());
  }
  @Test
  public void maxShort() {
    Wait wait = new Wait(100);
    assertEquals(Wait.OFFSET_SHORT, wait.getJumpAddress());
    assertEquals(0, wait.getInputMap().size());
  }
  @Test
  public void minLong() {
    Wait wait = new Wait(104);
    assertEquals(Wait.OFFSET_LONG + 6, wait.getJumpAddress());
    assertEquals(4, wait.getInputMap().size());
  }
  @Test
  public void maxLong() {
    Wait wait = new Wait(1051748);
    assertEquals(Wait.OFFSET_LONG, wait.getJumpAddress());
    assertEquals(4, wait.getInputMap().size());
  }
}
