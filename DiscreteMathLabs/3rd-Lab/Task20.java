import java.io.*;
import java.util.*;

public class Task20 {

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
        long num = 0L;
        int dep = 0;
        int ndep = 0;
        ArrayDeque<Character> prev = new ArrayDeque<>();
        for (int i = 0; i < 2 * n - 1; i++) {

            if (br.charAt(i) == '(') {
                prev.addLast('(');
                dep++;
                continue;
            }

            ndep = dep + 1;

            if (dep < n)
                num += arr[2 * n - i - 1][ndep] << ((2 * n - i - 1 - ndep) >> 1);

            if (br.charAt(i) == ')') {
                prev.removeLast();
                dep--;
                continue;
            }

            if (!prev.isEmpty()) {
                if (prev.peekLast() == '(' && dep > 0) {
                    ndep = dep - 1;
                    num += arr[2 * n - i - 1][ndep] << ((2 * n - i - 1 - ndep) >> 1);
                }
            }
            if (br.charAt(i) == '[') {
                prev.addLast('[');
                dep++;
                continue;
            }
            if (dep < n) {
                ndep = dep + 1;
                num += arr[2 * n - i - 1][ndep] << ((2 * n - i - 1 - ndep) >> 1);
            }
            if (br.charAt(i) == ']') {
                prev.removeLast();
                dep--;
            }
        }
        result.append(num);
    }

    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        in = new File("brackets2num2.in");
        out = new File("brackets2num2.out");

        Scanner sc = new Scanner(in);

        String brackets = sc.next();
        int n = brackets.length() / 2;
        f(brackets, n);

        writeInFile(result.toString());
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }
}