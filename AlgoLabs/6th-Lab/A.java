import java.util.Scanner;

public class Main {

    static int log(int val) {
        return (int) Math.ceil(Math.log(val) / Math.log(2));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        n++;

        int[] p = new int[n];
        for (int i = 1; i < n; i++) {
            p[i] = sc.nextInt();
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
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            sb.append(i).append(": ");
            for (int j = 0; j < lg; j++) {
                if (dp[i][j] == 0) break;
                else sb.append(dp[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}