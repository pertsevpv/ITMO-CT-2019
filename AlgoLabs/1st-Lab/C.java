import java.util.Arrays;
import java.util.Scanner;

public class C {

    public static long count;

    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int m = arr.length / 2;
        int[] l = Arrays.copyOfRange(arr, 0, m);
        int[] r = Arrays.copyOfRange(arr, m, arr.length);
        l = mergeSort(l);
        r = mergeSort(r);
        return merge(l, r);
    }

    public static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0;
        while (i < a.length || j < b.length) {
            if (i < a.length && (j == b.length || a[i] <= b[j])) {
                c[i + j] = a[i];
                i++;

            } else {
                c[i + j] = b[j];
                count += a.length - i;
                j++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        count = 0L;
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        mergeSort(arr);
        System.out.println(count);
    }
}
