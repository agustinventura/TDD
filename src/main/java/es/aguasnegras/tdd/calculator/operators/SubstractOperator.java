package es.aguasnegras.tdd.calculator.operators;

import es.aguasnegras.tdd.calculator.CalculatorProxy;

/**
 * Created by aventura on 14/02/14.
 */
public class SubstractOperator extends MathOperator {

    public SubstractOperator(int index) {
        super(index, 1, "-");
    }

    @Override
    public int resolve(int firstOperand, int secondOperand, CalculatorProxy calculator) {
        return calculator.binaryOperation(CalculatorProxy.CalculatorMethod.SUBSTRACT, firstOperand, secondOperand);
    }
}
