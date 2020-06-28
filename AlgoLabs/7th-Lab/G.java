package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static File in;
    private static File out;

    public static int n;
    public static int r;
    public static int[] arr;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }

    public static void reverse(int[] arr, int b) {
        for (int i = 0; i < (arr.length - b + 1) / 2; i++) {
            int tmp = arr[b + i];
            arr[b + i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }
    }

    public static boolean nextPermutation(int[] arr) {
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
                System.arraycopy(res, 0, arr, 0, arr.length);
                return true;
            }
        }
        return false;
    }

    public static int getPrice() {
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += arr[i] * arr[i + 1];
        }
        return sum % r;
    }

    public static boolean isBeauty() {
        int pr = getPrice();
        int count = 0;
        if (pr == 0) return true;
        for (double i = 1D; i <= (int) Math.sqrt(pr); i++) {
            if (pr % i == 0) {
                count++;
                if (pr / i != i) count++;
            }
        }
        return count % 3 == 0;
    }

    public static void main(String[] args) throws IOException {
        in = new File("beautiful.in");
        out = new File("beautiful.out");
        Scanner sc = new Scanner(in);

        n = sc.nextInt();
        r = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        int count = 0;
        do {
            if (isBeauty()) count++;
        } while (nextPermutation(arr));
        writeInFile(Integer.toString(count));
    }
}
