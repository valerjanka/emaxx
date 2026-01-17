package com.val.algos.generic.structure;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

/**
 * @author valerjanka
 */
public class MoSqrtDecompositionTest {
    @Test
    public void queryRepeatedElements() throws Exception {
        int[] values = new int[]{1, 2, 3, 1, 1, 2, 1, 2, 3, 1};
        int k = 3;
        MoSqrtDecomposition.Function<Integer> repeatedFunction =
                new MoSqrtDecomposition.RepeatedFunction(values, k, 10);
        MoSqrtDecomposition<Integer> algo = new MoSqrtDecomposition<>(repeatedFunction);
        algo.addQuery(0, 5);
        algo.addQuery(1, 9);
        List<Integer> result = algo.getAnswers();
        Assert.assertEquals(Arrays.asList(1, 2), result);
    }

    @Test
    public void queryMostOftenElements() throws Exception {
        int[] values = new int[]{1, 2, 3, 1, 1, 2, 1, 2, 3, 1};
        MoSqrtDecomposition.Function<Integer> mostOftenFunction =
                new MoSqrtDecomposition.MostOftenFunction(values);
        MoSqrtDecomposition<Integer> algo = new MoSqrtDecomposition<>(mostOftenFunction);
        algo.addQuery(0, 5);
        algo.addQuery(1, 8);
        algo.addQuery(5, 8); // 2, 1, 2, 3. 2:2, 1:1, 3:1. Max freq 2 (val 2). Expected 2.
        algo.addQuery(5, 10); // 2, 1, 2, 3, 1. 2:2, 1:2, 3:1. Max freq 2. Vals 1, 2. Pick 1. Expected 1.
        algo.addQuery(0, 1); // 1, 2. 1:1, 2:1. Pick 1. Expected 1.

        List<Integer> result = algo.getAnswers();
        Assert.assertEquals(Arrays.asList(1, 1, 2, 1, 1), result);
    }
}
