package com.tsystems.javaschool.tasks.calculator;

import com.tsystems.javaschool.tasks.calculator.term.Term;

import java.util.List;

/**
 * @author klapatnyuk
 */
public interface Parser {

    List<Term> parse(String statement) throws InstantiationException, IllegalAccessException;
}
