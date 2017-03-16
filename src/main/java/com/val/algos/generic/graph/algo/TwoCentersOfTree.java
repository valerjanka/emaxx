package com.val.algos.generic.graph.algo;

import com.val.algos.generic.graph.UnDiGraph;
import com.val.algos.generic.graph.algo.bfs.BreadthFirstPathsForNotConnectedComponents;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author valerjanka
 */
public class TwoCentersOfTree {
    private int firstCenter;
    private int secondCenter;


    public TwoCentersOfTree(UnDiGraph graph) {
        if(graph.vertices() == 1) {
            firstCenter = 0;
            secondCenter = 0;
            return;
        }
        ArrayList<Integer> diameter = new ArrayList<>(DiameterOfTree.getDiameter(graph));
        int maxRadius = (diameter.size() - 1) / 2;
        int[] handsDist = calculateHands(graph, diameter);
        int[] leftHands = Arrays.copyOfRange(handsDist, 0, maxRadius + 1);
        int[] rightHands = Arrays.copyOfRange(handsDist, handsDist.length - maxRadius - 1, handsDist.length);
        reverse(rightHands);
        int radiusForLeftPart = calculateRadius(leftHands);
        int radiusForRightPart = calculateRadius(rightHands);
        int optimumRadius = Math.max(radiusForLeftPart, radiusForRightPart);
        firstCenter = optimumRadius;
        secondCenter = diameter.size() - optimumRadius - 1;
    }

    private int[] calculateHands(UnDiGraph graph, ArrayList<Integer> diameter) {
        int[] handsPerGraph = new int[graph.vertices()];
        removeAdjFromDiameter(graph, diameter);
        BreadthFirstPathsForNotConnectedComponents bfs = new BreadthFirstPathsForNotConnectedComponents(graph, diameter);
        for (int v = 0; v < graph.vertices(); v++) {
            int onDiameter = bfs.getParentStart()[v];
            if (onDiameter != v) {
                handsPerGraph[onDiameter] = Math.max(handsPerGraph[onDiameter], bfs.getDistTo()[v]);
            }
        }
        int[] hands = new int[diameter.size()];
        for(int v = 0; v < diameter.size(); v++) {
            hands[v] = handsPerGraph[diameter.get(v)];
        }
        return hands;
    }

    private void removeAdjFromDiameter(UnDiGraph graph, ArrayList<Integer> diameter) {
        for (int i = 1; i < diameter.size(); i++) {
            graph.removeAdj(diameter.get(i - 1), diameter.get(i));
        }
    }

    private void reverse(int[] mas) {
        int first = 0;
        int last = mas.length - 1;
        while (first < last) {
            int x = mas[first];
            mas[first] = mas[last];
            mas[last] = x;
            ++first;
            --last;
        }
    }

    private int calculateRadius(int[] hands) {
        int resultRadius = hands.length - 1;
        int maxHand = hands[hands.length - 1];
        int v = hands.length - 1;
        while (maxHand < resultRadius && v > 0) {
            v--;
            maxHand = Math.max(maxHand + 1, hands[v]);
            resultRadius = Math.max(maxHand, resultRadius - 1);
        }
        return resultRadius;
    }

    public int getFirstCenter() {
        return firstCenter;
    }

    public int getSecondCenter() {
        return secondCenter;
    }
}
