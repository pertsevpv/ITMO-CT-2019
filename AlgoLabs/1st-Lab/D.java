import java.util.Scanner;

public class Main {

    public static int[] heap = new int[100_000];
    public static int heapSize = 0;


    public static void swap(int i1, int i2) {
        int tmp = heap[i2];
        heap[i2] = heap[i1];
        heap[i1] = tmp;
    }

    public static void siftDown(int i) {
        int j;
        do {
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            j = i;
            if (l < heapSize) {
                if (heap[l] > heap[r]) {
                    j = l;
                }
            }
            if (r < heapSize) {
                if (heap[r] > heap[j]) {
                    j = r;
                }
            }
            if (j != i) {
                swap(i, j);
                siftDown(j);
            }
        } while (i != j);
    }

    public static void siftUp(int i) {
        while (i > 0) {
            int p = (i - 1) / 2;
            if (heap[i] < heap[p]) {
                break;
            }
            swap(i, p);
            i = p;
        }
    }

    public static int getMax() {
        swap(0, heapSize - 1);
        int max = heap[heapSize - 1];
        heap[heapSize - 1] = 0;
        heapSize--;
        siftDown(0);
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n > 0) {
            switch (sc.nextInt()) {
                case 0:
                    heap[heapSize] = sc.nextInt();
                    heapSize++;
                    siftUp(heapSize-1);
                    break;
                case 1:
                    System.out.println(getMax());
                    break;
            }
            n--;
        }
    }
}