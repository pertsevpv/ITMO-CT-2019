package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static class Element implements Comparable<Element> {
        int value;
        int num;

        public Element(int v, int n) {
            this.value = v;
            this.num = n;
        }

        @Override
        public int compareTo(Element element) {
            return Integer.compare(element.value,value );
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Element element = (Element) o;
            return value == element.value &&
                    num == element.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, num);
        }
    }

    private static File in;
    private static File out;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(str);
        bf.close();
    }


    public static void main(String[] args) throws IOException {
        in = new File("sequence.in");
        out = new File("sequence.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        Element[] a = new Element[n];
        long sumL = 0;
        long sumR = 0;
        for (int i = 0; i < n; i++)
            a[i] = new Element(sc.nextInt(), i + 1);

        Arrays.sort(a);
        ArrayList<Element> left = new ArrayList<>();
        ArrayList<Element> right = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (sumL < sumR) {
                left.add(a[i]);
                sumL += a[i].value;
            } else {
                right.add(a[i]);
                sumR += a[i].value;
            }
        }
        if (sumL != sumR) {
            writeInFile("-1");
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (left.size() < right.size()) {
            Collections.sort(left);
            sb.append(left.size()).append("\n");
            for (Element e : left) {
                sb.append(e.num).append(" ");
            }
        } else {
            Collections.sort(right);
            sb.append(right.size()).append("\n");
            for (Element e : right) {
                sb.append(e.num).append(" ");
            }
        }
        writeInFile(sb.toString());
    }
}