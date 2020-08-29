package fibonacci;

import java.math.BigInteger;

public class FIbMatrixForCache extends FibMatrix {
    /*
    simply save the bottomRight calculation in cache.
    still allows us to not calculate it when computing fib.
     */
    public FIbMatrixForCache(FibMatrix m) {
        this(m.topLeft, m.topRight);
    }

    public FIbMatrixForCache(BigInteger topLeft, BigInteger topRight) {
        super(topLeft, topRight);
        bottomRight = super.getBottomRight();
    }

    private BigInteger bottomRight;

    @Override
    public FibMatrix mult(FibMatrix m) {
        return super.mult(m);
    }

    @Override
    BigInteger getBottomRight() {
        return bottomRight;
    }
}
