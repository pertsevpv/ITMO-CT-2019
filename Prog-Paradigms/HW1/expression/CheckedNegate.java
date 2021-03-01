
package expression;

import expression.exceptions.OverflowException;

public class CheckedNegate extends CommonExpression {

    private CommonExpression expression;

    public CheckedNegate(CommonExpression exp) {
        this.expression = exp;
    }

    @Override
    public int evaluate(int x) {
        int a = expression.evaluate(x);
        checkNegate(x);
        return -a;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int a = expression.evaluate(x, y, z);
        checkNegate(a);
        return -a;
    }

    public void checkNegate(int a) {
        if (a == Integer.MIN_VALUE) throw new OverflowException("-" + a);
    }

    @Override
    public String toString() {
        return new StringBuilder("-(").append(expression.toString()).append(")").toString();
    }
}