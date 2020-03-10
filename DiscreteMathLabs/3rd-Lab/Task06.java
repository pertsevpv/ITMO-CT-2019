import java.io.*;
import java.util.*;

public class Task06 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();
    public static HashSet<String> vectors = new HashSet<>();
    public static int count;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));

        bf.write(str);
        bf.flush();
        bf.close();
    }


    public static void f(String s, int i, int n) {
        if (i == n) {
            count++;
            result.append(s);
            result.append("\n");
        } else {
            i++;
            f(s + '0', i, n);
            if (s.charAt(s.length() - 1) != '1') {
                f(s + '1', i, n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        in = new File("vectors.in");
        out = new File("vectors.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        count = 0;
        f("0", 1, n);
        f("1", 1, n);
        writeInFile(count + "\n"+ result.toString());

        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }
}
