package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static File in;
    private static File out;
    private static int count;
    private static StringBuilder res;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }

    public static void generate(String s, int n, int len) throws IOException {
        if (n == len) {
            count++;
            res.append(s).append("\n");
            return;
        }
        generate(s + "0", n + 1, len);
        if (!s.endsWith("1")) generate(s + "1", n + 1, len);
    }

    public static void main(String[] args) throws IOException {
        in = new File("vectors2.in");
        out = new File("vectors2.out");
        Scanner sc = new Scanner(in);
        count = 0;
        res = new StringBuilder();

        int n = sc.nextInt();
        generate("", 0, n);
        writeInFile(count + "\n" + res.toString());
    }
}
