package es.aguasnegras.tdd;

import es.aguasnegras.tdd.calculator.lexer.ExpressionValidator;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

/**
 * Created by aventura on 10/02/14.
 */
public class ExpressionValidatorTests {

    private ExpressionValidator expressionValidator;

    @Before
    public void setUp() {
        expressionValidator = new ExpressionValidator();
    }

    @Test
    public void validateBinaryExpression() {
        String[] operators = "+ - * /".split(" ");
        for (String operator : operators) {
            boolean result = expressionValidator.isValid("2  " + operator + "  2");
            assertTrue("Could not validate operator " + operator, result);
        }
    }

    @Test
    public void validateWrongBinaryExpression() {
        assertFalse(expressionValidator.isValid("2a7"));
    }

    @Test
    public void validateBinaryExpressionWithSpaces() {
        assertTrue(expressionValidator.isValid("23     +  462"));
    }

    @Test
    public void validateBinaryExpressionWithoutSpaces() {
        assertFalse(expressionValidator.isValid("23+462"));
    }

    @Test
    public void validateVariousDigitsBinaryExpression() {
        assertTrue(expressionValidator.isValid("23 + 462"));
    }

    @Test
    public void validateTernaryExpression() {
        assertTrue(expressionValidator.isValid("2 + 3 - 7"));
    }

    @Test
    public void validateExpressionWithInvalidSubexpression() {
        assertFalse(expressionValidator.isValid("2 + 7 - 3 a 2 b"));
    }

    @Test
    public void validateTernaryExpressionWithoutNumbers() {
        assertFalse(expressionValidator.isValid("+ + 3"));
    }

    @Test
    public void validateBinaryExpressionStartingWithNegativeNumber() {
        assertTrue(expressionValidator.isValid("-7 + 3"));
    }

    @Test
    public void validateBinaryExpressionEndingWithNegativeNumber() {
        assertTrue(expressionValidator.isValid("18 + -8"));
    }

    @Test
    public void validateComplexExpression() {
        assertTrue(expressionValidator.isValid("-7 - -1 * 2 / 3 + -5"));
    }
}
