package divide;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

public class ForkAndJoinAdditionTest {

  @Test
  public void shouldReturn10WhenStartIs1AndEndIs4() {
    long result = ForkAndJoinAddition.run(1, 4);
    assertEquals(result, 10);
  }

  
}