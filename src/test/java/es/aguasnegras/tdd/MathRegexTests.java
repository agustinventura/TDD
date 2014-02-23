package es.aguasnegras.tdd;

import es.aguasnegras.tdd.calculator.lexer.MathRegex;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

/**
 * Created by aventura on 10/02/14.
 */
public class MathRegexTests {

    private MathRegex mathRegex;

    @Before
    public void setUp() {
        mathRegex = new MathRegex();
    }

    @Test
    public void validateBinaryExpression() {
        String[] operators = "+ - * /".split(" ");
        for (String operator : operators) {
            boolean result = mathRegex.isValid("2  " + operator + "  2");
            assertTrue("Could not validate operator " + operator, result);
        }
    }

    @Test
    public void validateWrongBinaryExpression() {
        assertFalse(mathRegex.isValid("2a7"));
    }

    @Test
    public void validateBinaryExpressionWithSpaces() {
        assertTrue(mathRegex.isValid("23     +  462"));
    }

    @Test
    public void validateBinaryExpressionWithoutSpaces() {
        assertFalse(mathRegex.isValid("23+462"));
    }

    @Test
    public void validateVariousDigitsBinaryExpression() {
        assertTrue(mathRegex.isValid("23 + 462"));
    }

    @Test
    public void validateTernaryExpression() {
        assertTrue(mathRegex.isValid("2 + 3 - 7"));
    }

    @Test
    public void validateExpressionWithInvalidSubexpression() {
        assertFalse(mathRegex.isValid("2 + 7 - 3 a 2 b"));
    }

    @Test
    public void validateTernaryExpressionWithoutNumbers() {
        assertFalse(mathRegex.isValid("+ + 3"));
    }

    @Test
    public void validateBinaryExpressionStartingWithNegativeNumber() {
        assertTrue(mathRegex.isValid("-7 + 3"));
    }

    @Test
    public void validateBinaryExpressionEndingWithNegativeNumber() {
        assertTrue(mathRegex.isValid("18 + -8"));
    }

    @Test
    public void validateComplexExpression() {
        assertTrue(mathRegex.isValid("-7 - -1 * 2 / 3 + -5"));
    }
}
