package expression.exceptions;

public class OverflowException extends CalculateException {
    public OverflowException(String message) {
        super("Overflow: " + message);
    }

    public OverflowException(int a, int b, String op) {
        this(a + op + b);
    }
}
