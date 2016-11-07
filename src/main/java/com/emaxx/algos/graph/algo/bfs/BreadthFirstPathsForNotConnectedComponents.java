package com.emaxx.algos.graph.algo.bfs;

import com.emaxx.algos.graph.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Perform breadth first search in Graph from list of start vertices.
 * Could be helpful when graph has not connected components
 *
 * Created by valerii.ryzhuk on 12/1/2015.
 */
public class BreadthFirstPathsForNotConnectedComponents {
    private boolean[] marked;
    private int[] distTo;
    private int[] parent;
    private int[] parentStart;
    private Graph graph;
    private List<Integer> componentStarts;

    public BreadthFirstPathsForNotConnectedComponents(Graph graph, List<Integer> componentStarts) {
        this.graph = graph;
        this.componentStarts = componentStarts;
        distTo = new int[graph.vertices()];
        parent = new int[graph.vertices()];
        marked = new boolean[graph.vertices()];
        parentStart = new int[graph.vertices()];
        bfs();
    }

    private void bfs() {
        for(int start : componentStarts) {
            marked[start] = true;
            parent[start] = start;
            parentStart[start] = start;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);

            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (int w : graph.adj(v)) {
                    if (!marked[w]) {
                        marked[w] = true;
                        distTo[w] = distTo[v] + 1;
                        parent[w] = v;
                        parentStart[w] = start;
                        queue.offer(w);
                    }
                }
            }
        }
    }

    public boolean[] getMarked() {
        return marked;
    }

    public int[] getDistTo() {
        return distTo;
    }

    public int[] getParent() {
        return parent;
    }

    public int[] getParentStart() {
        return parentStart;
    }
}
