package expression;

import expression.exceptions.CalculateException;
import expression.exceptions.OverflowException;

public class Pow2 extends CommonExpression {

    private CommonExpression expression;

    public Pow2(CommonExpression exp) {
        this.expression = exp;
    }

    @Override
    public int evaluate(int x) {
        int a = expression.evaluate(x);
        checkPow(a);
        return pow(a);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int a = expression.evaluate(x, y, z);
        checkPow(a);
        return 1 << a; //ow(a);
    }

    private void checkPow(int n) {
        if (n < 0) throw new CalculateException("Negative power: " + n);
        if (n > 30) throw new OverflowException("pow2(" + n + ")");
    }

    @Override
    public String toString() {
        return new StringBuilder("pow2(").append(expression.toString()).append(")").toString();
    }

    public int pow(int a) {
        int p = 1;
        for (; a > 0; a--) {
            if (Integer.MAX_VALUE / 2 < p) {
                throw new OverflowException("");
            }
            p *= 2;
        }
        return p;
    }

}
