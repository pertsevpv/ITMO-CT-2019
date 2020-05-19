import java.io.*;
import java.util.*;

public class Main {

    public static int getBit(int p, int i) {
        return (p >> i) & 1;
    }

    public static boolean match(int p, int p1, int n) {
        for (int i = 0; i < n - 1; i++) {
            if ((getBit(p, i) + getBit(p1, i) + getBit(p, i + 1) + getBit(p1, i + 1)) % 4 == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        if (n > m) {
            int tmp = m;
            m = n;
            n = tmp;
        }
        int[][] dp = new int[m][1 << n];
        for (int i = 0; i < 1 << n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int p = 0; p < 1 << n; p++) {
                for (int p1 = 0; p1 < 1 << n; p1++) {
                    if (match(p, p1, n)) {
                        dp[i][p] += dp[i - 1][p1];
                    }
                }
            }
        }
        long res = 0L;
        for (int i = 0; i < 1 << n; i++) {
            res += dp[m - 1][i];
        }
        System.out.println(res);
    }
}