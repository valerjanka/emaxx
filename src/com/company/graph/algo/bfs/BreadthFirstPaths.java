package com.company.graph.algo.bfs;

import com.company.graph.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by valerii.ryzhuk on 11/25/2015.
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] distTo;
    private int[] parent;
    private Graph graph;
    private int start;

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
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while(!queue.isEmpty()) {
            int v = queue.poll();
            for(int w : graph.adj(v)) {
                if(!marked[w]) {
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
