package coordination;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;

class ExchangerApp {

  public static void main(String[] args) throws InterruptedException {
    Exchanger exchanger = new Exchanger<ArrayList<Integer>>();
    Producer producer = new Producer(exchanger);
    Consumer consumer = new Consumer(exchanger);
    producer.start();
    consumer.start();
  }
}

class Producer extends Thread {
  Exchanger exchanger;
  ArrayList<Integer> list;

  Producer(Exchanger exchanger) {
    this.exchanger = exchanger;
    this.list = new ArrayList<Integer>();
  }

  public void run() {
    for (int i = 0; i < 3; i++) {
      this.list.add(i);
      this.list.add(i);
      this.list.add(i);
      try {
        this.exchanger.exchange(this.list);
        for (Integer temp : this.list) {
          System.out.println("From producer: " + temp);
        }
        this.list = new ArrayList<Integer>();
      } catch (Exception e) {

      }
    }
  }
}

class Consumer extends Thread {
  Exchanger exchanger;
  ArrayList<Integer> list;

  Consumer(Exchanger exchanger) {
    this.exchanger = exchanger;
    this.list = new ArrayList<Integer>();
  }

  public void run() {
    for (int i = 0; i < 3; i++) {
      int j = i * 10;
      this.list.add(j);
      this.list.add(j);
      this.list.add(j);
      try {
        this.exchanger.exchange(this.list);
        for (Integer temp : this.list) {
          System.out.println("From consumer: " + temp);
        }
        this.list = new ArrayList<Integer>();

      } catch (Exception e) {

      }
    }
  }
}