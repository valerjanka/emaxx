package com.val.emaxx.graph.algo.dfs;

import com.val.emaxx.graph.Graph;
import com.val.emaxx.graph.UnDiGraph;
import com.val.emaxx.graph.UnDiGraphImpl;

import com.val.emaxx.graph.algo.dfs.result.FindPathDfsResult;
import com.val.emaxx.graph.algo.dfs.result.FindReachableVerticesDfsResult;
import com.val.emaxx.graph.algo.dfs.result.FindComponentsDfsResult;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author valerjanka
 */
public class DepthFirstSearch {

    public FindPathDfsResult findPaths(Graph graph, int start) {
        FindReachablePathDfsAlgo dfsAlgo = new FindReachablePathDfsAlgo(graph, start);
        dfs(dfsAlgo);
        return new FindReachablePathDfsAlgo.ResultBuilder(dfsAlgo).build();
    }

    public FindReachableVerticesDfsResult findReachableComponents(Graph graph, int start) {
        FindReachableVerticesDfsAlgo dfsAlgo = new FindReachableVerticesDfsAlgo(graph, start);
        dfs(dfsAlgo);
        return new FindReachableVerticesDfsAlgo.ResultBuilder(dfsAlgo).build();
    }

    /**
     * Only works for UnDirected graph as result would very of the order of start vertices.
     *
     * @param graph un-directed graph
     * @return FindComponentsDfsResult
     */
    public FindComponentsDfsResult findComponents(UnDiGraph graph) {
        FindComponentsDfsAlgo dfsAlgo = new FindComponentsDfsAlgo(graph, 0);
        dfs(dfsAlgo);
        return new FindComponentsDfsAlgo.ResultBuilder(dfsAlgo).build();
    }

    public static void dfs(DfsAlgo dfsAlgo) {
        while(dfsAlgo.hasNextStep()) {
            dfsAlgo.stepToNextVertex();
            if(dfsAlgo.isCurrentVisited()) {
                dfsAlgo.handleProcessedVertex();
            } else {
                dfsAlgo.handleNewVertex();
                dfsAlgo.pushAdjToVisit();
            }
        }
    }

    public static void dfs(Graph graph, int start) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(start);
        boolean[] marked = new boolean[graph.vertices()];
        LinkedList<Integer> fullPath = new LinkedList<>();
        LinkedList<Integer> postOrder = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        int[] markedStackIndex = new int[graph.vertices()];
        markedStackIndex[0] = 0;
        while(!stack.isEmpty()) { // hasNext()
            int v = stack.getFirst();  // getFirst()
            fullPath.add(v); //
            if(marked[v]) {
                if(stack.size() == markedStackIndex[v]) { // stackSize()
                    postOrder.add(v); // returnTo(v)
                    path.add(v);
                }
                stack.pop(); // removeFirst()
            } else {
                marked[v] = true;
                markedStackIndex[v] = stack.size(); // stackSize()
                path.add(v);
                // firstTime
                List<Integer> adj = graph.adj(v); // adj
                if(!adj.isEmpty()) { // Note: for Directed graph - other logic
                    Iterable<Integer> reverseAdj = getReverseOrder(adj);
                    for(Integer w : reverseAdj) {
                        stack.push(w); // push
                    }
                }
            }
        }
        System.out.println("marked: " + Arrays.toString(marked));
        System.out.println("fullPath: " + fullPath);
        System.out.println("path: " + path);
        System.out.println("postOrder: " + postOrder);
    }

    private static Iterable<Integer> getReverseOrder(List<Integer> adj) {
        LinkedList<Integer> result = new LinkedList<>();
        adj.forEach(result::addFirst);
        return result;
    }

    public static void main(String[] args) {

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();

        UnDiGraph unDiGraph = new UnDiGraphImpl(5);
        unDiGraph.addEdge(0, 1);
        unDiGraph.addEdge(0, 2);
        unDiGraph.addEdge(2, 3);
        unDiGraph.addEdge(2, 4);
        unDiGraph.addEdge(3, 4);
        System.out.println("Input graph:");
        System.out.println(unDiGraph);
        System.out.println(Arrays.toString(depthFirstSearch.findReachableComponents(unDiGraph, 0).getMarked()));
        System.out.println(Arrays.toString(depthFirstSearch.findComponents(unDiGraph).getIds()));
        System.out.println("Parents: " + Arrays.toString(depthFirstSearch.findPaths(unDiGraph, 0).getParentIds()));
        System.out.println("postOrder: " + depthFirstSearch.findPaths(unDiGraph, 0).getPostOrder());
        System.out.println("path: " + depthFirstSearch.findPaths(unDiGraph, 0).getPath());


        UnDiGraph graph = new UnDiGraphImpl(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        System.out.println("Input graph:");
        dfs(graph, 0);
    }
}
