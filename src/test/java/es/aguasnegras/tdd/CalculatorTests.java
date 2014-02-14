package es.aguasnegras.tdd;

import es.aguasnegras.tdd.calculator.Calculator;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by aventura on 3/02/14.
 */
public class CalculatorTests {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void addWithDifferentArguments() {
        int result = calculator.add(2, 5);
        assertEquals(7, result);
    }

    @Test
    public void substract() {
        int result = calculator.substract(5, 3);
        assertEquals(result, 2);
    }

    @Test
    public void substractNegative() {
        int result = calculator.substract(3, 5);
        assertEquals(result, -2);
    }

    @Test
    public void multiply() {
        assertEquals(10, calculator.multiply(2, 5));
    }

    @Test
    public void divide() {
        Assert.assertEquals(3, calculator.divide(15, 5));
    }
}