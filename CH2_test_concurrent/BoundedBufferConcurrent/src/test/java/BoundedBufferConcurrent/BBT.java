package BoundedBufferConcurrent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class BBT { 

  int LOCKUP_DETECT_TIMEOUT = 5000; //in ms

  private static final ExecutorService pool = Executors.newCachedThreadPool();
  private final AtomicInteger putSum = new AtomicInteger(0);
  private final AtomicInteger takeSum = new AtomicInteger(0);
  private final CyclicBarrier barrier;
  private final BoundedBuffer<Integer> bb;
  private final int nTrials, nParis;

  // @Test
  // public static void main(String[] args) {
  //   new BBT(10, 10, 10000).test();
  //   pool.shutdown();
  // }


  BBT(int capacity, int npairs, int ntrials) {
    this.bb = new BoundedBuffer<Integer>(capacity);
    this.nTrials = ntrials;
    this.nParis = npairs;
    this.barrier = new CyclicBarrier(npairs * 2 + 1);
  }

  public void test() {
    try {
      for (int i = 0; i < nParis; i++) {
        pool.execute(new Producer());
        pool.execute(new Consumer());
      }
      barrier.await();
      barrier.await();
      System.out.println("putSum" + putSum.get());
      System.out.println("takeSum" + takeSum.get());
      assertEquals(putSum.get(), takeSum.get());
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      pool.shutdown();
    }
  }

  static int xorShift(int y) {
    y ^= (y << 6);
    y ^= (y >>> 21);
    y ^= (y << 7);
    return y;
  }

  class Producer implements Runnable {
    public void run() {
      try {
        int seed = (this.hashCode() ^ (int)System.nanoTime());
        int sum = 0;
        barrier.wait();
        for (int i = nTrials; i > 0; --i) {
          bb.put(seed);
          sum += seed;
          seed = BBT.xorShift(seed);
        }
        putSum.getAndAdd(sum);
        barrier.await();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } 
  }

  class Consumer implements Runnable {
    public void run() {
      try {
        int sum = 0;
        barrier.wait();
        for (int i = nTrials; i > 0; --i) {
          Integer item = bb.take();
          sum = sum + item;
        }
        takeSum.getAndAdd(sum);
        barrier.await();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } 
  }

}
