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

    public static class Pair<K, V> {
        public K key;
        public V value;
        public Pair(K k,V v) {
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

    public static DFA makeDFA(HashSet<Integer>[][] Q, boolean[] T) {
        HashSet<HashSet<Integer>> Qd = new HashSet<>();
        ArrayDeque<HashSet<Integer>> queue = new ArrayDeque<>();
        HashSet<Integer> s = new HashSet<>();
        s.add(0);
        queue.push(s);
        Qd.add(s);
        HashMap<Pair<HashSet<Integer>, Integer>, HashSet<Integer>> nxtd = new HashMap<>();
        while (!queue.isEmpty()) {
            HashSet<Integer> cur = queue.pop();
            for (int c = 0; c < 26; c++) {
                HashSet<Integer> nxt = new HashSet<>();
                for (Integer x : cur) {
                    if (Q[x][c] != null) {
                        nxt.addAll(Q[x][c]);
                    }
                }
                if (!Qd.contains(nxt) && !nxt.isEmpty()) {
                    Qd.add(nxt);
                    queue.add(nxt);
                }
                if (!nxt.isEmpty()) {
                    nxtd.put(new Pair<>(cur, c), nxt);
                }
            }
        }
        HashMap<HashSet<Integer>, Boolean> Tq = new HashMap<>();
        for (HashSet<Integer> q : Qd) {
            for (Integer qa : q) {
                if (T[qa]) {
                    Tq.put(q, true);
                    break;
                }
            }
        }
        HashMap<HashSet<Integer>, Integer> conv = new HashMap<>();
        int num = 0;
        for (HashSet<Integer> entry : Qd) {
            conv.put(entry, num++);
        }
        Integer[][] fnext = new Integer[Qd.size()][26];
        boolean[] fT = new boolean[Qd.size()];
        for (HashSet<Integer> entry : Qd) {
            for (int i = 0; i < 26; i++) {
                if (nxtd.containsKey(new Pair<>(entry, i))) {
                    fnext[conv.get(entry)][i] = conv.get(nxtd.get(new Pair<>(entry, i)));
                }
            }
        }
        for (Map.Entry<HashSet<Integer>, Boolean> t : Tq.entrySet()) {
            fT[conv.get(t.getKey())] = t.getValue();
        }
        return new DFA(fT, fnext);
    }

    public static void main(String[] args) throws IOException {
        in = new File("problem5.in");
        out = new File("problem5.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int l = sc.nextInt();
        HashSet<Integer>[][] nxt = new HashSet[n][26];

        boolean[] t = new boolean[n];
        for (int i = 0; i < k; i++) {
            t[sc.nextInt() - 1] = true;
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            char c = sc.next().charAt(0);
            if (nxt[a - 1][c - 'a'] == null) nxt[a - 1][c - 'a'] = new HashSet<>();
            nxt[a - 1][c - 'a'].add(b - 1);
        }
        DFA dfa = makeDFA(nxt, t);
        writeInFile(Long.toString(dfa.countWords(l)));
    }
}