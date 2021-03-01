package expression.generic;

import expression.exceptions.DivisionByZeroException;

public class ShortOperations extends AbstractOperations<Short> {

    @Override
    Short a(Short a, Short b) {
        return (short) (a + b);
    }

    @Override
    Short s(Short a, Short b) {
        return (short) (a - b);
    }

    @Override
    Short d(Short a, Short b) {
        return (short) (a / b);
    }

    @Override
    Short m(Short a, Short b) {
        return (short) (a * b);
    }

    @Override
    Short n(Short a){
        return (short) (-a);
    }

    @Override
    Short gn(String s) {
        return (short) Integer.parseInt(s);
    }

    @Override
    public void checkDiv(Short a, Short b) {
        if (b == 0) {
            throw new DivisionByZeroException(a, b);
        }
    }

    @Override
    public Short bitCount(Short a) {
        return null;
    }

    @Override
    public Short max(Short a, Short b) {
        return null;
    }

    @Override
    public Short min(Short a, Short b) {
        return null;
    }
}
