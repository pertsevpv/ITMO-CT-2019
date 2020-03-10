import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static File in;
    public static File out;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.write(str);
        bf.flush();
        bf.close();
    }

    public static void f(ArrayList<StringBuilder> s, int n) {
        for (int i = 1; i < n; i++) {
            int size = s.size();
            for (int j = 0; j < size; j++) {
                s.add(s.get(size - j - 1));
            }
            for (int j = 0; j < s.size(); j++) {
                StringBuilder tmp = new StringBuilder(j < s.size() / 2 ? "0" : "1").append(s.get(j));
                s.set(j, tmp);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("gray.in");
        out = new File("gray.out");
        Scanner sc = new Scanner(in);

        ArrayList<StringBuilder> base = new ArrayList<>();
        base.add(new StringBuilder("0"));
        base.add(new StringBuilder("1"));


        int n = sc.nextInt();
        f(base, n);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < base.size(); i++) {
            result.append(base.get(i));
            result.append("\n");
        }
        writeInFile(result.toString());
    }
}
