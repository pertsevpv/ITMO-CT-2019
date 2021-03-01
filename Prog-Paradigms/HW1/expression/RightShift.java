package expression;

public class RightShift extends CommonOperation {

    public RightShift(CommonExpression l, CommonExpression r) {
        super(l, r);
        sign = " >> ";
    }

    @Override
    protected int operate(int a, int b) {
        return a >> b;
    }

    @Override
    protected void checkOperation(int a, int b) {

    }

}
