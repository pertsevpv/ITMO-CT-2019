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
        private boolean[] isGood;
        private boolean[] isVisited;
        private boolean[] isCycled;
        private Integer[][] next;
        private Integer[][] rev;
        private boolean isInfinite;


        private Integer d(int q, char c) {
            return next[q][c - 'a'];
        }

        public DFA(boolean[] t, Integer[][] nxt, Integer[][] rv) {
            this.T = t;
            this.next = nxt;
            this.rev = rv;
            int size = nxt.length;

            this.isGood = new boolean[size];
            this.isVisited = new boolean[size];
            this.isCycled = new boolean[size];

            for (int i = 0; i < size; i++) {
                if (t[i]) findGood(i);
            }

            Arrays.fill(this.isVisited, false);
            this.isInfinite = isInfinite(0);
        }

        void findGood(int q) {
            if (isVisited[q]) return;
            this.isVisited[q] = true;
            this.isGood[q] = true;
            for (int i = 0; i < 26; i++) {
                if (this.rev[q][i] != null) {
                    findGood(this.rev[q][i]);
                }
            }
        }

        boolean isInfinite(int q) {
            if (this.isVisited[q]) return false;
            this.isVisited[q] = true;
            this.isCycled[q] = true;
            for (int i = 0; i < 26; i++) {
                if (this.next[q][i] != null) {
                    if (this.isGood[next[q][i]] && isCycled[next[q][i]]) {
                        return true;
                    }
                    if (isInfinite(next[q][i])) return true;
                }
            }
            isCycled[q] = false;
            return false;
        }

        public long countWords() {
            if (this.isInfinite) return -1;
            return count(0);
        }

        public long count(int q) {
            long res = this.T[q] ? 1L : 0L;
            for (int i = 0; i < 26; i++) {
                if (this.next[q][i] != null && this.isGood[q]) {
                    if (this.next[q][i] != null && this.isGood[q]) {
                        res = (res + count(this.next[q][i])) % 1_000_000_007;
                    }
                }
            }
            return res;
        }

    }

    public static void main(String[] args) throws IOException {
        in = new File("problem3.in");
        out = new File("problem3.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        Integer[][] nxt = new Integer[n][26];
        Integer[][] rev = new Integer[n][26];

        boolean[] t = new boolean[n];
        for (int i = 0; i < k; i++) {
            t[sc.nextInt() - 1] = true;
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            char c = sc.next().charAt(0);
            nxt[a - 1][c - 'a'] = b - 1;
            rev[b - 1][c - 'a'] = a - 1;
        }
        DFA dfa = new DFA(t, nxt, rev);
        writeInFile(Long.toString(dfa.countWords()));
    }
}