package com.emaxx.algos.graph.algo.dfs;

import com.emaxx.algos.graph.Graph;
import com.emaxx.algos.graph.UnDiGraph;
import com.emaxx.algos.graph.UnDiGraphImpl;
import com.emaxx.algos.graph.algo.dfs.result.FindComponentsDfsResult;
import com.emaxx.algos.graph.algo.dfs.result.FindPathDfsResult;
import com.emaxx.algos.graph.algo.dfs.result.FindReachableVerticesDfsResult;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Implements depth first search with different algorithms
 * Created by valerii.ryzhuk on 11/13/2015.
 */
public class DepthFirstSearch
{
    LinkedList<Integer> fullPath = new LinkedList<>();
    LinkedList<Integer> postOrder = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] marked;
    int[] markedStackIndex;
    Graph graph;
    int start;

    public DepthFirstSearch(Graph graph, int start)
    {
        this.graph = graph;
        this.start = start;
        marked = new boolean[graph.vertices()];
        markedStackIndex = new int[graph.vertices()];
        dfs();
    }

    /**
     * Perform depth first search
     */
    public void dfs()
    {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(start);
        markedStackIndex[0] = 0;
        while ( !stack.isEmpty() )
        { // hasNext()
            int v = stack.getFirst();  // getFirst(), as in Java Stack is adding and removing elements from the head
            fullPath.add(v); //
            if ( marked[v] )
            {
                if ( stack.size() == markedStackIndex[v] )
                { // we met all childs and return back to vertex V
                    postOrder.add(v);
                    path.add(v);
                }
                stack.pop(); // removeFirst()
            }
            else
            {
                marked[v] = true;
                markedStackIndex[v] = stack.size(); // we met V in the first time
                path.add(v);
                List<Integer> adj = graph.adj(v); // adj
                if ( !adj.isEmpty() )
                { // Note: for Directed graph - other logic
                    ListIterator<Integer> reverseAdjIterator = adj.listIterator(adj.size());
                    while ( reverseAdjIterator.hasPrevious() )
                    {
                        Integer w = reverseAdjIterator.previous();
                        stack.push(w); // push
                    }
                }
            }
        }
    }

    public static FindPathDfsResult findPaths(Graph graph, int start)
    {
        FindReachablePathDfsAlgo dfsAlgo = new FindReachablePathDfsAlgo(graph, start);
        dfs(dfsAlgo);
        return new FindReachablePathDfsAlgo.ResultBuilder(dfsAlgo).build();
    }

    public static FindReachableVerticesDfsResult findReachableComponents(Graph graph, int start)
    {
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
    public static FindComponentsDfsResult findComponents(UnDiGraph graph)
    {
        FindComponentsDfsAlgo dfsAlgo = new FindComponentsDfsAlgo(graph, 0);
        dfs(dfsAlgo);
        return new FindComponentsDfsAlgo.ResultBuilder(dfsAlgo).build();
    }

    public static void dfs(DfsAlgo dfsAlgo)
    {
        while ( dfsAlgo.hasNextStep() )
        {
            dfsAlgo.stepToNextVertex();
            if ( dfsAlgo.isCurrentVisited() )
            {
                dfsAlgo.handleProcessedVertex();
            }
            else
            {
                dfsAlgo.handleNewVertex();
            }
        }
    }

    private static Iterable<Integer> getReverseOrder(List<Integer> adj)
    {
        LinkedList<Integer> result = new LinkedList<>();
        for ( int v : adj )
        {
            result.addFirst(v);
        }
        return result;
    }

    public static void main(String[] args)
    {
        UnDiGraph unDiGraph = new UnDiGraphImpl(5);
        unDiGraph.addEdge(0, 1);
        unDiGraph.addEdge(0, 2);
        unDiGraph.addEdge(2, 3);
        unDiGraph.addEdge(2, 4);
        unDiGraph.addEdge(3, 4);
        System.out.println("Input graph:");
        System.out.println(unDiGraph);
        System.out.println(Arrays.toString(DepthFirstSearch.findReachableComponents(unDiGraph, 0).getMarked()));
        System.out.println(Arrays.toString(DepthFirstSearch.findComponents(unDiGraph).getComponentIds()));
        System.out.println("Parents: " + Arrays.toString(DepthFirstSearch.findPaths(unDiGraph, 0).getParentIds()));
        System.out.println("postOrder: " + DepthFirstSearch.findPaths(unDiGraph, 0).getPostOrder());
        System.out.println("path: " + DepthFirstSearch.findPaths(unDiGraph, 0).getPath());


        UnDiGraph graph = new UnDiGraphImpl(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        System.out.println("Input graph:");
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
        System.out.println("marked: " + Arrays.toString(dfs.marked));
        System.out.println("fullPath: " + dfs.fullPath);
        System.out.println("path: " + dfs.path);
        System.out.println("postOrder: " + dfs.postOrder);
    }
}
