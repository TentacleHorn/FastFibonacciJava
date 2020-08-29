package performance.ignore.testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static performance.PerformanceUtils.ignore;

public class compareNumberTypes {
    private static int testSize = 1000000;
    private static long[] longData;
    private static BigInteger[] bigData; //xD
    private static Random rng = new Random();

    @BeforeAll
    static void setup() {
        longData = new long[testSize];
        bigData = new BigInteger[testSize];
        long valueRange = 1000000000L;
        for (int i = 0; i < testSize; i++) {
            long v = Math.abs(rng.nextLong()) % valueRange;
            longData[i] = v;
            bigData[i] = BigInteger.valueOf(v);
        }
    }

    @Test
    void longs() {
        long sum = 0;
        for (int i = 0; i < testSize / 2; i++) {
            sum += longData[i] * longData[testSize/2 + i];
        }
        ignore(sum);
    }

    @Test
    void bigs() {
        long sum = 0;
        for (int i = 0; i < testSize / 2; i++) {
            sum += (bigData[i].multiply(bigData[testSize/2 + i])).longValue();
        }
        ignore(sum);
    }
}
