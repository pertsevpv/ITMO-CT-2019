import java.io.*;
import java.util.*;

public class Task13 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();
    public static HashSet<Long> perms = new HashSet<>();

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

    static void f(int n, long k) {
        k += 1;
        for (int i = 0; i < n; i++) {
            long f = fact(n - i - 1);
            long num = (k - 1) / f + 1;
            long res = 1L;
            long cur = 0L;
            while (res <= n) {
                if (!perms.contains(res)) {
                    ++cur;
                }
                if (num == cur) {
                    break;
                }
                ++res;
            }
            perms.add(res);
            result.append(res).append(" ");
            k = (k - 1) % f + 1;
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("num2perm.in");
        out = new File("num2perm.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        f(n, k);
        writeInFile(result.toString().trim());
    }
}