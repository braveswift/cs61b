package bearmaps;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    /* it needs add, swim and getSmallest are correct */
    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<String> minpq = new ArrayHeapMinPQ<>();
        minpq.add("priority5", 5);
        minpq.add("priority3", 3);
        minpq.add("priority2", 2);
        assertEquals("priority2", minpq.getSmallest());
    }

    /* It assumes add is correct. */
    @Test
    public void testContains() {
        ArrayHeapMinPQ<String> minpq = new ArrayHeapMinPQ<>();
        minpq.add("priority5", 5);
        minpq.add("priority3", 3);
        minpq.add("priority2", 2);
        assertTrue(minpq.contains("priority3"));
        assertFalse(minpq.contains("priority10"));

    }

    /* It assumes add is correct. */
    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<String> minpq = new ArrayHeapMinPQ<>();
        minpq.add("priority3", 3);
        minpq.add("priority2", 2);
        minpq.add("priority5", 5);
        minpq.add("priority6", 6);
        minpq.add("priority1", 1);
        assertEquals("priority1", minpq.removeSmallest());
        assertEquals("priority2", minpq.removeSmallest());
        assertFalse(minpq.contains("priority1"));
        assertFalse(minpq.contains("priority2"));
    }

    @Test
    public void testRemoveException() {
        ArrayHeapMinPQ<String> minpq = new ArrayHeapMinPQ<>();
        minpq.add("priority3", 3);
        minpq.add("priority2", 2);
        minpq.removeSmallest();
        minpq.removeSmallest();
        minpq.removeSmallest();
    }

    /* It assumes add is correct. */
    @Test
    public void testSize() {
        ArrayHeapMinPQ<String> minpq = new ArrayHeapMinPQ<>();
        minpq.add("priority3", 3);
        minpq.add("priority2", 2);
        minpq.add("priority5", 5);
        minpq.add("priority6", 6);
        minpq.add("priority1", 1);
        assertEquals(5, minpq.size());
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<String> minpq = new ArrayHeapMinPQ<>();
        minpq.add("priority3", 3);
        minpq.add("priority2", 2);
        minpq.add("priority5", 5);
        minpq.add("priority6", 6);
        minpq.add("priority8", 8);
        minpq.add("priority4", 4);
        minpq.changePriority("priority2", 1);
        assertEquals("priority2", minpq.getSmallest());
        minpq.changePriority("priority2", 10);
        assertEquals("priority3", minpq.getSmallest());

    }

    @Test
    public void testWithNaive() {
        ArrayHeapMinPQ<Integer> aMinpq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> nMinqp = new NaiveMinPQ<>();
        for (int i = 50; i > 0; i--) {
            aMinpq.add(i, i);
            nMinqp.add(i, i);
        }
        for (int i = 0; i < 20; i++) {
            int k = StdRandom.uniform(1, 50);
            int p = StdRandom.uniform(50);
            aMinpq.changePriority(k, p);
            nMinqp.changePriority(k, p);
        }
        assertEquals(aMinpq.getSmallest(), nMinqp.getSmallest());

    }

    @Test
    public void testTime() {
        Stopwatch sw1 = new Stopwatch();
        ArrayHeapMinPQ<Integer> aMinpq = new ArrayHeapMinPQ<>();
        for (int i = 10000; i > 0; i--) {
            aMinpq.add(i, i);
        }
        for (int i = 0; i < 5000; i++) {
            int k = StdRandom.uniform(3000, 7000);
            int p = StdRandom.uniform(500);
            aMinpq.changePriority(k, p);
        }
        System.out.println("Total time elapsed for Arrayminpq: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        NaiveMinPQ<Integer> nMinpq = new NaiveMinPQ<>();
        for (int i = 10000; i > 0; i--) {
            nMinpq.add(i, i);
        }
        for (int i = 0; i < 5000; i++) {
            int k = StdRandom.uniform(3000, 7000);
            int p = StdRandom.uniform(500);
            nMinpq.changePriority(k, p);
        }
        System.out.println("Total time elapsed for Naiveyminpq: " + sw2.elapsedTime() +  " seconds.");

    }

}
