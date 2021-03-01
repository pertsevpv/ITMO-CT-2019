package expression;

import expression.TripleExpression;

public class Const<T extends Number> implements TripleExpression<T> {

    private T value;

    public Const(T v){
        value = v;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }
}
