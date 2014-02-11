package es.aguasnegras.tdd;

/**
 * Created by aventura on 10/02/14.
 */
public class OperatorFactory {
    public static MathOperator create(int index, MathToken token) {
        MathOperator result = null;
        switch (token.getValue()) {
            case "*":
            case "/":
                result = new MathOperator(2, index, token);
                break;
            default:
                result = new MathOperator(1, index, token);
                break;
        }
        return result;
    }
}
