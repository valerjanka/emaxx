package com.val.algos.emaxx.structure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * MO’s Algorithm (Query square root decomposition)
 * See <a href="https://blog.anudeep2011.com/mos-algorithm/">https://blog.anudeep2011.com/mos-algorithm/</a>
 * <br>
 * Given an array of size N. All elements of array <= N. You need to answer M queries. Each query is of the form L, R. You need to answer the count of values in range [L, R] which are repeated at least 'repeated' times.
 * <br><br>
 * <b>Example</b>: Let the array be {1, 2, 3, 1, 1, 2, 1, 2, 3, 1} (zero indexed)
 * <br>
 * Query: L = 0, R = 4. Answer = 1. Values in the range [L, R] = {1, 2, 3, 1, 1} only 1 is repeated at least 3 times.
 * <br>
 * Query: L = 1, R = 8. Answer = 2. Values in the range [L, R] = {2, 3, 1, 1, 2, 1, 2, 3} 1 is repeated 3 times and 2 is repeated 3 times. Number of elements repeated at least 3 times = Answer = 2.
 *
 * @author valerjanka
 */
public class MoSqrtDecomposition {
    private final int[] values;
    // amount of at least repetition for value to count for answer
    private final int repeated;

    // temp array to store count of index in values on current segment
    private final int[] count;

    public MoSqrtDecomposition(int[] values, int repeated, int max_values) {
        this.values = values;
        count = new int[max_values + 1];
        this.repeated = repeated;
    }

    /**
     * Return the count of values in range [L, R] which are repeated at least
     * {@link MoSqrtDecomposition#repeated} times
     * <p>
     * Time Complexity: O((N + M) * sqrt(N)) where N is array length and M is number of queries.
     * Space Complexity: O(N + M) for storing counts and query mapping.
     *
     * @param queries keep left index for each segment inclusive and right index for each segment exclusive
     * @return array with answers for each query
     */
    public int[] query(Pair[] queries) {
        Map<Pair, Integer> pairToIndex = createMap(queries);
        sort(queries);
        int[] result = new int[queries.length];
        int currentL = 0;
        int currentR = 0;
        int answer = 0;

        for (Pair query : queries) {
            int l = query.l;
            int r = query.r;
            while (currentL < l) {
                if (remove(currentL)) {
                    --answer;
                }
                ++currentL;
            }
            while (currentL > l) {
                if (add(currentL)) {
                    ++answer;
                }
                --currentL;
            }
            while (currentR < r) {
                if (add(currentR)) {
                    ++answer;
                }
                ++currentR;
            }
            while (currentR > r) {
                if (remove(currentR)) {
                    --answer;
                }
                --currentR;
            }
            result[pairToIndex.get(query)] = answer;
        }
        return result;
    }

    private Map<Pair, Integer> createMap(Pair[] queries) {
        Map<Pair, Integer> result = new HashMap<>();
        for(int i = 0; i < queries.length; i++) {
            result.put(queries[i], i);
        }
        return result;
    }

    private void sort(Pair[] queries) {
        Arrays.sort(queries, new QueryComparator((int) Math.sqrt(queries.length)));
    }

    private boolean add(int i) {
        return ++count[values[i]] == repeated;
    }

    private boolean remove(int i) {
        return count[values[i]]-- == repeated;
    }

    public static class Pair {
        int l, r;

        public Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    private static class QueryComparator implements Comparator<Pair> {
        private final int sqrt;

        public QueryComparator(int sqrt) {
            this.sqrt = sqrt;
        }

        @Override
        public int compare(Pair o1, Pair o2) {
            if (o1.l / sqrt != o2.l / sqrt) {
                return Integer.compare(o1.l, o2.l);
            } else {
                return Integer.compare(o1.r, o2.r);
            }
        }
    }

}
