import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {


    public static int Vp;
    public static int Vf;
    public static double a;

    public static double T(double m) {
        return (Math.hypot((1 - m), a) / Vf) + (Math.hypot(m, (1 - a)) / Vp);
    }

    public static Double round(double n) {
        BigDecimal bd = new BigDecimal(String.valueOf(n));
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Vp = input.nextInt();
        Vf = input.nextInt();
        a = input.nextDouble();

        double l = 0d;
        double r = 1d;

        double m1 = (2 * l + r) / 3;
        double m2 = (l + 2 * r) / 3;
        double m = (l + r)/2;
        while (r-l > 0) {
            if (T(m1) < T(m2)) {
                r = m2;
            } else if (T(m1) > T(m2)) {
                l = m1;
            } else {
                break;
            }
            m1 = (2 * l + r) / 3;
            m2 = (l + 2 * r) / 3;
            m = (m1 + m2)/2;
        }
        System.out.println(m);
    }
}
