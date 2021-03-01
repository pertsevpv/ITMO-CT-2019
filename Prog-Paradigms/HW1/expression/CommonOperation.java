package expression;

import java.util.Objects;

public abstract class CommonOperation extends CommonExpression {

    private CommonExpression a;
    private CommonExpression b;
    protected String sign;

    protected abstract int operate(int a, int b);

    protected abstract void checkOperation(int a, int b);

    public CommonOperation(CommonExpression l, CommonExpression r) {
        this.a = l;
        this.b = r;
    }

    @Override
    public int evaluate(int x) {
        return this.operate(this.getA().evaluate(x), this.getB().evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.operate(this.getA().evaluate(x, y, z), this.getB().evaluate(x, y, z));
    }

    public String toString() {
        return new StringBuilder("(").append(a.toString()).append(sign).append(b.toString()).append(")").toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getA(), this.getClass(), this.getB());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (this.getClass() == obj.getClass()) {
                return this.getA().equals(((CommonOperation) obj).getA()) && this.getB().equals(((CommonOperation) obj).getB());
            }
        }
        return false;
    }

    public CommonExpression getA() {
        return a;
    }

    public CommonExpression getB() {
        return b;
    }
}