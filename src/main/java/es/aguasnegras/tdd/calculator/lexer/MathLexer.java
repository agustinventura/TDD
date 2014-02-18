package es.aguasnegras.tdd.calculator.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by case on 8/02/14.
 */
public class MathLexer implements Lexer {

    private final ExpressionValidator expressionValidator;
    private final ExpressionFixer expressionFixer;

    public MathLexer(ExpressionValidator expressionValidator, ExpressionFixer expressionFixer) {
        this.expressionValidator = expressionValidator;
        this.expressionFixer = expressionFixer;
    }

    @Override
    public List<MathToken> getTokens(String expression) {
        List<MathToken> tokens;
        if (expressionValidator.isValid(expression)) {
            String[] items = splitExpression(expression);
            tokens = getTokensFromStrings(items);
        } else {
            throw new IllegalArgumentException("Expression " + expression + " is not valid");
        }
        return tokens;
    }

    @Override
    public List<String> getExpressions(String expression) {
        List<StringBuilder> expressionsBuilder = new ArrayList<>();
        Stack<Integer> parenthesis = new Stack<>();
        int index = 0;
        expressionsBuilder.add(new StringBuilder(""));
        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                parenthesis.push(index);
                index++;
                expressionsBuilder.add(new StringBuilder(""));
            } else if (ch == ')') {
                index = parenthesis.pop();
            } else {
                expressionsBuilder.get(index).append(ch);
            }
        }
        List<String> expressions = expressionFixer.fixExpressions(expressionsBuilder);
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
