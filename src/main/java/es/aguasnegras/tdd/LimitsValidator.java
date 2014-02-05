package es.aguasnegras.tdd;

/**
 * Created by case on 4/02/14.
 */
public interface LimitsValidator {

    void checkArguments(int firstArgument, int secondArgument);

    void checkResult(int result);
}
