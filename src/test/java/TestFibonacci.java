import fibonacci.FastFibonacci;
import fibonacci.FibonacciGenerator;
import fibonacci.matrix.exponent.MatrixExponentCache;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFibonacci {
    private static MatrixExponentCache mCache;

    @BeforeAll
    static void setup() {
        mCache = new MatrixExponentCache(22);
    }

    static void testAllSetups(BigInteger firstElement, BigInteger secondElement, int testSize, boolean printing) {
        FastFibonacci generator = new FastFibonacci(firstElement, secondElement);
        FastFibonacci generatorWithExponentCache = new FastFibonacci(firstElement, secondElement, mCache, 0);
        FastFibonacci generatorWithExponentCacheAndKCache = new FastFibonacci(firstElement, secondElement, mCache, 4);

        testSetup(generator, "regular", firstElement, secondElement, testSize, printing);
        testSetup(generatorWithExponentCache, "with exponentCache", firstElement, secondElement, testSize, printing);
        testSetup(generatorWithExponentCacheAndKCache, "with exponentCache and kCache", firstElement, secondElement, testSize, printing);
    }

    static void testSetup(FibonacciGenerator generator, String name, BigInteger firstElement, BigInteger secondElement, int testSize, boolean printing) {
        SimpleFibonacci testGenerator = new SimpleFibonacci(firstElement, secondElement);
        BigInteger[] expected = new BigInteger[testSize];
        BigInteger[] results = new BigInteger[testSize];
        for (int i = 0; i < testSize; i++) {
            BigInteger fibNumber = generator.getNthElement(i);
            expected[i] = testGenerator.getNthElement(i);
            results[i] = fibNumber;
            if (printing) {
                System.out.println(fibNumber);
            }
        }
        assertArrayEquals(expected, results, name);
    }

    @Test
    void regularFib() {
        testAllSetups(BigInteger.ZERO, BigInteger.ONE, 100, true);
    }

    @Test
    void negative() {
        testAllSetups(BigInteger.ZERO, BigInteger.ONE.negate(), 100, true);
    }

    @Test
    void differentSequence() {
        testAllSetups(BigInteger.TWO, BigInteger.TEN, 100, true);
    }

    @Test
    void testSequential() {
        FastFibonacci generator = new FastFibonacci(0, 1, mCache, 11);
        int size = 20000;
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < size; i++) {
            BigInteger fibNumber = generator.getNthElement(i);
            sum = sum.add(fibNumber);
        }
        assertEquals(1, 1);
    }
}
