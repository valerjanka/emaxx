package com.val.algos.emaxx.structure;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author valerjanka
 */
public class MoSqrtDecompositionTest {
    @Test
    public void query() throws Exception {
        int[] values = new int[]{1, 2, 3, 1, 1, 2, 1, 2, 3, 1};
        int k = 3;
        MoSqrtDecomposition.Pair[] queries = new MoSqrtDecomposition.Pair[2];
        queries[0] = new MoSqrtDecomposition.Pair(0, 5);
        queries[1] = new MoSqrtDecomposition.Pair(1, 9);

        int[] result = new int[] {1, 2};

        MoSqrtDecomposition decomposition = new MoSqrtDecomposition(values, k, 10);
        Assert.assertArrayEquals(result, decomposition.query(queries));
    }

}