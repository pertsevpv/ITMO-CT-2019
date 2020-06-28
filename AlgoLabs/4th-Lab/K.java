import java.io.*;
import java.util.Scanner;

public class Main {

    public static File in;
    public static File out;
    public static int k;
    public static boolean[] tree;
    public static StringBuilder result = new StringBuilder();

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

    public static void exit(int i) {
        tree[k - 1 + i] = false;
        int j = k - 1 + i;
        while (j > 0) {
            j = (j - 1) / 2;
            tree[j] = false;
        }
    }

    public static int enter(int i) {
        int j = k - 1 + i;
        if (!tree[j]) {
            tree[j] = true;

            while (j > 0) {
                j = (j - 1) / 2;
                tree[j] = tree[j * 2 + 1] && tree[j * 2 + 2];
            }
            return i;
        }
        int prev = j;
        while (j > 0) {
            j = (j - 1) / 2;
            if (2 * j + 2 != prev && !tree[2 * j + 2]) {
                j = 2 * j + 2;
                break;
            }
            prev = j;
        }

        while (j < k - 1) {
            if (!tree[j * 2 + 1]) {
                j = j * 2 + 1;
            } else {
                j = j * 2 + 2;
            }
        }
        tree[j] = true;
        int ans = j - k + 1;
        while (j > 0) {
            j = (j - 1) / 2;
            tree[j] = tree[j * 2 + 1] && tree[j * 2 + 2];
        }
        return j + ans;
    }

    public static void main(String[] args) throws IOException {
        in = new File("parking.in");
        out = new File("parking.out");

        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        k = nextPow(n);
        tree = new boolean[2 * k - 1];
        for (int i = n; i <= k - 1; i++) {
            tree[k - 1 + i] = true;
        }
        for (int i = k - 2; i >= 0; i--) {
            tree[i] = tree[i * 2 + 1] && tree[i * 2 + 2];
        }
        for (int i = 0; i < m; i++) {
            switch (sc.next()) {
                case "enter":
                    result.append(enter(sc.nextInt() - 1) + 1).append("\n");
                    break;
                case "exit":
                    exit(sc.nextInt() - 1);
                    break;
            }
        }
        writeInFile(result.toString());
    }
}