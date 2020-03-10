import java.util.Scanner;

public class C {

    public static int[] func;

    public static String xorLine(String vec) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < vec.length() - 1; i++) {
            res.append((toBool(vec.charAt(i)) ^ toBool(vec.charAt(i + 1))) ? '1' : '0');
        }
        return res.toString();
    }

    public static boolean toBool(char c) {
        return c == '1';
    }

    public static int countOnes(String s) {
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                c++;
            }
        }
        return c;
    }

    public static String addNulls(String s, int n) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() + s.length() < n) {
            sb.append('0');
        }
        sb.append(s);
        if (sb.length() == 0) {
            sb.append('0');
        }
        return sb.toString();
    }

    public static boolean isM(String s, int v) {
        if (func.length == 1) {
            return true;
        }
        if (func[Integer.parseInt(s, 2)] < v) {
            return false;
        }
        v = func[Integer.parseInt(s, 2)];
        boolean b = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                StringBuilder s1 = new StringBuilder(s);
                s1.replace(i, i + 1, "1");
                b = b && isM(s1.toString(), v);
            }
        }
        return b;
    }

    public static boolean isL(String s) {
        StringBuilder res = new StringBuilder();
        res.append(s.charAt(0));
        while (s.length() > 1) {
            s = xorLine(s);
            res.append(s.charAt(0));
        }
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) == '1' && countOnes(Integer.toBinaryString(i)) > 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSd(String vec) {
        if (vec.length() == 1) {
            return false;
        }
        for (int i = 0; i < vec.length() / 2; i++) {
            if (vec.charAt(i) == vec.charAt(vec.length() - i - 1)) return false;
        }
        return true;
    }

    public static boolean saveZero(String vec) {
        return vec.charAt(0) == '0';
    }

    public static boolean saveOne(String vec) {
        return vec.charAt(vec.length() - 1) == '1';
    }

    public static void setF(int n, String vec) {
        func = new int[(int) Math.pow(2, n)];
        for (int i = 0; i < func.length; i++) {
            func[i] = Integer.parseInt(String.valueOf(vec.charAt(i)));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean k1 = false;
        boolean k2 = false;
        boolean k3 = false;
        boolean k4 = false;
        boolean k5 = false;

        for (int i = 0; i < n; i++) {
            int c = sc.nextInt();
            String vec = sc.next();
            setF(c, vec);
            k1 = k1 || !(saveZero(vec));
            k2 = k2 || !(saveOne(vec));
            k3 = k3 || !(isM(addNulls("", c), func[0]));
            k4 = k4 || !(isL(vec));
            k5 = k5 || !(isSd(vec));
        }
        if (k1 && k2 && k3 && k4 && k5) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

