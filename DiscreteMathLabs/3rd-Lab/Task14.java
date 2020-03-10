import java.io.*;
import java.util.*;

public class Task14 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();
    public static HashSet<Integer> perms = new HashSet<>();

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

    static void f(int n, int[] perm) {
        long[] p1 = new long[n];
        for (int i = 0; i < n; i++) {
            p1[i] = fact(i);
        }
        long res = 0L;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < perm[i]; j++) {
                if (!perms.contains(j)) {
                    res += p1[n - i - 1];
                }
            }
            perms.add(perm[i]);
        }
        result.append(res);
    }

    public static void main(String[] args) throws IOException {
        in = new File("perm2num.in");
        out = new File("perm2num.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }
        f(n, p);
        writeInFile(result.toString().trim());
    }
}