import model.kruskal.DisjointSets;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DisjointSet_ {

    private DisjointSets verticesSets;

    @Before
    public void setUp() throws Exception {
        verticesSets = new DisjointSets(6);
    }

    @Test
    public void should_raise_an_exception_if_a_vertex_is_not_inside_the_sets() throws Exception {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> verticesSets.areConnected(10, 0))
                .withMessage("vertex(10): is out of range {0..5}");
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> verticesSets.areConnected(0, -7))
                .withMessage("vertex(-7): is out of range {0..5}");
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> verticesSets.connectSets(0, 30))
                .withMessage("vertex(30): is out of range {0..5}");

    }

    @Test
    public void should_know_if_two_vertex_are_in_the_same_set() throws Exception {
        assertThat(verticesSets.areConnected(0, 0), is(true));
        assertThat(verticesSets.areConnected(1, 2), is(false));
    }

    @Test
    public void should_join_two_single_vertices_in_the_same_set() throws Exception {
        assertThat(verticesSets.amountOfSets(), is(6));
        verticesSets.connectSets(0, 1);
        verticesSets.connectSets(2, 3);
        verticesSets.connectSets(5, 4);
        assertThat(verticesSets.amountOfSets(), is(3));
        assertThat(verticesSets.areConnected(0, 1), is(true));
        assertThat(verticesSets.areConnected(2, 3), is(true));
        assertThat(verticesSets.areConnected(5, 4), is(true));
        assertThatAreDisconnected(0, 1, new int[]{2, 3, 4, 5});
        assertThatAreDisconnected(2, 3, new int[]{0, 1, 4, 5});
        assertThatAreDisconnected(4, 5, new int[]{0, 1, 2, 3});
    }

    private void assertThatAreDisconnected(int vertex1, int vertex2, int[] ints) {
        for (int vertex : ints) {
            assertThat(verticesSets.areConnected(vertex1, vertex), is(false));
            assertThat(verticesSets.areConnected(vertex2, vertex), is(false));
        }
    }

    @Test
    public void should_join_sets_of_different_size() throws Exception {
        verticesSets.connectSets(3, 5);
        verticesSets.connectSets(0, 1);
        verticesSets.connectSets(0, 3);
        verticesSets.connectSets(4, 2);
        assertThat(verticesSets.areConnected(4, 2), is(true));
        assertThat(verticesSets.areConnected(3, 5), is(true));
        assertThat(verticesSets.areConnected(0, 1), is(true));
        assertThat(verticesSets.areConnected(0, 3), is(true));
        assertThat(verticesSets.areConnected(0, 5), is(true));
        assertThat(verticesSets.areConnected(0, 4), is(false));
        assertThat(verticesSets.areConnected(0, 2), is(false));
        verticesSets.connectSets(2, 5);
        assertThat(verticesSets.areConnected(0, 4), is(true));
        assertThat(verticesSets.areConnected(0, 2), is(true));
    }


}
