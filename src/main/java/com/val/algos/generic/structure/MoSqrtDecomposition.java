package com.val.algos.generic.structure;

import java.util.*;

/**
 * MO’s Algorithm (Query square root decomposition). Complexity is N * Sqrt(N) for all queries.
 * Current implementation does not support update operation
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
    private final Function function;
    private final List<Query> queries = new ArrayList<>();

    public MoSqrtDecomposition(Function function) {
        if (function == null) {
            throw new IllegalArgumentException("function can't be null");
        }
        this.function = function;
    }

    /**
     * Add query for offline processing.
     */
    public void addQuery(int l, int r) {
        queries.add(new Query(l, r, queries.size()));
    }

    /**
     * Calculate answers for previous added queries
     */
    public int[] getAnswers() {
        sort(queries);
        int[] result = new int[queries.size()];
        int currentL = 0;
        int currentR = 0;

        for (Query query : queries) {
            int l = query.l;
            int r = query.r;
            while (currentL < l) {
                function.remove(currentL);
                ++currentL;
            }
            while (currentL > l) {
                function.add(currentL);
                --currentL;
            }
            while (currentR < r) {
                function.add(currentR);
                ++currentR;
            }
            while (currentR > r) {
                function.remove(currentR);
                --currentR;
            }
            result[query.index] = function.currentResult();
        }
        return result;
    }

    private void sort(List<Query> queries) {
        queries.sort(new QueryComparator((int) Math.sqrt(queries.size())));
    }

    public static class Query {
        int l, r, index;

        public Query(int l, int r, int index) {
            this.l = l;
            this.r = r;
            this.index = index;
        }
    }

    public interface Function {
        void add(int i);
        void remove(int i);
        int currentResult();
    }

    public static class RepeatedFunction implements Function {
        private final int[] values;
        private final int repeated;

        // temp array to store count of index in values on current segment
        private final int[] count;
        private int answer = 0;

        public RepeatedFunction(int[] values, int repeated, int max_values) {
            this.values = values;
            count = new int[max_values + 1];
            this.repeated = repeated;
        }

        @Override
        public void add(int i) {
            if(++count[values[i]] == repeated) {
                ++answer;
            }
        }

        @Override
        public void remove(int i) {
            if (count[values[i]]-- == repeated) {
                --answer;
            }
        }

        @Override
        public int currentResult() {
            return answer;
        }
    }

    public static class MostOftenFunction implements Function {
        private final int[] values;
        // temp array to store count of index in values on current segment
        private final int[] count;
        private final TreeSet<Integer> sortedValues = new TreeSet<>(new CountComparator());

        public MostOftenFunction(int[] values) {
            this.values = values;
            OptionalInt max = Arrays.stream(values).max();
            if (!max.isPresent()) {
                throw new IllegalArgumentException("values is empty");
            }
            count = new int[max.getAsInt() + 1];
        }

        @Override
        public void add(int i) {
            int val = values[i];
            if (count[val] > 0) {
                sortedValues.remove(val);
            }
            count[val]++;
            sortedValues.add(val);
        }

        @Override
        public void remove(int i) {
            int val = values[i];
            sortedValues.remove(val);
            count[val]--;
            if (count[val] > 0) {
                sortedValues.add(val);
            }
        }

        @Override
        public int currentResult() {
            return sortedValues.first();
        }

        private class CountComparator implements Comparator<Integer> {

            @Override
            public int compare(Integer x, Integer y) {
                if (count[x] == count[y]) {
                    return Integer.compare(x, y);
                } else {
                    return -Integer.compare(count[x], count[y]);
                }
            }
        }
    }


    private static class QueryComparator implements Comparator<Query> {
        private final int sqrt;

        public QueryComparator(int sqrt) {
            this.sqrt = sqrt;
        }

        @Override
        public int compare(Query o1, Query o2) {
            if (o1.l / sqrt != o2.l / sqrt) {
                return Integer.compare(o1.l, o2.l);
            } else {
                return Integer.compare(o1.r, o2.r);
            }
        }
    }
}
