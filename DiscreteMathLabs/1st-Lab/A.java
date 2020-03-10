package com.company;

import java.util.*;

public class A {


    public static boolean findWay(int[][] table, int node1, int node2) {
        Queue<Integer> queue = new ArrayDeque<>();

        int n = table.length;
        int[] nodes = new int[n];

        queue.add(node1);
        while (!queue.isEmpty()) {
            n = queue.remove();
            nodes[n] = 1;

            for (int i = 0; i < table.length; i++) {
                if (table[n][i] == 1 && nodes[i] == 0) {
                    queue.add(i);
                    nodes[i] = 1;
                    if (i == node2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] table = new int[2 * n][2 * n];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            if (a < 0) {
                a = Math.abs(a) - 1;
            } else {
                a += n - 1;
            }
            int b = sc.nextInt();
            if (b < 0) {
                b = Math.abs(b) - 1;
            } else {
                b += n - 1;
            }

            table[(a + n) % (2 * n)][b] = 1;
            table[(b + n) % (2 * n)][a] = 1;
        }
        boolean b = true;
        for (int i = 0; i < n; i++) {
            if (findWay(table, i, i + n) && findWay(table, i + n, i)) {
                b = false;
                break;
            }
        }

        if (!b) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
