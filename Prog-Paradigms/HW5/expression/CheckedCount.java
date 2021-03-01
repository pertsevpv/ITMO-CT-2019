package expression;

import expression.generic.AbstractOperations;

public class CheckedCount<T extends Number> extends CommonUnOperation<T> {

    public CheckedCount(TripleExpression<T> e, AbstractOperations<T> o) {
        super(e, o);
    }

    @Override
    protected T operate(T a) {
        return ops.bitCount(a);
    }
}