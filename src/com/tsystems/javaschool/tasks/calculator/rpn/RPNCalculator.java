package com.tsystems.javaschool.tasks.calculator.rpn;

import com.tsystems.javaschool.tasks.calculator.exception.OrderException;
import com.tsystems.javaschool.tasks.calculator.exception.TermException;
import com.tsystems.javaschool.tasks.calculator.term.Operand;
import com.tsystems.javaschool.tasks.calculator.term.Operator;
import com.tsystems.javaschool.tasks.calculator.term.Term;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Reverse Polish Notation calculator.
 *
 * @author klapatnyuk
 */
public class RPNCalculator {

    /**
     * Calculates value of statement according to Reverse Polish Notation.
     *
     * @param terms List of Terms in RPN order.
     * @return Result value of arithmetical statement.
     * @throws OrderException
     * @throws TermException
     */
    public static double calculate(List<Term> terms) throws OrderException, TermException, ArithmeticException {
        double result = 0;

        if (terms.size() == 1) {
            if (terms.get(0) instanceof Operand) {
                return ((Operand) terms.get(0)).getValue();
            } else {
                throw new OrderException("Wrong terms order.");
            }
        }
        if (terms.size() == 2) {
            throw new TermException("Wrong terms count.");
        }

        Term first;
        Term second;
        Term operator;
        LinkedList<Term> list = new LinkedList<>();
        list.addAll(terms);
        ListIterator<Term> iterator = list.listIterator(1);
        while (iterator.hasNext()) {
            operator = iterator.next();
            if (operator instanceof Operator) {
                iterator.previous();
                if (iterator.hasPrevious()) {
                    second = iterator.previous();
                    if (!(second instanceof Operand)) {
                        throw new TermException("Wrong terms type.");
                    }
                } else {
                    throw new OrderException("Wrong terms order.");
                }
                if (iterator.hasPrevious()) {
                    first = iterator.previous();
                    if (!(first instanceof Operand)) {
                        throw new TermException("Wrong terms type.");
                    }
                } else {
                    throw new OrderException("Wrong terms order.");
                }
                result = ((Operator) operator).doAction(((Operand) first).getValue(), ((Operand) second).getValue());
                list.remove(second);
                list.remove(operator);
                operator = new Operand(result);
                list.set(list.indexOf(first), operator);
                iterator = list.listIterator(list.indexOf(operator));
            }
        }

        return result;
    }
}
