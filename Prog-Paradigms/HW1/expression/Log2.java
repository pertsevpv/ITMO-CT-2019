package expression;

import expression.exceptions.CalculateException;
import expression.exceptions.ExpressionException;
import expression.exceptions.OverflowException;

public class Log2 extends CommonExpression {

    private CommonExpression expression;

    public Log2(CommonExpression exp) {
        this.expression = exp;
    }

    @Override
    public int evaluate(int x) {
        int a = expression.evaluate(x);
        checkLog(a);
        return log(a);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int a = expression.evaluate(x, y, z);
        checkLog(a);
        return log(a);
    }

    private void checkLog(int n) {
        if (n < 0) throw new CalculateException("Logarithm of a negative number: log2(" + n + ")");
        if (n == 0) throw new CalculateException("Logarithm of zero: log2(0)");
    }

    @Override
    public String toString() {
        return new StringBuilder("Log2(").append(expression.toString()).append(")").toString();
    }

    public int log(int a) {
        for (int i = 0; ; i++) {
            a /= 2;
            if (a == 0) return i;
        }
    }
}
