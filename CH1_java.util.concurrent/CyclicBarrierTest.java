import java.util.concurrent.CyclicBarrier;

class CyclicBarrierTest {
  CyclicBarrier cb;

  CyclicBarrierTest() {
    this.cb = new CyclicBarrier(5);
  }

  class MyRunnable implements Runnable {
    int i;
    String name;
    MyRunnable(int i) {
      this.i = i;
      this.name = Integer.toString(i);
    }
    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + " Start");
      try {

        System.out.println(Thread.currentThread().getName() + ": i " + i);
        System.out.println(Thread.currentThread().getName() + ": 1======================");
        Thread.sleep(1000 * i);
        System.out.println(Thread.currentThread().getName() + " Process finished, wait others" );
        cb.await();
        System.out.println(Thread.currentThread().getName() + ": 2======================");
        Thread.sleep(1000 * i);
        // cb.await();
        if(i == 0) {
          // Thread.sleep(1000* 5);

          System.out.println(Thread.currentThread().getName() + "about to 2 wait" );
          cb.wait();
        } else {
          System.out.println(Thread.currentThread().getName() + " Process finished, wait others" );
          cb.await();
        }
      } catch (Exception e) {
        System.out.println("error" + e);
      }
      System.out.println(Thread.currentThread().getName() + " All finished");
    }
  }

  void process() {
    for (int i = 0; i < 5; i++) {
      MyRunnable mr = new MyRunnable(i);
      Thread t = new Thread(mr);
      t.start();
    }
  }

  public static void main(String[] args) {
    CyclicBarrierTest cbt = new CyclicBarrierTest();
    cbt.process();
  }
}