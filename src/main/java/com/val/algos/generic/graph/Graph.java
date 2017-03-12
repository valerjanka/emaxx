package com.val.algos.generic.graph;

import java.util.List;

/**
 * @author valerjanka
 */
public interface Graph {
    List<Integer> adj(int v);
    boolean removeAdj(int v, int w);
    void addEdge(int v, int w);
    int vertices();
    int edges();
}
