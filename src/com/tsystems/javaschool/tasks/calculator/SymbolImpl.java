package com.tsystems.javaschool.tasks.calculator;

/**
 * @author klapatnyuk
 */
public enum SymbolImpl implements Symbol {

    OPERAND("\\d+(?:\\.(?:\\d+)?)?"),
    OPERATOR("[*/+-]|\\(|\\)");

    private final String sequence;

    SymbolImpl(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String getPattern() {
        return "(?<" + getName() + ">" + getSequence() + ")";
    }

    @Override
    public String getName() {
        return this.toString();
    }

    @Override
    public String getSequence() {
        return this.sequence;
    }
}