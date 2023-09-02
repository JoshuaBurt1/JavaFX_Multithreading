package joshuaburt_sec005_ex03;

import javafx.concurrent.Task;

import java.math.BigInteger;

public class Factorial extends Task<BigInteger> { //A. This class only comes into effect when "Start" button pressed in Class AsynchronousAppController.java

    private final int n;
    public Factorial(int n) { //B. gets parameter n from FiboCalcTask(ival)
        this.n = n;
    }

    @Override
    protected BigInteger call() throws Exception {
        updateMessage("    Processing... ");
        BigInteger result = factorial(n); //C. variable result is set to a method fibonacci(n) ; n being ival from Class FiboRun.java
        updateMessage("    Done.  ");
        return result;
    }

    public BigInteger factorial(int number) { //D. this manipulates variable n to the next Fibonacci value
        BigInteger factorial = BigInteger.ONE;

        for (int i = number; i > 0; i--) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
        //This is slow compared to other method the same Fibonacci # calculation in Class FiboRun.java when bottom button "Next Fibonacci number"

    }
}
