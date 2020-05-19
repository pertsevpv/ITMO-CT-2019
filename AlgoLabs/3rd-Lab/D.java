import java.util.*;

public class Main {

    public static HashMap<Integer, List<Integer>> ways = new HashMap<>();

    public static void setWays() {
        ways.put(0, List.of(4, 6));
        ways.put(1, List.of(6, 8));
        ways.put(2, List.of(7, 9));
        ways.put(3, List.of(4, 8));
        ways.put(4, List.of(3, 9, 0));
        ways.put(5, List.of());
        ways.put(6, List.of(1, 7, 0));
        ways.put(7, List.of(2, 6));
        ways.put(8, List.of(1, 3));
        ways.put(9, List.of(4, 2));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        setWays();
        int l = sc.nextInt();
        int[][] table = new int[l][10];
        for (int i = 0; i < 10; i++) {
            if (i != 0 && i != 8) table[0][i] = 1;
        }
        for (int i = 1; i < l; i++) {
            for (int j = 0; j < 10; j++) {
                ArrayList<Integer> list = new ArrayList<>(ways.get(j));
                for (Integer n : list) {
                    table[i][j] += table[i - 1][n];
                    table[i][j] %= 1_000_000_000;
                }
            }
        }
        long sum = 0L;
        for (int i = 0; i < 10; i++) {
            sum += table[l - 1][i];
            sum %= 1_000_000_000;
        }
        System.out.println(sum);
    }
}