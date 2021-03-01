package expression.exceptions;

public class DivisionByZeroException extends CalculateException {
    public DivisionByZeroException(Number a, Number b) {
        super("Division by zero: ", a, b, "/");
    }
}
