# FastFibonacciJava

This is my fast fibonacci generator.

implemented in Java using the "fast doubling" algorithm.

running the project:

  * sanity tests are in test.java.TestFibonacci
  
  * and a simple performance test is in test.java.performance.MeasureFibonacci
  
  
Footnotes:

 * the MatrixExponentCache implementation should be changed to store exponents in base X,
   than store x-1 options for each "bit".
   Example:
    * Base 2 (current version):
      * M^(2^0)
      * M^(2^1)
      * M^(2^2)
    * Base 3:
      * (M^(3^0))^1, (M^(3^0))^2
      * (M^(3^1))^1, (M^(3^1))^2
  
   <b>cache size :</b> log(base, inputRange) * (base-1)     
   https://www.wolframalpha.com/input/?i=plot+log%28y%2C+x%29+*+%28y+-+1%29%2C+10000%3C%3Dx%3C%3D1000000%2C+1%3Cy%3C20
   
   <b>cache population time :</b> exact growth as cache size
  
   <b>cache performance :</b> log(base, input)
   
   for base 5 for example you could get a linear speed gain 3x with space overhead of 2x.
   
   i decided that would be going overboard :)
  
