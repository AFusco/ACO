import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DisjointSetsTest {

    private DisjointSets verticesSets;

    @Before
    public void setUp() throws Exception {
        verticesSets = new DisjointSets(6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testOutOfBound1() throws Exception {
        verticesSets.areConnected(10, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testOutOfBound2() throws Exception {
        verticesSets.areConnected(0, -7);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testOutOfBound3() throws Exception {
        verticesSets.connectSets(0, 30);
    }

    @Test
    public void testAreConnected() throws Exception {
        assertTrue(verticesSets.areConnected(0, 0));
        assertFalse(verticesSets.areConnected(1, 2));
    }

    @Test
    public void testJoin1() throws Exception {

        assertEquals(6, verticesSets.amountOfSets());
        verticesSets.connectSets(0, 1);
        verticesSets.connectSets(2, 3);
        verticesSets.connectSets(5, 4);

        assertEquals(3, verticesSets.amountOfSets());

        assertTrue(verticesSets.areConnected(0, 1));
        assertTrue(verticesSets.areConnected(2, 3));
        assertTrue(verticesSets.areConnected(5, 4));
        assertThatAreDisconnected(0, 1, new int[]{2, 3, 4, 5});
        assertThatAreDisconnected(2, 3, new int[]{0, 1, 4, 5});
        assertThatAreDisconnected(4, 5, new int[]{0, 1, 2, 3});
    }

    private void assertThatAreDisconnected(int vertex1, int vertex2, int[] ints) {
        for (int vertex : ints) {
            assertFalse(verticesSets.areConnected(vertex1, vertex));
            assertFalse(verticesSets.areConnected(vertex2, vertex));
        }
    }

    @Test
    public void testJoin2() throws Exception {
        verticesSets.connectSets(3, 5);
        verticesSets.connectSets(0, 1);
        verticesSets.connectSets(0, 3);
        verticesSets.connectSets(4, 2);

        assertTrue(verticesSets.areConnected(4, 2));
        assertTrue(verticesSets.areConnected(3, 5));
        assertTrue(verticesSets.areConnected(0, 1));
        assertTrue(verticesSets.areConnected(0, 3));
        assertTrue(verticesSets.areConnected(0, 5));
        assertFalse(verticesSets.areConnected(0, 4));
        assertFalse(verticesSets.areConnected(0, 2));

        verticesSets.connectSets(2, 5);
        assertTrue(verticesSets.areConnected(0, 4));
        assertTrue(verticesSets.areConnected(0, 2));
    }


}
