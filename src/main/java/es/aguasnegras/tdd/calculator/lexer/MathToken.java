package es.aguasnegras.tdd.calculator.lexer;

/**
 * Created by aventura on 5/02/14.
 */
public class MathToken {
    private final String value;
    private boolean operator;

    public MathToken(String token) {
        this.value = token;
        checkOperator();
    }

    private void checkOperator() {
        switch (value) {
            case "+":
            case "-":
            case "*":
            case "/":
                operator = true;
                break;
            default:
                operator = false;
                break;
        }
    }

    public String getValue() {
        return value;
    }

    public boolean isOperator() {
        return operator;
    }
}
