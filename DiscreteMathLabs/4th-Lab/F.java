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

    public static class DFA {

        private boolean[] T;
        private Integer[][] next;
        private static boolean[] visited;
        private static int[] associations;

        private Integer d(int q, char c) {
            return next[q][c - 'a'];
        }

        public DFA(boolean[] t, Integer[][] nxt) {
            this.T = t;
            this.next = nxt;
        }

        public boolean isDevil(int q) {
            if (T[q]) return false;
            for (int i = 0; i < 26; i++) {
                if (next[q][i] != null) return false;
            }
            return true;
        }

        public static boolean checkIso(DFA first, DFA second, int u, int v) {
            visited[u] = true;
            if (first.T[u] != second.T[v]) return false;
            associations[u] = v;
            boolean res = true;
                for (int j = 0; j < 26; j++) {
                    if (first.next[u][j] == null || second.next[v][j] == null) {
                        if ((first.next[u][j] != null && second.next[v][j] == null) || (first.next[u][j] == null && second.next[v][j] != null)) {
                            return false;
                        }
                        continue;
                    }
                    int t1 = first.next[u][j];
                    int t2 = second.next[v][j];
                    if (first.isDevil(t1) != second.isDevil(t2)) return false;
                    if (visited[t1]) {
                        res = res && (t2 == associations[t1]);
                    } else {
                        res = res && checkIso(first, second, t1, t2);
                    }
                }
            return res;
        }
    }


    public static void main(String[] args) throws IOException {
        in = new File("isomorphism.in");
        out = new File("isomorphism.out");
        Scanner sc = new Scanner(in);

        int n1 = sc.nextInt();
        int m1 = sc.nextInt();
        int k1 = sc.nextInt();
        Integer[][] nxt1 = new Integer[n1][26];
        boolean[] t1 = new boolean[n1];
        for (int i = 0; i < k1; i++) {
            t1[sc.nextInt() - 1] = true;
        }
        for (int i = 0; i < m1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            char c = sc.next().charAt(0);
            nxt1[a - 1][c - 'a'] = b - 1;
        }

        int n2 = sc.nextInt();
        int m2 = sc.nextInt();
        int k2 = sc.nextInt();
        Integer[][] nxt2 = new Integer[n2][26];
        boolean[] t2 = new boolean[n2];
        for (int i = 0; i < k2; i++) {
            t2[sc.nextInt() - 1] = true;
        }
        for (int i = 0; i < m2; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            char c = sc.next().charAt(0);
            nxt2[a - 1][c - 'a'] = b - 1;
        }

        DFA dfa1 = new DFA(t1, nxt1);
        DFA dfa2 = new DFA(t2, nxt2);

        if (n1 != n2 || m1 != m2 || k1 != k2) {
            writeInFile("NO");
        } else {
            DFA.visited = new boolean[n1];
            DFA.associations = new int[n1];
            writeInFile(DFA.checkIso(dfa1, dfa2, 0, 0) ? "YES" : "NO");
        }
    }
}