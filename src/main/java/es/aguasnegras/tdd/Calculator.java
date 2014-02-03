package es.aguasnegras.tdd;

/**
 * Created by aventura on 3/02/14.
 */
public class Calculator {

    private int lowerLimitValue;
    private int upperLimitValue;

    public Calculator(int lowerLimitValue, int upperLimitValue) {
        this.lowerLimitValue = lowerLimitValue;
        this.upperLimitValue = upperLimitValue;
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

    public int add(int augend, int addend) {
        int result = augend + addend;
        checkArguments(augend, addend);
        if (result > upperLimitValue) {
            throw new IllegalStateException("The result of adding " + augend + " to "
                    + addend + " is bigger than maximum value " + upperLimitValue);
        }
        return result;
    }

    public int substract(int minuend, int substraend) {
        int result = minuend - substraend;
        if (result < lowerLimitValue) {
            throw new IllegalStateException("The result of substracting " + substraend + " to "
                    + minuend + " is smaller than minimum value " + lowerLimitValue);
        }
        return result;
    }

    private void checkArguments(int firstArgument, int secondArgument) {
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
