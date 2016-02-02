package com.tsystems.javaschool.tasks.calculator.term;

import com.tsystems.javaschool.tasks.calculator.SymbolImpl;

/**
 * @author vykla
 */
public class Operand implements Term {

    private final String sequence;
    private final double value;

    public Operand(double value) {
        this.sequence = "" + value;
        this.value = value;
    }

    public Operand(String sequence) {
        this.sequence = sequence;
        this.value = Double.parseDouble(this.sequence);
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public SymbolImpl getSymbol() {
        return SymbolImpl.OPERAND;
    }

    @Override
    public String getSequence() {
        return this.sequence;
    }

    @Override
    public String toString() {
        return getSequence();
    }
}