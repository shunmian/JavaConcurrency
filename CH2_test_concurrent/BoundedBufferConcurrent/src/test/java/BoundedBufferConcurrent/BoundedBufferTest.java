package BoundedBufferConcurrent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BoundedBufferTest { 

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
}