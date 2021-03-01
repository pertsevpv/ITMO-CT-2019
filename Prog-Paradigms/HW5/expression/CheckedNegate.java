package expression;

import expression.generic.AbstractOperations;

public class CheckedNegate<T extends Number> extends CommonUnOperation<T> {

    public CheckedNegate(TripleExpression<T> e, AbstractOperations<T> o) {
        super(e, o);
        sign = "-";
    }

    @Override
    protected T operate(T a) {
        return ops.neg(a);
    }
}