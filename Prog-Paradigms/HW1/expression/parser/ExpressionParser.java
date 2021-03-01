package expression.parser;

import expression.*;
import expression.exceptions.*;
import jdk.dynalink.Operation;

import java.util.HashSet;
import java.util.Set;

public class ExpressionParser extends BaseParser {

    private static int balance;

    @Override
    public TripleExpression parse(String expression) {
        this.source = new StringSource(expression);
        balance = 0;
        nextChar();
        TripleExpression result = parseThirdPriority();
        if (balance != 0) throw new ParenthesisException(source.data, "No closing parenthesis", source.pos + 1);
        return result;
    }

    public CommonExpression parseThirdPriority() {
        CommonExpression left = parseSecondPriority();
        skipWhiteSpace();
        while (!test('\0')) {
            if (test('<') || test('>')) {
                if (test(prev)) {
                    if (prev == '<') {
                        left = new LeftShift(left, parseSecondPriority());
                        skipWhiteSpace();
                    } else {
                        left = new RightShift(left, parseSecondPriority());
                        skipWhiteSpace();
                    }
                    continue;
                } else {
                    throw new IllegalSymbolException(source.data, source.pos, cur);
                }
            }
            if (test(')')) {
                balance--;
                if (balance < 0) {
                    throw new ParenthesisException(source.data, "No opening parenthesis for parenthesis", source.pos);
                }
                return left;
            }
            throw new IllegalSymbolException(source.data, source.pos, cur);
        }
        return left;
    }

    public CommonExpression parseSecondPriority() {
        CommonExpression left = parseFirstPriority();
        skipWhiteSpace();
        while (!test('\0')) {
            if (test('+')) {
                left = new CheckedAdd(left, parseFirstPriority());
                skipWhiteSpace();
                continue;
            }
            if (test('-')) {
                left = new CheckedSubtract(left, parseFirstPriority());
                skipWhiteSpace();
                continue;
            }
            break;
        }
        return left;
    }

    public CommonExpression parseFirstPriority() {
        CommonExpression left = parseExpression();
        skipWhiteSpace();
        while (!test('\0')) {
            if (test('*')) {
                left = new CheckedMultiply(left, parseExpression());
                skipWhiteSpace();
                continue;
            }
            if (test('/')) {
                left = new CheckedDivide(left, parseExpression());
                skipWhiteSpace();
                continue;
            }
            break;
        }
        return left;
    }

    public CommonExpression parseExpression() {
        skipWhiteSpace();
        if (test('-')) {
            if (Character.isDigit(cur)) {
                return new Const(parseNumber(true));
            }
            return new CheckedNegate(parseExpression());
        }
        if (test('(')) {
            balance++;
            return parseThirdPriority();
        }
        if (Character.isDigit(cur)) {
            return new Const(parseNumber(false));
        }
        if (test('x') || test('y') || test('z')) {
            return new Variable(Character.toString(prev));
        }
        if (test('l')) {
            expect("og2");
            if (cur != '(' && cur != '-' && cur != ' ') throw new IllegalSymbolException(source.data, source.pos, cur);
            return new Log2(parseExpression());
        }
        if (test('p')) {
            expect("ow2");
            if (cur != '(' && cur != '-' && cur != ' ') throw new IllegalSymbolException(source.data, source.pos, cur);
            return new Pow2(parseExpression());
        }
        throw new IllegalSymbolException(source.data, source.pos, cur);
    }

    public Integer parseNumber(boolean isNegative) {
        StringBuilder sb = new StringBuilder(isNegative ? "-" : "");
        while (Character.isDigit(cur)) {
            sb.append(cur);
            nextChar();
        }
        try {
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            throw new OverflowException(sb.toString());
        }

    }

/*    public String format(String str) {
        StringBuilder res = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                res.append(str.charAt(i));
            }
            if (str.charAt(i) == '(') balance++;
            if (str.charAt(i) == ')') balance--;
            if (balance < 0) throw new ExpressionException("No opening parenthesis");
        }
        if (balance != 0) throw new ExpressionException("No closing parenthesis");
        return res.toString();
    }*/
}