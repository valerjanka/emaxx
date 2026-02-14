package com.val.algos.generic.graph;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class DiGraphImplTest {

    @Test
    public void testAddEdge() {
        DiGraphImpl graph = new DiGraphImpl(5);
        assertEquals(0, graph.edges());
        graph.addEdge(0, 1);
        assertEquals(1, graph.edges());
        List<Integer> adj = graph.adj(0);
        assertEquals(1, adj.size());
        assertTrue(adj.contains(1));

        graph.addEdge(0, 2);
        assertEquals(2, graph.edges());
        adj = graph.adj(0);
        assertEquals(2, adj.size());
        assertTrue(adj.contains(2));
    }

    @Test
    public void testRemoveAdj() {
        DiGraphImpl graph = new DiGraphImpl(5);
        graph.addEdge(0, 1);
        assertTrue(graph.removeAdj(0, 1));
        assertFalse(graph.removeAdj(0, 1));
        List<Integer> adj = graph.adj(0);
        assertEquals(0, adj.size());
    }

    @Test
    public void testReverse() {
        DiGraphImpl graph = new DiGraphImpl(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        DiGraph reversed = graph.reverse();
        List<Integer> adj1 = reversed.adj(1);
        assertTrue(adj1.contains(0));

        List<Integer> adj2 = reversed.adj(2);
        assertTrue(adj2.contains(1));

        assertEquals(0, reversed.adj(0).size());
    }

    @Test
    public void testToString() {
        DiGraphImpl graph = new DiGraphImpl(2);
        graph.addEdge(0, 1);
        String s = graph.toString();
        // 0: {1}
        // 1: {}
        assertTrue(s.contains("0: {1}"));
        assertTrue(s.contains("1: {}"));
    }
}
