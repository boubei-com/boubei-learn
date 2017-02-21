package jinhe.lt.reflect.jprofiler;

/**
 * <p>
 * FibonacciImpl.java
 * </p>
 * 
 * @author Jon.King 2006-10-12
 * 
 * 菲波那契(Fibonacci)数列的几种解法 
 */
public class FibonacciImpl implements Fibonacci{
 
    public long recursive(int index) {
        if (index <= 2) {
            return 1;
        } else {
            return this.recursive(index - 1) + recursive(index - 2);
        }
    }
 
    public long fibArray(int index) {
        long[] array = new long[index];
        if (index == 1) {
            array[0] = 1;
        }
        if (index >= 2) {
            array[0] = 1;
            array[1] = 1;
        }
        if (index > 2) {
            for (int i = 2; i < array.length; i++) {
                array[i] = array[i - 2] + array[i - 1];
            }
        }
        return array[index - 1];
    }
 
    public long fib(int index) {
        long sum = 1, x = 1;
        if (index > 2) {
            for (int i = 0; i < index - 2; i++) {
                sum += x;
                x = sum - x;
            }
        }
        return sum;
    }
}
