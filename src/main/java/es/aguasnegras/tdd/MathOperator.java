package es.aguasnegras.tdd;

/**
 * Created by aventura on 10/02/14.
 */
public class MathOperator {

    private int precedence;
    private MathToken token;

    public MathOperator(int precedence, MathToken token) {
        this.precedence = precedence;
        this.token = token;
    }

    public String getValue() {
        return token.getValue();
    }

    public int getPrecedence() {
        return precedence;
    }
}
