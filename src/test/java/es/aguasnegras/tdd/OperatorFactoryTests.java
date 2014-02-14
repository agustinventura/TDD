package es.aguasnegras.tdd;

import es.aguasnegras.tdd.calculator.lexer.MathToken;
import es.aguasnegras.tdd.calculator.operators.MathOperator;
import es.aguasnegras.tdd.calculator.operators.OperatorFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by aventura on 10/02/14.
 */
public class OperatorFactoryTests {

    @Test
    public void testMultiplyOperator() {
        MathOperator operator = OperatorFactory.create(0, new MathToken("*"));
        assertEquals(2, operator.getPrecedence());
    }

    @Test
    public void testAddOperator() {
        MathOperator operator = OperatorFactory.create(0, new MathToken("+"));
        assertEquals(1, operator.getPrecedence());
    }
}