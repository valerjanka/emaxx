package com.company.graph.algo.dfs;

import java.util.Iterator;

/**
 * Created by valerii.ryzhuk on 11/15/2015.
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
