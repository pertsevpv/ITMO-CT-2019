package expression.exceptions;

public class DivisionByZeroException extends CalculateException {
    public DivisionByZeroException(String message) {
        super("Division by zero: " + message);
    }
}
