import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessandro Fusco
 * @author Eduardo Ortega
 *
 * Implementation of the Kruskal algorithm.
 */
public class Kruskal {

    /**
     * Returns the minimum spanning tree of a Graph.
     * If the graph is not connected (ie. it's made of multiple connected components),
     * the minimum spanning tree returned is not complete.
     * @param graph
     * @return The list of edges that make the MST of the given graph
     */
    public static List<Edge> minimumSpanningTree(Graph graph) {
        int vertex1, vertex2, amountOfVertex = graph.amountOfVertex();
        List<Edge> minimumSpanningTree = new ArrayList<>();
        DisjointSets set = new DisjointSets(amountOfVertex);
        for (Edge edge : graph.edges()) {

            if (edge.isLoop())
                continue;

            vertex1 = edge.getLowVertex();
            vertex2 = edge.getHighVertex();

            if (!set.areConnected(vertex1, vertex2)) {
                set.connectSets(vertex1, vertex2);
                minimumSpanningTree.add(edge);
                if (minimumSpanningTree.size() == amountOfVertex - 1)
                    break;
            }
        }

        /*
        CHECK FOR CONNECTION
        if (minimumSpanningTree.size() != amountOfVertex - 1) {
            System.err.println("Warning! Given graph is not connected. Incomplete MST returned.");
        }
        */

        return minimumSpanningTree;
    }

}
