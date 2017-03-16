package com.val.algos.generic.graph.algo;

import com.val.algos.generic.graph.UnDiGraph;
import com.val.algos.generic.graph.UnDiGraphImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author valerjanka
 */
public class CenterOfTreeTest {

    @Test
    public void testGetCenterWith3Nodes() throws Exception {
        UnDiGraph graph = new UnDiGraphImpl(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        assertEquals(1, CenterOfTree.getCenter(graph));
    }

    @Test
    public void testGetCenterWith5Nodes() throws Exception {
        UnDiGraph graph = new UnDiGraphImpl(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 1);
        graph.addEdge(4, 1);
        assertEquals(1, CenterOfTree.getCenter(graph));
    }

    @Test
    public void testGetCenterWith5Nodes2() throws Exception {
        UnDiGraph graph = new UnDiGraphImpl(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 2);
        graph.addEdge(4, 3);
        assertEquals(2, CenterOfTree.getCenter(graph));
    }

    @Test
    public void testGetCenterWith2() throws Exception {
        UnDiGraph graph = new UnDiGraphImpl(2);
        graph.addEdge(0, 1);
        assertTrue(CenterOfTree.getCenter(graph) == 1 || CenterOfTree.getCenter(graph) == 0);
    }

    @Test
    public void testGetCenterWith4() throws Exception {
        UnDiGraph graph = new UnDiGraphImpl(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 2);
        assertTrue(CenterOfTree.getCenter(graph) == 1 || CenterOfTree.getCenter(graph) == 2);
    }

    @Test
    public void testGetCenterWith6() throws Exception {
        UnDiGraph graph = new UnDiGraphImpl(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 2);
        graph.addEdge(4, 2);
        graph.addEdge(5, 2);
        assertTrue(CenterOfTree.getCenter(graph) == 2);
    }
}