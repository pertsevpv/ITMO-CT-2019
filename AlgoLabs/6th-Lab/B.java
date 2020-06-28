import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int log(int val) {
        return (int) Math.ceil(Math.log(val) / Math.log(2));
    }

    static int setH(int[] h, int[] p, int v) {
        if (h[v] != -1) return h[v];
        if (p[v] == 0) {
            h[v] = 0;
            return 0;
        } else {
            h[v] = 1 + setH(h, p, p[v]);
            return h[v];
        }
    }

    static int lca(int u, int v, int[] p, int[] h, int[][] dp) {
        if (h[v] > h[u]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        for (int i = log(p.length) - 1; i >= 0; i--) {
            if (h[dp[u][i]] - h[v] >= 0) {
                u = dp[u][i];
            }
        }
        if (v == u) return v;
        for (int i = log(p.length) - 1; i >= 0; i--) {
            if (dp[v][i] != dp[u][i]) {
                v = dp[v][i];
                u = dp[u][i];
            }
        }
        return p[v];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        n++;
        int[] p = new int[n];
        p[1] = 0;
        for (int i = 2; i < n; i++) {
            p[i] = sc.nextInt();
        }
        int[] h = new int[n];

        Arrays.fill(h, -1);
        for (int i = 1; i < n; i++) {
            if (h[i] == -1) setH(h, p, i);
        }
        int lg = log(n);
        int[][] dp = new int[n][lg];
        for (int i = 1; i < n; i++) {
            dp[i][0] = p[i];
        }
        for (int j = 1; j < lg; j++) {
            for (int i = 1; i < n; i++) {
                dp[i][j] = dp[dp[i][j - 1]][j - 1];
            }
        }
        int m = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(lca(sc.nextInt(), sc.nextInt(), p, h, dp)).append("\n");
        }
        System.out.println(sb.toString());
    }
}