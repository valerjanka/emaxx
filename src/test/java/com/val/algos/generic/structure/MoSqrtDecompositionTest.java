package com.val.algos.generic.structure;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author valerjanka
 */
public class MoSqrtDecompositionTest {
    @Test
    public void queryRepeatedElements() throws Exception {
        int[] values = new int[]{1, 2, 3, 1, 1, 2, 1, 2, 3, 1};
        int k = 3;
        MoSqrtDecomposition.Function repeatedFunction =
                new MoSqrtDecomposition.RepeatedFunction(values, k, 10);
        MoSqrtDecomposition algo = new MoSqrtDecomposition(repeatedFunction);
        algo.addQuery(0, 5);
        algo.addQuery(1, 9);
        Assert.assertArrayEquals(new int[]{1, 2}, algo.getAnswers());
    }

    @Test
    public void queryMostOftenElements() throws Exception {
        int[] values = new int[]{1, 2, 3, 1, 1, 2, 1, 2, 3, 1};
        MoSqrtDecomposition.Function mostOftenFunction =
                new MoSqrtDecomposition.MostOftenFunction(values);
        MoSqrtDecomposition algo = new MoSqrtDecomposition(mostOftenFunction);
        algo.addQuery(0, 5);
        algo.addQuery(1, 8);
        algo.addQuery(5, 8);
        algo.addQuery(5, 10);
        algo.addQuery(0, 1);
        Assert.assertArrayEquals(new int[]{1, 1, 2, 1, 1}, algo.getAnswers());
    }
}