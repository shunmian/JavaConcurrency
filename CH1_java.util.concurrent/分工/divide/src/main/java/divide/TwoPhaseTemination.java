package divide;

class TwoPhaseTermination extends Thread {
  private boolean isShutdown;
  private long counter;

  TwoPhaseTermination() {
    this.isShutdown = false;
    this.counter = 0;
  }

  public void setIsShutdown(boolean isShutdown) {
    this.isShutdown = isShutdown;
  }

  public boolean getIsShutDown() {
    return this.isShutdown;
  }

  public void run() {
    try {
      while (!isShutdown) {
        doWork();
      }
    } catch (Exception e) {

    } finally {
      this.doClean();
    }
  }

  void doWork() {
    System.out.println(Thread.currentThread().getName() + " do work: " + counter);
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    counter++;
  }

  void doClean() {
    System.out.println("cleaning during shutdown");

  }

  public static void main(String[] args) {
    try {
      TwoPhaseTermination tpt = new TwoPhaseTermination();
      tpt.start();
      Thread.sleep(10000);
      tpt.setIsShutdown(true);
      System.out.println("ask Shutdown thread to shutdown");
      tpt.join();
      System.out.println("Shutdown thread finished");
    } catch (Exception e) {


    }
  }
}