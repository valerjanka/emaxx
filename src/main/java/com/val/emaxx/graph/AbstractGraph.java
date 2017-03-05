package com.val.emaxx.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @author valerjanka
 */
public abstract class AbstractGraph implements Graph {
    LinkedList<Integer>[] verticesToAdjacencyList;

    @SuppressWarnings("unchecked")
    AbstractGraph(int vertices) {
        verticesToAdjacencyList = (LinkedList<Integer>[]) new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            verticesToAdjacencyList[i] = new LinkedList<>();
        }
    }

    @Override
    public List<Integer> adj(int v) {
        return verticesToAdjacencyList[v];
    }

    @Override
    public int vertices() {
        return verticesToAdjacencyList.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int v = 0; v < vertices(); v++) {
            sb.append(v).append(": {");
            boolean isFirst = true;
            for(int w : adj(v)) {
                if(!isFirst) {
                    sb.append(", ");
                }
                sb.append(w);
                isFirst = false;
            }
            sb.append("}\n");
        }
        return sb.toString();
    }

}
