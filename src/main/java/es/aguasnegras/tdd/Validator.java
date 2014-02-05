package es.aguasnegras.tdd;

/**
 * Created by case on 4/02/14.
 */
public class Validator implements LimitsValidator {
    private int lowerLimitValue;
    private int upperLimitValue;

    public Validator(int minValue, int maxValue) {
        setLimits(minValue, maxValue);
    }

    private void setLimits(int minValue, int maxValue) {
        lowerLimitValue = minValue;
        upperLimitValue = maxValue;
    }

    public int getUpperLimitValue() {
        return upperLimitValue;
    }

    public void setUpperLimitValue(int upperLimitValue) {
        this.upperLimitValue = upperLimitValue;
    }

    public int getLowerLimitValue() {
        return lowerLimitValue;
    }

    public void setLowerLimitValue(int lowerLimitValue) {
        this.lowerLimitValue = lowerLimitValue;
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
