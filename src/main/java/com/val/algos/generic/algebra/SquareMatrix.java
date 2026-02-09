package com.val.algos.generic.algebra;

/**
 * @author valerjanka
 */
public class SquareMatrix {
    private final int[][] matrix;

    /**
     * Creates a square matrix of size n x n.
     * <p>
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     *
     * @param n size of the matrix
     */
    public SquareMatrix(int n) {
        matrix = new int[n][n];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setCell(int row, int column, int value) {
        matrix[row][column] = value;
    }
}
