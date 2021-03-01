package queue;

public class ArrayQueue extends AbstractQueue {

    private int left, right;
    private Object[] elements;

    public ArrayQueue(){
        super();
        left = right = 0;
        elements = new Object[1024];
    }

    @Override
    protected void enq(Object obj) {
        ensureCapacity(size + 1);
        elements[right] = obj;
        right = (right + 1) % elements.length;
    }

    public Object el() {
        return elements[left];
    }

    @Override
    protected void deq() {
        Object n = elements[left];
        elements[left] = null;
        left = (left + 1) % elements.length;
    }

    public int size() {
        assert size >= 0;
        return size;
    }

    public boolean isEmpty() {
        assert size >= 0;
        return size == 0;
    }

    @Override
    protected void cl() {
        left = 0;
        right = 0;
        elements = new Object[1024];
    }

    private void ensureCapacity(int cap) {
        assert cap >= 0;
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
}
