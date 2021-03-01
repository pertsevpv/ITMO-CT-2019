package expression.exceptions;

public class CalculateException extends IllegalStateException {

    public CalculateException(String message, Number a, Number b, String sign) {
        super(message + ": " + a.toString() + sign + b.toString());
    }

    public CalculateException(String message, Number a, String sign) {
        super(message + ": " + sign + a.toString());
    }
}