import java.io.*;
import java.util.*;

public class Task22 {

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
        long[][] arr = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    for (int l = j; l < i + 1; l++) {
                        arr[j][i] += arr[l][i - j - 1];
                    }
                } else {
                    arr[i][j] = 1L;
                }
            }
        }
        long num = 0L;
        int min = 0;
        for (int i = 0; i < part.size(); i++) {
            while (min < part.get(i) - 1) {
                num += arr[min][n - 1];
                min++;
            }
            n -= min + 1;
        }
        result.append(num);
    }

    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        in = new File("part2num.in");
        out = new File("part2num.out");

        Scanner sc = new Scanner(in);
        String s = sc.next();
        String[] p = s.split("\\+");
        ArrayList<Integer> part = new ArrayList<>();
        int n = 0;
        for (int i = 0; i < p.length; i++) {
            part.add(Integer.parseInt(p[i]));
            n += part.get(i);
        }
        f(n, part);

        writeInFile(result.toString());
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }
}