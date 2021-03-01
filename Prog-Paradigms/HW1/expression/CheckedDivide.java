package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class CheckedDivide extends CommonOperation {

    public CheckedDivide(CommonExpression l, CommonExpression r) {
        super(l, r);
        sign = " / ";
    }

    @Override
    protected int operate(int a, int b) {
        checkOperation(a, b);
        return a / b;
    }

    @Override
    protected void checkOperation(int a, int b) {
        if (b == 0) {
            throw new DivisionByZeroException(a + sign + b);
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException(a, b, sign);
        }
    }
}