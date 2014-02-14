package es.aguasnegras.tdd.calculator.operators;

import es.aguasnegras.tdd.calculator.CalculatorProxy;

/**
 * Created by aventura on 10/02/14.
 */
public abstract class MathOperator {

    private final int precedence;
    private final int index;
    private final String token;

    public MathOperator(int index, int precedence, String token) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MathOperator)) return false;

        MathOperator that = (MathOperator) o;

        if (index != that.index) return false;
        if (precedence != that.precedence) return false;
        if (!token.equals(that.token)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = precedence;
        result = 31 * result + index;
        result = 31 * result + token.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MathOperator{" +
                "precedence=" + precedence +
                ", index=" + index +
                ", token='" + token + '\'' +
                '}';
    }
}
