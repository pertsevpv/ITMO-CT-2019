import java.io.*;
import java.util.*;

public class Task10 {

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

    static void f(String s, int cur, int prev) {
        if (cur < 0) {
            return;
        } else if (cur == 0) {
            result.append(s, 0, s.length() - 1).append("\n");
        } else {
            for (int i = prev; i < cur + 1; i++) {
                f(s + i + "+", cur - i, i);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        in = new File("partition.in");
        out = new File("partition.out");
//      long l1 = System.currentTimeMillis();
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();

        f("", n, 1);
        writeInFile(result.toString().trim()); 
//      long l2 = System.currentTimeMillis(); 
//      System.out.println(l2 - l1);
    }
}