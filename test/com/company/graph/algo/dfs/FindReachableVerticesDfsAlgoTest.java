package com.company.graph.algo.dfs;

import com.company.graph.Graph;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by valerii.ryzhuk on 11/15/2015.
 */
public class FindReachableVerticesDfsAlgoTest {
    private static final int START_0 = 0;
    private static final int START_1 = 1;
    private static final int VERTICES = 4;

    private static final List<Integer> ADJ_0 = Arrays.asList(1, 2, 3);
    private static final List<Integer> ADJ_0_ANSWER = Arrays.asList(1, 2, 3);
    private static final List<Integer> ADJ_0_ANSWER_WITH_MARKED_1 = Arrays.asList(2, 3);
    private static final List<Integer> ADJ_1 = Arrays.asList(0, 2, 3);
    private static final List<Integer> ADJ_1_ANSWER = Arrays.asList(0, 2, 3);

    private static final boolean[] markedNone = new boolean[]{false, false, false, false};
    private static final boolean[] marked0 = new boolean[]{true, false, false, false};

    private Graph graph;

    @Before
    public void setUp() {
        graph = Mockito.mock(Graph.class);
        when(graph.vertices()).thenReturn(VERTICES);
    }

    @Test
    public void testHasNextStepPushAdj() throws Exception {
        when(graph.adj(START_0)).thenReturn(ADJ_0);
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

}