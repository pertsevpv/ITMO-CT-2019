package expression;

import expression.generic.AbstractOperations;

public abstract class CommonBinOperation<T extends Number> implements TripleExpression<T> {

    private TripleExpression<T> a;
    private TripleExpression<T> b;
    protected AbstractOperations<T> ops;
    protected String sign;

    protected abstract T operate(T a, T b);

    protected CommonBinOperation(TripleExpression<T> l, TripleExpression<T> r, AbstractOperations<T> o){
        this.a = l;
        this.b = r;
        this.ops = o;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return operate(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }
}
