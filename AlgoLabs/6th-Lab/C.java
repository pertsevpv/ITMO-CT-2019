import java.io.*;
import java.util.*;

public class Main {

    private static File in = new File("minonpath.in");
    private static File out = new File("minonpath.out");
    private static Scanner sc;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }

    static {
        try {
            sc = new Scanner(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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

    static int lca(int u, int v, int[] w, int[] h, int[][] dp, int[][] dpW) {
        Integer min = Integer.MAX_VALUE;
        if (h[v] > h[u]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        int dl = h[u] - h[v];
        for (int i = log(w.length) - 1; i >= 0; i--) {
            if (dl >= (1 << i)) {
                dl -= (1 << i);
                min = Math.min(min, dpW[u][i]);
                u = dp[u][i];
            }
        }
        if (v == u) return min ;
        for (int i = log(w.length) - 1; i >= 0; i--) {
            if (dp[v][i] != dp[u][i]) {
                min = Math.min(min, Math.min(dpW[u][i], dpW[v][i]));
                v = dp[v][i];
                u = dp[u][i];
            }
        }
        return Math.min(min, Math.min(dpW[u][0], dpW[v][0]));
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        n++;
        int[] p = new int[n];
        int[] w = new int[n];
        p[1] = 0;
        for (int i = 2; i < n; i++) {
            p[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        int[] h = new int[n];
        Arrays.fill(h, -1);
        for (int i = 1; i < n; i++) {
            if (h[i] == -1) setH(h, p, i);
        }

        int lg = log(n);
        int[][] dp = new int[n][lg];
        int[][] dpW = new int[n][lg];

        for (int i = 1; i < n; i++) {
            dp[i][0] = p[i];
            dpW[i][0] = w[i];
        }

        for (int j = 1; j < lg; j++) {
            for (int i = 1; i < n; i++) {
                dp[i][j] = dp[dp[i][j - 1]][j - 1];
            }
            for (int i = 1; i < n; i++) {
                dpW[i][j] = Math.min(dpW[i][j - 1], dpW[dp[i][j - 1]][j - 1]);
            }
        }

        int m = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(lca(sc.nextInt(), sc.nextInt(), p, h, dp, dpW)).append("\n");
        }
        writeInFile(sb.toString());
    }
}