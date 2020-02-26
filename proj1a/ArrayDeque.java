import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.awt.desktop.AppReopenedEvent;
import java.util.Objects;

public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 7;
        nextLast = 0;
    }

    private int addOne(int index) {
        if (index == items.length - 1){
            return 0;
        }
        return index + 1;
    }

    private int minusOne(int index) {
        if (index  == 0){
            return items.length - 1;
        }
        return index - 1;
    }

    private T[] fromFirstToLast() {
        T[] orderArr = (T[]) new Object[size];
        int j = 0;
        int i = addOne(nextFirst);
        while (j < size) {
            orderArr[j] = items[i];
            i = addOne(i);
            j = j + 1;
        }
        return orderArr;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        T[] orderArr = fromFirstToLast();
        System.arraycopy(orderArr, 0, newItems, 0, size);
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size = size + 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size = size + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        T[] orderArr = fromFirstToLast();
        for (T t : orderArr) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if ((double) (size - 1) / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }

        nextFirst = addOne(nextFirst);
        T first = items[nextFirst];
        items[nextFirst] = null;
        size = size - 1;
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((double) (size - 1) / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        nextLast = minusOne(nextLast);
        T last  = items[nextLast];
        items[nextLast] = null;
        size = size - 1;
        return last;
    }

    public T get(int index) {
        if (size  == 0 || index > size) {
            return null;
        }
        int newIndex;
        if (nextFirst + 1 + index < items.length) {
            newIndex = nextFirst + 1 + index;
        } else {
            newIndex = nextFirst + 1 + index - items.length;
        }
        return items[newIndex];
    }

    public ArrayDeque(ArrayDeque<T> other) {
        T[] orderArr = other.fromFirstToLast();
        T[] copyArr = (T[]) new Object[other.items.length];
        System.arraycopy(orderArr, 0 , copyArr, 0, other.size);
        size = other.size;
        items = copyArr;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /* Test.
    public static void main(String arg[]) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addFirst(3);
        L.addLast(4);
        L.addLast(5);
        L.addLast(6);
        L.addLast(7);
        L.addFirst(2);
        L.addFirst(1);
        L.addFirst(0);
        L.addFirst(99);
        L.addLast(100);
        //L.removeLast();
        L.printDeque();
        System.out.println(L.get(1));
        ArrayDeque<Integer> F = new ArrayDeque<>(L);
        F.printDeque();
    }
     */


}
