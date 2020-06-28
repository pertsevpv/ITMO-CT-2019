package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static File in;
    private static File out;
    private static int count;
    private static StringBuilder res;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }

    public static int getBit(int p, int i) {
        return (p >> i) & 1;
    }

    public static int[][] maskToMatrix(int mask) {
        int[][] res = new int[4][4];
        for (int i = 0; i < 16; i++) {
            res[i / 4][i % 4] = getBit(mask, i);
        }
        return res;
    }

    public static String toString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static boolean equal(int[][] m1, int[][] m2) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (m1[i][j] != m2[i][j]) return false;
            }
        }
        return true;
    }

    public static int[][] sq(int[][] m) {
        int[][] res = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int ij = 0;
                for (int k = 0; k < 4; k++) {
                    ij += m[i][k] * m[k][j];
                }
                res[i][j] = ij % 2;
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        in = new File("sqroot.in");
        out = new File("sqroot.out");
        Scanner sc = new Scanner(in);
        int[][] B = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                B[i][j] = sc.nextInt();
            }
        }
        System.out.println();
        for (int mask = 0; mask < 1 << 16; mask++) {
            int[][] m = maskToMatrix(mask);
            if (equal(sq(m),B)){
                writeInFile(toString(m));
                return;
            }
        }
        writeInFile("NO SOLUTION");
    }
}
