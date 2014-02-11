package es.aguasnegras.tdd;

/**
 * Created by aventura on 10/02/14.
 */
public class MathOperator {

    private final int precedence;
    private final int index;
    private final MathToken token;

    public MathOperator(int precedence, int index, MathToken token) {
        this.precedence = precedence;
        this.index = index;
        this.token = token;
    }

    public String getValue() {
        return token.getValue();
    }

    public int getPrecedence() {
        return precedence;
    }

    public int getIndex() {
        return index;
    }

    public int resolve(int firstOperand, int secondOperand, CalculatorProxy calculator) {
        int result = 0;
        switch (token.getValue()) {
            case "*":
                result = calculator.binaryOperation(CalculatorProxy.CalculatorMethod.MULTIPLY, firstOperand,
                        secondOperand);
                break;
            case "/":
                result = calculator.binaryOperation(CalculatorProxy.CalculatorMethod.DIVIDE, firstOperand,
                        secondOperand);
                break;
            case "+":
                result = calculator.binaryOperation(CalculatorProxy.CalculatorMethod.ADD, firstOperand,
                        secondOperand);
                break;
            case "-":
                result = calculator.binaryOperation(CalculatorProxy.CalculatorMethod.SUBSTRACT, firstOperand,
                        secondOperand);
                break;
            default:
                break;
        }
        return result;
    }
}
