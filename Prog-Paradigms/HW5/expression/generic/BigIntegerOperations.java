package expression.generic;

import expression.exceptions.DivisionByZeroException;
import expression.generic.AbstractOperations;

import java.math.BigInteger;

public class BigIntegerOperations extends AbstractOperations<BigInteger> {

    @Override
    public BigInteger a(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger s(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger d(BigInteger a, BigInteger b) {
        return a.divide(b);
    }

    @Override
    public BigInteger m(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    BigInteger n(BigInteger a) {
        return a.multiply(new BigInteger("-1"));
    }

    @Override
    BigInteger gn(String s) {
        return new BigInteger(s);
    }

    @Override
    public void checkDiv(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException(a, b);
        }
    }

    @Override
    public BigInteger bitCount(BigInteger a) {
        return BigInteger.valueOf(a.bitCount());
    }

    @Override
    public BigInteger max(BigInteger a, BigInteger b) {
        if (a.compareTo(b) > 0){
            return a;
        }else return b;
    }

    @Override
    public BigInteger min(BigInteger a, BigInteger b) {
        if (a.compareTo(b) < 0){
            return a;
        }else return b;
    }

}
