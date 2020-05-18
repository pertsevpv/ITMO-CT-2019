import java.util.Scanner;

public class Main {
    static int[] sets;
    static int[] r;

    static int getSet(int x) {
        int root = x;
        while (sets[root] != root) {
            root = sets[root];
        }
        return root;
    }

    static void union(int x, int y) {
        x = getSet(x);
        y = getSet(y);
        if (x == y) {
            return;
        }
        sets[y] = x;
        r[y] -= r[x];
    }

    static void add(int x, int v) {
        x = getSet(x);
        r[x] += v;
    }

    static int get(int x) {
        int res = 0;
        while (x != sets[x]) {
            res += r[x];
            x = sets[x];
        }
        return res + r[x];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sets = new int[n];
        r = new int[n];
        for (int i = 0; i < n; i++) {
            sets[i] = i;
            r[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            switch (sc.next()) {
                case "join": {
                    int a = sc.nextInt() - 1;
                    int b = sc.nextInt() - 1;
                    union(a, b);
                    break;
                }
                case "get": {
                    int a = sc.nextInt() - 1;
                    System.out.println(get(a));
                    break;
                }
                case "add": {
                    int a = sc.nextInt() - 1;
                    int v = sc.nextInt();
                    add(a, v);
                    break;
                }
            }
        }
    }
}