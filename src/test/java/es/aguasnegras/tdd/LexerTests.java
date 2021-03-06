package es.aguasnegras.tdd;

import es.aguasnegras.tdd.calculator.lexer.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aventura on 10/02/14.
 */
public class LexerTests {

    private Lexer mathLexer;

    @Before
    public void setUp() {
        MathRegex mathRegex = new MathRegex();
        mathLexer = new MathLexer(mathRegex, new ExpressionFixer(mathRegex));
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

    @Test
    public void getExpressionWithOneParenthesis() {
        List<MathExpression> expressions = mathLexer.getExpressions("(2 + 2)");
        assertEquals(1, expressions.size());
        assertEquals("2 + 2", expressions.get(0).getExpression());
    }

    @Test
    public void getExpressionWithNestedParenthesis() {
        List<MathExpression> expressions = mathLexer.getExpressions("((2) + 2)");
        assertEquals(3, expressions.size());
        checkExpressionsContainsSubExpressions(expressions, "2", "+");
    }

    @Test
    public void getNestedExpressions() {
        List<MathExpression> expressions = mathLexer.getExpressions("(2 + 1) + 2");
        assertEquals(3, expressions.size());
        checkExpressionsContainsSubExpressions(expressions, "2 + 1", "+", "2");
    }

    @Test
    public void getExpressionsWithParenthesisAtTheEnd() {
        List<MathExpression> expressions = mathLexer.getExpressions("2 + (3 * 1)");
        assertEquals(3, expressions.size());
        checkExpressionsContainsSubExpressions(expressions, "2", "+", "3 * 1");
    }

    private void checkExpressionsContainsSubExpressions(List<MathExpression> expressions, String... subexpressions) {
        for (MathExpression expression : expressions) {
            assertTrue(Arrays.asList(subexpressions).contains(expression.toString()));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionOnUnclosedParenthesis() {
        List<MathExpression> expressions = mathLexer.getExpressions("(2 + 3 * 1");
    }

    @Test
    public void getExpressionWithTwoSubExpressions() {
        List<MathExpression> expressions = mathLexer.getExpressions("(2 + 2) * (3 + 1)");
        assertEquals(3, expressions.size());
        checkExpressionsContainsSubExpressions(expressions, "2 + 2", "*", "3 + 1");
    }

    @Test
    public void getSeveralParenthesisExpressionInOrder() {
        List<MathExpression> expressions = mathLexer.getExpressions("(2 + 2) * (3 + 1)");
        assertEquals("2 + 2", expressions.get(0).getExpression());
        assertEquals("*", expressions.get(1).getExpression());
        assertEquals("3 + 1", expressions.get(2).getExpression());
    }
}
