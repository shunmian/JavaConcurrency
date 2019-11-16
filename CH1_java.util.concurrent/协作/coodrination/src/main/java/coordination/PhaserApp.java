package coordination;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;

class PhaserApp {

  public static void main(String[] args) throws InterruptedException {

    int totalThreads = 3;
    int phases = 5;
    Phaser phaser = new Phaser(totalThreads) {
      @Override
      protected boolean onAdvance(int phase, int registeredParties) {
        System.out.println("==== phase: " + phase);
        return super.onAdvance(phase, registeredParties);
      }
    };

    for (int i = 0; i < totalThreads; i++) {
      Thread thread = new Thread() {
        public void run() {

          for (int i = 0; i < phases; i++) {
            try {
              System.out.println(Thread.currentThread().getName() + " start phase: " + phases);
              Thread.sleep(500);
            } catch (Exception e) {
  
            }
            phaser.arriveAndAwaitAdvance();
          }
        }
      };
      thread.start();
    }
  }
}