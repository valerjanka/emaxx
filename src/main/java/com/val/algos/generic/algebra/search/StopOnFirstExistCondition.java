package com.val.algos.generic.algebra.search;

/**
 * Created 4/11/2019
 * Stops on the smallest position (if there are multiple same targets) with the specified target.
 *
 * @author valerjanka
 */
class StopOnFirstExistCondition implements StopConditionBinarySearch.StopCondition {
    private final int[] array;
    private final int target;

    public StopOnFirstExistCondition(int[] array, int target) {
        this.array = array;
        this.target = target;
    }

    @Override
    public int compare(int position) {
        if (array[position] == target) {
            if (position == 0 || array[position - 1] < target) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return target - array[position];
        }
    }
}
