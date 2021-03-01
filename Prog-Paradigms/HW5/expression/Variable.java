package expression;

public class Variable<T extends Number> implements TripleExpression<T> {

    private String var;

    public Variable(String s) {
        var = s;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        switch (var) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        throw new IllegalStateException("x, y, z expected");
    }
}