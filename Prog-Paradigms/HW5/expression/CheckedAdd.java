package expression;

import expression.generic.AbstractOperations;

public class CheckedAdd<T extends Number> extends CommonBinOperation<T> {

    public CheckedAdd(TripleExpression<T> l, TripleExpression<T> r, AbstractOperations<T> o) {
        super(l, r, o);
        sign = "+";
    }

    @Override
    protected T operate(T a, T b) {
        return ops.add(a, b);
    }
}
