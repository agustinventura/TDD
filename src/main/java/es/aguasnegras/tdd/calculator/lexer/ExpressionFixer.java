package es.aguasnegras.tdd.calculator.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aventura on 18/02/14.
 */
public class ExpressionFixer {

    private final MathRegex mathRegex;

    public ExpressionFixer(MathRegex mathRegex) {
        this.mathRegex = mathRegex;
    }

    public List<String> fixExpressions(List<StringBuilder> expressionsBuilder) {
        List<String> expressions = new ArrayList<>();
        for (StringBuilder expression : expressionsBuilder) {
            fixExpression(expressions, expression);
        }
        return expressions;
    }

    private void fixExpression(List<String> expressions, StringBuilder expression) {
        if (mathRegex.isNumberAndOperator(expression.toString())) {
            splitNumberAndOperator(expressions, expression);
        } else {
            addNonEmptyExpression(expressions, expression);
        }
    }

    private void addNonEmptyExpression(List<String> expressions, StringBuilder expression) {
        if (expression.toString().trim().length() > 0) {
            expressions.add(expression.toString().trim());
        }
    }

    private void splitNumberAndOperator(List<String> expressions, StringBuilder expression) {
        String[] components = expression.toString().trim().split("\\s+");
        for (String component : components) {
            expressions.add(component);
        }
    }
}
