package es.aguasnegras.tdd.calculator;

import es.aguasnegras.tdd.calculator.validator.CalculatorValidator;
import es.aguasnegras.tdd.calculator.validator.LimitsValidator;

/**
 * Created by case on 4/02/14.
 */
public class SimpleCalculatorProxy implements CalculatorProxy {

    private final Calculator calculator;
    private final LimitsValidator calculatorValidator;

    public SimpleCalculatorProxy(CalculatorValidator calculatorValidator, Calculator calculator) {
        this.calculatorValidator = calculatorValidator;
        this.calculator = calculator;
    }

    @Override
    public int binaryOperation(CalculatorMethod method, int firstArg, int secondArg) {
        calculatorValidator.checkArguments(firstArg, secondArg);
        int result = 0;
        switch (method) {
            case ADD:
                result = calculator.add(firstArg, secondArg);
                break;
            case SUBSTRACT:
                result = calculator.substract(firstArg, secondArg);
                break;
            case MULTIPLY:
                result = calculator.multiply(firstArg, secondArg);
                break;
            case DIVIDE:
                result = calculator.divide(firstArg, secondArg);
            default:
                break;
        }
        calculatorValidator.checkResult(result);
        return result;
    }
}
