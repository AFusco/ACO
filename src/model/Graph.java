package model;

import java.util.*;

public abstract class Graph {
    protected final int amountOfVertex;
    protected final SortedSet<Edge> edges;

    public Graph(int size) {
        if (size < 0)
            throw new IllegalArgumentException("size(" + size + "): the size of a graph must be greater or equal than 0");
        this.amountOfVertex = size;
        edges = new TreeSet<>();
    }

    public abstract boolean addEdge(int vertex1, int vertex2, double weight);

    public abstract boolean isConnected();

    public int amountOfVertex() {
        return amountOfVertex;
    }

    public int amountOfEdges() {
        return edges.size();
    }

    public List<Edge> edges() {
        List<Edge> edges = new ArrayList<>();
        for (Edge edge : this.edges) edges.add(edge);
        return edges;
    }

    protected void checkBounds(int vertex) {
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
