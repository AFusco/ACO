import java.util.*;

public class Graph {
    private final int amountOfVertex;
    private final SortedSet<Edge> edges;
    private List<Edge> mst = null;
    private double totalWeight;

    /**
     * Constructor for a disconnected graph of a given size.
     * @param size The number of vertices
     */
    public Graph(int size) {
        if (size < 0)
            throw new IllegalArgumentException("size(" + size + "): the size of a graph must be greater or equal than 0");
        this.amountOfVertex = size;
        edges = new TreeSet<>((edge1, edge2) -> {
            if (edge1.hasTheSameVertices(edge2)) return 0;
            return edge1.compareTo(edge2);
        });
    }

    /**
     * Creates a random connected graph of a given number of vertices and edges
     * @param vertices The number of vertices
     * @param edges The number of edges to be randomly created.
     * @param r The random object to be used
     */
    public Graph(int vertices, int edges, Random r) {
        this(vertices);

        for (int vertex = 1; vertex < vertices; vertex++) {
            addEdge(vertex, r.nextInt(vertex), r.nextDouble());
        }

        for (int vertex = vertices; vertex < edges; vertex++) {
            addEdge(r.nextInt(vertices), r.nextInt(vertices), r.nextDouble());
        }
    }

    /**
     * Adds a weighted undirected edge to the graph.
     * Adding an edge causes the cached mst to be removed.
     * @param vertex1
     * @param vertex2
     * @param weight
     * @return True if the edge is correctly added.
     */
    public boolean addEdge(int vertex1, int vertex2, double weight) {
        checkBounds(vertex1);
        checkBounds(vertex2);
        Edge edge = new Edge(vertex1, vertex2, weight);
        mst = null;
        return edges.add(edge);
    }

    /**
     * If the graph is connected.
     * @return True if the graph is made by one single connected component.
     */
    public boolean isConnected() {
        if (isEmpty()) return false;
        return getMinimumSpanningTree().size() == amountOfVertex - 1;
    }

    /**
     * The amount of vertices in the graph.
     * @return the graph size.
     */
    public int amountOfVertex() {
        return amountOfVertex;
    }

    /**
     *
     * @return the amount of edges in the graph.
     */
    public int amountOfEdges() {
        return edges.size();
    }

    /**
     * A sorted list of all the edges in the graph.
     * @return
     */
    public List<Edge> edges() {
        List<Edge> edgeList = new ArrayList<>(this.edges.size());
        edgeList.addAll(this.edges);
        return edgeList;
    }

    private void checkBounds(int vertex) {
        if (isEmpty())
            throw new IndexOutOfBoundsException("vertex(" + vertex + "): is out of range {-}");
        if (!containsVertex(vertex))
            throw new IndexOutOfBoundsException("vertex(" + vertex + "): is out of range {0.." + (amountOfVertex - 1) + "}");
    }

    /**
     *
     * @param vertex
     * @return true if the specified vertex is contained in the graph
     */
    public boolean containsVertex(int vertex) {
        return vertex >= 0 && vertex < amountOfVertex;
    }

    public boolean isEmpty() {
        return amountOfVertex == 0;
    }

    /**
     * Get the graphs' minimum spanning tree as a list of edges.
     * @return
     */
    public List<Edge> getMinimumSpanningTree(){
        if (mst == null)
            mst = Kruskal.minimumSpanningTree(this);
        return mst;
    }

    @Override
    public String toString() {
        return "Graph { " + amountOfVertex + " vertices; " + amountOfEdges() + " edges; total weight = " + totalWeight() + " }";
    }

    /**
     * Return the sum of all the edges' weight.
     * @return
     */
    public double totalWeight() {
        double totalWeight = 0;
        for (Edge edge : edges)
            totalWeight += edge.weight();
        return totalWeight;
    }
}
