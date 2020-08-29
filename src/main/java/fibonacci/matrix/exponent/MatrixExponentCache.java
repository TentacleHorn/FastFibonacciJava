package fibonacci.matrix.exponent;

import fibonacci.FibMatrix;
import fibonacci.FIbMatrixForCache;

import java.util.ArrayList;

public class MatrixExponentCache implements MatrixExponentCacheI {
    /*
    you could save a tiny bit by implementing the first 7
    matrices as long instead of BigInt and do dynamic casting during calculation if need be.
    in my tests this was too negligible.
     */
    private ArrayList<FIbMatrixForCache> matrixExponentCache;

    public MatrixExponentCache(int size) {
        buildCache(size);
    }

    private void buildCache(int cacheSize) {
        matrixExponentCache = new ArrayList<>();
        matrixExponentCache.add(new FIbMatrixForCache(FibMatrix.firstMatrix()));
        growCache(cacheSize);
    }

    private void growCache(int sizeGoal) {
        FibMatrix current = matrixExponentCache.get(getSize() - 1);
        for (int i = getSize(); i < sizeGoal; i++) {
            current = current.mult(current);
            matrixExponentCache.add(new FIbMatrixForCache(current));
        }
    }

    public int getSize() {
        return matrixExponentCache.size();
    }

    @Override
    public FibMatrix getNthMatrix(int n) {
        if (n >= getSize()) {
            growCache(n + 1);
        }
        return matrixExponentCache.get(n);
    }
}
