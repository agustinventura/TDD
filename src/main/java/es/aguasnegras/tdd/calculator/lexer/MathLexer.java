package es.aguasnegras.tdd.calculator.lexer;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by case on 8/02/14.
 */
public class MathLexer implements Lexer {

    private final MathRegex mathRegex;
    private final ExpressionFixer expressionFixer;

    private static final char OPEN_SUBEXPRESSION = '(';
    private static final char CLOSE_SUBEXPRESSION = ')';

    private int openedParenthesis = 0;
    private int startSearchingAt = 0;

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
    public List<MathExpression> getExpressions(String expression) {
        List<MathExpression> totalExpressionsFound = new ArrayList<>();
        getExpressions(expression, new MathExpression(StringUtils.EMPTY), totalExpressionsFound);
        if (openedParenthesis > 0) {
            throw new IllegalArgumentException("There is unmatched parenthesis");
        }
        Collections.sort(totalExpressionsFound);
        expressionFixer.fix(totalExpressionsFound);
        return totalExpressionsFound;
    }

    private void getExpressions(String expression, MathExpression mathExpression, List<MathExpression> totalExpressionsFound) {
        boolean endOfExpression = false;
        int currentIndex = startSearchingAt;
        while (currentIndex < expression.length() && !endOfExpression) {
            char currentChar = expression.charAt(currentIndex);
            if (currentChar == OPEN_SUBEXPRESSION) {
                openedParenthesis++;
                startSearchingAt = currentIndex + 1;
                getExpressions(expression, new MathExpression(StringUtils.EMPTY, startSearchingAt), totalExpressionsFound);
                currentIndex = startSearchingAt;
            } else if (currentChar == CLOSE_SUBEXPRESSION) {
                totalExpressionsFound.add(mathExpression);
                startSearchingAt = currentIndex;
                openedParenthesis--;
                endOfExpression = true;
            } else {
                mathExpression.setExpression(mathExpression.getExpression() + currentChar);
                if (mathExpression.getOrder() == -1) {
                    mathExpression.setOrder(startSearchingAt);
                }
            }
            currentIndex++;
        }
        if (!endOfExpression) {
            totalExpressionsFound.add(mathExpression);
        }
    }

    private List<StringBuilder> getDisclosedExpressions(String expression) {
        List<StringBuilder> expressions = new ArrayList<StringBuilder>();
        Stack<Integer> parenthesis = new Stack<Integer>();
        int index = 1;
        expressions.add(new StringBuilder(""));
        for (char character : expression.toCharArray()) {
            if (character == OPEN_SUBEXPRESSION) {
                parenthesis.push(index);
                index++;
                expressions.add(index - 1, new StringBuilder(""));
            } else if (character == CLOSE_SUBEXPRESSION) {
                index = parenthesis.pop();
            } else {
                expressions.get(index - 1).append(character);
            }
        }
        if (parenthesis.size() > 0) {
            throw new IllegalArgumentException("There is unmatched parenthesis");
        }
        return expressions;
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
