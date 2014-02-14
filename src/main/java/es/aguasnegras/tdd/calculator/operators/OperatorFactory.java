package es.aguasnegras.tdd.calculator.operators;

import es.aguasnegras.tdd.calculator.lexer.MathToken;

/**
 * Created by aventura on 10/02/14.
 */
public class OperatorFactory {
    public static MathOperator create(int index, MathToken token) {
        MathOperator result;
        switch (token.getValue()) {
            case "*":
                result = new MultiplyOperator(index);
                break;
            case "/":
                result = new DivideOperator(index);
                break;
            case "+":
                result = new AddOperator(index);
                break;
            case "-":
                result = new SubstractOperator(index);
                break;
            default:
                throw new IllegalArgumentException("Token " + token.getValue() + " is not a valid operator");
        }
        return result;
    }
}
