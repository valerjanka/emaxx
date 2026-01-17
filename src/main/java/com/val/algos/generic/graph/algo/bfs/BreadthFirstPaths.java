package com.val.algos.generic.graph.algo.bfs;

import com.val.algos.generic.graph.Graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author valerjanka
 */
public class BreadthFirstPaths {
    private final boolean[] marked;
    private final int[] distTo;
    private final int[] parent;
    private final Graph graph;
    private final int start;

    public BreadthFirstPaths(Graph graph, int start) {
        this.graph = graph;
        this.start = start;
        distTo = new int[graph.vertices()];
        parent = new int[graph.vertices()];
        marked = new boolean[graph.vertices()];

        bfs();
    }

    private void bfs() {
        marked[start] = true;
        parent[start] = start;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while(!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : graph.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                    parent[w] = v;
                    queue.offer(w);
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
}
