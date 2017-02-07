import java.util.List;
import java.util.Random;

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


    private static void benchmark(int trials, int edgesCount, int initialSize) {
        for (int i = 1; i <= trials; i++) {

            int graphSize = initialSize << i;

            System.out.println(i + ") Graph with " + graphSize + " vertices");
            for (int j = 1; j <= edgesCount; j++) {

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
