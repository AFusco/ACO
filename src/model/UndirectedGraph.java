package model;

public class UndirectedGraph extends Graph {

    public UndirectedGraph(int size) {
        super(size);
    }

    @Override
    public boolean addEdge(int vertex1, int vertex2, double weight) {
        checkBounds(vertex1);
        checkBounds(vertex2);
        Edge edge = new Edge(vertex1, vertex2, weight);
        if (edges.containsKey(edge.hashCode())) return false;
        edges.put(edge.hashCode(), edge);
        return true;
    }

    @Override
    public boolean isConnected() {
        if (isEmpty()) return false;
        return edges().size() >= amountOfVertex - 1;
    }

}
