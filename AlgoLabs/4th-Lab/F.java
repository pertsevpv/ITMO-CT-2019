import java.util.Scanner;

public class Main {

    public static int[][] table;

    public static int log(int a) {
        for (int i = 1, j = 0; ; i *= 2, j++) {
            if (i >= a) return j;
        }
    }

    public static int log1(int a) {
        for (int i = 0; ; i++) {
            a /= 2;
            if (a == 0) return i;
        }
    }

    static int getMin(int[] a, int l, int r) {
        int k = log1(r - l + 1);
        if (a[table[r - (1 << k) + 1][k]] < a[table[l][k]]) {
            return table[r - (1 << k) + 1][k];
        } else return table[l][k];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int a1 = sc.nextInt();

        int u1 = sc.nextInt();
        int v1 = sc.nextInt();

        int[] a = new int[n + 1];
        int lg = log(n);
        if (lg == 0) {
            lg = 1;
        }
        table = new int[n + 1][lg];
        a[1] = a1;
        for (int i = 2; i <= n; i++) {
            a[i] = (23 * a[i - 1] + 21563) % 16714589;
        }
        for (int i = 0; i <= n; i++) {
            table[i][0] = i;
        }
        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                if (a[table[i][j - 1]] < a[table[i + (1 << (j - 1))][j - 1]]) {
                    table[i][j] = table[i][j - 1];
                } else {
                    table[i][j] = table[i + (1 << (j - 1))][j - 1];
                }
            }
        }
        int ri = 0;
        for (int i = 1; i <= m; i++) {
            int l = Math.min(u1, v1);
            int r = Math.max(u1, v1);
            ri = a[getMin(a, l, r)];
            if (i != m) {
                u1 = ((17 * u1 + 751 + ri + 2 * i) % n) + 1;
                v1 = ((13 * v1 + 593 + ri + 5 * i) % n) + 1;
            }
        }
        System.out.println(u1 + " " + v1 + " " + ri);
    }
}