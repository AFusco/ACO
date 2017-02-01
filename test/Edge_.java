import model.Edge;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import static model.Edge.*;

public class Edge_ {

    @Test
    public void should_throw_an_exception_if_some_vertex_is_lower_than_zero() throws Exception {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> edge(1, -1, 3.0))
                .withMessage("vertex(-1): Vertices must not be negative");
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> directedEdge(-7, 1, 3.0))
                .withMessage("vertex(-7): Vertices must not be negative");
    }


    @Test
    public void should_throw_an_exception_if_discriminating_vertex_is_not_inside_the_edge() throws Exception {
        Edge edge = edge(1, 2, 3.0);
        int discriminatingVertex = 100;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> edge.getOtherVertex(discriminatingVertex))
                .withMessage("The vertex {" + discriminatingVertex + "} is not inside this edge");
    }


    @Test
    public void vertices_on_undirected_edges_are_ordered_from_low_to_high() throws Exception {
        assertThat(edge(9, 5, 3.0).toString(), is("Edge between vertices {5,9} and weight {3.0}"));
        assertThat(edge(5, 9, 3.0).toString(), is("Edge between vertices {5,9} and weight {3.0}"));
        assertThat(edge(3, 3, 3.0).toString(), is("Edge between vertices {3,3} and weight {3.0}"));
    }


    @Test
    public void vertices_on_directed_edges_are_not_ordered() throws Exception {
        assertThat(directedEdge(9, 5, 3.0).toString(), is("Edge between vertices {9,5} and weight {3.0}"));
        assertThat(directedEdge(5, 9, 3.0).toString(), is("Edge between vertices {5,9} and weight {3.0}"));
        assertThat(directedEdge(3, 3, 3.0).toString(), is("Edge between vertices {3,3} and weight {3.0}"));
    }


    @Test
    public void should_know_if_contains_a_loop() throws Exception {
        assertTrue(directedEdge(1, 1, 7.0).isLoop());
        assertTrue(edge(1, 1, 7.0).isLoop());
        assertFalse(directedEdge(1, 10, 7.0).isLoop());
        assertFalse(edge(1, 10, 7.0).isLoop());
    }


    @Test
    public void edges_with_the_same_weight_and_vertices_are_equal() throws Exception {
        double weight = 9;
        int vertex1 = 1, vertex2 = 2;
        assertThat(edge(vertex1, vertex2, weight), is(edge(vertex1, vertex2, weight)));
        assertThat(edge(vertex1, vertex2, weight), is(edge(vertex2, vertex1, weight)));
        assertThat(directedEdge(vertex1, vertex2, weight), is(directedEdge(vertex1, vertex2, weight)));
        assertThat(directedEdge(vertex1, vertex2, weight), is(not(directedEdge(vertex2, vertex1, weight))));
    }


    @Test
    public void edges_with_distinct_weight_or_vertices_are_not_equal() throws Exception {
        double weight = 9;
        int vertex1 = 1, vertex2 = 2;
        assertThat(edge(vertex1, vertex2, weight), is(not(edge(vertex1, vertex2, weight + 3))));
        assertThat(edge(vertex1, vertex2, weight), is(not(edge(vertex1, vertex1, weight))));
    }


    @Test
    public void should_order_edges_by_weight_from_low_to_high() throws Exception {
        assertThat(orderList(newEdge(5.0), newEdge(5.0), newEdge(5.0)), is(expectedList(newEdge(5.0), newEdge(5.0), newEdge(5.0))));
        assertThat(orderList(newEdge(3.9), newEdge(3.2), newEdge(3.0)), is(expectedList(newEdge(3.0), newEdge(3.2), newEdge(3.9))));
        assertThat(orderList(newEdge(9.0), newEdge(9.0), newEdge(5.0)), is(expectedList(newEdge(5.0), newEdge(9.0), newEdge(9.0))));
        assertThat(orderList(newEdge(9.0), newEdge(9.5), newEdge(5.0)), is(expectedList(newEdge(5.0), newEdge(9.0), newEdge(9.5))));
        assertThat(orderList(newEdge(2.0), newEdge(9.0), newEdge(69.0)), is(expectedList(newEdge(2.0), newEdge(9.0), newEdge(69.0))));
    }

    private Edge newEdge(double weight) {
        return directedEdge(1, 1, weight);
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














