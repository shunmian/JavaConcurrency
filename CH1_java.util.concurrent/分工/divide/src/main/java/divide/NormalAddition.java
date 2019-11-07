package divide;

public class NormalAddition {
  long start;
  long end;
  long sum;
  NormalAddition(long start, long end) {
    this.start = start;
    this.end = end;
    this.sum = 0;
  }

  long compute() {
    for(long i = start; i <= end; i++) {
      this.sum += i;
    }
    return sum;
  }

  static long run(long start, long end) {
    NormalAddition normalAddition = new NormalAddition(start, end);
    long result = normalAddition.compute();
    System.out.println(start + " + ... + " + end + " = " + result);
    return result;
  }
}