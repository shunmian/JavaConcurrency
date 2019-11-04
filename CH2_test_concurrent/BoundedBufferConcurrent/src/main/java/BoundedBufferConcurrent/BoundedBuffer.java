package BoundedBufferConcurrent;
import java.util.concurrent.Semaphore;

import net.jcip.annotations.GuardedBy;

class BoundedBuffer<E> {
  private final Semaphore availableItems, availableSpaces;
  
  @GuardedBy("this") final E[] items;
  @GuardedBy("this") private final int capacity;
  @GuardedBy("this") private int putPosition = 0, takePosition = 0;

  public BoundedBuffer(int capacity) {
    availableItems = new Semaphore(0);
    availableSpaces = new Semaphore(capacity);
    items = (E[]) new Object[capacity];
    this.capacity = capacity;
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

  private synchronized void doInsert(E e) {
    this.items[this.putPosition] = e;
    this.putPosition = this.putPosition + 1 <= this.capacity ? this.putPosition + 1: 0;
  }

  private synchronized E doExtract() {
    E e = this.items[this.takePosition];
    this.items[this.takePosition] = null;
    this.takePosition = this.takePosition + 1 <= this.capacity ? this.takePosition + 1 : 0;
    return e;
  }
}