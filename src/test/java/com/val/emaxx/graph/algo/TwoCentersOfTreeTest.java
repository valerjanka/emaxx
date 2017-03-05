package com.val.emaxx.graph.algo;

import com.val.emaxx.graph.UnDiGraph;
import com.val.emaxx.graph.UnDiGraphImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author valerjanka
 */
public class TwoCentersOfTreeTest {
    private TwoCentersOfTree twoCentersOfTree;

    @Test
    public void test1Vertex() {
        UnDiGraph graph = new UnDiGraphImpl(1);
        twoCentersOfTree = new TwoCentersOfTree(graph);
        assertEquals(0, twoCentersOfTree.getFirstCenter());
        assertEquals(0, twoCentersOfTree.getSecondCenter());
    }

    @Test
    public void test2Vertices() {
        UnDiGraph graph = new UnDiGraphImpl(2);
        graph.addEdge(0, 1);
        twoCentersOfTree = new TwoCentersOfTree(graph);
        assertEquals(0, twoCentersOfTree.getFirstCenter());
        assertEquals(1, twoCentersOfTree.getSecondCenter());
    }

    @Test
    public void test3Vertices() {
        UnDiGraph graph = new UnDiGraphImpl(3);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        twoCentersOfTree = new TwoCentersOfTree(graph);
        assertEquals(1, twoCentersOfTree.getFirstCenter());
        assertEquals(1, twoCentersOfTree.getSecondCenter());
    }

    @Test
    public void test4Vertices() {
        UnDiGraph graph = new UnDiGraphImpl(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        twoCentersOfTree = new TwoCentersOfTree(graph);
        assertEquals(1, twoCentersOfTree.getFirstCenter());
        assertEquals(2, twoCentersOfTree.getSecondCenter());
    }

    @Test
    public void testWithLongMiddleVertices() {
        UnDiGraph graph = new UnDiGraphImpl(7);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(4, 3);
        graph.addEdge(2, 5);
        graph.addEdge(5, 6);
        twoCentersOfTree = new TwoCentersOfTree(graph);
        assertEquals(2, twoCentersOfTree.getFirstCenter());
        assertEquals(2, twoCentersOfTree.getSecondCenter());
    }

    @Test
    public void testWithShortMiddleVertices() {
        UnDiGraph graph = new UnDiGraphImpl(8);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(4, 3);
        graph.addEdge(4, 5);
        graph.addEdge(6, 5);
        graph.addEdge(3, 7);
        twoCentersOfTree = new TwoCentersOfTree(graph);
        assertEquals(2, twoCentersOfTree.getFirstCenter());
        assertEquals(4, twoCentersOfTree.getSecondCenter());
    }
}