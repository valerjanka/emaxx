package com.val.emaxx.graph.algo;

import com.val.emaxx.graph.UnDiGraph;
import com.val.emaxx.graph.algo.bfs.BreadthFirstPaths;

import java.util.LinkedList;
import java.util.List;

/**
 * @author valerjanka
 */
public class DiameterOfTree {
    public static List<Integer> getDiameter(UnDiGraph graph) {
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, 0);
        int onDiameter = findFarthest(breadthFirstPaths);
        breadthFirstPaths = new BreadthFirstPaths(graph, onDiameter);
        int anotherOnDiameter = findFarthest(breadthFirstPaths);
        List<Integer> diameter = new LinkedList<>();
        int currentV = anotherOnDiameter;
        while (currentV != onDiameter) {
            diameter.add(currentV);
            currentV = breadthFirstPaths.getParent()[currentV];
        }
        diameter.add(onDiameter);
        return diameter;
    }

    private static int findFarthest(BreadthFirstPaths breadthFirstPaths) {
        int[] len = breadthFirstPaths.getDistTo();
        int maxLenIndex = 0;
        for (int v = 1; v < len.length; v++) {
            if (len[v] > len[maxLenIndex]) {
                maxLenIndex = v;
            }
        }
        return maxLenIndex;
    }

}
