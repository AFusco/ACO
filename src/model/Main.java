package model;

import java.util.List;
import java.util.Random;

public class Main {

    final static Random r = new Random();

    public static void main(String[] args) {
        long startTime;
        double duration;
        List<Edge> minimumSpanningTree;
        System.out.println("{graph}   {amountOfVertex}    {amountOfEdges}     {treeSize}    {duration}");
        for (int i = 1; i <= 10; i++) {
            Graph graph = new Graph(1024 << i);
            createEdges(graph);
            startTime = System.nanoTime();
            minimumSpanningTree = graph.getMinimumSpanningTree();
            duration = (double) (System.nanoTime() - startTime) / 1000000000d;
            System.out.println(String.format("%5d % 15d     % 16d   % 15d    % 10f s",
                    i, graph.amountOfVertex(), graph.amountOfEdges(), minimumSpanningTree.size(), duration));
        }


    }

    private static void createEdges(Graph graph) {
        for (int vertex = 1; vertex < graph.amountOfVertex(); vertex++) {
            graph.addEdge(vertex, r.nextInt(vertex), r.nextDouble());
        }
        for (int i = 0; i < graph.amountOfVertex(); i++) {
            graph.addEdge(r.nextInt(graph.amountOfVertex()), r.nextInt(graph.amountOfVertex()), r.nextDouble());
        }
    }

}
