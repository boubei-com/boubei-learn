package jinhe.lt.reflect.memoization;

/**
 * <p>
 * PiBinaryDigitsCalculator.java
 * </p>
 * 
 * @author Jon.King 2006-8-13
 * 
 */
public class PiBinaryDigitsCalculator implements BinaryDigitsCalculator {

    public byte calculateBinaryDigit(final int n) {
        return runBBPAlgorithm(n);
    }

    private byte runBBPAlgorithm(final int n) {       
        // Lengthy routine goes here ...
        return (byte) n;
    }

}
