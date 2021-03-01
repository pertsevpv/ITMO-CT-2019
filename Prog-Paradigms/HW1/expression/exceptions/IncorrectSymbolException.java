package expression.exceptions;

public class IncorrectSymbolException extends ExpressionException {
    public IncorrectSymbolException(String exc, int pos, char exp, char get) {
        super(exc, "expected: " + exp + ", get: " + get, pos);
    }
}
