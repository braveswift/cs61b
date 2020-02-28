import org.junit.Test;
import static org.junit.Assert.*;


public class TestOffByN {


    CharacterComparator offBy5 = new OffByN(5);

    @Test
    public void equalChars() {
        assertTrue(offBy5.equalChars('a', 'f'));
        assertFalse(offBy5.equalChars('a', 'b'));


    }


}
