import edu.princeton.cs.algs4.Queue;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(9);
        q.enqueue(100);
        q.enqueue(6);
        q.enqueue(8);
        q.enqueue(10);
        Queue<Integer> quickSort = QuickSort.quickSort(q);
        assertEquals(true, isSorted(quickSort));
    }


    @Test
    public void testMergeSort() {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(9);
        q.enqueue(100);
        q.enqueue(6);
        q.enqueue(8);
        q.enqueue(10);
        Queue<Integer> mergeSort = MergeSort.mergeSort(q);
        assertEquals(true, isSorted(mergeSort));
    }

    @Test
    public void testMergeSortString() {
        Queue<String> q = new Queue<String>();
        q.enqueue("aa");
        q.enqueue("yz");
        q.enqueue("bc");
        q.enqueue("he");
        q.enqueue("zh");
        Queue<String> mergeSort = MergeSort.mergeSort(q);
        assertEquals(true, isSorted(mergeSort));
    }



    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
