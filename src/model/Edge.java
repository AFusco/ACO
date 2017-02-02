package model;

import static java.lang.Double.compare;
import static java.lang.Integer.compare;
import static java.lang.Math.min;
import static java.lang.Math.max;

public class Edge implements Comparable<Edge> {
    private final int lowVertex;
    private final int highVertex;
    private final double weight;

    public Edge(int vertex1, int vertex2, double weight) {
        if (vertex1 < 0)
            throw new IllegalArgumentException("vertex(" + vertex1 + "): Vertices must not be negative");
        if (vertex2 < 0)
            throw new IllegalArgumentException("vertex(" + vertex2 + "): Vertices must not be negative");
        this.lowVertex  = min(vertex1, vertex2);
        this.highVertex = max(vertex1, vertex2);
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int getLowVertex() {
        return lowVertex;
    }

    public int getHighVertex() {
        return highVertex;
    }

    public boolean isLoop() {
        return lowVertex == highVertex;
    }

    @Override
    public String toString() {
        return "Edge {" + lowVertex + "," + highVertex + "} weight {" + weight + "}";
    }

    @Override
    public boolean equals(Object obj) {
        Edge edge = (Edge) obj;
        return hasTheSameVertices(edge) && weight == edge.weight;
    }

    public boolean hasTheSameVertices(Edge edge) {
        return lowVertex == edge.lowVertex && highVertex == edge.highVertex;
    }

    @Override
    public int compareTo(Edge edge) {
        return compare(weight, edge.weight) != 0 ? compare(weight, edge.weight) : compareVertices(edge);
    }

    private int compareVertices(Edge edge) {
        return compare(lowVertex, edge.lowVertex) != 0 ? compare(lowVertex, edge.lowVertex) : compare(highVertex, edge.highVertex);
    }

}
