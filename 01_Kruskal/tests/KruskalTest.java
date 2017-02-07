import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class KruskalTest {


    @Test
    public void should_return_an_empty_list_if_graph_has_1_vertex_or_is_empty() throws Exception {
        Graph g = new Graph(1);
        g.addEdge(0, 0, 0.0);

        assertEquals(expectedList(), Kruskal.minimumSpanningTree(g));
        assertEquals(expectedList(), Kruskal.minimumSpanningTree(new Graph(0)));
    }

    @Test
    public void should_return_the_lower_vertices_if_edges_have_the_same_weight() throws Exception {
        Graph g1 = new Graph(3);
        g1.addEdge(0, 1, 0.5);
        g1.addEdge(0, 2, 0.5);
        g1.addEdge(1, 2, 0.5);

        assertEquals(
                expectedList(
                        edge(0, 1, 0.5),
                        edge(0, 2, 0.5)
                ),
                Kruskal.minimumSpanningTree(g1)
        );

        Graph g2 = new Graph(4);

        g2.addEdge(0, 1, 1);
        g2.addEdge(1, 2, 1);
        g2.addEdge(2, 3, 1);
        g2.addEdge(3, 0, 1);

        assertEquals(
                expectedList(
                        edge(0, 1, 1),
                        edge(0, 3, 1),
                        edge(1, 2, 1)
                ),
                Kruskal.minimumSpanningTree(g2)
        );
    }

    @Test
    public void should_ignore_self_loop_edges() throws Exception {
        Graph g = new Graph(3);
        g.addEdge(0, 0, 0.0);
        g.addEdge(0, 1, 0.5);
        g.addEdge(1, 1, 0.5);
        g.addEdge(0, 2, 0.5);
        g.addEdge(1, 2, 0.0);
        assertEquals(
                expectedList(
                        edge(1, 2, 0.0),
                        edge(0, 1, 0.5)
                ),
                Kruskal.minimumSpanningTree(g)
        );
    }

    @Test
    public void should_return_all_the_edges_ordered_if_a_graph_is_already_MSP() throws Exception {
        Graph g = new Graph(6);
        g.addEdge(0, 1, 0.5);
        g.addEdge(1, 2, 0.5);
        g.addEdge(2, 3, 3.5);
        g.addEdge(3, 5, 5.5);
        g.addEdge(3, 4, 5.5);

        assertEquals(
                expectedList(
                        edge(0, 1, 0.5),
                        edge(1, 2, 0.5),
                        edge(2, 3, 3.5),
                        edge(3, 4, 5.5),
                        edge(3, 5, 5.5)
                ),
                Kruskal.minimumSpanningTree(g)
        );
    }

    private Edge edge(int vertex1, int vertex2, double weight) {
        return new Edge(vertex1, vertex2, weight);
    }

    private List<Edge> expectedList(Edge... edges) {
        return Arrays.asList(edges);
    }
}
