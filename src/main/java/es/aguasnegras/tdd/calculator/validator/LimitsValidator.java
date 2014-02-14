package es.aguasnegras.tdd.calculator.validator;

/**
 * Created by case on 4/02/14.
 */
public interface LimitsValidator {

    void checkArguments(int firstArgument, int secondArgument);

    void checkResult(int result);
}
