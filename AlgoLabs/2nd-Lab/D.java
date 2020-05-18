import java.util.Scanner;

public class Main {

    public static class MyList {
        public static class Node {
            Node prev;
            Node next;
            int id;

            Node(Node prev, Node next, int i) {
                this.prev = prev;
                this.next = next;
                this.id = i;
            }
        }

        Node head;
        Node tail;
        Node mid;
        int len = 0;

        public MyList() {
            head = new Node(null, null, 0);
            tail = new Node(null, head, 0);
            head.prev = tail;
            mid = head;
        }

        void add(int num) {
            Node n = new Node(tail, tail.next, num);
            tail.next = n;
            tail.next.next.prev = tail.next;
            if (++len % 2 == 1) {
                mid = mid.prev;
            }
        }

        void addMid(int num) {
            Node n = new Node(mid.prev, mid, num);
            mid.prev.next = n;
            mid.prev = mid.prev.next;
            if (++len % 2 == 1) {
                mid = mid.prev;
            }
        }

        int getFirst() {
            Node n = head.prev;
            n.prev.next = head;
            head.prev = n.prev;
            if (--len == 0) {
                mid = head;
            } else if (len % 2 == 1) {
                mid = mid.prev;
            }
            return n.id;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyList l = new MyList();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String c = sc.next();
            switch (c) {
                case "+":
                    l.add(sc.nextInt());
                    break;
                case "*":
                    l.addMid(sc.nextInt());
                    break;
                case "-":
                    System.out.println(l.getFirst());
                    break;
            }
        }
    }
}