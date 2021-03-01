package expression.parser;

import expression.*;
import expression.exceptions.*;
import expression.exceptions.NumberFormatException;
import expression.generic.AbstractOperations;

public class ExpressionParser<T extends Number> extends BaseParser<T> {

    public ExpressionParser(AbstractOperations<T> o) {
        this.ops = o;
    }

    private int balance;
    private AbstractOperations<T> ops;

    @Override
    public TripleExpression<T> parse(String expression) {
        this.source = new StringSource(expression);
        balance = 0;
        nextChar();
        TripleExpression<T> result = parseThirdPriority();
        if (balance != 0) throw new ParenthesisException(source.data, "No closing parenthesis", source.pos + 1);
        return result;
    }

    public TripleExpression<T> parseThirdPriority() {
        TripleExpression<T> left = parseSecondPriority();
        skipWhiteSpace();
        while (!test('\0')) {
            if (test("min")) {
                left = new CheckedMin<>(left, parseSecondPriority(), ops);
                skipWhiteSpace();
                continue;
            }
            if (test("max")) {
                left = new CheckedMax<>(left, parseSecondPriority(), ops);
                skipWhiteSpace();
                continue;
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

    public TripleExpression<T> parseSecondPriority() {
        TripleExpression<T> left = parseFirstPriority();
        skipWhiteSpace();
        while (!test('\0')) {
            if (test('+')) {
                left = new CheckedAdd<>(left, parseFirstPriority(), ops);
                skipWhiteSpace();
                continue;
            }
            if (test('-')) {
                left = new CheckedSubtract<>(left, parseFirstPriority(), ops);
                skipWhiteSpace();
                continue;
            }
            break;
        }
        return left;
    }

    public TripleExpression<T> parseFirstPriority() {
        TripleExpression<T> left = parseExpression();
        skipWhiteSpace();
        while (!test('\0')) {
            if (test('*')) {
                left = new CheckedMultiply<>(left, parseExpression(), ops);
                skipWhiteSpace();
                continue;
            }
            if (test('/')) {
                left = new CheckedDivide<>(left, parseExpression(), ops);
                skipWhiteSpace();
                continue;
            }
            break;
        }
        return left;
    }

    private TripleExpression<T> parseExpression() {
        skipWhiteSpace();
        if (test('-')) {
            if (Character.isDigit(cur)) {
                return new Const<>(parseNumber(true));
            }
            return new CheckedNegate<>(parseExpression(), ops);
        }
        if (test('(')) {
            balance++;
            return parseThirdPriority();
        }
        if (Character.isDigit(cur)) {
            return new Const<>(parseNumber(false));
        }
        if (test('x') || test('y') || test('z')) {
            return new Variable<>(String.valueOf(prev));
        }
        if (test("count")) {
            return new CheckedCount<>(parseExpression(), ops);
        }
        throw new IllegalSymbolException(source.data, source.pos, cur);
    }

    public T parseNumber(boolean isNegative) {
        StringBuilder sb = new StringBuilder(isNegative ? "-" : "");
        while (Character.isDigit(cur) || test('.') || test('e') || test('E')) {
            sb.append(cur);
            nextChar();
        }
        try {
            return ops.getNum(sb.toString());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(sb.toString());
        }

    }
}
