import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task03 {

    public static File in;
    public static File out;

    public static ArrayList<String> vectors = new ArrayList<>();

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.write(str);
        bf.flush();
        bf.close();
    }

    public static String nextVec(String vec) {
        char[] c = vec.toCharArray();
        for (int i = 0; i < c.length; i++) {
            c[i] = (char) (((((int) c[i] - 48) + 1) % 3) + 48);
        }
        vec = String.valueOf(c);
        return vec;
    }

    public static void f(String vec, int i, int n) {
        if (i == n) {
            vectors.add(vec);
        } else {
            i++;
            f(vec + '0', i, n);
            f(vec + '1', i, n);
            f(vec + '2', i, n);
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("antigray.in");
        out = new File("antigray.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();

        f("", 0, n);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < vectors.size() / 3; i++) {
            String s = nextVec(vectors.get(i));
            for (int j = 0; j < 3; j++) {
                result.append(s).append("\n");
                s = nextVec(s);
            }
        }
        writeInFile(result.toString());
    }
}
