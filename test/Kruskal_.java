import model.Edge;
import model.Graph;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static model.kruskal.Kruskal.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Kruskal_ {

    @Test
    public void should_return_an_empty_list_if_graph_has_1_vertex_or_is_empty() throws Exception {
        Graph graph = graph(1).add(edge(0, 0, 0.0));
        assertThat(minimumSpanningTree(graph), is(expectedList()));
        assertThat(minimumSpanningTree(new Graph(0)), is(expectedList()));
    }

    @Test
    public void should_return_the_lower_vertices_if_edges_have_the_same_weight() throws Exception {
        Graph graph1 = graph(3).add(edge(0, 1, 0.5), edge(0, 2, 0.5), edge(1, 2, 0.5));
        assertThat(minimumSpanningTree(graph1), is(expectedList(edge(0, 1, 0.5), edge(0, 2, 0.5))));
        Graph graph2 = graph(4).add(edge(0, 1, 1), edge(1, 2, 1), edge(2, 3, 1), edge(3, 0, 1));
        assertThat(minimumSpanningTree(graph2), is(expectedList(edge(0, 1, 1), edge(0, 3, 1), edge(1, 2, 1))));
    }

    @Test
    public void should_ignore_self_loop_edges() throws Exception {
        Graph graph = graph(3).add(edge(0, 0, 0.0), edge(0, 1, 0.5), edge(1, 1, 0.5), edge(0, 2, 0.5), edge(1, 2, 0.0));
        assertThat(minimumSpanningTree(graph), is(expectedList(edge(1, 2, 0.0), edge(0, 1, 0.5))));
    }

    @Test
    public void should_return_all_the_edges_ordered_if_a_graph_is_already_MSP() throws Exception {
        Graph graph = graph(6).add(edge(0, 1, 0.5), edge(1, 2, 0.5), edge(2, 3, 3.5), edge(3, 5, 5.5), edge(3, 4, 5.5));
        List<Edge> list = expectedList(edge(0, 1, 0.5), edge(1, 2, 0.5), edge(2, 3, 3.5), edge(3, 4, 5.5), edge(3, 5, 5.5));
        assertThat(minimumSpanningTree(graph), is(list));
    }


    @FunctionalInterface
    interface GraphFiller {
        Graph add(Edge... edges);
    }

    private GraphFiller graph(int size) {
        Graph graph = new Graph(size);
        return edges -> {
            for (Edge edge : edges) graph.addEdge(edge.getLowVertex(), edge.getHighVertex(), edge.weight());
            return graph;
        };
    }

    private Edge edge(int vertex1, int vertex2, double weight) {
        return new Edge(vertex1, vertex2, weight);
    }

    private List<Edge> expectedList(Edge... edges) {
        return Arrays.asList(edges);
    }
}
