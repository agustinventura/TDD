package es.aguasnegras.tdd.calculator.lexer;

/**
 * Created by aventura on 5/02/14.
 */
public class MathToken {
    private final String value;
    private boolean operator;

    public MathToken(String value) {
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MathToken)) return false;

        MathToken mathToken = (MathToken) o;

        if (operator != mathToken.operator) return false;
        if (!value.equals(mathToken.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + (operator ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MathToken{" +
                "value='" + value + '\'' +
                ", operator=" + operator +
                '}';
    }
}
