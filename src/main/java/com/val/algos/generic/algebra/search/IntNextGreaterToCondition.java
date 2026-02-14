package com.val.algos.generic.algebra.search;

/**
 * Stops on the next element greater to the pivot.
 */
class IntNextGreaterToCondition implements StopConditionBinarySearch.StopCondition {
    private final int[] array;
    private final int pivot;

    public IntNextGreaterToCondition(int[] array, int pivot) {
        this.array = array;
        this.pivot = pivot;
    }

    @Override
    public int compare(int position) {
        int compare = array[position] - pivot;
        if (compare > 0 && (position == 0 || array[position - 1] <= pivot)) {
            return 0;
        }
        return (compare <= 0) ? 1 : -1;
    }
}
