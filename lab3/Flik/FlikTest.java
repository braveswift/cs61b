import org.junit.Test;
import static org.junit.Assert.*;


public class FlikTest {

    @Test
    public void testFlik() {
        int a = 500;
        int b = 499;
        assertTrue("not same", Flik.isSameNumber(a, b));
    }



}
