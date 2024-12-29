package com.val.algos.generic.graph.algo;

import java.util.*;

public class BfsAndDfsForContest
{
    public static void main(String[] args)
    {
        DiGraph g = new DiGraph(3);
        g.addEdge(0, 1);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 0);
        BreadthFirstSearch bfs = new BreadthFirstSearch(g, 2);
        System.out.println(Arrays.toString(bfs.distTo));
        System.out.println(Arrays.toString(bfs.getParent()));

        DepthFirstSearch dfs = new DepthFirstSearch(g, 2);
        System.out.println(dfs.getOrder());
        System.out.println(dfs.getPath());
        System.out.println(dfs.getPostOrder());
    }
}

class BreadthFirstSearch
{
    private DiGraph graph;
    private boolean[] marked;
    private int[] parent;
    int[] distTo;
    private int source;

    public BreadthFirstSearch(DiGraph graph, int source)
    {
        init(graph, source);
        bfs();
    }

    private void bfs()
    {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        marked[source] = true;
        parent[source] = source;
        while ( !queue.isEmpty() )
        {
            int v = queue.poll();
            for ( int next : graph.getAdjacencyList(v) )
            {
                if ( !marked[next] )
                {
                    marked[next] = true;
                    parent[next] = v;
                    distTo[next] = distTo[v] + 1;
                    queue.offer(next);
                }
            }
        }
    }

    public int[] getParent()
    {
        return parent;
    }

    private void init(DiGraph graph, int source)
    {
        this.graph = graph.clone();
        marked = new boolean[graph.vertices()];
        parent = new int[graph.vertices()];
        distTo = new int[graph.vertices()];
        this.source = source;
    }
}

class DepthFirstSearch
{
    private DiGraph graph;
    private int source;
    private boolean[] marked;
    private List<Integer> order;
    private List<Integer> postOrder;
    private List<Integer> path;

    public DepthFirstSearch(DiGraph g, int source)
    {
        validateInput(g, source);
        init(g, source);
        dfs();
    }

    private void validateInput(DiGraph g, int source)
    {

    }

    private void init(DiGraph g, int source)
    {
        graph = g.clone();
        this.source = source;
        marked = new boolean[g.vertices()];
        order = new LinkedList<>();
        postOrder = new LinkedList<>();
        path = new LinkedList<>();
    }

    private void dfs()
    {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(source);
        int[] markedStackSize = new int[graph.vertices()];
        while ( !stack.isEmpty() )
        {
            int v = stack.getFirst();
            path.add(v);
            if ( !marked[v] )
            {
                order.add(v);
                marked[v] = true;
                markedStackSize[v] = stack.size();
                graph.getAdjacencyList(v).forEach(stack::push);
            }
            else
            {
                if ( markedStackSize[v] == stack.size() )
                {
                    path.add(v);
                    postOrder.add(v);
                }
                stack.pop();
            }
        }
    }

    public List<Integer> getOrder()
    {
        return order;
    }

    public List<Integer> getPostOrder()
    {
        return postOrder;
    }

    public List<Integer> getPath()
    {
        return path;
    }
}

class DiGraph
{
    private final ArrayList<LinkedList<Integer>> verticesToAdjacencyList;

    public DiGraph(int v)
    {
        verticesToAdjacencyList = new ArrayList<>(v);
        for ( int i = 0; i < v; i++ )
        {
            verticesToAdjacencyList.add(new LinkedList<>());
        }
    }

    public DiGraph(DiGraph graph)
    {
        verticesToAdjacencyList = (ArrayList<LinkedList<Integer>>)graph.verticesToAdjacencyList.clone();
    }

    public LinkedList<Integer> getAdjacencyList(int s)
    {
        return verticesToAdjacencyList.get(s); // mutable
        // return verticesToAdjacencyList.get(s).clone(); // immutable
    }

    public void addEdge(int from, int to)
    {
        verticesToAdjacencyList.get(from).add(to);
        //verticesToAdjacencyList.get(to).add(from); // for Un-directed Graph
    }

    public int vertices()
    {
        return verticesToAdjacencyList.size();
    }

    public DiGraph clone()
    {
        return new DiGraph(this);
    }
}
