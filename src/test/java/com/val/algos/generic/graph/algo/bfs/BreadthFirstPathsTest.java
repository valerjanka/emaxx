package com.val.algos.generic.graph.algo.bfs;

import com.val.algos.generic.graph.DiGraphImpl;
import org.junit.Test;
import static org.junit.Assert.*;

public class BreadthFirstPathsTest {
    @Test
    public void testBfs() {
        DiGraphImpl graph = new DiGraphImpl(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, 0);

        assertTrue(bfs.getMarked()[4]);
        assertEquals(3, bfs.getDistTo()[4]);
        assertEquals(0, bfs.getDistTo()[0]);
        assertEquals(1, bfs.getDistTo()[1]);
        assertEquals(1, bfs.getDistTo()[2]);
        assertEquals(2, bfs.getDistTo()[3]);
    }
}
