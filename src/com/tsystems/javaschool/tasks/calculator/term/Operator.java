package com.tsystems.javaschool.tasks.calculator.term;

import com.tsystems.javaschool.tasks.calculator.Action;
import com.tsystems.javaschool.tasks.calculator.Symbol;
import com.tsystems.javaschool.tasks.calculator.SymbolImpl;
import com.tsystems.javaschool.tasks.calculator.exception.StatementException;

public enum Operator implements Symbol, Term {

    OPENED("(", 3),
    CLOSED(")", 3),
    MULTIPLY("*", 2, (f, s) -> f * s),
    DIVIDE("/", 2, (f, s) -> {
        if (s == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        return f / s;
    }),
    PLUS("+", 1, (f, s) -> f + s),
    MINUS("-", 1, (f, s) -> f - s);

    private final String sequence;
    private final int priority;
    private Action action;

    Operator(String sequence, int priority, Action action) {
        this.sequence = sequence;
        this.priority = priority;
        this.action = action;
    }

    Operator(String sequence, int priority) {
        this(sequence, priority, null);
    }

    public double doAction(double f, double s) {
        return this.action.doAction(f, s);
    }

    public static Operator getActionOf(String sequence) throws StatementException {
        for (Operator operator : Operator.values()) {
            if (operator.getSequence().equals(sequence)) {
                return operator;
            }
        }
        throw new StatementException("Non expected operator found");
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public String getName() {
        return this.toString();
    }

    @Override
    public String getPattern() {
        return "(?<" + getName() + ">" + getSequence() + ")";
    }

    @Override
    public String getSequence() {
        return this.sequence;
    }

    @Override
    public SymbolImpl getSymbol() {
        return SymbolImpl.OPERATOR;
    }

    @Override
    public String toString() {
        return getSequence();
    }
}