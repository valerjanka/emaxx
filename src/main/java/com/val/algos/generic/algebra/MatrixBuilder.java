package com.val.algos.generic.algebra;

/**
 * @author valerjanka
 */
public class MatrixBuilder {
    /**
     * Builds a 2x2 square matrix.
     * <p>
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param a11 element at 0,0
     * @param a12 element at 0,1
     * @param a21 element at 1,0
     * @param a22 element at 1,1
     * @return new 2x2 SquareMatrix
     */
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
