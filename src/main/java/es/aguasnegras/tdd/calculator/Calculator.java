package es.aguasnegras.tdd.calculator;

/**
 * Created by aventura on 3/02/14.
 */
public class Calculator {


    public Calculator() {
    }

    public int add(int augend, int addend) {
        return augend + addend;
    }

    public int substract(int minuend, int substraend) {
        return minuend - substraend;
    }

    public int multiply(int multiplicand, int multiplier) {
        return multiplicand * multiplier;
    }

    public int divide(int dividend, int divisor) {
        return dividend / divisor;
    }
}
