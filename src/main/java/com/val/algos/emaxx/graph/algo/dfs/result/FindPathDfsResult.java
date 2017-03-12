package com.val.algos.emaxx.graph.algo.dfs.result;

import java.util.List;

/**
 * @author valerjanka
 */
public interface FindPathDfsResult {
    int[] getParentIds();
    List<Integer> getPostOrder();
    List<Integer> getPath();
}
