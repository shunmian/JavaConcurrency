import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.plaf.synth.SynthScrollBarUI;

class AtomicIntegerTest {

  // int count = 0;
  AtomicInteger count = new AtomicInteger(10);

  class MyRunnable implements Runnable {

    public void run() {
      for (int i = 0; i < 10000; i++)
        increment();
    }

    void increment() {
      // synchronized(this.getClass()) {
        // count = count + 1;
        count.getAndIncrement();
      // }
    }
  }

  void process() {
    int n = 2;
    Thread[] threads = new Thread[n];
    for (int i = 0; i < n; i++) {
      MyRunnable my = new MyRunnable();
      Thread t = new Thread(my);
      t.start();
      threads[i] = t;
    }
    try {
      for (Thread t: threads) {
        t.join();
      }
    } catch(Exception e) {
        System.out.println("error" + e);
    }
    System.out.println("Finished, totoal: " + count.get());
  }

  public static void main(String[] args) {
    AtomicIntegerTest ait = new AtomicIntegerTest();
    // for (int i = 0; i < 20; i++)
    ait.process();
  }
}
