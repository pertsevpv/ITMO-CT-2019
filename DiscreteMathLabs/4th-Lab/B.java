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

    public static class NFA {
        private boolean[] T;
        private HashSet<Integer>[][] next;

        private HashSet<Integer> d(int q, char c) {
            return next[q][c - 'a'];
        }

        public NFA(boolean[] t, HashSet<Integer>[][] nxt) {
            this.T = t;
            this.next = nxt;
        }

        public boolean check(char[] word) {
            HashSet<Integer> curs = new HashSet<>();
            curs.add(0);
            int pos = 0;
            boolean res = false;
            while (pos < word.length) {
                HashSet<Integer> tmp = new HashSet<>();
                for (Integer a : curs) {
                    HashSet<Integer> p = d(a, word[pos]);
                    if (p != null) tmp.addAll(p);
                }
                curs = tmp;
                pos++;
            }
            for (Integer a : curs) {
                res = res || T[a];
            }
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("problem2.in");
        out = new File("problem2.out");
        Scanner sc = new Scanner(in);
        char[] word = sc.next().toCharArray();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        HashSet<Integer>[][] nxt = new HashSet[n][26];

        boolean[] t = new boolean[n];
        for (int i = 0; i < k; i++) {
            t[sc.nextInt() - 1] = true;
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            char c = sc.next().charAt(0);
            if (nxt[a - 1][c - 'a'] == null) {
                nxt[a - 1][c - 'a'] = new HashSet<>();
            }
            nxt[a - 1][c - 'a'].add(b - 1);
        }
        NFA dfa = new NFA(t, nxt);
        writeInFile(dfa.check(word) ? "Accepts" : "Rejects");
    }
}
