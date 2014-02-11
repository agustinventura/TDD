package es.aguasnegras.tdd;

/**
 * Created by aventura on 10/02/14.
 */
public interface CalculatorProxy {
    int binaryOperation(CalculatorMethod method, int firstArg, int secondArg);

    public enum CalculatorMethod {
        ADD, SUBSTRACT, MULTIPLY, DIVIDE
    }
}
