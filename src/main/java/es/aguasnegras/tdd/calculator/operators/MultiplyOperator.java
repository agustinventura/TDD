package es.aguasnegras.tdd.calculator.operators;

import es.aguasnegras.tdd.calculator.CalculatorProxy;

/**
 * Created by aventura on 13/02/14.
 */
public class MultiplyOperator extends MathOperator {

    public MultiplyOperator(int index) {
        super(index, 2, "*");
    }

    @Override
    public int resolve(int firstOperand, int secondOperand, CalculatorProxy calculator) {
        return calculator.binaryOperation(CalculatorProxy.CalculatorMethod.MULTIPLY, firstOperand, secondOperand);
    }
}
