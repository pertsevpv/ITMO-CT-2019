package expression.exceptions;

public class ParenthesisException extends ExpressionException {

    public ParenthesisException(String exc, String message, int pos) {
        super(exc, message, pos);
    }

}