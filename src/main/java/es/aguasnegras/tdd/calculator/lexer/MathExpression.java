package es.aguasnegras.tdd.calculator.lexer;

/**
 * Created by aventura on 11/04/14.
 */
public class MathExpression implements Comparable<MathExpression> {
    private String expression;
    private int order;

    public MathExpression(String expression) {
        this.expression = expression;
        order = -1;
    }

    public MathExpression(String expression, int order) {
        this.expression = expression;
        this.order = order;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MathExpression)) return false;

        MathExpression that = (MathExpression) o;

        if (order != that.order) return false;
        if (!expression.equals(that.expression)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = expression.hashCode();
        result = 31 * result + order;
        return result;
    }

    @Override
    public String toString() {
        return "MathExpression{" +
                "expression='" + expression + '\'' +
                ", order=" + order +
                '}';
    }

    @Override
    public int compareTo(MathExpression o) {
        return Integer.compare(getOrder(), o.getOrder());
    }
}
