import java.io.*;
import java.util.*;

public class Main {

    public static File in;
    public static File out;

    public static void writeInFile(String str) throws IOException {
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));

        bf.write(str);
        bf.flush();
        bf.close();
    }

    public static StringBuilder way = new StringBuilder();


    public static void getWay(int[][] arr) {
        Stack<String> w = new Stack<>();
        int i = arr.length - 1;
        int j = arr[0].length - 1;
        while (i != 0 || j != 0) {
            if (j != 0 && i != 0) {
                if (arr[i - 1][j] > arr[i][j - 1]) {
                    w.push("D");
                    i--;
                } else {
                    w.push("R");
                    j--;
                }
            } else if (i == 0) {
                w.push("R");
                j--;
            } else {
                w.push("D");
                i--;
            }
        }
        while (!w.isEmpty()) {
            way.append(w.pop()).append("");
        }
    }

    public static void main(String[] args) throws IOException {
        in = new File("input.txt");
        out = new File("output.txt");
        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        for (int i = 1; i < m; i++) {
            arr[0][i] = arr[0][i] + arr[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            arr[i][0] = arr[i][0] + arr[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]) + arr[i][j];
            }
        }
        getWay(arr);
        StringBuilder result = new StringBuilder(String.valueOf(arr[n - 1][m - 1])).append("\n").append(way.toString());
        writeInFile(result.toString().trim());
    }
}