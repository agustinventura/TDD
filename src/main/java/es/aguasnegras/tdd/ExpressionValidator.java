package es.aguasnegras.tdd;

/**
 * Created by case on 8/02/14.
 */
public class ExpressionValidator {
    public boolean isValid(String expression) {
        return expression.matches("^-?\\d+(\\s*[\\+|\\-|\\*|\\/]\\s*-?\\d+)+$");
    }
}
