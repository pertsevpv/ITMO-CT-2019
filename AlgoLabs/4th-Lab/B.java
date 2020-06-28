import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int k;

    private static int nextPow(int n) {
        int p = 1;
        while (p < n) p *= 2;
        return p;
    }

    public static long sum(long[] tree, int v, int l, int r, int a, int b) {
        if (l >= a && r <= b) {
            return tree[v];
        }
        if (l >= b || r <= a) {
            return 0;
        }
        return sum(tree, 2 * v + 1, l, (l + r) / 2, a, b) + sum(tree, 2 * v + 2, (l + r) / 2, r, a, b);
    }

    public static void set(long[] tree, int i, long x) {
        tree[k - 1 + i] = x;
        int j = k - 1 + i;
        while (j > 0) {
            j = (j - 1) / 2;
            tree[j] = tree[2 * j + 1] + tree[2 * j + 2];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        k = nextPow(n);
        long[] tree = new long[2 * k - 1];
        for (int i = 0; i < n; i++) {
            tree[k - 1 + i] = sc.nextLong();
        }
        for (int i = k - 2; i >= 0; i--) {
            tree[i] = tree[2 * i + 1] + tree[2 * i + 2];
        }
        while (sc.hasNext()) {
            switch (sc.next()) {
                case "sum":
                    System.out.println(sum(tree, 0, 0, k, sc.nextInt() - 1, sc.nextInt()));
                    break;
                case "set":
                    set(tree, sc.nextInt() - 1, sc.nextLong());
                    break;
            }
        }
    }
}
