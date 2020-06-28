package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static class Rule {
        private Character left;
        private String right;

        private int count;

        public Rule(String s) {
            String[] input = s.trim().split("->");
            this.left = input[0].trim().charAt(0);
            allNotTerms.add(this.left);
            this.right = input.length == 1 ? "" : input[1].trim();
            for (int i = 0; i < right.length(); i++) {
                if (!isTerm(right.charAt(i))) {
                    allNotTerms.add(right.charAt(i));
                    count++;
                }
            }
        }

        @Override
        public String toString() {
            return left + " -> " + right;
        }
    }

    private static File in = new File("useless.in");
    private static File out = new File("useless.out");
    private static Scanner sc;

    public static TreeSet<Character> allNotTerms = new TreeSet<>();

    public static char start;
    public static Rule[] rules;

    public static boolean[] isGenerating = new boolean[26];
    public static ArrayList<Integer>[] concernedRules = new ArrayList[26];
    public static ArrayDeque<Character> Q = new ArrayDeque<>();

    public static HashSet<Character> isReachable = new HashSet<>();

    private static boolean isTerm(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }

    public static void deleteNotGenerating() {
        while (!Q.isEmpty()) {
            char c = Q.pop();
            if (isGenerating[c - 'A']) continue;
            isGenerating[c - 'A'] = true;
            if (concernedRules[c - 'A'] != null) {
                for (int r : concernedRules[c - 'A']) {
                    rules[r].count--;
                    if (rules[r].count == 0 && !isGenerating[rules[r].left - 'A']) Q.add(rules[r].left);
                }
            }
        }
        ArrayList<Rule> tmp = new ArrayList<>();
        for (Rule rule : rules) {
            if (!isGenerating[rule.left - 'A']) continue;
            boolean isGood = true;
            for (int j = 0; j < rule.right.length(); j++) {
                if (isTerm(rule.right.charAt(j))) continue;
                if (!isGenerating[rule.right.charAt(j) - 'A']) {
                    isGood = false;
                    break;
                }
            }
            if (isGood) tmp.add(rule);
        }
        rules = new Rule[tmp.size()];
        for (int i = 0; i < rules.length; i++) {
            rules[i] = tmp.get(i);
        }
    }

    public static void deleteNotReachable() {
        if (!isGenerating[start - 'A']) {
            rules = new Rule[0];
            return;
        }
        isReachable.add(start);
        for (Rule rule : rules) {
            if (rule.left == start) {
                for (int j = 0; j < rule.right.length(); j++) {
                    if (!isTerm(rule.right.charAt(j))) {
                        dfs(rule.right.charAt(j));
                    }
                }
            }
        }
        ArrayList<Rule> tmp = new ArrayList<>();
        for (Rule rule : rules) {
            if (isReachable.contains(rule.left)) {
                tmp.add(rule);
            }
        }
        rules = new Rule[tmp.size()];
        for (int i = 0; i < rules.length; i++) {
            rules[i] = tmp.get(i);
        }
    }

    public static void dfs(char c) {
        if (isReachable.contains(c)) return;
        isReachable.add(c);
        for (Rule rule : rules) {
            if (rule.left == c) {
                for (int j = 0; j < rule.right.length(); j++) {
                    if (!isTerm(rule.right.charAt(j))) {
                        dfs(rule.right.charAt(j));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        sc = new Scanner(in);
        int n = sc.nextInt();
        rules = new Rule[n];
        start = sc.next().charAt(0);
        allNotTerms.add(start);
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            rules[i] = new Rule(sc.nextLine());
            if (rules[i].count == 0) {
                //isGenerating[rules[i].left - 'A'] = true;
                Q.push(rules[i].left);
            }
            for (int j = 0; j < rules[i].right.length(); j++) {
                if (!isTerm(rules[i].right.charAt(j))) {
                    if (concernedRules[rules[i].right.charAt(j) - 'A'] == null)
                        concernedRules[rules[i].right.charAt(j) - 'A'] = new ArrayList<>();
                    concernedRules[rules[i].right.charAt(j) - 'A'].add(i);
                }
            }
        }
        deleteNotGenerating();
        deleteNotReachable();

        HashSet<Character> newSet = new HashSet<>();
        for (Rule rule : rules) {
            newSet.add(rule.left);
        }

        allNotTerms.removeAll(newSet);

        StringBuilder sb = new StringBuilder();
        for (Character c : allNotTerms) {
            sb.append(c).append(" ");
        }
        writeInFile(sb.toString());
    }
}