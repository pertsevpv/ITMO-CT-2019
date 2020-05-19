import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        int[][] d = new int[s1.length() + 1][s2.length() + 1];
        d[0][0] = 0;
        for (int i = 1; i < s2.length() + 1; i++) {
            d[0][i] = d[0][i - 1] + 1;
        }
        for (int i = 1; i < s1.length() + 1; i++) {
            d[i][0] = d[i - 1][0] + 1;
        }
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                    d[i][j] = Math.min(d[i - 1][j] + 1, Math.min(d[i][j - 1] + 1, d[i - 1][j - 1] + 1));
                } else {
                    d[i][j] = d[i - 1][j - 1];
                }
            }
        }
        System.out.println(d[s1.length()][s2.length()]);
    }
}