package com.val.emaxx.graph.algo.dfs;

import com.val.emaxx.graph.Graph;
import com.val.emaxx.graph.algo.dfs.result.FindComponentsDfsResult;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @author valerjanka
 */
public class FindComponentsDfsAlgo implements DfsAlgo {
    private Graph graph;
    private int start;
    private boolean[] marked;
    private int[] ids;
    private Deque<Integer> stack = new LinkedList<>();
    private int amountOfMarked;
    private int currentId = 0;
    private int startComponentVertex;

    public FindComponentsDfsAlgo(Graph graph, int start) {
        this.graph = graph;
        this.start = start;
        startComponentVertex = start;
        marked = new boolean[graph.vertices()];
        ids = new int[graph.vertices()];
        amountOfMarked = 0;
        stack.push(start);
    }

    @Override
    public boolean hasNextStep() {
        return amountOfMarked != graph.vertices();
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
        stack.pop(); // removeFromStack
    }

    @Override
    public void handleNewVertex() {
        markAndSetId(stack.getFirst());
    }

    @Override
    public int stepToNextVertex() {
        if (amountOfMarked >= graph.vertices()) {
            throw new IllegalStateException("All vertices have already marked");
        }
        if (!stack.isEmpty()) {
            return stack.getFirst();
        } else {
            ++currentId;
            int v = findNextNotMetVertex();
            stack.push(v);
            startComponentVertex = v;
            return v;
        }
    }

    @Override
    public void pushAdjToVisit() {
        int v = stack.getFirst();
        Iterator<Integer> reverseOrder = graph.adj(v).stream().filter(w -> !marked[w])
                .collect(Collectors.toCollection(LinkedList<Integer>::new)).descendingIterator();
        while (reverseOrder.hasNext()) {
            stack.push(reverseOrder.next());
        }
    }

    public int[] getIds() {
        return ids;
    }

    private void markAndSetId(int v) {
        marked[v] = true;
        ++amountOfMarked;
        ids[v] = currentId;
    }

    private int findNextNotMetVertex() {
        for (int i = startComponentVertex + 1; i < graph.vertices(); i++) {
            if (!marked[i]) {
                return i;
            }
        }
        throw new IllegalStateException("Can't find next not met vertex");
    }

    public static class ResultBuilder implements DfsResultBuilder<FindComponentsDfsAlgo, FindComponentsDfsResult> {
        private FindComponentsDfsAlgo dfsAlgo;

        public ResultBuilder(FindComponentsDfsAlgo dfsAlgo) {
            this.dfsAlgo = dfsAlgo;
        }

        @Override
        public FindComponentsDfsResult build() {
            return () -> dfsAlgo.ids;
        }
    }
}