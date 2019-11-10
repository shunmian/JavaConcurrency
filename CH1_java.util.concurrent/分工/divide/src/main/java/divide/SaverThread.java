package divide;

import java.io.IOException;

class SaverThread extends Thread {
  private final Data data;

 SaverThread(String name, Data data) {
   super(name);
   this.data = data;
 }

  public void run() {
    try {
      while (true) {
        this.data.save();
        Thread.sleep(1000);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
}