package divide;

public class ThreadPerMessage {
  private Worker worker;

  ThreadPerMessage() {
    this.worker = new Worker();
  }

  public void handle(int count, String message) {
   new Thread() {
     public void run() {
       worker.handle(count, message);
     }
   }.start(); 
  }

  public static void main(String[] args) {
    ThreadPerMessage threadPerMessage = new ThreadPerMessage();
    threadPerMessage.handle(10, "A");
    threadPerMessage.handle(20, "B");
    threadPerMessage.handle(30, "C");
    System.out.println("Finished");
  }
}

class Worker {
  public void handle(int count, String message) {
    String threadName = Thread.currentThread().getName();
    System.out.println(threadName +  "        handle(" + count + ", " + message + ") BEGIN");
    for (int i = 0; i < count; i++) {
      System.out.println(threadName + ": " + i + ": " + message);
      this.slowly();
    }
    System.out.println(threadName + "        handle(" + count + ", " + message + ") END");
  }
  private void slowly() {
    try {
        Thread.sleep(100);
    } catch (InterruptedException e) {
    }
  }
}