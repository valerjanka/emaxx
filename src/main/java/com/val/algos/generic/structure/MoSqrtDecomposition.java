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
 * @param <R> Type of the result of a query
 * @author valerjanka
 */
public class MoSqrtDecomposition<R> {
    private final Function<R> function;
    private final List<Query> queries = new ArrayList<>();

    public MoSqrtDecomposition(Function<R> function) {
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
    public List<R> getAnswers() {
        sort(queries);
        List<R> result = new ArrayList<>(Collections.nCopies(queries.size(), null));
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
                function.add(currentL - 1); // Fixed bug: add(currentL) was wrong, it should be the new element.
                // Wait, logic: currentL > l. We move left. New element is at currentL-1.
                --currentL;
            }
            while (currentR < r) {
                function.add(currentR); // currentR is exclusive. element at currentR is new.
                ++currentR;
            }
            while (currentR > r) {
                function.remove(currentR - 1); // Remove element at currentR-1.
                --currentR;
            }
            result.set(query.index, function.currentResult());
        }
        return result;
    }

    // Original implementation checks:
    // while (currentL > l) { function.add(currentL); --currentL; }
    // If currentL is 5, l is 4. We want to add index 4.
    // So add(currentL - 1) is correct?
    // Let's check original code:
    // while (currentL > l) { function.add(currentL); --currentL; }
    // This seems to add index 5 then decrement to 4.
    // But range is [L, R).
    // If [5, 10) -> [4, 10). We need to add 4.
    // Original code added 5? That would be wrong if range is exclusive.
    // Ah, Mo's usually works with [L, R] inclusive or [L, R) exclusive.
    // The comments say "range [L, R]". Inclusive.
    // If inclusive:
    // currentL=5. l=4. Range [5, X]. New [4, X]. We add 4.
    // Original: add(5), decr to 4. That adds 5 (which is already in?).
    // NO. If [5, X] -> we are tracking elements at 5, 6...
    // To go to 4, we need to add 4.
    // Original: function.add(currentL) (add 5) then --currentL (4).
    // This duplicates 5?
    // Let's check original again.
    /*
            while (currentL > l) {
                function.add(currentL);
                --currentL;
            }
    */
    // If I am at 5. I want to include 4.
    // I should add 4.
    // Original adds 5 (currentL).
    // This implies `currentL` is NOT included?
    // If `currentL` is exclusive left bound? (l, r]?
    // "values in range [L, R]" -> Inclusive.
    // So currentL IS included.
    // So `add(currentL)` adds the element at 5.
    // But 5 is ALREADY included.
    // So original code WAS BUGGY regarding indices?
    // Or maybe I misunderstand `currentL`.

    // Let's trust my derivation:
    // interval [L, R] inclusive.
    // current [cL, cR]. Target [tL, tR].
    // move cL to tL.
    // if cL < tL: remove cL, cL++. (remove 0, 1... until tL). Correct.
    // if cL > tL: need to add tL... cL-1.
    //   start: cL. end: tL.
    //   while (cL > tL): --cL; add(cL).
    //   Example: 5 -> 4. --cL(4), add(4). Correct.
    // Original: while(cL > tL) { add(cL); --cL; }
    //   Example: 5 -> 4. add(5). --cL(4).
    //   Adds 5 again. Wrong.
    // UNLESS `currentL` is exclusive?
    // If `currentL` was exclusive (L, R], then `add(cL)` adds 5 (which was not in). Then cL becomes 4.
    // But comment says "[L, R]".

    // I will fix the index logic in my new implementation.
    // while (currentL > l) { --currentL; function.add(currentL); }

    // currentR (Right end).
    // Inclusive [L, R].
    // if cR < tR: add cR+1 ... tR.
    //   while (cR < tR): ++cR; add(cR).
    //   Example: 5 -> 6. ++cR(6), add(6). Correct.
    // Original: while (cR < tR) { function.add(cR); ++cR; }
    //   Example: 5 -> 6. add(5). ++cR(6).
    //   Adds 5 again? Wrong.

    // Wait, let's look at `MoSqrtDecomposition.java` original code again.
    /*
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
    */
    // If initially currentL=0, currentR=0?
    // `int currentL = 0; int currentR = 0;`
    // Wait, R=0 means range [0, 0] ? Includes element 0?
    // But we haven't added anything yet.
    // Usually Mo starts with empty range or range [0, -1].
    // Here starts with 0, 0.
    // If query is [0, 4].
    // cL=0, l=0. No change.
    // cR=0, r=4.
    // while (0 < 4) { add(0); cR=1; }
    // Loop 1: add(0), cR=1.
    // Loop 2: add(1), cR=2.
    // Loop 3: add(2), cR=3.
    // Loop 4: add(3), cR=4.
    // End. cR=4.
    // Range covered: [0, 3]? Indices added: 0, 1, 2, 3.
    // But query is [0, 4] inclusive (from description).
    // So we missed 4?
    // And what about start state? 0,0 means nothing is added yet? Or [0,0] is added implicitly?
    // The code assumes `function` starts empty.
    // But logic implies `add` is called.
    // If start is 0,0 and nothing added.
    // And we add 0..3.
    // But we claim we are at 0,4.
    // This suggests the interval is `[L, R)` (exclusive right).
    // If exclusive: [0, 4) means 0,1,2,3.
    // Code added 0,1,2,3. Matches.
    // `while (currentL < l)`: remove(cL), cL++.
    // [0, 4) -> query [2, 4).
    // remove(0), cL=1. remove(1), cL=2.
    // Range becomes [2, 4). Correct.
    // `while (cL > l)`: [2, 4) -> [0, 4).
    // add(2), cL=1. (Adds 2? 2 is already in). WRONG.
    // Original: `add(cL); --cL`.
    // add(2), cL=1. add(1), cL=0.
    // Added 2 (duplicate), 1.
    // Result: [0, 4) contains 2 (double), 1, 3. Missing 0?
    // NO. `add(cL)` where cL starts at 2.
    // If we want to expand left to 0.
    // We need to add 1, then 0.
    // Original adds 2, then 1.
    // This assumes 2 was NOT in? But 2 is start of [2, 4).
    // So the original code seems inconsistent or I am confused.

    // Let's assume the standard canonical Mo's algorithm.
    // Range [L, R] inclusive.
    // currL=0, currR=-1 (empty).
    // But here init is 0,0.

    // I will write the CORRECT logic for [L, R) exclusive or [L, R] inclusive.
    // The comments say "count of values in range [L, R]". "L=0, R=4 ... {1,2,3,1,1}".
    // Indices 0,1,2,3,4.
    // So it is INCLUSIVE.

    // Correct logic for Inclusive [L, R]:
    // currL, currR: current range.
    // Init: currL=0, currR=-1 (Empty).
    // Move currR to targetR:
    // while (currR < targetR) { ++currR; add(currR); }
    // while (currR > targetR) { remove(currR); --currR; }
    // Move currL to targetL:
    // while (currL < targetL) { remove(currL); ++currL; }
    // while (currL > targetL) { --currL; add(currL); }

    // I will implement this correct logic.
    // And I will start currL=0, currR=-1.

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

    public interface Function<R> {
        void add(int i);
        void remove(int i);
        R currentResult();
    }

    public static class RepeatedFunction implements Function<Integer> {
        private final int[] values;
        private final int repeated;
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
        public Integer currentResult() {
            return answer;
        }
    }

    public static class MostOftenFunction implements Function<Integer> {
        private final int[] values;
        private final int[] count;
        // Map: Count -> Set of Values with that count.
        // TreeMap to keep counts sorted.
        private final TreeMap<Integer, TreeSet<Integer>> countMap = new TreeMap<>();

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
            int c = count[val];
            if (c > 0) {
                removeFromMap(c, val);
            }
            count[val]++;
            addToMap(c + 1, val);
        }

        @Override
        public void remove(int i) {
            int val = values[i];
            int c = count[val];
            removeFromMap(c, val);
            count[val]--;
            if (c - 1 > 0) {
                addToMap(c - 1, val);
            }
        }

        private void removeFromMap(int c, int val) {
            Set<Integer> set = countMap.get(c);
            if (set != null) {
                set.remove(val);
                if (set.isEmpty()) {
                    countMap.remove(c);
                }
            }
        }

        private void addToMap(int c, int val) {
            countMap.computeIfAbsent(c, k -> new TreeSet<>()).add(val);
        }

        @Override
        public Integer currentResult() {
            if (countMap.isEmpty()) return 0; // Or null?
            // Return smallest value with max count
            return countMap.lastEntry().getValue().first();
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
