package expression;

public class LeftShift extends CommonOperation {

    public LeftShift(CommonExpression l, CommonExpression r) {
        super(l, r);
        sign = " << ";
    }


    @Override
    protected int operate(int a, int b) {
        return a << b;
    }

    @Override
    protected void checkOperation(int a, int b) {

    }

}