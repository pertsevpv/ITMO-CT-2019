import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        File in = new File("huffman.in");
        File out = new File("huffman.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int[] p = new int[n];

        for (int i = 0; i < p.length; i++) {
            p[i] = sc.nextInt();
        }
        Arrays.sort(p);
        Long[] mins = new Long[n];
        int i = 0, j = 0;
        long res = 0L;

        for (int k = 0; k < n - 1; k++) {
            long m1;
            if (i + 1 >= n) {
                m1 = Long.MAX_VALUE;
            } else {
                m1 = p[i] + p[i + 1];
            }
            long m2;
            if (mins[j] == null || i >= n) {
                m2 = Long.MAX_VALUE;
            } else {
                m2 = p[i] + mins[j];
            }
            long m3;
            if (mins[j] == null || mins[j + 1] == null) {
                m3 = Long.MAX_VALUE;
            } else {
                m3 = mins[j] + mins[j + 1];
            }

            if (m1 <= m2 && m1 <= m3) {
                mins[k] = m1;
                res += mins[k];
                i += 2;
            } else if (m2 <= m1 && m2 <= m3) {
                mins[k] = m2;
                res += mins[k];
                i++;
                j++;
            } else {
                mins[k] = m3;
                res += mins[k];
                j += 2;
            }
        }

        FileWriter fw = new FileWriter(out);
        fw.append(String.valueOf(res));
        fw.close();
    }
}