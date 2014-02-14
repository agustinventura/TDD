package es.aguasnegras.tdd;

/**
 * Created by aventura on 10/02/14.
 */
public abstract class MathOperator {

    private final int precedence;
    private final int index;
    private final String token;

    public MathOperator(int precedence, int index, String token) {
        this.precedence = precedence;
        this.index = index;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public int getPrecedence() {
        return precedence;
    }

    public int getIndex() {
        return index;
    }

    public abstract int resolve(int firstOperand, int secondOperand, CalculatorProxy calculator);
}
