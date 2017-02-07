import java.util.List;
import java.util.Random;

/**
 * @author Alessandro Fusco
 * @author Eduardo Ortega
 *
 * This class aims to benchmark the implementation of the Kruskal algorithm,
 * by timing it's execution time when increasing both the number of vertices
 * (size of the graph) and number of edges (sparcity of the graph)
 */
public class Main {

    final static Random r = new Random(1234);

    public static void main(String[] args) {

        int trials;

        try {
            trials = Integer.parseInt(args[0]);
        } catch (Exception e) {
            trials = 10;
        }

        benchmark(trials, 5, 1024);
    }

    /**
     * Benchmarking method
     *
     * @param trials How many times you want to double the number of vertices.
     * @param edgeIterations How many times do you want to add g.amountOfVertex() edges.
     * @param initialSize The number of vertices
     */
    private static void benchmark(int trials, int edgeIterations, int initialSize) {
        for (int i = 1; i <= trials; i++) {

            int graphSize = initialSize << i;

            System.out.println(i + ") Graph with " + graphSize + " vertices");
            for (int j = 1; j <= edgeIterations; j++) {

                int edges = graphSize * j;
                Graph g = new Graph(graphSize, edges, r);
                double time = mstTime(g);

                System.out.println(String.format("\tEdges: % 10d (%dx) - Kruskal Time: % 14f s", g.amountOfEdges(), j, time));
            }
            System.out.println();
        }
    }

    /**
     * Return time of MST calculation in seconds.
     */
    private static double mstTime(Graph g) {
        long startTime;
        double duration;
        startTime = System.nanoTime();
        List<Edge> mst = g.getMinimumSpanningTree();
        duration = (double) (System.nanoTime() - startTime) / 1000000000d;

        return duration;
    }
}
