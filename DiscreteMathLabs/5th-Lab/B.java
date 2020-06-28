package com.company;

import java.io.*;
import java.util.*;

public class Main {

    private static File in = new File("epsilon.in");
    private static File out = new File("epsilon.out");
    private static Scanner sc;

    public static boolean[] isEps = new boolean[26];
    public static ArrayList<String>[] rules = new ArrayList[26];

    private static boolean isTerm(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }


    public static void main(String[] args) throws IOException {
        sc = new Scanner(in);
        int n = sc.nextInt();
        char start = sc.next().charAt(0);
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().trim().split("->");
            char left = input[0].trim().charAt(0);
            if (input.length == 1) {
                isEps[left - 'A'] = true;
            } else {
                String right = input[1].trim();
                boolean b = false;
                for (int j = 0; j < right.length(); j++) {
                    if (isTerm(right.charAt(j))) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    if (rules[left - 'A'] == null) rules[left - 'A'] = new ArrayList<>();
                    rules[left - 'A'].add(right);
                }
            }
        }
        boolean changes = true;
        while (changes) {
            changes = false;
            for (int i = 0; i < 26; i++) {
                if (isEps[i] || rules[i] == null) continue;
                for (String s : rules[i]) {
                    boolean b = true;
                    for (int j = 0; j < s.length(); j++) {
                        if (!isEps[s.charAt(j) - 'A']) {
                            b = false;
                            break;
                        }
                    }
                    if (b) {
                        isEps[i] = true;
                        changes = true;
                    }
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (isEps[i]) res.append((char) (i + 'A')).append("\n");
        }
        writeInFile(res.toString());
    }
}