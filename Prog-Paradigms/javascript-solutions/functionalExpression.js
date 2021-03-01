"use strict";

function AbstractExpression(f) {
    return (x, y, z) => f(x, y, z);
}

function cnst(value) {
    return (x, y, z) => (value);
}

let pi = cnst(Math.PI);
let e = cnst(Math.E);

function variable(value) {
    let ind = value.charCodeAt(0) - 'x'.charCodeAt(0);
    return AbstractExpression(function (x, y, z) {
        return arguments[ind];
    });
}

function AbstractUnOperation(f) {
    return (exp) => (x, y, z) => f(exp(x, y, z));
}

let negate = AbstractUnOperation(function (exp) {
    return -exp;
});

let sin = AbstractUnOperation(function (exp) {
    return Math.sin(exp);
});

let cos = AbstractUnOperation(function (exp) {
    return Math.cos(-exp);
});

let cube = AbstractUnOperation(function (exp) {
    return exp * exp * exp;
});

let cuberoot = AbstractUnOperation(function (exp) {
    return Math.cbrt(exp);
});

function AbstractBinOperation(f) {
    return (left, right) => (x, y, z) => f(left(x, y, z), right(x, y, z));
}

let add = AbstractBinOperation(function (left, right) {
    return left + right;
});

let divide = AbstractBinOperation(function (left, right) {
    return left / right;
});

let multiply = AbstractBinOperation(function (left, right) {
    return left * right;
});

let subtract = AbstractBinOperation(function (left, right) {
    return left - right;
});
