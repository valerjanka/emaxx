package com.val.algos.generic.structure;

import java.util.Arrays;

/**
 * SQRT Decomposition for querying aggregated results using a generic items and a aggregation function.
 *
 * @param <ITEM_TYPE> Type of items in the array.
 * @author valerjanka
 */
public class SqrtDecomposition<ITEM_TYPE> {
    private final ITEM_TYPE[] items;
    private final AggregationFunction<ITEM_TYPE> aggregationFunction;
    private Node[] decomposedNodes;
    private int itemsPerNode;

    public SqrtDecomposition(ITEM_TYPE[] items, AggregationFunction<ITEM_TYPE> aggregationFunction) {
        this.items = items;
        this.aggregationFunction = aggregationFunction;
        decompose();
    }

    @SafeVarargs
    private final void decompose(Node... dummy) {
        int decomposeSize = (int) Math.sqrt(items.length);
        decomposedNodes = Arrays.copyOf(dummy, decomposeSize);
        itemsPerNode = (items.length + decomposeSize - 1) / decomposeSize;
        for (int nodeStart = 0, i = 0; i < decomposeSize; nodeStart += itemsPerNode, i++) {
            decomposedNodes[i] = new Node(nodeStart, Math.min(nodeStart + itemsPerNode, items.length));
        }
    }

    /**
     * Calculate result on [l, r) segment by aggregate function.
     *
     * @param startItem left segment border inclusive.
     * @param endItem   right segment border exclusive.
     * @return Aggregated result for the segment.
     */
    public ITEM_TYPE get(int startItem, int endItem) {
        if (startItem < 0 || startItem >= items.length || endItem > items.length) {
            throw new IndexOutOfBoundsException("startItem or endItem is out of array size");
        }
        if (startItem >= endItem) {
            throw new IllegalArgumentException("startItem should be more than endItem");
        }
        int firstNodeIndex = startItem / itemsPerNode;
        int lastNodeIndex = (endItem - 1) / itemsPerNode;
        ITEM_TYPE result;
        // Calculate result from the first Node.
        if (startItem == decomposedNodes[firstNodeIndex].l && firstNodeIndex < lastNodeIndex) {
            result = decomposedNodes[firstNodeIndex].aggregatedValue;
        } else {
            result = aggregateItems(startItem, Math.min(endItem, decomposedNodes[firstNodeIndex].r));
        }

        // Combine results from middle  nodes (excluding first and last)
        if (firstNodeIndex + 1 < lastNodeIndex) {
            result = aggregationFunction.get(result, aggregateNodes(firstNodeIndex + 1, lastNodeIndex));
        }
        // Combine result with the last node.
        if (firstNodeIndex != lastNodeIndex) {
            if (decomposedNodes[lastNodeIndex].r == endItem) {
                result = aggregationFunction.get(result, decomposedNodes[lastNodeIndex].aggregatedValue);
            } else {
                result = aggregationFunction.get(result, aggregateItems(decomposedNodes[lastNodeIndex].l, endItem));
            }
        }
        return result;
    }

    private ITEM_TYPE aggregateItems(int startItem, int endItem) {
        ITEM_TYPE item = items[startItem];
        for (int i = startItem + 1; i < endItem; i++) {
            item = aggregationFunction.get(item, items[i]);
        }
        return item;
    }

    private ITEM_TYPE aggregateNodes(int firstNode, int endNode) {
        ITEM_TYPE result = decomposedNodes[firstNode].aggregatedValue;
        for (int i = firstNode + 1; i < endNode; i++) {
            result = aggregationFunction.get(result, decomposedNodes[i].aggregatedValue);
        }
        return result;
    }

    public interface AggregationFunction<ITEM_TYPE> {
        ITEM_TYPE get(ITEM_TYPE a, ITEM_TYPE b);
    }

    public static class MinFunction implements AggregationFunction<Integer> {

        @Override
        public Integer get(Integer a, Integer b) {
            return Math.min(a, b);
        }
    }

    /**
     * Node represents aggregated value for items within [l, r) interval.
     */
    private class Node {
        private final ITEM_TYPE aggregatedValue;
        private final int l;
        private final int r;

        public Node(int l, int r) {
            this.l = l;
            this.r = r;
            aggregatedValue = aggregateItems(l, r);
        }
    }
}
