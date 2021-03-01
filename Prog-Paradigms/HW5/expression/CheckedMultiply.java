package expression;

import expression.generic.AbstractOperations;

public class CheckedMultiply<T extends Number> extends CommonBinOperation<T> {

    public CheckedMultiply(TripleExpression<T> l, TripleExpression<T> r, AbstractOperations<T> o) {
        super(l, r, o);
        sign = "*";
    }

    @Override
    protected T operate(T a, T b) {
        return ops.mult(a, b);
    }
}
