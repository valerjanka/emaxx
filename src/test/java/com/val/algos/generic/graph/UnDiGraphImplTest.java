package com.val.algos.generic.graph;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class UnDiGraphImplTest {

    @Test
    public void testAddEdge() {
        UnDiGraphImpl graph = new UnDiGraphImpl(3);
        assertEquals(0, graph.edges());

        graph.addEdge(0, 1);
        assertEquals(1, graph.edges());

        List<Integer> adj0 = graph.adj(0);
        assertTrue(adj0.contains(1));

        List<Integer> adj1 = graph.adj(1);
        assertTrue(adj1.contains(0));
    }

    @Test
    public void testRemoveAdj() {
        UnDiGraphImpl graph = new UnDiGraphImpl(3);
        graph.addEdge(0, 1);

        assertTrue(graph.removeAdj(0, 1));
        assertFalse(graph.removeAdj(0, 1));

        List<Integer> adj0 = graph.adj(0);
        assertFalse(adj0.contains(1));

        List<Integer> adj1 = graph.adj(1);
        assertFalse(adj1.contains(0));
    }

    @Test
    public void testDegree() {
        UnDiGraphImpl graph = new UnDiGraphImpl(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        assertEquals(2, graph.degree(0));
        assertEquals(1, graph.degree(1));
        assertEquals(1, graph.degree(2));
    }
}
