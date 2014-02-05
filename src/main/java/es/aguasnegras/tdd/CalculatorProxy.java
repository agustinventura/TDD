package es.aguasnegras.tdd;

import java.lang.reflect.Method;

/**
 * Created by case on 4/02/14.
 */
public class CalculatorProxy {
    public enum CalculatorMethod {
        ADD, SUBSTRACT;
    }

    private Calculator calculator;
    private Validator validator;

    public CalculatorProxy(Validator validator, Calculator calculator) {
        this.validator = validator;
        this.calculator = calculator;
    }

    public int binaryOperation(CalculatorMethod method, int firstArg, int secondArg) {
        validator.checkArguments(firstArg, secondArg);
        int result = 0;
        switch (method) {
            case ADD:
                result = calculator.add(firstArg, secondArg);
                break;
            case SUBSTRACT:
                result = calculator.substract(firstArg, secondArg);
                break;
            default:
                break;
        }
        validator.checkResult(result);
        return result;
    }
}
