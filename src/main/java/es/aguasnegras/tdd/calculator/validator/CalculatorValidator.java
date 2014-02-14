package es.aguasnegras.tdd.calculator.validator;

/**
 * Created by case on 4/02/14.
 */
public class CalculatorValidator implements LimitsValidator {
    private final int lowerLimitValue;
    private final int upperLimitValue;

    public CalculatorValidator(int minValue, int maxValue) {
        lowerLimitValue = minValue;
        upperLimitValue = maxValue;
    }

    @Override
    public void checkArguments(int firstArgument, int secondArgument) {
        if (!checkValueWithinLimits(firstArgument)) {
            throw new IllegalArgumentException("First argument " + firstArgument + " is not within limits "
                    + lowerLimitValue + ", " + upperLimitValue);
        }
        if (!checkValueWithinLimits(secondArgument)) {
            throw new IllegalArgumentException("Second argument " + secondArgument + " is not within limits "
                    + lowerLimitValue + ", " + upperLimitValue);
        }
    }

    @Override
    public void checkResult(int result) {
        if (!checkValueWithinLimits(result)) {
            throw new IllegalStateException("Result " + result + " is not within limits " + lowerLimitValue + ", "
                    + upperLimitValue);
        }
    }

    private boolean checkValueWithinLimits(int argument) {
        boolean withinLimits = true;
        if (argument < lowerLimitValue) {
            withinLimits = false;
        } else if (argument > upperLimitValue) {
            withinLimits = false;
        }
        return withinLimits;
    }

}
