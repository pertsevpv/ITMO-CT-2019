package expression.exceptions;

public class IllegalSymbolException extends ExpressionException {
    public IllegalSymbolException(String exc, int pos, char ill) {
        super(exc, "Illegal symbol " + ill, pos);
    }
}
