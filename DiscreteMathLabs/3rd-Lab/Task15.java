import java.io.*;
import java.util.*;

public class Task15 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();

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


    public static void f(int n, int k, int m, long[][] arr) {
        for (int i = 0; i < n; i -= -1) {
            int j = 0;
            while (j <= i && j < k) {
                if (j == 0 || i == j) {
                    arr[i][j] = 1L;
                } else {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
                j++;
            }
        }
        int cur = 1;
        while (k > 0) {
            if (m < arr[n - 1][k - 1]) {
                result.append(cur).append(" ");
                k--;
            } else {
                m -= arr[n - 1][k - 1];
            }
            n--;
            cur++;
        }
    }


    public static void main(String[] args) throws IOException {
        in = new File("num2choose.in");
        out = new File("num2choose.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int m = sc.nextInt();
        long[][] parts = new long[n][k];
        f(n, k, m, parts);
        writeInFile(result.toString().trim());
    }
}