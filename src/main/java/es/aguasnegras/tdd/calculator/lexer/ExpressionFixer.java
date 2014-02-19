package es.aguasnegras.tdd.calculator.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aventura on 18/02/14.
 */
public class ExpressionFixer {

    private final ExpressionValidator expressionValidator;

    public ExpressionFixer(ExpressionValidator expressionValidator) {
        this.expressionValidator = expressionValidator;
    }

    public List<String> fixExpressions(List<StringBuilder> expressionsBuilder) {
        List<String> expressions = new ArrayList<>();
        for (StringBuilder expression : expressionsBuilder) {
            if (expressionValidator.isNumberAndOperator(expression.toString())) {
                String[] components = expression.toString().trim().split("\\s+");
                for (String component : components) {
                    expressions.add(component);
                }
            } else {
                if (expression.toString().length() > 0) {
                    expressions.add(expression.toString());
                }
            }
        }
        return expressions;
    }
}
