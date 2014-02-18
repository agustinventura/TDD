package es.aguasnegras.tdd;

import es.aguasnegras.tdd.calculator.Calculator;
import es.aguasnegras.tdd.calculator.CalculatorProxy;
import es.aguasnegras.tdd.calculator.SimpleCalculatorProxy;
import es.aguasnegras.tdd.calculator.lexer.*;
import es.aguasnegras.tdd.calculator.operators.MathOperator;
import es.aguasnegras.tdd.calculator.validator.CalculatorValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by aventura on 5/02/14.
 */
public class ParserTests {

    private MathParser mathParser;
    private Lexer mathLexer;

    @Before
    public void setUp() {
        mathLexer = new MathLexer(new ExpressionValidator(), new ExpressionFixer());
        mathParser = new MathParser(mathLexer, new SimpleCalculatorProxy(new CalculatorValidator(Integer.MIN_VALUE, Integer.MAX_VALUE),
                new Calculator()));
    }

    @Test
    public void processBinaryExpression() {
        assertEquals(4, mathParser.processExpression("2 + 2"));
    }

    @Test
    public void parserWorksWithCalculatorProxy() {
        SimpleCalculatorProxy calculatorProxyMock = mock(SimpleCalculatorProxy.class);
        MathParser mathParser = new MathParser(mathLexer, calculatorProxyMock);
        mathParser.processExpression("0 + 2");
        verify(calculatorProxyMock).binaryOperation(CalculatorProxy.CalculatorMethod.ADD, 0, 2);
    }

    @Test
    public void parserWorksWithLexer() {
        List<MathToken> tokens = new ArrayList<>();
        tokens.add(new MathToken("2"));
        tokens.add(new MathToken("+"));
        tokens.add(new MathToken("2"));
        Lexer lexerMock = mock(MathLexer.class);
        when(lexerMock.getTokens("2 + 2")).thenReturn(tokens);
        MathParser parser = new MathParser(lexerMock, new SimpleCalculatorProxy(new CalculatorValidator(Integer.MIN_VALUE,
                Integer.MAX_VALUE), new Calculator()));
        parser.processExpression("2 + 2");
        verify(lexerMock).getTokens("2 + 2");
    }

    @Test
    public void processTernaryExpression() {
        assertEquals(6, mathParser.processExpression("3 + 2 + 1"));
    }

    @Test
    public void processExpressionWithPrecedence() {
        assertEquals(9, mathParser.processExpression("3 + 3 * 2"));
    }

    @Test
    public void getMaxPrecedence() {
        List<MathToken> tokens = mathLexer.getTokens("3 + 3 * 2");
        MathOperator operator = mathParser.getMaxPrecedence(tokens);
        assertEquals("*", operator.getToken());
    }

    @Test
    public void processAllOperatorsExpression() {
        assertEquals(9, mathParser.processExpression("5 + 4 * 2 / 2"));
    }
}
