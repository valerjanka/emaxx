package com.company.components;

/**
 * Created by Luba on 3/8/2015.
 */
public class SquareMatrix {
    private int n;
    private int[][] matrix;

    public SquareMatrix(int n) {
        this.n = n;
        matrix = new int[n][n];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setCell(int row, int column, int value) {
        matrix[row][column] = value;
    }

    public void pow(int n) {

    }
}
