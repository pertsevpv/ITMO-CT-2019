import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int x = sc.nextInt();
        int y = sc.nextInt();

        int max = Math.max(x, y);
        int min = Math.min(x, y);

        n--;
        int l = 0;
        int r = max * n;
        int m = (l + r) / 2;
        while (l < r) {
            if ((m / x + m / y) < n) {
                l = m + 1;
            } else {
                r = m;
            }
            m = (l + r) / 2;
        }
        System.out.println(r + min);
    }
}