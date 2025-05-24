package segtrees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SumPlusAddTest {
    @Test
    void smallTest() {
        Long[] a = {1L, 2L, 3L, 4L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiners.sumLongs(), Updaters.addLongs());
        assertEquals(14L, st.query(1, 4));
        st.update(0, 4, 3L);
        assertEquals(29L, st.query(0, 4));
    }


    @Test
    void edgeQuery() {
        Long[] a = {10L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiners.sumLongs(), Updaters.addLongs());
        assertEquals(10L, st.query(0, 1));
        st.update(0, 1, 5L);
        assertEquals(15L, st.query(0, 1));
    }

    @Test
    void multipleUpdates() {
        Long[] a = {1L, 1L, 1L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiners.sumLongs(), Updaters.addLongs());
        st.update(0, 2, 2L);
        st.update(1, 2, 3L);
        assertEquals(1L + 6L + 6L, st.query(0, 2)); // 1 + (1+2+3) + (1+2+3) = 13
    }

    @Test
    void queryWholeArray() {
        Long[] a = {2L, 4L, 6L, 8L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiners.sumLongs(), Updaters.addLongs());
        assertEquals(20L, st.query(0, 4));
    }

    @Test
    void partialUpdateAndQuery() {
        Long[] a = {5L, 10L, 15L, 20L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiners.sumLongs(), Updaters.addLongs());
        st.update(1, 3, 5L);
        assertEquals(5L + 15L + 20L + 20L, st.query(0, 4)); // updated: [5,15,20,20]
    }
}