import java.util.Scanner;
 
public class Main {
 
    public static int k;
    public static long[] tree;
    public static long[] upd;
    public static long[] set;
    public static boolean[] hasSet;
 
    private static int nextPow(int n) {
        int p = 1;
        while (p < n) p *= 2;
        return p;
    }
 
    public static void pushset(int v) {
        if (hasSet[v]) {
            tree[v] = set[v];
            hasSet[v] = false;
 
            if (v < k - 1) {
                set[v * 2 + 1] = set[v];
                set[v * 2 + 2] = set[v];
 
                hasSet[v * 2 + 1] = true;
                hasSet[v * 2 + 2] = true;
 
                upd[v * 2 + 1] = 0;
                upd[v * 2 + 2] = 0;
            }
        }
    }
 
    public static void push(int v) {
        pushset(v);
        if (upd[v] == 0) return;
 
        tree[v] += upd[v];
        set[v] = tree[v];
        long tmp = upd[v];
        upd[v] = 0;
        if (v < k - 1) {
            upd[2 * v + 1] += tmp;
            upd[2 * v + 2] += tmp;
        }
    }
 
    public static long min(int v, int l, int r, int a, int b) {
        if (b <= l || a >= r) return Long.MAX_VALUE;
        if (l >= a && r <= b) {
            push(v);
            return tree[v];
        }
        push(v);
        return Math.min(min(2 * v + 1, l, (l + r) / 2, a, b), min(2 * v + 2, (l + r) / 2, r, a, b));
    }
 
    public static void add(int v, int l, int r, int a, int b, long x) {
        if (b <= l || a >= r) return;
        if (l >= a && r <= b) {
            push(v);
            upd[v] = x;
            return;
        }
        push(v);
        add(2 * v + 1, l, (l + r) / 2, a, b, x);
        add(2 * v + 2, (l + r) / 2, r, a, b, x);
        tree[v] = Math.min(set[2 * v + 1] + upd[2 * v + 1], set[2 * v + 2] + upd[2 * v + 2]);
        set[v] = tree[v];
    }
 
    public static void set(int v, int l, int r, int a, int b, long x) {
        if (b <= l || a >= r) return;
        if (l >= a && r <= b) {
            upd[v] = 0;
            pushset(v);
            set[v] = x;
            hasSet[v] = true;
            return;
        }
        push(v);
        set(2 * v + 1, l, (l + r) / 2, a, b, x);
        set(2 * v + 2, (l + r) / 2, r, a, b, x);
        tree[v] = Math.min(set[2 * v + 1] + upd[2 * v + 1], set[2 * v + 2] + upd[2 * v + 2]);
        set[v] = tree[v];
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        k = nextPow(n);
        tree = new long[2 * k - 1];
        upd = new long[2 * k - 1];
        set = new long[2 * k - 1];
        hasSet = new boolean[2 * k - 1];
 
        for (int i = 0; i < n; i++) {
            tree[k - 1 + i] = sc.nextLong();
            set[k - 1 + i] = tree[k - 1 + i];
        }
        for (int i = n; i <= k - 1; i++) {
            tree[k - 1 + i] = Long.MAX_VALUE;
            set[k - 1 + i] = tree[k - 1 + i];
        }
        for (int i = k - 2; i >= 0; i--) {
            tree[i] = Math.min(tree[2 * i + 1], tree[2 * i + 2]);
            set[i] = tree[i];
        }
 
 
        while (sc.hasNext()) {
            switch (sc.next()) {
                case "min":
                    System.out.println(min(0, 0, k, sc.nextInt() - 1, sc.nextInt()));
                    break;
                case "set":
                    set(0, 0, k, sc.nextInt() - 1, sc.nextInt(), sc.nextLong());
                    break;
                case "add":
                    add(0, 0, k, sc.nextInt() - 1, sc.nextInt(), sc.nextLong());
                    break;
            }
        }
    }
}