package com.val.algos.generic.graph.algo.bfs;

import com.val.algos.generic.graph.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author valerjanka
 */
public class BreadthFirstPathsForNotConnectedComponents {
    private final boolean[] marked;
    private final int[] distTo;
    private final int[] parent;
    private final int[] parentStart;
    private final Graph graph;
    private final List<Integer> componentStarts;

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
                graph.adj(v).stream().filter(w -> !marked[w]).forEach(w -> {
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                    parent[w] = v;
                    parentStart[w] = start;
                    queue.offer(w);
                });
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
