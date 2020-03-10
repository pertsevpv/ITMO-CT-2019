import java.io.*;
import java.util.*;

public class Task12 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();
    public static ArrayList<ArrayList<Integer>> parts = new ArrayList<>();

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));

        bf.write(str);
        bf.flush();
        bf.close();
    }

    static long fact(int n) {
        long res = 1L;
        for (int i = 1; i < n + 1; i++) {
            res *= i;
        }
        return res;
    }

    static void getPart(int[] arr, int n, int k) {
        StringBuilder[] sb = new StringBuilder[k];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }
        for (int i = 0; i < n; i++) {
            sb[arr[i]].append(i + 1).append(" ");
        }
        for (int i = 0; i < k; i++) {
            result.append(sb[i].toString()).append("\n");
        }
        result.append("\n");
    }

    public static void f(int i, int j, int n, int k, int[] arr) {
        if (i == n) {
            if (j != k) return;
            getPart(arr, n, k);
        } else {
            for (int l = 0; l < j + 1; l++) {
                arr[i] = l;
                if (l == j) {
                    f(i + 1, j + 1, n, k, arr);
                } else {
                    f(i + 1, j, n, k, arr);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        in = new File("part2sets.in");
        out = new File("part2sets.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] parts = new int[n];
        f(0, 0, n, k, parts);
        writeInFile(result.toString().trim());
    }
}