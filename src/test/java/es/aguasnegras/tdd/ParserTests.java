package es.aguasnegras.tdd;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by aventura on 5/02/14.
 */
public class ParserTests {

    private MathParser mathParser;

    @Before
    public void setUp() {
        mathParser = new MathParser();
    }

    @Test
    public void getTokens() {
        List<MathToken> tokens = mathParser.getTokens("2 + 2");
        assertEquals(3, tokens.size());
        assertEquals("2", tokens.get(0).getValue());
        assertEquals("+", tokens.get(1).getValue());
        assertEquals("2", tokens.get(2).getValue());
    }

    @Test
    public void validateBinaryExpression() {
        String[] operators = "+ - * /".split(" ");
        for (String operator: operators) {
            boolean result = mathParser.isValid("2  " + operator + "  2");
            assertTrue("Could not validate operator " + operator, result);
        }
    }

    @Test
    public void validateBinaryExpressionWithSpaces() {
        boolean result = mathParser.isValid("23     +  462");
        assertTrue(result);
    }

    @Test
    public void validateBinaryExpressionWithoutSpaces() {
        boolean result = mathParser.isValid("23+462");
        assertTrue(result);
    }

    @Test
    public void validateVariousDigitsBinaryExpression() {
        boolean result = mathParser.isValid("23 + 462");
        assertTrue(result);
    }
}
