package com.val.emaxx.graph;

import java.util.Iterator;
import java.util.List;

/**
 * Created by valerii.ryzhuk on 11/7/2015.
 */
public interface Graph {
    List<Integer> adj(int v);
    boolean removeAdj(int v, int w);
    void addEdge(int v, int w);
    int vertices();
    int edges();
}
