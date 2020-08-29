package fibonacci;

import java.math.BigInteger;

public abstract class FibonacciGenerator {
    protected BigInteger firstElement;
    protected BigInteger secondElement;

    public FibonacciGenerator(BigInteger firstElement, BigInteger secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public abstract BigInteger getNthElement(int index);
}
