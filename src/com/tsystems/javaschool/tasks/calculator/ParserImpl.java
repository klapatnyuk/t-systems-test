package com.tsystems.javaschool.tasks.calculator;

import com.tsystems.javaschool.tasks.calculator.exception.StatementException;
import com.tsystems.javaschool.tasks.calculator.term.Operand;
import com.tsystems.javaschool.tasks.calculator.term.Operator;
import com.tsystems.javaschool.tasks.calculator.term.Term;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static com.tsystems.javaschool.tasks.calculator.Config.*;

/**
 * Parse statement to list of expression Terms.
 *
 * @author klapatnyuk
 */
public abstract class ParserImpl {

    /**
     * Parses statement to list of expression Terms.
     *
     * @param statement
     * @return
     * @throws StatementException
     */
    public static List<Term> parse(String statement) throws StatementException {

        // remove whitespaces from original statement.
        statement = statement.replaceAll(WHITESPACES, "");
        if (statement.isEmpty()) {
            throw new StatementException("Statement is empty");
        }

        // simple validate
        if (!PATTERN_STATEMENT.matcher(statement).matches()) {
            throw new StatementException("Non expected terms found");
        }

        // parse statement
        Matcher matcher = PATTERN_PARSER.matcher(statement);
        List<Term> terms = new ArrayList<>();
        String sequence;
        List<SymbolImpl> symbols = Arrays.asList(SymbolImpl.values());
        while (matcher.find()) {
            for (SymbolImpl symbol : symbols) {
                sequence = matcher.group(symbol.getName());
                if (sequence != null) {
                    if (symbol.equals(SymbolImpl.OPERAND)) {
                        terms.add(new Operand(sequence));
                    } else if (symbol.equals(SymbolImpl.OPERATOR)) {
                        terms.add(Operator.getActionOf(sequence));
                    }
                    break;
                }
            }
        }

        return terms;
    }
}