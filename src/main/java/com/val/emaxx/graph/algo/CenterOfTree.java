package com.val.emaxx.graph.algo;

import com.val.emaxx.graph.UnDiGraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author valerjanka
 */
public class CenterOfTree {

    public static int getCenter(UnDiGraph graph) {
        int[] children = new int[graph.vertices()];
        calculateChildren(children, graph);

        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < graph.vertices(); v++) {
            if (children[v] == 1) {
                queue.offer(v);
            }
        }
        while (queue.size() > 1) {
            int v = queue.poll();
            children[v]--;
            graph.adj(v).stream().filter(w -> children[w] > 0).forEach(w -> {
                children[w]--;
                if (children[w] == 1) {
                    queue.offer(w);
                }
            });
        }
        return queue.poll();
    }

    private static void calculateChildren(int[] children, UnDiGraph graph) {
        for (int v = 0; v < graph.vertices(); v++) {
            children[v] = graph.adj(v).size();
        }
    }
}
