package es.aguasnegras.tdd;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.beans.Expression;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Created by aventura on 5/02/14.
 */
public class ParserTests {

    private MathParser mathParser;
    private ExpressionValidator expressionValidator;
    private MathLexer mathLexer;

    @Before
    public void setUp() {
        mathParser = new MathParser();
        expressionValidator = new ExpressionValidator();
        mathLexer = new MathLexer(expressionValidator);
    }

    @Test
    public void getTokens() {
        List<MathToken> tokens = mathLexer.getTokens("2 + 2");
        assertEquals(3, tokens.size());
        assertEquals("2", tokens.get(0).getValue());
        assertEquals("+", tokens.get(1).getValue());
        assertEquals("2", tokens.get(2).getValue());
    }

    @Test
    public void validateBinaryExpression() {
        String[] operators = "+ - * /".split(" ");
        for (String operator: operators) {
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
        assertTrue(expressionValidator.isValid("23+462"));
    }
    @Test
    public void validateVariousDigitsBinaryExpression() {
        assertTrue(expressionValidator.isValid("23 + 462"));
    }

    @Test
    public void validateTernaryExpression() {
        assertTrue(expressionValidator.isValid("2+3-7"));
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
        assertTrue(expressionValidator.isValid("-7+3"));
    }

    @Test
    public void validateBinaryExpressionEndingWithNegativeNumber() {
        assertTrue(expressionValidator.isValid("18+-8"));
    }

    @Test
    public void validateComplexExpression() {
        assertTrue(expressionValidator.isValid("-7 - -1 * 2 / 3 + -5"));
    }

    @Test
    public void getComplexExpressionTokens() {
        List<MathToken> tokens = mathLexer.getTokens("-7 - -1 * 2 / 3 + -5");
        assertEquals(9, tokens.size());
        assertEquals("-7", tokens.get(0).getValue());
        assertEquals("-", tokens.get(1).getValue());
        assertEquals("-1", tokens.get(2).getValue());
        assertEquals("*", tokens.get(3).getValue());
        assertEquals("2", tokens.get(4).getValue());
        assertEquals("/", tokens.get(5).getValue());
        assertEquals("3", tokens.get(6).getValue());
        assertEquals("+", tokens.get(7).getValue());
        assertEquals("-5", tokens.get(8).getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWrongExpressionTokens() {
        List<MathToken> tokens = mathLexer.getTokens("2 - 1 ++ 3");
        fail("IllegalArgumentException expected here");
    }

    @Test
    public void getTokensWithSpaces() {
        List<MathToken> tokens = mathLexer.getTokens("5 -   88");
        assertEquals(3, tokens.size());
        assertEquals("5", tokens.get(0).getValue());
        assertEquals("-", tokens.get(1).getValue());
        assertEquals("88", tokens.get(2).getValue());
    }
}
