package fibonacci.matrix.exponent;

import fibonacci.FibMatrix;

public interface MatrixExponentCacheI {
    /*
    @input integer n from 0 inclusive
    @output
    the fibonacci initial matrix:
        [1 1
         1 0]
     to the power of 2^(n+1)
     */
    FibMatrix getNthMatrix(int n);
}
