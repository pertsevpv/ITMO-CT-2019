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

    public static class Request implements Comparable<Request> {
        int s;
        int f;

        public Request(int a, int b) {
            s = a;
            f = b;
        }

        @Override
        public int compareTo(Request req) {
            return Integer.compare(this.f, req.f);
        }

    }

    public static void main(String[] args) throws IOException {
        in = new File("request.in");
        out = new File("request.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        Request[] requests = new Request[n];
        for (int i = 0; i < n; i++) {
            requests[i] = new Request(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(requests);
        int time = 0;
        int cnt = 0;
        time = requests[0].f;
        cnt++;
        for (int i = 1; i < n; i++) {
            if (requests[i].s >= time) {
                time = requests[i].f;
                cnt++;
            }

        }
        writeInFile(String.valueOf(cnt));
    }
}
