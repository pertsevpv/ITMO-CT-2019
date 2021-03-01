package expression;

import expression.generic.AbstractOperations;

public abstract class CommonUnOperation<T extends Number> implements TripleExpression<T> {

    private TripleExpression<T> exp;
    protected AbstractOperations<T> ops;
    protected String sign;

    protected abstract T operate(T a);

    protected CommonUnOperation(TripleExpression<T> e, AbstractOperations<T> o) {
        this.exp = e;
        ops = o;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return operate(exp.evaluate(x, y, z));
    }
}
