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
        if (firstArgument < lowerLimitValue) {
            throw new IllegalArgumentException("First argument " + firstArgument + " is smaller than minimum allowed: "
                    + lowerLimitValue);
        } else if (firstArgument > upperLimitValue) {
            throw new IllegalArgumentException("First argument " + firstArgument + " is bigger than maximum allowed: "
                    + upperLimitValue);
        } else if (secondArgument < lowerLimitValue) {
            throw new IllegalArgumentException("Second argument " + secondArgument +
                    " is smaller than minimum allowed: "+ lowerLimitValue);
        } else if (secondArgument > upperLimitValue) {
            throw new IllegalArgumentException("Second argument " + secondArgument +
                    " is bigger than maximum allowed: "+ upperLimitValue);
        }
    }

}
