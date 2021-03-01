package expression;

public class Variable extends CommonExpression {

    private String var;

    public Variable(String s) {
        this.var = s;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (var) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        throw new IllegalStateException("Unknown variable");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (this.getClass() == obj.getClass()) {
                return var.equals(((Variable) obj).var);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }

    @Override
    public String toString() {
        return var;
    }

}