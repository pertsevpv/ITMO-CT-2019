package expression;

import expression.exceptions.OverflowException;

public class CheckedMultiply extends CommonOperation {

    public CheckedMultiply(CommonExpression l, CommonExpression r) {
        super(l, r);
        sign = " * ";
    }

    @Override
    protected int operate(int a, int b) {
        checkOperation(a, b);
        return a * b;
    }

    @Override
    protected void checkOperation(int a, int b) {
        if (a != 0 && b != 0) {
            if ((a == Integer.MIN_VALUE && b != 1) || (b == Integer.MIN_VALUE && a != 1)) {
                throw new OverflowException(a, b, sign);
            }
            if (signum(a) == signum(b)) {
                if (abs(a) > Integer.MAX_VALUE / abs(b)) {
                    throw new OverflowException(a, b, sign);
                }
            } else {
                if (a < Integer.MIN_VALUE / abs(b) || b < Integer.MIN_VALUE / abs(a)) {
                    throw new OverflowException(a, b, sign);
                }
            }
        }
    }

    private static int signum(int a) {
        return Integer.compare(a, 0);
    }

    private static int abs(int a) {
        return a >= 0 ? a : -a;
    }
}
