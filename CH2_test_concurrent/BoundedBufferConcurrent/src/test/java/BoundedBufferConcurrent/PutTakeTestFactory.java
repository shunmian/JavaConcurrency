package BoundedBufferConcurrent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class PutTakeTestFactory { 
  @Test
  public void testABC() {
    new PutTakeTest(10, 10, 10000).test();
    PutTakeTest.pool.shutdown();
  }
}
