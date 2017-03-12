package com.val.algos.generic.algebra;

import com.val.algos.generic.components.MatrixBuilder;
import com.val.algos.generic.components.SquareMatrix;

/**
 * @author valerjanka
 */
public class Fibonacci {
    /**
     * F0 = 0; F1 = 1; F2 = 1; F3 = 2
     * Fn = Fn-1 + Fn-2
     * F2n = Fn*(Fn+1 + Fn-1)
     * <p/>
     * Calculate Fn via fast binary calculation
     * Implements next logic: (F0 F1) * (0 1, 1 1)^n = (Fn-1 Fn)
     *
     * @param n the number of Fibonacci number
     * @return nth value of Fibonacci number using fast binary exponentiation
     */
    public static int binary(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        BinaryUtils.BinaryStepAction<SquareMatrix> action = new BinaryUtils.BinaryStepAction<SquareMatrix>() {
            @Override
            public SquareMatrix nextNumber(SquareMatrix number) {
                int[][] matrix = number.getMatrix();
                int a11 = matrix[0][0] * matrix[0][0] + matrix[0][1] * matrix[1][0];
                int a12 = matrix[0][0] * matrix[0][1] + matrix[0][1] * matrix[1][1];
                int a21 = matrix[1][0] * matrix[0][0] + matrix[1][1] * matrix[0][1];
                matrix[1][1] = matrix[1][0] * matrix[0][1] + matrix[1][1] * matrix[1][1];
                matrix[0][0] = a11;
                matrix[0][1] = a12;
                matrix[1][0] = a21;
                return number;
            }

            @Override
            public SquareMatrix operator(SquareMatrix result, SquareMatrix number) {
                int[][] res = result.getMatrix();
                int[][] num = number.getMatrix();
                int a11 = res[0][0] * num[0][0] + res[0][1] * num[1][0];
                int a12 = res[0][0] * num[0][1] + res[0][1] * num[1][1];
                int a21 = res[1][0] * num[0][0] + res[1][1] * num[0][1];
                res[1][1] = res[1][0] * num[0][1] + res[1][1] * num[1][1];
                res[0][0] = a11;
                res[0][1] = a12;
                res[1][0] = a21;
                return result;
            }

            @Override
            public SquareMatrix zeroResult() {
                return MatrixBuilder.build2n2(1, 0, 0, 1);
            }
        };
        SquareMatrix matrix = MatrixBuilder.build2n2(0, 1, 1, 1);
        matrix = BinaryUtils.binary(matrix, n-2, action);
        return matrix.getMatrix()[0][1] + matrix.getMatrix()[1][1];
    }
}
