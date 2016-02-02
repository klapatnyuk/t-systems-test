package com.tsystems.javaschool.tasks.calculator.rpn;

import com.tsystems.javaschool.tasks.calculator.exception.BracketException;
import com.tsystems.javaschool.tasks.calculator.exception.TermException;
import com.tsystems.javaschool.tasks.calculator.term.Operand;
import com.tsystems.javaschool.tasks.calculator.term.Operator;
import com.tsystems.javaschool.tasks.calculator.term.Term;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Reverse Polish Notation converter.
 *
 * @author klapatnyuk
 */
public abstract class RPNConverter {

    /**
     * Converts list of Terms to Reverse Polish Notation order.
     *
     * @param terms
     * @return
     * @throws TermException
     * @throws BracketException
     */
    public static List<Term> convert(List<Term> terms)
            throws TermException, BracketException {
        if (terms.isEmpty()) {
            throw new TermException("List of terms is empty.");
        }

        Stack<Term> stack = new Stack<>();
        List<Term> result = new ArrayList<>();
        for (Term term : terms) {
            if (term instanceof Operand) {
                result.add(term);
            } else if (term instanceof Operator) {
                if (term == Operator.CLOSED) {
                    boolean found = false;
                    while (!stack.isEmpty() && !found) {
                        Term stackTerm = stack.pop();
                        if (stackTerm instanceof Operand) {
                            result.add(stackTerm);
                        } else if (stackTerm instanceof Operator) {
                            Operator stackOperator = (Operator) stackTerm;
                            if (stackOperator == Operator.OPENED) {
                                found = true;
                            } else {
                                result.add(stackTerm);
                            }
                        } else {
                            throw new TermException("Non expected Term's symbol.");
                        }
                    }
                    if (!found) {
                        throw new BracketException("Brackets inconsistent");
                    }
                } else if (term == Operator.OPENED) {
                    stack.push(term);
                } else {
                    while (!stack.empty()
                            && stack.peek() instanceof Operator
                            && stack.peek() != Operator.OPENED
                            && ((Operator) stack.peek()).getPriority()
                            >= ((Operator) term).getPriority()) {
                        result.add(stack.pop());
                    }
                    stack.push(term);
                }
            } else {
                throw new TermException("Non expected Term's symbol.");
            }
        }
        while (!stack.empty()) {
            Term term = stack.pop();
            if (term instanceof Operator) {
                result.add(term);
            } else {
                throw new BracketException("Brackets inconsistent");
            }
        }
        return result;
    }
}