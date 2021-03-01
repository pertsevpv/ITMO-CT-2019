package expression.generic;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class IntegerOperations extends AbstractOperations<Integer> {

    @Override
    public Integer a(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer s(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer d(Integer a, Integer b) {
        return a / b;
    }

    @Override
    public Integer m(Integer a, Integer b) {
        return a * b;
    }

    @Override
    Integer n(Integer a) {
        return -a;
    }

    @Override
    public void checkAdd(Integer a, Integer b) {
        if (b > 0) {
            if (Integer.MAX_VALUE - b < a) {
                throw new OverflowException(a, b, "+");
            }
        } else {
            if (Integer.MIN_VALUE - b > a) {
                throw new OverflowException(a, b, "+");
            }
        }
    }

    @Override
    public void checkSub(Integer a, Integer b) {
        if (b > 0) {
            if (Integer.MIN_VALUE + b > a) {
                throw new OverflowException(a, b, "-");
            }
        } else {
            if (Integer.MAX_VALUE + b < a) {
                throw new OverflowException(a, b, "-");
            }
        }
    }

    @Override
    public void checkDiv(Integer a, Integer b) {
        if (b == 0) {
            throw new DivisionByZeroException(a, b);
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException(a, b, "/");
        }
    }

    @Override
    public void checkMult(Integer a, Integer b) {
        if (a != 0 && b != 0) {
            if ((a == Integer.MIN_VALUE && b != 1) || (b == Integer.MIN_VALUE && a != 1)) {
                throw new OverflowException(a, b, "*");
            }
            if (signum(a) == signum(b)) {
                if (abs(a) > Integer.MAX_VALUE / abs(b)) {
                    throw new OverflowException(a, b, "*");
                }
            } else {
                if (a < Integer.MIN_VALUE / abs(b) || b < Integer.MIN_VALUE / abs(a)) {
                    throw new OverflowException(a, b, "*");
                }
            }
        }
    }

    @Override
    protected void checkNegate(Integer a) {
        if (a == Integer.MIN_VALUE) throw new OverflowException(a, "-");
    }

    @Override
    Integer gn(String s) {
        return Integer.parseInt(s);
    }

    @Override
    public Integer bitCount(Integer a) {
        return Integer.bitCount(a);
    }

    @Override
    public Integer max(Integer a, Integer b) {
        return Math.max(a,b);
    }

    @Override
    public Integer min(Integer a, Integer b) {
        return Math.min(a,b);
    }
}