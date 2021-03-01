package queue;

public class LinkedQueue extends AbstractQueue {

    private static class Node {
        private Node prev;
        private Node next;
        private Object data;

        private Node() {
            this(null,null,null);
        }

        private Node(Node p, Node n, Object obj) {
            prev = p;
            next = n;
            data = obj;
        }
    }

    private final Node head;
    private final Node tail;

    public LinkedQueue() {
        super();
        head = new Node();
        tail = new Node();

        head.prev = tail;
        tail.next = head;
    }

    @Override
    protected void enq(Object obj) {
        Node n = new Node(tail, tail.next, obj);
        tail.next = n;
        tail.next.next.prev = n;
    }

    @Override
    protected void deq() {
        Node n = head.prev;
        n.prev.next = head;
        head.prev = n.prev;
    }

    @Override
    public Object el() {
        return head.prev.data;
    }

    @Override
    protected void cl() {
        head.prev = tail;
        tail.next = head;
    }
}