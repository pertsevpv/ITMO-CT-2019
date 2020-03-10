import java.io.*;
import java.util.Scanner;

public class Task01 {

    public static File in;
    public static File out;
    public static StringBuilder result = new StringBuilder("");

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out,true)));
        bf.append(result.toString());
        bf.close();
    }

    public static void f(String s, int i, int n) {
        if (i == n) {
            result.append(s);
            result.append("\n");
        } else {
            i++;
            f(s + '0', i, n);
            f(s + '1', i, n);
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("allvectors.in");
        out = new File("allvectors.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        f("", 0, n);
        writeInFile(result.toString());
    }
}
