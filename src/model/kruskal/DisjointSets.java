package model.kruskal;

import static java.lang.Integer.parseInt;

public class DisjointSets {

    private final String[] sets;
    private final String EMPTY = "N";
    private int amountOfSets;

    public DisjointSets(int amountOfSets) {
        this.amountOfSets = amountOfSets;
        sets = new String[amountOfSets];
        for (int vertex = 0; vertex < this.amountOfSets; vertex++) sets[vertex] = EMPTY;
    }

    public int amountOfSets() {
        return amountOfSets;
    }

    private void checkBounds(int vertex) {
        if (vertex < 0 || vertex >= sets.length)
            throw new IllegalArgumentException("vertex(" + vertex + "): is out of range {0.." + (sets.length - 1) + "}");
    }

    public boolean areConnected(int vertex1, int vertex2) {
        checkBounds(vertex1);
        checkBounds(vertex2);
        return rootOfVertex(vertex1) == rootOfVertex(vertex2);
    }

    private int rootOfVertex(int vertex) {
        return isRoot(vertex) ? vertex : rootOfVertex(parseInt(sets[vertex]));
    }

    private boolean isRoot(int vertex) {
        return sets[vertex].equals(EMPTY) || parseInt(sets[vertex]) < 0;
    }

    public boolean connectSets(int vertex1, int vertex2) {
        if (areConnected(vertex1, vertex2)) return false;
        int root1 = rootOfVertex(vertex1);
        int root2 = rootOfVertex(vertex2);
        if (sets[root1].equals(sets[root2])) joinSetsOfSameSize(root1, vertex2);
        else joinSetsOfDifferentSize(root1, root2);
        amountOfSets--;
        return true;
    }

    private void joinSetsOfSameSize(int root1, int vertex2) {
        sets[root1] = sets[root1].equals(EMPTY) ? Integer.toString(-1) : Integer.toString(parseInt(sets[root1]) - 1);
        sets[vertex2] = Integer.toString(root1);
    }

    private void joinSetsOfDifferentSize(int root1, int root2) {
        int valueOfRoot1 = sets[root1].equals(EMPTY) ? 0 : parseInt(sets[root1]);
        int valueOfRoot2 = sets[root2].equals(EMPTY) ? 0 : parseInt(sets[root2]);
        if (valueOfRoot1 < valueOfRoot2) sets[root2] = Integer.toString(root1);
        else sets[root1] = Integer.toString(root2);
    }

    @Override
    public String toString() {
        String index = "  ";
        String content = "";
        for (int vertex = 0; vertex < sets.length; vertex++) {
            index += vertex + "   ,  ";
            content += sets[vertex] + "   ,  ";
        }
        return "Disjoint Sets { \n" + index + " \n " + content + "\n}";
    }
}