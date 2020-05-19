import java.io.*;
import java.util.*;

public class Main {

    public static class Sum implements Comparable {
        public int sum = 0;
        int mask = 0;

        public Sum() {
            sum = 0;
            mask = 0;
        }

        public int compareTo(Sum s) {
            return this.sum - s.sum;
        }

        @Override
        public int compareTo(Object o) {
            return compareTo((Sum) o);
        }
    }

    public static class Cow implements Comparable {
        int weight = 0;
        int number = 0;

        public Cow(int w, int n) {
            this.weight = w;
            this.number = n;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    public static File in;
    public static File out;
    public static StringBuilder result = new StringBuilder("");

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
        bf.append(result.toString());
        bf.close();
    }

    public static int getBit(int p, int i) {
        return (p >> i) & 1;
    }

    public static void find(ArrayList<Cow> w, long b) {
        Object[] a = w.toArray();
        int n = a.length;
        int sizeL = 1 << (n / 2);
        int sizeR = 1 << (n - n / 2);
        Sum[] sumsL = new Sum[sizeL];
        Sum[] sumsR = new Sum[sizeR];
        for (int i = 0; i < sizeL; i++) {
            sumsL[i] = new Sum();
        }
        for (int i = 0; i < sizeR; i++) {
            sumsR[i] = new Sum();
        }
        for (int i = 0; i < sizeL; i++)
            for (int j = 0; j < n / 2; j++)
                if (getBit(i, j) == 1) {
                    sumsL[i].sum += ((Cow)(a[j])).weight;
                    sumsL[i].mask = i;
                }

        for (int i = 0; i < sizeR; i++)
            for (int j = 0; j < n - n / 2; j++)
                if (getBit(i, j) == 1) {
                    sumsR[i].sum += ((Cow)(a[j + n / 2])).weight;
                    sumsR[i].mask = i;
                }

        Arrays.sort(sumsL);
        Arrays.sort(sumsR);
        int left = 0;
        int right = sizeR - 1;
        long max = Long.MIN_VALUE;
        int mask1 = 0;
        int mask2 = 0;
        while (left < sizeL && right >= 0) {
            if (sumsL[left].sum + sumsR[right].sum <= b) {
                if (max < sumsL[left].sum + sumsR[right].sum) {
                    max = sumsL[left].sum + sumsR[right].sum;
                    mask1 = sumsL[left].mask;
                    mask2 = sumsR[right].mask;
                }
                ++left;
            } else {
                --right;
            }
        }
        int count = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n / 2; i++) {
            if (getBit(mask1, i) == 1) {
                res.append(w.get(i).number + 1).append(" ");
                w.set(i, null);
                count++;
            }
        }
        for (int i = 0; i < n - n / 2; i++) {
            if (getBit(mask2, i) == 1) {
                res.append(w.get((n / 2 + i)).number + 1).append(" ");
                w.set(n / 2 + i, null);
                count++;
            }
        }
        for (int i = 0; i < w.size(); i++) {
            if (w.get(i) == null) {
                w.remove(i);
                i--;
            }
        }
        result.append(count).append(" ").append(res.toString()).append("\n");
    }

    public static void main(String[] args) throws IOException {
        in = new File("skyscraper.in");
        out = new File("skyscraper.out");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int maxWeight = sc.nextInt();
        ArrayList<Cow> w = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            w.add(new Cow(sc.nextInt(), i));
        }
        int count = 0;
        while (!w.isEmpty()) {
            find(w, maxWeight);
            count++;
        }
        result = new StringBuilder(String.valueOf(count)).append("\n").append(result.toString());
        writeInFile(result.toString());
    }
}