import model.Edge;
import model.Graph;
import model.UndirectedGraph;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Graph_ {

    @Test
    public void should_not_create_a_graph_with_less_than_0_vertex() throws Exception {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new UndirectedGraph(-1))
                .withMessage("size(-1): the size of a graph must be greater or equal than 0");
    }

    @Test
    public void should_create_an_empty_graph() throws Exception {
        Graph graph = new UndirectedGraph(0);
        assertThat(graph.isEmpty(), is(true));
        assertThat(graph.amountOfVertex(), is(0));
        assertThat(graph.amountOfEdges() , is(0));
        assertThat(graph.containsVertex(-1), is(false));
        assertThat(graph.containsVertex(0) , is(false));
        assertThat(graph.containsVertex(1) , is(false));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> graph.addEdge(0, 0, 3.0))
                .withMessage("vertex(0): is out of range {-}");
    }

    @Test
    public void should_create_a_graph_of_a_given_size() throws Exception {
        int size = 5;
        Graph graph = new UndirectedGraph(size);
        assertThat(graph.amountOfVertex()    , is(size));
        assertThat(graph.containsVertex(size), is(false));
        assertThat(graph.containsVertex(-1)  , is(false));
        for (int vertex = 0; vertex < size; vertex++)
            assertThat(graph.containsVertex(vertex), is(true));
    }

    @Test
    public void should_add_edges_between_existing_vertices() throws Exception {
        Graph graph = new UndirectedGraph(3);
        assertThat(graph.addEdge(0, 0, 0.0), is(true));
        assertThat(graph.addEdge(0, 1, 1.0), is(true));
        assertThat(graph.addEdge(0, 2, 3.0), is(true));
        assertThat(graph.addEdge(1, 2, 4.0), is(true));
        assertThat(graph.amountOfEdges(), is(4));
    }

    @Test
    public void should_not_add_an_edge_if_already_exists() throws Exception {
        Graph graph = new UndirectedGraph(3);
        assertThat(graph.addEdge(0, 1, 2.0), is(true));
        assertThat(graph.addEdge(0, 1, 2.0), is(false));
        assertThat(graph.addEdge(0, 1, 9.0), is(false));
        assertThat(graph.amountOfEdges(), is(1));
    }

    @Test
    public void should_not_add_edges_if_a_vertex_not_exists() throws Exception {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> new UndirectedGraph(3).addEdge(1, -1, 3.0))
                .withMessage("vertex(-1): is out of range {0..2}");
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> new UndirectedGraph(7).addEdge(0, 10, 1.0))
                .withMessage("vertex(10): is out of range {0..6}");
    }

    @Test
    public void summary_of_a_graph() throws Exception {
        Graph graph = new UndirectedGraph(3);
        assertThat(graph.toString(), is("Graph { 3 vertices; 0 edges; total weight = 0.0 }"));
        graph.addEdge(0, 0, 0.0);
        graph.addEdge(0, 1, 1.0);
        graph.addEdge(0, 2, 3.0);
        graph.addEdge(1, 2, 6.0);
        assertThat(graph.toString(), is("Graph { 3 vertices; 4 edges; total weight = 10.0 }"));
    }

    @Test
    public void should_know_if_a_graph_is_connected() throws Exception {
        Graph graph = new UndirectedGraph(7);
        assertThat(graph.isConnected(), is(false));
        graph.addEdge(0, 3, 1.0);
        graph.addEdge(2, 3, 1.0);
        graph.addEdge(3, 4, 1.0);
        graph.addEdge(5, 4, 1.0);
        graph.addEdge(0, 6, 1.0);
        assertThat(graph.isConnected(), is(false));
        graph.addEdge(0, 1, 9.0);
        assertThat(graph.isConnected(), is(true));
        assertThat(graph.amountOfEdges(), is(6));
    }

    @Test
    public void should_return_an_ordered_list_of_edges() throws Exception {
        Graph graph = new UndirectedGraph(3);
        graph.addEdge(1, 2, 0.5);
        graph.addEdge(0, 2, 1.0);
        graph.addEdge(0, 1, 1.0);
        graph.addEdge(0, 0, 0.0);
        List<Edge> expectedList = Arrays.asList(new Edge(0, 0, 0.0), new Edge(1, 2, 0.5), new Edge(0, 1, 1.0), new Edge(0, 2, 1.0));
        assertThat(graph.edges(), is(expectedList));
    }

}
