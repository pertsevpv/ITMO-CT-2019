package expression.parser;

public class StringSource implements ExpressionSource {

    protected final String data;
    protected int pos;

    public StringSource(String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

}
