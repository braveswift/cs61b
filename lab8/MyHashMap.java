import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V>  {
    private int size;
    private double loadFactor = 0.75;
    private int bucketNum = 16;
    private HashSet<K> keysSet;
    private LinkedList<Node> [] st;

    private class Node {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        keysSet = new HashSet<>();
        st = (LinkedList<Node> [])new LinkedList[bucketNum];
        createLinkedList(st, bucketNum);

    }

    public MyHashMap(int initialSize) {
        bucketNum = initialSize;
        st = (LinkedList<Node> [])new LinkedList[bucketNum];
        keysSet = new HashSet<>();
        size = 0;
        createLinkedList(st, bucketNum);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        bucketNum = initialSize;
        this.loadFactor = loadFactor;
        st = (LinkedList<Node> [])new LinkedList[bucketNum];
        size = 0;
        keysSet = new HashSet<>();
        createLinkedList(st, bucketNum);
    }

    private void createLinkedList(LinkedList<Node> [] st, int m) {
        for (int i = 0; i < m; i++) {
            st[i] = new LinkedList<>();
        }
    }


    @Override
    public void clear() {
        st = null;
        size = 0;
        keysSet = null;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (keysSet == null) {
            return false;
        }
        return keysSet.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (containsKey(key)) {
            for (Node node : st[hash(key, bucketNum)]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {
            if ((double) size / bucketNum >= loadFactor ) {
                resize(2 * bucketNum);
            }
            Node newNode = new Node(key, value);
            int index = hash(key, bucketNum);
            st[index].add(newNode);
            size += 1;
            keysSet.add(key);
        } else {
            for (Node node : st[hash(key, bucketNum)]) {
                if (node.key == key) {
                    node.value = value;
                }
            }
        }
    }

    private int hash(K key, int m) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int m) {
        LinkedList<Node> [] newSt = (LinkedList<Node> [])new LinkedList[m];
        createLinkedList(newSt, m);
        for (int i = 0; i < bucketNum; i++) {
            for (Node node : st[i]) {
                int newIndex = hash(node.key, m);
                newSt[newIndex].add(node);
            }
        }
        this.bucketNum = m;
        this.st = newSt;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keysSet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keysSet.iterator();
    }

}

