package expression.generic;

import expression.TripleExpression;
import expression.exceptions.CalculateException;
import expression.exceptions.UnknownOperationType;
import expression.parser.ExpressionParser;

public class GenericTabulator implements Tabulator {

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        AbstractOperations<?> o = null;
        switch (mode) {
            case "i":
                o = new IntegerOperations();
                break;
            case "d":
                o = new DoubleOperations();
                break;
            case "bi":
                o = new BigIntegerOperations();
                break;
            case "l":
                o = new LongOperations();
                break;
            case "s":
                o = new ShortOperations();
                break;
            default:
                throw new UnknownOperationType(mode);
        }
        return tabulate(o, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T extends Number> Object[][][] tabulate(AbstractOperations<T> ops, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        Object[][][] result =  new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        TripleExpression<T> exp = new ExpressionParser<>(ops).parse(expression);
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        result[i - x1][j - y1][k - z1] = exp.evaluate(ops.getNum(Integer.toString(i)), ops.getNum(Integer.toString(j)), ops.getNum(Integer.toString(k)));
                    } catch (CalculateException e) {
                        result[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return result;
    }
}