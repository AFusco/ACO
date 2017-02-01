package model;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Edge implements Comparable<Edge> {
    private final int lowVertex;
    private final int highVertex;
    private final double weight;

    private Edge(int lowVertex, int highVertex, double weight) {
        this.lowVertex = lowVertex;
        this.highVertex = highVertex;
        this.weight = weight;
    }

    public int getEitherVertex() {
        return lowVertex;
    }

    public int getOtherVertex(int vertex) {
        if (vertex == lowVertex) return highVertex;
        if (vertex == highVertex) return lowVertex;
        throw new IllegalArgumentException("The vertex {" + vertex + "} is not inside this edge");
    }

    public boolean isLoop() {
        return lowVertex == highVertex;
    }

    @Override
    public String toString() {
        return "Edge between vertices {" + lowVertex + "," + highVertex + "} and weight {" + weight + "}";
    }

    @Override
    public boolean equals(Object obj) {
        Edge edge = (Edge) obj;
        return weight == edge.weight && lowVertex == edge.lowVertex && highVertex == edge.highVertex;
    }

    @Override
    public int compareTo(Edge edge) {
        return Double.compare(weight, edge.weight);
    }



    public static Edge directedEdge(int origin, int destiny, double weight){
        checkIntegrityOf(origin);
        checkIntegrityOf(destiny);
        return new Edge(origin, destiny, weight);
    }

    public static Edge edge(int vertex1, int vertex2, double weight){
        checkIntegrityOf(vertex1);
        checkIntegrityOf(vertex2);
        return new Edge(min(vertex1, vertex2), max(vertex1, vertex2), weight);
    }

    private static void checkIntegrityOf(int vertex) {
        if (vertex < 0)
            throw new IllegalArgumentException("vertex(" + vertex + "): Vertices must not be negative");
    }
}
