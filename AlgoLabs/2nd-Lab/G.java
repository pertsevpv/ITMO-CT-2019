import java.util.Scanner;

public class Main {

    public static int[] sets;
    public static int[][] r;


    public static void union(int x, int y) {
        x = getSet(x);
        y = getSet(y);
        int min = Math.min(r[x][1], r[y][1]);
        int max = Math.max(r[x][2], r[y][2]);
        if (x == y) {
            return;
        }

        if (r[x][0] < r[y][0]) {
            sets[x] = y;
            r[y][0] += r[x][0];
            r[y][1] = min;
            r[y][2] = max;
        } else {
            sets[y] = x;
            r[x][0] += r[y][0];
            r[x][1] = min;
            r[x][2] = max;
        }
    }

    public static void get(int n) {
        System.out.println((r[getSet(n)][1] + 1) + " " + (r[getSet(n)][2] + 1) + " " + r[getSet(n)][0]);
    }

    public static int getSet(int x) {
        int root = x;
        while (sets[root] != root) {
            root = sets[root];
        }
        int t = x;
        while (sets[t] != t) {
            int tmp = sets[t];
            sets[t] = root;
            t = tmp;
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sets = new int[n];
        r = new int[n][3];
        for (int i = 0; i < n; i++) {
            sets[i] = i;
            r[i][0] = 1;
            r[i][1] = i;
            r[i][2] = i;
        }
        while (sc.hasNext()) {
            switch (sc.next()) {
                case "union": {
                    int a = sc.nextInt() - 1;
                    int b = sc.nextInt() - 1;
                    union(a, b);
                    break;
                }
                case "get": {
                    int a = sc.nextInt() - 1;
                    get(a);
                    break;
                }
            }
        }
    }
}