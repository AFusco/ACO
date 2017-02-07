import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class EdgeTest {
    @Test (expected = IllegalArgumentException.class)
    public void testNegativeVertex1() throws Exception {
        new Edge(1, -1, 3.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNegativeVertex2() throws Exception {
        new Edge(-7, 3, 3.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNegativeWeight() throws Exception {
        new Edge(7, 3, -3.0);
    }

    @Test
    public void vertices_on_edges_are_ordered_from_low_to_high() throws Exception {
        assertEquals("Edge {5,9} weight {3.0}", new Edge(9, 5, 3.0).toString());
        assertEquals("Edge {5,9} weight {3.0}", new Edge(5, 9, 3.0).toString());
        assertEquals("Edge {3,3} weight {3.0}", new Edge(3, 3, 3.0).toString());
    }

    @Test
    public void testIsLoop() {
        assertTrue(new Edge(1, 1, 7.0).isLoop());
        assertTrue(new Edge(1, 1, 7.0).isLoop());
        assertFalse(new Edge(1, 10, 7.0).isLoop());
        assertFalse(new Edge(1, 10, 7.0).isLoop());
    }

    @Test
    public void testEquality() throws Exception {

        int vertex1 = 1, vertex2 = 2;

        assertEquals(new Edge(vertex1, vertex2, 9.0), new Edge(vertex1, vertex2, 9.0));
        assertEquals(new Edge(vertex1, vertex2, 9.0), new Edge(vertex2, vertex1, 9.0));
    }

    @Test
    public void testWeightInequality() throws Exception {
        int vertex1 = 1, vertex2 = 2;
        assertNotEquals(new Edge(vertex1, vertex2, 9.0), new Edge(vertex1, vertex2, 12.0));
    }

    @Test
    public void testVerticesInequality() throws Exception {
        double weight = 9;
        assertNotEquals(new Edge(1, 2, weight), new Edge(1, 1, weight));
    }

    @Test
    public void testSortComparison() throws Exception {
        assertEquals(
                expectedList(edge(5.0), edge(5.0), edge(5.0)),
                orderList(edge(5.0), edge(5.0), edge(5.0)));

        assertEquals(
                orderList(edge(3.9), edge(3.2), edge(3.0)),
                expectedList(edge(3.0), edge(3.2), edge(3.9)));
        assertEquals(
                expectedList(edge(5.0), edge(9.0), edge(9.0)),
                orderList(edge(9.0), edge(9.0), edge(5.0)));
        assertEquals(
                expectedList(edge(5.0), edge(9.0), edge(9.5)),
                orderList(edge(9.0), edge(9.5), edge(5.0)));
        assertEquals(
                expectedList(edge(2.0), edge(9.0), edge(69.0)),
                orderList(edge(2.0), edge(9.0), edge(69.0)));
    }
    



    private Edge edge(double weight) {
        return new Edge(1, 1, weight);
    }

    private List<Edge> expectedList(Edge... edges) {
        return Arrays.asList(edges);
    }

    private List<Edge> orderList(Edge... edges) {
        List<Edge> list = Arrays.asList(edges);
        Collections.sort(list);
        return list;
    }
}
