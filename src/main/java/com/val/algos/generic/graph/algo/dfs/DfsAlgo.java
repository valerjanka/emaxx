package com.val.algos.generic.graph.algo.dfs;


/**
 * @author valerjanka
 */
public interface DfsAlgo {
    boolean hasNextStep();

    int vertices();

    int getStart();

    boolean isCurrentVisited();

    void handleProcessedVertex();

    void handleNewVertex();

    int stepToNextVertex();

    void pushAdjToVisit();
}
