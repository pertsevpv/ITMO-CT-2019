
import java.io.*;
import java.util.*;

public class Task29 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.write(str);
        bf.flush();
        bf.close();
    }

    public static void f(int n, ArrayList<Integer> part) {
        part.set(part.size() - 1, part.get(part.size() - 1) - 1);
        part.set(part.size() - 2, part.get(part.size() - 2) + 1);
        if (part.get(part.size() - 2) > part.get(part.size() - 1)) {
            part.set(part.size() - 2, part.get(part.size() - 2) + part.get(part.size() - 1));
            part.remove(part.size() - 1);
        } else {
            while (part.get(part.size() - 2) * 2 <= part.get(part.size() - 1)) {
                part.add(part.get(part.size() - 1) - part.get(part.size() - 2));
                part.set(part.size() - 2, part.get(part.size() - 3));
            }
        }
        result.append(n).append("=");
        for (int i = 0; i < part.size(); i++) {
            result.append(part.get(i)).append(i != part.size() - 1 ? "+" : "");
        }
    }

    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        in = new File("nextpartition.in");
        out = new File("nextpartition.out");

        Scanner sc = new Scanner(in);

        String s = sc.next();
        int n = Integer.parseInt(s.substring(0, s.indexOf('=')));
        System.out.println(n);
        String[] p = s.substring(s.indexOf('=') + 1).split("\\+");
        ArrayList<Integer> part = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            part.add(Integer.parseInt(p[i]));
        }

        if (n == part.get(0)) {
            result.append("No solution");
        } else {
            f(n, part);
        }

        writeInFile(result.toString());
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }
}