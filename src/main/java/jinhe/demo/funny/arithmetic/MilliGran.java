package jinhe.lt.funny.arithmetic;

/**
 * <p>
 * MilliGran.java
 * </p>
 * 测试System.currentTimeMillis() 以及 System.nanoTime()（纳秒） 的系统开销
 * 
 * @author Jon.King 2007-8-20
 */

public class MilliGran {
    // 取小数后一位
    private static String roundOneDecimal(double value) {
        int whole = (int) value;
        return whole + "." + (int) ((value - whole) * 10.0);
    }

    private static void testCurrentTimeMillis(int repeats, int sets){
        // loop until time rolls over
        long start = System.currentTimeMillis();
        long last;
        while ((last = System.currentTimeMillis()) == start);

        // loop for count of sets specified
        int[] diffs = new int[repeats];
        for (int i = 0; i < sets; i++) {
            double lsum = 0.0;
            double dsum = 0.0;
            for (int j = 0; j < repeats; j++) {
                int loop = 0;
                last = System.currentTimeMillis();
                long now;
                do {// count loops until result changes
                    loop++;
                    now = System.currentTimeMillis();
                } while (now == last);

                lsum += loop;
                int diff = (int) (now - last);
                dsum += diff;
                diffs[j] = diff;
            }
            // print results for set
            System.out.println("Set " + i + " results:");
            System.out.println(" Average time per call "
                            + roundOneDecimal(dsum / lsum * 1000000.0) + " nanoseconds");
            System.out.print(" Differences:");
            for (int j = 0; j < repeats; j++) {
                System.out.print(" " + diffs[j]);
            }
            System.out.println(" (ms)");
        }
    }
    
    static void testNanoTime(int repeats, int sets){
        // loop until time rolls over
        long start = System.nanoTime();
        long last;
        while ((last = System.nanoTime()) == start);

        // loop for count of sets specified
        int[] diffs = new int[repeats];
        for (int i = 0; i < sets; i++) {
            double lsum = 0.0;
            double dsum = 0.0;
            for (int j = 0; j < repeats; j++) {
                int loop = 0;
                last = System.nanoTime();
                long now;
                do {// count loops until result changes
                    loop++;
                    now = System.nanoTime();
                } while (now == last);

                lsum += loop;
                int diff = (int) (now - last);
                dsum += diff;
                diffs[j] = diff;
            }
            // print results for set
            System.out.println("Set " + i + " results:");
            System.out.println(" Average time per call "
                            + roundOneDecimal(dsum / lsum) + " nanoseconds");
            System.out.print(" Differences:");
            for (int j = 0; j < repeats; j++) {
                System.out.print(" " + diffs[j]);
            }
            System.out.println(" (ns)");
        }
    }

    public static void main(String[] argv) {
        testCurrentTimeMillis(10, 4);
        testNanoTime(100, 4);
    }
}
