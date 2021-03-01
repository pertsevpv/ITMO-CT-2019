package expression.parser;

import expression.TripleExpression;
import expression.exceptions.IncorrectSymbolException;

public class BaseParser implements Parser {

    public StringSource source;
    protected char cur;
    protected char prev;

    public BaseParser() {

    }

    private BaseParser(StringSource source) {
        this.source = source;
    }

    protected void nextChar() {
        prev = cur;
        cur = source.hasNext() ? source.next() : '\0';
    }

    protected boolean test(char expected) {
        if (cur == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected void expect(final char c) {
        if (cur != c) {
            throw new IncorrectSymbolException(source.data, source.pos, c, cur);
        }
        nextChar();
    }

    protected void expect(final String value) {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    @Override
    public TripleExpression parse(String expression) {
        return null;
    }

    public void skipWhiteSpace() {
        while (true) {
            if (Character.isWhitespace(cur)) nextChar();
            else return;
        }
    }
}
