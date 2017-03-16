package com.val.algos.generic.components;

/**
 * @author valerjanka
 */
public class MatrixBuilder {
    public static SquareMatrix build2n2(int a11, int a12, int a21, int a22) {
        SquareMatrix squareMatrix = new SquareMatrix(2);
        int[][] matrix = squareMatrix.getMatrix();
        matrix[0][0] = a11;
        matrix[0][1] = a12;
        matrix[1][0] = a21;
        matrix[1][1] = a22;
        return squareMatrix;
    }
}
