package es.aguasnegras.tdd.calculator.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by case on 8/02/14.
 */
public class MathLexer implements Lexer {

    private final MathRegex mathRegex;
    private final ExpressionFixer expressionFixer;

    private static final char OPEN_SUBEXPRESSION = '(';
    private static final char CLOSE_SUBEXPRESSION = ')';
    //This is a workaround to make possible the modification inside getSubexpressions
    int openedParenthesis = 0;
    int startSearchingAt = 0;

    public MathLexer(MathRegex mathRegex, ExpressionFixer expressionFixer) {
        this.mathRegex = mathRegex;
        this.expressionFixer = expressionFixer;
    }

    @Override
    public List<MathToken> getTokens(String expression) {
        List<MathToken> tokens;
        if (mathRegex.isValid(expression)) {
            String[] items = splitExpression(expression);
            tokens = getTokensFromStrings(items);
        } else {
            throw new IllegalArgumentException("Expression " + expression + " is not valid");
        }
        return tokens;
    }

    @Override
    public List<String> getExpressions(String expression) {
        List<StringBuilder> expressionsBuilder = getDisclosedExpressions(expression);
        List<String> expressions = expressionFixer.fixExpressions(expressionsBuilder);
        return expressions;
    }

    private List<StringBuilder> getDisclosedExpressions(String expression) {
        List<StringBuilder> expressionsBuilder = new ArrayList<>();
        getSubExpressions(expression, new StringBuilder(""),
                expressionsBuilder);
        if (openedParenthesis != 0) {
            throw new IllegalArgumentException("Expression " + expression + " has unclosed parenthesis");
        }
        return expressionsBuilder;
    }

    private void getSubExpressions(String expression,
                                   StringBuilder subExpressionUnderConstruction,
                                   List<StringBuilder> expressionsBuilder) {
        int subExpressionStartIndex = startSearchingAt;
        for (int currentIndex = subExpressionStartIndex; currentIndex < expression.length(); currentIndex++) {
            char currentChar = expression.charAt(currentIndex);
            if (currentChar == OPEN_SUBEXPRESSION) {
                openedParenthesis++;
                subExpressionStartIndex = currentIndex + 1;
                getSubExpressions(expression, new StringBuilder(""),
                        expressionsBuilder);
                currentIndex = subExpressionStartIndex;
            } else if (currentChar == CLOSE_SUBEXPRESSION) {
                expressionsBuilder.add(subExpressionUnderConstruction);
                subExpressionStartIndex = currentIndex;
                openedParenthesis--;
            } else {
                subExpressionUnderConstruction.append(currentChar);
            }
        }
    }

    private List<MathToken> getTokensFromStrings(String[] items) {
        List<MathToken> tokens = new ArrayList<>();
        for (String item : items) {
            tokens.add(new MathToken(item));
        }
        return tokens;
    }

    private String[] splitExpression(String expression) {
        return expression.split("\\s+");
    }
}
