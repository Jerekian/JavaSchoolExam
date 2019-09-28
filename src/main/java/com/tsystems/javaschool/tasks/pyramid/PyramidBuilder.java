package com.tsystems.javaschool.tasks.pyramid;

import java.util.Comparator;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // TODO : Implement your solution here

        int n = isPossibleBuildPyramid(inputNumbers.size());

        if (inputNumbers.size() != n * (n + 1) / 2) throw new CannotBuildPyramidException();

        return buildPyramid(inputNumbers, n);
    }

    private int[][] buildPyramid(List<Integer> inputNumbers, int n) {
        int[][] pyramid = new int[n][2 * n - 1];
        if (inputNumbers.contains(null)) throw new CannotBuildPyramidException();
        inputNumbers.sort(Comparator.comparingInt(o -> o));

        int numberIndex = 0;
        for (int i = 0, numbersOnLine = 1; i < pyramid.length; i++, numbersOnLine++) {
            for (int j = n - i - 1, count = 0; count < numbersOnLine; count++, j += 2) {
                Integer number = inputNumbers.get(numberIndex);
                numberIndex++;
                pyramid[i][j] = number;
            }
        }
        return pyramid;
    }

    private int isPossibleBuildPyramid(int size) {
        /*
        size = n*(n+1)/2;
        n^2 + n - 2*size = 0;
        D = 1^2 - 4*1*(-2*size);
        n1 = sqrt(-1 + (1 + 8*size))/2
        n2 < 0;
         */
        return (int) (Math.sqrt(1 + 8 * size) - 1) / 2;
    }

}












