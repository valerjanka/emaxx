package com.val.emaxx.structure;

/**
 * Created by valerii.ryzhuk on 1/2/2016.
 */
public class SqrtDecomposition<ITEM_TYPE> {
    private ITEM_TYPE[] items;
    private Node[] decomposed;
    private int decomposedStep;
    private ItemSelector<ITEM_TYPE> itemSelector;

    public SqrtDecomposition(ITEM_TYPE[] items, ItemSelector<ITEM_TYPE> itemSelector) {
        this.items = items;
        this.itemSelector = itemSelector;
        decompose();
    }

    private void decompose() {
        int decomposeSize = (int) Math.sqrt(items.length);
        decomposed = (Node[]) new Object[decomposeSize];
        decomposedStep = (items.length + decomposeSize - 1) / decomposeSize;
        for (int i = 0; i < items.length; i += decomposedStep) {
            decomposed[i] = new Node(i, Math.min(i + decomposedStep, items.length));
        }
    }

    public ITEM_TYPE get(int l, int r) {
        if (l >= items.length || r >= items.length) {
            throw new IndexOutOfBoundsException("l or r is out of array size");
        }
        int firstNodeIndex = l / decomposedStep;
        int lastNodeIndex = (r - 1) / decomposedStep;
        ITEM_TYPE result = selectItem(l, Math.min(r, decomposed[firstNodeIndex].r));
        if (firstNodeIndex + 1 < lastNodeIndex) {
            result = itemSelector.get(result, selectNode(firstNodeIndex + 1, lastNodeIndex));
        }
        if (firstNodeIndex != lastNodeIndex) {
            result = itemSelector.get(result, selectItem(decomposed[lastNodeIndex].l, r));
        }
        return result;
    }

    private ITEM_TYPE selectItem(int l, int r) {
        ITEM_TYPE item = items[l];
        for (int i = l + 1; i < r; i++) {
            item = itemSelector.get(item, items[i]);
        }
        return item;
    }

    private ITEM_TYPE selectNode(int l, int r) {
        ITEM_TYPE result = decomposed[l].item;
        for (int i = l + 1; i < r; i++) {
            result = itemSelector.get(result, decomposed[i].item);
        }
        return result;
    }

    class Node {
        private ITEM_TYPE item;
        private int l;
        private int r;

        public Node(int l, int r) {
            this.l = l;
            this.r = r;
            item = selectItem(l, r);
        }
    }

    public interface ItemSelector<ITEM_TYPE> {
        ITEM_TYPE get(ITEM_TYPE a, ITEM_TYPE b);
    }

    public static class MinSelector implements ItemSelector<Integer>{

        @Override
        public Integer get(Integer a, Integer b) {
            return Math.min(a, b);
        }
    }
}
