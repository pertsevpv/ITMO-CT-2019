package com.company;

import java.io.*;
import java.util.*;

public class Main {

/*    public static class Rule {
        private Character left;
        private String right;

        public Rule(String s) {
            String[] input = s.trim().split("->");
            this.left = input[0].trim().charAt(0);
            this.right = input.length == 1 ? "" : input[1].trim();
        }

        @Override
        public String toString() {
            return left + " -> " + right;
        }
    }*/

    private static File in = new File("nfc.in");
    private static File out = new File("nfc.out");
    private static Scanner sc;

    public static ArrayList<String>[] rules = new ArrayList[26];
    public static Character start;
    public static long dp[][][];

    private static boolean isTerm(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }

    public static long count(char[] word) {
        for (int i = 0; i < word.length; i++) {
            for (int j = 0; j < 26; j++) {
                if (rules[j] == null) continue;
                for (String k : rules[j]) {
                    if (k.length() == 1 && (word[i] == k.charAt(0))) dp[j][i][i]++;
                }
            }
        }
        for (int l = 0; l <= word.length; l++) {
            for (int r = 0; r <= word.length - l; r++) {
                int m = r + l - 1;
                for (int k = r; k < m; ++k) {
                    for (int i = 0; i < 26; ++i) {
                        if (rules[i] == null) continue;
                        for (String x : rules[i]) {
                            if (x.length() == 2) {
                                dp[i][r][m] = (dp[i][r][m] + dp[x.charAt(0) - 'A'][r][k] * dp[x.charAt(1) - 'A'][k + 1][m]) % 1000000007;
                            }
                        }
                    }
                }
            }
        }
        return dp[start - 'A'][0][word.length - 1];
    }

    public static void main(String[] args) throws IOException {
        sc = new Scanner(in);
        int n = sc.nextInt();
        start = sc.next().charAt(0);
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().trim().split("->");
            Character left = input[0].trim().charAt(0);
            String right = input.length == 1 ? "" : input[1].trim();

            if (rules[left - 'A'] == null) rules[left - 'A'] = new ArrayList<>();
            rules[left - 'A'].add(right);
        }
        String word = sc.next();
        dp = new long[26][word.length()][word.length()];
        writeInFile(Long.toString(count(word.toCharArray())));
    }
}