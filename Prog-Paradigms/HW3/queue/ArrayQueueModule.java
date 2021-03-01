package queue;

public class ArrayQueueModule {
    // a[] -- queue
    // n -- size
    // INV: n >= 0
    // INV: a[i] != null for i = 0..n - 1
    private static int size;
    private static int left, right;
    private static Object[] elements = new Object[2];

    // PRE: obj != null
    // POST: (n'= n + 1) && (queue'[n] == obj) && (queue'[0..(n - 1)] = queue[0..(n - 1)])
    public static void enqueue(Object obj) {
        assert obj != null;
        ensureCapacity(size + 1);
        size++;

        elements[right] = obj;
        right = (right + 1) % elements.length;
    }

    // PRE: n > 0
    // POST: (n' = n) && (a' == a)
    public static Object element() {
        assert size > 0;
        return elements[left];
    }

    // PRE: n > 0
    // POST: (n' = n - 1) && (a'[0..(n - 2)] == a[1..(n - 1)])
    public static Object dequeue() {
        assert size > 0;
        size--;

        Object n = elements[left];
        elements[left] = null;
        left = (left + 1) % elements.length;
        return n;
    }

    // PRE: n >= 0
    // POST: n' == n
    public static int size() {
        return size;
    }

    // PRE: n >= 0
    // POST: n' == n
    public static boolean isEmpty() {
        return size == 0;
    }

    // PRE: -
    // POST: n' == 0
    public static void clear() {
        size = left = right = 0;
        elements = new Object[2];
    }

    // PRE: cap >= 0
    // POST: (n' == n) && (a'[0..(n - 1)] == a[0..(n - 1)])
    private static void ensureCapacity(int cap) {
        assert size >= 0;
        if (cap >= elements.length) {
            Object[] tmp = new Object[elements.length * 2];
            int j = 0;
            for (int i = left; i != right; i = (i + 1) % elements.length, j++) {
                tmp[j] = elements[i];
            }

            elements = tmp;
            left = 0;
            right = j;
        }
    }

    // PRE: n >= 0
    // POST: a' == a
    public static String toStr() {
        assert size >= 0;
        StringBuilder res = new StringBuilder("[");
        for (int i = left; i != right; i = (i + 1) % elements.length) {
            res.append(elements[i]);
            if ((i + 1) % elements.length != right) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    // PRE: n >= 0
    // POST: a' == a
    public static Object[] toArray() {
        assert size >= 0;
        Object[] tmp = new Object[size];
        for (int i = left, j = 0; i != right; i = (i + 1) % elements.length, j++) {
            tmp[j] = elements[i];
        }
        return tmp;
    }
}
