import java.io.*;
import java.util.*;

public class Task24 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();

    public static int[] invalidRes;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.write(str);
        bf.flush();
        bf.close();
    }

    public static void reverse(int[] arr, int b) {
        for (int i = 0; i < (arr.length - b + 1) / 2; i++) {
            int tmp = arr[b + i];
            arr[b + i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }
    }

    public static int[] getNext(int[] arr) {
        int[] res = Arrays.copyOf(arr, arr.length);
        for (int i = res.length - 1; i >= 1; i--) {
            if (res[i - 1] < res[i]) {
                int min = i;
                for (int j = i; j < res.length; j++) {
                    if (res[j] < res[min] && res[j] > res[i - 1]) {
                        min = j;
                    }
                }
                int tmp = res[i - 1];
                res[i - 1] = res[min];
                res[min] = tmp;
                reverse(res, i);
                return res;
            }
        }
        return invalidRes;
    }

    public static int[] getPrev(int[] arr) {
        int[] res = Arrays.copyOf(arr, arr.length);
        for (int i = res.length - 1; i >= 1; i--) {
            if (res[i - 1] > res[i]) {
                int max = i;
                for (int j = i; j < res.length; j++) {
                    if (res[j] > res[max] && res[j] < res[i - 1]) {
                        max = j;
                    }
                }
                int tmp = res[i - 1];
                res[i - 1] = res[max];
                res[max] = tmp;
                reverse(res, i);
                return res;
            }
        }
        return invalidRes;
    }

    public static void f(int[] arr) {
        int[] p = getPrev(arr);
        for (int a : p) {
            result.append(a).append(" ");
        }
        result.append("\n");
        int[] n = getNext(arr);
        for (int a : n) {
            result.append(a).append(" ");
        }
    }

    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        in = new File("nextperm.in");
        out = new File("nextperm.out");

        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = sc.nextInt();
        }
        invalidRes = new int[n];
        Arrays.fill(invalidRes, 0);

        f(perm);
        writeInFile(result.toString());

        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }
}