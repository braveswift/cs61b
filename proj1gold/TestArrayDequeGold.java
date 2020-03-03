import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testStudentArray() {
        StudentArrayDeque<Integer> sd = new StudentArrayDeque();
        ArrayDequeSolution<Integer> ad = new ArrayDequeSolution();
        String msg = "";

        int i = 0;
        while (true) {
            double randomNumber  = StdRandom.uniform();
            if (randomNumber < 0.25) {
                sd.addFirst(i);
                ad.addFirst(i);
                msg = msg + "addFirst(" + i + ")\n";
            }
            if (randomNumber >= 0.25 && randomNumber < 0.5) {
                sd.addLast(i);
                ad.addLast(i);
                msg = msg + "addLast(" + i + ")\n";
            }
            if (randomNumber >= 0.5 && randomNumber < 0.75 && !ad.isEmpty()) {
                Integer adFirst = ad.removeFirst();
                Integer sdFirst = sd.removeFirst();
                msg = msg + "removeFirst()\n";
                assertEquals(msg, adFirst, sdFirst);
            }
            if (randomNumber >= 0.75 && randomNumber < 1 && !ad.isEmpty()) {
                Integer adLast = ad.removeLast();
                Integer sdLast = sd.removeLast();
                msg = msg + "removeLast()\n";
                assertEquals(msg, adLast, sdLast);
            }
            i++;
        }
    }
}
