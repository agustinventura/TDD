package es.aguasnegras.tdd;

import java.util.List;

/**
 * Created by aventura on 5/02/14.
 */
public class MathParser {

    private CalculatorProxy calculatorProxy;
    private Lexer mathLexer;

    public MathParser(Lexer mathLexer, CalculatorProxy calculatorProxy) {
        this.mathLexer = mathLexer;
        this.calculatorProxy = calculatorProxy;
    }

    public int processExpression(String expression) {
        List<MathToken> tokens = mathLexer.getTokens(expression);
        int total = 0;
        for (MathToken token : tokens) {
            if (!token.isOperator()) {
                total = calculatorProxy.binaryOperation(CalculatorProxy.CalculatorMethod.ADD,
                        total, Integer.parseInt(token.getValue()));
            }
        }
        return total;
    }

    public MathOperator getMaxPrecedence(List<MathToken> tokens) {
        int precedence = 0;
        MathOperator maxPrecedenceOperator = null;
        for (MathToken token : tokens) {
            if (token.isOperator()) {
                MathOperator operator = OperatorFactory.create(token);
                if (operator.getPrecedence() > precedence) {
                    precedence = operator.getPrecedence();
                    maxPrecedenceOperator = operator;
                }
            }
        }
        return maxPrecedenceOperator;
    }
}
