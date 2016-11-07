package com.emaxx.algos.graph.algo.dfs;

/**
 * Stateful entity that holds graph and state and knows what to do while doing dfs.
 * Includes all steps that performs in Depth First Search algorithms:
 * Steps:
 * <pre>
 * {@code
 * while ( dfsAlgo.hasNextStep() )
 * {
 *      dfsAlgo.stepToNextVertex();
 *      if ( dfsAlgo.isCurrentVisited() )
 *      {
 *          dfsAlgo.handleProcessedVertex();
 *      }
 *      else
 *      {
 *          dfsAlgo.handleNewVertex();
 *      }
 * }
 * }
 * </pre>
 *
 * Created by valerii.ryzhuk on 11/15/2015.
 */
public interface DfsAlgo
{
    boolean hasNextStep();

    int vertices();

    int getStart();

    boolean isCurrentVisited();

    void handleProcessedVertex();

    void handleNewVertex();

    int stepToNextVertex();
}
