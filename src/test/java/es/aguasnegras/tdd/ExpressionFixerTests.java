package es.aguasnegras.tdd;

import es.aguasnegras.tdd.calculator.lexer.ExpressionFixer;
import es.aguasnegras.tdd.calculator.lexer.MathRegex;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aventura on 18/02/14.
 */
public class ExpressionFixerTests {

    @Test
    public void splitExpressionWithOperatorAtTheEnd() {
        ExpressionFixer expressionFixer = new ExpressionFixer(new MathRegex());
        List<StringBuilder> expressions = new ArrayList<>();
        expressions.add(new StringBuilder("2 +"));
        List<String> fixedExpressions = expressionFixer.fixExpressions(expressions);
        assertEquals(2, fixedExpressions.size());
        assertTrue(fixedExpressions.contains("2"));
        assertTrue(fixedExpressions.contains("+"));
    }
}
