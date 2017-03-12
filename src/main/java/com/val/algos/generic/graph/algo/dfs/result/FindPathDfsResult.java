package com.val.algos.generic.graph.algo.dfs.result;

import java.util.List;

/**
 * @author valerjanka
 */
public interface FindPathDfsResult {
    int[] getParentIds();
    List<Integer> getPostOrder();
    List<Integer> getPath();
}
