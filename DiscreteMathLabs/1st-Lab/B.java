import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B {

    public static int count(int[] arr) {
        int c = 0;
        for (int value : arr) {
            if (value != -1) {
                c++;
            }
        }
        return c;
    }

    public static boolean getF(int[][] func, HashMap<Integer, Boolean> arg, int k, int n) {
        boolean b = true;
        for (int i = 0; i < k; i++) {
            boolean b1 = false;
            if (count(func[i]) == 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                int a = func[i][j];
                if (a == 1) {
                    if (arg.get(j) == null) {
                        b1 = true;
                        break;
                    } else {
                        b1 = b1 || arg.get(j);
                    }
                } else if (a == 0) {
                    if (arg.get(j) == null) {
                        b1 = true;
                        break;
                    } else {
                        b1 = b1 || !arg.get(j);
                    }
                }

            }
            b = b && b1;
        }
        return b;
    }

    public static boolean getF1(int[][] func, HashMap<Integer, Boolean> arg, int k, int n) {
        boolean b = true;
        for (int i = 0; i < k; i++) {
            boolean b1 = false;
            if (count(func[i]) == 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                int a = func[i][j];
                if (a == 1) {
                    if (arg.get(j) != null) {
                        b1 = b1 || arg.get(j);
                    }
                } else if (a == 0) {
                    if (arg.get(j) != null) {
                        b1 = b1 || !arg.get(j);
                    } else {
                        b1 = true;
                    }
                }

            }
            b = b && b1;
        }
        return b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] func = new int[k][n];
        int[][] func1 = new int[k][n];

        boolean isZero = false;

        HashMap<Integer, Boolean> vals = new HashMap<>();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                int ni = sc.nextInt();
                func[i][j] = ni;
                func1[i][j] = ni;
            }
            if (count(func[i]) == 0) {
                isZero = true;
            }

            vals.put(i, null);
        }
        if (!isZero) {
            HashMap<Integer, Boolean> av;
            do {
                av = new HashMap<>();
                for (int i = 0; i < k; i++) {
                    int c = count(func[i]);
                    if (c == 1) {
                        for (int j = 0; j < func[i].length; j++) {
                            if (func[i][j] != -1) {
                                av.put(j, func[i][j] == 1);
                            }
                        }
                    }
                }
                if (!av.isEmpty()) {
                    for (Map.Entry<Integer, Boolean> entry : av.entrySet()) {
                        boolean val = entry.getValue();
                        int key = entry.getKey();
                        vals.put(key, val);
                        isZero = !getF(func1, vals, k, n);
                        for (int i = 0; i < k; i++) {
                            func[i][key] = -1;
                        }

                    }
                }
                if (isZero) {
                    break;
                }
            } while (!av.isEmpty());
        }

        if (!isZero) {
            isZero = !getF1(func1, vals, k, n);
        }

        if (isZero) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }
}