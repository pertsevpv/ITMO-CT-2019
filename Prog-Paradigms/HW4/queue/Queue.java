package queue;

public interface Queue {
    // queue[] -- a[]
    // n -- size
    // INV: n >= 0
    // INV: a[i] != null for i = 0..n - 1

    // PRE: obj != null
    // POST: (n'= n + 1) && (a'[n] == obj) && (a'[0..(n - 1)] = a[0..(n - 1)])
    void enqueue(Object obj);

    // PRE: n > 0
    // POST: (n' == n) && (a' == a)
    // Result = a[0]
    Object element();

    // PRE: n > 0
    // POST: (n' = n - 1) && (a'[0..(n - 2)] == a[1..(n - 1)])
    // Result = a[0]
    Object dequeue();

    // PRE: n >= 0
    // POST: (n' == n) && (a' = a)
    // Result = n'
    int size();

    // PRE: n >= 0
    // POST: (n' == n) && (a' = a)
    // Result = n == 0
    boolean isEmpty();

    // PRE: -
    // POST: (n' == 0) && a' - empty
    void clear();

    // PRE: n >= 0
    // POST: (n' == n) && (a' = a)
    // Result = a'
    Object[] toArray();
}
