package com.company;

import java.io.*;
import java.util.Arrays;
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
        in = new File("cobbler.in");
        out = new File("cobbler.out");
        Scanner sc = new Scanner(in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int time = k;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (time - arr[i] >= 0) {
                time -= arr[i];
                cnt++;
            } else break;
        }
        writeInFile(String.valueOf(cnt));
    }
}
