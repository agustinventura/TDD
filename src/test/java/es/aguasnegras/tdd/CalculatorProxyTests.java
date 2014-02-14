package es.aguasnegras.tdd;

import es.aguasnegras.tdd.calculator.Calculator;
import es.aguasnegras.tdd.calculator.CalculatorProxy;
import es.aguasnegras.tdd.calculator.SimpleCalculatorProxy;
import es.aguasnegras.tdd.calculator.validator.CalculatorValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by case on 4/02/14.
 */
public class CalculatorProxyTests {

    private CalculatorProxy calculatorProxy;
    private CalculatorProxy limitsCalculatorProxy;

    @Before
    public void setUp() {
        Calculator calculator = new Calculator();
        calculatorProxy = new SimpleCalculatorProxy(new CalculatorValidator(Integer.MIN_VALUE, Integer.MAX_VALUE), calculator);
        limitsCalculatorProxy = new SimpleCalculatorProxy(new CalculatorValidator(-10, 10), calculator);
    }

    @Test
    public void add() {
        int result = calculatorProxy.binaryOperation(SimpleCalculatorProxy.CalculatorMethod.ADD, 2, 2);
        assertEquals(4, result);
    }

    @Test
    public void substract() {
        int result = calculatorProxy.binaryOperation(SimpleCalculatorProxy.CalculatorMethod.SUBSTRACT, 5, 2);
        assertEquals(3, result);
    }

    @Test
    public void addWithDifferentArguments() {
        int result = calculatorProxy.binaryOperation(SimpleCalculatorProxy.CalculatorMethod.ADD, 2, 5);
        assertEquals(7, result);
    }

    @Test
    public void substractReturningNegative() {
        int result = calculatorProxy.binaryOperation(SimpleCalculatorProxy.CalculatorMethod.SUBSTRACT, 3, 5);
        assertEquals(-2, result);
    }

    @Test
    public void multiply() {
        assertEquals(10, calculatorProxy.binaryOperation(CalculatorProxy.CalculatorMethod.MULTIPLY, 2, 5));
    }

    @Test
    public void divide() {
        assertEquals(33, calculatorProxy.binaryOperation(CalculatorProxy.CalculatorMethod.DIVIDE, 99, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void argumentsExceedPositiveLimit() {
        limitsCalculatorProxy.binaryOperation(SimpleCalculatorProxy.CalculatorMethod.ADD, 30, 50);
        fail("This method is expected to throw IllegalArgumentException as both arguments are bigger " +
                "than the specified limits above: -10 and 10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void argumentsExceedNegativeLimit() {
        limitsCalculatorProxy.binaryOperation(SimpleCalculatorProxy.CalculatorMethod.ADD, -30, -50);
        fail("This method is expected to throw IllegalArgumentException as both arguments are smaller " +
                "than the specified limits above: -10 and 10");
    }

    @Test(expected = IllegalStateException.class)
    public void resultExceedsLimit() {
        limitsCalculatorProxy.binaryOperation(SimpleCalculatorProxy.CalculatorMethod.ADD, 10, 10);
        fail("This method is expected to throw IllegalStateException as result is bigger than the specified limit: 10");
    }
}
