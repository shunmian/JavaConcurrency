package BoundedBufferConcurrent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class BoundedBufferTest { 

  int LOCKUP_DETECT_TIMEOUT = 5000; //in ms

  @Test
  public void testIsEmptyWhenConstructed() {
    BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<Integer>(10);
    assertTrue("Should be empty when constructed", boundedBuffer.isEmpty());
    assertFalse("Should be empty when constructed", boundedBuffer.isFull());
  }

  @Test
  public void testIsFullWhenConstructed() {
    BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<Integer>(10);
    try {
      for (int i = 0; i < 10; i++) {
        boundedBuffer.put(i);
      }
      assertTrue("Should be full when constructed", boundedBuffer.isFull());
      assertFalse("Should be full when constructed", boundedBuffer.isEmpty());
    } catch(Exception e) {
    }
  }

  @Test
  public void testTakeBlocksWhenEmpty() {
    final BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<Integer>(10);
    Thread taker = new  Thread() {
      public void run() {
        try {
          int unused = boundedBuffer.take();
          BoundedBufferTest.fail();
        } catch (InterruptedException suceess) {
        }
      }
    };

    try {
      taker.start();
      Thread.sleep(LOCKUP_DETECT_TIMEOUT);
      taker.interrupt();
      taker.join(LOCKUP_DETECT_TIMEOUT);
      assertFalse("taker thread should die", taker.isAlive());
    } catch (Exception unexpected) {
      BoundedBufferTest.fail();
    }
  }

  static void fail() {
    assertTrue("Should fail", false);
  }
}
