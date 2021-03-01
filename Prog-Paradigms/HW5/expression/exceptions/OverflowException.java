package expression.exceptions;

public class OverflowException extends CalculateException  {
    public OverflowException(Number a, Number b, String op) {
        super("Overflow: ", a, b, op);
    }

    public OverflowException(Number a, String op) {
        super("Overflow: ", a, op);
    }
}
