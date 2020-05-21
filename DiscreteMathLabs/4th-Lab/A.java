package com.company;

import java.io.*;
import java.util.Scanner;

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
        private int next[][];

        private int d(int q, char c) {
            return next[q][c - 'a'] - 1;
        }

        public DFA(boolean[] t, int[][] nxt) {
            this.T = t;
            this.next = nxt;
        }

        public boolean check(char[] word) {
            int pos = 0;
            int curq = 0;
            while (pos < word.length) {
                int p = d(curq, word[pos]);
                if (p == -1) return false;
                else {
                    pos++;
                    curq = p;
                }
            }
            return T[curq];
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("problem1.in");
        out = new File("problem1.out");
        Scanner sc = new Scanner(in);
        char[] word = sc.next().toCharArray();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[][] nxt = new int[n][26];

        boolean[] t = new boolean[n];
        for (int i = 0; i < k; i++) {
            t[sc.nextInt() - 1] = true;
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            char c = sc.next().charAt(0);
            nxt[a - 1][c - 'a'] = b;
        }
        DFA dfa = new DFA(t, nxt);
        writeInFile(dfa.check(word) ? "Accepts" : "Rejects");
    }
}
