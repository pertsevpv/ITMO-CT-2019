package expression.exceptions;

public class ConstantOverflowException extends ExpressionException {
    public ConstantOverflowException(String exc, String message, int pos) {
        super(exc, "Constant Overflow: ", pos);
    }
}
