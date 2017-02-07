import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeVertices() throws Exception {
        new Graph(-1);
    }

    @Test
    public void shouldCreateEmptyGraph() throws Exception {

        Graph g = new Graph(0);
        assertTrue(g.isEmpty());
        assertEquals(0, g.amountOfVertex());
        assertEquals(0, g.amountOfEdges());
        assertFalse(g.containsVertex(-1));
        assertFalse(g.containsVertex(0));
        assertFalse(g.containsVertex(1));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldNotAddEdgeToEmptyGraph() throws Exception {
        Graph g = new Graph(0);
        g.addEdge(0, 0, 3.0);
    }

    @Test
    public void shouldCreateGraphOfGivenSize() throws Exception {

        int size = 5;
        Graph g = new Graph(size);

        assertEquals(size, g.amountOfVertex());
        assertEquals(0, g.amountOfEdges());

        assertFalse(g.containsVertex(size));
        assertFalse(g.containsVertex(-1));
        for (int vertex = 0; vertex < size; vertex++)
            assertTrue(g.containsVertex(vertex));

    }

    @Test
    public void shouldAddEdgesBetweenExistingVertices() throws Exception {
        Graph graph = new Graph(3);
        assertTrue(graph.addEdge(0, 0, 0.0));
        assertTrue(graph.addEdge(0, 1, 1.0));
        assertTrue(graph.addEdge(0, 2, 3.0));
        assertTrue(graph.addEdge(1, 2, 4.0));
        assertEquals(4, graph.amountOfEdges());
    }

    @Test
    public void shouldNotAddEdgeIfAlreadyExists() {
        Graph graph = new Graph(3);
        assertTrue (graph.addEdge(0, 1, 2.0));
        assertFalse(graph.addEdge(0, 1, 2.0));
        assertFalse(graph.addEdge(0, 1, 9.0));
        //assertTrue (graph.addEdge(0, 1, 1.0));
        assertEquals(1, graph.amountOfEdges());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void shouldNotAddInvalidEdge1() throws Exception {
        new Graph(3).addEdge(1, -1, 3.0);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void shouldNotAddInvalidEdge2() throws Exception {
        new Graph(7).addEdge(0, 10, 1.0);
    }

    @Test
    public void testGraphSummary() throws Exception {
        Graph graph = new Graph(3);

        assertEquals("Graph { 3 vertices; 0 edges; total weight = 0.0 }", graph.toString());
        graph.addEdge(0, 0, 0.0);
        graph.addEdge(0, 1, 1.0);
        graph.addEdge(0, 2, 3.0);
        graph.addEdge(1, 2, 6.0);
        assertEquals("Graph { 3 vertices; 4 edges; total weight = 10.0 }", graph.toString());
    }

    @Test
    public void shouldNotBeConnected() {
        Graph graph = new Graph(7);
        assertFalse(graph.isConnected());
        graph.addEdge(0, 3, 1.0);
        graph.addEdge(2, 3, 1.0);
        graph.addEdge(3, 4, 1.0);
        graph.addEdge(5, 4, 1.0);
        graph.addEdge(0, 6, 1.0);
        assertFalse(graph.isConnected());
    }


    @Test
    public void shouldBeConnected() {
        Graph graph = new Graph(7);
        assertFalse(graph.isConnected());
        graph.addEdge(0, 3, 1.0);
        graph.addEdge(2, 3, 1.0);
        graph.addEdge(3, 4, 1.0);
        graph.addEdge(5, 4, 1.0);
        graph.addEdge(0, 6, 1.0);
        graph.addEdge(0, 1, 9.0);
        assertTrue(graph.isConnected());
    }

    @Test
    public void shouldReturnOrderedListOfEdges() throws Exception {
        Graph graph = new Graph(3);
        graph.addEdge(2, 1, 0.5);
        graph.addEdge(0, 2, 1.0);
        graph.addEdge(1, 0, 1.0);
        graph.addEdge(0, 0, 0.0);
        List<Edge> expectedList = Arrays.asList(
                new Edge(0, 0, 0.0),
                new Edge(1, 2, 0.5),
                new Edge(0, 1, 1.0),
                new Edge(0, 2, 1.0)
        );
        assertEquals(expectedList, graph.edges());
    }
}
