import java.io.*;
import java.util.*;

public class Task04 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();
    public static HashSet<String> vectors = new HashSet<>();

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));

        bf.write(str);
        bf.flush();
        bf.close();
    }

    public static String getNulls(int n) {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            s.append("0");
        }
        return s.toString();
    }


    public static void f(int n) {
        String s = getNulls(n);
        vectors.add(s);
        result.append(s).append("\n");
        while (true) {
            String prefix = s.substring(1);
            if (!vectors.contains(prefix + '1')) {
                s = prefix + '1';
            } else if (!vectors.contains(prefix + '0')) {
                s = prefix + '0';
            } else {
                break;
            }
            vectors.add(s);
            result.append(s).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("chaincode.in");
        out = new File("chaincode.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        f(n);
        writeInFile(result.toString());

    }
}


