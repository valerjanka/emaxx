package com.val.algos.generic.graph;

/**
 * @author valerjanka
 */
public class UnDiGraphImpl extends AbstractGraph implements UnDiGraph {
    private int edges;

    public UnDiGraphImpl(int vertices) {
        super(vertices);
    }


    @Override
    public void addEdge(int v, int w) {
        verticesToAdjacencyList[v].add(w);
        verticesToAdjacencyList[w].add(v);
        edges++;
    }

    @Override
    public boolean removeAdj(int v, int w) {
        verticesToAdjacencyList[v].removeFirstOccurrence(w);
        return verticesToAdjacencyList[w].removeFirstOccurrence(v);
    }

    @Override
    public int edges() {
        return edges;
    }

    @Override
    public int degree(int v) {
        return verticesToAdjacencyList[v].size();
    }
}
