package divide;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

public class ForkAndJoinPerformanceTest {
  final int MILLISECONDS_PER_SECOND = 1000;

  @Test
  public void shouldReturn10WhenStartIs1AndEndIs4() {
    long result = ForkAndJoinAddition.run(1, 4);
    assertEquals(result, 10);
  }

  void measureTimeForkAndJoin(long start, long end) {
    long startTime = System.currentTimeMillis();
    long result = ForkAndJoinAddition.run(start, end);
    long endTime = System.currentTimeMillis();
    float diff = (endTime - startTime) / (float)MILLISECONDS_PER_SECOND;
    System.out.println("ForkAndJoinAddion: " + start + " to " + end + " = " + result + ", use " + diff + " seconds");
  }

  @Test
  public void testMeasureTime() {
    measureTimeForkAndJoin(1, 400000000);
  }

  
}