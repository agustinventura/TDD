package es.aguasnegras.tdd.calculator.lexer;

import java.util.ArrayList;
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
