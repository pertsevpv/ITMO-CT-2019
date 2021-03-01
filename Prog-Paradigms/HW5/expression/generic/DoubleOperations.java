package expression.generic;

import expression.generic.AbstractOperations;

public class DoubleOperations extends AbstractOperations<Double> {

    @Override
    Double a(Double a, Double b) {
        return a + b;
    }

    @Override
    Double s(Double a, Double b) {
        return a - b;
    }

    @Override
    Double d(Double a, Double b) {
        return a / b;
    }

    @Override
    Double m(Double a, Double b) {
        return a * b;
    }

    @Override
    Double n(Double a) {
        return -a;
    }

    @Override
    Double gn(String s) {
        return Double.parseDouble(s);
    }

    @Override
    public Double bitCount(Double a) {
        return (double) Long.bitCount(Double.doubleToLongBits(a));
    }

    @Override
    public Double max(Double a, Double b) {
        if (a.compareTo(b) > 0){
            return a;
        }else return b;
    }

    @Override
    public Double min(Double a, Double b) {
        if (a.compareTo(b) < 0){
            return a;
        }else return b;
    }
}
