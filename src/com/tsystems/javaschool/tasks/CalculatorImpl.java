package com.tsystems.javaschool.tasks;

import com.tsystems.javaschool.tasks.calculator.Config;
import com.tsystems.javaschool.tasks.calculator.ParserImpl;
import com.tsystems.javaschool.tasks.calculator.exception.BracketException;
import com.tsystems.javaschool.tasks.calculator.exception.OrderException;
import com.tsystems.javaschool.tasks.calculator.exception.StatementException;
import com.tsystems.javaschool.tasks.calculator.exception.TermException;
import com.tsystems.javaschool.tasks.calculator.rpn.RPNCalculator;
import com.tsystems.javaschool.tasks.calculator.rpn.RPNConverter;
import com.tsystems.javaschool.tasks.calculator.term.Term;

import java.util.List;

/**
 * Calculate value of arithmetic expression.
 *
 * @author klapatnyuk
 */
public class CalculatorImpl implements Calculator {

    @Override
    public String evaluate(String statement) {

        // Parse statement.
        List<Term> terms;
        try {
            terms = ParserImpl.parse(statement);
        } catch (StatementException exception) {
            exception.printStackTrace();
            return null;
        }

        // Convert statement to "Reverse Polish Notation" terms list.
        try {
            terms = RPNConverter.convert(terms);
        } catch (TermException
                | BracketException exception) {
            exception.printStackTrace();
            return null;
        }

        // Calculate double result.
        double result;
        try {
            result = RPNCalculator.calculate(terms);
        } catch (OrderException | TermException | ArithmeticException exception) {
            exception.printStackTrace();
            return null;
        }

        // Return formatted string.
        String formatted;
        if (result == (long) result) {
            formatted = String.format("%d", (long) result);
        } else {
            formatted = String.format("%." + Config.FRACTION + "f", result);
        }
        return formatted;
    }
}