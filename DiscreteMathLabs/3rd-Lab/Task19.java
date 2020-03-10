import java.io.*;
import java.util.*;

public class Task19 {

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
        Stack<Character> prev = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {

            long cur = 0L;
            if (dep < n)
                cur = arr[i][dep + 1] << ((i - dep - 1) >> 1);

            if (cur >= k) {
                result.append('(');
                prev.push('(');
                dep++;
                continue;
            }

            k -= cur;
            if (!prev.isEmpty()) {
                cur = (prev.peek() == '(' && dep - 1 >= 0) ? arr[i][dep - 1] << ((i - dep + 1) >> 1) : 0L;
            } else {
                cur = 0L;
            }

            if (cur >= k) {
                result.append(')');
                prev.pop();
                dep--;
                continue;
            }

            k -= cur;
            cur = (dep < n) ? arr[i][dep + 1] << ((i - dep - 1) >> 1) : 0L;
            if (cur >= k) {
                result.append('[');
                prev.push('[');
                dep++;
                continue;
            }

            k -= cur;
            result.append(']');
            prev.pop();
            dep--;
        }
    }

    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        in = new File("num2brackets2.in");
        out = new File("num2brackets2.out");

        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        long k = sc.nextLong();
        f(n, k);

        writeInFile(result.toString());
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }
}