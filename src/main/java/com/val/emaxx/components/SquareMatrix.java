package com.val.emaxx.components;

/**
 * Created by vryzhuk on 3/8/2015.
 */
public class SquareMatrix {
    private int[][] matrix;

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
