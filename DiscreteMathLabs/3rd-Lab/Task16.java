import java.io.*;
import java.util.*;

public class Task16 {

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


    public static void f(int n, int k, long[][] arr, int[] choose) {
        for (int i = 0; i < n; i++) {
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
        int num = 0;

        for (int i = 1; i < k + 1; i++) {
            for (int j = choose[i - 1] + 1; j < choose[i]; j++) {
                num += arr[n - j][k - i];
            }
        }
        result.append(num);
    }


    public static void main(String[] args) throws IOException {
        in = new File("choose2num.in");
        out = new File("choose2num.out");

        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        long[][] arr = new long[n][k];
        int[] choose = new int[k + 1];

        for (int i = 0; i < k; i++) {
            choose[i + 1] = sc.nextInt();
        }

        f(n, k, arr, choose);

        writeInFile(result.toString().trim());
    }
}