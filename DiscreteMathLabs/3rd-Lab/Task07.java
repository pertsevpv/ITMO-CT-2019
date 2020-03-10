import java.io.*;
import java.util.*;

public class Task07 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));

        bf.write(str);
        bf.flush();
        bf.close();
    }


    public static void f(String s, ArrayList<Integer> arr) {
        if (arr.size() == 0) {
            result.append(s.trim()).append("\n");
        } else {
            for (int i = 0; i < arr.size(); i++) {
                ArrayList<Integer> arr1 = new ArrayList<>(arr);
                String s1 = new String(s);
                s1 = s + arr1.remove(i) + " ";
                f(s1, arr1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("permutations.in");
        out = new File("permutations.out");
        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            l.add(i+1);
        }
        f("", l);

        writeInFile(result.toString());
    }
}