package performance;

import fibonacci.FastFibonacci;
import fibonacci.matrix.exponent.MatrixExponentCache;

public class MeasureFibonacci {
    /*
    testing of performance should always be coupled to a specific use pattern.
    i assumed throttling as our testing measure.
    amount of randomly selected fibNumbers in index range of
    0 - 20K per second.

    allowing us to use cache of log2(N) exponents.
    as well as cache of first K bits exponents.
        used 13 bits for 8192 entries cache.
    maximize cpu cache hits.
     */

    public static void main(String[] args) throws InterruptedException {
        int exponentCacheSize = 21;
        int kBitsCacheSize = 13;

        long cacheStart = System.currentTimeMillis();
        MatrixExponentCache mCache = new MatrixExponentCache(exponentCacheSize);
        FastFibonacci generator = new FastFibonacci(0, 1, mCache, kBitsCacheSize);
        System.out.println("Building cache took : " + (System.currentTimeMillis() - cacheStart) + " ms.");

        /*
        for this test setup.
        about 30s is  needed to get accurate/consistent results.
         */
        int testTimes = 15;
        long testSleepTime = 400;

        int iterationsPerTest = 10;
        int fibonacciRange = 20000;

        long totalDuration = 0;
        for (int t = 0; t < testTimes; t++) {
            long start = System.nanoTime();
            for (int i = 0; i < iterationsPerTest; i++) {
                for (int j = 0; j < fibonacciRange; j++) {
                    generator.getNthElement(j);
                }
            }
            long duration = System.nanoTime() - start;
            totalDuration += duration;
            Thread.sleep(testSleepTime);
        }
        System.out.println("total duration " + totalDuration / (1000 * 1000) + " ms.");
        System.out.println("average time per fibNumber : " + (totalDuration / (fibonacciRange * iterationsPerTest * testTimes)) + " ns.");
    }
}
