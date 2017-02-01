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
        return edges.add(edge);
    }

    @Override
    public boolean isConnected() {
        if (isEmpty()) return false;
        return edges().size() >= amountOfVertex - 1;
    }

}
