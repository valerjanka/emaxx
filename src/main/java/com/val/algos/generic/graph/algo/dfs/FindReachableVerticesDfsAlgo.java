package com.val.algos.generic.graph.algo.dfs;

import com.val.algos.generic.graph.Graph;
import com.val.algos.generic.graph.algo.dfs.result.FindReachableVerticesDfsResult;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Result - boolean marked[]
 * Created by valerii.ryzhuk on 11/15/2015.
 */
public class FindReachableVerticesDfsAlgo implements DfsAlgo {
    private final Graph graph;
    private final int start;
    private final boolean[] marked;
    protected Deque<Integer> stack = new ArrayDeque<>();

    public FindReachableVerticesDfsAlgo(Graph graph, int start) {
        marked = new boolean[graph.vertices()];
        this.start = start;
        this.graph = graph;
        stack.push(start);
    }

    @Override
    public boolean hasNextStep() {
        return !stack.isEmpty();
    }

    @Override
    public int vertices() {
        return graph.vertices();
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public boolean isCurrentVisited() {
        return marked[stack.getFirst()];
    }

    @Override
    public void handleProcessedVertex() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Can't process current vertex. Stack is empty");
        }
        stack.pop();
    }

    @Override
    public void handleNewVertex() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Can't process new vertex. Stack is empty");
        }
        marked[stack.getFirst()] = true;
    }

    @Override
    public int stepToNextVertex() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Can't get vertex. Stack is empty");
        }
        return stack.getFirst();
    }

    @Override
    public void pushAdjToVisit() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Can't get vertex. Stack is empty");
        }
        int v = stack.getFirst();
        List<Integer> adj = graph.adj(v);
        ListIterator<Integer> it = adj.listIterator(adj.size());
        while (it.hasPrevious()) {
            int w = it.previous();
            if (!marked[w]) {
                stack.push(w);
            }
        }
    }

    public boolean[] getMarked() {
        return marked;
    }

    public static class ResultBuilder implements DfsResultBuilder<FindReachableVerticesDfsAlgo, FindReachableVerticesDfsResult> {
        private final FindReachableVerticesDfsAlgo dfsAlgo;

        public ResultBuilder(FindReachableVerticesDfsAlgo dfsAlgo) {
            this.dfsAlgo = dfsAlgo;
        }

        @Override
        public FindReachableVerticesDfsResult build() {
            return new FindReachableVerticesDfsResult() {
                @Override
                public boolean[] getMarked() {
                    return dfsAlgo.getMarked();
                }

                @Override
                public int getStart() {
                    return dfsAlgo.getStart();
                }
            };
        }
    }
}
