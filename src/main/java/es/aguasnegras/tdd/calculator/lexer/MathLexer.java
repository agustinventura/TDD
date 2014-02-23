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
        StringBuilder currentExpression = new StringBuilder("");
        expressionsBuilder.add(currentExpression);
        int i = 0;
        boolean endOfExpression = false;
        while (i < expression.length() && !endOfExpression) {
            char ch = expression.charAt(i);
            if (ch == OPEN_SUBEXPRESSION) {
                int subExpressionBegin = i + 1;
                int subExpressionEnd = expression.lastIndexOf(CLOSE_SUBEXPRESSION);
                String subExpression = expression.substring(subExpressionBegin, subExpressionEnd);
                expressionsBuilder.addAll(getDisclosedExpressions(subExpression));
                i = expression.indexOf(CLOSE_SUBEXPRESSION, i) + 1;
                if (i == 0) {
                    throw new IllegalArgumentException("Expression " + expression + "has unclosed parenthesis");
                }
            } else if (ch == CLOSE_SUBEXPRESSION) {
                endOfExpression = true;
            } else {
                currentExpression.append(ch);
                i++;
            }
        }
        return expressionsBuilder;
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
