package divide;

import java.io.IOException;
import java.util.Random;

class ChangerThread extends Thread {
  private final Data data;
  private final Random random = new Random();

 ChangerThread(String name, Data data) {
   super(name);
   this.data = data;
 }

  public void run() {
    try {
      for (int i = 0; true; i++) {
        this.data.change("No: " + i);
        Thread.sleep(random.nextInt(1000)); //执行其他操作
        this.data.save();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
}