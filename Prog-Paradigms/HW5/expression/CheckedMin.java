package expression;

import expression.generic.AbstractOperations;

public class CheckedMin<T extends Number> extends CommonBinOperation<T> {

    public CheckedMin(TripleExpression<T> l, TripleExpression<T> r, AbstractOperations<T> o) {
        super(l, r, o);
    }

    @Override
    protected T operate(T a, T b) {
        return ops.min(a, b);
    }
}
