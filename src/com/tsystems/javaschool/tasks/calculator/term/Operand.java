package com.tsystems.javaschool.tasks.calculator.term;

import com.tsystems.javaschool.tasks.calculator.SymbolImpl;

/**
 * @author vykla
 */
public class Operand implements Term {

    private final String sequence;
    private final double value;

    public Operand(final double newValue) {
        this.sequence = "" + newValue;
        this.value = newValue;
    }

    public Operand(final String newSequence) {
        this.sequence = newSequence;
        this.value = Double.parseDouble(this.sequence);
    }

    public final double getValue() {
        return this.value;
    }

    @Override
    public final SymbolImpl getSymbol() {
        return SymbolImpl.OPERAND;
    }

    @Override
    public final String getSequence() {
        return this.sequence;
    }

    @Override
    public final String toString() {
        return getSequence();
    }
}
