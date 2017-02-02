package model.kruskal;

import model.Edge;
import model.Graph;

import java.util.ArrayList;
import java.util.List;

public class Kruskal {

    public static List<Edge> minimumSpanningTree(Graph graph) {
        int vertex1, vertex2, amountOfVertex = graph.amountOfVertex();
        List<Edge> minimumSpanningTree = new ArrayList<>();
        DisjointSets set = new DisjointSets(amountOfVertex);
        for (Edge edge : graph.edges()) {
            if (edge.isLoop()) continue;
            vertex1 = edge.getLowVertex();
            vertex2 = edge.getHighVertex();
            if (!set.areConnected(vertex1, vertex2)) {
                set.connectSets(vertex1, vertex2);
                minimumSpanningTree.add(edge);
                if (minimumSpanningTree.size() == amountOfVertex - 1) break;
            }
        }
        return minimumSpanningTree;
    }

}
