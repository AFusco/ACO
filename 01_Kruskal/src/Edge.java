import static java.lang.Double.compare;
import static java.lang.Integer.compare;
import static java.lang.Math.min;
import static java.lang.Math.max;

/**
 * @author Alessandro Fusco
 * @author Eduardo Ortega
 *
 * This class implements an immutable undirected weighted edge to use in a graph of integers.
 */
public class Edge implements Comparable<Edge> {

    private final int lowVertex;
    private final int highVertex;
    private final double weight;

    /**
     * Constructor for an undirected edge.
     * @param vertex1 Must not be negative.
     * @param vertex2 Must not be negative.
     * @param weight Must not be negative.
     */
    public Edge(int vertex1, int vertex2, double weight) {
        if (vertex1 < 0)
            throw new IllegalArgumentException("vertex(" + vertex1 + "): Vertices must not be negative");
        if (vertex2 < 0)
            throw new IllegalArgumentException("vertex(" + vertex2 + "): Vertices must not be negative");
        if (weight < 0)
            throw new IllegalArgumentException("weight(" + weight + "): Weight must not be negative");
        this.lowVertex  = min(vertex1, vertex2);
        this.highVertex = max(vertex1, vertex2);
        this.weight = weight;
    }

    /**
     * @return The weight of the edge.
     */
    public double weight() {
        return weight;
    }

    /**
     * @return the minor vertex.
     */
    public int getLowVertex() {
        return lowVertex;
    }


    /**
     * @return the major vertex.
     */
    public int getHighVertex() {
        return highVertex;
    }

    /**
     * Check if the edge is a self loop.
     * @return True if v1 == v2
     */
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

    /**
     * Check if two edges have the same vertices
     * @param other The other edge
     * @return True if this.v1 == other.v1 and this.v2 == other.v2
     */
    public boolean hasTheSameVertices(Edge other) {
        return this.lowVertex == other.lowVertex && this.highVertex == other.highVertex;
    }

    /**
     * Comparison by weight
     * @param edge
     */
    @Override
    public int compareTo(Edge edge) {
        return compare(weight, edge.weight) != 0 ? compare(weight, edge.weight) : compareVertices(edge);
    }

    /**
     * Comparison by vertices.
     * @param edge
     */
    private int compareVertices(Edge edge) {
        return compare(lowVertex, edge.lowVertex) != 0 ? compare(lowVertex, edge.lowVertex) : compare(highVertex, edge.highVertex);
    }

}
