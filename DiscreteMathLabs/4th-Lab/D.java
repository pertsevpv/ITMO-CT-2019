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


        private Integer d(int q, char c) {
            return next[q][c - 'a'];
        }

        public DFA(boolean[] t, Integer[][] nxt) {
            this.T = t;
            this.next = nxt;
        }

        public long countWords(int l) {
            long[] sum = new long[next.length];
            sum[0] = 1;
            for (int i = 0; i < l; i++) {
                long[] tmp = sum;
                sum = new long[next.length];
                for (int j = 0; j < next.length; j++) {
                    for (int k = 0; k < 26; k++) {
                        if (next[j][k] != null) {
                            sum[next[j][k]] = (sum[next[j][k]] + tmp[j]) % 1_000_000_007;
                        }

                    }
                }
            }
            long res = 0;
            for (int i = 0; i < next.length; i++) {
                if (T[i]) {
                    res = (res + sum[i]) % 1_000_000_007;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("problem4.in");
        out = new File("problem4.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int l = sc.nextInt();
        Integer[][] nxt = new Integer[n][26];

        boolean[] t = new boolean[n];
        for (int i = 0; i < k; i++) {
            t[sc.nextInt() - 1] = true;
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            char c = sc.next().charAt(0);
            nxt[a - 1][c - 'a'] = b - 1;
        }
        DFA dfa = new DFA(t, nxt);
        writeInFile(Long.toString(dfa.countWords(l)));
    }
}