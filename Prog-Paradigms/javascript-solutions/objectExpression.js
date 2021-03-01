"use strict";

const add = (a, b) => a + b;
const sub = (a, b) => a - b;
const mul = (a, b) => a * b;
const div = (a, b) => a / b;

const neg = (exp) => -exp;
const atan = (exp) => Math.atan(exp);
const ex = (exp) => Math.exp(exp);

const min = (a, b) => Math.min(a, b);
const max = (a, b) => Math.max(a, b);

function makeExp(constructor, evaluate, toString, prefix) {
    constructor.prototype.evaluate = evaluate;
    constructor.prototype.toString = toString;
    constructor.prototype.prefix = prefix;
}

function makeOp(operate, sign, argNum) {
    let constructor = function (...args) {
        AbstractAnyOperation.call(this, ...args);
    };
    constructor.prototype = Object.create(AbstractAnyOperation.prototype);
    constructor.prototype.operate = operate;
    constructor.prototype.sign = sign;
    constructor.prototype.argNum = argNum;
    return constructor;
}

function Const(val) {
    this.value = val;
}

makeExp(
    Const,
    function () {
        return this.value;
    },
    function () {
        return this.value.toString();
    },
    function () {
        return this.value.toString();
    }
);

function Variable(val) {
    this.value = val;
    this.ind = this.value.charCodeAt(0) - 'x'.charCodeAt(0);
}

makeExp(
    Variable,
    function (x, y, z) {
        return arguments[this.ind]
    },
    function () {
        return this.value.toString();
    },
    function () {
        return this.value.toString();
    }
);

function AbstractAnyOperation(...args) {
    if (args.length > 0)
        if (args[0].constructor === Array)
            this.argList = args[0];
        else
            this.argList = args;
}

makeExp(
    AbstractAnyOperation,
    function (x, y, z) {
        if (this.argNum === 1) return this.operate(this.argList[0].evaluate(x, y, z));
        let result = this.argList[0].evaluate(x, y, z);
        for (let i = 1; i < this.argNum; i++) {
            result = this.operate(result, this.argList[i].evaluate(x, y, z));
        }
        return result;
    },
    function () {
        let result = "";
        for (let i = 0; i < this.argNum; i++) {
            result += this.argList[i] + " ";
        }
        result += this.sign;
        return result;
    },
    function () {
        let result = "(" + this.sign;
        for (let i = 0; i < this.argNum; i++) {
            result += " " + this.argList[i].prefix();
        }
        result += ")";
        return result;
    }, true
);

const Add = makeOp(add, "+", 2);

const Subtract = makeOp(sub, "-", 2);

const Multiply = makeOp(mul, "*", 2);

const Divide = makeOp(div, "/", 2);

const Negate = makeOp(neg, "negate", 1);

const Min3 = makeOp(min, "min3", 3);

const Max5 = makeOp(max, "max5", 5);

const ArcTan = makeOp(atan, "atan", 1);

const Exp = makeOp(ex, "exp", 1);

//HW8

const errors = (() => {

    function BaseParseError(src, mes) {
        this.message = (mes + " at pos " + (src.pos - 1) + "\n" + src.data + "\n" + " ".repeat(src.pos - 1) + "^")
    }

    BaseParseError.prototype = Object.create(Error.prototype);
    BaseParseError.prototype.constructor = BaseParseError;

    function IllegalSymbolError(exc, ill) {
        BaseParseError.call(this, exc, "Illegal symbol '" + ill + "'");
    }

    IllegalSymbolError.prototype = Object.create(BaseParseError.prototype);
    IllegalSymbolError.prototype.constructor = IllegalSymbolError;

    function IncorrectSymbolError(exc, exp, get) {
        BaseParseError.call(this, exc, "Incorrect symbol: expected: '" + exp + "', get: '" + get + "'");
    }

    IncorrectSymbolError.prototype = Object.create(BaseParseError.prototype);
    IncorrectSymbolError.prototype.constructor = IncorrectSymbolError;

    function UnknownOperationError(exc, un) {
        BaseParseError.call(this, exc, "Unknown operation: " + un)
    }

    UnknownOperationError.prototype = Object.create(BaseParseError.prototype);
    UnknownOperationError.prototype.constructor = UnknownOperationError;

    function NoOpeningParenthesisError(exc) {
        BaseParseError.call(this, exc, "No opening parenthesis for parenthesis");
    }

    NoOpeningParenthesisError.prototype = Object.create(BaseParseError.prototype);
    NoOpeningParenthesisError.prototype.constructor = NoOpeningParenthesisError;

    function EmptyInputError() {
        this.message = "Empty input";
    }

    EmptyInputError.prototype = Object.create(Error.prototype);
    EmptyInputError.prototype.constructor = EmptyInputError;

    function EmptyOperationError(exc) {
        exc.pos--;
        BaseParseError.call(this, exc, "Empty Operation");
    }

    EmptyOperationError.prototype = Object.create(BaseParseError.prototype);
    EmptyOperationError.prototype.constructor = EmptyOperationError;

    function WrongNumOfArgs(exc, exp, get) {
        exc.pos--;
        BaseParseError.call(this, exc, "Wrong number of args: " + exp + ", get " + get);
    }

    WrongNumOfArgs.prototype = Object.create(BaseParseError.prototype);
    WrongNumOfArgs.prototype.constructor = WrongNumOfArgs;

    return {
        IllegalSymbolError: IllegalSymbolError,
        IncorrectSymbolError: IncorrectSymbolError,
        UnknownOperationError: UnknownOperationError,
        NoOpeningParenthesisError: NoOpeningParenthesisError,
        EmptyInputError: EmptyInputError,
        EmptyOperationError: EmptyOperationError,
        WrongNumOfArgs: WrongNumOfArgs
    }
})();


function Source(d) {
    this.data = d;
    this.pos = 0;
    this.hasNext = function () {
        return this.pos < this.data.length;
    };
    this.next = function () {
        return this.data.charAt(this.pos++);
    }
}

const operationMap = {
    "+": Add,
    "-": Subtract,
    "*": Multiply,
    "/": Divide,
    "negate": Negate,
    "atan": ArcTan,
    "exp": Exp
};

const digits = new RegExp("[0-9]");
const vars = new RegExp("[x-z]");
const signs = new RegExp("[+\\-*/a-z]")

function Parser(src) {
    this.source = src;
    this.cur = '\0';
    this.nextChar = function () {
        this.cur = this.source.hasNext() ? this.source.next() : '\0';
    };
    this.test = function (expected) {
        if (this.cur === expected) {
            this.nextChar();
            return true;
        }
        return false;
    };
    this.expect = function (expected) {
        for (let i = 0; i < expected.length; i++) {
            if (this.cur !== expected.charAt(i)) {
                throw new errors.IncorrectSymbolError(this.source, expected.charAt(i), this.cur);
            }
            this.nextChar();
        }
    };
    this.skipWhiteSpace = function () {
        while (true) {
            if (this.cur === " ") this.nextChar();
            else return;
        }
    };
    this.parsePrefix = function () {
        this.nextChar();
        if (this.cur === '\0') throw new errors.EmptyInputError();
        const result = this.parseExpression();
        this.skipWhiteSpace();
        if (this.cur !== '\0') throw new errors.IllegalSymbolError(this.source, this.cur);
        return result;
    };


    this.parseOperation = function () {
        this.skipWhiteSpace();
        if (this.test(")")) throw new errors.EmptyOperationError(this.source);
        let exp = this.getOperation();
        let argList = [];
        while (!this.test(")")) {
            argList.push(this.parseExpression());
            this.skipWhiteSpace();
        }
        this.skipWhiteSpace();
        exp.constructor(argList);
        if (exp.argNum !== argList.length) throw new errors.WrongNumOfArgs(this.source, exp.argNum, argList.length);
        return exp;
    };

    this.parseExpression = function () {
        this.skipWhiteSpace();
        if (this.test("-")) {
            return new Const(this.parseNumber(true));
        }
        if (this.test("(")) {
            return this.parseOperation();
        }
        if (digits.test(this.cur)) {
            return new Const(this.parseNumber(false));
        }
        if (vars.test(this.cur)) {
            let tmp = new Variable(this.cur);
            this.nextChar();
            return tmp;
        }
        throw new errors.IllegalSymbolError(this.source, this.cur);
    };

    this.parseNumber = function (isNegative) {
        let result = isNegative ? "-" : "";
        while (digits.test(this.cur)) {
            result += this.cur;
            this.nextChar();
        }
        this.skipWhiteSpace();
        return parseInt(result);
    };

    this.getOperation = function () {
        let op = "";
        while (signs.test(this.cur)) {
            op += this.cur;
            this.nextChar();
        }
        if (this.cur !== " " && this.cur !== "(") throw new errors.IllegalSymbolError(this.source, this.cur);
        if (operationMap[op] !== undefined)
            return new operationMap[op];
        else
            throw new errors.UnknownOperationError(this.source, this.cur);
    }
}

function parsePrefix(string) {
    const src = new Source(string);
    const prs = new Parser(src);
    return prs.parsePrefix();
}
