package com.val.emaxx.graph.algo.bfs;

import com.val.emaxx.graph.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author valerjanka
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
            graph.adj(v).stream().filter(w -> !marked[w]).forEach(w -> {
                marked[w] = true;
                distTo[w] = distTo[v] + 1;
                parent[w] = v;
                queue.offer(w);
            });
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
