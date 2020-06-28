import java.io.*;
import java.util.Scanner;

public class Main {

    public static File in;
    public static File out;
    public static int k;
    public static int r;
    public static int[][] tree;
    public static StringBuilder result = new StringBuilder();
    public static final int[] E = new int[]{1, 0, 0, 1};

    public static void writeInFile(String s) throws IOException {
        BufferedWriter bf = new BufferedWriter(new FileWriter(out));
        bf.write(s);
        bf.close();
    }

    private static int nextPow(int n) {
        int p = 1;
        while (p < n) p *= 2;
        return p;
    }

    private static int[] mult(int[] m1, int[] m2) {
        int[] res = new int[4];
        res[0] = (m1[0] * m2[0] + m1[1] * m2[2]) % r;
        res[1] = (m1[0] * m2[1] + m1[1] * m2[3]) % r;
        res[2] = (m1[2] * m2[0] + m1[3] * m2[2]) % r;
        res[3] = (m1[2] * m2[1] + m1[3] * m2[3]) % r;
        return res;
    }

    public static String toString(int[] m) {
        return m[0] + " " + m[1] + "\n" + m[2] + " " + m[3] + "\n\n";
    }

    private static int[] mult(int v, int l, int r, int a, int b) {
        if (l >= a && r <= b) {
            return tree[v];
        }
        if (l >= b || r <= a) {
            return E;
        }
        return mult(mult(2 * v + 1, l, (l + r) / 2, a, b), mult(2 * v + 2, (l + r) / 2, r, a, b));
    }

    public static void main(String[] args) throws IOException {
        in = new File("crypto.in");
        out = new File("crypto.out");

        Scanner sc = new Scanner(in);

        r = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();

        k = nextPow(n);
        tree = new int[2 * k - 1][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                tree[k - 1 + i][j] = sc.nextInt();
            }
        }
        for (int i = n; i < k - 1; i++) {
            tree[k - 1 + i] = E;
        }
        for (int i = k - 2; i >= 0; i--) {
            tree[i] = mult(tree[2 * i + 1], tree[2 * i + 2]);
        }

        for (int i = 0; i < m; i++) {
            result.append(toString(mult(0, 0, k, sc.nextInt() - 1, sc.nextInt())));
        }
        writeInFile(result.toString());
    }
}