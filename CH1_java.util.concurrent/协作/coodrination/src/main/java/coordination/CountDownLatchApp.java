package coordination;

import java.util.concurrent.CountDownLatch;

class CountDownLatchApp {
  CountDownLatchApp(){
  }

  public static void main(String[] args) throws InterruptedException {

    int totalThreads = 3;
    CountDownLatch countDownLatch = new CountDownLatch(totalThreads);

    long start = System.currentTimeMillis();
    for (int i = 0; i < totalThreads; i++) {
      Thread thread = new Thread() {
        public void run() {
          try {
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(500);
            countDownLatch.countDown();
          } catch (Exception e) {

          }
        }
      };
      thread.start();
    }
    countDownLatch.await();
    long end = System.currentTimeMillis();
    System.out.println("total takes: " + (end - start)); 
  }
}