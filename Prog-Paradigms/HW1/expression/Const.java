package expression;

public class Const extends CommonExpression {

    private Number value;

    public Const(Number n) {
        value = n;
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (this.getClass() == obj.getClass()) {
                return value.equals(((Const) obj).value);
            }
        }
        return false;
    }

    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public Number getValue() {
        return value;
    }
}