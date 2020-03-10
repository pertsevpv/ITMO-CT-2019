import java.io.*;
import java.util.*;

public class Task09 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));

        bf.write(str);
        bf.flush();
        bf.close();
    }

    public static void f(String s, int op, int cl, int n) {
        if (op + cl == 2 * n) {
            result.append(s).append("\n");
        } else {
            if (op < n) f(s + "(", op + 1, cl, n);
            if (op > cl) f(s + ")", op, cl + 1, n);
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("brackets.in");
        out = new File("brackets.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        f("", 0, 0, n);
        writeInFile(result.toString());
    }
}