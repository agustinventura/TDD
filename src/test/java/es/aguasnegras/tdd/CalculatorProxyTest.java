package es.aguasnegras.tdd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by case on 4/02/14.
 */
public class CalculatorProxyTest {

    private Calculator calculator;
    private CalculatorProxy calculatorProxy;
    private CalculatorProxy limitsCalculatorProxy;

    @Before
    public void setUp() {
        calculator = new Calculator();
        calculatorProxy = new CalculatorProxy(new Validator(Integer.MIN_VALUE, Integer.MAX_VALUE), calculator);
        limitsCalculatorProxy = new CalculatorProxy(new Validator(-10, 10), calculator);
    }

    @Test
    public void add() {
        int result = calculatorProxy.binaryOperation(CalculatorProxy.CalculatorMethod.ADD, 2, 2);
        assertEquals(4, result);
    }

    @Test
    public void substract() {
        int result = calculatorProxy.binaryOperation(CalculatorProxy.CalculatorMethod.SUBSTRACT, 5, 2);
        assertEquals(3, result);
    }

    @Test
    public void addWithDifferentArguments() {
        int result = calculatorProxy.binaryOperation(CalculatorProxy.CalculatorMethod.ADD, 2, 5);
        assertEquals(7, result);
    }

    @Test
    public void substractReturningNegative() {
        int result = calculatorProxy.binaryOperation(CalculatorProxy.CalculatorMethod.SUBSTRACT, 3, 5);
        assertEquals(-2, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void argumentsExceedPositiveLimit() {
        limitsCalculatorProxy.binaryOperation(CalculatorProxy.CalculatorMethod.ADD, 30, 50);
        fail("This method is expected to throw IllegalArgumentException as both arguments are bigger " +
                "than the specified limits above: -10 and 10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void argumentsExceedNegativeLimit() {
        limitsCalculatorProxy.binaryOperation(CalculatorProxy.CalculatorMethod.ADD, -30, -50);
        fail("This method is expected to throw IllegalArgumentException as both arguments are smaller " +
                "than the specified limits above: -10 and 10");
    }
}