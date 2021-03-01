package expression.exceptions;

public class ExpressionException extends IllegalStateException {

    public ExpressionException(String exc, String message, int pos) {
        super(message + " at pos " + (pos - 1) + "\n" + exc + "\n" + " ".repeat(pos - 1) + "^\n");
    }
}