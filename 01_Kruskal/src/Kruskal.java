
import java.util.ArrayList;
import java.util.List;

public class Kruskal {

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

        if (minimumSpanningTree.size() != amountOfVertex - 1) {
            //System.err.println("Warning! Given graph is not connected. Incomplete MST returned.");
        }

        return minimumSpanningTree;
    }

}
