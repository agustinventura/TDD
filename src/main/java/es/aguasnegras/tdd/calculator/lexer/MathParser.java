package es.aguasnegras.tdd.calculator.lexer;

import es.aguasnegras.tdd.calculator.CalculatorProxy;
import es.aguasnegras.tdd.calculator.operators.MathOperator;
import es.aguasnegras.tdd.calculator.operators.OperatorFactory;

import java.util.List;

/**
 * Created by aventura on 5/02/14.
 */
public class MathParser {

    private final CalculatorProxy calculatorProxy;
    private final Lexer mathLexer;

    public MathParser(Lexer mathLexer, CalculatorProxy calculatorProxy) {
        this.mathLexer = mathLexer;
        this.calculatorProxy = calculatorProxy;
    }

    public int processExpression(String expression) {
        List<MathExpression> subExpressions = mathLexer.getExpressions(expression);
        StringBuilder flatExpression = new StringBuilder();
        for (MathExpression subExpression : subExpressions) {
            if (isSubExpression(subExpression.getExpression())) {
                flatExpression.append(resolveSimpleExpression(subExpression.getExpression()));
            } else {
                flatExpression.append(" " + subExpression.getExpression() + " ");
            }
        }
        return resolveSimpleExpression(flatExpression.toString());
    }

    private int resolveSimpleExpression(String flatExpression) {
        List<MathToken> tokens = mathLexer.getTokens(flatExpression);
        while (tokens.size() > 1) {
            processTokens(tokens);
        }
        return Integer.parseInt(tokens.get(0).getValue());
    }

    private boolean isSubExpression(String subExpression) {
        boolean isSubExpression = false;
        String isOperator = "[\\+|\\-|\\*|/]";
        String isNumber = "\\d+";
        if (subExpression.matches(isOperator) || subExpression.matches(isNumber)) {
            isSubExpression = true;
        }
        return isSubExpression;
    }

    private void processTokens(List<MathToken> tokens) {
        MathOperator operator = evaluateMaxPrecedenceOperation(tokens);
        deleteUsedTokens(tokens, operator);
    }

    private void deleteUsedTokens(List<MathToken> tokens, MathOperator operator) {
        for (int i = 0; i < 3; i++) {
            tokens.remove(operator.getIndex());
        }
    }

    private MathOperator evaluateMaxPrecedenceOperation(List<MathToken> tokens) {
        MathOperator operator = getMaxPrecedence(tokens);
        int firstOperand = Integer.parseInt(tokens.get(operator.getIndex() - 1).getValue());
        int secondOperand = Integer.parseInt(tokens.get(operator.getIndex() + 1).getValue());
        int result = operator.resolve(firstOperand, secondOperand, calculatorProxy);
        tokens.add(operator.getIndex() - 1, new MathToken(Integer.toString(result)));
        return operator;
    }

    public MathOperator getMaxPrecedence(List<MathToken> tokens) {
        int precedence = 0;
        int index = 0;
        MathOperator maxPrecedenceOperator = null;
        for (MathToken token : tokens) {
            if (token.isOperator()) {
                MathOperator operator = OperatorFactory.create(index, token);
                if (operator.getPrecedence() > precedence) {
                    precedence = operator.getPrecedence();
                    maxPrecedenceOperator = operator;
                }
            }
            index++;
        }
        return maxPrecedenceOperator;
    }
}
