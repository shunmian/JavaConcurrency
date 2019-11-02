package BoundedBufferConcurrent;
import java.util.concurrent.Semaphore;

class BoundedBuffer<E> {
  private final Semaphore availableItems, availableSpaces;
  private final E[] items;
  private int putPosition = 0, takePosition = 0;
  private int capacity;

  public BoundedBuffer(int capacity) {
    this.capacity = capacity;
    availableItems = new Semaphore(0);
    availableSpaces = new Semaphore(capacity);
    items = (E[])new Object[capacity];
  }

  public boolean isEmpty() {
    return availableItems.availablePermits() == 0;
  }

  public boolean isFull() {
    return availableSpaces.availablePermits() == 0;
  }

  public void put(E x) throws InterruptedException {
    availableSpaces.acquire();
    doInsert(x);
    availableItems.release();
  }

  public E take() throws InterruptedException {
    availableItems.acquire();
    E e = doExtract();
    availableSpaces.release();
    return e;
  }

  public void doInsert(E e) {
    this.items[this.putPosition] = e;
    this.putPosition = this.putPosition + 1 <= this.capacity ? this.putPosition + 1: 0;
  }

  public E doExtract() {
    E e = this.items[this.takePosition];
    this.items[this.takePosition] = null;
    this.takePosition = this.takePosition + 1 <= this.capacity ? this.takePosition + 1: 0;
    return e;
  }
}