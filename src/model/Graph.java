package model;

import java.util.*;

public abstract class Graph {
    protected final int amountOfVertex;
    protected final Map<Integer, Edge> edges;

    public Graph(int size) {
        if (size < 0)
            throw new IllegalArgumentException("size(" + size + "): the size of a graph must be greater or equal than 0");
        this.amountOfVertex = size;
        edges = new HashMap<>();
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
        for (Integer key : this.edges.keySet()) {
            Edge edge = this.edges.get(key);
            if (!edge.isLoop()) edges.add(edge);
        }
        Collections.sort(edges);
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
        for (Integer key : edges.keySet()) totalWeight += edges.get(key).weight();
        return totalWeight;
    }
}
