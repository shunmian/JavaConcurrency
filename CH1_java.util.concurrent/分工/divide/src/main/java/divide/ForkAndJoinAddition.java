package divide;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkAndJoinAddition extends RecursiveTask<Long> {

  final long THRESH_HOLD = 10000000000L;

  long start;
  long end;
  long sum;

  ForkAndJoinAddition(long start, long end) {
    this.start = start;
    this.end = end;
    this.sum = 0;
  }

  @Override
  protected Long compute() {
   
   Boolean shouldCompute = end - start < this.THRESH_HOLD; 

   if(shouldCompute) {
      for (long i = this.start; i <= this.end; i++) {
        this.sum += i;
      }
   } else {
      long middle = (start + end) / 2;
      ForkAndJoinAddition left = new ForkAndJoinAddition(start, middle);
      ForkAndJoinAddition right = new ForkAndJoinAddition(middle + 1, end);

      left.fork();
      right.fork();

      long leftSum = left.join();
      long rightSum = right.join();

      this.sum = leftSum + rightSum;
   }

    return this.sum;
  }

  public static long run(long start, long end) {
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    ForkAndJoinAddition forkAndJoinAddition = new ForkAndJoinAddition(start, end);
    Future<Long> totalSum = forkJoinPool.submit(forkAndJoinAddition);

    try {
      long result = totalSum.get();
      System.out.println(start + " + ... + " + end + " = " + result);
      return result;
    } catch(Exception e) {
      return 0;
    }
  }
  public static void main(String[] args) {
    ForkAndJoinAddition.run(1,4);
  }
}