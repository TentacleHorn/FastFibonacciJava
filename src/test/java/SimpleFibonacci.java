import fibonacci.FibonacciGenerator;

import java.math.BigInteger;

public class SimpleFibonacci extends FibonacciGenerator {
    public SimpleFibonacci(BigInteger firstElement, BigInteger secondElement) {
        super(firstElement, secondElement);
    }

    @Override
    public BigInteger getNthElement(int index) {
        if (index < 2){
            return index == 0 ? firstElement : secondElement;
        }
        BigInteger previous = secondElement;
        BigInteger current = firstElement.add(secondElement);
        for (int i = 0; i < index - 2; i++) {
            BigInteger newCurrent = current.add(previous);
            previous = current;
            current = newCurrent;
        }
        return current;
    }
}
