package es.aguasnegras.tdd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by aventura on 10/02/14.
 */
public class OperatorFactoryTests {

    @Test
    public void testMultiplyOperator() {
        MathOperator operator = OperatorFactory.create(new MathToken("*"));
        assertEquals(2, operator.getPrecedence());
    }

    @Test
    public void testAddOperator() {
        MathOperator operator = OperatorFactory.create(new MathToken("+"));
        assertEquals(0, operator.getPrecedence());
    }
}