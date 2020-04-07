package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private int size;
    private HashMap<T, Integer> indexMap;
    private ArrayList<Node> minHeapList;

    private class Node {
        private T item;
        private double priority;

        public Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    public ArrayHeapMinPQ() {
        size = 0;
        indexMap = new HashMap<>();
        minHeapList = new ArrayList<>();
        minHeapList.add(null);
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        size = size + 1;
        minHeapList.add(new Node(item, priority));
        swim(size);
    }


    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        return indexMap.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (indexMap.isEmpty()) {
            throw new NoSuchElementException();
        }
        return minHeapList.get(1).item;
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T returnItem = minHeapList.get(1).item;
        indexMap.remove(minHeapList.get(1).item);
        Node rightMost = minHeapList.get(size);
        minHeapList.set(1, rightMost);
        minHeapList.remove(size);
        size = size - 1;
        sink(1);
        return returnItem;
    }


    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return size;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int index = indexMap.get(item);
        minHeapList.get(index).priority = priority;
        swim(index);
        sink(index);
    }


    private void sink(int k) {
        while (k * 2 <= size) {
            int j = k * 2;
            if (j < size && smaller(j + 1, j)) {
                j = j + 1;
            }
            if (smaller(j, k)) {
                exchange(j, k);
                k = j;
            } else {
                break;
            }
        }
    }


    private void swim(int k) {
        if (!indexMap.containsKey(minHeapList.get(k).item)) {
            indexMap.put(minHeapList.get(k).item, k);
        }
        while (k > 1 && smaller(k, k / 2)) {
            exchange(k, k / 2);
            k = k / 2;
        }
    }

    private boolean smaller(int i, int j) {
        return minHeapList.get(i).priority < minHeapList.get(j).priority;
    }

    private void exchange(int i, int j) {
        Node nodei = minHeapList.get(i);
        Node nodej = minHeapList.get(j);
        minHeapList.set(i, nodej);
        minHeapList.set(j, nodei);
        indexMap.put(nodei.item, j);
        indexMap.put(nodej.item, i);
    }

}



