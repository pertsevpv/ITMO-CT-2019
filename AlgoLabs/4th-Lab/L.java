import java.util.Scanner;

public class Main {

    public static long[][][] tree;
    public static int n;

    public static int f(int i) {
        return i & (i + 1);
    }

    public static void update(int x, int y, int z, int val) {
        for (int i = x; i < n; i |= (i + 1)) {
            for (int j = y; j < n; j |= (j + 1)) {
                for (int k = z; k < n; k |= (k + 1)) {
                    tree[i][j][k] += val;
                }
            }
        }
    }

    public static long sum(int x, int y, int z) {
        long res = 0;
        for (int i = x; i >= 0; i = f(i) - 1) {
            for (int j = y; j >= 0; j = f(j) - 1) {
                for (int k = z; k >= 0; k = f(k) - 1) {
                    res += tree[i][j][k];
                }
            }
        }
        return res;
    }

    public static long rmq(int x1, int y1, int z1, int x2, int y2, int z2) {
        return sum(x2, y2, z2)
                - sum(x1 - 1, y2, z2) - sum(x2, y1 - 1, z2) - sum(x2, y2, z1 - 1)
                + sum(x2, y1 - 1, z1 - 1) + sum(x1 - 1, y2, z1 - 1) + sum(x1 - 1, y1 - 1, z2)
                - sum(x1 - 1, y1 - 1, z1 - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        tree = new long[n][n][n];
        int m = sc.nextInt();
        while (m != 3) {
            switch (m) {
                case 1:
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    int z = sc.nextInt();
                    int k = sc.nextInt();
                    update(x, y, z, k);
                    break;
                case 2:
                    int x1 = sc.nextInt();
                    int y1 = sc.nextInt();
                    int z1 = sc.nextInt();
                    int x2 = sc.nextInt();
                    int y2 = sc.nextInt();
                    int z2 = sc.nextInt();
                    System.out.println(rmq(x1, y1, z1, x2, y2, z2));
                    break;
            }
            m = sc.nextInt();
        }
    }
}