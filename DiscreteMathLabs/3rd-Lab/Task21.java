import java.io.*;
import java.util.*;

public class Task21 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.write(str);
        bf.flush();
        bf.close();
    }

    public static void f(int n, int k, int p) {
        k += 1;
        long[][] arr = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    for (int l = j; l < i + 1; l++) {
                        arr[j][i] += arr[l][i - j - 1];
                    }
                } else {
                    arr[i][j] = 1L;
                }
            }
        }

        while (n != p) {
            if (arr[p - 1][n - 1] >= k) {
                result.append(p).append("+");
                n -= p;
            } else {
                p++;
                k -= arr[p - 2][n - 1];
            }
        }
        result.append(p);
    }

    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        in = new File("num2part.in");
        out = new File("num2part.out");

        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        f(n, k, 1);
        writeInFile(result.toString());
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }
}