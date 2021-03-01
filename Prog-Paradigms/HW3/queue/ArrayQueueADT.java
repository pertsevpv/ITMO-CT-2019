package queue;

public class ArrayQueueADT {
    // a[] -- queue
    // n -- size
    // INV: n >= 0
    // INV: a[i] != null for i = 0..n - 1
    private int size;
    private int left, right;
    private Object[] elements = new Object[1024];

    // PRE: obj != null && adt != null
    // POST: (n'= n + 1) && (a'[n] == obj) && (a'[0..(n - 1)] = a[0..(n - 1)])
    public static void enqueue(ArrayQueueADT adt, Object obj) {
        assert obj != null;
        assert adt != null;
        ensureCapacity(adt, adt.size + 1);
        adt.size++;

        adt.elements[adt.right] = obj;
        adt.right = (adt.right + 1) % adt.elements.length;
    }

    // PRE: n > 0 && adt != null
    // POST: (n' = n) && (a' == a)
    public static Object element(ArrayQueueADT adt) {
        assert adt != null;
        assert adt.size > 0;

        return adt.elements[adt.left];
    }

    // PRE: n > 0 && adt != null
    // POST: (n' = n - 1) && (a'[0..(n - 2)] == a[1..(n - 1)])
    public static Object dequeue(ArrayQueueADT adt) {
        assert adt != null;
        assert adt.size > 0;
        adt.size--;

        Object n = adt.elements[adt.left];
        adt.elements[adt.left] = null;
        adt.left = (adt.left + 1) % adt.elements.length;
        return n;
    }

    // PRE: n >= 0 && adt != null
    // POST: n' == n
    public static int size(ArrayQueueADT adt) {
        assert adt != null;
        return adt.size;
    }

    // PRE: n >= 0 && adt != null
    // POST: n' == n
    public static boolean isEmpty(ArrayQueueADT adt) {
        assert adt != null;
        return adt.size == 0;
    }

    // PRE: adt != null
    // POST: n' == 0
    public static void clear(ArrayQueueADT adt) {
        assert adt != null;
        adt.size = 0;
        adt.left = 0;
        adt.right = 0;
        adt.elements = new Object[5];
    }

    // PRE: cap >= 0 && adt != null
    // POST: (n' == n) && (a'[0..(n - 1)] == a[0..(n - 1)])
    private static void ensureCapacity(ArrayQueueADT adt, int cap) {
        assert adt != null;
        assert adt.left >= 0;
        assert adt.right >= 0;
        assert cap >= 0;
        if (cap >= adt.elements.length) {
            Object[] tmp = new Object[adt.elements.length * 2];
            int j = 0;
            for (int i = adt.left; i != adt.right; i = (i + 1) % adt.elements.length, j++) {
                tmp[j] = adt.elements[i];
            }

            adt.elements = tmp;
            adt.left = 0;
            adt.right = j;
        }
    }

    // PRE: n >= 0 && adt != null
    // POST: a' == a
    public static String toStr(ArrayQueueADT adt) {
        assert adt != null;
        assert adt.size >= 0;
        assert adt.left >= 0;
        assert adt.right >= 0;
        StringBuilder res = new StringBuilder("[");
        for (int i = adt.left; i != adt.right; i = (i + 1) % adt.elements.length) {
            res.append(adt.elements[i]);
            if ((i + 1) % adt.elements.length != adt.right) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    // PRE: n >= 0 && adt != null
    // POST: a' == a
    public static Object[] toArray(ArrayQueueADT adt) {
        assert adt != null;
        assert adt.size >= 0;
        assert adt.left >= 0;
        assert adt.right >= 0;
        Object[] tmp = new Object[adt.size];
        for (int i = adt.left, j = 0; i != adt.right; i = (i + 1) % adt.elements.length, j++) {
            tmp[j] = adt.elements[i];
        }
        return tmp;
    }

}
