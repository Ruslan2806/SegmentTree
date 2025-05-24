package segtrees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Random;
import java.util.Arrays;

public class MinPlusAssignTest {
    @Test
    void smallTest() {
        Long[] a = {5L, 3L, 2L, 4L, 1L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiners.minLongs(), Updaters.assignLongs());
        assertEquals(2L, st.query(0, 3));
        st.update(1, 4, 0L);
        assertEquals(0L, st.query(0, 5));
    }

    @Test
    void edgeCases() {
        Long[] a = {100L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiners.minLongs(), Updaters.assignLongs());
        assertEquals(100L, st.query(0, 1));
        st.update(0, 1, 50L);
        assertEquals(50L, st.query(0, 1));
    }

    @Test
    void multipleAssigns() {
        Long[] a = {10L, 20L, 30L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiners.minLongs(), Updaters.assignLongs());
        st.update(0, 3, 5L);
        st.update(1, 2, 3L);
        assertEquals(3L, st.query(0, 3));
    }

    @Test
    void overrideUpdates() {
        Long[] a = {8L, 6L, 7L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiners.minLongs(), Updaters.assignLongs());
        st.update(0, 3, 5L);
        st.update(0, 3, 10L);
        assertEquals(10L, st.query(0, 3));
    }

    @Test
    void partialUpdate() {
        Long[] a = {9L, 8L, 7L, 6L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiners.minLongs(), Updaters.assignLongs());
        st.update(1, 3, 5L);
        assertEquals(5L, st.query(0, 4));
    }
}