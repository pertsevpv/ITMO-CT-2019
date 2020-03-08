
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long w = sc.nextLong();
        long h = sc.nextLong();
        long n = sc.nextLong();

        long max = Math.max(w, h);
        long min = Math.min(w, h);

        long l = 0L;
        long r = n * max;
        long m = (l + r) / 2;
        while (l < r) {
            if (((m/max) * (m / min)) < n)
            {
                l = m + 1;
            } else {
                r = m;
            }
            m = (l + r) / 2;
        }
        System.out.println(l);
    }
}