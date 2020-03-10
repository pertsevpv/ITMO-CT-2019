import java.io.*;
import java.util.*;

public class Task23 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.write(str);
        bf.flush();
        bf.close();
    }

    public static String getNext(String str) {
        char[] vec = str.toCharArray();
        for (int i = str.length() - 1; i >= 0; i--) {
            if (vec[i] == '0') {
                vec[i] = '1';
                return String.valueOf(vec);
            } else {
                vec[i] = '0';
            }
        }
        return "-";
    }

    public static String getPrev(String str) {
        char[] vec = str.toCharArray();
        for (int i = str.length() - 1; i >= 0; i--) {
            if (vec[i] == '1') {
                vec[i] = '0';
                return String.valueOf(vec);
            } else {
                vec[i] = '1';
            }
        }
        return "-";
    }

    public static void f(String s) {
        result.append(getPrev(s)).append("\n").append(getNext(s));
    }

    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        in = new File("nextvector.in");
        out = new File("nextvector.out");

        Scanner sc = new Scanner(in);
        String s = sc.next();

        f(s);
        writeInFile(result.toString());

        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }
}