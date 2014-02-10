package es.aguasnegras.tdd;

/**
 * Created by case on 4/02/14.
 */
public class SimpleCalculatorProxy implements CalculatorProxy {

    private Calculator calculator;
    private Validator validator;

    public SimpleCalculatorProxy(Validator validator, Calculator calculator) {
        this.validator = validator;
        this.calculator = calculator;
    }

    @Override
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
