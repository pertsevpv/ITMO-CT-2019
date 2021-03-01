package expression;

import expression.exceptions.OverflowException;

public class CheckedSubtract extends CommonOperation {

    public CheckedSubtract(CommonExpression l, CommonExpression r) {
        super(l, r);
        sign = " - ";
    }

    @Override
    protected int operate(int a, int b) {
        checkOperation(a, b);
        return a - b;
    }

    @Override
    protected void checkOperation(int a, int b) {
        if (b > 0) {
            if (Integer.MIN_VALUE + b > a) {
                throw new OverflowException(a, b, sign);
            }
        } else {
            if (Integer.MAX_VALUE + b < a) {
                throw new OverflowException(a, b, sign);
            }
        }
    }
}