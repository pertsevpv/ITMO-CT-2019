
import java.util.Scanner;

public class F {

    public static int n;

    public static String getPol(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++) {
            res.append((toBool(s.charAt(i)) ^ toBool(s.charAt(i + 1))) ? '1' : '0');
        }
        return res.toString();
    }

    public static boolean toBool(char c) {
        return c == '1';
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        String[][] val = new String[(int) Math.pow(2, n)][2];
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < val.length; i++) {
            val[i][0] = sc.next();
            line.append(sc.nextInt());
        }
        val[0][1] = String.valueOf(line.charAt(0));
        int c = 1;
        while (line.length() > 1) {
            String s = getPol(line.toString());
            val[c][1] = String.valueOf(s.charAt(0));
            line = new StringBuilder(s);
            c++;
        }
        for (int i = 0; i < Math.pow(2, n); i++) {
            System.out.println(val[i][0] + " " + val[i][1]);
        }
    }
}
