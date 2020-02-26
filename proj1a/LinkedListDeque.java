public class LinkedListDeque<T> {
    private class DLNode {
        private DLNode prev;
        private T item;
        private DLNode next;

        public DLNode(DLNode p, T i, DLNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size;
    private DLNode sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new DLNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T i) {
        size = size + 1;
        DLNode p = new DLNode(sentinel, i, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;

    }

    public void addLast(T i) {
        size = size + 1;
        DLNode p = new DLNode(sentinel.prev, i, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        DLNode p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size = size - 1;
        return firstItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T lastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size = size - 1;
        return lastItem;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        DLNode p = sentinel.next;
        int i = 0;
        while (i != index) {
            p = p.next;
            i++;
        }
        return p.item;
    }

    //help from Jerome
    public T getRecursive(int index) {
        if (index >= size){
            return null;
        }

        DLNode p = sentinel.next;
        return getRecursive(index, p);
    }

    private T getRecursive(int index, DLNode root) {
        if (root == null) {
            return null;
        }
        if (index == 0) {
            return root.item;
        }
        return getRecursive(index - 1, root.next);
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
        size = 0;
        sentinel = new DLNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;

        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
        }
    }

    /*Test
    public static void main(String[] arg) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addFirst(1);
        L.addFirst(2);
        L.addFirst(3);
        //L.removeLast();
        System.out.println(L.removeFirst());
        L.printDeque();
    }
     */
}



