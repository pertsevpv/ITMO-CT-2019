package expression.generic;

import expression.exceptions.NumberFormatException;

import java.util.Comparator;

public abstract class AbstractOperations<T extends Number> implements Operations<T> {

    public T add(T a, T b) {
        checkAdd(a, b);
        return a(a, b);
    }

    public T sub(T a, T b) {
        checkSub(a, b);
        return s(a, b);
    }

    public T div(T a, T b) {
        checkDiv(a, b);
        return d(a, b);
    }

    public T mult(T a, T b) {
        checkMult(a, b);
        return m(a, b);
    }

    public T neg(T a) {
        checkNegate(a);
        return n(a);
    }

    public T getNum(String s) throws NumberFormatException {
        try {
            return gn(s);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage() + ": " + s);
        }
    }

    protected void checkAdd(T a, T b) {

    }

    protected void checkSub(T a, T b) {

    }

    protected void checkDiv(T a, T b) {

    }

    protected void checkMult(T a, T b) {

    }

    protected void checkNegate(T a) {
    }

    abstract T a(T a, T b);

    abstract T s(T a, T b);

    abstract T d(T a, T b);

    abstract T m(T a, T b);

    abstract T n(T a);

    abstract T gn(String s);

    protected static int signum(int a) {
        return Integer.compare(a, 0);
    }

    protected static int abs(int a) {
        return a >= 0 ? a : -a;
    }
}
