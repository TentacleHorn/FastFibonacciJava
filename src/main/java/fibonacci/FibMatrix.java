package fibonacci;

import java.math.BigInteger;

/*
the fibonacci firstMatrix is
[1 1
 1 0]
due to us only needing exponents of this initial matrix
we can neglect to save or calculate the following:
    bottom left -> always equals topRight
    bottom right -> always equals topLeft - topRight
 */
public class FibMatrix {
    BigInteger topLeft;
    BigInteger topRight;

    FibMatrix(BigInteger topLeft, BigInteger topRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
    }

    public static FibMatrix firstMatrix() {
        return new FibMatrix(BigInteger.ONE, BigInteger.ONE);
    }



    public FibMatrix mult(FibMatrix m) {
        return new FibMatrix(topLeft.multiply(m.topLeft).add(topRight.multiply(m.topRight)),
                topLeft.multiply(m.topRight).add(topRight.multiply(m.getBottomRight())));
    }

    BigInteger getTopLeft() {
        return topLeft;
    }

    BigInteger getTopRight() {
        return topRight;
    }

    BigInteger getBottomRight() {
        return topLeft.subtract(topRight);
    }
}
