package queue;

public abstract class AbstractQueue implements Queue {
    // queue[] -- a[]
    // n -- size
    // INV: n >= 0
    // INV: a[i] != null for i = 0..n - 1
    protected int size;

    public AbstractQueue() {
        size = 0;
    }

    // PRE: obj != null
    // POST: (n'= n + 1) && (a'[n] == obj) && (a'[0..(n - 1)] = a[0..(n - 1)])
    protected abstract void enq(Object obj);

    // PRE: n > 0
    // POST: (n' = n - 1) && (a'[0..(n - 2)] == a[1..(n - 1)])
    protected abstract void deq();

    // PRE: -
    // POST: (n' == 0) && a' - empty
    protected abstract void cl();

    // PRE: n > 0
    // POST: (n' = n) && (a' == a)
    // Result = a[0]
    protected abstract Object el();

    // PRE: obj != null
    // POST: (n'= n + 1) && (a'[n] == obj) && (a'[0..(n - 1)] = a[0..(n - 1)])
    @Override
    public void enqueue(Object obj) {
        assert obj != null;
        size++;
        enq(obj);
    }

    // PRE: n > 0
    // POST: (n' = n - 1) && (a'[0..(n - 2)] == a[1..(n - 1)])
    // Result = a[0]
    @Override
    public Object dequeue() {
        assert size >= 0;
        Object res = element();
        size--;
        deq();
        return res;
    }

    // PRE: -
    // POST: n' == 0
    @Override
    public void clear() {
        size = 0;
        cl();
    }

    // PRE: n >= 0
    // POST: n' == n
    // Result = n'
    @Override
    public int size() {
        assert size >= 0;
        return size;
    }

    // PRE: n >= 0
    // POST: (n' == n) && (a' = a)
    // Result = n' == n
    @Override
    public boolean isEmpty() {
        assert size >= 0;
        return size == 0;
    }

    // PRE: n > 0
    // POST: (n' = n) && (a' == a)
    // Result = a'[0]
    @Override
    public Object element() {
        assert size > 0;
        return el();
    }

    // PRE: n >= 0
    // POST: (n' == n) && (a' == a)
    // Result: a'
    @Override
    public Object[] toArray() {
        assert size >= 0;
        Object[] tmp = new Object[size];
        for (int i = 0; i < size; i++) {
            tmp[i] = dequeue();
            enqueue(tmp[i]);
        }
        return tmp;
    }
}
