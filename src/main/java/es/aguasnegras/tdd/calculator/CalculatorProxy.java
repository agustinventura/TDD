package es.aguasnegras.tdd.calculator;

/**
 * Created by aventura on 10/02/14.
 */
public interface CalculatorProxy {
    public enum CalculatorMethod {
        ADD, SUBSTRACT, MULTIPLY, DIVIDE
    }

    int binaryOperation(CalculatorMethod method, int firstArg, int secondArg);
}
