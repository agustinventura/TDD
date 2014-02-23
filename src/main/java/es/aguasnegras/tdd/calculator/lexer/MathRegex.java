package es.aguasnegras.tdd.calculator.lexer;

/**
 * Created by case on 8/02/14.
 */
public class MathRegex {

    private String regExStartsWithOperator = "^(\\s*)[\\+|\\-|\\*|/](\\s+)(\\d+)";
    private String regExEndsWithOperator = "(\\d+)(\\s+)[\\+|\\-|\\*|/](\\s*)$";

    public boolean isValid(String expression) {
        return expression.matches("^-?\\d+(\\s+[\\+|\\-|\\*|/]\\s+-?\\d+)+$");
    }

    public boolean isNumberAndOperator(String expression) {
        return expression.matches(regExStartsWithOperator) || expression.matches(regExEndsWithOperator);
    }
}
