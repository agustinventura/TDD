package es.aguasnegras.tdd.calculator.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by case on 8/02/14.
 */
public class MathLexer implements Lexer {

    private ExpressionValidator expressionValidator;

    public MathLexer(ExpressionValidator expressionValidator) {
        this.expressionValidator = expressionValidator;
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