/**
 * @author Alessandro Fusco
 * @author Eduardo Ortega
 *
 * This class implements the DisjointSet data structure, used to solve the
 * connected components problem (aka Union-Find)
 */
public class DisjointSets {

    private final int[] sets;
    private final int EMPTY = Integer.MAX_VALUE;
    private int amountOfSets;


    /**
     * Initialize a Disjoint Set data structure with amountOfSets disjoint components.
     * @param amountOfSets The amount of initial components
     */
    public DisjointSets(int amountOfSets) {
        this.amountOfSets = amountOfSets;
        sets = new int[amountOfSets];
        for (int vertex = 0; vertex < this.amountOfSets; vertex++) {
            sets[vertex] = EMPTY;
        }
    }

    /**
     * @return The amount of connected components
     */
    public int amountOfSets() {
        return amountOfSets;
    }


    /**
     * Check if vertex1 and vertex2 are connected.
     * @param vertex1
     * @param vertex2
     * @return True if vertex1 and vertex2 are part of the same component
     */
    public boolean areConnected(int vertex1, int vertex2) {
        checkBounds(vertex1);
        checkBounds(vertex2);
        return rootOfVertex(vertex1) == rootOfVertex(vertex2);
    }

    /**
     * Perform a join operation.
     * After connectSets is called, unless an exception is thrown,
     * the two vertices are assured to be connected.
     * @param vertex1
     * @param vertex2
     * @return False if the two vertices were already connected. Otherwise true.
     */
    public boolean connectSets(int vertex1, int vertex2) {
        // Bound check done by areConnected()
        if (areConnected(vertex1, vertex2))
            return false;

        int root1 = rootOfVertex(vertex1);
        int root2 = rootOfVertex(vertex2);

        if (sets[root1] == sets[root2]) {
            joinSetsOfSameSize(root1, vertex2);
        } else {
            joinSetsOfDifferentSize(root1, root2);
        }

        return true;
    }

    private boolean isRoot(int vertex) {
        return sets[vertex] == EMPTY || sets[vertex] < 0;
    }

    private int rootOfVertex(int vertex) {
        return isRoot(vertex) ? vertex : rootOfVertex(sets[vertex]);
    }

    private void joinSetsOfSameSize(int root1, int vertex2) {
        sets[root1] = sets[root1] == EMPTY ? -1 : sets[root1] - 1;
        sets[vertex2] = root1;
        amountOfSets--;
    }

    private void joinSetsOfDifferentSize(int root1, int root2) {
        int valueOfRoot1 = sets[root1] == EMPTY ? 0 : sets[root1];
        int valueOfRoot2 = sets[root2] == EMPTY ? 0 : sets[root2];

        if (valueOfRoot1 < valueOfRoot2) {
            sets[root2] = root1;
        } else {
            sets[root1] = root2;
        }
        amountOfSets--;

    }

    private void checkBounds(int vertex) {
        if (vertex < 0 || vertex >= sets.length)
            throw new IllegalArgumentException("vertex(" + vertex + "): is out of range {0.." + (sets.length - 1) + "}");
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
