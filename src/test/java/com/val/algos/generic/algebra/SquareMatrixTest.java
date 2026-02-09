package com.val.algos.generic.algebra;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SquareMatrixTest {

    @Test
    public void testInitialization() {
        SquareMatrix matrix = new SquareMatrix(3);
        int[][] data = matrix.getMatrix();
        assertEquals(3, data.length);
        assertEquals(3, data[0].length);
        for (int[] row : data) {
            for (int cell : row) {
                assertEquals(0, cell);
            }
        }
    }

    @Test
    public void testSetCell() {
        SquareMatrix matrix = new SquareMatrix(2);
        matrix.setCell(0, 1, 5);
        assertEquals(5, matrix.getMatrix()[0][1]);
        assertEquals(0, matrix.getMatrix()[0][0]);
    }
}
