package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        // TODO: Implement the logic here

        if (x == null || y == null) throw new IllegalArgumentException();

        if (x.isEmpty()) return true;
        else if (y.isEmpty()) return false;

        if (x.size() > y.size()) return false;

        for (int yIndex = 0, xIndex = 0; yIndex < y.size(); yIndex++) {

            if (y.get(yIndex).equals(x.get(xIndex))) {
                xIndex++;
                if (xIndex == x.size()) return true;
            }

        }

        return false;

    }

}
