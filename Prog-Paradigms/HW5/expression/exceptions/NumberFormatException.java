package expression.exceptions;

public class NumberFormatException extends java.lang.NumberFormatException {
    public NumberFormatException(String s) {
        super("Error while parsing number: " + s);
    }
}
