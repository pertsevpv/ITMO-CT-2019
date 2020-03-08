import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static Double f(Double x) {
        return (x * x) + (Math.sqrt(x));
    }

    public static Double round(double n) {
        BigDecimal bd = new BigDecimal(String.valueOf(n));
        bd = bd.setScale(6, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double C = sc.nextDouble();
        double l = 0;
        double r = C;
        double m = (l + r) / 2;
        double y = f(m);
        while (l < r) {
            if (y == C) {
                break;
            } else if (y < C) {
                l = m;
            } else {
                r = m;
            }
            m = ((l + r) / 2);
            if (f(m) == y) {
                break;
            }
            y = f(m);
        }
        System.out.println(round(m));
    }
}
