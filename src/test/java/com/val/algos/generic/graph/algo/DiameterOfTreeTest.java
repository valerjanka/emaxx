package com.val.algos.generic.graph.algo;

import com.val.algos.generic.graph.UnDiGraph;
import com.val.algos.generic.graph.UnDiGraphImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author valerjanka
 */
public class DiameterOfTreeTest {

    @Test
    public void testGetDiameter() throws Exception {
        UnDiGraph graph = new UnDiGraphImpl(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        List<Integer> result = DiameterOfTree.getDiameter(graph);
        assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5), result);
    }

    @Test
    public void testGetDiameter2() throws Exception {
        UnDiGraph graph = new UnDiGraphImpl(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);

        List<Integer> result = DiameterOfTree.getDiameter(graph);
        assertEquals(Arrays.asList(0, 1, 2), result);
    }

    @Test
    public void testGetDiameter3() throws Exception {
        UnDiGraph graph = new UnDiGraphImpl(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);

        List<Integer> result = DiameterOfTree.getDiameter(graph);
        assertEquals(Arrays.asList(0, 1, 2, 4, 5), result);
    }
}