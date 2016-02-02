package com.tsystems.javaschool.tasks;

import java.util.Iterator;
import java.util.List;

/**
 * Check if first list could be retrieved by removing elements from second list.
 *
 * @author klapatnyuk
 */
public class SubsequenceImpl implements Subsequence {

    @Override
    public boolean find(List first, List second) {
        if (first.isEmpty()) {
            return true;
        }
        Iterator find = first.iterator();
        Iterator check = second.iterator();
        Object required = find.next();
        while (check.hasNext()) {
            if (required.equals(check.next())) {
                if (find.hasNext()) {
                    required = find.next();
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}