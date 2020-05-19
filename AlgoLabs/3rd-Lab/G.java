import java.io.*;
import java.util.*;

public class Main {

    public static StringBuilder result = new StringBuilder();

    public static Map<Character, Character> brackets = Map.of(
            '(', ')',
            '{', '}',
            '[', ']',
            '<', '>');
    public static Set<Character> open = Set.of('(', '{', '[', '<');


    public static void get(int l, int r, int[][] d, int[][] a, String s) {
        if (d[l][r] == r - l + 1) return;
        if (d[l][r] == 0) {
            if (l < r - l) {
                result.append(s, l, r - l + 1);
            } else {
                result.append(s, l, r + 1);
            }
            return;
        }
        if (a[l][r] == -1) {
            result.append(s.charAt(l));
            get(l + 1, r - 1, d, a, s);
            result.append(s.charAt(r));
            return;
        }
        get(l, a[l][r], d, a, s);
        get(a[l][r] + 1, r, d, a, s);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.next().trim();
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dp[i][i] = 1;
                } else if (i > j) {
                    dp[i][j] = 0;
                } else dp[i][j] = -1;
            }
        }
        int[][] a = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == -1) {
                    int min = Integer.MAX_VALUE;
                    int pos = -1;
                    if (open.contains(s.charAt(i)) && !(open.contains(s.charAt(j)))) {
                        if (brackets.get(s.charAt(i)) == s.charAt(j)) {
                            min = dp[i + 1][j - 1];
                        }
                    }
                    for (int k = i; k < j; ++k) {
                        if (dp[i][k] + dp[k + 1][j] < min) {
                            min = dp[i][k] + dp[k + 1][j];
                            pos = k;
                        }
                    }
                    dp[i][j] = min;
                    a[i][j] = pos;
                }
            }
        }
/*        for (int[] a1 : dp) {
            System.out.println(Arrays.toString(a1));
        }*/
        get(0, n - 1, dp, a, s);
        System.out.println(result.toString());
    }
}