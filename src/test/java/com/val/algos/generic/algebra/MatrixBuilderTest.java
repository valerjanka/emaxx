package com.val.algos.generic.algebra;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class MatrixBuilderTest {

    @Test
    public void testBuild2n2() {
        SquareMatrix matrix = MatrixBuilder.build2n2(1, 2, 3, 4);
        int[][] expected = {{1, 2}, {3, 4}};
        assertArrayEquals(expected, matrix.getMatrix());
    }
}
