import java.util.*;

public class Main {
    
    public static StringBuilder way = new StringBuilder();
    
    public static void getLIS(int[] arr) {
        int n = arr.length;
        int[] d = new int[n];
        int[] s = new int[n];
        int max = Integer.MIN_VALUE;
        int pos = -1;

        for (int i = 0; i < n; i++) {
            d[i] = 1;
            s[i] = -1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && d[i] < (1 + d[j])) {
                    d[i] = 1 + d[j];
                    s[i] = j;
                }
            }
            if (max < d[i]) {
                max = d[i];
                pos = i;
            }
        }
        int tmp = -1;
        Stack<Integer> stack = new Stack<>();
        while (pos != -1) {
            stack.push(pos);
            d[pos] = tmp;
            tmp = pos;
            pos = s[pos];
        }
        int count = stack.size();
        way.append(count).append("\n");
        while (!stack.isEmpty()) {
            way.append(arr[stack.pop()]).append(" ");
        }
        System.out.println(way.toString());
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        getLIS(arr);
    }
}