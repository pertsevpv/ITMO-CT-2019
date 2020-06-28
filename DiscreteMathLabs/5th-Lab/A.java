package com.company;

import java.io.*;
import java.util.*;

public class Main {

    private static File in = new File("automaton.in");
    private static File out = new File("automaton.out");
    private static Scanner sc;

    public static class NFA {
        private boolean[] T;
        private HashSet<Integer>[][] next;
        private int start;

        public NFA(boolean[] t, HashSet<Integer>[][] nxt, int st) {
            this.T = t;
            this.next = nxt;
            this.start = st;
        }

        public boolean check(char[] word) {
            HashSet<Integer> curs = new HashSet<>();
            curs.add(start);
            int pos = 0;
            boolean res = false;
            while (pos < word.length) {
                HashSet<Integer> tmp = new HashSet<>();
                for (Integer a : curs) {
                    HashSet<Integer> p = next[a][word[pos] - 'a'];
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

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }

    public static NFA readNFA() {
        int n = sc.nextInt();
        int begin = sc.next().charAt(0) - 'A';
        HashSet<Integer>[][] nxt = new HashSet[27][26];
        boolean[] T = new boolean[27];
        T[26] = true;
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String[] inp = sc.nextLine().split("->");
            String left = inp[0].trim();
            String right = inp[1].trim();

            int lt = left.charAt(0) - 'A';
            if (right.length() == 1) {
                int rt = right.charAt(0) - 'a';
                if (nxt[lt][rt] == null) {
                    nxt[lt][rt] = new HashSet<>();
                }
                nxt[lt][rt].add(26);
            } else {
                int rt1 = right.charAt(0) - 'a';
                int rt2 = right.charAt(1) - 'A';
                if (nxt[lt][rt1] == null) {
                    nxt[lt][rt1] = new HashSet<>();
                }
                nxt[lt][rt1].add(rt2);
            }
        }
        return new NFA(T, nxt, begin);
    }

    public static void main(String[] args) throws IOException {
        sc = new Scanner(in);
        NFA nfa = readNFA();
        StringBuilder res = new StringBuilder();
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            res.append(nfa.check(sc.next().toCharArray()) ? "yes" : "no").append("\n");
        }
        writeInFile(res.toString());
    }
}