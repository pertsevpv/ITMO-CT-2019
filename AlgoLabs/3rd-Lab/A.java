import java.io.*;
import java.util.*;

public class Main {

    public static File in;
    public static File out;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));

        bf.write(str);
        bf.flush();
        bf.close();
    }

    public static StringBuilder way = new StringBuilder();
    public static int jumps = 0;

    public static int getMin(int[] arr, int pos, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = pos - 1; i >= Math.max(pos - k, 0); i--) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int getMinPos(int[] arr, int pos, int k) {
        int max = arr[pos - 1];
        int res = pos - 1;
        for (int i = pos - 2; i >= Math.max(pos - k, 0); i--) {
            if (max < arr[i]) {
                max = arr[i];
                res = i;
            }
        }
        return res;
    }

    public static void getWay(int[] arr, int k) {

        Stack<Integer> w = new Stack<>();
        w.push(arr.length);
        int cur = arr.length - 1;
        while (cur > 0) {
            cur = getMinPos(arr, cur, k);
            w.push(cur + 1);
            jumps++;
        }
        while (!w.isEmpty()) {
            way.append(w.pop()).append(" ");
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("input.txt");
        out = new File("output.txt");
        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 1; i < n - 1; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 1; i < n; i++) {
            arr[i] = getMin(arr, i, k) + arr[i];
        }
        System.out.println();
        getWay(arr, k);
        StringBuilder result = new StringBuilder(String.valueOf(arr[n - 1])).append("\n").append(jumps).append("\n").append(way.toString());
        writeInFile(result.toString());
    }
}