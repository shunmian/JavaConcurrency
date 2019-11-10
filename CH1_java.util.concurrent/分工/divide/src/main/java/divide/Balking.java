package divide;

public class Balking {

  public static void run() {
    System.out.println("Test Balking");
    Data data = new Data("data.txt", "empty");
    new ChangerThread("ChangerThread", data).start();
    new SaverThread("SaverThread", data).start();
  }

  public static void main(String[] args) {
    Balking.run();
  }
}