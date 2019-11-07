package divide;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class NormalAdditionTest {

  @Test
  public void shouldReturn10WhenStartIs1AndEndIs4() {
    long result = NormalAddition.run(1, 4);
    assertEquals(result, 10);
  }

  
}