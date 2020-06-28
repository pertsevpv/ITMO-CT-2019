import java.util.Scanner;

public class Main {

    public static class Node {
        long min;
        int pos;
        boolean hasSet;

        Node() {
            min = 0;
            pos = 0;
            hasSet = false;
        }
    }

    public static Node[] tree;
    public static int[] set;
    public static int k;
    public static long minAns = Long.MAX_VALUE;
    public static int pos = -1;

    private static int nextPow(int n) {
        int p = 1;
        while (p < n) p *= 2;
        return p;
    }

    public static void get(int v, int l, int r, int a, int b) {
        if (b <= l || a >= r) return;
        if (l >= a && r <= b) {
            if (tree[v].min < minAns) {
                minAns = tree[v].min;
                pos = tree[v].pos;
            }
            return;
        }
        if (v < k - 1) {
            push(v);
            get(2 * v + 1, l, (l + r) / 2, a, b);
            get(2 * v + 2, (l + r) / 2, r, a, b);
            if (tree[2 * v + 1].min < tree[2 * v + 2].min) {
                tree[v].min = tree[2 * v + 1].min;
                tree[v].pos = tree[2 * v + 1].pos;
            } else {
                tree[v].min = tree[2 * v + 2].min;
                tree[v].pos = tree[2 * v + 2].pos;
            }
        }
    }

    public static void set(int v, int l, int r, int a, int b, long x) {
        if (b <= l || a >= r) return;
        if (l >= a && r <= b) {
            if (tree[v].min < x) {
                tree[v].min = x;
                tree[v].hasSet = true;
            }
            return;
        }
        if (v < k - 1) {
            push(v);
            set(2 * v + 1, l, (l + r) / 2, a, b, x);
            set(2 * v + 2, (l + r) / 2, r, a, b, x);
            if (tree[2 * v + 1].min < tree[2 * v + 2].min) {
                tree[v].min = tree[2 * v + 1].min;
                tree[v].pos = tree[2 * v + 1].pos;
            } else {
                tree[v].min = tree[2 * v + 2].min;
                tree[v].pos = tree[2 * v + 2].pos;
            }
        }
    }

    public static void push(int v) {
        if (tree[v].hasSet) {
            if (tree[2 * v + 1].min < tree[v].min) {
                tree[2 * v + 1].hasSet = tree[v].hasSet;
                tree[2 * v + 1].min = tree[v].min;
            }
            if (tree[2 * v + 2].min < tree[v].min) {
                tree[2 * v + 2].hasSet = tree[v].hasSet;
                tree[2 * v + 2].min = tree[v].min;
            }
            tree[v].hasSet = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        k = nextPow(n);
        tree = new Node[2 * k - 1];

        for (int i = 0; i < n; i++) {
            tree[k - 1 + i] = new Node();
            tree[k - 1 + i].pos = i;
        }
        for (int i = n; i < k; i++) {
            tree[k - 1 + i] = new Node();
            tree[k - 1 + i].min = Long.MAX_VALUE;
            tree[k - 1 + i].pos = i;
        }
        for (int i = k - 2; i >= 0; i--) {
            tree[i] = new Node();
            if (tree[2 * i + 1].min < tree[2 * i + 2].min) {
                tree[i].min = tree[2 * i + 1].min;
                tree[i].pos = tree[2 * i + 1].pos;
            } else {
                tree[i].min = tree[2 * i + 2].min;
                tree[i].pos = tree[2 * i + 2].pos;
            }

        }


        for (int i = 0; i < m; i++) {
            switch (sc.next()) {
                case "attack":
                    minAns = Long.MAX_VALUE;
                    pos = -1;
                    get(0, 0, k, sc.nextInt()-1, sc.nextInt());
                    System.out.println(minAns + " " + (pos + 1));
                    break;
                case "defend":
                    set(0, 0, k, sc.nextInt()-1, sc.nextInt(), sc.nextLong());
                    break;
            }
        }
    }
}