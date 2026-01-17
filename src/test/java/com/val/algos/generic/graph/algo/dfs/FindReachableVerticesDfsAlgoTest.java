package com.val.algos.generic.graph.algo.dfs;

import com.val.algos.generic.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author valerjanka
 */
public class FindReachableVerticesDfsAlgoTest {
    private static final int START_0 = 0;
    private static final int VERTICES = 4;

    private static final List<Integer> ADJ_0 = Arrays.asList(1, 2, 3);
    private static final List<Integer> ADJ_0_ANSWER = Arrays.asList(1, 2, 3);

    private Graph graph;

    @Before
    public void setUp() {
        graph = new GraphStub();
    }

    @Test
    public void testHasNextStepPushAdj() throws Exception {
        FindReachableVerticesDfsAlgo dfsAlgo = new FindReachableVerticesDfsAlgo(graph, START_0);
        assertTrue(dfsAlgo.hasNextStep());
        assertEquals(START_0, dfsAlgo.stepToNextVertex());
        dfsAlgo.pushAdjToVisit();
        for (int v : ADJ_0_ANSWER) {
            assertEquals(v, dfsAlgo.stepToNextVertex());
            dfsAlgo.handleProcessedVertex();
        }
        assertEquals(START_0, dfsAlgo.stepToNextVertex());
        dfsAlgo.handleProcessedVertex();
        assertFalse(dfsAlgo.hasNextStep());
    }

    @Test(expected = IllegalStateException.class)
    public void testPopWithoutVertex() throws Exception {
        FindReachableVerticesDfsAlgo dfsAlgo = new FindReachableVerticesDfsAlgo(graph, START_0);
        assertTrue(dfsAlgo.hasNextStep());
        dfsAlgo.handleProcessedVertex();
        assertFalse(dfsAlgo.hasNextStep());
        dfsAlgo.handleProcessedVertex();
    }

    @Test
    public void testVertices() throws Exception {
        FindReachableVerticesDfsAlgo dfsAlgo = new FindReachableVerticesDfsAlgo(graph, START_0);
        assertEquals(VERTICES, dfsAlgo.vertices());
    }

    @Test
    public void testGetStart() throws Exception {
        FindReachableVerticesDfsAlgo dfsAlgo = new FindReachableVerticesDfsAlgo(graph, START_0);
        assertEquals(START_0, dfsAlgo.getStart());
    }

    private static class GraphStub implements Graph {
        @Override
        public List<Integer> adj(int v) {
            if (v == START_0) return ADJ_0;
            return Collections.emptyList();
        }

        @Override
        public boolean removeAdj(int v, int w) {
            return false;
        }

        @Override
        public void addEdge(int v, int w) {
        }

        @Override
        public int vertices() {
            return VERTICES;
        }

        @Override
        public int edges() {
            return 0;
        }
    }
}
