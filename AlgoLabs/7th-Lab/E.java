package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static File in;
    private static File out;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }

    public static class Apple {
        int a, b;
        int num;
        int delta;

        public Apple(int a, int b, int n) {
            this.a = a;
            this.b = b;
            this.num = n;
            this.delta = b - a;
        }
    }

    public static class PlusApple extends Apple implements Comparable<Apple> {

        public PlusApple(int a, int b, int n) {
            super(a, b, n);
        }

        @Override
        public int compareTo(Apple apple) {
            return Integer.compare(this.a, apple.a);
        }
    }

    public static class MinusApple extends Apple implements Comparable<Apple> {

        public MinusApple(int a, int b, int n) {
            super(a, b, n);
        }

        @Override
        public int compareTo(Apple apple) {
            return Integer.compare(apple.b, this.b);
        }
    }


    public static void main(String[] args) throws IOException {
        in = new File("apples.in");
        out = new File("apples.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int s = sc.nextInt();

        ArrayList<PlusApple> plus = new ArrayList<>();
        ArrayList<MinusApple> minus = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (b > a) {
                plus.add(new PlusApple(a, b, i + 1));
            } else {
                minus.add(new MinusApple(a, b, i + 1));
            }
        }
        Collections.sort(plus);
        Collections.sort(minus);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < plus.size(); i++) {
            if (s <= plus.get(i).a) {
                writeInFile("-1");
                return;
            }
            s += plus.get(i).delta;
            res.append(plus.get(i).num).append(" ");
        }
        for (int i = 0; i < minus.size(); i++) {
            if (s <= minus.get(i).a) {
                writeInFile("-1");
                return;
            }
            s += minus.get(i).delta;
            res.append(minus.get(i).num).append(" ");
        }
        writeInFile(res.toString());
    }
}
