package expression.generic;

import expression.exceptions.DivisionByZeroException;

public class LongOperations extends AbstractOperations<Long> {

    @Override
    Long a(Long a, Long b) {
        return a + b;
    }

    @Override
    Long s(Long a, Long b) {
        return a - b;
    }

    @Override
    Long d(Long a, Long b) {
        return a / b;
    }

    @Override
    Long m(Long a, Long b) {
        return a * b;
    }

    @Override
    Long n(Long a) {
        return -a;
    }

    @Override
    Long gn(String s) {
        return Long.parseLong(s);
    }

    @Override
    public Long bitCount(Long a) {
        return null;
    }

    @Override
    public Long max(Long a, Long b) {
        return null;
    }

    @Override
    public Long min(Long a, Long b) {
        return null;
    }

    public void checkDiv(Long a, Long b) {
        if (b == 0) {
            throw new DivisionByZeroException(a, b);
        }
    }
}
