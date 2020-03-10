import java.io.*;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class Main {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();
    public static HashSet<String> subs = new HashSet<>();

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));

        bf.write(str);
        bf.flush();
        bf.close();
    }

    public static void f(int n, int p, String sub) {
        if (!subs.contains(sub)) {
            result.append(sub.trim()).append("\n");
            subs.add(sub);
        }
        for (int i = p; i < n; i++) {
            f(n, i + 1, sub + (i + 1) + " ");
            f(n, i + 1, sub);
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("subsets.in");
        out = new File("subsets.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        f(n, 0, "");
        writeInFile(result.toString());
    }
}