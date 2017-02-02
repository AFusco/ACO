import model.Edge;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class Edge_ {

    @Test
    public void should_throw_an_exception_if_some_vertex_is_lower_than_zero() throws Exception {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Edge(1, -1, 3.0))
                .withMessage("vertex(-1): Vertices must not be negative");
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Edge(-7, 1, 3.0))
                .withMessage("vertex(-7): Vertices must not be negative");
    }

    @Test
    public void vertices_on_edges_are_ordered_from_low_to_high() throws Exception {
        assertThat(new Edge(9, 5, 3.0).toString(), is("Edge {5,9} weight {3.0}"));
        assertThat(new Edge(5, 9, 3.0).toString(), is("Edge {5,9} weight {3.0}"));
        assertThat(new Edge(3, 3, 3.0).toString(), is("Edge {3,3} weight {3.0}"));
    }

    @Test
    public void should_know_if_contains_a_loop() throws Exception {
        assertTrue(new Edge(1, 1, 7.0).isLoop());
        assertTrue(new Edge(1, 1, 7.0).isLoop());
        assertFalse(new Edge(1, 10, 7.0).isLoop());
        assertFalse(new Edge(1, 10, 7.0).isLoop());
    }


    @Test
    public void edges_with_the_same_weight_and_vertices_are_equal() throws Exception {
        int vertex1 = 1, vertex2 = 2;
        assertThat(new Edge(vertex1, vertex2, 9.0), is(new Edge(vertex1, vertex2, 9.0)));
        assertThat(new Edge(vertex1, vertex2, 9.0), is(new Edge(vertex2, vertex1, 9.0)));
    }

    @Test
    public void edges_with_distinct_weight_and_same_vertices_are_not_equal() throws Exception {
        int vertex1 = 1, vertex2 = 2;
        assertThat(new Edge(vertex1, vertex2, 9.0), is(not(new Edge(vertex1, vertex2, 12.0))));
    }

    @Test
    public void edges_with_distinct_vertices_are_not_equal() throws Exception {
        double weight = 9;
        assertThat(new Edge(1, 2, weight), is(not(new Edge(1, 1, weight))));
    }

    @Test
    public void should_order_edges_by_weight_from_low_to_high() throws Exception {
        assertThat(orderList(edge(5.0), edge(5.0), edge(5.0)), is(expectedList(edge(5.0), edge(5.0), edge(5.0))));
        assertThat(orderList(edge(3.9), edge(3.2), edge(3.0)), is(expectedList(edge(3.0), edge(3.2), edge(3.9))));
        assertThat(orderList(edge(9.0), edge(9.0), edge(5.0)), is(expectedList(edge(5.0), edge(9.0), edge(9.0))));
        assertThat(orderList(edge(9.0), edge(9.5), edge(5.0)), is(expectedList(edge(5.0), edge(9.0), edge(9.5))));
        assertThat(orderList(edge(2.0), edge(9.0), edge(69.0)), is(expectedList(edge(2.0), edge(9.0), edge(69.0))));
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














