package expression;

import expression.generic.AbstractOperations;

public class CheckedMax<T extends Number> extends CommonBinOperation<T> {

    public CheckedMax(TripleExpression<T> l, TripleExpression<T> r, AbstractOperations<T> o) {
        super(l, r, o);
    }

    @Override
    protected T operate(T a, T b) {
        return ops.max(a, b);
    }
}
