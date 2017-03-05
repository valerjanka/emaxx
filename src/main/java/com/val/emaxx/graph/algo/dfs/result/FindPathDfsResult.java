package com.val.emaxx.graph.algo.dfs.result;

import java.util.List;

/**
 * Created by valerii.ryzhuk on 11/18/2015.
 */
public interface FindPathDfsResult {
    int[] getParentIds();
    List<Integer> getPostOrder();
    List<Integer> getPath();
}
