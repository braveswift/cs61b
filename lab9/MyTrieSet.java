import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private static final int R = 256;
    private Node root;

    private static class Node{
        private Node[] children;
        private boolean isKey;

        Node(boolean isKey) {
            children = new Node[R];
            this.isKey = isKey;
        }
    }

    public MyTrieSet() {
        root = new Node(false);
    }


    /** Clears all items out of Trie */
    public void clear() {
        for (int i = 0; i < R; i++) {
            root.children[i] = null;
        }

    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (curr.children[c] == null) {
                return false;
            }
            curr = curr.children[c];
        }
        return curr.isKey;
    }


    /** Inserts string KEY into Trie */
    public void add(String key) {
        if (key == null || key.length() < 1) {
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (curr.children[c] == null) {
                curr.children[c] = new Node(false);
            }
            curr = curr.children[c];
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            curr = curr.children[c];
        }

        return  keysWithPrefixHelper(prefix, curr, result);
    }

    private List<String> keysWithPrefixHelper(String s, Node curr, List<String> result) {
        if (curr.isKey) {
            result.add(s);
        }
        for (int i = 0; i < curr.children.length; i++) {
            if (curr.children[i] != null) {
                keysWithPrefixHelper(s + (char) i, curr.children[i], result);
            }
        }
        return result;
    }


    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        String result = null;
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (curr.children[c] != null) {
                sb.append(c);
                curr = curr.children[c];
                if (curr.isKey) {
                    result = sb.toString();
                }
            }
        }
        return result;
    }
}
