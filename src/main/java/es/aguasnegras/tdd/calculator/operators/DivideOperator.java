package es.aguasnegras.tdd.calculator.operators;

import es.aguasnegras.tdd.calculator.CalculatorProxy;

/**
 * Created by aventura on 14/02/14.
 */
public class DivideOperator extends MathOperator {

    public DivideOperator(int index) {
        super(index, 2, "/");
    }

    @Override
    public int resolve(int firstOperand, int secondOperand, CalculatorProxy calculator) {
        return calculator.binaryOperation(CalculatorProxy.CalculatorMethod.DIVIDE, firstOperand, secondOperand);
    }
}
