import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String a = "a";
        String b = "noon";
        String c = "";
        String d = "acn";
        assertTrue(palindrome.isPalindrome(a));
        assertTrue(palindrome.isPalindrome(b));
        assertTrue(palindrome.isPalindrome(c));
        assertFalse(palindrome.isPalindrome(d));
    }

    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testIsPalindromeNew() {
        String word = "flake";
        assertTrue(palindrome.isPalindrome(word, offByOne));

    }

    CharacterComparator offBy3 = new OffByN(3);

    @Test
    public void testIsPanlindromeN() {
        String word = "abcfed";
        assertTrue(palindrome.isPalindrome(word, offBy3));
    }

}