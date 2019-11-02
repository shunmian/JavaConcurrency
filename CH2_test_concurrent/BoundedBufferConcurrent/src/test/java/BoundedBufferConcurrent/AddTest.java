package BoundedBufferConcurrent;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AddTest { 

  @Test
  public void testA() {
    int sum = Add.add(1, 2);
    assertEquals(3, sum);
  }
}