package com.val.algos.generic.graph.algo.dfs;

import com.val.algos.generic.graph.Graph;
import com.val.algos.generic.graph.algo.dfs.result.FindPathDfsResult;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Store parentIds to restore the path, also store postOrder vertices and path
 *
 * @author valerjanka
 */
public class FindReachablePathDfsAlgo extends FindReachableVerticesDfsAlgo implements DfsAlgo {
    private final int[] parentIds;
    private final LinkedList<Integer> postOrder;
    private final List<Integer> path;
    private final int[] markedStackIndex;
    private final Deque<Integer> parentIdStack;

    public FindReachablePathDfsAlgo(Graph graph, int start) {
        super(graph, start);
        parentIds = new int[graph.vertices()];
        postOrder = new LinkedList<>();
        path = new LinkedList<>();
        markedStackIndex = new int[graph.vertices()];
        parentIdStack = new LinkedList<>();
        parentIdStack.push(-1);
    }

    @Override
    public void handleNewVertex() {
        super.handleNewVertex();
        int v = stack.getFirst();
        markedStackIndex[v] = stack.size();
        parentIdStack.push(v);
        path.add(v);
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

    public static class ResultBuilder implements DfsResultBuilder<FindReachablePathDfsAlgo, FindPathDfsResult> {
        private final FindReachablePathDfsAlgo dfsAlgo;

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
