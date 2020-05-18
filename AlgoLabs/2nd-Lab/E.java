import java.util.Scanner;

public class Main {



    static long[] stack = new long[100];
    static int pos = 0;

    static void add(long val) {
        stack[pos++] = val;
    }

    static long getLast() {
        return stack[--pos];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        add(sc.nextLong());
        add(sc.nextLong());
        while (sc.hasNext()) {
            String s = sc.next();
            switch (s) {
                case "+": {
                    long a = getLast();
                    long b = getLast();
                    add(a + b);
                    break;
                }
                case "-": {
                    long a = getLast();
                    long b = getLast();
                    add(b - a);
                    break;
                }
                case "*": {
                    long a = getLast();
                    long b = getLast();
                    add(a * b);
                    break;
                }
                default:
                    add(Long.parseLong(s));
                    break;
            }
        }
        System.out.println(stack[0]);
    }
}