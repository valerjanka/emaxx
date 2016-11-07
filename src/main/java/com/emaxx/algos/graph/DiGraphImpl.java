package com.emaxx.algos.graph;

/**
 * Created by valerii.ryzhuk on 11/7/2015.
 */
public class DiGraphImpl extends AbstractGraph implements DiGraph {
    int edges = 0;

    public DiGraphImpl(int vertices) {
        super(vertices);
    }

    @Override
    public DiGraph reverse() {
        DiGraph reverse = new DiGraphImpl(vertices());
        for(int v = 0; v < vertices(); v++) {
            for(int w : verticesToAdjacencyList[v]) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    @Override
    public void addEdge(int v, int w) {
        verticesToAdjacencyList[v].add(w);
        ++edges;
    }

    @Override
    public boolean removeAdj(int v, int w) {
        return verticesToAdjacencyList[v].removeFirstOccurrence(w);
    }

    @Override
    public int edges() {
        return edges;
    }
}
