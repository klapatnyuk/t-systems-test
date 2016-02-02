package com.tsystems.javaschool.tasks.calculator.term;

import com.tsystems.javaschool.tasks.calculator.SymbolImpl;

/**
 * @author klapatnyuk
 */
public interface Term {

    SymbolImpl getSymbol();

    String getSequence();
}