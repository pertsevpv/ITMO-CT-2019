import java.io.*;
import java.util.*;

public class Task17 {

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


    public static void f(int n, long k) {
        result = new StringBuilder("");
        k += 1;
        long[][] arr = new long[2 * n + 1][n + 1];
        arr[0][0] = 1L;
        for (int i = 1; i < 2 * n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (j > 0) {
                    arr[i][j] += arr[i - 1][j - 1];
                }
                if (j + 1 <= n) {
                    arr[i][j] += arr[i - 1][j + 1];
                }
            }
        }
        int dep = 0;
        for (int i = 2 * n - 1; i >= 0; i--) {
            if (dep + 1 <= n && arr[i][dep + 1] >= k) {
                result.append("(");
                dep++;
            } else {
                result.append(")");
                if (dep + 1 <= n) {
                    k -= arr[i][dep + 1];
                }
                dep--;
            }
        }
        System.out.println(result.toString());
    }


    public static void main(String[] args) throws IOException {
        in = new File("num2brackets.in");
        out = new File("num2brackets.out");

        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        long k = sc.nextLong();
        f(n, k);

        writeInFile(result.toString().trim());
    }
}