package com.emaxx.algos.graph.algo.dfs;

import com.emaxx.algos.graph.Graph;
import com.emaxx.algos.graph.algo.dfs.result.FindPathDfsResult;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by valerii.ryzhuk on 11/18/2015.
 */
public class FindReachablePathDfsAlgo implements DfsAlgo {
    private Graph graph;
    private int start;
    private boolean[] marked;
    protected Deque<Integer> stack = new LinkedList<>();

    private int[] parentIds;
    private LinkedList<Integer> postOrder;
    private List<Integer> path;
    private int[] markedStackIndex;
    private Deque<Integer> parentIdStack;

    public FindReachablePathDfsAlgo(Graph graph, int start) {
        marked = new boolean[graph.vertices()];
        this.start = start;
        this.graph = graph;
        stack.push(start);

        parentIds = new int[graph.vertices()];
        postOrder = new LinkedList<>();
        path = new LinkedList<>();
        markedStackIndex = new int[graph.vertices()];
        parentIdStack = new LinkedList<>();
        parentIdStack.push(-1);
    }

    @Override
    public void handleNewVertex() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Can't process new vertex. Stack is empty");
        }
        marked[stack.getFirst()] = true;

        int v = stack.getFirst();
        markedStackIndex[v] = stack.size();
        parentIdStack.push(v);
        path.add(v);

        addAdjacentVerticesToBeVisited();
    }

    @Override
    public int stepToNextVertex() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Can't get vertex. Stack is empty");
        }
        return stack.getFirst();
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
        int v = stack.getFirst();
        if (stack.size() == markedStackIndex[v]) { // stackSize()
            parentIdStack.pop();
            parentIds[v] = parentIdStack.getFirst();
            postOrder.add(v); // returnTo(v)
            path.add(v);
        }
        stack.pop();
    }

    public int[] getParentIds() {
        return parentIds;
    }

    public List<Integer> getPostOrder() {
        return postOrder;
    }

    public List<Integer> getPath() {
        return path;
    }

    protected void addAdjacentVerticesToBeVisited() {
        int v = stack.getFirst();
        Iterator<Integer> reverseOrder = graph.adj(v).stream().filter(w -> !marked[w])
            .collect(Collectors.toCollection(LinkedList<Integer>::new)).descendingIterator();
        while (reverseOrder.hasNext()) {
            stack.push(reverseOrder.next());
        }
    }

    public static class ResultBuilder implements DfsResultBuilder<FindReachablePathDfsAlgo, FindPathDfsResult> {
        private FindReachablePathDfsAlgo dfsAlgo;

        public ResultBuilder(FindReachablePathDfsAlgo dfsAlgo) {
            this.dfsAlgo = dfsAlgo;
        }

        @Override
        public FindPathDfsResult build() {
            return new FindPathDfsResult() {

                @Override
                public int[] getParentIds() {
                    return dfsAlgo.getParentIds();
                }

                @Override
                public List<Integer> getPostOrder() {
                    return dfsAlgo.getPostOrder();
                }

                @Override
                public List<Integer> getPath() {
                    return dfsAlgo.getPath();
                }
            };
        }
    }
}
