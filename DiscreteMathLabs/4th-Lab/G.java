package com.company;

import java.io.*;
import java.util.*;

public class Main {

    private static File in;
    private static File out;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }

    public static class Pair<K, V> {
        public K key;
        public V value;

        public Pair(K k, V v) {
            this.key = k;
            this.value = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(key, pair.key) &&
                    Objects.equals(value, pair.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    public static class DFA {

        private boolean[] T;
        private int[][] next;

        public DFA(boolean[] t, int[][] nxt) {
            this.T = t;
            this.next = nxt;
        }

        public static boolean checkEqual(DFA dfa1, DFA dfa2) {
            ArrayDeque<Pair<Integer, Integer>> Q = new ArrayDeque<>();
            Q.push(new Pair<>(1, 1));
            boolean[][] used = new boolean[dfa1.next.length][dfa2.next.length];
            while (!Q.isEmpty()) {
                Pair<Integer, Integer> p = Q.pop();
                int u = p.key;
                int v = p.value;
                if (dfa1.T[u] != dfa2.T[v]) return false;
                used[u][v] = true;
                for (int i = 0; i < 26; i++) {
                    if (dfa1.next[u][i] != 0 || dfa2.next[v][i] != 0) {
                        if (!used[dfa1.next[u][i]][dfa2.next[v][i]]) {
                            Q.addLast(new Pair<>(dfa1.next[u][i], dfa2.next[v][i]));
                        }
                    }
                }
            }
            return true;
        }
    }


    public static void main(String[] args) throws IOException {
        in = new File("equivalence.in");
        out = new File("equivalence.out");
        Scanner sc = new Scanner(in);

        int n1 = sc.nextInt();
        int m1 = sc.nextInt();
        int k1 = sc.nextInt();
        int[][] nxt1 = new int[n1 + 1][26];
        boolean[] t1 = new boolean[n1 + 1];
        for (int i = 0; i < k1; i++) {
            t1[sc.nextInt()] = true;
        }
        for (int i = 0; i < m1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            char c = sc.next().charAt(0);
            nxt1[a][c - 'a'] = b;
        }

        int n2 = sc.nextInt();
        int m2 = sc.nextInt();
        int k2 = sc.nextInt();
        int[][] nxt2 = new int[n2 + 1][26];
        boolean[] t2 = new boolean[n2 + 1];
        for (int i = 0; i < k2; i++) {
            t2[sc.nextInt()] = true;
        }
        for (int i = 0; i < m2; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            char c = sc.next().charAt(0);
            nxt2[a][c - 'a'] = b;
        }

        DFA dfa1 = new DFA(t1, nxt1);
        DFA dfa2 = new DFA(t2, nxt2);
        writeInFile(DFA.checkEqual(dfa1, dfa2) ? "YES" : "NO");
    }
}