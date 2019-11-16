package coordination;

import java.util.concurrent.CyclicBarrier;

class CyclicBarrierApp {

  public static void main(String[] args) throws InterruptedException {

    int totalThreads = 3;
    CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThreads);

    for (int i = 0; i < totalThreads; i++) {
      Thread thread = new Thread() {
        public void run() {
          try {
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(500);
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " end");
          } catch (Exception e) {

          }
        }
      };
      thread.start();
    }
  }
}