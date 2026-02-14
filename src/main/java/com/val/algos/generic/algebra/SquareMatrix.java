package com.val.algos.generic.algebra;

/**
 * @author valerjanka
 */
public class SquareMatrix {
    private final int[][] matrix;

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
