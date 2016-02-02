package com.tsystems.javaschool.tasks.calculator;

import java.util.regex.Pattern;

import static com.tsystems.javaschool.tasks.calculator.SymbolImpl.OPERAND;
import static com.tsystems.javaschool.tasks.calculator.SymbolImpl.OPERATOR;

/**
 * @author klapatnyuk
 */
public class Config {

    /**
     * Number of digits after dot in result string.
     */
    public static final int FRACTION = 4;

    /**
     * String pattern for replacing whitespaces in statement.
     */
    public static final String WHITESPACES = "\\s+";

    /**
     * Regular expression pattern for entire statement.
     */
    public static final Pattern PATTERN_STATEMENT = Pattern.compile("(" + OPERAND.getPattern()
            + "|" + OPERATOR.getPattern() + ")+");

    /**
     * Regular expression pattern for every term in statement.
     */
    public static final Pattern PATTERN_PARSER = Pattern.compile(OPERAND.getPattern() + "|" + OPERATOR.getPattern());
}