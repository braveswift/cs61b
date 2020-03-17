package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testPeek() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(4);
        arb.enqueue(1);
        arb.enqueue(2);
        Integer expected = 1;
        Integer actual = arb.peek();
        assertEquals(expected, actual);

    }

    @Test
    public void testCapactiy() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(4);
        Integer expected = 4;
        Integer actual = arb.capacity();
        assertEquals(expected, actual);
    }

    @Test
    public void testFillCount() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(4);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.dequeue();
        Integer expected = 1;
        Integer actual = arb.fillCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testEnandDe() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(4);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.dequeue();
        arb.enqueue(5);
        Integer actual = arb.dequeue();
        Integer expected = 2;
        assertEquals(expected, actual);
        Integer actualSize = arb.fillCount();
        Integer expectedSize = 3;
        assertEquals(expectedSize, actualSize);

    }
}
