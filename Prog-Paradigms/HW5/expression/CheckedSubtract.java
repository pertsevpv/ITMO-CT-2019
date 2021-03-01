package expression;

import expression.generic.AbstractOperations;

public class CheckedSubtract<T extends Number> extends CommonBinOperation<T> {

    public CheckedSubtract(TripleExpression<T> l, TripleExpression<T> r, AbstractOperations<T> o) {
        super(l, r, o);
        sign = "-";
    }

    @Override
    protected T operate(T a, T b) {
        return ops.sub(a, b);
    }
}
