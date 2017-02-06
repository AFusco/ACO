package model;

import model.kruskal.Kruskal;

import java.util.*;

public class Graph {
    private final int amountOfVertex;
    private final SortedSet<Edge> edges;
    private List<Edge> mst = null;

    public Graph(int size) {
        if (size < 0)
            throw new IllegalArgumentException("size(" + size + "): the size of a graph must be greater or equal than 0");
        this.amountOfVertex = size;
        edges = new TreeSet<>((edge1, edge2) -> {
            if (edge1.hasTheSameVertices(edge2)) return 0;
            return edge1.compareTo(edge2);
        });
    }

    public boolean addEdge(int vertex1, int vertex2, double weight) {
        checkBounds(vertex1);
        checkBounds(vertex2);
        Edge edge = new Edge(vertex1, vertex2, weight);
        mst = null;
        return edges.add(edge);
    }

    public boolean isConnected() {
        if (isEmpty()) return false;
        return getMinimumSpanningTree().size() == amountOfVertex - 1;
    }

    public int amountOfVertex() {
        return amountOfVertex;
    }

    public int amountOfEdges() {
        return edges.size();
    }

    public List<Edge> edges() {
        List<Edge> edges = new ArrayList<>(this.edges.size());
        for (Edge edge : this.edges) edges.add(edge);
        return edges;
    }

    private void checkBounds(int vertex) {
        if (isEmpty())
            throw new IndexOutOfBoundsException("vertex(" + vertex + "): is out of range {-}");
        if (!containsVertex(vertex))
            throw new IndexOutOfBoundsException("vertex(" + vertex + "): is out of range {0.." + (amountOfVertex - 1) + "}");
    }

    public boolean containsVertex(int vertex) {
        return vertex >= 0 && vertex < amountOfVertex;
    }

    public boolean isEmpty() {
        return amountOfVertex == 0;
    }

    public List<Edge> getMinimumSpanningTree(){
        if (mst == null)
            mst = Kruskal.minimumSpanningTree(this);
        return mst;
    }

    @Override
    public String toString() {
        return "Graph { " + amountOfVertex + " vertices; " + amountOfEdges() + " edges; total weight = " + totalWeight() + " }";
    }

    public double totalWeight() {
        int totalWeight = 0;
        for (Edge edge : edges) totalWeight += edge.weight();
        return totalWeight;
    }
}
