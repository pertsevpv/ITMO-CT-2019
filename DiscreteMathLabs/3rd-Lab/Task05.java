import java.io.*;
import java.util.*;

public class Task05 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();
    public static HashSet<String> vectors = new HashSet<>();
    public static int count;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));

        bf.write(str);
        bf.flush();
        bf.close();
    }


    public static void f(ArrayList<StringBuilder> s, int n, int k) {
        for (int i = 1; i < n; i++) {
            int size = s.size();
            boolean inv = true;
            for (int j = 1; j < k; j++) {
                for (int l = 0; l < size; l++) {
                    if (inv) {
                        s.add(s.get(size - l - 1));
                    } else {
                        s.add(s.get(l));
                    }

                }
                inv = !inv;
            }
            int counter = 0;
            int num = 0;
            int r = s.size() / k;
            for (int j = 0; j < s.size(); j++, counter++) {
                if (counter==r){
                    counter = 0;
                    num++;
                }
                StringBuilder tmp = new StringBuilder(s.get(j)).append(num);
                s.set(j, tmp);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        in = new File("telemetry.in");
        out = new File("telemetry.out");
        Scanner sc = new Scanner(in);

        ArrayList<StringBuilder> base = new ArrayList<>();

        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            base.add(new StringBuilder(String.valueOf(i)));
        }
        f(base, n, k);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < base.size(); i++) {
            result.append(base.get(i));
            result.append("\n");
        }
        writeInFile(result.toString());
    }
}