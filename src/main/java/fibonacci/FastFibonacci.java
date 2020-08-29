package fibonacci;

import fibonacci.matrix.exponent.MatrixExponentCache;
import fibonacci.matrix.exponent.MatrixExponentCacheI;

import java.math.BigInteger;

public class FastFibonacci extends FibonacciGenerator {
    private MatrixExponentCacheI matrixExponentCache;
    private FibMatrix[] kBitsCache;

    public FastFibonacci() {
        this(new MatrixExponentCache(0), 0);
    }

    public FastFibonacci(BigInteger firstElement, BigInteger secondElement) {
        this(firstElement, secondElement, new MatrixExponentCache(0), 0);
    }

    public FastFibonacci(MatrixExponentCacheI mCache) {
        this(mCache, 0);
    }

    public FastFibonacci(MatrixExponentCacheI mCache, int kBitsCacheSize) {
        this(BigInteger.ZERO, BigInteger.ONE, mCache, kBitsCacheSize);

    }

    public FastFibonacci(long firstElement, long secondElement, MatrixExponentCacheI mCache, int kBitsCacheSize) {
        this(BigInteger.valueOf(firstElement), BigInteger.valueOf(secondElement), mCache, kBitsCacheSize);
    }

    public FastFibonacci(BigInteger firstElement, BigInteger secondElement, MatrixExponentCacheI mCache, int kBitsCacheSize) {
        super(firstElement, secondElement);
        matrixExponentCache = mCache;
        initializeKBitsCache(kBitsCacheSize);
    }

    private void initializeKBitsCache(int bitLength) {
        if (bitLength <= 0) {
            kBitsCache = new FibMatrix[0];
            return;
        }
        int size = (int) Math.pow(2, bitLength);
        kBitsCache = new FibMatrix[size];

        FibMatrix current = FibMatrix.firstMatrix();
        for (int i = 0; i < size; i++) {
            kBitsCache[i] = current;
            current = current.mult(FibMatrix.firstMatrix());
        }
    }

    /*
    defined for values above 0 inclusive
     */
    @Override
    public BigInteger getNthElement(int index) {
        if (index < 2) {
            return index == 0 ? firstElement : secondElement;
        }
        if (index == 2) {
            return firstElement.add(secondElement);
        }
        int seqIndex = index - 3;
        FibMatrix matrix = seqIndex < kBitsCache.length ? kBitsCache[seqIndex] : FibMatrix.firstMatrix();
        String bin = Integer.toBinaryString(seqIndex < kBitsCache.length ? 0: seqIndex);
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(bin.length() - 1 - i) == '1') {
                matrix = matrix.mult(matrixExponentCache.getNthMatrix(i));
            }
        }
        return (matrix.topLeft.add(matrix.topRight)).multiply(secondElement)
                .add(
                        (matrix.topRight.add(matrix.getBottomRight())).multiply(firstElement)
                );
        /*
        technically if firstElement is 0 and second element is 1:
            we could just :
                return matrix.topLeft.add((matrix.topRight));
            but i kept only the general implementation for simplicity.
         */
    }
}
