package es.aguasnegras.tdd;

import es.aguasnegras.tdd.Calculator;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        int result = calculator.add(2,5);
        assertEquals(7, result);
    }

    @Test
    public void substract() {
        int result = calculator.substract(5,3);
        assertEquals(result, 2);
    }

    @Test
    public void substractNegative() {
        int result = calculator.substract(3,5);
        assertEquals(result, -2);
    }
}