import java.io.*;
import java.util.*;

public class Task08 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));

        bf.write(str);
        bf.flush();
        bf.close();
    }


    public static void f(String s, ArrayList<Integer> arr, int l, int k, int pos) {
        if (l == k) {
            result.append(s.trim()).append("\n");
        } else {
            for (int i = pos; i < arr.size(); i++) {
                f(s + arr.get(i) + " ", arr, l + 1, k, i + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("choose.in");
        out = new File("choose.out");
        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            l.add(i + 1);
        }
        f("", l, 0, k, 0);

        writeInFile(result.toString());
    }
}