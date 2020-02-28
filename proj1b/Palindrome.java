public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result  = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            result.addLast(c);
        }
        return result;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        if (d.isEmpty() || d.size() == 1) {
            return true;
        }
        while (d.size() > 1) {
            if (d.removeFirst() != d.removeLast()) {
                return false;
            }
        }
        return true;

    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        if (d.isEmpty() || d.size() == 1) {
            return true;
        }
        while (d.size() > 1) {
            char first = d.removeFirst();
            char last = d.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;

    }

}
