import java.io.*;
import java.util.*;

public class Task18 {

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


    public static void f(String br, int n) {
        result = new StringBuilder("");
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
        long num = 0L;
        for (int i = 0; i < 2 * n; i++) {
            if (br.charAt(i) == '(') {
                dep++;
            } else {
                if (!(dep + 1 > n)) num += arr[2 * n - i - 1][dep + 1];
                dep--;
            }
        }
        result.append(num);
        System.out.println(result.toString());
    }


    public static void main(String[] args) throws IOException {
        in = new File("brackets2num.in");
        out = new File("brackets2num.out");

        Scanner sc = new Scanner(in);

        String brackets = sc.next();
        int n = brackets.length()/2;
        f(brackets, n);

        writeInFile(result.toString().trim());
    }
}