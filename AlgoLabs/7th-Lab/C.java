package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static class Order implements Comparable<Order> {
        double cost;
        int num, price;

        public Order(int price, int num) {
            this.price = price;
            this.num = num;
            this.cost = (double) price / (double) num;
        }

        @Override
        public int compareTo(Order order) {
            return Double.compare(cost, order.cost);
        }

        @Override
        public String toString() {
            return "Order{" +
                    "cost=" + cost +
                    ", num=" + num +
                    ", price=" + price +
                    '}';
        }
    }

    private static File in;
    private static File out;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }

    public static int count(Order[] a, int n, int i) {
        if (n == 0) return 0;
        int res = (n / a[i].num) * a[i].price;
        return res + Math.min(a[i].price, count(a, n % a[i].num, i + 1));
    }

    public static void main(String[] args) throws IOException {
        in = new File("printing.in");
        out = new File("printing.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        Order[] a = new Order[7];
        for (int i = 0, num = 1; i < 7; i++, num *= 10) {
            a[i] = new Order(sc.nextInt(), num);
        }
        Arrays.sort(a);
        System.out.println();
        writeInFile(Integer.toString(count(a, n, 0)));
    }
}