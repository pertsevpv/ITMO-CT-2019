package com.company;

import java.io.*;
import java.util.*;

public class Task25 {

    public static File in;
    public static File out;

    public static StringBuilder result = new StringBuilder();

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.write(str);
        bf.flush();
        bf.close();
    }


    public static void f(int n, int k, int[] choose) {
        int min = n;
        for (int i = k - 1; i >= 0; i--) {
            if (choose[i] == min) {
                min--;
            } else {
                choose[i]++;
                for (int j = i + 1; j < k; j++) {
                    choose[j] = choose[j - 1] + 1;
                }
				break;
            }
        }
        if (min != n - k) {
            for (int a : choose) {
                result.append(a).append(" ");
            }
        } else {
            result.append("-1");
        }
    }

    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        in = new File("nextchoose.in");
        out = new File("nextchoose.out");

        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] choose = new int[k];
        for (int i = 0; i < k; i++) {
            choose[i] = sc.nextInt();
        }

        f(n, k, choose);
        writeInFile(result.toString());

        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }
}