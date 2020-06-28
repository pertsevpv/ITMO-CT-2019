package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static class MyScanner {
        private Reader reader;
        private char[] buf = new char[8192];
        private boolean hasNext = true;
        private int pos, len;

        public MyScanner(InputStream is) {
            reader = new BufferedReader(new InputStreamReader(is));
            len = 0;
            pos = 0;
            read();
        }

        public MyScanner(File file) {
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                pos = 0;
                len = 0;
                read();
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }

        public MyScanner(String str) {
            reader = new StringReader(str);
            pos = 0;
            len = 0;
            read();
        }


        public boolean hasNext() {
            nextChar();
            pos--;
            return hasNext;
        }

        public String next() {
            skipWhiteSpace();
            StringBuilder sb = new StringBuilder();
            while (hasNext()) {
                char c = nextChar();
                if (Character.isWhitespace(c)) {
                    break;
                }
                sb.append(c);

            }
            return sb.toString();
        }

        public String nextLine() {
            StringBuilder sb = new StringBuilder();
            char c;
            while (hasNext()) {
                c = nextChar();
                if (c == '\n') {
                    break;
                }
                if (c != '\r') {
                    sb.append(c);
                }
            }
            return sb.toString();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        private void skipWhiteSpace() {
            if (hasNext()) {
                char c = nextChar();
                while (hasNext && Character.isWhitespace(c)) {
                    c = nextChar();
                }
            }
            pos--;
        }


        private void read() {
            try {
                len = reader.read(buf);
                while (len == 0) {
                    len = reader.read(buf);
                }
                if (len < 8192 && len != -1) {
                    buf = Arrays.copyOf(buf, len);
                }
                if (len == -1) {
                    hasNext = false;
                }
                pos = 0;
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        private char nextChar() {
            if (pos >= len) {
                read();
            }
            return buf[pos++];
        }
    }

    public static class MySet {
        private static class SetNode {
            private String key;
            private String data;

            public SetNode(String k, String v) {
                this.key = k;
                this.data = v;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                SetNode setNode = (SetNode) o;
                return data.equals(setNode.data);
            }

        }

        public static final int HASH_CONST_A = 994_667;
        public static final int HASH_CONST_B = 1_063_409;
        private LinkedList<SetNode>[] table;

        public MySet() {
            this.table = new LinkedList[HASH_CONST_A];
        }

        public void insert(String k, String v) {
            delete(k);
            int h = hash(k);
            if (table[h] == null) table[h] = new LinkedList<>();
            table[h].add(new SetNode(k, v));
        }

        public boolean exists(String k) {
            int h = hash(k);
            if (table[h] == null) return false;
            for (SetNode s : table[h]) {
                if (s.key.equals(k)) return true;
            }
            return false;
        }

        public void delete(String k) {
            int h = hash(k);
            if (table[h] == null) return;
            table[h].removeIf(s -> s.key.equals(k));
        }

        public String get(String k) {
            int h = hash(k);
            if (table[h] == null) return null;
            for (SetNode s : table[h]) {
                if (s.key.equals(k)) return s.data;
            }
            return null;
        }

        private static int hash(String k) {
            long h = k.charAt(0) - 'a';
            for (int i = 1, n = 31; i < k.length(); i++, n *= 31) {
                h += (k.charAt(i) - 'a') * n;
            }
            int hash = (int) (h ^ h >>> 32);
            return Math.abs((hash * HASH_CONST_B) % HASH_CONST_A);
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
        in = new File("map.in");
        out = new File("map.out");
        MyScanner sc = new MyScanner(in);
        MySet set = new MySet();
        StringBuilder res = new StringBuilder();
        System.out.println(MySet.hash("hello warld"));
        while (sc.hasNext()) {
            switch (sc.next()) {
                case "put":
                    set.insert(sc.next(), sc.next());
                    break;
                case "delete":
                    set.delete(sc.next());
                    break;
                case "get":
                    String data = set.get(sc.next());
                    res.append(data == null ? "none" : data).append("\n");
                    break;
            }
        }
        writeInFile(res.toString());
    }
}