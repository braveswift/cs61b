import java.lang.ref.PhantomReference;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;


    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key,  V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return containsKey(key, root);
    }

    private boolean containsKey(K key, Node node) {
        if (node == null) {
            return false;
        }
        if (node.key.compareTo(key) < 0) {
            return containsKey(key, node.right);
        } else if (node.key.compareTo(key) > 0) {
            return containsKey(key, node.left);
        } else {
            return true;
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, Node node) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) < 0) {
            return get(key, node.right);
        } else if (node.key.compareTo(key) > 0) {
            return get(key, node.left);
        } else {
            return node.value;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private Node put(K key, V value, Node node) {
        if (node == null) {
            size = size + 1;
            return new Node(key, value, null, null);
        }
        if (node.key.compareTo(key) < 0) {
            node.right = put(key, value, node.right);
        } else if (node.key.compareTo(key) > 0) {
            node.left = put(key, value, node.left);
        } else {
            node.value = value;
        }
        return node;
    }




    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw  new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw  new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw  new UnsupportedOperationException();
    }
}
